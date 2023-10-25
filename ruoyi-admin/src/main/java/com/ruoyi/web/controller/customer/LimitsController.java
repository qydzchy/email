/*
package com.ruoyi.web.controller.customer;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.customer.domain.Limits;
import com.ruoyi.customer.service.ILimitsService;
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

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

*/
/**
 * 客户上限Controller
 * 
 * @author tangJM.
 * @date 2023-10-25
 *//*

@RestController
@RequestMapping("/customer/customer")
public class LimitsController extends BaseController
{
    @Autowired
    private ILimitsService limitsService;

    */
/**
     * 查询客户上限列表
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:list')")
    @GetMapping("/list")
    public TableDataInfo list(Limits limits)
    {
        startPage();
        List<Limits> list = limitsService.selectLimitsList(limits);
        return getDataTable(list);
    }

    */
/**
     * 导出客户上限列表
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:export')")
    @Log(title = "客户上限", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Limits limits)
    {
        List<Limits> list = limitsService.selectLimitsList(limits);
        ExcelUtil<Limits> util = new ExcelUtil<Limits>(Limits.class);
        util.exportExcel(response, list, "客户上限数据");
    }

    */
/**
     * 获取客户上限详细信息
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(limitsService.selectLimitsById(id));
    }

    */
/**
     * 新增客户上限
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:add')")
    @Log(title = "客户上限", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Limits limits)
    {
        return toAjax(limitsService.insertLimits(limits));
    }

    */
/**
     * 修改客户上限
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:edit')")
    @Log(title = "客户上限", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Limits limits)
    {
        return toAjax(limitsService.updateLimits(limits));
    }

    */
/**
     * 删除客户上限
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:remove')")
    @Log(title = "客户上限", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(limitsService.deleteLimitsByIds(ids));
    }
}
*/
