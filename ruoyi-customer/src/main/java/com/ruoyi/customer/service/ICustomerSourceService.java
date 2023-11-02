package com.ruoyi.customer.service;

import java.util.List;
import com.ruoyi.customer.domain.CustomerSource;

/**
 * 客户来源关联Service接口
 * 
 * @author tangJM.
 * @date 2023-11-02
 */
public interface ICustomerSourceService 
{
    /**
     * 查询客户来源关联
     * 
     * @param id 客户来源关联主键
     * @return 客户来源关联
     */
    public CustomerSource selectCustomerSourceById(Long id);

    /**
     * 查询客户来源关联列表
     * 
     * @param customerSource 客户来源关联
     * @return 客户来源关联集合
     */
    public List<CustomerSource> selectCustomerSourceList(CustomerSource customerSource);

    /**
     * 新增客户来源关联
     * 
     * @param customerSource 客户来源关联
     * @return 结果
     */
    public int insertCustomerSource(CustomerSource customerSource);

    /**
     * 修改客户来源关联
     * 
     * @param customerSource 客户来源关联
     * @return 结果
     */
    public int updateCustomerSource(CustomerSource customerSource);

    /**
     * 批量删除客户来源关联
     * 
     * @param ids 需要删除的客户来源关联主键集合
     * @return 结果
     */
    public int deleteCustomerSourceByIds(Long[] ids);

    /**
     * 删除客户来源关联信息
     * 
     * @param id 客户来源关联主键
     * @return 结果
     */
    public int deleteCustomerSourceById(Long id);
}
