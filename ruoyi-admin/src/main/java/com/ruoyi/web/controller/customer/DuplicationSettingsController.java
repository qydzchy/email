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
import com.ruoyi.customer.domain.DuplicationSettings;
import com.ruoyi.customer.service.IDuplicationSettingsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

*/
/**
 * 客户查重设置Controller
 * 
 * @author tangJM.
 * @date 2023-10-25
 *//*

@RestController
@RequestMapping("/customer/customer")
public class DuplicationSettingsController extends BaseController
{
    @Autowired
    private IDuplicationSettingsService duplicationSettingsService;

    */
/**
     * 查询客户查重设置列表
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:list')")
    @GetMapping("/list")
    public TableDataInfo list(DuplicationSettings duplicationSettings)
    {
        startPage();
        List<DuplicationSettings> list = duplicationSettingsService.selectDuplicationSettingsList(duplicationSettings);
        return getDataTable(list);
    }

    */
/**
     * 导出客户查重设置列表
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:export')")
    @Log(title = "客户查重设置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DuplicationSettings duplicationSettings)
    {
        List<DuplicationSettings> list = duplicationSettingsService.selectDuplicationSettingsList(duplicationSettings);
        ExcelUtil<DuplicationSettings> util = new ExcelUtil<DuplicationSettings>(DuplicationSettings.class);
        util.exportExcel(response, list, "客户查重设置数据");
    }

    */
/**
     * 获取客户查重设置详细信息
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(duplicationSettingsService.selectDuplicationSettingsById(id));
    }

    */
/**
     * 新增客户查重设置
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:add')")
    @Log(title = "客户查重设置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DuplicationSettings duplicationSettings)
    {
        return toAjax(duplicationSettingsService.insertDuplicationSettings(duplicationSettings));
    }

    */
/**
     * 修改客户查重设置
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:edit')")
    @Log(title = "客户查重设置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DuplicationSettings duplicationSettings)
    {
        return toAjax(duplicationSettingsService.updateDuplicationSettings(duplicationSettings));
    }

    */
/**
     * 删除客户查重设置
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:remove')")
    @Log(title = "客户查重设置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(duplicationSettingsService.deleteDuplicationSettingsByIds(ids));
    }
}
*/
