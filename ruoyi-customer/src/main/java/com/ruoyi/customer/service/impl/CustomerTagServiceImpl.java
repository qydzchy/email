package com.ruoyi.customer.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.CustomerTagMapper;
import com.ruoyi.customer.domain.CustomerTag;
import com.ruoyi.customer.service.ICustomerTagService;

/**
 * 客户标签关联Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-11-02
 */
@Service
public class CustomerTagServiceImpl implements ICustomerTagService 
{
    @Autowired
    private CustomerTagMapper customerTagMapper;

    /**
     * 查询客户标签关联
     * 
     * @param id 客户标签关联主键
     * @return 客户标签关联
     */
    @Override
    public CustomerTag selectCustomerTagById(Long id)
    {
        return customerTagMapper.selectCustomerTagById(id);
    }

    /**
     * 查询客户标签关联列表
     * 
     * @param customerTag 客户标签关联
     * @return 客户标签关联
     */
    @Override
    public List<CustomerTag> selectCustomerTagList(CustomerTag customerTag)
    {
        return customerTagMapper.selectCustomerTagList(customerTag);
    }

    /**
     * 新增客户标签关联
     * 
     * @param customerTag 客户标签关联
     * @return 结果
     */
    @Override
    public int insertCustomerTag(CustomerTag customerTag)
    {
        return customerTagMapper.insertCustomerTag(customerTag);
    }

    /**
     * 修改客户标签关联
     * 
     * @param customerTag 客户标签关联
     * @return 结果
     */
    @Override
    public int updateCustomerTag(CustomerTag customerTag)
    {
        return customerTagMapper.updateCustomerTag(customerTag);
    }

    /**
     * 批量删除客户标签关联
     * 
     * @param ids 需要删除的客户标签关联主键
     * @return 结果
     */
    @Override
    public int deleteCustomerTagByIds(Long[] ids)
    {
        return customerTagMapper.deleteCustomerTagByIds(ids);
    }

    /**
     * 删除客户标签关联信息
     * 
     * @param id 客户标签关联主键
     * @return 结果
     */
    @Override
    public int deleteCustomerTagById(Long id)
    {
        return customerTagMapper.deleteCustomerTagById(id);
    }
}
