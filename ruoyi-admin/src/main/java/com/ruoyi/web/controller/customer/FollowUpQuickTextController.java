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
import com.ruoyi.customer.domain.FollowUpQuickText;
import com.ruoyi.customer.service.IFollowUpQuickTextService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

*/
/**
 * 写跟进快捷文本Controller
 * 
 * @author tangJM.
 * @date 2023-10-25
 *//*

@RestController
@RequestMapping("/customer/customer")
public class FollowUpQuickTextController extends BaseController
{
    @Autowired
    private IFollowUpQuickTextService followUpQuickTextService;

    */
/**
     * 查询写跟进快捷文本列表
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:list')")
    @GetMapping("/list")
    public TableDataInfo list(FollowUpQuickText followUpQuickText)
    {
        startPage();
        List<FollowUpQuickText> list = followUpQuickTextService.selectFollowUpQuickTextList(followUpQuickText);
        return getDataTable(list);
    }

    */
/**
     * 导出写跟进快捷文本列表
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:export')")
    @Log(title = "写跟进快捷文本", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, FollowUpQuickText followUpQuickText)
    {
        List<FollowUpQuickText> list = followUpQuickTextService.selectFollowUpQuickTextList(followUpQuickText);
        ExcelUtil<FollowUpQuickText> util = new ExcelUtil<FollowUpQuickText>(FollowUpQuickText.class);
        util.exportExcel(response, list, "写跟进快捷文本数据");
    }

    */
/**
     * 获取写跟进快捷文本详细信息
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(followUpQuickTextService.selectFollowUpQuickTextById(id));
    }

    */
/**
     * 新增写跟进快捷文本
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:add')")
    @Log(title = "写跟进快捷文本", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody FollowUpQuickText followUpQuickText)
    {
        return toAjax(followUpQuickTextService.insertFollowUpQuickText(followUpQuickText));
    }

    */
/**
     * 修改写跟进快捷文本
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:edit')")
    @Log(title = "写跟进快捷文本", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody FollowUpQuickText followUpQuickText)
    {
        return toAjax(followUpQuickTextService.updateFollowUpQuickText(followUpQuickText));
    }

    */
/**
     * 删除写跟进快捷文本
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:remove')")
    @Log(title = "写跟进快捷文本", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(followUpQuickTextService.deleteFollowUpQuickTextByIds(ids));
    }
}
*/
