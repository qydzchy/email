package com.ruoyi.quartz.task;

import com.ruoyi.email.service.ITaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 邮件任务
 */
@Slf4j
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
     * 10秒执行一次
     */
    public void sendEmail() {
        // 发送草稿箱的任务
        taskService.sendEmail();
    }

    /**
     * 异常邮箱检测
     */
    public void testEmail() {
        taskService.testEmail();
    }
}
