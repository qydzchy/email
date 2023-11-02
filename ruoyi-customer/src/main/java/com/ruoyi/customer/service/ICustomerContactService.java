package com.ruoyi.customer.service;

import java.util.List;
import com.ruoyi.customer.domain.CustomerContact;

/**
 * 客户联系人Service接口
 * 
 * @author tangJM.
 * @date 2023-11-02
 */
public interface ICustomerContactService 
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
     * 批量删除客户联系人
     * 
     * @param ids 需要删除的客户联系人主键集合
     * @return 结果
     */
    public int deleteCustomerContactByIds(Long[] ids);

    /**
     * 删除客户联系人信息
     * 
     * @param id 客户联系人主键
     * @return 结果
     */
    public int deleteCustomerContactById(Long id);
}
