package com.ruoyi.email.service.impl;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.email.EmailTypeEnum;
import com.ruoyi.common.enums.email.ImportStatusEnum;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.UUID;
import com.ruoyi.email.domain.Folder;
import com.ruoyi.email.domain.Task;
import com.ruoyi.email.domain.vo.ImportListVO;
import com.ruoyi.email.domain.vo.TaskListVO;
import com.ruoyi.email.mapper.FolderMapper;
import com.ruoyi.email.mapper.TaskEmailMapper;
import com.ruoyi.email.mapper.TaskMapper;
import com.ruoyi.email.service.ITaskService;
import com.ruoyi.email.service.handler.email.MailItemParser;
import com.ruoyi.email.service.handler.email.UniversalAttachment;
import com.ruoyi.email.service.handler.email.UniversalMail;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.ImportEmailMapper;
import com.ruoyi.email.domain.ImportEmail;
import com.ruoyi.email.service.IImportEmailService;
import org.springframework.transaction.annotation.Transactional;
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
    private FolderMapper folderMapper;
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
    @Transactional(rollbackFor = Exception.class)
    public boolean insertImportEmail(MultipartFile file, Long taskId, Long folderId, Boolean filterEmailFlag)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();


        String tempDir = tempPath + "/" + UUID.fastUUID();
        Long id = -1L;
        int mailTotal = 0;
        int addedCount = 0;
        int duplicateCount = 0;
        int filteredCount = 0;
        int failureCount = 0;
        int status = ImportStatusEnum.SUCCESS.getStatus();
        String failureReasons = null;
        try {
            Task task = taskMapper.selectTaskById(taskId);
            if (task == null) {
                throw new ServiceException("没有该目标邮箱");
            }

            String folderName = "";
            if (folderId.longValue() == -2L) {
                folderName = "收件箱";
            } else if (folderId.longValue() == -1L) {
                folderName = "发件箱";
            } else {
                Folder folder = folderMapper.selectFolderById(folderId);
                if (folder == null) {
                    throw new ServiceException("没有该文件夹");
                }
                folderName = folder.getName();
            }

            String fileName = file.getOriginalFilename();
            String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);

            ImportEmail importEmail = generateImportEmail(fileName, taskId, task.getAccount(), folderId, folderName, userId, username);
            // 保存导入邮件记录
            importEmailMapper.insertImportEmail(importEmail);
            id = importEmail.getId();

            List<String> emlFilePathList = new ArrayList<>();
            if ("eml".equals(fileExtension)) {
                InputStream emlStream = file.getInputStream();
                String tempFilePath = saveEmlToTempDirectory(emlStream, tempDir, fileName);
                emlFilePathList.add(tempFilePath);

            } else if ("zip".equals(fileExtension)) {
                emlFilePathList.addAll(extractEmlFilesFromZip(file, tempDir));
            }

            folderId = folderId.intValue() > 0 ? folderId : -1L;
            int type = folderId.intValue() == -1 ? EmailTypeEnum.SEND.getType() : EmailTypeEnum.PULL.getType();

            String newEmailPath = emailPath.concat("/").concat(task.getAccount());
            String newAttachmentPath = attachmentPath.concat("/").concat(task.getAccount());

            mailTotal = emlFilePathList.size();
            // 判断文件是否存在该数据库中
            for (String emlFilePath : emlFilePathList) {
                FileInputStream fileInputStream = null;
                try {
                    Session session = Session.getDefaultInstance(new Properties());
                    fileInputStream = new FileInputStream(new File(emlFilePath));
                    MimeMessage mimeMessage = new MimeMessage(session, fileInputStream);
                    String title = mimeMessage.getSubject();
                    Date sentDate = mimeMessage.getSentDate();
                    int count = taskEmailMapper.countByEmlFileParam(taskId, folderId, title, sentDate);
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
                } finally {
                    fileInputStream.close();
                }
            }

        } catch (Exception e) {
            log.error("处理文件失败：{}", e);
            status = ImportStatusEnum.FAILURE.getStatus();
            failureReasons = e.getMessage();
        } finally {
            // 更新导入邮件记录
            updateStatus(id, status, failureReasons, mailTotal, addedCount, duplicateCount, filteredCount, failureCount);

            // 在处理完成后删除临时目录及其内容
            deleteTempDirectory(tempDir);
        }

        return true;
    }

    /**
     * 解压zip压缩包到指定目录
     * @param file
     * @param outputDirectory
     * @return
     * @throws IOException
     */
    private List<String> extractEmlFilesFromZip(MultipartFile file, String outputDirectory) {
        File tempDir = new File(outputDirectory);
        if (!tempDir.exists()) {
            tempDir.mkdirs();
        }

        List<String> emlFilePathList = new ArrayList<>();

        try (ZipInputStream zipInputStream = new ZipInputStream(file.getInputStream(), Charset.forName("GBK"))) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                if (!entry.isDirectory() && entry.getName().endsWith(".eml")) {
                    String tempFileName = FilenameUtils.getName(entry.getName());
                    String tempFilePath = outputDirectory + File.separator + tempFileName;

                    try (FileOutputStream fos = new FileOutputStream(tempFilePath)) {
                        IOUtils.copy(zipInputStream, fos);
                    }

                    emlFilePathList.add(tempFilePath);
                }
            }
        } catch (IOException e) {
            log.error("解压zip压缩包到指定目录异常：{}", e);
        }

        return emlFilePathList;
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
    private void updateStatus(Long id, int status, String failureReasons, int mailTotal, int addedCount, int duplicateCount, int filteredCount, int failureCount) {
        ImportEmail importEmail = new ImportEmail();
        importEmail.setId(id);
        importEmail.setImportStatus(status);
        importEmail.setFailureReasons(failureReasons);
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
     * @param path
     * @throws IOException
     */
    private String saveEmlToTempDirectory(InputStream emlStream, String path, String tempFileName) throws IOException {
        File tempDir = new File(path);
        if (!tempDir.exists()) {
            tempDir.mkdirs();
        }

        File tempFile = new File(tempDir, tempFileName);

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
    private void deleteTempDirectory(String path) {
        try {
            FileUtils.deleteDirectory(new File(path));
            log.info("临时目录删除成功");
        } catch (IOException e) {
            log.error("删除临时目录失败：{}", e.getMessage(), e);

            // 如果文件被占用等原因导致删除失败，您可以尝试延迟一段时间后再次尝试删除
            // 例如，等待1秒后再次尝试删除
            try {
                TimeUnit.SECONDS.sleep(1);
                FileUtils.deleteDirectory(new File(path));
                log.info("临时目录第二次删除成功");
            } catch (IOException | InterruptedException ex) {
                log.error("再次尝试删除临时目录失败：{}", ex.getMessage(), ex);
            }
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
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();

        Task taskParam = new Task();
        taskParam.setCreateId(userId);
        taskParam.setDelFlag("0");
        List<Task> taskList = taskMapper.selectTaskList(taskParam);
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
    public Pair<Integer, List<ImportListVO>> page(Integer pageNum, Integer pageSize) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();

        int count = importEmailMapper.count(userId);
        if (count == 0L) {
            return Pair.of(0, new ArrayList<>());
        }

        int offset = (pageNum - 1) * pageSize;
        int limit = pageSize;
        List<ImportListVO> importVOList = importEmailMapper.list(userId, offset, limit);
        return Pair.of(count, importVOList);
    }
}
