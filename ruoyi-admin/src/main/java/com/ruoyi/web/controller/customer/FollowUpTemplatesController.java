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
import com.ruoyi.customer.domain.FollowUpTemplates;
import com.ruoyi.customer.service.IFollowUpTemplatesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

*/
/**
 * 写跟进模板Controller
 * 
 * @author tangJM.
 * @date 2023-10-25
 *//*

@RestController
@RequestMapping("/customer/customer")
public class FollowUpTemplatesController extends BaseController
{
    @Autowired
    private IFollowUpTemplatesService followUpTemplatesService;

    */
/**
     * 查询写跟进模板列表
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:list')")
    @GetMapping("/list")
    public TableDataInfo list(FollowUpTemplates followUpTemplates)
    {
        startPage();
        List<FollowUpTemplates> list = followUpTemplatesService.selectFollowUpTemplatesList(followUpTemplates);
        return getDataTable(list);
    }

    */
/**
     * 导出写跟进模板列表
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:export')")
    @Log(title = "写跟进模板", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FollowUpTemplates followUpTemplates)
    {
        List<FollowUpTemplates> list = followUpTemplatesService.selectFollowUpTemplatesList(followUpTemplates);
        ExcelUtil<FollowUpTemplates> util = new ExcelUtil<FollowUpTemplates>(FollowUpTemplates.class);
        util.exportExcel(response, list, "写跟进模板数据");
    }

    */
/**
     * 获取写跟进模板详细信息
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(followUpTemplatesService.selectFollowUpTemplatesById(id));
    }

    */
/**
     * 新增写跟进模板
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:add')")
    @Log(title = "写跟进模板", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FollowUpTemplates followUpTemplates)
    {
        return toAjax(followUpTemplatesService.insertFollowUpTemplates(followUpTemplates));
    }

    */
/**
     * 修改写跟进模板
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:edit')")
    @Log(title = "写跟进模板", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FollowUpTemplates followUpTemplates)
    {
        return toAjax(followUpTemplatesService.updateFollowUpTemplates(followUpTemplates));
    }

    */
/**
     * 删除写跟进模板
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:remove')")
    @Log(title = "写跟进模板", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(followUpTemplatesService.deleteFollowUpTemplatesByIds(ids));
    }
}
*/
