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
import com.ruoyi.customer.domain.PacketDesignateMember;
import com.ruoyi.customer.service.IPacketDesignateMemberService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

*/
/**
 * 客户分组指定成员Controller
 * 
 * @author tangJM.
 * @date 2023-10-25
 *//*

@RestController
@RequestMapping("/customer/customer")
public class PacketDesignateMemberController extends BaseController
{
    @Autowired
    private IPacketDesignateMemberService packetDesignateMemberService;

    */
/**
     * 查询客户分组指定成员列表
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:list')")
    @GetMapping("/list")
    public TableDataInfo list(PacketDesignateMember packetDesignateMember)
    {
        startPage();
        List<PacketDesignateMember> list = packetDesignateMemberService.selectPacketDesignateMemberList(packetDesignateMember);
        return getDataTable(list);
    }

    */
/**
     * 导出客户分组指定成员列表
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:export')")
    @Log(title = "客户分组指定成员", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PacketDesignateMember packetDesignateMember)
    {
        List<PacketDesignateMember> list = packetDesignateMemberService.selectPacketDesignateMemberList(packetDesignateMember);
        ExcelUtil<PacketDesignateMember> util = new ExcelUtil<PacketDesignateMember>(PacketDesignateMember.class);
        util.exportExcel(response, list, "客户分组指定成员数据");
    }

    */
/**
     * 获取客户分组指定成员详细信息
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(packetDesignateMemberService.selectPacketDesignateMemberById(id));
    }

    */
/**
     * 新增客户分组指定成员
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:add')")
    @Log(title = "客户分组指定成员", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PacketDesignateMember packetDesignateMember)
    {
        return toAjax(packetDesignateMemberService.insertPacketDesignateMember(packetDesignateMember));
    }

    */
/**
     * 修改客户分组指定成员
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:edit')")
    @Log(title = "客户分组指定成员", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PacketDesignateMember packetDesignateMember)
    {
        return toAjax(packetDesignateMemberService.updatePacketDesignateMember(packetDesignateMember));
    }

    */
/**
     * 删除客户分组指定成员
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:remove')")
    @Log(title = "客户分组指定成员", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(packetDesignateMemberService.deletePacketDesignateMemberByIds(ids));
    }
}
*/
