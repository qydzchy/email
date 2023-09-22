package com.ruoyi.email.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.email.domain.bo.EmailAttachmentBO;
import com.ruoyi.email.domain.vo.attachment.AttachmentListVO;
import com.ruoyi.email.service.ITaskAttachmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.TaskAttachmentMapper;
import com.ruoyi.email.domain.TaskAttachment;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * 邮件附件Service业务层处理
 * 
 * @author tangJM
 * @date 2023-07-31
 */
@Slf4j
@Service
public class TaskAttachmentServiceImpl implements ITaskAttachmentService
{
    @Resource
    private TaskAttachmentMapper taskAttachmentMapper;

    @Value("${email.attachment.path}")
    private String uploadAttachmentPath;

    /**
     * 查询邮件附件
     * 
     * @param id 邮件附件主键
     * @return 邮件附件
     */
    @Override
    public TaskAttachment selectTaskAttachmentById(Long id)
    {
        return taskAttachmentMapper.selectTaskAttachmentById(id);
    }

    /**
     * 查询邮件附件列表
     * 
     * @param taskAttachment 邮件附件
     * @return 邮件附件
     */
    @Override
    public List<TaskAttachment> selectTaskAttachmentList(TaskAttachment taskAttachment)
    {
        return taskAttachmentMapper.selectTaskAttachmentList(taskAttachment);
    }

    /**
     * 新增邮件附件
     * 
     * @param taskAttachment 邮件附件
     * @return 结果
     */
    @Override
    public int insertTaskAttachment(TaskAttachment taskAttachment)
    {
        taskAttachment.setCreateTime(DateUtils.getNowDate());
        return taskAttachmentMapper.insertTaskAttachment(taskAttachment);
    }

    /**
     * 修改邮件附件
     * 
     * @param taskAttachment 邮件附件
     * @return 结果
     */
    @Override
    public int updateTaskAttachment(TaskAttachment taskAttachment)
    {
        taskAttachment.setUpdateTime(DateUtils.getNowDate());
        return taskAttachmentMapper.updateTaskAttachment(taskAttachment);
    }

    /**
     * 批量删除邮件附件
     * 
     * @param ids 需要删除的邮件附件主键
     * @return 结果
     */
    @Override
    public int deleteTaskAttachmentByIds(Long[] ids)
    {
        return taskAttachmentMapper.deleteTaskAttachmentByIds(ids);
    }

    /**
     * 删除邮件附件信息
     * 
     * @param id 邮件附件主键
     * @return 结果
     */
    @Override
    public int deleteTaskAttachmentById(Long id)
    {
        return taskAttachmentMapper.deleteTaskAttachmentById(id);
    }

    @Override
    public void batchInsertTaskAttachment(List<TaskAttachment> attachments) {
        taskAttachmentMapper.batchInsertTaskAttachment(attachments);
    }

    /**
     * 上传附件
     * @param files
     * @return
     */
    @Override
    @Transactional
    public List<AttachmentListVO> uploadAttachments(MultipartFile[] files) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        List<AttachmentListVO> attachmentList = new ArrayList<>();
        List<TaskAttachment> taskAttachmentList = new ArrayList<>();
        try {
            for (MultipartFile file : files) {
                // 获取文件信息
                String originalFilename = file.getOriginalFilename();
                long fileSize = file.getSize();
                String filePath = uploadAttachmentPath + File.separator + originalFilename;

                Date now = new Date();
                TaskAttachment emailAttachment = new TaskAttachment();
                emailAttachment.setName(originalFilename);
                emailAttachment.setSize(fileSize);
                emailAttachment.setPath(filePath);
                emailAttachment.setCreateId(userId);
                emailAttachment.setCreateBy(username);
                emailAttachment.setCreateTime(now);
                emailAttachment.setUpdateId(userId);
                emailAttachment.setUpdateBy(username);
                emailAttachment.setUpdateTime(now);
                taskAttachmentList.add(emailAttachment);

                // 创建上传目录（如果不存在）
                File uploadDir = new File(uploadAttachmentPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                // 保存文件到上传目录
                File destFile = new File(filePath);
                file.transferTo(destFile);
            }

            if (!taskAttachmentList.isEmpty()) {
                batchInsertTaskAttachment(taskAttachmentList);

                taskAttachmentList.stream().forEach(taskEmailAttachment -> {
                    attachmentList.add(AttachmentListVO.builder().id(taskEmailAttachment.getId()).name(taskEmailAttachment.getName()).size(taskEmailAttachment.getSize()).build());
                });
            }

        } catch (Exception e) {
            log.error("upload attachment exception: {}", e);
        }

        return attachmentList;
    }

    @Override
    public boolean rename(Long id, String name) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        TaskAttachment taskAttachment = taskAttachmentMapper.getById(id, userId);
        if (taskAttachment == null) {
            throw new ServiceException("不存在该附件");
        }
        String oldName = taskAttachment.getName();
        String suffix = oldName.substring(oldName.lastIndexOf("."));
        name += suffix;
        int affectedRow = taskAttachmentMapper.updateNameById(name, id, userId);
        return affectedRow > 0 ? true : false;
    }
    
    @Override
    public boolean delete(Long id) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        int affectedRow = taskAttachmentMapper.deleteById(id, userId);
        return affectedRow > 0 ? true : false;
    }

    @Override
    public List<EmailAttachmentBO> listByEmailIds(List<Long> emailIds) {
        return taskAttachmentMapper.selectByEmailIds(emailIds);
    }

    @Override
    public List<TaskAttachment> uploadAttachment(Path sourceFile) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        long fileSize = -1L;
        try {
            fileSize = Files.size(sourceFile);
        } catch (IOException e) {
            log.error("获取文件大小失败：{}", e);
        }
        String fileName = sourceFile.getFileName().toString();
        String filePath = uploadAttachmentPath + File.separator + fileName;

        Date now = new Date();
        TaskAttachment emailAttachment = new TaskAttachment();
        emailAttachment.setName(fileName);
        emailAttachment.setSize(fileSize);
        emailAttachment.setPath(filePath);
        emailAttachment.setCreateId(userId);
        emailAttachment.setCreateBy(username);
        emailAttachment.setCreateTime(now);
        emailAttachment.setUpdateId(userId);
        emailAttachment.setUpdateBy(username);
        emailAttachment.setUpdateTime(now);
        taskAttachmentMapper.insertTaskAttachment(emailAttachment);

        Path targetDir = Paths.get(uploadAttachmentPath);
        // 确保目标文件夹存在
        if (!Files.exists(targetDir)) {
            try {
                Files.createDirectories(targetDir);
            } catch (IOException e) {
                log.error("创建文件夹失败：{}", e);
            }
        }

        Path targetFile = targetDir.resolve(sourceFile.getFileName());
        try {
            Files.copy(sourceFile, targetFile);  // 从源文件复制到目标文件夹
        } catch (IOException e) {
            log.error("从源文件复制到目标文件夹失败：{}", e);
        }
        return Arrays.asList(emailAttachment);
    }

    @Override
    public List<String> getPathByEmailId(Long emailId) {
        return taskAttachmentMapper.getPathByEmailId(emailId);
    }
}
