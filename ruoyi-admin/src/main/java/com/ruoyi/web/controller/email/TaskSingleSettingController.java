package com.ruoyi.web.controller.email;

import java.util.List;
import javax.annotation.Resource;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.email.domain.vo.TaskSingleSettingListVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.email.domain.TaskSingleSetting;
import com.ruoyi.email.service.ITaskSingleSettingService;

/**
 * 单个邮箱设置Controller
 * 
 * @author tangJM.
 * @date 2023-12-27
 */
@RestController
@RequestMapping("/email/setting")
public class TaskSingleSettingController extends BaseController
{
    @Resource
    private ITaskSingleSettingService taskSingleSettingService;

    /**
     * 查询单个邮箱设置列表
     */
    @PreAuthorize("@ss.hasPermi('email:setting:list')")
    @GetMapping("/list")
    public AjaxResult list()
    {
        List<TaskSingleSettingListVO> list = taskSingleSettingService.list();
        return success(list);
    }

    /**
     * 修改单个邮箱设置
     */
    @PreAuthorize("@ss.hasPermi('email:setting:edit')")
    @Log(title = "修改单个邮箱设置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody List<TaskSingleSetting> taskSingleSettingList)
    {
        if (taskSingleSettingList == null || taskSingleSettingList.isEmpty()) {
            throw new ServiceException("参数不能为空");
        }

        for (TaskSingleSetting taskSingleSetting : taskSingleSettingList) {
            if (taskSingleSetting.getTaskId() == null) {
                throw new ServiceException("任务id不能为空");
            }
        }

        return toAjax(taskSingleSettingService.updateTaskSingleSetting(taskSingleSettingList));
    }

}
