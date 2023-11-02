package com.ruoyi.customer.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.customer.domain.Customer;
import com.ruoyi.customer.domain.dto.CustomerAddOrUpdateDTO;

/**
 * 客户详情Service接口
 * 
 * @author tangJM.
 * @date 2023-11-02
 */
public interface ICustomerService 
{
    /**
     * 查询客户详情
     * 
     * @param id 客户详情主键
     * @return 客户详情
     */
    public Customer selectCustomerById(Long id);

    /**
     * 查询客户详情列表
     * 
     * @param customer 客户详情
     * @return 客户详情集合
     */
    public List<Customer> selectCustomerList(Customer customer);

    /**
     * 新增客户详情
     * 
     * @param customerAddOrUpdateDTO 客户详情
     * @return 结果
     */
    public boolean insertCustomer(CustomerAddOrUpdateDTO customerAddOrUpdateDTO);

    /**
     * 修改客户详情
     * 
     * @param customerAddOrUpdateDTO 客户详情
     * @return 结果
     */
    public boolean updateCustomer(CustomerAddOrUpdateDTO customerAddOrUpdateDTO);

    /**
     * 批量删除客户详情
     * 
     * @param ids 需要删除的客户详情主键集合
     * @return 结果
     */
    public int deleteCustomerByIds(Long[] ids);

    /**
     * 删除客户详情信息
     * 
     * @param id 客户详情主键
     * @return 结果
     */
    public int deleteCustomerById(Long id);

    /**
     * 客户列表（分页）
     * @param segmentId
     * @param seaType
     * @param pageNum
     * @param pageSize
     * @return
     */
    Map<String, Object> list(Long segmentId, Integer seaType, Integer pageNum, Integer pageSize);
}
