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
import com.ruoyi.customer.domain.PublicleadsWhiteList;
import com.ruoyi.customer.service.IPublicleadsWhiteListService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

*/
/**
 * 移入公海白名单Controller
 * 
 * @author tangJM.
 * @date 2023-10-25
 *//*

@RestController
@RequestMapping("/customer/customer")
public class PublicleadsWhiteListController extends BaseController
{
    @Autowired
    private IPublicleadsWhiteListService publicleadsWhiteListService;

    */
/**
     * 查询移入公海白名单列表
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:list')")
    @GetMapping("/list")
    public TableDataInfo list(PublicleadsWhiteList publicleadsWhiteList)
    {
        startPage();
        List<PublicleadsWhiteList> list = publicleadsWhiteListService.selectPublicleadsWhiteListList(publicleadsWhiteList);
        return getDataTable(list);
    }

    */
/**
     * 导出移入公海白名单列表
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:export')")
    @Log(title = "移入公海白名单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PublicleadsWhiteList publicleadsWhiteList)
    {
        List<PublicleadsWhiteList> list = publicleadsWhiteListService.selectPublicleadsWhiteListList(publicleadsWhiteList);
        ExcelUtil<PublicleadsWhiteList> util = new ExcelUtil<PublicleadsWhiteList>(PublicleadsWhiteList.class);
        util.exportExcel(response, list, "移入公海白名单数据");
    }

    */
/**
     * 获取移入公海白名单详细信息
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(publicleadsWhiteListService.selectPublicleadsWhiteListById(id));
    }

    */
/**
     * 新增移入公海白名单
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:add')")
    @Log(title = "移入公海白名单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PublicleadsWhiteList publicleadsWhiteList)
    {
        return toAjax(publicleadsWhiteListService.insertPublicleadsWhiteList(publicleadsWhiteList));
    }

    */
/**
     * 修改移入公海白名单
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:edit')")
    @Log(title = "移入公海白名单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PublicleadsWhiteList publicleadsWhiteList)
    {
        return toAjax(publicleadsWhiteListService.updatePublicleadsWhiteList(publicleadsWhiteList));
    }

    */
/**
     * 删除移入公海白名单
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:remove')")
    @Log(title = "移入公海白名单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(publicleadsWhiteListService.deletePublicleadsWhiteListByIds(ids));
    }
}
*/
