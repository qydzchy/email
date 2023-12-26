package com.ruoyi.email.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.email.EmailTypeEnum;
import com.ruoyi.common.enums.email.ImportStatusEnum;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.email.domain.Task;
import com.ruoyi.email.domain.vo.email.ImportListVO;
import com.ruoyi.email.domain.vo.task.TaskListVO;
import com.ruoyi.email.mapper.TaskEmailMapper;
import com.ruoyi.email.mapper.TaskMapper;
import com.ruoyi.email.service.ITaskService;
import com.ruoyi.email.service.handler.email.MailItemParser;
import com.ruoyi.email.service.handler.email.UniversalAttachment;
import com.ruoyi.email.service.handler.email.UniversalMail;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.ImportEmailMapper;
import com.ruoyi.email.domain.ImportEmail;
import com.ruoyi.email.service.IImportEmailService;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

/**
 * 导入邮件Service业务层处理
 * 
 * @author tangJM
 * @date 2023-07-31
 */
@Slf4j
@Service
public class ImportEmailServiceImpl implements IImportEmailService 
{
    @Resource
    private ImportEmailMapper importEmailMapper;
    @Resource
    private TaskMapper taskMapper;
    @Resource
    private TaskEmailMapper taskEmailMapper;
    @Resource
    private ITaskService taskService;

    @Value("${email.import.temp-path}")
    private String tempPath;
    @Value("${email.pull.path}")
    private String emailPath;
    @Value("${email.attachment.path}")
    private String attachmentPath;

    /**
     * 查询导入邮件
     * 
     * @param id 导入邮件主键
     * @return 导入邮件
     */
    @Override
    public ImportEmail selectImportEmailById(Long id)
    {
        return importEmailMapper.selectImportEmailById(id);
    }

    /**
     * 查询导入邮件列表
     * 
     * @param importEmail 导入邮件
     * @return 导入邮件
     */
    @Override
    public List<ImportEmail> selectImportEmailList(ImportEmail importEmail)
    {
        return importEmailMapper.selectImportEmailList(importEmail);
    }

    /**
     * 新增导入邮件
     * 
     * @param file
     * @return 结果
     */
    @Override
    public boolean insertImportEmail(MultipartFile file, Long taskId, String taskName, Long folderId, String folderName, Boolean filterEmailFlag)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();


        Long id = -1L;
        int mailTotal = 0;
        int addedCount = 0;
        int duplicateCount = 0;
        int filteredCount = 0;
        int failureCount = 0;
        int status = ImportStatusEnum.SUCCESS.getStatus();
        try {
            Task task = taskMapper.selectTaskById(taskId);
            if (task == null) {
                throw new ServiceException("没有该目标邮箱");
            }

            String fileName = file.getOriginalFilename();
            String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);

            ImportEmail importEmail = generateImportEmail(fileName, taskId, taskName, folderId, folderName, userId, username);
            // 保存导入邮件记录
            importEmailMapper.insertImportEmail(importEmail);

            List<Pair<String, String>> emlFileInfoList = new ArrayList<>();
            if ("eml".equals(fileExtension)) {
                InputStream emlStream = file.getInputStream();
                String tempFilePath = saveEmlToTempDirectory(emlStream, fileName);
                emlFileInfoList.add(Pair.of(fileName, tempFilePath));

            } else if ("zip".equals(fileExtension)) {
                File zipFile = convertMultiPartToFile(file);

                try (ZipInputStream zipInputStream = new ZipInputStream(FileUtils.openInputStream(zipFile))) {
                    ZipEntry entry;
                    while ((entry = zipInputStream.getNextEntry()) != null) {
                        if (!entry.isDirectory() && entry.getName().endsWith(".eml")) {
                            InputStream emlStream = zipInputStream;
                            // 使用上传的文件名作为临时文件名
                            String tempFileName = file.getOriginalFilename();
                            String tempFilePath = saveEmlToTempDirectory(emlStream, tempFileName);
                            emlFileInfoList.add(Pair.of(tempFileName, tempFilePath));
                        }
                    }
                }
            }

            folderId = folderId.intValue() > 0 ? folderId : -1L;
            int type = folderId.intValue() == -1 ? EmailTypeEnum.SEND.getType() : EmailTypeEnum.PULL.getType();

            String newEmailPath = emailPath.concat("/").concat(task.getAccount());
            String newAttachmentPath = attachmentPath.concat("/").concat(task.getAccount());


