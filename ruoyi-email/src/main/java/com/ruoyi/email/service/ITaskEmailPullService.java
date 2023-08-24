package com.ruoyi.email.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.email.domain.TaskEmailPull;
import com.ruoyi.email.domain.vo.email.PullEmailInfoListVO;
import org.springframework.data.util.Pair;

/**
 * 拉取邮件Service接口
 * 
 * @author tangJM
 * @date 2023-07-31
 */
public interface ITaskEmailPullService 
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
     * 批量删除拉取邮件
     * 
     * @param ids 需要删除的拉取邮件主键集合
     * @return 结果
     */
    public int deleteTaskEmailPullByIds(Long[] ids);

    /**
     * 删除拉取邮件信息
     * 
     * @param id 拉取邮件主键
     * @return 结果
     */
    public int deleteTaskEmailPullById(Long id);

    /**
     * 获取拉取邮件数量
     * @param ids
     * @return
     */
    Map<Long, Integer> getPullEmailQuantityByIds(List<Long> ids);

    /**
     * 获取拉取邮件
     * @return
     */
    Pair<Integer, List<Map<String, List<PullEmailInfoListVO>>>> listPullHeader(Long taskId, Integer pageNum, Integer pageSize);
}
