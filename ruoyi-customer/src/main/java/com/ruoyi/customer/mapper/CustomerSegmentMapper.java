package com.ruoyi.customer.mapper;

import java.util.List;
import com.ruoyi.customer.domain.CustomerSegment;

/**
 * 客户客群关联Mapper接口
 * 
 * @author tangJM.
 * @date 2023-11-02
 */
public interface CustomerSegmentMapper 
{
    /**
     * 查询客户客群关联
     * 
     * @param id 客户客群关联主键
     * @return 客户客群关联
     */
    public CustomerSegment selectCustomerSegmentById(Long id);

    /**
     * 查询客户客群关联列表
     * 
     * @param customerSegment 客户客群关联
     * @return 客户客群关联集合
     */
    public List<CustomerSegment> selectCustomerSegmentList(CustomerSegment customerSegment);

    /**
     * 新增客户客群关联
     * 
     * @param customerSegment 客户客群关联
     * @return 结果
     */
    public int insertCustomerSegment(CustomerSegment customerSegment);

    /**
     * 修改客户客群关联
     * 
     * @param customerSegment 客户客群关联
     * @return 结果
     */
    public int updateCustomerSegment(CustomerSegment customerSegment);

    /**
     * 删除客户客群关联
     * 
     * @param id 客户客群关联主键
     * @return 结果
     */
    public int deleteCustomerSegmentById(Long id);

    /**
     * 批量删除客户客群关联
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCustomerSegmentByIds(Long[] ids);
}
