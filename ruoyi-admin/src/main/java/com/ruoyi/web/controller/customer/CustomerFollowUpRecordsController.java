package com.ruoyi.web.controller.customer;

import java.util.List;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.customer.domain.dto.CustomerFollowUpRecordsListDTO;
import com.ruoyi.customer.domain.vo.CustomerFollowUpRecordsListVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.customer.domain.CustomerFollowUpRecords;
import com.ruoyi.customer.service.ICustomerFollowUpRecordsService;

import javax.annotation.Resource;

/**
 * 客户写跟进Controller
 * 
 * @author tangJM.
 * @date 2023-11-02
 */

@RestController
@RequestMapping("/customer/customer/follow/up/records")
public class CustomerFollowUpRecordsController extends BaseController
{
    @Resource
    private ICustomerFollowUpRecordsService customerFollowUpRecordsService;

    /**
     * 查询客户写跟进列表
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:follow:up:records:list')")
    @GetMapping("/list")
    public AjaxResult list(CustomerFollowUpRecordsListDTO customerFollowUpRecordsListDTO)
    {
        if (customerFollowUpRecordsListDTO.getCustomerId() == null) {
            throw new ServiceException("客户ID不能为空");
        }
        List<CustomerFollowUpRecordsListVO> list = customerFollowUpRecordsService.list(customerFollowUpRecordsListDTO);
        return success(list);
    }

    /**
     * 新增客户写跟进
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:follow:up:records:add')")
    @Log(title = "新增客户写跟进", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody CustomerFollowUpRecords customerFollowUpRecords)
    {
        if (customerFollowUpRecords.getCustomerId() == null) {
            throw new ServiceException("客户ID不能为空");
        }

        checkParam(customerFollowUpRecords);
        return toAjax(customerFollowUpRecordsService.insertCustomerFollowUpRecords(customerFollowUpRecords));
    }

    /**
     * 修改客户写跟进
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:follow:up:records:edit')")
    @Log(title = "修改客户写跟进", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody CustomerFollowUpRecords customerFollowUpRecords)
    {
        if (customerFollowUpRecords.getId() == null) {
            throw new ServiceException("ID不能为空");
        }

        checkParam(customerFollowUpRecords);
        return toAjax(customerFollowUpRecordsService.updateCustomerFollowUpRecords(customerFollowUpRecords));
    }

    /**
     * 删除客户写跟进
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:follow:up:records:delete')")
    @Log(title = "删除客户写跟进", businessType = BusinessType.DELETE)
	@PostMapping("/delete")
    public AjaxResult delete(@RequestBody CustomerFollowUpRecords customerFollowUpRecords)
    {
        if (customerFollowUpRecords.getId() == null) {
            throw new ServiceException("ID不能为空");
        }

        return toAjax(customerFollowUpRecordsService.deleteCustomerFollowUpRecordsById(customerFollowUpRecords.getId()));
    }

    /**
     * 参数校验
     * @param customerFollowUpRecords
     */
    private void checkParam(CustomerFollowUpRecords customerFollowUpRecords) {
        if (customerFollowUpRecords.getFollowUpType() == null) {
            throw new ServiceException("跟进类型不能为空");
        }
        if (StringUtils.isBlank(customerFollowUpRecords.getFollowUpContent())) {
            throw new ServiceException("跟进内容不能为空");
        }
        if (customerFollowUpRecords.getSubmissionTime() == null) {
            throw new ServiceException("提交时间不能为空");
        }
        if (customerFollowUpRecords.getFollowUpContactId() == null) {
            throw new ServiceException("跟进联系人不能为空");
        }
    }
}
