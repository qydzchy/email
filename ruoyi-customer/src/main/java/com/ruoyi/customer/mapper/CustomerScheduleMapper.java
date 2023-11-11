package com.ruoyi.customer.mapper;

import java.util.List;
import com.ruoyi.customer.domain.CustomerSchedule;
import com.ruoyi.customer.domain.vo.CustomerScheduleListVO;
import org.apache.ibatis.annotations.Param;

/**
 * 客户日程Mapper接口
 * 
 * @author tangJM.
 * @date 2023-11-03
 */
public interface CustomerScheduleMapper 
{
    /**
     * 查询客户日程
     * 
     * @param id 客户日程主键
     * @return 客户日程
     */
    public CustomerSchedule selectCustomerScheduleById(Long id);

    /**
     * 查询客户日程列表
     * 
     * @param customerSchedule 客户日程
     * @return 客户日程集合
     */
    public List<CustomerSchedule> selectCustomerScheduleList(CustomerSchedule customerSchedule);

    /**
     * 新增客户日程
     * 
     * @param customerSchedule 客户日程
     * @return 结果
     */
    public int insertCustomerSchedule(CustomerSchedule customerSchedule);

    /**
     * 修改客户日程
     * 
     * @param customerSchedule 客户日程
     * @return 结果
     */
    public int updateCustomerSchedule(CustomerSchedule customerSchedule);

    /**
     * 删除客户日程
     * 
     * @param id 客户日程主键
     * @return 结果
     */
    public int deleteCustomerScheduleById(@Param("id") Long id, @Param("updateId") Long updateId, @Param("updateBy") String updateBy);

    /**
     * 批量删除客户日程
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCustomerScheduleByIds(Long[] ids);

    /**
     * 统计客户日程数量
     * @param startTime
     * @param endTime
     * @param userIds
     * @return
     */
    Integer count(@Param("customerId") Long customerId, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("userIds") List<Long> userIds);

    /**
     * 客户日程列表
     * @param startTime
     * @param endTime
     * @param userIds
     * @param offset
     * @param limit
     * @return
     */
    List<CustomerScheduleListVO> list(@Param("customerId") Long customerId, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("userIds") List<Long> userIds, @Param("offset") Integer offset, @Param("limit") Integer limit);

    /**
     * 修改重点关注标志
     * @param id
     * @param updateId
     * @param updateBy
     * @return
     */
    boolean updateFocusFlag(@Param("id") Long id, @Param("updateId") Long updateId, @Param("updateBy") String updateBy);
}
