package com.ruoyi.customer.mapper;

import java.util.List;
import com.ruoyi.customer.domain.CustomerPublicleads;

/**
 * 移入客户到公海Mapper接口
 * 
 * @author tangJM.
 * @date 2023-11-04
 */
public interface CustomerPublicleadsMapper 
{
    /**
     * 查询移入客户到公海
     * 
     * @param id 移入客户到公海主键
     * @return 移入客户到公海
     */
    public CustomerPublicleads selectCustomerPublicleadsById(Long id);

    /**
     * 查询移入客户到公海列表
     * 
     * @param customerPublicleads 移入客户到公海
     * @return 移入客户到公海集合
     */
    public List<CustomerPublicleads> selectCustomerPublicleadsList(CustomerPublicleads customerPublicleads);

    /**
     * 新增移入客户到公海
     * 
     * @param customerPublicleads 移入客户到公海
     * @return 结果
     */
    public int insertCustomerPublicleads(CustomerPublicleads customerPublicleads);

    /**
     * 修改移入客户到公海
     * 
     * @param customerPublicleads 移入客户到公海
     * @return 结果
     */
    public int updateCustomerPublicleads(CustomerPublicleads customerPublicleads);

    /**
     * 删除移入客户到公海
     * 
     * @param id 移入客户到公海主键
     * @return 结果
     */
    public int deleteCustomerPublicleadsById(Long id);

    /**
     * 批量删除移入客户到公海
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCustomerPublicleadsByIds(Long[] ids);
}
