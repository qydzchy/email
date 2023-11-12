package com.ruoyi.customer.service.impl;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.customer.CustomerSeaTypeEnum;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.customer.domain.*;
import com.ruoyi.customer.domain.bo.*;
import com.ruoyi.customer.domain.dto.*;
import com.ruoyi.customer.domain.vo.*;
import com.ruoyi.customer.mapper.*;
import com.ruoyi.customer.service.ICustomerFollowUpRecordsService;
import com.ruoyi.customer.service.IPublicleadsGroupsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.service.ICustomerService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 客户详情Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-11-02
 */
@Slf4j
@Service
public class CustomerServiceImpl implements ICustomerService 
{
    @Resource
    private CustomerMapper customerMapper;
    @Resource
    private CustomerContactMapper customerContactMapper;
    @Resource
    private CustomerSourceMapper customerSourceMapper;
    @Resource
    private CustomerTagMapper customerTagMapper;
    @Resource
    private CustomerFollowUpPersonnelMapper customerFollowUpPersonnelMapper;
    @Resource
    private CustomerPublicleadsMapper customerPublicleadsMapper;
    @Resource
    private SegmentMapper segmentMapper;
    @Resource
    private CustomerFollowUpRecordsMapper customerFollowUpRecordsMapper;
    @Resource
    private StageMapper stageMapper;
    @Resource
    private PacketMapper packetMapper;
    @Resource
    private ICustomerFollowUpRecordsService customerFollowUpRecordsService;
    @Resource
    private IPublicleadsGroupsService publicleadsGroupsService;

    private static final Executor executor = Executors.newFixedThreadPool(3);

    /**
     * 查询客户详情
     * 
     * @param id 客户详情主键
     * @return 客户详情
     */
    public CustomerDetailVO getCustomerDetail(Long id) {
        CompletableFuture<Customer> customerFuture = CompletableFuture.supplyAsync(() ->
                customerMapper.selectCustomerById(id), executor
        );

        CompletableFuture<List<CustomerContactBO>> contactListFuture = CompletableFuture.supplyAsync(() ->
                customerContactMapper.selectCustomerContactByCustomerId(id), executor
        );

        CompletableFuture<List<CustomerFollowUpPersonnelListVO>> followUpPersonnelListFuture = CompletableFuture.supplyAsync(() ->
                customerFollowUpPersonnelMapper.selectCustomerFollowUpPersonnelByCustomerId(id), executor
        );

        CompletableFuture<List<CustomerFollowUpRecordsListVO>> followUpRecordsListFuture = CompletableFuture.supplyAsync(() -> {
            CustomerFollowUpRecordsListDTO customerFollowUpRecordsListDTO = new CustomerFollowUpRecordsListDTO();
            customerFollowUpRecordsListDTO.setCustomerId(id);
            return customerFollowUpRecordsService.list(customerFollowUpRecordsListDTO);
        }, executor);

        CompletableFuture<List<CustomerTagListVO>> tagListFuture = CompletableFuture.supplyAsync(() ->
                customerTagMapper.selectCustomerTagByCustomerId(id), executor
        );

        CompletableFuture<List<SourceListVO>> sourceListFuture = CompletableFuture.supplyAsync(() ->
                customerSourceMapper.selectCustomerSourceByCustomerId(id), executor
        );

        CompletableFuture<StageListVO> stageFuture = CompletableFuture.supplyAsync(() ->
                stageMapper.selectStageByCustomerId(id), executor
        );

        CompletableFuture<SimplePacketVO> packetFuture = CompletableFuture.supplyAsync(() ->
                packetMapper.selectSimplePacketByCustomerId(id), executor
        );

        CompletableFuture<PublicleadsGroupsListVO> publicleadsGroupsFuture = CompletableFuture.supplyAsync(() ->
                publicleadsGroupsService.selectPublicleadsGroupsByCustomerId(id), executor
        );

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(customerFuture, contactListFuture, followUpPersonnelListFuture,
                followUpRecordsListFuture, tagListFuture, sourceListFuture, stageFuture, packetFuture, publicleadsGroupsFuture);

        return allFutures.thenApplyAsync(ignored ->
                        buildCustomerDetailVO(customerFuture.join(), contactListFuture.join(), followUpPersonnelListFuture.join(),
                                followUpRecordsListFuture.join(), tagListFuture.join(), sourceListFuture.join(),
                                stageFuture.join(), packetFuture.join(), publicleadsGroupsFuture.join()), executor)
                .join();
    }

