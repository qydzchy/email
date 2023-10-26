package com.ruoyi.web.controller.customer;

import java.util.List;
import javax.annotation.Resource;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.customer.domain.vo.StageListVO;
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
import com.ruoyi.customer.domain.Stage;
import com.ruoyi.customer.service.IStageService;

/**
 * 客户阶段Controller
 * 
 * @author tangJM.
 * @date 2023-10-25
 */

@RestController
@RequestMapping("/customer/stage")
public class StageController extends BaseController
{
    @Resource
    private IStageService stageService;


    /**
     * 查询客户阶段列表
     */
    @PreAuthorize("@ss.hasPermi('customer:stage:list')")
    @GetMapping("/list")
    public AjaxResult list()
    {
        List<StageListVO> list = stageService.list();
        return success(list);
    }

    /**
     * 新增客户阶段
     */
    @PreAuthorize("@ss.hasPermi('customer:stage:add')")
    @Log(title = "新增客户阶段", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody Stage stage)
    {
        if (StringUtils.isBlank(stage.getName())) {
            throw new ServiceException("阶段名称不能为空");
        }
        if (StringUtils.isBlank(stage.getColor())) {
            throw new ServiceException("阶段颜色不能为空");
        }

        return toAjax(stageService.insertStage(stage));
    }

    /**
     * 修改客户阶段
     */
    @PreAuthorize("@ss.hasPermi('customer:stage:edit')")
    @Log(title = "修改客户阶段", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody Stage stage)
    {
        if (stage.getId() == null) {
            throw new ServiceException("id不能为空");
        }
        if (StringUtils.isBlank(stage.getName())) {
            throw new ServiceException("阶段名称不能为空");
        }
        if (StringUtils.isBlank(stage.getColor())) {
            throw new ServiceException("阶段颜色不能为空");
        }

        return toAjax(stageService.updateStage(stage));
    }

    /**
     * 删除客户阶段
     */
    @PreAuthorize("@ss.hasPermi('customer:stage:delete')")
    @Log(title = "删除客户阶段", businessType = BusinessType.DELETE)
	@PostMapping("/delete")
    public AjaxResult delete(@RequestBody Stage stage)
    {
        if (stage.getId() == null) {
            throw new ServiceException("id不能为空");
        }

        return toAjax(stageService.deleteStageById(stage.getId()));
    }
}
