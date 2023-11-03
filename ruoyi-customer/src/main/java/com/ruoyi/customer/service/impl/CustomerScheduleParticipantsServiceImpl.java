package com.ruoyi.customer.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.CustomerScheduleParticipantsMapper;
import com.ruoyi.customer.domain.CustomerScheduleParticipants;
import com.ruoyi.customer.service.ICustomerScheduleParticipantsService;

import javax.annotation.Resource;

/**
 * 客户日程参与人Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-11-03
 */
@Service
public class CustomerScheduleParticipantsServiceImpl implements ICustomerScheduleParticipantsService 
{
    @Resource
    private CustomerScheduleParticipantsMapper customerScheduleParticipantsMapper;

    /**
     * 查询客户日程参与人
     * 
     * @param id 客户日程参与人主键
     * @return 客户日程参与人
     */
    @Override
    public CustomerScheduleParticipants selectCustomerScheduleParticipantsById(Long id)
    {
        return customerScheduleParticipantsMapper.selectCustomerScheduleParticipantsById(id);
    }

    /**
     * 查询客户日程参与人列表
     * 
     * @param customerScheduleParticipants 客户日程参与人
     * @return 客户日程参与人
     */
    @Override
    public List<CustomerScheduleParticipants> selectCustomerScheduleParticipantsList(CustomerScheduleParticipants customerScheduleParticipants)
    {
        return customerScheduleParticipantsMapper.selectCustomerScheduleParticipantsList(customerScheduleParticipants);
    }

    /**
     * 新增客户日程参与人
     * 
     * @param customerScheduleParticipants 客户日程参与人
     * @return 结果
     */
    @Override
    public int insertCustomerScheduleParticipants(CustomerScheduleParticipants customerScheduleParticipants)
    {
        customerScheduleParticipants.setCreateTime(DateUtils.getNowDate());
        return customerScheduleParticipantsMapper.insertCustomerScheduleParticipants(customerScheduleParticipants);
    }

    /**
     * 修改客户日程参与人
     * 
     * @param customerScheduleParticipants 客户日程参与人
     * @return 结果
     */
    @Override
    public int updateCustomerScheduleParticipants(CustomerScheduleParticipants customerScheduleParticipants)
    {
        customerScheduleParticipants.setUpdateTime(DateUtils.getNowDate());
        return customerScheduleParticipantsMapper.updateCustomerScheduleParticipants(customerScheduleParticipants);
    }

    /**
     * 批量删除客户日程参与人
     * 
     * @param ids 需要删除的客户日程参与人主键
     * @return 结果
     */
    @Override
    public int deleteCustomerScheduleParticipantsByIds(Long[] ids)
    {
        return customerScheduleParticipantsMapper.deleteCustomerScheduleParticipantsByIds(ids);
    }

    /**
     * 删除客户日程参与人信息
     * 
     * @param id 客户日程参与人主键
     * @return 结果
     */
    @Override
    public int deleteCustomerScheduleParticipantsById(Long id)
    {
        return customerScheduleParticipantsMapper.deleteCustomerScheduleParticipantsById(id);
    }
}
