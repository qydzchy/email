package com.ruoyi.email.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.email.EmailTypeEnum;
import com.ruoyi.common.enums.email.TaskExecutionStatusEnum;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.email.domain.Task;
import com.ruoyi.email.domain.TaskEmailAttachment;
import com.ruoyi.email.domain.TaskEmailContent;
import com.ruoyi.email.domain.dto.email.EmailSendSaveDTO;
import com.ruoyi.email.service.ITaskEmailAttachmentService;
import com.ruoyi.email.service.ITaskEmailContentService;
import com.ruoyi.email.service.ITaskEmailSendService;
import com.ruoyi.email.service.ITaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.TaskEmailSendMapper;
import com.ruoyi.email.domain.TaskEmailSend;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * 发送邮件Service业务层处理
 * 
 * @author tangJM
 * @date 2023-07-31
 */
@Slf4j
@Service
public class TaskEmailSendServiceImpl implements ITaskEmailSendService
{
    @Resource
    private TaskEmailSendMapper taskEmailSendMapper;

    @Resource
    private ITaskEmailContentService taskEmailContentService;

    @Resource
    private ITaskEmailAttachmentService taskEmailAttachmentService;

    @Resource
    private ITaskService taskService;

    @Value("${email.send.upload.attachment.path}")
    private String uploadAttachmentPath;

    /**
     * 查询发送邮件
     * 
     * @param id 发送邮件主键
     * @return 发送邮件
     */
    @Override
    public TaskEmailSend selectTaskEmailSendById(Long id)
    {
        return taskEmailSendMapper.selectTaskEmailSendById(id);
    }

    /**
     * 查询发送邮件列表
     * 
     * @param taskEmailSend 发送邮件
     * @return 发送邮件
     */
    @Override
    public List<TaskEmailSend> selectTaskEmailSendList(TaskEmailSend taskEmailSend)
    {
        return taskEmailSendMapper.selectTaskEmailSendList(taskEmailSend);
    }

    /**
     * 新增发送邮件
     * 
     * @param taskEmailSend 发送邮件
     * @return 结果
     */
    @Override
    public int insertTaskEmailSend(TaskEmailSend taskEmailSend)
    {
        taskEmailSend.setCreateTime(DateUtils.getNowDate());
        return taskEmailSendMapper.insertTaskEmailSend(taskEmailSend);
    }

    /**
     * 修改发送邮件
     * 
     * @param taskEmailSend 发送邮件
     * @return 结果
     */
    @Override
    public int updateTaskEmailSend(TaskEmailSend taskEmailSend)
    {
        taskEmailSend.setUpdateTime(DateUtils.getNowDate());
        return taskEmailSendMapper.updateTaskEmailSend(taskEmailSend);
    }

    /**
     * 批量删除发送邮件
     * 
     * @param ids 需要删除的发送邮件主键
     * @return 结果
     */
    @Override
    public int deleteTaskEmailSendByIds(Long[] ids)
    {
        return taskEmailSendMapper.deleteTaskEmailSendByIds(ids);
    }

    /**
     * 删除发送邮件信息
     * 
     * @param id 发送邮件主键
     * @return 结果
     */
    @Override
    public int deleteTaskEmailSendById(Long id)
    {
        return taskEmailSendMapper.deleteTaskEmailSendById(id);
    }

    /**
     * 保存-（保存）
     * @param dto
     * @return
     */
    @Override
    public Long save(EmailSendSaveDTO dto) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();
        Date now = new Date();

        TaskEmailSend emailSend = new TaskEmailSend();
        BeanUtils.copyProperties(dto, emailSend);
        emailSend.setStatus(TaskExecutionStatusEnum.NOT_STARTED.getStatus());
        emailSend.setCreateId(userId);
        emailSend.setCreateBy(username);
        emailSend.setCreateTime(now);
        emailSend.setUpdateId(userId);
        emailSend.setUpdateBy(username);
        emailSend.setUpdateTime(now);
        taskEmailSendMapper.insertTaskEmailSend(emailSend);

