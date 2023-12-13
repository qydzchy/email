package com.ruoyi.web.controller.customer;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.customer.service.ICustomerEmailService;
import com.ruoyi.customer.service.ICustomerService;
import com.ruoyi.customer.service.IPacketService;
import com.ruoyi.customer.service.ISourceService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 邮件系统
 */
@RestController
@RequestMapping("/customer/email")
public class CustomerEmailController extends BaseController {

    @Resource
    private ICustomerService customerService;
    @Resource
    private IPacketService packetService;
    @Resource
    private ISourceService sourceService;
    @Resource
    private ICustomerEmailService customerEmailService;

    /**
     * 邮件系统-公海分组列表
     * @return
     */
    @PreAuthorize("@ss.hasPermi('customer:email:publicleads:groups:list')")
    @GetMapping("/publicleads/groups/list")
    public AjaxResult publicleadsGroupsList()
    {
        return success(customerEmailService.publicleadsGroupsList());
    }

    /**
     * 邮件系统-公海分组列表
     * @return
     */
    @PreAuthorize("@ss.hasPermi('customer:email:packet:list')")
    @GetMapping("/packet/list")
    public AjaxResult packetList() {
        return success(customerEmailService.packetList());
    }

    /**
     * 邮件系统-客户来源列表
     */
    @PreAuthorize("@ss.hasPermi('customer:email:source:list')")
    @GetMapping("/source/list")
    public AjaxResult sourceList() {
        return success(customerEmailService.sourceList());
    }


    /**
     * 邮件系统-客户星级列表
     */
    @PreAuthorize("@ss.hasPermi('customer:email:rating:list')")
    @GetMapping("/rating/list")
    public AjaxResult ratingList() {
        return success(customerEmailService.ratingList());
    }


}