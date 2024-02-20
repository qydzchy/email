package com.ruoyi.customer.mapper;

import java.util.Date;
import java.util.List;

import com.ruoyi.customer.domain.Customer;
import com.ruoyi.customer.domain.bo.*;
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
     * @param ids
     * @param packetId
     */
    void moveCustomerToPacket(@Param("ids") List<Long> ids, @Param("packetId") Long packetId);

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
     */
    void batchUpdateCustomerSeaType(@Param("customerIdList") List<Long> customerIdList, @Param("seaType") Integer seaType);

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
    List<Long> findSubordinateDeptIds(@Param("deptId") Long deptId);

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

    void insertBaseCountry(@Param("type") String type, @Param("value") String value, @Param("label") String label);

    /**
     * 查询国家/地区
     * @return
     */
    List<SubgroupColumnListVO> selectCountryRegion();

    /**
     * 查询时区
     * @return
     */
    List<SubgroupColumnListVO> selectTimeZone();

    /**
     * 查询客户公司名称是否已存在
     * @param companyName
     * @return
     */
    Integer countByCompanyName(@Param("companyName") String companyName);

    /**
     * 查询客户公司名称是否已存在
     * @param id
     * @param companyName
     * @return
     */
    Integer countByIdAndCompanyName(@Param("id") Long id, @Param("companyName") String companyName);

    /**
     * 查询星级邮件数
     * @param userId
     * @return
     */
    List<EmailCountGroupByRatingBO> selectEmailCountGroupByRating(@Param("userId") Long userId);

    /**
     * 查询公海分组邮件数
     * @param userId
     * @return
     */
    List<EmailCountGroupByPublicleadsGroupsBO> selectEmailCountGroupByPublicleadsGroups(@Param("userId") Long userId);

    /**
     * 查询分组邮件数
     * @param userId
     * @return
     */
    List<EmailCountGroupByPacketBO> selectEmailCountGroupByPacket(@Param("userId") Long userId);

    /**
     * 查询来源邮件数
     * @param userId
     * @return
     */
    List<EmailCountGroupBySourceBO> selectEmailCountGroupBySource(@Param("userId") Long userId);

    /**
     * 查询客户邮件数
     * @param userId
     * @return
     */
    List<EmailCountGroupByCustomerBO> selectEmailCountGroupByCustomer(@Param("userId") Long userId);

    /**
     * 查询“最近7天有往来”客户的邮件数
     * @param userId
     * @return
     */
    List<EmailCountByRecentInteractions7DaysBO> selectRecentInteractions7Days(@Param("userId") Long userId);

    /**
     * 查询关注客户的邮件数
     * @param userId
     * @return
     */
    List<EmailCountGroupByFocusFlagBO> selectEmailCountGroupByFocusFlag(@Param("userId") Long userId);

    /**
     * 查询阶段邮件数
     * @param userId
     * @return
     */
    List<EmailCountGroupByStageBO> selectEmailCountGroupByStage(@Param("userId") Long userId);

    /**
     * 搜索列表
     * @param userId
     * @return
     */
    List<EmailCustomerVO> selectEmailCountGroupBySearch(@Param("userId") Long userId, @Param("keyword") String keyword);

    /**
     * 更新最后联系时间或最后跟进时间
     * @param customerIdList
     * @param lastContactedAt
     * @param lastFollowupAt
     */
    void updateLastContactedAtOrLastFollowupAt(@Param("customerIdList") List<Long> customerIdList, @Param("lastContactedAt") Date lastContactedAt, @Param("lastFollowupAt") Date lastFollowupAt);

    /**
     * 根据公司名称查询客户
     * @param companyName
     * @return
     */
    Customer getByCompanyName(@Param("companyName") String companyName);

    /**
     * 根据用户名称查询用户ID
     * @param nickName
     * @return
     */
    Long getUserIdByNickName(@Param("nickName") String nickName);

    /**
     * 根据客户编号查询客户
     * @param customerNo
     * @return
     */
    Customer getByCustomerNo(@Param("customerNo") String customerNo);
}