        Long emailSendId = emailSend.getId();
        // 保存邮件内容
        TaskEmailContent emailContent = new TaskEmailContent();
        emailContent.setEmailId(emailSendId);
        emailContent.setContent(dto.getContent());
        emailContent.setType(EmailTypeEnum.SEND.getType());
        emailContent.setCreateId(userId);
        emailContent.setCreateBy(username);
        emailContent.setCreateTime(now);
        emailContent.setUpdateId(userId);
        emailContent.setUpdateBy(username);
        emailContent.setUpdateTime(now);
        taskEmailContentService.insertTaskEmailContent(emailContent);

        // 更新邮件附件的emailId
        List<Long> attachmentIdList = dto.getAttachmentIdList();
        taskEmailAttachmentService.updateEmailIdByIds(emailSendId, attachmentIdList);
        return emailSendId;
    }

    /**
     * 邮件发送-（写信）
     * @param id
     * @return
     */
    @Override
    public boolean send(Long id) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();

        TaskEmailSend taskEmailSend = taskEmailSendMapper.getTaskEmailSendById(id, userId);

        // 查询邮件内容
        TaskEmailContent emailContent = taskEmailContentService.selectTaskEmailContentById(id);
        // 查询附件
        List<TaskEmailAttachment> taskEmailAttachmentList = taskEmailAttachmentService.selectByEmailId(id);

        // 获取邮箱信息
        Long taskId = taskEmailSend.getTaskId();
        Task task = taskService.selectTaskById(taskId);
        boolean isSuccess = false;
        try {
            String[] mailTos = getEmails(taskEmailSend.getReceiver());
            String[] mailBccs = getEmails(taskEmailSend.getBcc());
            String[] mailCcs = getEmails(taskEmailSend.getCc());

            String title = taskEmailSend.getTitle();
            String content = emailContent != null ? emailContent.getContent() : null;

            String [] attachmentPaths = null;
            if (taskEmailAttachmentList != null && !taskEmailAttachmentList.isEmpty()) {
                List<String> filePathList =  new ArrayList<>();
                taskEmailAttachmentList.stream().forEach(taskEmailAttachment -> {
                    filePathList.add(taskEmailAttachment.getPath());
                });
                attachmentPaths = filePathList.toArray(new String[filePathList.size()]);
            }

            sendEmail(task.getOutgoingServer(), task.getOutgoingPort(), task.getOutgoingSslFlag(), task.getAccount(), task.getPassword(),
                    mailTos, mailCcs, mailBccs, title, content,
                    task.getProxyServer(), task.getProxyPort(),
                    task.getProxyUsername(), task.getProxyPassword(), attachmentPaths);

            isSuccess = true;
        } catch (MessagingException e) {
            log.error("messagingException e:{}", e);
            throw new ServiceException("发送失败");
        } catch (Exception e) {
            log.error("发送失败，e:{}", e);
            throw new ServiceException();
        }

        // 更新数据
        int status = isSuccess ? TaskExecutionStatusEnum.SUCCESS.getStatus() : TaskExecutionStatusEnum.FAILURE.getStatus();
        // 更新邮件发送状态
        updateStatusById(status, id);
        return true;
    }

    /**
     * 上传附件
     * @param taskId
     * @param files
     * @return
     */
    @Override
    @Transactional
    public boolean uploadAttachments(Long taskId, MultipartFile[] files) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        List<TaskEmailAttachment> taskEmailAttachmentList = new ArrayList<>();
        try {
            for (MultipartFile file : files) {
                // 获取文件信息
                String originalFilename = file.getOriginalFilename();
                long fileSize = file.getSize();
                String filePath = uploadAttachmentPath + File.separator + originalFilename;

                Date now = new Date();
                TaskEmailAttachment emailAttachment = new TaskEmailAttachment();
                emailAttachment.setTaskId(taskId);
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

            taskEmailAttachmentService.batchInsertTaskEmailAttachment(taskEmailAttachmentList);

        } catch (Exception e) {
            log.error("upload attachment exception: {}", e);
        }

        return true;
    }

    /**
     * 更新邮件发送状态
     * @param status
     * @param id
     */
    private void updateStatusById(int status, Long id) {
        taskEmailSendMapper.updateStatusById(status, id);
    }

    /**
     * 获取收件人、密送、抄送邮箱
     * @param mail
     */
    private String[] getEmails(String mail) throws Exception {
        if (StringUtils.isNotEmpty(mail)) {
            JSONArray mailJsonA = JSONObject.parseArray(mail);
            List<String> emailList = new ArrayList<>();
            mailJsonA.stream().forEach(receiverJson -> {
                JSONObject json = (JSONObject) receiverJson;
                emailList.add(json.getString("email"));
            });

            return emailList.toArray(new String[emailList.size()]);
        }

        return null;
    }

    /**
     * 发送邮件
     * @param host
     * @param port
     * @param useSSL
     * @param mailFrom
     * @param password
     * @param mailTos
     * @param mailCcs
     * @param mailBccs
     * @param title
     * @param content
     * @param proxyHost
     * @param proxyPort
     * @param attachmentPaths
     * @throws MessagingException
     */
    public void sendEmail(String host, Integer port, boolean useSSL, String mailFrom, String password,
                                                        String[] mailTos, String[] mailCcs, String[] mailBccs,
                                                        String title, String content, String proxyHost, Integer proxyPort,
                                                        String proxyUser, String proxyPassword, String[] attachmentPaths) throws MessagingException {
        Properties properties = new Properties();

        if (useSSL) {
            properties.put("mail.smtps.host", host);
            properties.put("mail.smtps.port", port);
            properties.put("mail.smtps.auth", "true");
            properties.put("mail.smtps.starttls.enable", "true");
            properties.put("mail.smtps.ssl.checkserveridentity", "true");
            properties.put("mail.smtps.ssl.trust", host);
        } else {
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", port);
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
        }

        if (proxyHost != null && !proxyHost.isEmpty()) {
            if (useSSL) {
                properties.put("mail.smtps.proxy.host", proxyHost);
                properties.put("mail.smtps.proxy.port", proxyPort);
                properties.put("mail.smtps.proxy.user", proxyUser);
                properties.put("mail.smtps.proxy.password", proxyPassword);
            } else {
                properties.put("mail.smtp.proxy.host", proxyHost);
                properties.put("mail.smtp.proxy.port", proxyPort);
                properties.put("mail.smtp.proxy.user", proxyUser);
                properties.put("mail.smtp.proxy.password", proxyPassword);
            }
        }

        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailFrom, password);
            }
        };

        Session session = Session.getInstance(properties, auth);

        Message msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress(mailFrom));
        // 设置收件人
        if (mailTos != null && mailTos.length > 0) {
            InternetAddress[] toAddresses = InternetAddress.parse(String.join(",", mailTos));
            msg.setRecipients(Message.RecipientType.TO, toAddresses);
        }

        // 设置抄送人
        if (mailCcs != null && mailCcs.length > 0) {
            InternetAddress[] ccAddresses = InternetAddress.parse(String.join(",", mailCcs));
            msg.setRecipients(Message.RecipientType.CC, ccAddresses);
        }

        // 设置密送人
        if (mailBccs != null && mailBccs.length > 0) {
            InternetAddress[] bccAddresses = InternetAddress.parse(String.join(",", mailBccs));
            msg.setRecipients(Message.RecipientType.BCC, bccAddresses);
        }

        msg.setSubject(title);

        // 创建多部分消息
        Multipart multipart = new MimeMultipart();

        // 创建文本消息部分
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText(content);
        multipart.addBodyPart(messageBodyPart);

        // 添加附件
        if (attachmentPaths != null && attachmentPaths.length > 0) {
            for (String attachmentPath : attachmentPaths) {
                MimeBodyPart attachmentPart = new MimeBodyPart();
                FileDataSource fileDataSource = new FileDataSource(attachmentPath);
                attachmentPart.setDataHandler(new DataHandler(fileDataSource));
                attachmentPart.setFileName(fileDataSource.getName());
                multipart.addBodyPart(attachmentPart);
            }
        }

        // 设置消息的多部分内容
        msg.setContent(multipart);

        if (useSSL) {
            Transport transport = session.getTransport("smtps");
            transport.connect(host, mailFrom, password);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
        } else {
            Transport.send(msg);
        }
    }

}
