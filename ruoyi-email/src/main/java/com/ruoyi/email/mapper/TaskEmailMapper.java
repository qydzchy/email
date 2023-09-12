package com.ruoyi.email.mapper;

import java.util.List;
import com.ruoyi.email.domain.TaskEmail;
import com.ruoyi.email.domain.vo.email.EmailListVO;
import org.apache.ibatis.annotations.Param;

/**
 * 邮件Mapper接口
 * 
 * @author tangJM.
 * @date 2023-09-12
 */
public interface TaskEmailMapper 
{
    /**
     * 查询邮件
     * 
     * @param id 邮件主键
     * @return 邮件
     */
    public TaskEmail selectTaskEmailById(Long id);

    /**
     * 查询邮件列表
     * 
     * @param taskEmail 邮件
     * @return 邮件集合
     */
    public List<TaskEmail> selectTaskEmailList(TaskEmail taskEmail);

    /**
     * 新增邮件
     * 
     * @param taskEmail 邮件
     * @return 结果
     */
    public int insertTaskEmail(TaskEmail taskEmail);

    /**
     * 修改邮件
     * 
     * @param taskEmail 邮件
     * @return 结果
     */
    public int updateTaskEmail(TaskEmail taskEmail);

    /**
     * 删除邮件
     * 
     * @param id 邮件主键
     * @return 结果
     */
    public int deleteTaskEmailById(Long id);

    /**
     * 批量删除邮件
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTaskEmailByIds(Long[] ids);

    /**
     * 统计总数
     * @param taskIdList
     * @param type
     * @param readFlag
     * @param pendingFlag
     * @param delFlag
     * @param status
     * @return
     */
    int count(@Param("taskIdList") List<Long> taskIdList, @Param("type") Integer type, @Param("readFlag") Boolean readFlag, @Param("pendingFlag") Boolean pendingFlag, @Param("delFlag") Boolean delFlag, @Param("status") Integer status);

    /**
     * 查询邮件列表数据（首页）
     * @param taskIdList
     * @param type
     * @param readFlag
     * @param pendingFlag
     * @param delFlag
     * @param status
     * @param offset
     * @param limit
     * @return
     */
    List<EmailListVO> selectTaskEmailPage(@Param("taskIdList") List<Long> taskIdList, @Param("type") Integer type, @Param("readFlag") Boolean readFlag, @Param("pendingFlag") Boolean pendingFlag, @Param("delFlag") Boolean delFlag, @Param("status") Integer status, @Param("offset") int offset, @Param("limit") int limit);

    /**
     * 根据id获取邮件信息
     * @param id
     * @param createId
     * @return
     */
    TaskEmail getTaskEmailById(@Param("id") Long id, @Param("createId") Long createId);

    /**
     * 更新固定状态
     * @param id
     * @param fixedFlag
     * @param createId
     * @return
     */
    boolean updateFixed(@Param("id") Long id, @Param("fixedFlag") Boolean fixedFlag, @Param("createId") Long createId);
}
