package com.ruoyi.email.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.ProxyTypeEnum;
import com.ruoyi.common.enums.email.ConnStatusEnum;
import com.ruoyi.common.enums.email.EmailTypeEnum;
import com.ruoyi.common.enums.email.ProtocolTypeEnum;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.exception.mailbox.MailPlusException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.email.domain.*;
import com.ruoyi.email.domain.dto.EditTaskDTO;
import com.ruoyi.email.service.*;
import com.ruoyi.email.service.handler.email.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.TaskMapper;

/**
 * 邮箱任务Service业务层处理
 * 
 * @author tangJM
 * @date 2023-07-31
 */
@Slf4j
@Service
public class TaskServiceImpl implements ITaskService
{
    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private MailContext mailContext;

    @Autowired
    private IHostService hostService;

    @Autowired
    private ITaskEmailPullService taskEmailPullService;

    @Autowired
    private ITaskEmailContentService taskEmailContentService;

    @Autowired
    private ITaskEmailAttachmentService taskEmailAttachmentService;

    @Value("${email.path}")
    private String emailPath;

    /**
     * 查询邮箱任务
     * 
     * @param id 邮箱任务主键
     * @return 邮箱任务
     */
    @Override
    public Task selectTaskById(Long id)
    {
        return taskMapper.selectTaskById(id);
    }

    /**
     * 查询邮箱任务列表
     * 
     * @param task 邮箱任务
     * @return 邮箱任务
     */
    @Override
    public List<Task> selectTaskList(Task task)
    {
        return taskMapper.selectTaskList(task);
    }

    /**
     * 新增邮箱任务
     * 
     * @param task 邮箱任务
     * @return 结果
     */
    @Override
    public Boolean insertTask(Task task)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();

        // 是否存在邮箱账号
        if (isExistAccount(task.getAccount(), userId)) {
            throw new ServiceException("邮箱账号已存在");
        }

        // 获取协议连接配置信息
        List<Pair<ProtocolTypeEnum, MailConnCfg>> protocolConnCfgPairList = getProtocolConnCfg(task);

        boolean flag = false;
        MailConnCfg mailConnCfg = getMailConnCfg(task);
        int protocolType = 0;
        for (Pair<ProtocolTypeEnum, MailConnCfg> protocolConnCfgPair : protocolConnCfgPairList) {
            ProtocolTypeEnum protocolTypeEnum = protocolConnCfgPair.getFirst();
            MailConnCfg receivingServerInfo = protocolConnCfgPair.getSecond();

            // 邮箱连接
            mailConnCfg.setHost(receivingServerInfo.getHost());
            mailConnCfg.setPort(receivingServerInfo.getPort());
            mailConnCfg.setSsl(receivingServerInfo.isSsl());

            try {
                mailContext.createConn(protocolTypeEnum, mailConnCfg, Optional.ofNullable(task.getCustomProxyFlag()).orElse(false));
                flag = true;
                protocolType = protocolTypeEnum.getType();
                break;
            } catch (MailPlusException e) {
                log.error("邮箱连接失败{}，\n" +
                        "配置信息为{}", e, mailConnCfg);
            }
        }

        if (!flag) {
            throw new ServiceException("邮箱连接失败");
        }

        task.setProtocolType(protocolType);
        task.setReceivingServer(mailConnCfg.getHost());
        task.setReceivingPort(mailConnCfg.getPort());
        task.setReceivingSslFlag(mailConnCfg.isSsl());
        task.setOutgoingSslFlag(Optional.ofNullable(task.getOutgoingSslFlag()).orElse(false));
        task.setCreateId(loginUser.getUserId());
        task.setCreateBy(loginUser.getUsername());
        task.setCreateTime(DateUtils.getNowDate());
        task.setUpdateId(loginUser.getUserId());
        task.setUpdateBy(loginUser.getUsername());
        task.setUpdateTime(DateUtils.getNowDate());
        task.setConnStatus(ConnStatusEnum.NORMAL.getType());
        // 插入邮箱任务
        taskMapper.insertTask(task);

        // 获取邮箱邮件
        new Thread(() -> {
            pullEmail(task);
        }).start();