    private CustomerDetailVO buildCustomerDetailVO(Customer customer, List<CustomerContactBO> contactList,
                                                   List<CustomerFollowUpPersonnelListVO> followUpPersonnelList,
                                                   List<CustomerFollowUpRecordsListVO> followUpRecordsList,
                                                   List<CustomerTagListVO> tagList, List<SourceListVO> sourceList,
                                                   StageListVO stage, SimplePacketVO packet,
                                                   PublicleadsGroupsListVO publicleadsGroupsListVO) {
        CustomerDetailVO customerDetailVO = new CustomerDetailVO();
        BeanUtils.copyProperties(customer, customerDetailVO);

        customerDetailVO.setContactList(contactList);
        customerDetailVO.setFollowUpPersonnelList(followUpPersonnelList);
        customerDetailVO.setFollowUpRecordsList(followUpRecordsList);
        customerDetailVO.setTagList(tagList);
        customerDetailVO.setSourceList(sourceList);
        customerDetailVO.setStage(stage);
        customerDetailVO.setPacket(packet);
        customerDetailVO.setPublicleadsGroups(publicleadsGroupsListVO);

        return customerDetailVO;
    }

    /**
     * 查询客户详情列表
     * 
     * @param customer 客户详情
     * @return 客户详情
     */
    @Override
    public List<Customer> selectCustomerList(Customer customer)
    {
        return customerMapper.selectCustomerList(customer);
    }

    /**
     * 新增客户详情
     * 
     * @param customerAddOrUpdateDTO 客户详情
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insertCustomer(CustomerAddOrUpdateDTO customerAddOrUpdateDTO)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        Customer customer = new Customer();
        BeanUtils.copyProperties(customerAddOrUpdateDTO, customer);

        String customerNo = System.currentTimeMillis() + "";

        customer.setCustomerNo(customerNo);
        customer.setLastContactedAt(new Date());
        customer.setCreateId(userId);
        customer.setCreateBy(username);
        customer.setCreateTime(DateUtils.getNowDate());
        customer.setUpdateId(userId);
        customer.setUpdateBy(username);
        customer.setUpdateTime(DateUtils.getNowDate());
        customerMapper.insertCustomer(customer);
        Long id = customer.getId();

        // 判断新增的客户是公海还是私海类型，如果是私海，需要将该客户关联到当下用户作为跟进人
        if (customerAddOrUpdateDTO.getSeaType().intValue() == CustomerSeaTypeEnum.PRIVATE_LEADS.getType()) {
            // 新增客户跟进人
            insertCustomerFollowUpPersonnel(id, userId, username);
        }

        // 新增客户来源的关联关系
        batchInsertCustomerSource(id, customerAddOrUpdateDTO.getSourceIds());
        // 新增客户标签的关联关系
        batchInsertCustomerTag(id, customerAddOrUpdateDTO.getTagIds());

        // 批量新增客户联系人
        batchInsertCustomerContact(customerAddOrUpdateDTO.getContactList(), userId, username, id);
        return true;
    }

    /**
     * 新增客户跟进人
     * @param id
     * @param userId
     */
    private void insertCustomerFollowUpPersonnel(Long id, Long userId, String username) {
        CustomerFollowUpPersonnel customerFollowUpPersonnel = new CustomerFollowUpPersonnel();
        customerFollowUpPersonnel.setCustomerId(id);
        customerFollowUpPersonnel.setUserId(userId);
        customerFollowUpPersonnel.setCreateId(userId);
        customerFollowUpPersonnel.setCreateBy(username);
        customerFollowUpPersonnel.setCreateTime(DateUtils.getNowDate());
        customerFollowUpPersonnel.setUpdateId(userId);
        customerFollowUpPersonnel.setUpdateBy(username);
        customerFollowUpPersonnel.setUpdateTime(DateUtils.getNowDate());

        customerFollowUpPersonnelMapper.insertCustomerFollowUpPersonnel(customerFollowUpPersonnel);
    }

