package com.ruoyi.customer.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.CustomerSegmentMapper;
import com.ruoyi.customer.domain.CustomerSegment;
import com.ruoyi.customer.service.ICustomerSegmentService;

/**
 * 客户客群关联Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-11-02
 */
@Service
public class CustomerSegmentServiceImpl implements ICustomerSegmentService 
{
    @Autowired
    private CustomerSegmentMapper customerSegmentMapper;

    /**
     * 查询客户客群关联
     * 
     * @param id 客户客群关联主键
     * @return 客户客群关联
     */
    @Override
    public CustomerSegment selectCustomerSegmentById(Long id)
    {
        return customerSegmentMapper.selectCustomerSegmentById(id);
    }

    /**
     * 查询客户客群关联列表
     * 
     * @param customerSegment 客户客群关联
     * @return 客户客群关联
     */
    @Override
    public List<CustomerSegment> selectCustomerSegmentList(CustomerSegment customerSegment)
    {
        return customerSegmentMapper.selectCustomerSegmentList(customerSegment);
    }

    /**
     * 新增客户客群关联
     * 
     * @param customerSegment 客户客群关联
     * @return 结果
     */
    @Override
    public int insertCustomerSegment(CustomerSegment customerSegment)
    {
        return customerSegmentMapper.insertCustomerSegment(customerSegment);
    }

    /**
     * 修改客户客群关联
     * 
     * @param customerSegment 客户客群关联
     * @return 结果
     */
    @Override
    public int updateCustomerSegment(CustomerSegment customerSegment)
    {
        return customerSegmentMapper.updateCustomerSegment(customerSegment);
    }

    /**
     * 批量删除客户客群关联
     * 
     * @param ids 需要删除的客户客群关联主键
     * @return 结果
     */
    @Override
    public int deleteCustomerSegmentByIds(Long[] ids)
    {
        return customerSegmentMapper.deleteCustomerSegmentByIds(ids);
    }

    /**
     * 删除客户客群关联信息
     * 
     * @param id 客户客群关联主键
     * @return 结果
     */
    @Override
    public int deleteCustomerSegmentById(Long id)
    {
        return customerSegmentMapper.deleteCustomerSegmentById(id);
    }
}
