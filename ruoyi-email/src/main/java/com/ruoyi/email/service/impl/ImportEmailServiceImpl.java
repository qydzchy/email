package com.ruoyi.email.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.email.domain.Task;
import com.ruoyi.email.domain.vo.email.ImportListVO;
import com.ruoyi.email.domain.vo.task.TaskListVO;
import com.ruoyi.email.mapper.TaskMapper;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.ImportEmailMapper;
import com.ruoyi.email.domain.ImportEmail;
import com.ruoyi.email.service.IImportEmailService;

import javax.annotation.Resource;

/**
 * 导入邮件Service业务层处理
 * 
 * @author tangJM
 * @date 2023-07-31
 */
@Service
public class ImportEmailServiceImpl implements IImportEmailService 
{
    @Resource
    private ImportEmailMapper importEmailMapper;
    @Resource
    private TaskMapper taskMapper;

    /**
     * 查询导入邮件
     * 
     * @param id 导入邮件主键
     * @return 导入邮件
     */
    @Override
    public ImportEmail selectImportEmailById(Long id)
    {
        return importEmailMapper.selectImportEmailById(id);
    }

    /**
     * 查询导入邮件列表
     * 
     * @param importEmail 导入邮件
     * @return 导入邮件
     */
    @Override
    public List<ImportEmail> selectImportEmailList(ImportEmail importEmail)
    {
        return importEmailMapper.selectImportEmailList(importEmail);
    }

    /**
     * 新增导入邮件
     * 
     * @param importEmail 导入邮件
     * @return 结果
     */
    @Override
    public int insertImportEmail(ImportEmail importEmail)
    {
        importEmail.setCreateTime(DateUtils.getNowDate());
        return importEmailMapper.insertImportEmail(importEmail);
    }

    /**
     * 修改导入邮件
     * 
     * @param importEmail 导入邮件
     * @return 结果
     */
    @Override
    public int updateImportEmail(ImportEmail importEmail)
    {
        importEmail.setUpdateTime(DateUtils.getNowDate());
        return importEmailMapper.updateImportEmail(importEmail);
    }

    /**
     * 批量删除导入邮件
     * 
     * @param ids 需要删除的导入邮件主键
     * @return 结果
     */
    @Override
    public int deleteImportEmailByIds(Long[] ids)
    {
        return importEmailMapper.deleteImportEmailByIds(ids);
    }

    /**
     * 删除导入邮件信息
     * 
     * @param id 导入邮件主键
     * @return 结果
     */
    @Override
    public int deleteImportEmailById(Long id)
    {
        return importEmailMapper.deleteImportEmailById(id);
    }

    /**
     * 邮箱任务列表
     * @return
     */
    @Override
    public List<TaskListVO> getTaskList() {
        List<Task> taskList = taskMapper.selectTaskList(new Task());
        List<TaskListVO> taskVOList = new ArrayList<>();
        for (Task task : taskList) {
            TaskListVO taskListVO = new TaskListVO();
            taskListVO.setId(task.getId());
            taskListVO.setAccount(task.getAccount());
            taskVOList.add(taskListVO);
        }

        return taskVOList;
    }

    /**
     * 导入邮件列表（分页）
     * @return
     */
    @Override
    public Pair<Integer, List<ImportListVO>> page() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();

        long count = importEmailMapper.count(userId);
        if (count == 0L) {
            return Pair.of(0, new ArrayList<>());
        }

        List<ImportListVO> importListVOList = importEmailMapper.list(userId);
        return null;
    }
}
