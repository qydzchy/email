package com.ruoyi.email.mapper;

import java.util.List;
import com.ruoyi.email.domain.TaskEmailPullLabel;

/**
 * 邮件标签Mapper接口
 * 
 * @author tangJM
 * @date 2023-07-31
 */
public interface TaskEmailPullLabelMapper 
{
    /**
     * 查询邮件标签
     * 
     * @param id 邮件标签主键
     * @return 邮件标签
     */
    public TaskEmailPullLabel selectTaskEmailPullLabelById(Long id);

    /**
     * 查询邮件标签列表
     * 
     * @param taskEmailPullLabel 邮件标签
     * @return 邮件标签集合
     */
    public List<TaskEmailPullLabel> selectTaskEmailPullLabelList(TaskEmailPullLabel taskEmailPullLabel);

    /**
     * 新增邮件标签
     * 
     * @param taskEmailPullLabel 邮件标签
     * @return 结果
     */
    public int insertTaskEmailPullLabel(TaskEmailPullLabel taskEmailPullLabel);

    /**
     * 修改邮件标签
     * 
     * @param taskEmailPullLabel 邮件标签
     * @return 结果
     */
    public int updateTaskEmailPullLabel(TaskEmailPullLabel taskEmailPullLabel);

    /**
     * 删除邮件标签
     * 
     * @param id 邮件标签主键
     * @return 结果
     */
    public int deleteTaskEmailPullLabelById(Long id);

    /**
     * 批量删除邮件标签
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTaskEmailPullLabelByIds(Long[] ids);
}
