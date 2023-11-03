package com.ruoyi.customer.service;

import java.util.List;
import com.ruoyi.customer.domain.CustomerSchedule;
import com.ruoyi.customer.domain.dto.CustomerScheduleAddOrUpdateDTO;

/**
 * 客户日程Service接口
 * 
 * @author tangJM.
 * @date 2023-11-03
 */
public interface ICustomerScheduleService 
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
     * @param customerScheduleAddOrUpdateDTO 客户日程
     * @return 结果
     */
    public boolean insertCustomerSchedule(CustomerScheduleAddOrUpdateDTO customerScheduleAddOrUpdateDTO);

    /**
     * 修改客户日程
     * 
     * @param customerScheduleAddOrUpdateDTO 客户日程
     * @return 结果
     */
    public boolean updateCustomerSchedule(CustomerScheduleAddOrUpdateDTO customerScheduleAddOrUpdateDTO);

    /**
     * 批量删除客户日程
     * 
     * @param ids 需要删除的客户日程主键集合
     * @return 结果
     */
    public int deleteCustomerScheduleByIds(Long[] ids);

    /**
     * 删除客户日程信息
     * 
     * @param id 客户日程主键
     * @return 结果
     */
    public boolean deleteCustomerScheduleById(Long id);
}
