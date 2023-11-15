package com.ruoyi.customer.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.CustomerSegmentLogMapper;
import com.ruoyi.customer.domain.CustomerSegmentLog;
import com.ruoyi.customer.service.ICustomerSegmentLogService;

/**
 * 客户关联客群日志Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-11-15
 */
@Service
public class CustomerSegmentLogServiceImpl implements ICustomerSegmentLogService 
{
    @Autowired
    private CustomerSegmentLogMapper customerSegmentLogMapper;

    /**
     * 查询客户关联客群日志
     * 
     * @param id 客户关联客群日志主键
     * @return 客户关联客群日志
     */
    @Override
    public CustomerSegmentLog selectCustomerSegmentLogById(Long id)
    {
        return customerSegmentLogMapper.selectCustomerSegmentLogById(id);
    }

    /**
     * 查询客户关联客群日志列表
     * 
     * @param customerSegmentLog 客户关联客群日志
     * @return 客户关联客群日志
     */
    @Override
    public List<CustomerSegmentLog> selectCustomerSegmentLogList(CustomerSegmentLog customerSegmentLog)
    {
        return customerSegmentLogMapper.selectCustomerSegmentLogList(customerSegmentLog);
    }

    /**
     * 新增客户关联客群日志
     * 
     * @param customerSegmentLog 客户关联客群日志
     * @return 结果
     */
    @Override
    public int insertCustomerSegmentLog(CustomerSegmentLog customerSegmentLog)
    {
        customerSegmentLog.setCreateTime(DateUtils.getNowDate());
        return customerSegmentLogMapper.insertCustomerSegmentLog(customerSegmentLog);
    }

    /**
     * 修改客户关联客群日志
     * 
     * @param customerSegmentLog 客户关联客群日志
     * @return 结果
     */
    @Override
    public int updateCustomerSegmentLog(CustomerSegmentLog customerSegmentLog)
    {
        return customerSegmentLogMapper.updateCustomerSegmentLog(customerSegmentLog);
    }

    /**
     * 批量删除客户关联客群日志
     * 
     * @param ids 需要删除的客户关联客群日志主键
     * @return 结果
     */
    @Override
    public int deleteCustomerSegmentLogByIds(Long[] ids)
    {
        return customerSegmentLogMapper.deleteCustomerSegmentLogByIds(ids);
    }

    /**
     * 删除客户关联客群日志信息
     * 
     * @param id 客户关联客群日志主键
     * @return 结果
     */
    @Override
    public int deleteCustomerSegmentLogById(Long id)
    {
        return customerSegmentLogMapper.deleteCustomerSegmentLogById(id);
    }
}