    /**
     * 批量新增客户标签
     * @param id
     * @param tagIds
     */
    private void batchInsertCustomerTag(Long id, List<Long> tagIds) {
        if (tagIds == null || tagIds.isEmpty()) return;

        List<CustomerTag> customerTagList = new ArrayList<>();
        for (Long tagId : tagIds) {
            CustomerTag customerTag = new CustomerTag();
            customerTag.setCustomerId(id);
            customerTag.setTagId(tagId);
            customerTagList.add(customerTag);
        }

        if (customerTagList == null || customerTagList.isEmpty()) return;

        // 批量新增客户标签
        customerTagMapper.batchInsertCustomerTag(customerTagList);
    }

    /**
     * 批量新增客户来源
     * @param id
     * @param sourceIds
     */
    private void batchInsertCustomerSource(Long id, List<Long> sourceIds) {
        if (sourceIds == null || sourceIds.isEmpty()) return;

        List<CustomerSource> customerSourceList = new ArrayList<>();
        for (Long sourceId : sourceIds) {
            CustomerSource customerSource = new CustomerSource();
            customerSource.setCustomerId(id);
            customerSource.setSourceId(sourceId);
            customerSourceList.add(customerSource);
        }

        // 批量新增客户来源
        customerSourceMapper.batchInsertCustomerSource(customerSourceList);
    }

    /**
     * 批量新增客户联系人
     * @param contactDtoList
     * @param userId
     * @param username
     * @param id
     */
    private void batchInsertCustomerContact(List<CustomerContactAddOrUpdateDTO> contactDtoList, Long userId, String username, Long id) {
        if (contactDtoList == null || contactDtoList.isEmpty()) return;

        List<CustomerContact> customerContactList = new ArrayList<>();
        for (CustomerContactAddOrUpdateDTO customerContactAddOrUpdateDTO : contactDtoList) {
            CustomerContact customerContact = new CustomerContact();
            BeanUtils.copyProperties(customerContactAddOrUpdateDTO, customerContact);
            customerContact.setCustomerId(id);
            customerContact.setDelFlag("0");
            customerContact.setCustomerId(id);
            customerContact.setCreateId(userId);
            customerContact.setCreateBy(username);
            customerContact.setCreateTime(DateUtils.getNowDate());
            customerContact.setUpdateId(userId);
            customerContact.setUpdateBy(username);
            customerContact.setUpdateTime(DateUtils.getNowDate());
            customerContactList.add(customerContact);
        }

        // 批量新增客户联系人
        customerContactMapper.batchInsertCustomerContact(customerContactList);
    }

    /**
     * 修改客户详情
     * 
     * @param customerAddOrUpdateDTO 客户详情
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateCustomer(CustomerAddOrUpdateDTO customerAddOrUpdateDTO)
    {
        Long id = customerAddOrUpdateDTO.getId();
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        Customer customer = new Customer();
        BeanUtils.copyProperties(customerAddOrUpdateDTO, customer);

        customer.setUpdateId(userId);
        customer.setUpdateBy(username);
        customer.setUpdateTime(DateUtils.getNowDate());
        customerMapper.updateCustomer(customer);

        // 新增客户来源的关联关系
        if (customerAddOrUpdateDTO.getSourceIds() != null && !customerAddOrUpdateDTO.getSourceIds().isEmpty()) {
            // 删除客户来源的关联关系
            customerSourceMapper.deleteCustomerSourceByCustomerId(id);
            // 批量新增客户来源
            batchInsertCustomerSource(id, customerAddOrUpdateDTO.getSourceIds());
        }

        if (customerAddOrUpdateDTO.getTagIds() != null && !customerAddOrUpdateDTO.getTagIds().isEmpty()) {
            // 删除客户标签的关联关系
            customerTagMapper.deleteCustomerTagByCustomerId(id);
            // 批量新增客户标签
            batchInsertCustomerTag(id, customerAddOrUpdateDTO.getTagIds());
        }

        List<CustomerContactAddOrUpdateDTO> contactList = customerAddOrUpdateDTO.getContactList();
        if (contactList != null && !contactList.isEmpty()) {
            List<CustomerContactAddOrUpdateDTO> saveContactList = new ArrayList<>();
            for (CustomerContactAddOrUpdateDTO contact : contactList) {
                if (contact.getId() == null) {
                    saveContactList.add(contact);
                } else {
                    CustomerContact customerContact = new CustomerContact();
                    BeanUtils.copyProperties(contact, customerContact);
                    customerContact.setUpdateId(userId);
                    customerContact.setUpdateBy(username);
                    customerContact.setUpdateTime(DateUtils.getNowDate());
                    customerContactMapper.updateCustomerContact(customerContact);
                }
            }
            // 批量新增客户跟进人
            batchInsertCustomerContact(customerAddOrUpdateDTO.getContactList(), userId, username, id);
        }

        return true;
    }

    /**
     * 批量删除客户详情
     * 
     * @param ids 需要删除的客户详情主键
     * @return 结果
     */
    @Override
    public int deleteCustomerByIds(Long[] ids)
    {
        return customerMapper.deleteCustomerByIds(ids);
    }

