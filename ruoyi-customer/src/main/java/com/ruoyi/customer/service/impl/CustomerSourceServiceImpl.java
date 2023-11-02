package com.ruoyi.customer.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.CustomerSourceMapper;
import com.ruoyi.customer.domain.CustomerSource;
import com.ruoyi.customer.service.ICustomerSourceService;

/**
 * 客户来源关联Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-11-02
 */
@Service
public class CustomerSourceServiceImpl implements ICustomerSourceService 
{
    @Autowired
    private CustomerSourceMapper customerSourceMapper;

    /**
     * 查询客户来源关联
     * 
     * @param id 客户来源关联主键
     * @return 客户来源关联
     */
    @Override
    public CustomerSource selectCustomerSourceById(Long id)
    {
        return customerSourceMapper.selectCustomerSourceById(id);
    }

    /**
     * 查询客户来源关联列表
     * 
     * @param customerSource 客户来源关联
     * @return 客户来源关联
     */
    @Override
    public List<CustomerSource> selectCustomerSourceList(CustomerSource customerSource)
    {
        return customerSourceMapper.selectCustomerSourceList(customerSource);
    }

    /**
     * 新增客户来源关联
     * 
     * @param customerSource 客户来源关联
     * @return 结果
     */
    @Override
    public int insertCustomerSource(CustomerSource customerSource)
    {
        return customerSourceMapper.insertCustomerSource(customerSource);
    }

    /**
     * 修改客户来源关联
     * 
     * @param customerSource 客户来源关联
     * @return 结果
     */
    @Override
    public int updateCustomerSource(CustomerSource customerSource)
    {
        return customerSourceMapper.updateCustomerSource(customerSource);
    }

    /**
     * 批量删除客户来源关联
     * 
     * @param ids 需要删除的客户来源关联主键
     * @return 结果
     */
    @Override
    public int deleteCustomerSourceByIds(Long[] ids)
    {
        return customerSourceMapper.deleteCustomerSourceByIds(ids);
    }

    /**
     * 删除客户来源关联信息
     * 
     * @param id 客户来源关联主键
     * @return 结果
     */
    @Override
    public int deleteCustomerSourceById(Long id)
    {
        return customerSourceMapper.deleteCustomerSourceById(id);
    }
}
