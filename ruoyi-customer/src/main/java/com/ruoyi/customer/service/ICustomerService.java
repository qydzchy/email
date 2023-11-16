package com.ruoyi.customer.service;

import java.util.List;

import com.ruoyi.common.enums.customer.CustomerSeaLogTypeEnum;
import com.ruoyi.common.enums.customer.FollowUpRulesTypeEnum;
import com.ruoyi.customer.domain.Customer;
import com.ruoyi.customer.domain.dto.*;
import com.ruoyi.customer.domain.vo.*;
import org.springframework.data.util.Pair;

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
    Pair<Integer, List<PrivateleadsCustomerSimpleListVO>> privateleadsList(Long segmentId, Integer pageNum, Integer pageSize);

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
     * @param customerId
     * @return
     */
    List<CustomerPublicleadsGroupListVO> publicleadsGroupsList(Long customerId);

    /**
     * 移入私海
     * @param id
     * @return
     */
    boolean moveToPrivateleads(Long id);

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
     * @param customerId
     * @param followUpRulesTypeEnum
     */
    void customerFollowUpRulesHandler(Long customerId, FollowUpRulesTypeEnum followUpRulesTypeEnum);

    /**
     * 移入公海或私海
     */
    void customerMoveToSeaHandler(Long customerId, Integer seaType, Long createId, CustomerSeaLogTypeEnum customerSeaLogTypeEnum);

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
}
