package com.ruoyi.customer.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.CustomerFollowUpRecordsMapper;
import com.ruoyi.customer.domain.CustomerFollowUpRecords;
import com.ruoyi.customer.service.ICustomerFollowUpRecordsService;

/**
 * 客户写跟进Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-11-02
 */
@Service
public class CustomerFollowUpRecordsServiceImpl implements ICustomerFollowUpRecordsService 
{
    @Autowired
    private CustomerFollowUpRecordsMapper customerFollowUpRecordsMapper;

    /**
     * 查询客户写跟进
     * 
     * @param id 客户写跟进主键
     * @return 客户写跟进
     */
    @Override
    public CustomerFollowUpRecords selectCustomerFollowUpRecordsById(Long id)
    {
        return customerFollowUpRecordsMapper.selectCustomerFollowUpRecordsById(id);
    }

    /**
     * 查询客户写跟进列表
     * 
     * @param customerFollowUpRecords 客户写跟进
     * @return 客户写跟进
     */
    @Override
    public List<CustomerFollowUpRecords> selectCustomerFollowUpRecordsList(CustomerFollowUpRecords customerFollowUpRecords)
    {
        return customerFollowUpRecordsMapper.selectCustomerFollowUpRecordsList(customerFollowUpRecords);
    }

    /**
     * 新增客户写跟进
     * 
     * @param customerFollowUpRecords 客户写跟进
     * @return 结果
     */
    @Override
    public int insertCustomerFollowUpRecords(CustomerFollowUpRecords customerFollowUpRecords)
    {
        customerFollowUpRecords.setCreateTime(DateUtils.getNowDate());
        return customerFollowUpRecordsMapper.insertCustomerFollowUpRecords(customerFollowUpRecords);
    }

    /**
     * 修改客户写跟进
     * 
     * @param customerFollowUpRecords 客户写跟进
     * @return 结果
     */
    @Override
    public int updateCustomerFollowUpRecords(CustomerFollowUpRecords customerFollowUpRecords)
    {
        customerFollowUpRecords.setUpdateTime(DateUtils.getNowDate());
        return customerFollowUpRecordsMapper.updateCustomerFollowUpRecords(customerFollowUpRecords);
    }

    /**
     * 批量删除客户写跟进
     * 
     * @param ids 需要删除的客户写跟进主键
     * @return 结果
     */
    @Override
    public int deleteCustomerFollowUpRecordsByIds(Long[] ids)
    {
        return customerFollowUpRecordsMapper.deleteCustomerFollowUpRecordsByIds(ids);
    }

    /**
     * 删除客户写跟进信息
     * 
     * @param id 客户写跟进主键
     * @return 结果
     */
    @Override
    public int deleteCustomerFollowUpRecordsById(Long id)
    {
        return customerFollowUpRecordsMapper.deleteCustomerFollowUpRecordsById(id);
    }
}
