package com.ruoyi.web.controller.customer;

import java.util.List;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.customer.domain.vo.FollowUpQuickTextListVO;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.ruoyi.customer.domain.FollowUpQuickText;
import com.ruoyi.customer.service.IFollowUpQuickTextService;

import javax.annotation.Resource;

/**
 * 写跟进快捷文本Controller
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
@RestController
@RequestMapping("/customer/follow/up/quick/text")
public class FollowUpQuickTextController extends BaseController
{
    @Resource
    private IFollowUpQuickTextService followUpQuickTextService;

    /**
     * 查询写跟进快捷文本列表
     */
    @PreAuthorize("@ss.hasPermi('customer:follow:up:quick:text:list')")
    @GetMapping("/list")
    public AjaxResult list()
    {
        List<FollowUpQuickTextListVO> list = followUpQuickTextService.list();
        return success(list);
    }

    /**
     * 新增写跟进快捷文本
     */
    @PreAuthorize("@ss.hasPermi('customer:follow:up:quick:text:add')")
    @Log(title = "新增写跟进快捷文本", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody FollowUpQuickText followUpQuickText)
    {
        if (StringUtils.isBlank(followUpQuickText.getName())) {
            throw new ServiceException("快捷文本名称不能为空");
        }
        if (StringUtils.isBlank(followUpQuickText.getLabel())) {
            throw new ServiceException("快捷文本标签不能为空");
        }

        return toAjax(followUpQuickTextService.insertFollowUpQuickText(followUpQuickText));
    }

    /**
     * 修改写跟进快捷文本
     */
    @PreAuthorize("@ss.hasPermi('customer:follow:up:quick:text:edit')")
    @Log(title = "修改写跟进快捷文本", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody FollowUpQuickText followUpQuickText)
    {
        if (followUpQuickText.getId() == null) {
            throw new ServiceException("快捷文本ID不能为空");
        }
        if (StringUtils.isBlank(followUpQuickText.getName())) {
            throw new ServiceException("快捷文本名称不能为空");
        }
        if (StringUtils.isBlank(followUpQuickText.getLabel())) {
            throw new ServiceException("快捷文本标签不能为空");
        }
        return toAjax(followUpQuickTextService.updateFollowUpQuickText(followUpQuickText));
    }

    /**
     * 删除写跟进快捷文本
     */
    @PreAuthorize("@ss.hasPermi('customer:follow:up:quick:text:delete')")
    @Log(title = "删除写跟进快捷文本", businessType = BusinessType.DELETE)
	@PostMapping("/delete")
    public AjaxResult delete(@RequestBody FollowUpQuickText followUpQuickText)
    {
        if (followUpQuickText.getId() == null) {
            throw new ServiceException("快捷文本ID不能为空");
        }

        return toAjax(followUpQuickTextService.deleteFollowUpQuickTextById(followUpQuickText.getId()));
    }
}
