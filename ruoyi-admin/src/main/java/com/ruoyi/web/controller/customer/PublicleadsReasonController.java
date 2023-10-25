/*
package com.ruoyi.web.controller.customer;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.ruoyi.customer.domain.PublicleadsReason;
import com.ruoyi.customer.service.IPublicleadsReasonService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

*/
/**
 * 移入公海原因Controller
 * 
 * @author tangJM.
 * @date 2023-10-25
 *//*

@RestController
@RequestMapping("/customer/customer")
public class PublicleadsReasonController extends BaseController
{
    @Autowired
    private IPublicleadsReasonService publicleadsReasonService;

    */
/**
     * 查询移入公海原因列表
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:list')")
    @GetMapping("/list")
    public TableDataInfo list(PublicleadsReason publicleadsReason)
    {
        startPage();
        List<PublicleadsReason> list = publicleadsReasonService.selectPublicleadsReasonList(publicleadsReason);
        return getDataTable(list);
    }

    */
/**
     * 导出移入公海原因列表
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:export')")
    @Log(title = "移入公海原因", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PublicleadsReason publicleadsReason)
    {
        List<PublicleadsReason> list = publicleadsReasonService.selectPublicleadsReasonList(publicleadsReason);
        ExcelUtil<PublicleadsReason> util = new ExcelUtil<PublicleadsReason>(PublicleadsReason.class);
        util.exportExcel(response, list, "移入公海原因数据");
    }

    */
/**
     * 获取移入公海原因详细信息
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(publicleadsReasonService.selectPublicleadsReasonById(id));
    }

    */
/**
     * 新增移入公海原因
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:add')")
    @Log(title = "移入公海原因", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PublicleadsReason publicleadsReason)
    {
        return toAjax(publicleadsReasonService.insertPublicleadsReason(publicleadsReason));
    }

    */
/**
     * 修改移入公海原因
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:edit')")
    @Log(title = "移入公海原因", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PublicleadsReason publicleadsReason)
    {
        return toAjax(publicleadsReasonService.updatePublicleadsReason(publicleadsReason));
    }

    */
/**
     * 删除移入公海原因
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:remove')")
    @Log(title = "移入公海原因", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(publicleadsReasonService.deletePublicleadsReasonByIds(ids));
    }
}
*/
