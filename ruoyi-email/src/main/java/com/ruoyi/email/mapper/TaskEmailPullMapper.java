package com.ruoyi.email.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.email.domain.TaskEmailPull;
import com.ruoyi.email.domain.vo.email.PullEmailInfoListVO;
import org.apache.ibatis.annotations.Param;

/**
 * 拉取邮件Mapper接口
 * 
 * @author tangJM
 * @date 2023-07-31
 */
public interface TaskEmailPullMapper 
{
    /**
     * 查询拉取邮件
     * 
     * @param id 拉取邮件主键
     * @return 拉取邮件
     */
    public TaskEmailPull selectTaskEmailPullById(Long id);

    /**
     * 查询拉取邮件列表
     * 
     * @param taskEmailPull 拉取邮件
     * @return 拉取邮件集合
     */
    public List<TaskEmailPull> selectTaskEmailPullList(TaskEmailPull taskEmailPull);

    /**
     * 新增拉取邮件
     * 
     * @param taskEmailPull 拉取邮件
     * @return 结果
     */
    public int insertTaskEmailPull(TaskEmailPull taskEmailPull);

    /**
     * 修改拉取邮件
     * 
     * @param taskEmailPull 拉取邮件
     * @return 结果
     */
    public int updateTaskEmailPull(TaskEmailPull taskEmailPull);

    /**
     * 删除拉取邮件
     * 
     * @param id 拉取邮件主键
     * @return 结果
     */
    public int deleteTaskEmailPullById(Long id);

    /**
     * 批量删除拉取邮件
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTaskEmailPullByIds(Long[] ids);

    /**
     * 获取拉取邮件数量
     * @param ids
     * @return
     */
    List<Map<String, Object>> getPullEmailQuantityByIds(@Param("ids") List<Long> ids);

    /**
     *
     * @param taskIdList
     * @param offset
     * @param limit
     * @return
     */
    List<PullEmailInfoListVO> selectTaskEmailPullByTaskIdPage(@Param("taskIdList") List<Long> taskIdList, @Param("readFlag") Boolean readFlag, @Param("pendingFlag") Boolean pendingFlag, @Param("offset") Integer offset, @Param("limit") Integer limit);

    /**
     * 获取任务下拉取邮件数量
     * @param taskIdList
     * @return
     */
    int countByTaskId(@Param("taskIdList") List<Long> taskIdList, @Param("readFlag") Boolean readFlag, @Param("pendingFlag") Boolean pendingFlag);

    /**
     * 查询拉取邮件的uid
     * @param taskId
     * @return
     */
    List<String> getUidsByTaskId(@Param("taskId") Long taskId);

    /**
     * 更新固定状态
     * @param id
     * @param fixedFlag
     * @param createId
     * @return
     */
    boolean updateFixed(@Param("id") Long id, @Param("fixedFlag") Boolean fixedFlag, @Param("createId") Long createId);

    /**
     * 根据id获取拉取的邮件信息
     * @param id
     * @param createId
     * @return
     */
    TaskEmailPull getById(@Param("id") Long id, @Param("createId") Long createId);
}
