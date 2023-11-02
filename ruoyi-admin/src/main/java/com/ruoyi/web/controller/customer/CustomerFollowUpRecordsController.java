/*
package com.ruoyi.web.controller.customer;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.customer.domain.CustomerFollowUpRecords;
import com.ruoyi.customer.service.ICustomerFollowUpRecordsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

*/
/**
 * 客户写跟进Controller
 * 
 * @author tangJM.
 * @date 2023-11-02
 *//*

@RestController
@RequestMapping("/customer/customer")
public class CustomerFollowUpRecordsController extends BaseController
{
    @Autowired
    private ICustomerFollowUpRecordsService customerFollowUpRecordsService;

    */
/**
     * 查询客户写跟进列表
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:list')")
    @GetMapping("/list")
    public TableDataInfo list(CustomerFollowUpRecords customerFollowUpRecords)
    {
        startPage();
        List<CustomerFollowUpRecords> list = customerFollowUpRecordsService.selectCustomerFollowUpRecordsList(customerFollowUpRecords);
        return getDataTable(list);
    }

    */
/**
     * 导出客户写跟进列表
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:export')")
    @Log(title = "客户写跟进", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CustomerFollowUpRecords customerFollowUpRecords)
    {
        List<CustomerFollowUpRecords> list = customerFollowUpRecordsService.selectCustomerFollowUpRecordsList(customerFollowUpRecords);
        ExcelUtil<CustomerFollowUpRecords> util = new ExcelUtil<CustomerFollowUpRecords>(CustomerFollowUpRecords.class);
        util.exportExcel(response, list, "客户写跟进数据");
    }

    */
/**
     * 获取客户写跟进详细信息
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(customerFollowUpRecordsService.selectCustomerFollowUpRecordsById(id));
    }

    */
/**
     * 新增客户写跟进
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:add')")
    @Log(title = "客户写跟进", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CustomerFollowUpRecords customerFollowUpRecords)
    {
        return toAjax(customerFollowUpRecordsService.insertCustomerFollowUpRecords(customerFollowUpRecords));
    }

    */
/**
     * 修改客户写跟进
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:edit')")
    @Log(title = "客户写跟进", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CustomerFollowUpRecords customerFollowUpRecords)
    {
        return toAjax(customerFollowUpRecordsService.updateCustomerFollowUpRecords(customerFollowUpRecords));
    }

    */
/**
     * 删除客户写跟进
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:remove')")
    @Log(title = "客户写跟进", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(customerFollowUpRecordsService.deleteCustomerFollowUpRecordsByIds(ids));
    }
}
*/
