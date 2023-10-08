package com.ruoyi.web.controller.email;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.email.domain.Label;
import com.ruoyi.email.service.ILabelService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/email/label")
@Validated
public class LabelController extends BaseController {

    @Resource
    private ILabelService labelService;

    /**
     * 查询标签列表
     */
    @PreAuthorize("@ss.hasPermi('email:label:list')")
    @GetMapping("/list")
    public AjaxResult list()
    {
        return AjaxResult.success(labelService.list());
    }

    /**
     * 新增标签
     */
    @PreAuthorize("@ss.hasPermi('email:label:add')")
    @Log(title = "新增标签", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody Label label)
    {
        if (label.getName() == null) {
            throw new ServiceException("标签名称不能为空");
        }

        if (label.getColor() == null) {
            throw new ServiceException("标签颜色不能为空");
        }

        return toAjax(labelService.insertLabel(label));
    }

    /**
     * 修改标签颜色
     */
    @PreAuthorize("@ss.hasPermi('email:label:edit:color')")
    @Log(title = "修改标签颜色", businessType = BusinessType.UPDATE)
    @PostMapping("/edit/color")
    public AjaxResult editColor(@RequestBody Label label)
    {
        if (label.getId() == null) {
            throw new ServiceException("id不能为空");
        }

        if (label.getColor() == null) {
            throw new ServiceException("标签颜色不能为空");
        }

        return toAjax(labelService.editColor(label.getId(), label.getColor()));
    }

    /**
     * 修改标签名称
     */
    @PreAuthorize("@ss.hasPermi('email:label:edit:name')")
    @Log(title = "修改标签颜色", businessType = BusinessType.UPDATE)
    @PostMapping("/edit/name")
    public AjaxResult editName(@RequestBody Label label)
    {
        if (label.getId() == null) {
            throw new ServiceException("id不能为空");
        }

        if (label.getName() == null) {
            throw new ServiceException("标签名称不能为空");
        }

        return toAjax(labelService.editName(label.getId(), label.getName()));
    }

    /**
     * 删除标签
     */
    @PreAuthorize("@ss.hasPermi('email:label:delete')")
    @Log(title = "删除标签", businessType = BusinessType.UPDATE)
    @PostMapping("/delete")
    public AjaxResult delete(@RequestBody Label label)
    {
        if (label.getId() == null) {
            throw new ServiceException("id不能为空");
        }

        return toAjax(labelService.delete(label.getId()));
    }
}
