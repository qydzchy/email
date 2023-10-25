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
import com.ruoyi.customer.domain.PublicleadsGroups;
import com.ruoyi.customer.service.IPublicleadsGroupsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

*/
/**
 * 公海分组Controller
 * 
 * @author tangJM.
 * @date 2023-10-25
 *//*

@RestController
@RequestMapping("/customer/customer")
public class PublicleadsGroupsController extends BaseController
{
    @Autowired
    private IPublicleadsGroupsService publicleadsGroupsService;

    */
/**
     * 查询公海分组列表
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:list')")
    @GetMapping("/list")
    public TableDataInfo list(PublicleadsGroups publicleadsGroups)
    {
        startPage();
        List<PublicleadsGroups> list = publicleadsGroupsService.selectPublicleadsGroupsList(publicleadsGroups);
        return getDataTable(list);
    }

    */
/**
     * 导出公海分组列表
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:export')")
    @Log(title = "公海分组", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PublicleadsGroups publicleadsGroups)
    {
        List<PublicleadsGroups> list = publicleadsGroupsService.selectPublicleadsGroupsList(publicleadsGroups);
        ExcelUtil<PublicleadsGroups> util = new ExcelUtil<PublicleadsGroups>(PublicleadsGroups.class);
        util.exportExcel(response, list, "公海分组数据");
    }

    */
/**
     * 获取公海分组详细信息
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(publicleadsGroupsService.selectPublicleadsGroupsById(id));
    }

    */
/**
     * 新增公海分组
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:add')")
    @Log(title = "公海分组", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PublicleadsGroups publicleadsGroups)
    {
        return toAjax(publicleadsGroupsService.insertPublicleadsGroups(publicleadsGroups));
    }

    */
/**
     * 修改公海分组
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:edit')")
    @Log(title = "公海分组", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PublicleadsGroups publicleadsGroups)
    {
        return toAjax(publicleadsGroupsService.updatePublicleadsGroups(publicleadsGroups));
    }

    */
/**
     * 删除公海分组
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:remove')")
    @Log(title = "公海分组", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(publicleadsGroupsService.deletePublicleadsGroupsByIds(ids));
    }
}
*/
