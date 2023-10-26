package com.ruoyi.web.controller.customer;

import java.util.List;
import javax.annotation.Resource;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.customer.domain.vo.PublicleadsRulesListVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.customer.domain.PublicleadsRules;
import com.ruoyi.customer.service.IPublicleadsRulesService;

/**
 * 移入公海规则Controller
 * 
 * @author tangJM.
 * @date 2023-10-25
 */

@RestController
@RequestMapping("/customer/public/leads/rules")
public class PublicleadsRulesController extends BaseController
{
    @Resource
    private IPublicleadsRulesService publicleadsRulesService;

    /**
     * 查询移入公海规则列表
     */
    @PreAuthorize("@ss.hasPermi('customer:public:leads:rules:list')")
    @GetMapping("/list")
    public AjaxResult list()
    {
        List<PublicleadsRulesListVO> list = publicleadsRulesService.list();
        return success(list);
    }

    /**
     * 新增移入公海规则
     */
    @PreAuthorize("@ss.hasPermi('customer:public:leads:rules:add')")
    @Log(title = "新增移入公海规则", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody PublicleadsRules publicleadsRules)
    {
        return toAjax(publicleadsRulesService.insertPublicleadsRules(publicleadsRules));
    }

    /**
     * 修改移入公海规则
     */
    @PreAuthorize("@ss.hasPermi('customer:public:leads:rules:edit')")
    @Log(title = "修改移入公海规则", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody PublicleadsRules publicleadsRules)
    {
        return toAjax(publicleadsRulesService.updatePublicleadsRules(publicleadsRules));
    }

    /**
     * 删除移入公海规则
     */
    @PreAuthorize("@ss.hasPermi('customer:public:leads:rules:delete')")
    @Log(title = "删除移入公海规则", businessType = BusinessType.DELETE)
	@PostMapping("/delete")
    public AjaxResult delete(@RequestBody PublicleadsRules publicleadsRules)
    {
        if (publicleadsRules.getId() == null) {
            throw new ServiceException("id不能为空");
        }

        return toAjax(publicleadsRulesService.deletePublicleadsRulesById(publicleadsRules.getId()));
    }
}
