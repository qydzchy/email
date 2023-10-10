package com.ruoyi.email.service;

import java.util.List;
import com.ruoyi.email.domain.TaskEmailLabel;

/**
 * 邮件标签Service接口
 * 
 * @author tangJM
 * @date 2023-10-10
 */
public interface ITaskEmailLabelService 
{
    /**
     * 查询邮件标签
     * 
     * @param id 邮件标签主键
     * @return 邮件标签
     */
    public TaskEmailLabel selectTaskEmailLabelById(Long id);

    /**
     * 查询邮件标签列表
     * 
     * @param taskEmailLabel 邮件标签
     * @return 邮件标签集合
     */
    public List<TaskEmailLabel> selectTaskEmailLabelList(TaskEmailLabel taskEmailLabel);

    /**
     * 新增邮件标签
     * 
     * @param taskEmailLabel 邮件标签
     * @return 结果
     */
    public int insertTaskEmailLabel(TaskEmailLabel taskEmailLabel);

    /**
     * 修改邮件标签
     * 
     * @param taskEmailLabel 邮件标签
     * @return 结果
     */
    public int updateTaskEmailLabel(TaskEmailLabel taskEmailLabel);

    /**
     * 批量删除邮件标签
     * 
     * @param ids 需要删除的邮件标签主键集合
     * @return 结果
     */
    public int deleteTaskEmailLabelByIds(Long[] ids);

    /**
     * 删除邮件标签信息
     * 
     * @param id 邮件标签主键
     * @return 结果
     */
    public int deleteTaskEmailLabelById(Long id);

    /**
     * 根据邮件id和标签id删除
     * @param id
     * @param labelId
     * @param userId
     */
    void deleteByEmailIdAndLabelId(Long id, Long labelId, Long userId);
}