            mailTotal = emlFileInfoList.size();
            // 判断文件是否存在该数据库中
            for (Pair<String, String> emlFileInfo : emlFileInfoList) {
                try {
                    String emlFileName = emlFileInfo.getFirst();
                    String emlFilePath = emlFileInfo.getSecond();
                    Session session = Session.getDefaultInstance(new Properties());
                    MimeMessage mimeMessage = new MimeMessage(session, new FileInputStream(new File(emlFilePath)));
                    Date sentDate = mimeMessage.getSentDate();

                    int count = taskEmailMapper.countByEmlFileParam(taskId, folderId, emlFileName, sentDate);
                    if (count > 0) {
                        duplicateCount++;
                        if (Optional.ofNullable(filterEmailFlag).orElse(false)) {
                            filteredCount++;
                            continue;
                        }
                    }

                    UniversalMail universalMail = MailItemParser.parseMimeMessage(mimeMessage);
                    String emlPath = MailItemParser.saveMimiMessageAsLocalEml(mimeMessage, newEmailPath);
                    List<UniversalAttachment> universalAttachments = MailItemParser.parseAttachment(mimeMessage, newAttachmentPath);
                    universalMail.setAttachments(universalAttachments);
                    universalMail.setEmlPath(emlPath);
                    universalMail.setUid("import_" + System.currentTimeMillis());
                    MailItemParser.setMessageHeaderParam(mimeMessage, universalMail);

                    taskService.saveEmailData(taskId, folderId, type, universalMail);
                    addedCount++;
                } catch(Exception e) {
                    log.error("导入邮件操作异常：{}", e);
                    failureCount++;
                }
            }


        } catch (Exception e) {
            log.error("处理文件失败：{}", e);
            status = ImportStatusEnum.FAILURE.getStatus();
        } finally {
            // 在处理完成后删除临时目录及其内容
            deleteTempDirectory();

            // 更新导入邮件记录
            updateStatus(id, status, mailTotal, addedCount, duplicateCount, filteredCount, failureCount);
        }

        return true;
    }

    /**
     * 更新状态
     * @param id
     * @param status
     * @param mailTotal
     * @param addedCount
     * @param duplicateCount
     * @param filteredCount
     * @param failureCount
     */
    private void updateStatus(Long id, int status, int mailTotal, int addedCount, int duplicateCount, int filteredCount, int failureCount) {
        ImportEmail importEmail = new ImportEmail();
        importEmail.setId(id);
        importEmail.setImportStatus(status);
        importEmail.setMailTotal(mailTotal);
        importEmail.setAddedCount(addedCount);
        importEmail.setDuplicateCount(duplicateCount);
        importEmail.setFilteredCount(filteredCount);
        importEmail.setFailureCount(failureCount);
        importEmailMapper.updateImportEmail(importEmail);
    }

    /**
     * 生成导入记录
     * @param fileName
     * @param taskId
     * @param taskName
     * @param folderId
     * @param folderName
     * @param userId
     * @param username
     * @return
     */
    private ImportEmail generateImportEmail(String fileName, Long taskId, String taskName, Long folderId, String folderName, Long userId, String username) {
        Date now = new Date();
        ImportEmail importEmail = new ImportEmail();
        importEmail.setFileName(fileName);
        importEmail.setTaskId(taskId);
        importEmail.setTaskName(taskName);
        importEmail.setFolderId(folderId);
        importEmail.setFolderName(folderName);
        importEmail.setImportStatus(ImportStatusEnum.PROCESSING.getStatus());
        importEmail.setMailTotal(0);
        importEmail.setAddedCount(0);
        importEmail.setDuplicateCount(0);
        importEmail.setFilteredCount(0);
        importEmail.setFailureCount(0);
        importEmail.setCreateId(userId);
        importEmail.setCreateBy(username);
        importEmail.setCreateTime(now);
        importEmail.setUpdateId(userId);
        importEmail.setUpdateBy(username);
        importEmail.setUpdateTime(now);
        return importEmail;
    }

    /**
     * 保存eml文件到临时目录
     * @param emlStream
     * @param tempFileName
     * @throws IOException
     */
    private String saveEmlToTempDirectory(InputStream emlStream, String tempFileName) throws IOException {
        File tempFile = new File(tempPath, tempFileName);

        // 将 EML 内容保存到临时文件
        FileUtils.copyInputStreamToFile(emlStream, tempFile);
        return tempFile.getAbsolutePath();
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileUtils.copyInputStreamToFile(file.getInputStream(), convFile);
        return convFile;
    }

    /**
     * 删除临时目录
     */
    private void deleteTempDirectory() {
        try {
            FileUtils.deleteDirectory(new File(tempPath));
        } catch (IOException e) {
            log.error("删除临时目录失败：{}", e);
        }
    }

    /**
     * 修改导入邮件
     * 
     * @param importEmail 导入邮件
     * @return 结果
     */
    @Override
    public int updateImportEmail(ImportEmail importEmail)
    {
        importEmail.setUpdateTime(DateUtils.getNowDate());
        return importEmailMapper.updateImportEmail(importEmail);
    }

    /**
     * 批量删除导入邮件
     * 
     * @param ids 需要删除的导入邮件主键
     * @return 结果
     */
    @Override
    public int deleteImportEmailByIds(Long[] ids)
    {
        return importEmailMapper.deleteImportEmailByIds(ids);
    }

    /**
     * 删除导入邮件信息
     * 
     * @param id 导入邮件主键
     * @return 结果
     */
    @Override
    public int deleteImportEmailById(Long id)
    {
        return importEmailMapper.deleteImportEmailById(id);
    }

    /**
     * 邮箱任务列表
     * @return
     */
    @Override
    public List<TaskListVO> getTaskList() {
        List<Task> taskList = taskMapper.selectTaskList(new Task());
        List<TaskListVO> taskVOList = new ArrayList<>();
        for (Task task : taskList) {
            TaskListVO taskListVO = new TaskListVO();
            taskListVO.setId(task.getId());
            taskListVO.setAccount(task.getAccount());
            taskVOList.add(taskListVO);
        }

        return taskVOList;
    }

    /**
     * 导入邮件列表（分页）
     * @return
     */
    @Override
    public Pair<Integer, List<ImportListVO>> page() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();

        int count = importEmailMapper.count(userId);
        if (count == 0L) {
            return Pair.of(0, new ArrayList<>());
        }

        List<ImportListVO> importVOList = importEmailMapper.list(userId);
        return Pair.of(count, importVOList);
    }
}
