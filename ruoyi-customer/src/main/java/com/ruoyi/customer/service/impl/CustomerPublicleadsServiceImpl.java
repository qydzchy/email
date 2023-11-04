package com.ruoyi.customer.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.CustomerPublicleadsMapper;
import com.ruoyi.customer.domain.CustomerPublicleads;
import com.ruoyi.customer.service.ICustomerPublicleadsService;

/**
 * 移入客户到公海Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-11-04
 */
@Service
public class CustomerPublicleadsServiceImpl implements ICustomerPublicleadsService 
{
    @Autowired
    private CustomerPublicleadsMapper customerPublicleadsMapper;

    /**
     * 查询移入客户到公海
     * 
     * @param id 移入客户到公海主键
     * @return 移入客户到公海
     */
    @Override
    public CustomerPublicleads selectCustomerPublicleadsById(Long id)
    {
        return customerPublicleadsMapper.selectCustomerPublicleadsById(id);
    }

    /**
     * 查询移入客户到公海列表
     * 
     * @param customerPublicleads 移入客户到公海
     * @return 移入客户到公海
     */
    @Override
    public List<CustomerPublicleads> selectCustomerPublicleadsList(CustomerPublicleads customerPublicleads)
    {
        return customerPublicleadsMapper.selectCustomerPublicleadsList(customerPublicleads);
    }

    /**
     * 新增移入客户到公海
     * 
     * @param customerPublicleads 移入客户到公海
     * @return 结果
     */
    @Override
    public int insertCustomerPublicleads(CustomerPublicleads customerPublicleads)
    {
        customerPublicleads.setCreateTime(DateUtils.getNowDate());
        return customerPublicleadsMapper.insertCustomerPublicleads(customerPublicleads);
    }

    /**
     * 修改移入客户到公海
     * 
     * @param customerPublicleads 移入客户到公海
     * @return 结果
     */
    @Override
    public int updateCustomerPublicleads(CustomerPublicleads customerPublicleads)
    {
        customerPublicleads.setUpdateTime(DateUtils.getNowDate());
        return customerPublicleadsMapper.updateCustomerPublicleads(customerPublicleads);
    }

    /**
     * 批量删除移入客户到公海
     * 
     * @param ids 需要删除的移入客户到公海主键
     * @return 结果
     */
    @Override
    public int deleteCustomerPublicleadsByIds(Long[] ids)
    {
        return customerPublicleadsMapper.deleteCustomerPublicleadsByIds(ids);
    }

    /**
     * 删除移入客户到公海信息
     * 
     * @param id 移入客户到公海主键
     * @return 结果
     */
    @Override
    public int deleteCustomerPublicleadsById(Long id)
    {
        return customerPublicleadsMapper.deleteCustomerPublicleadsById(id);
    }
}