    /**
     * 删除客户详情信息
     * 
     * @param id 客户详情主键
     * @return 结果
     */
    @Override
    public int deleteCustomerById(Long id)
    {
        return customerMapper.deleteCustomerById(id);
    }

    @Override
    public Pair<Integer, List<PrivateleadsCustomerSimpleListVO>> privateleadsList(Long segmentId, Integer pageNum, Integer pageSize) {
        try {
            int count = customerMapper.countPrivateleadsCustomer(segmentId);
            if (count <= 0) {
                return Pair.of(count, new ArrayList<>());
            }

            int offset = (pageNum - 1) * pageSize;
            int limit = pageSize;
            List<PrivateleadsCustomerSimpleListVO> customerSimpleVOList = customerMapper.selectPrivateleadsCustomerPage(segmentId, offset, limit);
            if (customerSimpleVOList == null || customerSimpleVOList.isEmpty()) {
                return Pair.of(count, new ArrayList<>());
            }

            // 获取查询的客户id
            List<Long> ids = customerSimpleVOList.stream().map(PrivateleadsCustomerSimpleListVO::getId).collect(Collectors.toList());
            // 查询最近动态
            List<CustomerRecentActivityBO> customerRecentActivityList = customerFollowUpRecordsMapper.selectRecentActivityByCustomerIds(ids);
            Map<Long, CustomerRecentActivityBO> customerIdMap = customerRecentActivityList.stream().collect(Collectors.toMap(customerRecentActivity -> customerRecentActivity.getCustomerId(), customerRecentActivity -> customerRecentActivity));

            customerSimpleVOList.stream().forEach(customerSimpleVO -> {
                if (customerIdMap.containsKey(customerSimpleVO.getId())) {
                    customerSimpleVO.setRecentActivity(customerIdMap.get(customerSimpleVO.getId()));
                }
            });

            return Pair.of(count, customerSimpleVOList);
        } catch (Exception e) {
            log.error("查询客户列表（分页）异常：{}", e);
            return Pair.of(0, new ArrayList<>());
        }
    }

    @Override
    public boolean moveCustomerToPacket(CustomerPacketMoveDTO customerPacketMoveDTO) {
        customerMapper.moveCustomerToPacket(customerPacketMoveDTO.getId(), customerPacketMoveDTO.getPacketId());
        return true;
    }

    /**
     * 转移给
     * @param transferredToDTO
     * @return
     */
    @Override
    public boolean transferredTo(TransferredToDTO transferredToDTO) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        // 查询转移的跟进人是否已经存在该客户了，如若存在，直接删除当前的跟进人，不存在则更新跟进人
        List<CustomerFollowUpPersonnelListVO> customerFollowUpPersonnelVOList = customerFollowUpPersonnelMapper.selectCustomerFollowUpPersonnelByCustomerId(transferredToDTO.getId());
        List<Long> dbUserIds = customerFollowUpPersonnelVOList.stream().map(CustomerFollowUpPersonnelListVO::getUserId).collect(Collectors.toList());
        if (!dbUserIds.contains(userId)) {
            throw new ServiceException("您不是该客户的跟进人，无法转移");
        }

