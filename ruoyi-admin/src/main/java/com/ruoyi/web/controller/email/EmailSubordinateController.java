package com.ruoyi.web.controller.email;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.customer.service.ICustomerEmailService;
import com.ruoyi.customer.service.ICustomerService;
import com.ruoyi.email.service.ITaskEmailService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 下属邮件
 */
@RestController
@RequestMapping("/email/subordinate/")
public class EmailSubordinateController extends BaseController {

    @Resource
    private ITaskEmailService taskEmailService;
    @Resource
    private ICustomerEmailService customerEmailService;

    /**
     * 下属列表
     */
    @PreAuthorize("@ss.hasPermi('email:subordinate:user:list')")
    @GetMapping("/user/list")
    public AjaxResult userList()
    {
        return success(taskEmailService.userList());
    }

    /**
     * 下属客户邮件类别列表
     */
    @PreAuthorize("@ss.hasPermi('email:subordinate:category:list')")
    @GetMapping("/category/list")
    public AjaxResult categoryList(Long userId)
    {
        return success(customerEmailService.categoryList(userId));
    }
}
