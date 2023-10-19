package com.ruoyi.web.controller.email;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.email.domain.QuickText;
import com.ruoyi.email.domain.Template;
import com.ruoyi.email.service.ITemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/email/template")
@Validated
public class TemplateController extends BaseController {

    @Resource
    private ITemplateService templateService;

    /**
     * 模板列表
     */
    @PreAuthorize("@ss.hasPermi('email:template:list')")
    @GetMapping("/list")
    public AjaxResult list()
    {
        return AjaxResult.success(templateService.list());
    }

    /**
     * 新增模板
     */
    @PreAuthorize("@ss.hasPermi('email:template:add')")
    @Log(title = "新增模板", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody Template template)
    {
        if (StringUtils.isEmpty(template.getName())) {
            throw new ServiceException("模板名称不能为空");
        }

        if (StringUtils.isEmpty(template.getContent())) {
            throw new ServiceException("模板内容不能为空");
        }

        return toAjax(templateService.insertTemplate(template));
    }

    /**
     * 编辑模板
     */
    @PreAuthorize("@ss.hasPermi('email:template:edit')")
    @Log(title = "编辑模板", businessType = BusinessType.INSERT)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody Template template)
    {
        if (template.getId() == null) {
            throw new ServiceException("id不能为空");
        }

        if (StringUtils.isEmpty(template.getName())) {
            throw new ServiceException("模板名称不能为空");
        }

        if (StringUtils.isEmpty(template.getContent())) {
            throw new ServiceException("模板内容不能为空");
        }

        return toAjax(templateService.updateTemplate(template));
    }

    /**
     * 删除模板
     */
    @PreAuthorize("@ss.hasPermi('email:template:delete')")
    @Log(title = "删除模板", businessType = BusinessType.UPDATE)
    @PostMapping("/delete")
    public AjaxResult delete(@RequestBody Template template)
    {
        if (template.getId() == null) {
            throw new ServiceException("id不能为空");
        }

        return toAjax(templateService.delete(template.getId()));
    }
}
