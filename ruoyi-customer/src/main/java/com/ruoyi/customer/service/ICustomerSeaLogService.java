package com.ruoyi.customer.service;

import java.util.List;
import com.ruoyi.customer.domain.CustomerSeaLog;

/**
 * 客户私海/公海日志Service接口
 * 
 * @author tangJM.
 * @date 2023-11-15
 */
public interface ICustomerSeaLogService 
{
    /**
     * 查询客户私海/公海日志
     * 
     * @param id 客户私海/公海日志主键
     * @return 客户私海/公海日志
     */
    public CustomerSeaLog selectCustomerSeaLogById(Long id);

    /**
     * 查询客户私海/公海日志列表
     * 
     * @param customerSeaLog 客户私海/公海日志
     * @return 客户私海/公海日志集合
     */
    public List<CustomerSeaLog> selectCustomerSeaLogList(CustomerSeaLog customerSeaLog);

    /**
     * 新增客户私海/公海日志
     * 
     * @param customerSeaLog 客户私海/公海日志
     * @return 结果
     */
    public int insertCustomerSeaLog(CustomerSeaLog customerSeaLog);

    /**
     * 修改客户私海/公海日志
     * 
     * @param customerSeaLog 客户私海/公海日志
     * @return 结果
     */
    public int updateCustomerSeaLog(CustomerSeaLog customerSeaLog);

    /**
     * 批量删除客户私海/公海日志
     * 
     * @param ids 需要删除的客户私海/公海日志主键集合
     * @return 结果
     */
    public int deleteCustomerSeaLogByIds(Long[] ids);

    /**
     * 删除客户私海/公海日志信息
     * 
     * @param id 客户私海/公海日志主键
     * @return 结果
     */
    public int deleteCustomerSeaLogById(Long id);
}
