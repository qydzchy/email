package com.ruoyi.quartz.task;

import com.ruoyi.common.utils.customer.CustomerShuffleThreadPoolUtil;
import com.ruoyi.customer.service.ICustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component("customerTask")
public class CustomerTask {

    @Resource
    private ICustomerService customerService;

    /**
     * 客户客群清洗
     * 一天执行一次
     */
    public void shuffle() {
        // 客户客群洗牌
        CustomerShuffleThreadPoolUtil.getThreadPool().execute(() -> customerService.shuffle(null, null));
    }


    /**
     * 移动公海规则处理
     * 一天执行一次
     */
    public void movePublicleadsRulesHandler() {
        // 移动公海规则处理
        customerService.movePublicleadsRulesHandler();
    }
}
