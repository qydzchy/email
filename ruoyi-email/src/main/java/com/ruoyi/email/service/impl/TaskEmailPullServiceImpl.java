package com.ruoyi.email.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.email.domain.vo.email.PullEmailInfoListVO;
import com.ruoyi.email.service.ITaskEmailPullService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.TaskEmailPullMapper;
import com.ruoyi.email.domain.TaskEmailPull;

import javax.annotation.Resource;

/**
 * 拉取邮件Service业务层处理
 * 
 * @author tangJM
 * @date 2023-07-31
 */
@Service
public class TaskEmailPullServiceImpl implements ITaskEmailPullService
{
    @Resource
    private TaskEmailPullMapper taskEmailPullMapper;

    /**
     * 查询拉取邮件
     * 
     * @param id 拉取邮件主键
     * @return 拉取邮件
     */
    @Override
    public TaskEmailPull selectTaskEmailPullById(Long id)
    {
        return taskEmailPullMapper.selectTaskEmailPullById(id);
    }

    /**
     * 查询拉取邮件列表
     * 
     * @param taskEmailPull 拉取邮件
     * @return 拉取邮件
     */
    @Override
    public List<TaskEmailPull> selectTaskEmailPullList(TaskEmailPull taskEmailPull)
    {
        return taskEmailPullMapper.selectTaskEmailPullList(taskEmailPull);
    }

    /**
     * 新增拉取邮件
     * 
     * @param taskEmailPull 拉取邮件
     * @return 结果
     */
    @Override
    public int insertTaskEmailPull(TaskEmailPull taskEmailPull)
    {
        taskEmailPull.setCreateTime(DateUtils.getNowDate());
        return taskEmailPullMapper.insertTaskEmailPull(taskEmailPull);
    }

    /**
     * 修改拉取邮件
     * 
     * @param taskEmailPull 拉取邮件
     * @return 结果
     */
    @Override
    public int updateTaskEmailPull(TaskEmailPull taskEmailPull)
    {
        taskEmailPull.setUpdateTime(DateUtils.getNowDate());
        return taskEmailPullMapper.updateTaskEmailPull(taskEmailPull);
    }

    /**
     * 批量删除拉取邮件
     * 
     * @param ids 需要删除的拉取邮件主键
     * @return 结果
     */
    @Override
    public int deleteTaskEmailPullByIds(Long[] ids)
    {
        return taskEmailPullMapper.deleteTaskEmailPullByIds(ids);
    }

    /**
     * 删除拉取邮件信息
     * 
     * @param id 拉取邮件主键
     * @return 结果
     */
    @Override
    public int deleteTaskEmailPullById(Long id)
    {
        return taskEmailPullMapper.deleteTaskEmailPullById(id);
    }

    /**
     * 获取邮箱拉取的邮件数量
     * @param ids
     * @return
     */
    @Override
    public Map<Long, Integer> getPullEmailQuantityByIds(List<Long> ids) {
        List<Map<String, Object>> pullEmailQuantityByIds = taskEmailPullMapper.getPullEmailQuantityByIds(ids);
        if (pullEmailQuantityByIds == null || pullEmailQuantityByIds.size() == 0) {
            return new HashMap<>();
        }

        return pullEmailQuantityByIds.stream().collect(Collectors.toMap(
                map -> Long.valueOf(map.get("taskId").toString()),
                map -> Integer.valueOf(map.get("quantity").toString()))
        );
    }

    @Override
    public List<PullEmailInfoListVO> listPullHeader(Long taskId) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();

        TaskEmailPull taskEmailPull = new TaskEmailPull();
        taskEmailPull.setTaskId(taskId);
        taskEmailPull.setCreateId(userId);
        taskEmailPull.setDelFlag("0");
        List<TaskEmailPull> taskEmailPullList = selectTaskEmailPullList(taskEmailPull);
        if (taskEmailPullList == null || taskEmailPullList.size() == 0) {
            return new ArrayList<>();
        }

        List<PullEmailInfoListVO> pullEmailInfoListVOList = new ArrayList<>();
        taskEmailPullList.stream().forEach(emailPull -> {
            PullEmailInfoListVO pullEmailInfoListVO = new PullEmailInfoListVO();
            BeanUtils.copyProperties(emailPull, pullEmailInfoListVO);
            pullEmailInfoListVOList.add(pullEmailInfoListVO);
        });

        return pullEmailInfoListVOList;
    }
}
