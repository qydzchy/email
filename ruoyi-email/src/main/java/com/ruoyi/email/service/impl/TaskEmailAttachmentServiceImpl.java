package com.ruoyi.email.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.email.EmailTypeEnum;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.email.domain.vo.attachment.AttachmentListVO;
import com.ruoyi.email.service.ITaskEmailAttachmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.TaskEmailAttachmentMapper;
import com.ruoyi.email.domain.TaskEmailAttachment;
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
public class TaskEmailAttachmentServiceImpl implements ITaskEmailAttachmentService
{
    @Resource
    private TaskEmailAttachmentMapper taskEmailAttachmentMapper;

    @Value("${email.send.upload.attachment.path}")
    private String uploadAttachmentPath;

    /**
     * 查询邮件附件
     * 
     * @param id 邮件附件主键
     * @return 邮件附件
     */
    @Override
    public TaskEmailAttachment selectTaskEmailAttachmentById(Long id)
    {
        return taskEmailAttachmentMapper.selectTaskEmailAttachmentById(id);
    }

    /**
     * 查询邮件附件列表
     * 
     * @param taskEmailAttachment 邮件附件
     * @return 邮件附件
     */
    @Override
    public List<TaskEmailAttachment> selectTaskEmailAttachmentList(TaskEmailAttachment taskEmailAttachment)
    {
        return taskEmailAttachmentMapper.selectTaskEmailAttachmentList(taskEmailAttachment);
    }

    /**
     * 新增邮件附件
     * 
     * @param taskEmailAttachment 邮件附件
     * @return 结果
     */
    @Override
    public int insertTaskEmailAttachment(TaskEmailAttachment taskEmailAttachment)
    {
        taskEmailAttachment.setCreateTime(DateUtils.getNowDate());
        return taskEmailAttachmentMapper.insertTaskEmailAttachment(taskEmailAttachment);
    }

    /**
     * 修改邮件附件
     * 
     * @param taskEmailAttachment 邮件附件
     * @return 结果
     */
    @Override
    public int updateTaskEmailAttachment(TaskEmailAttachment taskEmailAttachment)
    {
        taskEmailAttachment.setUpdateTime(DateUtils.getNowDate());
        return taskEmailAttachmentMapper.updateTaskEmailAttachment(taskEmailAttachment);
    }

    /**
     * 批量删除邮件附件
     * 
     * @param ids 需要删除的邮件附件主键
     * @return 结果
     */
    @Override
    public int deleteTaskEmailAttachmentByIds(Long[] ids)
    {
        return taskEmailAttachmentMapper.deleteTaskEmailAttachmentByIds(ids);
    }

    /**
     * 删除邮件附件信息
     * 
     * @param id 邮件附件主键
     * @return 结果
     */
    @Override
    public int deleteTaskEmailAttachmentById(Long id)
    {
        return taskEmailAttachmentMapper.deleteTaskEmailAttachmentById(id);
    }

    @Override
    public void batchInsertTaskEmailAttachment(List<TaskEmailAttachment> emailAttachments) {
        taskEmailAttachmentMapper.batchInsertTaskEmailAttachment(emailAttachments);
    }

    @Override
    public void updateEmailIdByIds(Long emailId, List<Long> ids) {
        taskEmailAttachmentMapper.updateEmailIdByIds(emailId, ids);
    }

    @Override
    public List<TaskEmailAttachment> selectByEmailId(Long id) {
        return taskEmailAttachmentMapper.selectByEmailId(id);
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
        List<TaskEmailAttachment> taskEmailAttachmentList = new ArrayList<>();
        try {
            for (MultipartFile file : files) {
                // 获取文件信息
                String originalFilename = file.getOriginalFilename();
                long fileSize = file.getSize();
                String filePath = uploadAttachmentPath + File.separator + originalFilename;

                Date now = new Date();
                TaskEmailAttachment emailAttachment = new TaskEmailAttachment();
                emailAttachment.setType(EmailTypeEnum.SEND.getType());
                emailAttachment.setName(originalFilename);
                emailAttachment.setSize(fileSize);
                emailAttachment.setPath(filePath);
                emailAttachment.setCreateId(userId);
                emailAttachment.setCreateBy(username);
                emailAttachment.setCreateTime(now);
                emailAttachment.setUpdateId(userId);
                emailAttachment.setUpdateBy(username);
                emailAttachment.setUpdateTime(now);
                taskEmailAttachmentList.add(emailAttachment);

                // 创建上传目录（如果不存在）
                File uploadDir = new File(uploadAttachmentPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                // 保存文件到上传目录
                File destFile = new File(filePath);
                file.transferTo(destFile);
            }

            batchInsertTaskEmailAttachment(taskEmailAttachmentList);

            taskEmailAttachmentList.stream().forEach(taskEmailAttachment -> {
                attachmentList.add(AttachmentListVO.builder().id(taskEmailAttachment.getId()).name(taskEmailAttachment.getName()).size(taskEmailAttachment.getSize()).build());
            });

        } catch (Exception e) {
            log.error("upload attachment exception: {}", e);
        }

        return attachmentList;
    }

    @Override
    public boolean rename(Long id, String name) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        TaskEmailAttachment taskEmailAttachment = taskEmailAttachmentMapper.getById(id, userId);
        if (taskEmailAttachment == null) {
            throw new ServiceException("不存在该附件");
        }
        String oldName = taskEmailAttachment.getName();
        String suffix = oldName.substring(oldName.lastIndexOf("."));
        name += suffix;
        int affectedRow = taskEmailAttachmentMapper.updateNameById(name, id, userId);
        return affectedRow > 0 ? true : false;
    }
    
    @Override
    public boolean delete(Long id) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        int affectedRow = taskEmailAttachmentMapper.deleteById(id, userId);
        return affectedRow > 0 ? true : false;
    }

    @Override
    public List<AttachmentListVO> list(Long emailId) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        List<AttachmentListVO> attachmentListVOList = taskEmailAttachmentMapper.getByTaskIdAndEmailId(emailId, userId);
        return attachmentListVOList;
    }
}
