package com.ruoyi.web.controller.customer;

import java.util.List;

import com.ruoyi.customer.domain.dto.DetailsAddOrUpdateDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.customer.domain.Details;
import com.ruoyi.customer.service.IDetailsService;
import com.ruoyi.common.core.page.TableDataInfo;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * 客户详情Controller
 * 
 * @author tangJM.
 * @date 2023-10-31
 */
@RestController
@RequestMapping("/customer/details")
public class DetailsController extends BaseController
{
    @Resource
    private IDetailsService detailsService;

    /**
     * 查询客户详情列表
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:list')")
    @GetMapping("/list")
    public TableDataInfo list(@NotNull(message = "私海/公海类型不能为空") Integer seaType,
                              @NotNull(message = "页数不能为空") Integer pageNum,
                              @NotNull(message = "页大小不能为空") Integer pageSize)
    {
        List<Details> list = detailsService.list(seaType, pageNum, pageSize);
        return getDataTable(list);
    }

    /**
     * 获取客户详情详细信息
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(detailsService.selectDetailsById(id));
    }

    /**
     * 新增客户详情
     */
    @PreAuthorize("@ss.hasPermi('customer:details:add')")
    @Log(title = "新增客户详情", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody DetailsAddOrUpdateDTO detailsAddOrUpdateDTO)
    {
        return toAjax(detailsService.insertDetails(detailsAddOrUpdateDTO));
    }

    /**
     * 修改客户详情
     */
    @PreAuthorize("@ss.hasPermi('customer:details:edit')")
    @Log(title = "修改客户详情", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody DetailsAddOrUpdateDTO detailsAddOrUpdateDTO)
    {
        return toAjax(detailsService.updateDetails(detailsAddOrUpdateDTO));
    }
}
