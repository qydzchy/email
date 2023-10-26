package com.ruoyi.web.controller.customer;

import java.util.List;
import javax.annotation.Resource;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.customer.domain.vo.FollowUpRulesListVO;
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
import com.ruoyi.customer.domain.FollowUpRules;
import com.ruoyi.customer.service.IFollowUpRulesService;

/**
 * 客户跟进规则Controller
 * 
 * @author tangJM.
 * @date 2023-10-25
 */

@RestController
@RequestMapping("/customer/follow/up/rules")
public class FollowUpRulesController extends BaseController
{
    @Resource
    private IFollowUpRulesService followUpRulesService;

    /**
     * 查询客户跟进规则列表
     */
    @PreAuthorize("@ss.hasPermi('customer:follow:up:rules:list')")
    @GetMapping("/list")
    public AjaxResult list()
    {
        List<FollowUpRulesListVO> list = followUpRulesService.list();
        return success(list);
    }

    /**
     * 修改客户跟进规则标志
     */
    @PreAuthorize("@ss.hasPermi('customer:follow:up:rules:edit:activeFlag')")
    @Log(title = "修改客户跟进规则标志", businessType = BusinessType.UPDATE)
    @PostMapping("/edit/activeFlag")
    public AjaxResult editActiveFlag(@RequestBody FollowUpRules followUpRules)
    {
        if (followUpRules.getId() == null) {
            throw new ServiceException("ID不能为空");
        }

        return toAjax(followUpRulesService.updateActiveFlag(followUpRules.getId()));
    }
}
