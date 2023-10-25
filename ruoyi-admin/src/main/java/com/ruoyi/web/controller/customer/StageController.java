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
import com.ruoyi.customer.domain.Stage;
import com.ruoyi.customer.service.IStageService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

*/
/**
 * 客户阶段Controller
 * 
 * @author tangJM.
 * @date 2023-10-25
 *//*

@RestController
@RequestMapping("/customer/customer")
public class StageController extends BaseController
{
    @Autowired
    private IStageService stageService;

    */
/**
     * 查询客户阶段列表
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:list')")
    @GetMapping("/list")
    public TableDataInfo list(Stage stage)
    {
        startPage();
        List<Stage> list = stageService.selectStageList(stage);
        return getDataTable(list);
    }

    */
/**
     * 导出客户阶段列表
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:export')")
    @Log(title = "客户阶段", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Stage stage)
    {
        List<Stage> list = stageService.selectStageList(stage);
        ExcelUtil<Stage> util = new ExcelUtil<Stage>(Stage.class);
        util.exportExcel(response, list, "客户阶段数据");
    }

    */
/**
     * 获取客户阶段详细信息
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(stageService.selectStageById(id));
    }

    */
/**
     * 新增客户阶段
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:add')")
    @Log(title = "客户阶段", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Stage stage)
    {
        return toAjax(stageService.insertStage(stage));
    }

    */
/**
     * 修改客户阶段
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:edit')")
    @Log(title = "客户阶段", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Stage stage)
    {
        return toAjax(stageService.updateStage(stage));
    }

    */
/**
     * 删除客户阶段
     *//*

    @PreAuthorize("@ss.hasPermi('customer:customer:remove')")
    @Log(title = "客户阶段", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(stageService.deleteStageByIds(ids));
    }
}
*/
