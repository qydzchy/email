package com.ruoyi.email.mapper;

import java.util.List;
import com.ruoyi.email.domain.TaskEmailLabel;
import org.apache.ibatis.annotations.Param;

/**
 * 邮件标签Mapper接口
 * 
 * @author tangJM
 * @date 2023-10-10
 */
public interface TaskEmailLabelMapper 
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
     * 删除邮件标签
     * 
     * @param id 邮件标签主键
     * @return 结果
     */
    public int deleteTaskEmailLabelById(Long id);

    /**
     * 批量删除邮件标签
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTaskEmailLabelByIds(Long[] ids);

    /**
     * 根据邮件id和标签id删除
     * @param emailId
     * @param labelId
     * @param userId
     */
    void deleteByEmailIdAndLabelId(@Param("emailId") Long emailId, @Param("labelId") Long labelId, @Param("createId") Long userId);
}
