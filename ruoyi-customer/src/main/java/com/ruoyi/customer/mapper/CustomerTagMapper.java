package com.ruoyi.customer.mapper;

import java.util.List;
import com.ruoyi.customer.domain.CustomerTag;
import com.ruoyi.customer.domain.vo.CustomerTagListVO;
import com.ruoyi.customer.domain.vo.SubgroupColumnListVO;
import org.apache.ibatis.annotations.Param;

/**
 * 客户标签关联Mapper接口
 * 
 * @author tangJM.
 * @date 2023-11-02
 */
public interface CustomerTagMapper 
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
     * 删除客户标签关联
     * 
     * @param id 客户标签关联主键
     * @return 结果
     */
    public int deleteCustomerTagById(Long id);

    /**
     * 批量删除客户标签关联
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCustomerTagByIds(Long[] ids);

    /**
     * 批量插入客户标签关联
     * @param customerTagList
     */
    void batchInsertCustomerTag(@Param("customerTagList") List<CustomerTag> customerTagList);

    /**
     * 根据客户id删除客户标签关联
     * @param customerId
     */
    void deleteCustomerTagByCustomerId(@Param("customerId") Long customerId);

    /**
     * 根据客户id查询客户标签关联
     * @param id
     * @return
     */
    List<CustomerTagListVO> selectCustomerTagByCustomerId(@Param("customerId") Long id);
}
