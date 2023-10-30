package com.ruoyi.web.controller.customer;

import java.util.List;
import javax.annotation.Resource;

import com.ruoyi.customer.domain.Limits;
import com.ruoyi.customer.domain.LimitsListVO;
import com.ruoyi.customer.service.ILimitsService;
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


/**
 * 客户上限Controller
 * 
 * @author tangJM.
 * @date 2023-10-25
 */

@RestController
@RequestMapping("/customer/limits")
public class LimitsController extends BaseController
{
    @Resource
    private ILimitsService limitsService;

    /**
     * 查询客户上限列表
     */
    @PreAuthorize("@ss.hasPermi('customer:limits:list')")
    @GetMapping("/list")
    public AjaxResult list()
    {
        List<LimitsListVO> list = limitsService.list();
        return success(list);
    }

    /**
     * 修改客户上限
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:edit')")
    @Log(title = "修改客户上限", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody Limits limits)
    {
        return toAjax(limitsService.updateLimits(limits));
    }
}
