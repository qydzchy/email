package com.ruoyi.customer.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.CustomerFollowUpPersonnelMapper;
import com.ruoyi.customer.domain.CustomerFollowUpPersonnel;
import com.ruoyi.customer.service.ICustomerFollowUpPersonnelService;

/**
 * 客户跟进人Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-11-02
 */
@Service
public class CustomerFollowUpPersonnelServiceImpl implements ICustomerFollowUpPersonnelService 
{
    @Autowired
    private CustomerFollowUpPersonnelMapper customerFollowUpPersonnelMapper;

    /**
     * 查询客户跟进人
     * 
     * @param id 客户跟进人主键
     * @return 客户跟进人
     */
    @Override
    public CustomerFollowUpPersonnel selectCustomerFollowUpPersonnelById(Long id)
    {
        return customerFollowUpPersonnelMapper.selectCustomerFollowUpPersonnelById(id);
    }

    /**
     * 查询客户跟进人列表
     * 
     * @param customerFollowUpPersonnel 客户跟进人
     * @return 客户跟进人
     */
    @Override
    public List<CustomerFollowUpPersonnel> selectCustomerFollowUpPersonnelList(CustomerFollowUpPersonnel customerFollowUpPersonnel)
    {
        return customerFollowUpPersonnelMapper.selectCustomerFollowUpPersonnelList(customerFollowUpPersonnel);
    }

    /**
     * 新增客户跟进人
     * 
     * @param customerFollowUpPersonnel 客户跟进人
     * @return 结果
     */
    @Override
    public int insertCustomerFollowUpPersonnel(CustomerFollowUpPersonnel customerFollowUpPersonnel)
    {
        return customerFollowUpPersonnelMapper.insertCustomerFollowUpPersonnel(customerFollowUpPersonnel);
    }

    /**
     * 修改客户跟进人
     * 
     * @param customerFollowUpPersonnel 客户跟进人
     * @return 结果
     */
    @Override
    public int updateCustomerFollowUpPersonnel(CustomerFollowUpPersonnel customerFollowUpPersonnel)
    {
        return customerFollowUpPersonnelMapper.updateCustomerFollowUpPersonnel(customerFollowUpPersonnel);
    }

    /**
     * 批量删除客户跟进人
     * 
     * @param ids 需要删除的客户跟进人主键
     * @return 结果
     */
    @Override
    public int deleteCustomerFollowUpPersonnelByIds(Long[] ids)
    {
        return customerFollowUpPersonnelMapper.deleteCustomerFollowUpPersonnelByIds(ids);
    }

    /**
     * 删除客户跟进人信息
     * 
     * @param id 客户跟进人主键
     * @return 结果
     */
    @Override
    public int deleteCustomerFollowUpPersonnelById(Long id)
    {
        return customerFollowUpPersonnelMapper.deleteCustomerFollowUpPersonnelById(id);
    }
}
