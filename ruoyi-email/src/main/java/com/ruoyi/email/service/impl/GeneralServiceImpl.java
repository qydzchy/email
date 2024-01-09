package com.ruoyi.email.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.email.domain.Task;
import com.ruoyi.email.domain.TaskSingleSetting;
import com.ruoyi.email.domain.dto.email.GeneralSaveOrUpdateDTO;
import com.ruoyi.email.domain.vo.GeneralVO;
import com.ruoyi.email.domain.vo.TaskListVO;
import com.ruoyi.email.mapper.TaskMapper;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.GeneralMapper;
import com.ruoyi.email.domain.General;
import com.ruoyi.email.service.IGeneralService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 邮箱常规Service业务层处理
 * 
 * @author tangJM
 * @date 2023-07-31
 */
@Service
public class GeneralServiceImpl implements IGeneralService 
{
    @Resource
    private GeneralMapper generalMapper;
    @Resource
    private TaskMapper taskMapper;

    /**
     * 查询邮箱常规
     * 
     * @param id 邮箱常规主键
     * @return 邮箱常规
     */
    @Override
    public General selectGeneralById(Long id)
    {
        return generalMapper.selectGeneralById(id);
    }

    /**
     * 查询邮箱常规列表
     * 
     * @param general 邮箱常规
     * @return 邮箱常规
     */
    @Override
    public List<General> selectGeneralList(General general)
    {
        return generalMapper.selectGeneralList(general);
    }

    /**
     * 新增邮箱常规
     * 
     * @param general 邮箱常规
     * @return 结果
     */
    @Override
    public int insertGeneral(General general)
    {
        general.setCreateTime(DateUtils.getNowDate());
        return generalMapper.insertGeneral(general);
    }

    /**
     * 修改邮箱常规
     * 
     * @param generalSaveOrUpdateDTO 邮箱常规
     * @return 结果
     */
    @Override //todo 待完成
    @Transactional(rollbackFor = Exception.class)
    public int updateGeneral(GeneralSaveOrUpdateDTO generalSaveOrUpdateDTO)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();
        Date now = DateUtils.getNowDate();

        General general = new General();
        general.setUpdateId(userId);
        general.setUpdateBy(username);
        general.setUpdateTime(now);

        if (generalSaveOrUpdateDTO.getId() == null) {
            general.setCreateId(userId);
            general.setCreateBy(username);
            general.setCreateTime(now);
        }

        List<TaskSingleSetting> taskSingleSettingList = generalSaveOrUpdateDTO.getTaskSingleSettingList();
        List<TaskSingleSetting> insertTaskSingleSettingList = taskSingleSettingList.stream().filter(taskSingleSetting -> taskSingleSetting.getId() == null).collect(Collectors.toList());
        List<TaskSingleSetting> updateTaskSingleSettingList = taskSingleSettingList.stream().filter(taskSingleSetting -> taskSingleSetting.getId() != null).collect(Collectors.toList());




        if (general.getId() == null) {

            return generalMapper.insertGeneral(general);
        }

        return generalMapper.updateGeneral(general);
    }

    /**
     * 批量删除邮箱常规
     * 
     * @param ids 需要删除的邮箱常规主键
     * @return 结果
     */
    @Override
    public int deleteGeneralByIds(Long[] ids)
    {
        return generalMapper.deleteGeneralByIds(ids);
    }

    /**
     * 删除邮箱常规信息
     * 
     * @param id 邮箱常规主键
     * @return 结果
     */
    @Override
    public int deleteGeneralById(Long id)
    {
        return generalMapper.deleteGeneralById(id);
    }

    /**
     * 获取邮箱常规详细信息
     * @return
     */
    @Override
    public GeneralVO getInfo() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();

        GeneralVO generalVO = generalMapper.getByCreateId(userId);
        if (generalVO == null) {
            generalVO = new GeneralVO();
        }

        return generalVO;
    }

    /**
     * 获取默认任务列表
     * @return
     */
    @Override
    public List<TaskListVO> defaultTaskList() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();

        Task taskParam = new Task();
        taskParam.setCreateId(userId);
        taskParam.setDelFlag("0");
        List<Task> taskList = taskMapper.selectTaskList(taskParam);
        List<TaskListVO> taskVOList = new ArrayList<>();
        for (Task task : taskList) {
            TaskListVO taskVO = new TaskListVO();
            taskVO.setId(task.getId());
            taskVO.setAccount(task.getAccount());
            taskVOList.add(taskVO);
        }

        return taskVOList;
    }
}
