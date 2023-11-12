package com.ruoyi.customer.mapper;

import java.util.List;
import com.ruoyi.customer.domain.CustomerContact;
import com.ruoyi.customer.domain.bo.CustomerContactBO;
import org.apache.ibatis.annotations.Param;

/**
 * 客户联系人Mapper接口
 * 
 * @author tangJM.
 * @date 2023-11-02
 */
public interface CustomerContactMapper 
{
    /**
     * 查询客户联系人
     * 
     * @param id 客户联系人主键
     * @return 客户联系人
     */
    public CustomerContact selectCustomerContactById(Long id);

    /**
     * 查询客户联系人列表
     * 
     * @param customerContact 客户联系人
     * @return 客户联系人集合
     */
    public List<CustomerContact> selectCustomerContactList(CustomerContact customerContact);

    /**
     * 新增客户联系人
     * 
     * @param customerContact 客户联系人
     * @return 结果
     */
    public int insertCustomerContact(CustomerContact customerContact);

    /**
     * 修改客户联系人
     * 
     * @param customerContact 客户联系人
     * @return 结果
     */
    public int updateCustomerContact(CustomerContact customerContact);

    /**
     * 删除客户联系人
     * 
     * @param id 客户联系人主键
     * @return 结果
     */
    public int deleteCustomerContactById(Long id);

    /**
     * 批量删除客户联系人
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCustomerContactByIds(Long[] ids);

    /**
     * 批量插入客户联系人
     * @param customerContactList
     */
    void batchInsertCustomerContact(@Param("detailsContactList") List<CustomerContact> customerContactList);

    /**
     * 根据客户id删除联系人
     * @param customerId
     * @param updateId
     * @param updateBy
     */
    void deleteCustomerContactByCustomerId(@Param("customerId") Long customerId, @Param("updateId") Long updateId, @Param("updateBy") String updateBy);

    /**
     * 根据客户id查询主要联系人
     * @param customerIds
     * @return
     */
    List<CustomerContactBO> selectCustomerMainContactByCustomerIds(@Param("customerIds") List<Long> customerIds);

    /**
     * 查询客户联系人
     * @param customerId
     * @return
     */
    List<CustomerContactBO> selectCustomerContactByCustomerId(@Param("customerId") Long customerId);
}
