package com.ruoyi.customer.mapper;

import java.util.List;
import com.ruoyi.customer.domain.CustomerFollowUpRecords;
import com.ruoyi.customer.domain.bo.CustomerFollowUpRecordsListBO;
import org.apache.ibatis.annotations.Param;

/**
 * 客户写跟进Mapper接口
 * 
 * @author tangJM.
 * @date 2023-11-02
 */
public interface CustomerFollowUpRecordsMapper 
{
    /**
     * 查询客户写跟进
     * 
     * @param id 客户写跟进主键
     * @return 客户写跟进
     */
    public CustomerFollowUpRecords selectCustomerFollowUpRecordsById(Long id);

    /**
     * 查询客户写跟进列表
     * 
     * @param customerFollowUpRecords 客户写跟进
     * @return 客户写跟进集合
     */
    public List<CustomerFollowUpRecords> selectCustomerFollowUpRecordsList(CustomerFollowUpRecords customerFollowUpRecords);

    /**
     * 新增客户写跟进
     * 
     * @param customerFollowUpRecords 客户写跟进
     * @return 结果
     */
    public int insertCustomerFollowUpRecords(CustomerFollowUpRecords customerFollowUpRecords);

    /**
     * 修改客户写跟进
     * 
     * @param customerFollowUpRecords 客户写跟进
     * @return 结果
     */
    public int updateCustomerFollowUpRecords(CustomerFollowUpRecords customerFollowUpRecords);

    /**
     * 删除客户写跟进
     * 
     * @param id 客户写跟进主键
     * @return 结果
     */
    public int deleteCustomerFollowUpRecordsById(@Param("id") Long id, @Param("updateId") Long updateId, @Param("updateBy") String updateBy);

    /**
     * 批量删除客户写跟进
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCustomerFollowUpRecordsByIds(Long[] ids);

    /**
     * 写跟进列表
     * @param searchText
     * @return
     */
    List<CustomerFollowUpRecordsListBO> list(@Param("customerId") Long customerId, @Param("searchText") String searchText);
}
