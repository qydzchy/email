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
import com.ruoyi.customer.domain.DetailsContact;
import com.ruoyi.customer.service.IDetailsContactService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

*/
/**
 * 客户联系人Controller
 * 
 * @author tangJM.
 * @date 2023-10-31
 *//*

@RestController
@RequestMapping("/customer/details")
public class DetailsContactController extends BaseController
{
    @Autowired
    private IDetailsContactService detailsContactService;

    */
/**
     * 查询客户联系人列表
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:list')")
    @GetMapping("/list")
    public TableDataInfo list(DetailsContact detailsContact)
    {
        startPage();
        List<DetailsContact> list = detailsContactService.selectDetailsContactList(detailsContact);
        return getDataTable(list);
    }

    */
/**
     * 导出客户联系人列表
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:export')")
    @Log(title = "客户联系人", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DetailsContact detailsContact)
    {
        List<DetailsContact> list = detailsContactService.selectDetailsContactList(detailsContact);
        ExcelUtil<DetailsContact> util = new ExcelUtil<DetailsContact>(DetailsContact.class);
        util.exportExcel(response, list, "客户联系人数据");
    }

    */
/**
     * 获取客户联系人详细信息
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(detailsContactService.selectDetailsContactById(id));
    }

    */
/**
     * 新增客户联系人
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:add')")
    @Log(title = "客户联系人", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DetailsContact detailsContact)
    {
        return toAjax(detailsContactService.insertDetailsContact(detailsContact));
    }

    */
/**
     * 修改客户联系人
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:edit')")
    @Log(title = "客户联系人", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DetailsContact detailsContact)
    {
        return toAjax(detailsContactService.updateDetailsContact(detailsContact));
    }

    */
/**
     * 删除客户联系人
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:remove')")
    @Log(title = "客户联系人", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(detailsContactService.deleteDetailsContactByIds(ids));
    }
}
*/
