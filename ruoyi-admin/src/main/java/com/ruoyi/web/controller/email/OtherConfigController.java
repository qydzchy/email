package com.ruoyi.web.controller.email;

import javax.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.email.domain.OtherConfig;
import com.ruoyi.email.service.IOtherConfigService;

/**
 * 其他配置Controller
 * 
 * @author tangJM
 * @date 2023-12-28
 */
@RestController
@RequestMapping("/email/config")
public class OtherConfigController extends BaseController
{
    @Resource
    private IOtherConfigService otherConfigService;

    /**
     * 获取其他配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('email:config:get:info')")
    @GetMapping(value = "/get/info")
    public AjaxResult getInfo()
    {
        return success(otherConfigService.getInfo());
    }

    /**
     * 修改其他配置
     */
    @PreAuthorize("@ss.hasPermi('email:config:edit')")
    @Log(title = "修改其他配置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody OtherConfig otherConfig)
    {
        return toAjax(otherConfigService.updateOtherConfig(otherConfig));
    }
}
