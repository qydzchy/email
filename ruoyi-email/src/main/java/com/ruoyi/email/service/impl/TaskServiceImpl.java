package com.ruoyi.email.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.email.domain.Host;
import com.ruoyi.email.service.IHostService;
import com.ruoyi.email.service.ITaskService;
import com.ruoyi.email.service.handler.email.MailContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.TaskMapper;
import com.ruoyi.email.domain.Task;


/**
 * 邮箱任务Service业务层处理
 * 
 * @author tangJM
 * @date 2023-07-31
 */
@Service
public class TaskServiceImpl implements ITaskService
{
    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private MailContext mailContext;

    @Autowired
    private IHostService hostService;

    /**
     * 查询邮箱任务
     * 
     * @param id 邮箱任务主键
     * @return 邮箱任务
     */
    @Override
    public Task selectTaskById(Long id)
    {
        return taskMapper.selectTaskById(id);
    }

    /**
     * 查询邮箱任务列表
     * 
     * @param task 邮箱任务
     * @return 邮箱任务
     */
    @Override
    public List<Task> selectTaskList(Task task)
    {
        return taskMapper.selectTaskList(task);
    }

    /**
     * 新增邮箱任务
     * 
     * @param task 邮箱任务
     * @return 结果
     */
    @Override
    public int insertTask(Task task)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();

        // 是否存在邮箱账号
        if (isExistAccount(task.getAccount(), userId)) {
            throw new ServiceException("邮箱账号已存在");
        }

        // 解析邮箱平台
        Host host = hostService.getByDomain(task.getAccount());

        // 判断用户是否手动填写了邮箱收件服务信息
        if (StringUtils.isBlank(task.getReceivingServer()) || task.getReceivingPort() == null) {
            task.setReceivingServer(host.getImapHost());
            task.setReceivingPort(host.getImapPort());
            task.setReceivingSslFlag(host.getImapSsl());
        }

        //
        if (StringUtils.isBlank(task.getOutgoingServer()) || task.getOutgoingPort() == null) {
            task.setOutgoingServer(host.getImapHost());
            task.setOutgoingPort(host.getImapPort());
            task.setOutgoingSslFlag(host.getImapSsl());
        }


        // 建立连接
       // mailContext.createConn()


        task.setCreateBy(loginUser.getUsername());

        task.setCreateTime(DateUtils.getNowDate());
        return taskMapper.insertTask(task);
    }

    /**
     * 是否存在邮箱账号
     * @param account
     */
    private boolean isExistAccount(String account, Long userId) {
        int count = taskMapper.countAccount(account, Long userId);
        return count > 0 ? true : false;
    }

    /**
     * 修改邮箱任务
     * 
     * @param task 邮箱任务
     * @return 结果
     */
    @Override
    public int updateTask(Task task)
    {
        task.setUpdateTime(DateUtils.getNowDate());
        return taskMapper.updateTask(task);
    }

    /**
     * 批量删除邮箱任务
     * 
     * @param ids 需要删除的邮箱任务主键
     * @return 结果
     */
    @Override
    public int deleteTaskByIds(Long[] ids)
    {
        return taskMapper.deleteTaskByIds(ids);
    }

    /**
     * 删除邮箱任务信息
     * 
     * @param id 邮箱任务主键
     * @return 结果
     */
    @Override
    public int deleteTaskById(Long id)
    {
        return taskMapper.deleteTaskById(id);
    }
}