        return true;
    }

    /**
     * 获取协议连接配置信息
     * @return
     */
    private List<Pair<ProtocolTypeEnum, MailConnCfg>> getProtocolConnCfg(Task task) {
        List<Pair<ProtocolTypeEnum, MailConnCfg>> protocolConnCfgPairList = new ArrayList<>();
        if (task.getProtocolType() != null) {
            ProtocolTypeEnum protocolTypeEnum = ProtocolTypeEnum.getByType(task.getProtocolType());
            if (protocolTypeEnum == null) {
                throw new ServiceException("暂不支持该协议类型");
            }

            MailConnCfg mailConnCfg = MailConnCfg.builder().host(task.getReceivingServer()).port(task.getReceivingPort()).ssl(Optional.ofNullable(task.getReceivingSslFlag()).orElse(false)).build();
            protocolConnCfgPairList.add(Pair.of(protocolTypeEnum, mailConnCfg));
        } else {
            // 没有选择协议类型，则获取服务器信息
            String domain = task.getAccount().substring(task.getAccount().indexOf("@") + 1);
            Host host = hostService.selectHostByDomain(domain);
            if (host == null) {
                throw new ServiceException("暂不支持该邮箱类型");
            }

            if (StringUtils.isNotBlank(host.getImapHost()) && host.getImapPort() != null) {
                MailConnCfg imapMailConnCfg = MailConnCfg.builder().host(host.getImapHost()).port(host.getImapPort()).ssl(Optional.ofNullable(host.getImapSsl()).orElse(false)).build();
                protocolConnCfgPairList.add(Pair.of(ProtocolTypeEnum.IMAP, imapMailConnCfg));
            }

            if (StringUtils.isNotBlank(host.getPopHost()) && host.getPopPort() != null) {
                MailConnCfg pop3MailConnCfg = MailConnCfg.builder().host(host.getPopHost()).port(host.getPopPort()).ssl(Optional.ofNullable(host.getPopSsl()).orElse(false)).build();
                protocolConnCfgPairList.add(Pair.of(ProtocolTypeEnum.POP3, pop3MailConnCfg));
            }

            if (protocolConnCfgPairList.isEmpty()) {
                throw new ServiceException("暂不支持该邮箱类型");
            }
        }

        return protocolConnCfgPairList;
    }

    /**
     * 拉去邮件
     * @param task
     */
    private void pullEmail(Task task) {
        try {
            // 获取邮箱协议
            ProtocolTypeEnum protocolTypeEnum = ProtocolTypeEnum.getByType(task.getProtocolType());
            if (protocolTypeEnum == null) {
                log.info("暂不支持该协议类型");
                return;
            }

            // 邮箱连接
            MailConnCfg mailConnCfg = getMailConnCfg(task);
            MailConn mailConn = null;
            try {
                mailConn = mailContext.createConn(protocolTypeEnum, mailConnCfg, Optional.ofNullable(task.getCustomProxyFlag()).orElse(false));
            } catch (MailPlusException e) {
                log.error("邮箱连接失败");
                return;
            }

            List<MailItem> mailItems = mailContext.listAll(protocolTypeEnum, mailConn, "", null);
            if (mailItems == null || mailItems.size() == 0) {
                return;
            }

            for (MailItem mailItem : mailItems) {
                UniversalMail universalMail = mailContext.parseEmail(protocolTypeEnum, mailItem, emailPath);
                // 保存邮件信息
                saveEmailData(task.getId(), universalMail);
            }

        } catch (MailPlusException e) {
            log.error("邮件拉取失败，原始错误信息为【{}】", e);
        }
    }

    /**
     * 保存邮件数据
     * @param taskId
     * @param universalMail
     */
    private void saveEmailData(Long taskId, UniversalMail universalMail) {
        TaskEmailPull taskEmailPull = new TaskEmailPull();

        // 邮件
        BeanUtils.copyProperties(universalMail, taskEmailPull);
        taskEmailPull.setTaskId(taskId);
        taskEmailPull.setCreateTime(DateUtils.getNowDate());
        taskEmailPullService.insertTaskEmailPull(taskEmailPull);
        Long emailId = taskEmailPull.getId();

        // 邮件内容
        TaskEmailContent emailContent = new TaskEmailContent();
        emailContent.setEmailId(emailId);
        emailContent.setType(EmailTypeEnum.PULL.getType());
        emailContent.setContent(universalMail.getContent());
        emailContent.setCreateTime(DateUtils.getNowDate());
        taskEmailContentService.insertTaskEmailContent(emailContent);

        //邮件附件
        List<TaskEmailAttachment> emailAttachments = new ArrayList<>();
        List<UniversalAttachment> attachments = universalMail.getAttachments();
        if (attachments != null) {
            for (UniversalAttachment attachment : attachments) {
                TaskEmailAttachment emailAttachment = new TaskEmailAttachment();
                BeanUtils.copyProperties(attachment, emailAttachment);
                emailAttachment.setEmailId(emailId);
                emailAttachment.setType(EmailTypeEnum.PULL.getType());
                emailAttachment.setCreateTime(DateUtils.getNowDate());
                emailAttachments.add(emailAttachment);
            }

            taskEmailAttachmentService.batchInsertTaskEmailAttachment(emailAttachments);
        }
    }

    /**
     * 获取邮箱配置
     * @param task
     * @return
     */
    private MailConnCfg getMailConnCfg(Task task) {
        MailConnCfg mailConnCfg = new MailConnCfg();
        mailConnCfg.setEmail(task.getAccount());
        mailConnCfg.setPassword(task.getPassword());
        mailConnCfg.setHost(task.getReceivingServer());
        mailConnCfg.setPort(task.getReceivingPort());
        mailConnCfg.setSsl(Optional.ofNullable(task.getReceivingSslFlag()).orElse(false));

        Boolean customProxyFlag = task.getCustomProxyFlag();
        if (Optional.ofNullable(customProxyFlag).orElse(false)) return mailConnCfg;

        Integer proxyServerType = task.getProxyServerType();
        ProxyTypeEnum proxyTypeEnum = ProxyTypeEnum.getByType(proxyServerType);
        if (proxyTypeEnum == null) return mailConnCfg;

        mailConnCfg.setProxyType(proxyTypeEnum);
        switch (proxyTypeEnum) {
            case HTTP:
                mailConnCfg.setProxyHost(task.getProxyServer());
                mailConnCfg.setProxyPort(task.getProxyPort());
            case SOCKS:
                mailConnCfg.setSocksProxyHost(task.getProxyServer());
                mailConnCfg.setSocksProxyPort(task.getProxyPort());
            default:
                mailConnCfg.setProxyUsername(task.getProxyUsername());
                mailConnCfg.setProxyPassword(task.getProxyPassword());
        }

        return mailConnCfg;
    }

    /**
     * 是否存在邮箱账号
     * @param account
     */
    private boolean isExistAccount(String account, Long userId) {
        int count = taskMapper.countAccount(account, userId);
        return count > 0 ? true : false;
    }

    /**
     * 修改邮箱任务
     * 
     * @param editTaskDTO 邮箱任务
     * @return 结果
     */
    @Override
    public int updateTask(EditTaskDTO editTaskDTO)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        Task task = taskMapper.getTaskById(editTaskDTO.getId(), userId);
        BeanUtils.copyProperties(editTaskDTO, task);

        MailConnCfg mailConnCfg = getMailConnCfg(task);
        ProtocolTypeEnum protocolTypeEnum = ProtocolTypeEnum.getByType(task.getProtocolType());

        try {
            mailContext.createConn(protocolTypeEnum, mailConnCfg, Optional.ofNullable(task.getCustomProxyFlag()).orElse(false));
        } catch (MailPlusException e) {
            log.error("邮箱连接失败{}，\n" +
                    "配置信息为{}", e, mailConnCfg);
            throw new ServiceException("邮箱连接失败");
        }

        // 根据ID查询邮箱任务
        task.setUpdateId(userId);
        task.setUpdateBy(username);
        task.setUpdateTime(DateUtils.getNowDate());
        return taskMapper.updateTask(task);
    }

    /**
     * 批量删除邮箱任务
     * 
     * @param ids 需要删除的邮箱任务主键
     * @return 结果
     */
    @Override
    public int deleteTaskByIds(Long[] ids)
    {
        return taskMapper.deleteTaskByIds(ids);
    }

    /**
     * 删除邮箱任务信息
     * 
     * @param id 邮箱任务主键
     * @return 结果
     */
    @Override
    public int deleteTaskById(Long id)
    {
        return taskMapper.deleteTaskById(id);
    }

    /**
     * 邮箱检测
     * @param id
     * @return
     */
    @Override
    public Boolean test(Long id) {
        Task task = selectTaskById(id);

        if (task == null) {
            log.info("任务为空，id为【{}】", id);
            throw new ServiceException("任务为空");
        }

        // 获取邮箱协议
        ProtocolTypeEnum protocolTypeEnum = ProtocolTypeEnum.getByType(task.getProtocolType());
        if (protocolTypeEnum == null) {
            log.info("暂不支持该协议类型");
            throw new ServiceException("暂不支持该协议类型");
        }

        // 邮箱连接
        MailConnCfg mailConnCfg = getMailConnCfg(task);
        try {
            mailContext.createConn(protocolTypeEnum, mailConnCfg, Optional.ofNullable(task.getCustomProxyFlag()).orElse(false));
        } catch (MailPlusException e) {
            log.error("邮箱连接失败");
            return false;
        }

        return true;
    }
}
