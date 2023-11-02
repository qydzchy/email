package com.ruoyi.customer.service;

import java.util.List;
import com.ruoyi.customer.domain.CustomerTag;

/**
 * 客户标签关联Service接口
 * 
 * @author tangJM.
 * @date 2023-11-02
 */
public interface ICustomerTagService 
{
    /**
     * 查询客户标签关联
     * 
     * @param id 客户标签关联主键
     * @return 客户标签关联
     */
    public CustomerTag selectCustomerTagById(Long id);

    /**
     * 查询客户标签关联列表
     * 
     * @param customerTag 客户标签关联
     * @return 客户标签关联集合
     */
    public List<CustomerTag> selectCustomerTagList(CustomerTag customerTag);

    /**
     * 新增客户标签关联
     * 
     * @param customerTag 客户标签关联
     * @return 结果
     */
    public int insertCustomerTag(CustomerTag customerTag);

    /**
     * 修改客户标签关联
     * 
     * @param customerTag 客户标签关联
     * @return 结果
     */
    public int updateCustomerTag(CustomerTag customerTag);

    /**
     * 批量删除客户标签关联
     * 
     * @param ids 需要删除的客户标签关联主键集合
     * @return 结果
     */
    public int deleteCustomerTagByIds(Long[] ids);

    /**
     * 删除客户标签关联信息
     * 
     * @param id 客户标签关联主键
     * @return 结果
     */
    public int deleteCustomerTagById(Long id);
}
