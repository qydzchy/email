package com.ruoyi.customer.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.CustomerContactMapper;
import com.ruoyi.customer.domain.CustomerContact;
import com.ruoyi.customer.service.ICustomerContactService;

/**
 * 客户联系人Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-11-02
 */
@Service
public class CustomerContactServiceImpl implements ICustomerContactService 
{
    @Autowired
    private CustomerContactMapper customerContactMapper;

    /**
     * 查询客户联系人
     * 
     * @param id 客户联系人主键
     * @return 客户联系人
     */
    @Override
    public CustomerContact selectCustomerContactById(Long id)
    {
        return customerContactMapper.selectCustomerContactById(id);
    }

    /**
     * 查询客户联系人列表
     * 
     * @param customerContact 客户联系人
     * @return 客户联系人
     */
    @Override
    public List<CustomerContact> selectCustomerContactList(CustomerContact customerContact)
    {
        return customerContactMapper.selectCustomerContactList(customerContact);
    }

    /**
     * 新增客户联系人
     * 
     * @param customerContact 客户联系人
     * @return 结果
     */
    @Override
    public int insertCustomerContact(CustomerContact customerContact)
    {
        customerContact.setCreateTime(DateUtils.getNowDate());
        return customerContactMapper.insertCustomerContact(customerContact);
    }

    /**
     * 修改客户联系人
     * 
     * @param customerContact 客户联系人
     * @return 结果
     */
    @Override
    public int updateCustomerContact(CustomerContact customerContact)
    {
        customerContact.setUpdateTime(DateUtils.getNowDate());
        return customerContactMapper.updateCustomerContact(customerContact);
    }

    /**
     * 批量删除客户联系人
     * 
     * @param ids 需要删除的客户联系人主键
     * @return 结果
     */
    @Override
    public int deleteCustomerContactByIds(Long[] ids)
    {
        return customerContactMapper.deleteCustomerContactByIds(ids);
    }

    /**
     * 删除客户联系人信息
     * 
     * @param id 客户联系人主键
     * @return 结果
     */
    @Override
    public int deleteCustomerContactById(Long id)
    {
        return customerContactMapper.deleteCustomerContactById(id);
    }
}
