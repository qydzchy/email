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
import com.ruoyi.customer.domain.BlackListRecords;
import com.ruoyi.customer.service.IBlackListRecordsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

*/
/**
 * 建档黑名单Controller
 * 
 * @author tangJM.
 * @date 2023-10-25
 *//*

@RestController
@RequestMapping("/customer/customer")
public class BlackListRecordsController extends BaseController
{
    @Autowired
    private IBlackListRecordsService blackListRecordsService;

    */
/**
     * 查询建档黑名单列表
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:list')")
    @GetMapping("/list")
    public TableDataInfo list(BlackListRecords blackListRecords)
    {
        startPage();
        List<BlackListRecords> list = blackListRecordsService.selectBlackListRecordsList(blackListRecords);
        return getDataTable(list);
    }

    */
/**
     * 导出建档黑名单列表
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:export')")
    @Log(title = "建档黑名单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BlackListRecords blackListRecords)
    {
        List<BlackListRecords> list = blackListRecordsService.selectBlackListRecordsList(blackListRecords);
        ExcelUtil<BlackListRecords> util = new ExcelUtil<BlackListRecords>(BlackListRecords.class);
        util.exportExcel(response, list, "建档黑名单数据");
    }

    */
/**
     * 获取建档黑名单详细信息
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(blackListRecordsService.selectBlackListRecordsById(id));
    }

    */
/**
     * 新增建档黑名单
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:add')")
    @Log(title = "建档黑名单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BlackListRecords blackListRecords)
    {
        return toAjax(blackListRecordsService.insertBlackListRecords(blackListRecords));
    }

    */
/**
     * 修改建档黑名单
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:edit')")
    @Log(title = "建档黑名单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BlackListRecords blackListRecords)
    {
        return toAjax(blackListRecordsService.updateBlackListRecords(blackListRecords));
    }

    */
/**
     * 删除建档黑名单
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:remove')")
    @Log(title = "建档黑名单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(blackListRecordsService.deleteBlackListRecordsByIds(ids));
    }
}
*/
