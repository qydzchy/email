package com.ruoyi.customer.service;

import java.util.List;

import com.ruoyi.common.enums.customer.CustomerSeaLogTypeEnum;
import com.ruoyi.common.enums.customer.FollowUpRulesTypeEnum;
import com.ruoyi.customer.domain.Customer;
import com.ruoyi.customer.domain.bo.*;
import com.ruoyi.customer.domain.dto.*;
import com.ruoyi.customer.domain.vo.*;
import org.springframework.data.util.Pair;
import org.springframework.web.multipart.MultipartFile;

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
    public CustomerDetailVO getCustomerDetail(Long id);

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
     * @param pageNum
     * @param pageSize
     * @return
     */
    Pair<Integer, List<PrivateleadsCustomerSimpleListVO>> privateleadsList(Long segmentId, Integer type, Long teamMemberId, Integer pageNum, Integer pageSize);

    /**
     * 移入客户至分组
     * @param customerPacketMoveDTO
     * @return
     */
    boolean moveCustomerToPacket(CustomerPacketMoveDTO customerPacketMoveDTO);

    /**
     * 转移给
     * @param transferredToDTO
     * @return
     */
    boolean transferredTo(TransferredToDTO transferredToDTO);

    /**
     * 共享给
     * @param shareToDTO
     * @return
     */
    boolean shareTo(ShareToDTO shareToDTO);

    /**
     * 取消跟进
     * @param id
     * @return
     */
    boolean cancelFollowUp(Long id);

    /**
     * 移入公海
     * @param moveToPublicleadsDTO
     * @return
     */
    boolean moveToPublicleads(MoveToPublicleadsDTO moveToPublicleadsDTO);

    /**
     * 重新分配
     * @param reassignToDTO
     * @return
     */
    boolean reassignTo(ReassignToDTO reassignToDTO);

    /**
     * 取消跟进人
     * @param unassignFollowUpDTO
     * @return
     */
    boolean unassignFollowUp(UnassignFollowUpDTO unassignFollowUpDTO);

    /**
     * 查询客户跟进人
     * @param id
     * @return
     */
    List<CustomerFollowUpPersonnelListVO> followUpPersonnelList(Long id);

    /**
     * 修改重点客户
     * @param id
     * @return
     */
    boolean editFocusFlag(Long id);

    /**
     * 变更公海分组
     * @param id
     * @param publicleadsGroupsId
     * @return
     */
    boolean changePublicleadsGroups(Long id, Long publicleadsGroupsId);

    /**
     * 公海分组列表
     * @return
     */
    List<CustomerPublicleadsGroupListVO> publicleadsGroupsList();

    /**
     * 移入私海
     * @param ids
     * @return
     */
    boolean moveToPrivateleads(List<Long> ids, Long packetId);

    /**
     * 洗牌
     */
    boolean shuffle(Long customerId, Long segmentId);

    /**
     * 公海列表（分页）
     * @param pageNum
     * @param pageSize
     * @return
     */
    Pair<Integer, List<PublicleadsCustomerSimpleListVO>> publicleadsList(Long publicleadsGroupsId, Long packetId, Integer pageNum, Integer pageSize);

    /**
     * 客户跟进规则处理
     * @param customerIdList
     * @param followUpRulesTypeEnum
     */
    void customerFollowUpRulesHandler(List<Long> customerIdList, FollowUpRulesTypeEnum followUpRulesTypeEnum);

    /**
     * 移入公海或私海
     */
    void customerMoveToSeaHandler(List<Long> customerIdList, Integer seaType, Long createId, CustomerSeaLogTypeEnum customerSeaLogTypeEnum);

    /**
     * 客户查重筛选字段列表
     * @return
     */
    List<CustomerDuplicateFilterColumnListVO> customerDuplicateFilterColumnList();

    /**
     * 客户查重列表
     * @param columnName
     * @param searchText
     * @param pageNum
     * @param pageSize
     * @return
     */
    Pair<Integer, List<CustomerDuplicateListVO>> duplicateList(String columnName, String searchText, Integer pageNum, Integer pageSize);

    /**
     * 导入客户
     * @param file
     * @return
     */
    boolean importCustomer(Integer importType, Boolean updateFlag, MultipartFile file);

    /**
     * 移入公海规则处理
     */
    boolean movePublicleadsRulesHandler();

    /**
     * 获取团队成员
     * @return
     */
    List<TeamMembersListVO> getTeamMembers();

    /**
     * 查询所有用户
     * @return
     */
    List<TeamMembersListVO> getAllUsers();

    /**
     * 查询星级邮件数
     * @param userId
     * @return
     */
    List<EmailCountGroupByRatingBO> selectEmailCountGroupByRating(Long userId);

    /**
     * 查询公海分组邮件数
     * @param userId
     * @return
     */
    List<EmailCountGroupByPublicleadsGroupsBO> selectEmailCountGroupByPublicleadsGroups(Long userId);

    /**
     * 查询分组邮件数
     * @param userId
     * @return
     */
    List<EmailCountGroupByPacketBO> selectEmailCountGroupByPacket(Long userId);

    /**
     * 查询来源邮件数
     * @param userId
     * @return
     */
    List<EmailCountGroupBySourceBO> selectEmailCountGroupBySource(Long userId);

    /**
     * 查询客户邮件数
     * @param userId
     * @return
     */
    List<EmailCountGroupByCustomerBO> selectEmailCountGroupByCustomer(Long userId);

    /**
     * 查询“最近7天有往来”客户的邮件数
     * @param userId
     * @return
     */
    List<EmailCountByRecentInteractions7DaysBO> selectRecentInteractions7Days(Long userId);

    /**
     * 查询关注的邮件数
     * @param userId
     * @return
     */
    List<EmailCountGroupByFocusFlagBO> selectEmailCountGroupByFocusFlag(Long userId);

    /**
     * 阶段列表
     * @param userId
     * @return
     */
    List<EmailCountGroupByStageBO> selectEmailCountGroupByStage(Long userId);

    /**
     * 搜索列表
     * @param userId
     * @return
     */
    List<EmailCustomerVO> selectEmailCountGroupBySearch(Long userId, String keyword);
}
