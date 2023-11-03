package com.ruoyi.customer.service;

import java.util.List;
import com.ruoyi.customer.domain.CustomerScheduleParticipants;

/**
 * 客户日程参与人Service接口
 * 
 * @author tangJM.
 * @date 2023-11-03
 */
public interface ICustomerScheduleParticipantsService 
{
    /**
     * 查询客户日程参与人
     * 
     * @param id 客户日程参与人主键
     * @return 客户日程参与人
     */
    public CustomerScheduleParticipants selectCustomerScheduleParticipantsById(Long id);

    /**
     * 查询客户日程参与人列表
     * 
     * @param customerScheduleParticipants 客户日程参与人
     * @return 客户日程参与人集合
     */
    public List<CustomerScheduleParticipants> selectCustomerScheduleParticipantsList(CustomerScheduleParticipants customerScheduleParticipants);

    /**
     * 新增客户日程参与人
     * 
     * @param customerScheduleParticipants 客户日程参与人
     * @return 结果
     */
    public int insertCustomerScheduleParticipants(CustomerScheduleParticipants customerScheduleParticipants);

    /**
     * 修改客户日程参与人
     * 
     * @param customerScheduleParticipants 客户日程参与人
     * @return 结果
     */
    public int updateCustomerScheduleParticipants(CustomerScheduleParticipants customerScheduleParticipants);

    /**
     * 批量删除客户日程参与人
     * 
     * @param ids 需要删除的客户日程参与人主键集合
     * @return 结果
     */
    public int deleteCustomerScheduleParticipantsByIds(Long[] ids);

    /**
     * 删除客户日程参与人信息
     * 
     * @param id 客户日程参与人主键
     * @return 结果
     */
    public int deleteCustomerScheduleParticipantsById(Long id);
}
