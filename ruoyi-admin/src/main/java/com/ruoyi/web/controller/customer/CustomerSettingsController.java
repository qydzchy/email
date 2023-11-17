package com.ruoyi.web.controller.customer;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.customer.service.IPacketService;
import com.ruoyi.customer.service.IPublicleadsRulesService;
import com.ruoyi.customer.service.ISourceService;
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
    @Resource
    private IPacketService packetService;
    @Resource
    private ISourceService sourceService;

    /**
     * 客户设置-移入公海规则列表
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:publicleads:rules:list')")
    @GetMapping("/publicleads/rules/list")
    public AjaxResult getPublicleadsRules() {
        return success(publicleadsRulesService.publicleadsRulesList());
    }

    /**
     * 客户设置-分组列表
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:packet:list')")
    @GetMapping("/packet/list")
    public AjaxResult packetList() {
        return success(packetService.packetList());
    }

    /**
     * 客户设置-客户来源列表
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:source:list')")
    @GetMapping("/source/list")
    public AjaxResult sourceList() {
        return success(sourceService.list());
    }
}
