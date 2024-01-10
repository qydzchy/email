package com.ruoyi.web.controller.email;

import javax.annotation.Resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.email.domain.General;
import com.ruoyi.email.service.IGeneralService;

/**
 * 邮箱常规Controller
 * 
 * @author tangJM
 * @date 2023-12-27
 */
@RestController
@RequestMapping("/email/general")
public class GeneralController extends BaseController
{
    @Resource
    private IGeneralService generalService;

    /**
     * 默认邮箱列表
     */
    @PreAuthorize("@ss.hasPermi('email:general:default:task:list')")
    @GetMapping(value = "/default/task/list")
    public AjaxResult defaultTaskList()
    {
        return success(generalService.defaultTaskList());
    }


    /**
     * 获取邮箱常规详细信息
     */
    @PreAuthorize("@ss.hasPermi('email:general:get:info')")
    @GetMapping(value = "/get/info")
    public AjaxResult getInfo()
    {
        return success(generalService.getInfo());
    }

    /**
     * 修改邮箱常规
     */
    @PreAuthorize("@ss.hasPermi('email:general:edit')")
    @Log(title = "修改邮箱常规", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody General general)
    {
        return toAjax(generalService.updateGeneral(general));
    }

}
