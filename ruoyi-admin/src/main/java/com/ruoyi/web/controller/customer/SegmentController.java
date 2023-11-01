package com.ruoyi.web.controller.customer;

import java.util.List;
import javax.annotation.Resource;
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
import com.ruoyi.customer.domain.Segment;
import com.ruoyi.customer.service.ISegmentService;
import com.ruoyi.common.core.page.TableDataInfo;

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
        List<Segment> list = segmentService.selectSegmentList(segment);
        return success(list);
    }

    /**
     * 新增客群
     */
    @PreAuthorize("@ss.hasPermi('customer:segment:add')")
    @Log(title = "新增客群", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody Segment segment)
    {
        return toAjax(segmentService.insertSegment(segment));
    }

    /**
     * 修改客群
     */
    @PreAuthorize("@ss.hasPermi('customer:segment:edit')")
    @Log(title = "修改客群", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody Segment segment)
    {
        return toAjax(segmentService.updateSegment(segment));
    }

    /**
     * 删除客群
     */
    @PreAuthorize("@ss.hasPermi('customer:segment:delete')")
    @Log(title = "删除客群", businessType = BusinessType.DELETE)
	@PostMapping("/delete")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(segmentService.deleteSegmentByIds(ids));
    }
}
