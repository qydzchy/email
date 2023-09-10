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

    public void syncEmail() {
        // 同步所有任务邮件
        taskService.syncAllTaskEmail();
    }
}
