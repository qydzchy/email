package com.ruoyi.web.controller.email;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.email.domain.Label;
import com.ruoyi.email.domain.QuickText;
import com.ruoyi.email.service.IQuickTextService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/email/quick/text")
@Validated
public class QuickTextController extends BaseController {

    @Resource
    private IQuickTextService quickTextService;

    /**
     * 查询快速文本列表
     */
    @PreAuthorize("@ss.hasPermi('email:quick:text:list')")
    @GetMapping("/list")
    public AjaxResult list()
    {
        return AjaxResult.success(quickTextService.list());
    }

    /**
     * 新增快速文本
     */
    @PreAuthorize("@ss.hasPermi('email:quick:text:add')")
    @Log(title = "新增快速文本", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody QuickText quickText)
    {
        if (StringUtils.isEmpty(quickText.getTitle())) {
            throw new ServiceException("快速文本标题不能为空");
        }

        if (StringUtils.isEmpty(quickText.getContent())) {
            throw new ServiceException("快速文本内容不能为空");
        }

        if (StringUtils.isEmpty(quickText.getHtml())) {
            throw new ServiceException("快速文本html不能为空");
        }

        return toAjax(quickTextService.insertQuickText(quickText));
    }

    /**
     * 编辑快速文本
     * @param quickText
     * @return
     */
    @PreAuthorize("@ss.hasPermi('email:quick:text:edit')")
    @Log(title = "编辑快速文本", businessType = BusinessType.INSERT)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody QuickText quickText)
    {
        if (quickText.getId() == null) {
            throw new ServiceException("id不能为空");
        }

        if (StringUtils.isEmpty(quickText.getTitle())) {
            throw new ServiceException("快速文本标题不能为空");
        }

        if (StringUtils.isEmpty(quickText.getContent())) {
            throw new ServiceException("快速文本内容不能为空");
        }

        if (StringUtils.isEmpty(quickText.getHtml())) {
            throw new ServiceException("快速文本html不能为空");
        }

        return toAjax(quickTextService.updateQuickText(quickText));
    }

    /**
     * 删除快速文本
     */
    @PreAuthorize("@ss.hasPermi('email:quick:text:delete')")
    @Log(title = "删除快速文本", businessType = BusinessType.UPDATE)
    @PostMapping("/delete")
    public AjaxResult delete(@RequestBody QuickText quickText)
    {
        if (quickText.getId() == null) {
            throw new ServiceException("id不能为空");
        }

        return toAjax(quickTextService.delete(quickText.getId()));
    }
}
