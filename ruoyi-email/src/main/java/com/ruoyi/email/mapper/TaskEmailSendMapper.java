package com.ruoyi.email.mapper;

import java.util.List;

import com.ruoyi.email.domain.TaskEmailSend;
import com.ruoyi.email.domain.vo.email.SendEmailInfoListVO;
import org.apache.ibatis.annotations.Param;

/**
 * 发送邮件Mapper接口
 * 
 * @author tangJM
 * @date 2023-07-31
 */
public interface TaskEmailSendMapper 
{
    /**
     * 查询发送邮件
     * 
     * @param id 发送邮件主键
     * @return 发送邮件
     */
    public TaskEmailSend selectTaskEmailSendById(Long id);

    /**
     * 查询发送邮件列表
     * 
     * @param taskEmailSend 发送邮件
     * @return 发送邮件集合
     */
    public List<TaskEmailSend> selectTaskEmailSendList(TaskEmailSend taskEmailSend);

    /**
     * 新增发送邮件
     * 
     * @param taskEmailSend 发送邮件
     * @return 结果
     */
    public int insertTaskEmailSend(TaskEmailSend taskEmailSend);

    /**
     * 修改发送邮件
     * 
     * @param taskEmailSend 发送邮件
     * @return 结果
     */
    public int updateTaskEmailSend(TaskEmailSend taskEmailSend);

    /**
     * 删除发送邮件
     * 
     * @param id 发送邮件主键
     * @return 结果
     */
    public int deleteTaskEmailSendById(Long id);

    /**
     * 批量删除发送邮件
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTaskEmailSendByIds(Long[] ids);

    /**
     * 获取邮件信息-发送
     * @param id
     * @param userId
     * @return
     */
    TaskEmailSend getTaskEmailSendById(@Param("id") Long id, @Param("createId") Long userId);

    /**
     * 根据id修改状态
     * @param status
     * @param id
     */
    void updateStatusById(@Param("status") int status, @Param("id") Long id);

    /**
     * 获取任务下发送到的邮件数量
     * @param taskIdList
     * @param delFlag
     * @param status
     * @return
     */
    int countByTaskId(@Param("taskIdList") List<Long> taskIdList, @Param("delFlag") String delFlag, @Param("status") Integer status);

    /**
     * 获取任务下发送到的邮件列表
     * @param taskIdList
     * @param delFlag
     * @param status
     * @param offset
     * @param limit
     * @return
     */
    List<SendEmailInfoListVO> selectTaskEmailSendByTaskIdPage(@Param("taskIdList") List<Long> taskIdList, @Param("delFlag") String delFlag, @Param("status") Integer status, @Param("offset") int offset, @Param("limit") int limit);
}
