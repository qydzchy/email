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
     * 定时发送草稿箱的任务 todo 存在问题，在进行发送任务的时候，因为使用了多线程，那么在下次执行该定时任务的时候，可能对原来邮件进行重复发送
     * 2分钟执行一次
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
