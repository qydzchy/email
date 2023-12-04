package com.ruoyi.web.controller.email;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.email.domain.TemplateType;
import com.ruoyi.email.service.ITemplateTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/email/template/type")
@Validated
public class TemplateTypeController extends BaseController {

    @Resource
    private ITemplateTypeService templateTypeService;

    /**
     * 模板类型列表
     */
    @PreAuthorize("@ss.hasPermi('email:template:type:list')")
    @GetMapping("/list")
    public AjaxResult list()
    {
        return AjaxResult.success(templateTypeService.list());
    }

    /**
     * 新增模板类型
     */
    @PreAuthorize("@ss.hasPermi('email:template:type:add')")
    @Log(title = "新增模板类型", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody TemplateType templateType)
    {
        if (StringUtils.isEmpty(templateType.getName())) {
            throw new ServiceException("模板类型名称不能为空");
        }

        return toAjax(templateTypeService.insertTemplateType(templateType));
    }

    /**
     * 编辑快速文本
     * @param templateType
     * @return
     */
    @PreAuthorize("@ss.hasPermi('email:template:type:edit')")
    @Log(title = "编辑模板名称", businessType = BusinessType.INSERT)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody TemplateType templateType)
    {
        if (templateType.getId() == null) {
            throw new ServiceException("id不能为空");
        }

        if (StringUtils.isEmpty(templateType.getName())) {
            throw new ServiceException("快速文本标题不能为空");
        }

        return toAjax(templateTypeService.updateTemplateType(templateType));
    }

    /**
     * 删除快速文本
     */
    @PreAuthorize("@ss.hasPermi('email:template:type:delete')")
    @Log(title = "删除模板类型", businessType = BusinessType.UPDATE)
    @PostMapping("/delete")
    public AjaxResult delete(@RequestBody TemplateType templateType)
    {
        if (templateType.getId() == null) {
            throw new ServiceException("id不能为空");
        }

        return toAjax(templateTypeService.delete(templateType.getId()));
    }
}
