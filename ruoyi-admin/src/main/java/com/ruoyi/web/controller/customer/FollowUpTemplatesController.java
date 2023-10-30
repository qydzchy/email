package com.ruoyi.web.controller.customer;

import java.util.List;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.customer.domain.vo.FollowUpTemplatesListVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.customer.domain.FollowUpTemplates;
import com.ruoyi.customer.service.IFollowUpTemplatesService;

import javax.annotation.Resource;

/**
 * 写跟进模板Controller
 * 
 * @author tangJM.
 * @date 2023-10-25
 */

@RestController
@RequestMapping("/customer/follow/up/templates")
public class FollowUpTemplatesController extends BaseController
{
    @Resource
    private IFollowUpTemplatesService followUpTemplatesService;

    /**
     * 查询写跟进模板列表
     */
    @PreAuthorize("@ss.hasPermi('customer:follow:up:templates:list')")
    @GetMapping("/list")
    public AjaxResult list()
    {
        List<FollowUpTemplatesListVO> list = followUpTemplatesService.list();
        return success(list);
    }

    /**
     * 新增写跟进模板
     */
    @PreAuthorize("@ss.hasPermi('customer:follow:up:templates:add')")
    @Log(title = "新增写跟进模板", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody FollowUpTemplates followUpTemplates)
    {
        if (StringUtils.isBlank(followUpTemplates.getName())) {
            throw new ServiceException("模板名称不能为空");
        }
        if (StringUtils.isBlank(followUpTemplates.getContent())) {
            throw new ServiceException("模板内容不能为空");
        }

        return toAjax(followUpTemplatesService.insertFollowUpTemplates(followUpTemplates));
    }

    /**
     * 修改写跟进模板
     */
    @PreAuthorize("@ss.hasPermi('customer:follow:up:templates:edit')")
    @Log(title = "修改写跟进模板", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody FollowUpTemplates followUpTemplates)
    {
        if (followUpTemplates.getId() == null) {
            throw new ServiceException("ID不能为空");
        }
        if (StringUtils.isBlank(followUpTemplates.getName())) {
            throw new ServiceException("模板名称不能为空");
        }
        if (StringUtils.isBlank(followUpTemplates.getContent())) {
            throw new ServiceException("模板内容不能为空");
        }

        return toAjax(followUpTemplatesService.updateFollowUpTemplates(followUpTemplates));
    }

    /**
     * 删除写跟进模板
     */
    @PreAuthorize("@ss.hasPermi('customer:follow:up:templates:delete')")
    @Log(title = "删除写跟进模板", businessType = BusinessType.DELETE)
	@PostMapping("/delete")
    public AjaxResult delete(@RequestBody FollowUpTemplates followUpTemplates)
    {
        if (followUpTemplates.getId() == null) {
            throw new ServiceException("ID不能为空");
        }

        return toAjax(followUpTemplatesService.deleteFollowUpTemplatesById(followUpTemplates.getId()));
    }
}
