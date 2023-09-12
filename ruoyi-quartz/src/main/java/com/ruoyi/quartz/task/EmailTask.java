package com.ruoyi.quartz.task;

import com.ruoyi.email.service.ITaskService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 邮件任务
 */
@Component("emailTask")
public class EmailTask {

    @Resource
    private ITaskService taskService;

    /**
     * 同步所有任务邮件
     * 5分钟执行一次
     */
    public void syncEmail() {
        // 同步所有任务邮件
        taskService.syncAllTaskEmail();
    }

    /**
     * 定时发送草稿箱的任务
     * 2分钟执行一次
     */
    public void sendEmail() {
        // 发送草稿箱的任务
        taskService.sendEmail();
    }
}
