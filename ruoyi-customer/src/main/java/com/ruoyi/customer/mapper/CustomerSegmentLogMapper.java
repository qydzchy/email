package com.ruoyi.customer.mapper;

import java.util.List;
import com.ruoyi.customer.domain.CustomerSegmentLog;

/**
 * 客户关联客群日志Mapper接口
 * 
 * @author tangJM.
 * @date 2023-11-15
 */
public interface CustomerSegmentLogMapper 
{
    /**
     * 查询客户关联客群日志
     * 
     * @param id 客户关联客群日志主键
     * @return 客户关联客群日志
     */
    public CustomerSegmentLog selectCustomerSegmentLogById(Long id);

    /**
     * 查询客户关联客群日志列表
     * 
     * @param customerSegmentLog 客户关联客群日志
     * @return 客户关联客群日志集合
     */
    public List<CustomerSegmentLog> selectCustomerSegmentLogList(CustomerSegmentLog customerSegmentLog);

    /**
     * 新增客户关联客群日志
     * 
     * @param customerSegmentLog 客户关联客群日志
     * @return 结果
     */
    public int insertCustomerSegmentLog(CustomerSegmentLog customerSegmentLog);

    /**
     * 修改客户关联客群日志
     * 
     * @param customerSegmentLog 客户关联客群日志
     * @return 结果
     */
    public int updateCustomerSegmentLog(CustomerSegmentLog customerSegmentLog);

    /**
     * 删除客户关联客群日志
     * 
     * @param id 客户关联客群日志主键
     * @return 结果
     */
    public int deleteCustomerSegmentLogById(Long id);

    /**
     * 批量删除客户关联客群日志
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCustomerSegmentLogByIds(Long[] ids);
}
