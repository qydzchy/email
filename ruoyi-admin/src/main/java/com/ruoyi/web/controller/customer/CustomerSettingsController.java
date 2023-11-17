package com.ruoyi.web.controller.customer;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.customer.service.IPublicleadsRulesService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 客户设置
 */
@RestController
@RequestMapping("/customer/customer/settings")
public class CustomerSettingsController extends BaseController {

    @Resource
    private IPublicleadsRulesService publicleadsRulesService;

    /**
     * 查询移入公海规则配置
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:publicleads:rules')")
    @GetMapping("/get/publicleads/rules")
    public AjaxResult getPublicleadsRules() {
        return success(publicleadsRulesService.getPublicleadsRules());
    }
}
