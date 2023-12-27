package com.ruoyi.email.service.impl;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.email.domain.vo.task.TaskSingleSettingListVO;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.TaskSingleSettingMapper;
import com.ruoyi.email.domain.TaskSingleSetting;
import com.ruoyi.email.service.ITaskSingleSettingService;

import javax.annotation.Resource;

/**
 * 单个邮箱设置Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-12-27
 */
@Service
public class TaskSingleSettingServiceImpl implements ITaskSingleSettingService 
{
    @Resource
    private TaskSingleSettingMapper taskSingleSettingMapper;

    /**
     * 查询单个邮箱设置
     * 
     * @param id 单个邮箱设置主键
     * @return 单个邮箱设置
     */
    @Override
    public TaskSingleSetting selectTaskSingleSettingById(Long id)
    {
        return taskSingleSettingMapper.selectTaskSingleSettingById(id);
    }

    /**
     * 查询单个邮箱设置列表
     * 
     * @param taskSingleSetting 单个邮箱设置
     * @return 单个邮箱设置
     */
    @Override
    public List<TaskSingleSetting> selectTaskSingleSettingList(TaskSingleSetting taskSingleSetting)
    {
        return taskSingleSettingMapper.selectTaskSingleSettingList(taskSingleSetting);
    }

    /**
     * 新增单个邮箱设置
     * 
     * @param taskSingleSetting 单个邮箱设置
     * @return 结果
     */
    @Override
    public int insertTaskSingleSetting(TaskSingleSetting taskSingleSetting)
    {
        taskSingleSetting.setCreateTime(DateUtils.getNowDate());
        return taskSingleSettingMapper.insertTaskSingleSetting(taskSingleSetting);
    }

    /**
     * 修改单个邮箱设置
     * 
     * @param taskSingleSetting 单个邮箱设置
     * @return 结果
     */
    @Override
    public int updateTaskSingleSetting(TaskSingleSetting taskSingleSetting)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();
        Date now = DateUtils.getNowDate();

        if (taskSingleSetting.getId() == null) {
            taskSingleSetting.setCreateId(userId);
            taskSingleSetting.setCreateBy(username);
            taskSingleSetting.setCreateTime(now);
            taskSingleSetting.setUpdateId(userId);
            taskSingleSetting.setUpdateBy(username);
            taskSingleSetting.setUpdateTime(now);
            return taskSingleSettingMapper.insertTaskSingleSetting(taskSingleSetting);

        } else {
            taskSingleSetting.setUpdateId(userId);
            taskSingleSetting.setUpdateBy(username);
            taskSingleSetting.setUpdateTime(now);
            return taskSingleSettingMapper.updateTaskSingleSetting(taskSingleSetting);

        }
    }

    /**
     * 批量删除单个邮箱设置
     * 
     * @param ids 需要删除的单个邮箱设置主键
     * @return 结果
     */
    @Override
    public int deleteTaskSingleSettingByIds(Long[] ids)
    {
        return taskSingleSettingMapper.deleteTaskSingleSettingByIds(ids);
    }

    /**
     * 删除单个邮箱设置信息
     * 
     * @param id 单个邮箱设置主键
     * @return 结果
     */
    @Override
    public int deleteTaskSingleSettingById(Long id)
    {
        return taskSingleSettingMapper.deleteTaskSingleSettingById(id);
    }

    /**
     * 列表
     * @return
     */
    @Override
    public List<TaskSingleSettingListVO> list() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();

        return taskSingleSettingMapper.list(userId);
    }
}
