package com.ruoyi.web.controller.customer;

import javax.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.customer.domain.Settings;
import com.ruoyi.customer.service.ISettingsService;

/**
 * 客户设置Controller
 * 
 * @author tangJM.
 * @date 2023-10-25
 */

@RestController
@RequestMapping("/customer/settings")
public class SettingsController extends BaseController
{
    @Resource
    private ISettingsService settingsService;


    /**
     * 查询客户设置
     */
    @PreAuthorize("@ss.hasPermi('customer:settings:get')")
    @GetMapping("/get")
    public AjaxResult get()
    {
        return success(settingsService.selectSettings());
    }


    /**
     * 修改客户设置
     */
    @PreAuthorize("@ss.hasPermi('customer:settings:edit')")
    @Log(title = "修改客户设置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody Settings settings)
    {
        return toAjax(settingsService.updateSettings(settings));
    }
}
