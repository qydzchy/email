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
import com.ruoyi.customer.domain.FollowUpRules;
import com.ruoyi.customer.service.IFollowUpRulesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

*/
/**
 * 客户跟进规则Controller
 * 
 * @author tangJM.
 * @date 2023-10-25
 *//*

@RestController
@RequestMapping("/customer/customer")
public class FollowUpRulesController extends BaseController
{
    @Autowired
    private IFollowUpRulesService followUpRulesService;

    */
/**
     * 查询客户跟进规则列表
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:list')")
    @GetMapping("/list")
    public TableDataInfo list(FollowUpRules followUpRules)
    {
        startPage();
        List<FollowUpRules> list = followUpRulesService.selectFollowUpRulesList(followUpRules);
        return getDataTable(list);
    }

    */
/**
     * 导出客户跟进规则列表
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:export')")
    @Log(title = "客户跟进规则", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FollowUpRules followUpRules)
    {
        List<FollowUpRules> list = followUpRulesService.selectFollowUpRulesList(followUpRules);
        ExcelUtil<FollowUpRules> util = new ExcelUtil<FollowUpRules>(FollowUpRules.class);
        util.exportExcel(response, list, "客户跟进规则数据");
    }

    */
/**
     * 获取客户跟进规则详细信息
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(followUpRulesService.selectFollowUpRulesById(id));
    }

    */
/**
     * 新增客户跟进规则
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:add')")
    @Log(title = "客户跟进规则", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FollowUpRules followUpRules)
    {
        return toAjax(followUpRulesService.insertFollowUpRules(followUpRules));
    }

    */
/**
     * 修改客户跟进规则
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:edit')")
    @Log(title = "客户跟进规则", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FollowUpRules followUpRules)
    {
        return toAjax(followUpRulesService.updateFollowUpRules(followUpRules));
    }

    */
/**
     * 删除客户跟进规则
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:remove')")
    @Log(title = "客户跟进规则", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(followUpRulesService.deleteFollowUpRulesByIds(ids));
    }
}
*/
