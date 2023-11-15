package com.ruoyi.customer.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.CustomerSeaLogMapper;
import com.ruoyi.customer.domain.CustomerSeaLog;
import com.ruoyi.customer.service.ICustomerSeaLogService;

/**
 * 客户私海/公海日志Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-11-15
 */
@Service
public class CustomerSeaLogServiceImpl implements ICustomerSeaLogService 
{
    @Autowired
    private CustomerSeaLogMapper customerSeaLogMapper;

    /**
     * 查询客户私海/公海日志
     * 
     * @param id 客户私海/公海日志主键
     * @return 客户私海/公海日志
     */
    @Override
    public CustomerSeaLog selectCustomerSeaLogById(Long id)
    {
        return customerSeaLogMapper.selectCustomerSeaLogById(id);
    }

    /**
     * 查询客户私海/公海日志列表
     * 
     * @param customerSeaLog 客户私海/公海日志
     * @return 客户私海/公海日志
     */
    @Override
    public List<CustomerSeaLog> selectCustomerSeaLogList(CustomerSeaLog customerSeaLog)
    {
        return customerSeaLogMapper.selectCustomerSeaLogList(customerSeaLog);
    }

    /**
     * 新增客户私海/公海日志
     * 
     * @param customerSeaLog 客户私海/公海日志
     * @return 结果
     */
    @Override
    public int insertCustomerSeaLog(CustomerSeaLog customerSeaLog)
    {
        customerSeaLog.setCreateTime(DateUtils.getNowDate());
        return customerSeaLogMapper.insertCustomerSeaLog(customerSeaLog);
    }

    /**
     * 修改客户私海/公海日志
     * 
     * @param customerSeaLog 客户私海/公海日志
     * @return 结果
     */
    @Override
    public int updateCustomerSeaLog(CustomerSeaLog customerSeaLog)
    {
        return customerSeaLogMapper.updateCustomerSeaLog(customerSeaLog);
    }

    /**
     * 批量删除客户私海/公海日志
     * 
     * @param ids 需要删除的客户私海/公海日志主键
     * @return 结果
     */
    @Override
    public int deleteCustomerSeaLogByIds(Long[] ids)
    {
        return customerSeaLogMapper.deleteCustomerSeaLogByIds(ids);
    }

    /**
     * 删除客户私海/公海日志信息
     * 
     * @param id 客户私海/公海日志主键
     * @return 结果
     */
    @Override
    public int deleteCustomerSeaLogById(Long id)
    {
        return customerSeaLogMapper.deleteCustomerSeaLogById(id);
    }
}
