package com.ruoyi.email.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
     * @param statusList
     * @return
     */
    int count(@Param("taskIdList") List<Long> taskIdList, @Param("type") Integer type, @Param("readFlag") Boolean readFlag, @Param("pendingFlag") Boolean pendingFlag, @Param("delFlag") String delFlag, @Param("statusList") List<Integer> statusList);

    /**
     * 查询邮件列表数据（首页）
     * @param taskIdList
     * @param type
     * @param readFlag
     * @param pendingFlag
     * @param delFlag
     * @param statusList
     * @param offset
     * @param limit
     * @return
     */
    List<EmailListVO> selectTaskEmailPage(@Param("taskIdList") List<Long> taskIdList, @Param("type") Integer type, @Param("readFlag") Boolean readFlag, @Param("pendingFlag") Boolean pendingFlag, @Param("delFlag") String delFlag, @Param("statusList") List<Integer> statusList, @Param("offset") int offset, @Param("limit") int limit);

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
     * @return
     */
    boolean updateFixed(@Param("id") Long id, @Param("fixedFlag") Boolean fixedFlag);

    /**
     * 查询拉取邮件的uid
     * @param taskId
     * @return
     */
    List<String> getUidsByTaskId(@Param("taskId") Long taskId);

    /**
     * 获取拉取邮件数量
     * @param ids
     * @param type
     * @return
     */
    List<Map<String, Object>> getEmailQuantityByIds(@Param("ids") List<Long> ids, @Param("type") Integer type);

    /**
     * 批量删除邮件（物理删除）
     * @param ids
     * @param updateId
     * @param updateBy
     * @param updateTime
     * @return
     */
    void removeTaskEmailIds(@Param("ids") List<Long> ids, @Param("updateId") Long updateId, @Param("updateBy") String updateBy, @Param("updateTime") Date updateTime);

    /**
     * 根据邮件状态
     * @param status
     * @param id
     */
    void updateStatusById(@Param("status") int status, @Param("id") Long id);
}
