package com.ruoyi.customer.mapper;

import java.util.List;
import com.ruoyi.customer.domain.Customer;
import com.ruoyi.customer.domain.bo.CustomerMovePublicleadsRulesInfoBO;
import com.ruoyi.customer.domain.bo.RoleDeptSimpleInfoVO;
import com.ruoyi.customer.domain.vo.*;
import org.apache.ibatis.annotations.Param;

/**
 * 客户详情Mapper接口
 * 
 * @author tangJM.
 * @date 2023-11-02
 */
public interface CustomerMapper 
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
     * @param customer 客户详情
     * @return 结果
     */
    public int insertCustomer(Customer customer);

    /**
     * 修改客户详情
     * 
     * @param customer 客户详情
     * @return 结果
     */
    public int updateCustomer(Customer customer);

    /**
     * 删除客户详情
     * 
     * @param id 客户详情主键
     * @return 结果
     */
    public int deleteCustomerById(Long id);

    /**
     * 批量删除客户详情
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCustomerByIds(Long[] ids);

    /**
     * 统计客户数量
     * @param segmentId
     * @return
     */
    int countPrivateleadsCustomer(@Param("userIdList") List<Long> userIdList, @Param("segmentId") Long segmentId);

    /**
     * 移动客户到分组
     * @param id
     * @param packetId
     */
    void moveCustomerToPacket(@Param("id") Long id, @Param("packetId") Long packetId);

    /**
     * 修改重点关注
     * @param id
     * @param updateId
     * @param updateBy
     */
    void updateFocusFlag(@Param("id") Long id, @Param("updateId") Long updateId, @Param("updateBy") String updateBy);

    /**
     * 变更公海分组
     * @param id
     * @param publicleadsGroupsId
     * @param updateId
     * @param updateBy
     */
    void changePublicleadsGroups(@Param("id") Long id, @Param("publicleadsGroupsId") Long publicleadsGroupsId, @Param("updateId") Long updateId, @Param("updateBy") String updateBy);

    /**
     * 查询公海分组列表
     * @param userId
     * @return
     */
    List<CustomerPublicleadsGroupListVO> publicleadsGroupsList(@Param("userId") Long userId);

    /**
     * 将客户移入私海
     * @param id
     */
    void moveToPrivateleads(@Param("id") Long id, @Param("updateId") Long updateId, @Param("updateBy") String updateBy);

    /**
     * 获取私海客户列表
     * @param segmentId
     * @param offset
     * @param limit
     * @return
     */
    List<PrivateleadsCustomerSimpleListVO> selectPrivateleadsCustomerPage(@Param("userIdList") List<Long> userIdList, @Param("segmentId") Long segmentId, @Param("offset") int offset, @Param("limit") int limit);

    /**
     * 统计公海客户数量
     * @param publicleadsGroupsId
     * @param packetId
     * @return
     */
    int countPublicleadsCustomer(@Param("publicleadsGroupsId") Long publicleadsGroupsId, @Param("packetId") Long packetId);

    /**
     * 获取公海客户列表
     * @param publicleadsGroupsId
     * @param packetId
     * @param offset
     * @param limit
     * @return
     */
    List<PublicleadsCustomerSimpleListVO> selectPublicleadsCustomerPage(@Param("publicleadsGroupsId") Long publicleadsGroupsId, @Param("packetId") Long packetId, @Param("offset") int offset, @Param("limit") int limit);

    /**
     * 查询客户ID列表
     * @return
     */
    List<Long> selectCustomerIdList();

    /**
     * 根据用户ID查询部门ID
     * @param userId
     * @return
     */
    Long getDeptIdByUserId(@Param("userId") Long userId);

    /**
     * 移至客户到公海
     * @param id
     * @param seaType
     */
    void updateCustomerSeaType(@Param("id") Long id, @Param("seaType") Integer seaType, @Param("updateId") Long updateId, @Param("updateBy") String updateBy);

    /**
     * 查询私海客户数量
     * @param userId
     * @return
     */
    Integer selectPrivateleadsNumByUserId(@Param("userId") Long userId);

    /**
     * 统计
     * @param columnName
     * @param searchText
     * @return
     */
    int countCustomerDuplicate(@Param("columnName") String columnName, @Param("searchText") String searchText);

    /**
     *
     * @param columnName
     * @param searchText
     * @return
     */
    List<CustomerDuplicateListVO> customerDuplicateList(@Param("columnName") String columnName, @Param("searchText") String searchText, @Param("offset") int offset, @Param("limit") int limit);

    /**
     * 根据客户ID查询客户移入公海规则信息
     * @param segmentId
     * @return
     */
    List<CustomerMovePublicleadsRulesInfoBO> selectCustomerMovePublicleadsRulesInfoBySegmentId(Long segmentId);

    /**
     * 批量更新客户的公海/私海类型
     * @param customerIdList
     * @param type
     */
    void batchUpdateCustomerSeaType(@Param("customerIdList") List<Long> customerIdList, @Param("type") Integer type);

    /**
     * 查询角色部门简单信息
     * @param userId
     * @return
     */
    List<RoleDeptSimpleInfoVO> queryRoleDeptSimpleInfo(@Param("userId") Long userId);

    /**
     * 获取所有用户
     * @return
     */
    List<TeamMembersListVO> getAllUser();

    /**
     * 根据部门查询用户
     * @param deptIdList
     * @return
     */
    List<TeamMembersListVO> getUserByDeptIds(@Param("deptIdList") List<Long> deptIdList);

    /**
     * 查询部门下所有子部门
     * @param deptId
     * @return
     */
    List<Long> findSubordinateDeptIds(Long deptId);

    /**
     * 获取最大的客户编号
     * @return
     */
    Long getMaxCustomerNo();

    /**
     * 根据客户编号查询客户数量
     * @param customerNo
     * @return
     */
    Integer countByCustomerNo(@Param("customerNo") String customerNo);
}