        Long transferredToFollowerId = transferredToDTO.getUserId();
        if (dbUserIds.contains(transferredToFollowerId)) {
            // 删除当前的跟进人
            customerFollowUpPersonnelMapper.deleteCustomerFollowUpPersonnelByCustomerIdAndUserId(transferredToDTO.getId(), transferredToFollowerId, userId, username);

        } else {
            customerFollowUpPersonnelMapper.transferredTo(transferredToDTO.getId(), userId, transferredToDTO.getUserId());
        }

        return true;
    }

    /**
     * 共享给
     * @param shareToDTO
     * @return
     */
    @Override
    public boolean shareTo(ShareToDTO shareToDTO) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();

        // 查询客户跟进人是否包含当前用户，没有则不给分享，防止恶意使用该功能
        List<CustomerFollowUpPersonnelListVO> customerFollowUpPersonnelsVOList = customerFollowUpPersonnelMapper.selectCustomerFollowUpPersonnelByCustomerId(shareToDTO.getId());
        List<Long> dbUserIds = customerFollowUpPersonnelsVOList.stream().map(CustomerFollowUpPersonnelListVO::getUserId).collect(Collectors.toList());

        if (!dbUserIds.contains(userId)) {
            throw new ServiceException("您不是该客户的跟进人，无法分享");
        }

        // 删掉已经存在的跟进人
        List<Long> userIds = shareToDTO.getUserIds();
        Iterator<Long> iterator = userIds.iterator();
        while (iterator.hasNext()) {
            Long next = iterator.next();
            if (dbUserIds.contains(next)) {
                iterator.remove();
            }
        }

        if (!userIds.isEmpty()) {
            // 批量新增跟进人
            List<CustomerFollowUpPersonnel> customerFollowUpPersonnelList = new ArrayList<>();
            for (Long userId1 : userIds) {
                CustomerFollowUpPersonnel customerFollowUpPersonnel = new CustomerFollowUpPersonnel();
                customerFollowUpPersonnel.setCustomerId(shareToDTO.getId());
                customerFollowUpPersonnel.setUserId(userId1);
                customerFollowUpPersonnel.setDelFlag("0");
                customerFollowUpPersonnel.setCreateId(userId);
                customerFollowUpPersonnel.setCreateBy(loginUser.getUsername());
                customerFollowUpPersonnel.setCreateTime(DateUtils.getNowDate());
                customerFollowUpPersonnel.setUpdateId(userId);
                customerFollowUpPersonnel.setUpdateBy(loginUser.getUsername());
                customerFollowUpPersonnel.setUpdateTime(DateUtils.getNowDate());
                customerFollowUpPersonnelList.add(customerFollowUpPersonnel);
            }
            customerFollowUpPersonnelMapper.batchInsertCustomerFollowUpPersonnel(customerFollowUpPersonnelList);
        }

        return true;
    }

    /**
     * 取消跟进
     * @param id
     * @return
     */
    @Override
    public boolean cancelFollowUp(Long id) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        customerFollowUpPersonnelMapper.deleteCustomerFollowUpPersonnelByCustomerIdAndUserId(id, userId, userId, username);
        return true;
    }

    /**
     * 移入公海
     * @param moveToPublicleadsDTO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean moveToPublicleads(MoveToPublicleadsDTO moveToPublicleadsDTO) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        CustomerPublicleads customerPublicleads = new CustomerPublicleads();
        customerPublicleads.setCustomerId(moveToPublicleadsDTO.getId());
        customerPublicleads.setPublicleadsGroupsId(moveToPublicleadsDTO.getPublicleadsGroupsId());
        customerPublicleads.setPublicleadsReasonId(moveToPublicleadsDTO.getPublicleadsReasonId());
        customerPublicleads.setCreateId(userId);
        customerPublicleads.setCreateBy(username);
        customerPublicleads.setCreateTime(DateUtils.getNowDate());
        customerPublicleads.setUpdateId(userId);
        customerPublicleads.setUpdateBy(username);
        customerPublicleads.setUpdateTime(DateUtils.getNowDate());
        customerPublicleadsMapper.insertCustomerPublicleads(customerPublicleads);

        Customer customer = customerMapper.selectCustomerById(moveToPublicleadsDTO.getId());
        customer.setPublicleadsGroupsId(moveToPublicleadsDTO.getPublicleadsGroupsId());
        customer.setSeaType(CustomerSeaTypeEnum.PUBLIC_LEADS.getType());
        customerMapper.updateCustomer(customer);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean reassignTo(ReassignToDTO reassignToDTO) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        // 删除客户的跟进人
        customerFollowUpPersonnelMapper.deleteCustomerFollowUpPersonnelByCustomerId(reassignToDTO.getId(), userId, username);

        // 批量新增跟进人
        List<CustomerFollowUpPersonnel> customerFollowUpPersonnelList = new ArrayList<>();
        for (Long userId1 : reassignToDTO.getUserIds()) {
            CustomerFollowUpPersonnel customerFollowUpPersonnel = new CustomerFollowUpPersonnel();
            customerFollowUpPersonnel.setCustomerId(reassignToDTO.getId());
            customerFollowUpPersonnel.setUserId(userId1);
            customerFollowUpPersonnel.setDelFlag("0");
            customerFollowUpPersonnel.setCreateId(userId);
            customerFollowUpPersonnel.setCreateBy(loginUser.getUsername());
            customerFollowUpPersonnel.setCreateTime(DateUtils.getNowDate());
            customerFollowUpPersonnel.setUpdateId(userId);
            customerFollowUpPersonnel.setUpdateBy(loginUser.getUsername());
            customerFollowUpPersonnel.setUpdateTime(DateUtils.getNowDate());
            customerFollowUpPersonnelList.add(customerFollowUpPersonnel);
        }
        customerFollowUpPersonnelMapper.batchInsertCustomerFollowUpPersonnel(customerFollowUpPersonnelList);
        return true;
    }

    @Override
    public boolean unassignFollowUp(UnassignFollowUpDTO unassignFollowUpDTO) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        // 删除客户的跟进人
        customerFollowUpPersonnelMapper.deleteCustomerFollowUpPersonnelByCustomerIdAndUserId(unassignFollowUpDTO.getId(), unassignFollowUpDTO.getUserId(), userId, username);
        return true;
    }

    /**
     * 查询客户跟进人列表
     * @param id
     * @return
     */
    @Override
    public List<CustomerFollowUpPersonnelListVO> followUpPersonnelList(Long id) {
        return customerFollowUpPersonnelMapper.selectCustomerFollowUpPersonnelByCustomerId(id);
    }

    /**
     * 修改重点客户
     * @param id
     * @return
     */
    @Override
    public boolean editFocusFlag(Long id) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        List<CustomerFollowUpPersonnelListVO> customerFollowUpPersonnelVOList = customerFollowUpPersonnelMapper.selectCustomerFollowUpPersonnelByCustomerId(id);
        List<Long> dbUserIds = customerFollowUpPersonnelVOList.stream().map(CustomerFollowUpPersonnelListVO::getUserId).collect(Collectors.toList());
        if (!dbUserIds.contains(userId)) {
            throw new ServiceException("您不是该客户的跟进人，无法修改重点客户");
        }

        customerMapper.updateFocusFlag(id, userId, username);
        return true;
    }

    /**
     * 查询公海分组列表
     */


    /**
     * 变更公海分组
     * @param id
     * @param publicleadsGroupsId
     * @return
     */
    @Override
    public boolean changePublicleadsGroups(Long id, Long publicleadsGroupsId) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();
        // 变更公海分组
        customerMapper.changePublicleadsGroups(id, publicleadsGroupsId, userId, username);
        return true;
    }

    @Override
    public List<CustomerPublicleadsGroupListVO> publicleadsGroupsList(Long customerId) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        return customerMapper.publicleadsGroupsList(customerId, userId);
    }

    /**
     * 移入私海
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean moveToPrivateleads(Long id) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        customerMapper.moveToPrivateleads(id, userId, username);

        // 删除跟进人
        customerFollowUpPersonnelMapper.deleteCustomerFollowUpPersonnelByCustomerId(id, userId, username);

        // 新增跟进人
        CustomerFollowUpPersonnel customerFollowUpPersonnel = new CustomerFollowUpPersonnel();
        customerFollowUpPersonnel.setCustomerId(id);
        customerFollowUpPersonnel.setUserId(userId);
        customerFollowUpPersonnel.setCreateId(userId);
        customerFollowUpPersonnel.setCreateBy(username);
        customerFollowUpPersonnel.setCreateTime(DateUtils.getNowDate());
        customerFollowUpPersonnel.setUpdateId(userId);
        customerFollowUpPersonnel.setUpdateBy(username);
        customerFollowUpPersonnel.setUpdateTime(DateUtils.getNowDate());
        customerFollowUpPersonnelMapper.insertCustomerFollowUpPersonnel(customerFollowUpPersonnel);

        return true;
    }

    @Override
    public boolean shuffle(Long customerIdParam, Long segmentIdParam) {
        // 获取客户列表
        List<Long> customerIdList = getCustomerIdList(customerIdParam);
        if (customerIdList == null || customerIdList.isEmpty()) return false;

        // 获取客群列表（第一级）
        List<Segment> segmentList = getSegmentList(segmentIdParam);
        if (segmentList == null || segmentList.isEmpty()) return false;

        customerIdList.stream().forEach(customerId -> {
            // 获取客户详情
            CustomerDetailVO customerDetail = getCustomerDetail(customerId);
            // 获取客户跟进人
            List<CustomerFollowUpPersonnelListVO> customerFollowUpPersonnelVOList = customerFollowUpPersonnelMapper.selectCustomerFollowUpPersonnelByCustomerId(customerId);
            for (Segment segment : segmentList) {
                // 可见范围是否成立，不成立跳过客群
                if (!isVisibleConditionMet(customerFollowUpPersonnelVOList, segment)) continue;
                //
                String conditionRuleContent = segment.getConditionRuleContent();
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    List<SegmentConditionRuleBO> segmentConditionRuleBOList = objectMapper.readValue(conditionRuleContent, List.class);

                } catch (JsonProcessingException e) {
                    log.error("客群条件规则转换异常：{}", e);
                    continue;
                }
            }
        });

        return false;
    }

    @Override
    public Pair<Integer, List<PublicleadsCustomerSimpleListVO>> publicleadsList(Long publicleadsGroupsId, Long packetId, Integer pageNum, Integer pageSize) {
        try {
            int count = customerMapper.countPublicleadsCustomer(publicleadsGroupsId, packetId);
            if (count <= 0) {
                return Pair.of(count, new ArrayList<>());
            }

            int offset = (pageNum - 1) * pageSize;
            int limit = pageSize;
            List<PublicleadsCustomerSimpleListVO> customerSimpleVOList = customerMapper.selectPublicleadsCustomerPage(publicleadsGroupsId, packetId, offset, limit);
            if (customerSimpleVOList == null || customerSimpleVOList.isEmpty()) {
                return Pair.of(count, new ArrayList<>());
            }

            // 获取查询的客户id
            List<Long> ids = customerSimpleVOList.stream().map(PublicleadsCustomerSimpleListVO::getId).collect(Collectors.toList());
            // 查询最近动态
            List<CustomerRecentActivityBO> customerRecentActivityList = customerFollowUpRecordsMapper.selectRecentActivityByCustomerIds(ids);
            Map<Long, CustomerRecentActivityBO> recentActivityMap = customerRecentActivityList.stream().collect(Collectors.toMap(customerRecentActivity -> customerRecentActivity.getCustomerId(), customerRecentActivity -> customerRecentActivity));
            // 查询客户主要联系人信息
            List<CustomerContactBO> customerContactBOList = customerContactMapper.selectCustomerMainContactByCustomerIds(ids);
            Map<Long, CustomerContactBO> customerContactMap = customerContactBOList.stream().collect(Collectors.toMap(customerContactBO -> customerContactBO.getCustomerId(), customerContactBO -> customerContactBO));
            customerSimpleVOList.stream().forEach(customerSimpleVO -> {
                if (recentActivityMap.containsKey(customerSimpleVO.getId())) {
                    customerSimpleVO.setRecentActivity(recentActivityMap.get(customerSimpleVO.getId()));
                    customerSimpleVO.setRecentFollowUp(recentActivityMap.get(customerSimpleVO.getId()));
                }

                if (customerContactMap.containsKey(customerSimpleVO.getId())) {
                    customerSimpleVO.setContact(customerContactMap.get(customerSimpleVO.getId()));
                }
            });

            return Pair.of(count, customerSimpleVOList);
        } catch (Exception e) {
            log.error("查询客户列表（分页）异常：{}", e);
            return Pair.of(0, new ArrayList<>());
        }
    }

    /**
     * 可见范围是否成立
     * @param customerFollowUpPersonnelVOList
     * @param segment
     * @return
     */
    private boolean isVisibleConditionMet(List<CustomerFollowUpPersonnelListVO> customerFollowUpPersonnelVOList, Segment segment) {
        Integer usageScope = segment.getUsageScope();
        // 公司可见
        if (usageScope.intValue() == 1) {
            String visibilityScope = segment.getVisibilityScope();
            ObjectMapper objectMapper = new ObjectMapper();
            // 将 JSON 字符串转换为 Java 对象
            try {
                SegmentVisibilityScopeBO segmentVisibilityScopeBO = objectMapper.readValue(visibilityScope, SegmentVisibilityScopeBO.class);
                SegmentVisibilityScopeDeptBO dept = segmentVisibilityScopeBO.getDept();
                SegmentVisibilityScopeUserBO user = segmentVisibilityScopeBO.getUser();
                // 用户维度判断可见范围是否成立
                if (user.getAllFlag()) {
                    return true;
                } else {
                    List<Long> userIds = user.getUserIds();
                    if (!userIds.isEmpty()) {
                        List<Long> followUpPersonnelUserIds = customerFollowUpPersonnelVOList.stream().map(CustomerFollowUpPersonnelListVO::getUserId).collect(Collectors.toList());

                        boolean userContainsAny = containsAnyElement(userIds, followUpPersonnelUserIds);
                        if (userContainsAny) return true;
                    }
                }

                // 部门维度判断可见范围是否成立
                if (dept.getAllFlag()) {
                    // 存在一个部门，客群全选部门，条件成立
                    long count = customerFollowUpPersonnelVOList.stream().map(CustomerFollowUpPersonnelListVO::getDeptId).count();
                    if (count > 0) return true;

                } else {
                    List<Long> deptIds = dept.getDeptIds();
                    if (!deptIds.isEmpty()) {
                        List<Long> followUpPersonnelDeptIds = customerFollowUpPersonnelVOList.stream().map(CustomerFollowUpPersonnelListVO::getDeptId).collect(Collectors.toList());

                        boolean deptContainsAny = containsAnyElement(deptIds, followUpPersonnelDeptIds);
                        if (deptContainsAny) return true;
                    }
                }
            } catch (JsonProcessingException e) {
                return false;
            }

            // 个人可见
        } else if (usageScope.intValue() == 2) {
            // 判断当前客群创建人是否是该客户的跟进人
            if (!customerFollowUpPersonnelVOList.isEmpty()) {
                List<Long> followUpPersonnelUserIds = customerFollowUpPersonnelVOList.stream().map(CustomerFollowUpPersonnelListVO::getUserId).collect(Collectors.toList());
                if (followUpPersonnelUserIds.contains(segment.getCreateId())) {
                    return true;
                }
            } else {
                return false;
            }
        }

        return false;
    }

    public boolean containsAnyElement(List<Long> a, List<Long> b) {
        for (Long element : b) {
            if (a.contains(element)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取客群列表
     * @param segmentId
     * @return
     */
    private List<Segment> getSegmentList(Long segmentId) {
        List<Segment> segmentList = new ArrayList<>();
        // segmentId == null代表所有客群
        if (segmentId == null) {
            Segment segment = new Segment();
            segment.setParentId(-1L);
            segmentList = segmentMapper.selectSegmentList(segment);
        } else {
            Segment segment = segmentMapper.selectSegmentById(segmentId);
            if (segment == null) {
                log.error("客群不存在，segmentId：{}", segmentId);
                return null;
            }
            segmentList.add(segment);
        }

        return segmentList;
    }

    /**
     * 获取客户列表
     * @param customerId
     * @return
     */
    private List<Long> getCustomerIdList(Long customerId) {
        List<Long> customerIdList = new ArrayList<>();
        // customerId == null代表所有客户
        if (customerId == null) {
            customerIdList = customerMapper.selectCustomerIdList();
        } else {
            customerIdList.add(customerId);
        }

        return customerIdList;
    }

}
