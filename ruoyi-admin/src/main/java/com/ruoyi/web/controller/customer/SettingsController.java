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
import com.ruoyi.customer.domain.Settings;
import com.ruoyi.customer.service.ISettingsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

*/
/**
 * 客户设置Controller
 * 
 * @author tangJM.
 * @date 2023-10-25
 *//*

@RestController
@RequestMapping("/customer/customer")
public class SettingsController extends BaseController
{
    @Autowired
    private ISettingsService settingsService;

    */
/**
     * 查询客户设置列表
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:list')")
    @GetMapping("/list")
    public TableDataInfo list(Settings settings)
    {
        startPage();
        List<Settings> list = settingsService.selectSettingsList(settings);
        return getDataTable(list);
    }

    */
/**
     * 导出客户设置列表
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:export')")
    @Log(title = "客户设置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Settings settings)
    {
        List<Settings> list = settingsService.selectSettingsList(settings);
        ExcelUtil<Settings> util = new ExcelUtil<Settings>(Settings.class);
        util.exportExcel(response, list, "客户设置数据");
    }

    */
/**
     * 获取客户设置详细信息
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(settingsService.selectSettingsById(id));
    }

    */
/**
     * 新增客户设置
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:add')")
    @Log(title = "客户设置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Settings settings)
    {
        return toAjax(settingsService.insertSettings(settings));
    }

    */
/**
     * 修改客户设置
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:edit')")
    @Log(title = "客户设置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Settings settings)
    {
        return toAjax(settingsService.updateSettings(settings));
    }

    */
/**
     * 删除客户设置
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:remove')")
    @Log(title = "客户设置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(settingsService.deleteSettingsByIds(ids));
    }
}
*/
