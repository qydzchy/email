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
import com.ruoyi.customer.domain.PublicleadsClaimLimit;
import com.ruoyi.customer.service.IPublicleadsClaimLimitService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

*/
/**
 * 领取上限Controller
 * 
 * @author tangJM.
 * @date 2023-10-25
 *//*

@RestController
@RequestMapping("/customer/customer")
public class PublicleadsClaimLimitController extends BaseController
{
    @Autowired
    private IPublicleadsClaimLimitService publicleadsClaimLimitService;

    */
/**
     * 查询领取上限列表
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:list')")
    @GetMapping("/list")
    public TableDataInfo list(PublicleadsClaimLimit publicleadsClaimLimit)
    {
        startPage();
        List<PublicleadsClaimLimit> list = publicleadsClaimLimitService.selectPublicleadsClaimLimitList(publicleadsClaimLimit);
        return getDataTable(list);
    }

    */
/**
     * 导出领取上限列表
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:export')")
    @Log(title = "领取上限", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PublicleadsClaimLimit publicleadsClaimLimit)
    {
        List<PublicleadsClaimLimit> list = publicleadsClaimLimitService.selectPublicleadsClaimLimitList(publicleadsClaimLimit);
        ExcelUtil<PublicleadsClaimLimit> util = new ExcelUtil<PublicleadsClaimLimit>(PublicleadsClaimLimit.class);
        util.exportExcel(response, list, "领取上限数据");
    }

    */
/**
     * 获取领取上限详细信息
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(publicleadsClaimLimitService.selectPublicleadsClaimLimitById(id));
    }

    */
/**
     * 新增领取上限
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:add')")
    @Log(title = "领取上限", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PublicleadsClaimLimit publicleadsClaimLimit)
    {
        return toAjax(publicleadsClaimLimitService.insertPublicleadsClaimLimit(publicleadsClaimLimit));
    }

    */
/**
     * 修改领取上限
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:edit')")
    @Log(title = "领取上限", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PublicleadsClaimLimit publicleadsClaimLimit)
    {
        return toAjax(publicleadsClaimLimitService.updatePublicleadsClaimLimit(publicleadsClaimLimit));
    }

    */
/**
     * 删除领取上限
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:remove')")
    @Log(title = "领取上限", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(publicleadsClaimLimitService.deletePublicleadsClaimLimitByIds(ids));
    }
}
*/
