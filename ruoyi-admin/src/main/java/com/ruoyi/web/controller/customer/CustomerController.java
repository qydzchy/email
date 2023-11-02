package com.ruoyi.web.controller.customer;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

import com.ruoyi.customer.domain.dto.CustomerAddOrUpdateDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.customer.service.ICustomerService;

/**
 * 客户详情Controller
 * 
 * @author tangJM.
 * @date 2023-11-02
 */
@RestController
@RequestMapping("/customer/customer")
public class CustomerController extends BaseController
{
    @Resource
    private ICustomerService customerService;

    /**
     * 查询客户详情列表
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:list')")
    @GetMapping("/list")
    public AjaxResult list(
            Long segmentId,
            @NotNull(message = "私海/公海类型不能为空") Integer seaType,
            @NotNull(message = "页数不能为空") Integer pageNum,
            @NotNull(message = "页大小不能为空") Integer pageSize)
    {
        return success(customerService.list(segmentId, seaType, pageNum, pageSize));
    }

    /**
     * 获取客户详情详细信息
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(customerService.selectCustomerById(id));
    }

    /**
     * 新增客户详情
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:add')")
    @Log(title = "新增客户详情", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody CustomerAddOrUpdateDTO customerAddOrUpdateDTO)
    {
        return toAjax(customerService.insertCustomer(customerAddOrUpdateDTO));
    }

    /**
     * 修改客户详情
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:edit')")
    @Log(title = "修改客户详情", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody CustomerAddOrUpdateDTO customerAddOrUpdateDTO)
    {
        return toAjax(customerService.updateCustomer(customerAddOrUpdateDTO));
    }
}
