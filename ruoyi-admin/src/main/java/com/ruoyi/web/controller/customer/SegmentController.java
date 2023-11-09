package com.ruoyi.web.controller.customer;

import java.util.List;
import javax.annotation.Resource;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.customer.domain.dto.SegmentAddOrUpdateDTO;
import com.ruoyi.customer.domain.vo.SegmentListVO;
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
import com.ruoyi.customer.domain.Segment;
import com.ruoyi.customer.service.ISegmentService;

/**
 * 客群Controller
 * 
 * @author tangJM.
 * @date 2023-11-01
 */
@RestController
@RequestMapping("/customer/segment")
public class SegmentController extends BaseController
{
    @Resource
    private ISegmentService segmentService;

    /**
     * 查询客群列表
     */
    @PreAuthorize("@ss.hasPermi('customer:segment:list')")
    @GetMapping("/list")
    public AjaxResult list(Segment segment)
    {
        Integer usageScope = segment.getUsageScope();
        List<SegmentListVO> list = segmentService.getSegmentTree(usageScope);
        return success(list);
    }

    /**
     * 新增客群
     */
    @PreAuthorize("@ss.hasPermi('customer:segment:add')")
    @Log(title = "新增客群", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody SegmentAddOrUpdateDTO segmentAddOrUpdateDTO)
    {
        if (segmentAddOrUpdateDTO.getUsageScope() == null) {
            throw new ServiceException("使用范围不能为空");
        }

        return toAjax(segmentService.insertSegment(segmentAddOrUpdateDTO));
    }

    /**
     * 修改客群
     */
    @PreAuthorize("@ss.hasPermi('customer:segment:edit')")
    @Log(title = "修改客群", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody SegmentAddOrUpdateDTO segmentAddOrUpdateDTO)
    {
        if (segmentAddOrUpdateDTO.getId() == null) {
            throw new ServiceException("客群ID不能为空");
        }
        if (segmentAddOrUpdateDTO.getUsageScope() == null) {
            throw new ServiceException("使用范围不能为空");
        }

        return toAjax(segmentService.updateSegment(segmentAddOrUpdateDTO));
    }

    /**
     * 删除客群
     */
    @PreAuthorize("@ss.hasPermi('customer:segment:delete')")
    @Log(title = "删除客群", businessType = BusinessType.DELETE)
	@PostMapping("/delete")
    public AjaxResult delete(@RequestBody Segment segment)
    {
        if (segment.getId() == null) {
            throw new ServiceException("客群ID不能为空");
        }

        return toAjax(segmentService.deleteSegmentById(segment.getId()));
    }

    /**
     * 获取条件规则字段
     */
    @PreAuthorize("@ss.hasPermi('customer:segment:condition:rule:column')")
    @GetMapping("/get/condition/rule/column")
    public AjaxResult getConditionRuleColumn()
    {
        return success(segmentService.getConditionRuleColumn());
    }
}
