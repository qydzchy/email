package com.ruoyi.email.service;

import java.util.List;
import com.ruoyi.email.domain.TaskEmailDealings;

/**
 * 往来邮件Service接口
 * 
 * @author tangJM
 * @date 2023-07-31
 */
public interface ITaskEmailDealingsService 
{
    /**
     * 查询往来邮件
     * 
     * @param id 往来邮件主键
     * @return 往来邮件
     */
    public TaskEmailDealings selectTaskEmailDealingsById(Long id);

    /**
     * 查询往来邮件列表
     * 
     * @param taskEmailDealings 往来邮件
     * @return 往来邮件集合
     */
    public List<TaskEmailDealings> selectTaskEmailDealingsList(TaskEmailDealings taskEmailDealings);

    /**
     * 新增往来邮件
     * 
     * @param taskEmailDealings 往来邮件
     * @return 结果
     */
    public int insertTaskEmailDealings(TaskEmailDealings taskEmailDealings);

    /**
     * 修改往来邮件
     * 
     * @param taskEmailDealings 往来邮件
     * @return 结果
     */
    public int updateTaskEmailDealings(TaskEmailDealings taskEmailDealings);

    /**
     * 批量删除往来邮件
     * 
     * @param ids 需要删除的往来邮件主键集合
     * @return 结果
     */
    public int deleteTaskEmailDealingsByIds(Long[] ids);

    /**
     * 删除往来邮件信息
     * 
     * @param id 往来邮件主键
     * @return 结果
     */
    public int deleteTaskEmailDealingsById(Long id);
}
