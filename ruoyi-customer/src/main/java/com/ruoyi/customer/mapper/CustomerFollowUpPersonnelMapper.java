package com.ruoyi.customer.mapper;

import java.util.List;
import com.ruoyi.customer.domain.CustomerFollowUpPersonnel;
import com.ruoyi.customer.domain.vo.CustomerFollowUpPersonnelListVO;
import com.ruoyi.customer.domain.vo.SubgroupColumnListVO;
import org.apache.ibatis.annotations.Param;

/**
 * 客户跟进人Mapper接口
 * 
 * @author tangJM.
 * @date 2023-11-02
 */
public interface CustomerFollowUpPersonnelMapper 
{
    /**
     * 查询客户跟进人
     * 
     * @param id 客户跟进人主键
     * @return 客户跟进人
     */
    public CustomerFollowUpPersonnel selectCustomerFollowUpPersonnelById(Long id);

    /**
     * 查询客户跟进人列表
     * 
     * @param customerFollowUpPersonnel 客户跟进人
     * @return 客户跟进人集合
     */
    public List<CustomerFollowUpPersonnel> selectCustomerFollowUpPersonnelList(CustomerFollowUpPersonnel customerFollowUpPersonnel);

    /**
     * 新增客户跟进人
     * 
     * @param customerFollowUpPersonnel 客户跟进人
     * @return 结果
     */
    public int insertCustomerFollowUpPersonnel(CustomerFollowUpPersonnel customerFollowUpPersonnel);

    /**
     * 修改客户跟进人
     * 
     * @param customerFollowUpPersonnel 客户跟进人
     * @return 结果
     */
    public int updateCustomerFollowUpPersonnel(CustomerFollowUpPersonnel customerFollowUpPersonnel);

    /**
     * 删除客户跟进人
     * 
     * @param id 客户跟进人主键
     * @return 结果
     */
    public int deleteCustomerFollowUpPersonnelById(Long id);

    /**
     * 批量删除客户跟进人
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCustomerFollowUpPersonnelByIds(Long[] ids);

    /**
     * 转移给
     * @param customerId 客户ID
     * @param currentFollowerId 当前跟进人ID
     * @param transferredToFollowerId 转移给跟进人ID
     */
    void transferredTo(@Param("customerId") Long customerId, @Param("currentFollowerId") Long currentFollowerId, @Param("transferredToFollowerId") Long transferredToFollowerId);

    /**
     * 根据客户ID删除
     * @param customerId
     */
    void deleteCustomerFollowUpPersonnelByCustomerId(@Param("customerId") Long customerId, @Param("updateId") Long updateId, @Param("updateBy") String updateBy);

    /**
     * 查询客户跟进人列表
     * @param customerId
     * @return
     */
    List<CustomerFollowUpPersonnelListVO> selectCustomerFollowUpPersonnelByCustomerId(@Param("customerId") Long customerId);

    /**
     * 批量新增跟进人
     * @param customerFollowUpPersonnelList
     */
    void batchInsertCustomerFollowUpPersonnel(@Param("customerFollowUpPersonnelList") List<CustomerFollowUpPersonnel> customerFollowUpPersonnelList);

    /**
     * 删除客户跟进人
     * @param customerId
     * @param currentFollowerId
     * @param updateId
     * @param updateBy
     */
    void deleteCustomerFollowUpPersonnelByCustomerIdAndUserId(@Param("customerId") Long customerId, @Param("currentFollowerId") Long currentFollowerId, @Param("updateId") Long updateId, @Param("updateBy") String updateBy);

    /**
     * 查询客户跟进人列表
     * @return
     */
    List<SubgroupColumnListVO> selectCustomerFollowUpPersonnelSimpleInfo();
}
