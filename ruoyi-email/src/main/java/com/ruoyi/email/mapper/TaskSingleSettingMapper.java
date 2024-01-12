package com.ruoyi.email.mapper;

import java.util.List;
import com.ruoyi.email.domain.TaskSingleSetting;
import com.ruoyi.email.domain.vo.TaskSingleSettingListVO;
import org.apache.ibatis.annotations.Param;

/**
 * 单个邮箱设置Mapper接口
 * 
 * @author tangJM.
 * @date 2023-12-27
 */
public interface TaskSingleSettingMapper 
{
    /**
     * 查询单个邮箱设置
     * 
     * @param id 单个邮箱设置主键
     * @return 单个邮箱设置
     */
    public TaskSingleSetting selectTaskSingleSettingById(Long id);

    /**
     * 查询单个邮箱设置列表
     * 
     * @param taskSingleSetting 单个邮箱设置
     * @return 单个邮箱设置集合
     */
    public List<TaskSingleSetting> selectTaskSingleSettingList(TaskSingleSetting taskSingleSetting);

    /**
     * 新增单个邮箱设置
     * 
     * @param taskSingleSetting 单个邮箱设置
     * @return 结果
     */
    public int insertTaskSingleSetting(TaskSingleSetting taskSingleSetting);

    /**
     * 修改单个邮箱设置
     * 
     * @param taskSingleSetting 单个邮箱设置
     * @return 结果
     */
    public int updateTaskSingleSetting(TaskSingleSetting taskSingleSetting);

    /**
     * 删除单个邮箱设置
     * 
     * @param id 单个邮箱设置主键
     * @return 结果
     */
    public int deleteTaskSingleSettingById(Long id);

    /**
     * 批量删除单个邮箱设置
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTaskSingleSettingByIds(Long[] ids);

    /**
     * 查询单个邮箱设置列表
     * @return
     */
    List<TaskSingleSettingListVO> list(@Param("createId") Long createId);


    /**
     * 批量新增单个邮箱设置
     */
    int batchInsert(@Param("taskSingleSettingList") List<TaskSingleSetting> taskSingleSettingList);

    /**
     * 删除单个邮箱设置
     * @param createId
     */
    void deleteTaskSingleSettingByUserId(@Param("createId") Long createId, @Param("updateId") Long updateId, @Param("updateBy") String updateBy);
}
