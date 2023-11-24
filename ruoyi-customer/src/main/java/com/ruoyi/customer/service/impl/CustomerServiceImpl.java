package com.ruoyi.customer.service.impl;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.customer.*;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.customer.CustomerShuffleThreadPoolUtil;
import com.ruoyi.customer.domain.*;
import com.ruoyi.customer.domain.bo.*;
import com.ruoyi.customer.domain.dto.*;
import com.ruoyi.customer.domain.vo.*;
import com.ruoyi.customer.mapper.*;
import com.ruoyi.customer.service.ICustomerFollowUpRecordsService;
import com.ruoyi.customer.service.IPublicleadsGroupsService;
import com.ruoyi.customer.service.ISegmentService;
import com.ruoyi.customer.service.handler.customer.column.ColumnContext;
import com.ruoyi.customer.service.handler.customer.column.utils.TimeRangeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.service.ICustomerService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * 客户详情Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-11-02
 */
@Slf4j
@Service
public class CustomerServiceImpl implements ICustomerService {
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
    private TagMapper tagMapper;
    @Resource
    private SourceMapper sourceMapper;
    @Resource
    private PacketMapper packetMapper;
    @Resource
    private CustomerSegmentMapper customerSegmentMapper;
    @Resource
    private FollowUpRulesMapper followUpRulesMapper;
    @Resource
    private CustomerSeaLogMapper customerSeaLogMapper;
    @Resource
    private SettingsMapper settingsMapper;
    @Resource
    private PublicleadsClaimLimitMapper publicleadsClaimLimitMapper;
    @Resource
    private LimitsMapper limitsMapper;
    @Resource
    private CustomerImportMapper customerImportMapper;
    @Resource
    private CustomerSegmentLogMapper customerSegmentLogMapper;
    @Resource
    private PublicleadsRulesMapper publicleadsRulesMapper;
    @Resource
    private PublicleadsWhiteListMapper publicleadsWhiteListMapper;
    @Resource
    private ICustomerFollowUpRecordsService customerFollowUpRecordsService;
    @Resource
    private IPublicleadsGroupsService publicleadsGroupsService;
    @Resource
    private ISegmentService segmentService;
    @Resource
    private ColumnContext columnContext;

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
    public List<Customer> selectCustomerList(Customer customer) {
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
    public boolean insertCustomer(CustomerAddOrUpdateDTO customerAddOrUpdateDTO) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        Customer customer = new Customer();
        BeanUtils.copyProperties(customerAddOrUpdateDTO, customer);

        Integer customerNoType = customerAddOrUpdateDTO.getCustomerNoType();
        String customerNo = null;
        // 客户编号类型：1-自动生成；2-自定义
        if (customerNoType.intValue() == 1) {
            customerNo = generateCustomerNo();
        } else if (customerNoType.intValue() == 2) {
            if (StringUtils.isBlank(customerAddOrUpdateDTO.getCustomerNo())) {
                throw new ServiceException("客户编号不能为空");
            }
            customerNo = customerAddOrUpdateDTO.getCustomerNo();
            // 判断客户编号是否已存在
            Integer count = customerMapper.countByCustomerNo(customerNo);
            if (count > 0) {
                throw new ServiceException("客户编号已存在");
            }
        }

        customer.setCustomerNo(customerNo);
        customer.setLastContactedAt(DateUtils.getNowDate());
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

        // 洗牌
        CustomerShuffleThreadPoolUtil.getThreadPool().execute(() -> shuffle(id, null));
        return true;
    }

    /**
     * 自动生成客户编号
     * @return
     */
    private String generateCustomerNo() {
        // 从数据库中获取最大的客户编号
        Long maxId = customerMapper.getMaxCustomerNo();

        // 如果数据库中没有客户编号，从10000开始
        if (maxId == null) {
            maxId = 9999L;
        }

        // 返回下一个递增的编号
        return String.valueOf(maxId + 1);
    }

    /**
     * 新增客户跟进人
     *
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
     *
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
     *
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
     *
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
    public boolean updateCustomer(CustomerAddOrUpdateDTO customerAddOrUpdateDTO) {
        Long id = customerAddOrUpdateDTO.getId();
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        Customer customer = new Customer();
        BeanUtils.copyProperties(customerAddOrUpdateDTO, customer);

        // 不能修改客户编号
        customerAddOrUpdateDTO.setCustomerNo(null);
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

        // 编辑客户事件
        customerFollowUpRulesHandler(id, FollowUpRulesTypeEnum.EDIT_CUSTOMER);

        // 洗牌
        CustomerShuffleThreadPoolUtil.getThreadPool().execute(() -> shuffle(id, null));
        return true;
    }

    /**
     * 批量删除客户详情
     *
     * @param ids 需要删除的客户详情主键
     * @return 结果
     */
    @Override
    public int deleteCustomerByIds(Long[] ids) {
        return customerMapper.deleteCustomerByIds(ids);
    }

    /**
     * 删除客户详情信息
     *
     * @param id 客户详情主键
     * @return 结果
     */
    @Override
    public int deleteCustomerById(Long id) {
        return customerMapper.deleteCustomerById(id);
    }

    @Override
    public Pair<Integer, List<PrivateleadsCustomerSimpleListVO>> privateleadsList(Long segmentId, Integer type, Long teamMemberId, Integer pageNum, Integer pageSize) {
        List<Long> userIdList = new ArrayList<>();
        if (type.intValue() == 1) {
            LoginUser loginUser = SecurityUtils.getLoginUser();
            Long userId = loginUser.getUserId();
            userIdList.add(userId);
        } else if (type.intValue() == 2) {
            List<TeamMembersListVO> teamMembers = getTeamMembers();
            List<Long> teamMembersIdList = teamMembers.stream().map(TeamMembersListVO::getUserId).collect(Collectors.toList());
            if (teamMemberId != null && teamMembersIdList.contains(teamMemberId)) {
                userIdList.add(teamMemberId);
            } else if (teamMemberId == null) {
                userIdList.addAll(teamMembersIdList);
            }
        }

        if (userIdList.isEmpty()) {
            return Pair.of(0, new ArrayList<>());
        }

        try {
            int count = customerMapper.countPrivateleadsCustomer(userIdList, segmentId);
            if (count <= 0) {
                return Pair.of(count, new ArrayList<>());
            }

            int offset = (pageNum - 1) * pageSize;
            int limit = pageSize;
            List<PrivateleadsCustomerSimpleListVO> customerSimpleVOList = customerMapper.selectPrivateleadsCustomerPage(userIdList, segmentId, offset, limit);
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
     *
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
     *
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
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean cancelFollowUp(Long id) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        // 查询当前客户的所有跟进人
        List<CustomerFollowUpPersonnelListVO> customerFollowUpPersonnelVOList = customerFollowUpPersonnelMapper.selectCustomerFollowUpPersonnelByCustomerId(id);
        // 判断当前是否只有一个跟进人，并且是自己，如果是的话，那么需要将该客户移至公海
        if (customerFollowUpPersonnelVOList != null && customerFollowUpPersonnelVOList.size() == 1
                && customerFollowUpPersonnelVOList.get(0).getUserId().longValue() == userId.longValue()) {
            Integer type = CustomerSeaTypeEnum.PUBLIC_LEADS.getType();
            // 移至客户到公海
            customerMapper.updateCustomerSeaType(id, type, userId, username);

            // 客户移入公海规则处理
            customerMoveToSeaHandler(Arrays.asList(id), type, userId, CustomerSeaLogTypeEnum.AUTO);
        }

        customerFollowUpPersonnelMapper.deleteCustomerFollowUpPersonnelByCustomerIdAndUserId(id, userId, userId, username);
        return true;
    }

    /**
     * 移入公海
     *
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

        Integer type = CustomerSeaTypeEnum.PUBLIC_LEADS.getType();
        Customer customer = customerMapper.selectCustomerById(moveToPublicleadsDTO.getId());
        customer.setPublicleadsGroupsId(moveToPublicleadsDTO.getPublicleadsGroupsId());
        customer.setSeaType(type);
        customer.setUpdateId(userId);
        customer.setUpdateBy(username);
        customer.setUpdateTime(DateUtils.getNowDate());
        customerMapper.updateCustomer(customer);

        customerMoveToSeaHandler(Arrays.asList(moveToPublicleadsDTO.getId()), type, userId, CustomerSeaLogTypeEnum.MANUAL);
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
     *
     * @param id
     * @return
     */
    @Override
    public List<CustomerFollowUpPersonnelListVO> followUpPersonnelList(Long id) {
        return customerFollowUpPersonnelMapper.selectCustomerFollowUpPersonnelByCustomerId(id);
    }

    /**
     * 修改重点客户
     *
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
     *
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
    public List<CustomerPublicleadsGroupListVO> publicleadsGroupsList() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        return customerMapper.publicleadsGroupsList(userId);
    }

    /**
     * 移入私海
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean moveToPrivateleads(Long id, Long packetId) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        // 校验公海领取规则
        /*isClaimCountValid(id, userId);
        // 校验领取上限
        isPublicleadsClaimLimitValid(userId);
        // 校验客户上限
        isCustomerLimitsValid(userId);*/

        Integer type = CustomerSeaTypeEnum.PRIVATE_LEADS.getType();
        Customer customer = customerMapper.selectCustomerById(id);
        customer.setSeaType(type);
        if (packetId != null) {
            customer.setPacketId(packetId);
        }
        customer.setUpdateId(userId);
        customer.setUpdateBy(username);
        customer.setUpdateTime(DateUtils.getNowDate());
        customerMapper.updateCustomer(customer);

        // 客户移入私海处理
        customerMoveToSeaHandler(Arrays.asList(id), type, userId, CustomerSeaLogTypeEnum.MANUAL);

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

    /**
     * 校验客户上限
     *
     * @param userId
     */
    private void isCustomerLimitsValid(Long userId) {
        Limits limitsParam = new Limits();
        limitsParam.setUserId(userId);
        // 客户上限
        limitsParam.setType(2);
        limitsParam.setDelFlag("0");
        List<Limits> limitsList = limitsMapper.selectLimitsList(limitsParam);
        if (limitsList == null || limitsList.isEmpty()) return;

        Limits limits = limitsList.get(0);
        Integer limitsNum = limits.getLimits();
        if (limitsNum != null) {
            // 查询私海客户数量
            Integer privateleadsCustomerNum = customerMapper.selectPrivateleadsNumByUserId(userId);
            if (privateleadsCustomerNum >= limitsNum) {
                throw new ServiceException("您的客户数量已达上限");
            }
        }
    }

    /**
     * 公海领取上限校验
     *
     * @param userId
     */
    private void isPublicleadsClaimLimitValid(Long userId) {
        PublicleadsClaimLimit publicleadsClaimLimitParam = new PublicleadsClaimLimit();
        publicleadsClaimLimitParam.setUserId(userId);
        publicleadsClaimLimitParam.setDelFlag("0");
        List<PublicleadsClaimLimit> publicleadsClaimLimitList = publicleadsClaimLimitMapper.selectPublicleadsClaimLimitList(publicleadsClaimLimitParam);
        if (publicleadsClaimLimitList == null || publicleadsClaimLimitList.isEmpty()) return;

        PublicleadsClaimLimit publicleadsClaimLimit = publicleadsClaimLimitList.get(0);
        Integer claimLimit = publicleadsClaimLimit.getClaimLimit();
        Integer claimPeriod = publicleadsClaimLimit.getClaimPeriod();
        Pair<String, String> timeRangePair = getTimeRange(claimPeriod);
        String startTime = timeRangePair.getFirst();
        String endTime = timeRangePair.getSecond();

        // 统计在指定时间领取的客户数量
        int count = customerSeaLogMapper.countCustomerSeaByUserIdAndCreateTime(userId, startTime, endTime);
        if (count >= claimLimit) {
            throw new ServiceException("领取失败，您在" + startTime + "至" + endTime + "时间内领取的客户数量已达到上限");
        }
    }

    /**
     * 获取周期时间区间
     *
     * @param claimPeriod
     * @return
     */
    private Pair<String, String> getTimeRange(Integer claimPeriod) {
        PublicleadsClaimPeriodEnum publicleadsClaimPeriodEnum = PublicleadsClaimPeriodEnum.getByClaimPeriod(claimPeriod);
        Date startTime = null;
        Date endTime = null;
        switch (publicleadsClaimPeriodEnum) {
            case DAILY:
                startTime = TimeRangeUtils.getStartOfDay(new Date());
                endTime = TimeRangeUtils.getEndOfDay(new Date());
                break;
            case WEEKLY:
                startTime = TimeRangeUtils.getStartOfThisWeek();
                endTime = TimeRangeUtils.getEndOfThisWeek();
                break;
            case MONTHLY:
                startTime = TimeRangeUtils.getStartOfThisMonth();
                endTime = TimeRangeUtils.getEndOfThisMonth();
                break;
            case YEARLY:
                startTime = TimeRangeUtils.getStartOfThisYear();
                endTime = TimeRangeUtils.getEndOfThisYear();
                break;
            default:
                break;
        }

        if (startTime == null || endTime == null) return null;

        return Pair.of(DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss", startTime), DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss", endTime));
    }

    /**
     * 领取数量限制是否成立
     *
     * @param customerId
     * @param userId
     * @return
     */
    private void isClaimCountValid(Long customerId, Long userId) {
        List<Settings> settingsList = settingsMapper.selectSettingsList(new Settings());
        if (settingsList == null || settingsList.isEmpty()) return;

        Settings settings = settingsList.get(0);
        if (settings.getClaimLimitFlag() != null && settings.getClaimLimitFlag().intValue() == 1
                && settings.getClaimLimitDays() != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());

            calendar.add(Calendar.DAY_OF_YEAR, -settings.getClaimLimitDays());

            String resultDate = DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss", calendar.getTime());

            // 查询在该时间之后是否存在该跟进人的领取该客户的记录
            int count = customerSeaLogMapper.countCustomerSeaByCustomerIdAndUserIdAndCreateTime(customerId, userId, resultDate);
            if (count > 0) {
                throw new ServiceException("领取失败，该客户在" + settings.getClaimLimitDays() + "天内已被领取过");
            }
        }

        return;
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
            List<Long> segmentIdList = new ArrayList<>();
            // 获取客户详情
            CustomerDetailVO customerDetail = getCustomerDetail(customerId);
            // 获取客户跟进人
            List<CustomerFollowUpPersonnelListVO> customerFollowUpPersonnelVOList = customerFollowUpPersonnelMapper.selectCustomerFollowUpPersonnelByCustomerId(customerId);
            for (Segment segment : segmentList) {
                // 可见范围是否成立，不成立跳过客群
                List<UserDeptInfoBO> userDeptInfoBOList = new ArrayList<>();
                for (CustomerFollowUpPersonnelListVO customerFollowUpPersonnelVO : customerFollowUpPersonnelVOList) {
                    userDeptInfoBOList.add(UserDeptInfoBO.builder()
                            .userId(customerFollowUpPersonnelVO.getUserId())
                            .deptId(customerFollowUpPersonnelVO.getDeptId())
                            .build());
                }
                if (!segmentService.isVisibleConditionMet(userDeptInfoBOList, segment)) continue;
                // 先判断父客群是否成立
                boolean parentSegmentConditionMet = isSegmentConditionMet(customerDetail, segment);

                if (parentSegmentConditionMet) {
                    // 添加一级客群id
                    segmentIdList.add(segment.getId());

                    // 查询二级客群列表
                    List<Segment> secondSegmentList = getSencondSegmentList(segment.getId());
                    if (secondSegmentList != null && !secondSegmentList.isEmpty()) {
                        // 有二级客群，判断二级客群是否成立
                        for (Segment secondSegment : secondSegmentList) {
                            boolean secondSegmentConditionMet = isSegmentConditionMet(customerDetail, secondSegment);
                            if (secondSegmentConditionMet) {
                                // 二级客群成立，添加二级客群id
                                segmentIdList.add(secondSegment.getId());
                            }
                        }
                    }
                }
            }

            // 删除客户和客群的关系
            customerSegmentMapper.deleteCustomerSegmentByCustomerId(customerId);
            // 批量保存客户和客群的关系
            batchSaveCustomerSegment(customerId, segmentIdList);
            // 批量保存客户和客群的关系日志
            batchSaveCustomerSegmentLog(customerId, segmentIdList);
        });

        return true;
    }

    /**
     * 批量保存客户和客群的关系日志
     * @param customerId
     * @param segmentIdList
     */
    private void batchSaveCustomerSegmentLog(Long customerId, List<Long> segmentIdList) {
        if (segmentIdList == null || segmentIdList.isEmpty()) return;

        List<CustomerSegmentLog> customerSegmentLogList = new ArrayList<>();
        segmentIdList.stream().forEach(segmentId -> {
            CustomerSegmentLog customerSegmentLog = new CustomerSegmentLog();
            customerSegmentLog.setCustomerId(customerId);
            customerSegmentLog.setSegmentId(segmentId);
            customerSegmentLog.setCreateTime(DateUtils.getNowDate());
            customerSegmentLogList.add(customerSegmentLog);
        });

        customerSegmentLogMapper.batchInsertCustomerSegmentLog(customerSegmentLogList);
    }

    /**
     * 批量保存客户和客群的关系
     *
     * @param customerId
     * @param segmentIdList
     */
    private void batchSaveCustomerSegment(Long customerId, List<Long> segmentIdList) {
        if (segmentIdList == null || segmentIdList.isEmpty()) return;

        List<CustomerSegment> customerSegmentList = new ArrayList<>();
        segmentIdList.stream().forEach(segmentId -> {
            CustomerSegment customerSegment = new CustomerSegment();
            customerSegment.setCustomerId(customerId);
            customerSegment.setSegmentId(segmentId);
            customerSegmentList.add(customerSegment);
        });

        customerSegmentMapper.batchInsertCustomerSegment(customerSegmentList);
    }

    /**
     * 查询二级客群列表
     *
     * @param segmentId
     * @return
     */
    private List<Segment> getSencondSegmentList(Long segmentId) {
        Segment segment = new Segment();
        segment.setParentId(segmentId);
        return segmentMapper.selectSegmentList(segment);
    }

    /**
     * 判断该客户是否满足客群条件
     *
     * @param customerDetail
     * @param segment
     * @return
     */
    private boolean isSegmentConditionMet(CustomerDetailVO customerDetail, Segment segment) {
        String conditionRuleContent = segment.getConditionRuleContent();
        List<SegmentConditionRuleBO> segmentConditionRuleBOList = null;
        Gson gson = new Gson();
        try {
            segmentConditionRuleBOList = Arrays.asList(gson.fromJson(conditionRuleContent, SegmentConditionRuleBO[].class));

        } catch (Exception e) {
            log.error("客群条件规则转换异常 客群ID：{}" +
                    "\n原因：{}", segment.getId(), e);
            return false;
        }

        for (SegmentConditionRuleBO segmentConditionRuleBO : segmentConditionRuleBOList) {
            boolean isConditionMet = columnContext.handler(customerDetail, segmentConditionRuleBO);
            if (!isConditionMet) return false;

            // 多条件如果是或条件，则只要有一个条件成立就返回true
            String andOr = segmentConditionRuleBO.getAndOr();
            if (AndOrEnum.OR.getAndOr().equals(andOr)) return true;
        }

        return true;
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


    /**
     * 客户跟进规则处理
     * @param customerId
     */
    @Override
    public void customerFollowUpRulesHandler(Long customerId, FollowUpRulesTypeEnum followUpRulesTypeEnum) {
        if (followUpRulesTypeEnum == null) return;

        FollowUpRules followUpRulesParam = new FollowUpRules();
        followUpRulesParam.setType(followUpRulesTypeEnum.getType());
        followUpRulesParam.setActiveFlag(true);
        followUpRulesParam.setDelFlag("0");
        // 查询客户跟进规则
        List<FollowUpRules> followUpRulesList = followUpRulesMapper.selectFollowUpRulesList(followUpRulesParam);
        long lastContactedAtCount = followUpRulesList.stream().filter(followUpRules -> followUpRules.getCategory().intValue() == FollowUpRulesCategoryEnum.LAST_CONTACTED_AT.getCategory()).count();
        long lastFollowUpAtCount = followUpRulesList.stream().filter(followUpRules -> followUpRules.getCategory().intValue() == FollowUpRulesCategoryEnum.LAST_FOLLOWUP_AT.getCategory()).count();
        Date lastContactedAt = null;
        Date lastFollowUpAt = null;
        if (lastContactedAtCount > 0) {
            lastContactedAt = new Date();
        }
        if (lastFollowUpAtCount > 0) {
            lastFollowUpAt = new Date();
        }

        // 更新客户最后联系时间和最后跟进时间
        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setLastContactedAt(lastContactedAt);
        customer.setLastFollowupAt(lastFollowUpAt);
        if (lastContactedAt != null || lastFollowUpAt != null) {
            customerMapper.updateCustomer(customer);
        }
    }

    /**
     * 客户移入公海规则处理
     * @param customerIdList
     * @param seaType
     * @param customerSeaLogTypeEnum
     */
    @Override
    public void customerMoveToSeaHandler(List<Long> customerIdList, Integer seaType, Long createId, CustomerSeaLogTypeEnum customerSeaLogTypeEnum) {
        List<CustomerSeaLog> customerSeaLogList = new ArrayList<>();
        for (Long customerId : customerIdList) {
            // 写入到日志
            CustomerSeaLog customerSeaLog = new CustomerSeaLog();
            customerSeaLog.setCustomerId(customerId);
            customerSeaLog.setSeaType(seaType);
            customerSeaLog.setType(customerSeaLogTypeEnum.getType());
            customerSeaLog.setCreateTime(DateUtils.getNowDate());
            customerSeaLog.setCreateId(createId);
            customerSeaLogList.add(customerSeaLog);
        }

        customerSeaLogMapper.batchInsertCustomerSeaLog(customerSeaLogList);
    }

    /**
     * 客户查重筛选字段列表
     * @return
     */
    @Override
    public List<CustomerDuplicateFilterColumnListVO> customerDuplicateFilterColumnList() {
        List<CustomerDuplicateFilterColumnListVO> customerDuplicateFilterColumnVOList = new ArrayList<>();
        for (CustomerDuplicateColumnEnum customerDuplicateColumnEnum : CustomerDuplicateColumnEnum.values()) {
            customerDuplicateFilterColumnVOList.add(CustomerDuplicateFilterColumnListVO.builder().columnName(customerDuplicateColumnEnum.getColumnName()).columnAlias(customerDuplicateColumnEnum.getColumnAlias()).build());
        }

        return customerDuplicateFilterColumnVOList;
    }

    @Override
    public Pair<Integer, List<CustomerDuplicateListVO>> duplicateList(String columnName, String searchText, Integer pageNum, Integer pageSize) {
        if (StringUtils.isBlank(searchText)) {
            return Pair.of(0, new ArrayList<>());
        }

        // 统计总数量
        int count = customerMapper.countCustomerDuplicate(columnName, searchText);
        if (count == 0) {
            return Pair.of(0, Collections.emptyList());
        }

        int offset = (pageNum - 1) * pageSize;
        int limit = pageSize;
        // 客户查重列表
        List<CustomerDuplicateListVO> customerDuplicateVOList = customerMapper.customerDuplicateList(columnName, searchText, offset, limit);
        return Pair.of(count, customerDuplicateVOList);
    }

    @Override
    public boolean importCustomer(Integer importType, MultipartFile file) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        CustomerImport customerImport = new CustomerImport();
        customerImport.setImportType(importType);
        customerImport.setImportStatus(CustomerImportStatusEnum.IN_PROGRESS.getStatus());
        customerImport.setExpectedImportCount(0);
        customerImport.setSuccessImportCount(0);
        customerImport.setFailedImportCount(0);
        customerImport.setCreateId(userId);
        customerImport.setCreateBy(username);
        customerImport.setCreateTime(DateUtils.getNowDate());
        customerImport.setUpdateId(userId);
        customerImport.setUpdateBy(username);
        customerImport.setUpdateTime(DateUtils.getNowDate());
        customerImportMapper.insertCustomerImport(customerImport);

        List<Map<String, Object>> mapList = new ArrayList<>();
        int importStatus = CustomerImportStatusEnum.FAILURE.getStatus();
        boolean isFirstRow = true; // 标志，判断是否是第一行
        AtomicInteger expectedImportCount = new AtomicInteger();
        AtomicInteger successImportCount = new AtomicInteger();
        AtomicInteger failedImportCount = new AtomicInteger();
        try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                if (isFirstRow) {
                    // 跳过第一行
                    isFirstRow = false;
                    continue;
                }

                mapList.add(getExcelData(row));
            }

            // 查询存在的标签ID
            Map<String, Long> tagMap = getTagMap(mapList);
            // 查询存在的客户阶段
            Map<String, Long> stageMap = getStageMap(mapList);
            // 查询存在的客户来源ID
            Map<String, Long> sourceMap = getSourceMap(mapList);

            Map<Object, List<Map<String, Object>>> companyNameMap = mapList.stream().filter(map -> map.get("companyName") != null && !map.get("companyName").toString().equals("")).collect(Collectors.groupingBy(map -> map.get("companyName").toString()));

            companyNameMap.forEach((companyName, contactMapList) -> {
                CustomerAddOrUpdateDTO customerAddOrUpdateDTO = generateCustomerAddOrUpdateDTO(tagMap, stageMap, sourceMap, contactMapList, importType);
                try {
                    insertCustomer(customerAddOrUpdateDTO);
                    successImportCount.getAndIncrement();
                } catch (Exception e) {
                    log.error("新增客户异常：{}", e);
                    failedImportCount.getAndIncrement();
                }
                expectedImportCount.getAndIncrement();
            });

            importStatus = CustomerImportStatusEnum.SUCCESS.getStatus();
        } catch (IOException e) {
            log.error("获取excel数据异常{}", e);
        }

        // 更新状态
        customerImport.setExpectedImportCount(expectedImportCount.get());
        customerImport.setSuccessImportCount(successImportCount.get());
        customerImport.setFailedImportCount(failedImportCount.get());
        customerImport.setImportStatus(importStatus);
        customerImportMapper.updateCustomerImport(customerImport);
        return true;
    }

    /**
     * 移入公海规则处理
     * @return
     */
    @Override
    public boolean movePublicleadsRulesHandler() {
        // 查询所有开启状态和已经开始规则的客群天数
        List<PublicleadsRulesSegmentIdDaysBO> segmentIdDaysBOList = publicleadsRulesMapper.getSegmentIdAndDays();
        if (segmentIdDaysBOList == null || segmentIdDaysBOList.isEmpty()) return true;

        // 获取白名单用户
        List<PublicleadsWhiteList> publicleadsWhiteListList = publicleadsWhiteListMapper.selectPublicleadsWhiteListList(new PublicleadsWhiteList());
        Set<Long> whiteListUserIdSet = publicleadsWhiteListList.stream().map(PublicleadsWhiteList::getUserId).collect(Collectors.toSet());

        // 即将移入公海的客户ID集合
        Set<Long> movePublicleadsCustomerIdSet = new HashSet<>();

        segmentIdDaysBOList.stream().forEach(segmentIdDaysBO -> {
            Long segmentId = segmentIdDaysBO.getSegmentId();
            Integer days = segmentIdDaysBO.getDays();

            // 查询所关联该客群的客户信息
            List<CustomerMovePublicleadsRulesInfoBO> customerMovePublicleadsRulesInfoBOList = customerMapper.selectCustomerMovePublicleadsRulesInfoBySegmentId(segmentId);
            Map<Long, List<CustomerMovePublicleadsRulesInfoBO>> customerMovePublicleadsRulesMap = customerMovePublicleadsRulesInfoBOList.stream().collect(Collectors.groupingBy(CustomerMovePublicleadsRulesInfoBO::getId));
            customerMovePublicleadsRulesMap.forEach((customerId, customerMovePublicleadsRulesInfoList) -> {
                // 是否移入公海
                boolean isMovePublicleads = false;
                // 当前时间
                Date currentDate = new Date();
                // 获取最近联系时间
                Date lastContactedAt = customerMovePublicleadsRulesInfoList.get(0).getLastContactedAt();

                Set<Long> userIdSet = customerMovePublicleadsRulesInfoList.stream().map(CustomerMovePublicleadsRulesInfoBO::getUserId).filter(userId -> userId != null).collect(Collectors.toSet());
                // 如果该客户在私海里面没有跟进记录，则移入公海
                if (userIdSet == null || userIdSet.isEmpty()) {
                    isMovePublicleads = true;
                // 如果存在的跟进人存在不在白名单里面的，则移入公海
                } else if (!whiteListUserIdSet.containsAll(userIdSet)) {
                    isMovePublicleads = true;
                }

                // 计算两个日期之间的天数差距
                long diffInMillies = Math.abs(lastContactedAt.getTime() - currentDate.getTime());
                long daysDifference = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                if (daysDifference >= days.longValue()) {
                    isMovePublicleads = true;
                }

                if (isMovePublicleads) {
                    movePublicleadsCustomerIdSet.add(customerId);
                }
            });
        });

        // 移入公海
        if (movePublicleadsCustomerIdSet.isEmpty()) {
            //
            List<Long> movePublicleadsCustomerIdList = new ArrayList<>(movePublicleadsCustomerIdSet);
            List<List<Long>> customerIdListPartition = Lists.partition(movePublicleadsCustomerIdList, 200);

            for (List<Long> customerIdList : customerIdListPartition) {
                // 批量更新客户为公海类型
                int seaType = CustomerSeaTypeEnum.PUBLIC_LEADS.getType();
                customerMapper.batchUpdateCustomerSeaType(customerIdList, seaType);
                // 记录移入公海的日志
                customerMoveToSeaHandler(customerIdList, seaType, null, CustomerSeaLogTypeEnum.AUTO);
            }
        }

        return true;
    }

    /**
     * 获取团队成员
     * @return
     */
    @Override
    public List<TeamMembersListVO> getTeamMembers() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        Long deptId = loginUser.getDeptId();
        SysUser user = loginUser.getUser();
        String nickName = user.getNickName();

        List<TeamMembersListVO> teamMembersListVOList = new ArrayList<>();

        // 查询角色信息
        List<RoleDeptSimpleInfoVO> roleDeptSimpleInfoVOList = customerMapper.queryRoleDeptSimpleInfo(userId);
        Map<Long, List<RoleDeptSimpleInfoVO>> roleIdRoleDeptSimpleInfoMap = roleDeptSimpleInfoVOList.stream().collect(Collectors.groupingBy(roleDeptSimpleInfoVO -> roleDeptSimpleInfoVO.getRoleId()));

        for (Map.Entry<Long, List<RoleDeptSimpleInfoVO>> entry : roleIdRoleDeptSimpleInfoMap.entrySet()) {
            List<RoleDeptSimpleInfoVO> roleDeptInfoVOList = entry.getValue();

            RoleDeptSimpleInfoVO firstRoleDeptSimpleInfoVO = roleDeptInfoVOList.get(0);
            DataScopeEnum dataScopeEnum = DataScopeEnum.getByCode(firstRoleDeptSimpleInfoVO.getDataScope());

            // 获取角色部门ID集合
            List<Long> roleDeptIdList = roleDeptInfoVOList.stream().filter(roleDeptInfoVO -> roleDeptInfoVO.getRoleDeptId() != null).map(roleDeptInfoVO -> roleDeptInfoVO.getRoleDeptId()).collect(Collectors.toList());
            if (dataScopeEnum != null) {
                switch (dataScopeEnum) {
                    case ALL:
                        teamMembersListVOList.addAll(customerMapper.getAllUser());
                        break;
                    case CUSTOM:
                        if (roleDeptIdList != null && !roleDeptIdList.isEmpty()) {
                            teamMembersListVOList.addAll(customerMapper.getUserByDeptIds(roleDeptIdList));
                        }
                        break;
                    case DEPARTMENT:
                        if (deptId != null) {
                            teamMembersListVOList.addAll(customerMapper.getUserByDeptIds(Arrays.asList(deptId)));
                        }
                        break;
                    case DEPARTMENT_AND_BELOW:
                        if (deptId != null) {
                            List<Long> subordinateDeptIds = getSubordinateDeptIds(deptId);
                            subordinateDeptIds.add(deptId);
                            teamMembersListVOList.addAll(customerMapper.getUserByDeptIds(subordinateDeptIds));
                        }
                        break;
                }
            }
        }

        // 把自己加入到团队成员中
        teamMembersListVOList.add(TeamMembersListVO.builder().userId(userId).nickName(nickName).build());
        return deduplicateById(teamMembersListVOList);
    }

    /**
     * 去重
     * @param list
     * @return
     */
    private List<TeamMembersListVO> deduplicateById(List<TeamMembersListVO> list) {
        Set<TeamMembersListVO> set = new HashSet<>(list);
        return new ArrayList<>(set);
    }

    public List<Long> getSubordinateDeptIds(Long deptId) {
        List<Long> result = new ArrayList<>();
        findSubordinateDeptIds(deptId, result);
        return result;
    }

    private void findSubordinateDeptIds(Long deptId, List<Long> result) {
        List<Long> subDepartments = customerMapper.findSubordinateDeptIds(deptId);
        for (Long subDeptId : subDepartments) {
            result.add(subDeptId);
            // 递归查询下属部门
            findSubordinateDeptIds(subDeptId, result);
        }
    }

    /**
     * 生成CustomerAddOrUpdateDTO对象
     * @param contactMapList
     * @return
     */
    private CustomerAddOrUpdateDTO generateCustomerAddOrUpdateDTO(Map<String, Long> tagMap, Map<String, Long> stageMap, Map<String, Long> sourceMap, List<Map<String, Object>> contactMapList, Integer importType) {
        Map<String, Object> map = contactMapList.get(0);
        String companyName = map.get("companyName") != null ? String.valueOf(map.get("companyName")) : null;
        String shortName = map.get("shortName") != null ? String.valueOf(map.get("shortName")) : null;
        String countryRegion = map.get("countryRegion") != null ? String.valueOf(map.get("countryRegion")) : null;
        String tag = map.get("tag") != null ? String.valueOf(map.get("tag")) : null;
        List<Long> tagIds = null;
        if (StringUtils.isNotBlank(tag)) {
            tagIds = new ArrayList<>();
            String[] tagArr = tag.split(";");
            for (String tagStr : tagArr) {
                if (tagMap.containsKey(tagStr)) {
                    Long tagId = tagMap.get(tagStr);
                    tagIds.add(tagId);
                }
            }
        }

        Long stageId = null;
        String stageName = map.get("stage") != null ? String.valueOf(map.get("stage")) : null;
        if (StringUtils.isNotBlank(stageName)) {
            stageId = stageMap.get(stageName);
        }

        List<Long> sourceIds = null;
        String source = map.get("source") != null ? String.valueOf(map.get("source")) : null;
        if (StringUtils.isNotBlank(source)) {
            sourceIds = new ArrayList<>();
            String[] sourceArr = source.split(";");
            for (String sourceStr : sourceArr) {
                if (sourceMap.containsKey(sourceStr)) {
                    Long sourceId = sourceMap.get(sourceStr);
                    sourceIds.add(sourceId);
                }
            }
        }

        String companyWebsite = map.get("companyWebsite") != null ? String.valueOf(map.get("companyWebsite")) : null;
        String phonePrefix = null;
        String phone = null;
        String phoneStr = map.get("phone") != null ? String.valueOf(map.get("phone")) : null;
        if (StringUtils.isBlank(phoneStr)) {
            String[] split = phoneStr.split("-");
            if (split.length == 2) {
                phonePrefix = split[0];
                phone = split[1];
            } else {
                phone = phoneStr;
            }
        }

        String address = map.get("address") != null ? String.valueOf(map.get("address")) : null;
        String companyRemarks = map.get("companyRemarks") != null ? String.valueOf(map.get("companyRemarks")) : null;
        String customerNo = map.get("customerNo") != null ? String.valueOf(map.get("customerNo")) : null;

        List<CustomerContactAddOrUpdateDTO> contactList = new ArrayList<>();
        contactMapList.stream().forEach(contactMap -> {
            String contactNickName = contactMap.get("contactNickName") != null ? String.valueOf(contactMap.get("contactNickName")) : null;
            String contactEmail = contactMap.get("contactEmail") != null ? String.valueOf(contactMap.get("contactEmail")) : null;
            String contactPhone = contactMap.get("contactPhone") != null ? String.valueOf(contactMap.get("contactPhone")) : null;
            String facebook = contactMap.get("facebook") != null ? String.valueOf(contactMap.get("facebook")) : null;
            String twitter = contactMap.get("twitter") != null ? String.valueOf(contactMap.get("twitter")) : null;
            String linkedIn = contactMap.get("linkedIn") != null ? String.valueOf(contactMap.get("linkedIn")) : null;

            JSONArray contactPhoneJsonArr = new JSONArray();
            String[] contactPhoneArr = contactPhone.split(";");
            for (String contactPhoneStr : contactPhoneArr) {
                String[] arr = contactPhoneStr.split("-");
                JSONObject jsonObj = new JSONObject();
                if (arr.length == 2) {
                    jsonObj.put("phone_prefix", arr[0]);
                    jsonObj.put("phone", arr[1]);
                } else {
                    jsonObj.put("phone", arr[0]);
                }
                contactPhoneJsonArr.add(jsonObj);
            }

            JSONArray socialPlatformJsonArr = new JSONArray();
            if (StringUtils.isNotBlank("facebook")) {
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("facebook", facebook);
                socialPlatformJsonArr.add(jsonObj);
            }

            if (StringUtils.isNotBlank("twitter")) {
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("twitter", twitter);
                socialPlatformJsonArr.add(jsonObj);
            }

            if (StringUtils.isNotBlank("linkedIn")) {
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("linkedIn", linkedIn);
                socialPlatformJsonArr.add(jsonObj);
            }
            // 社交平台
            String socialPlatform = !socialPlatformJsonArr.isEmpty() ? JSONObject.toJSONString(socialPlatformJsonArr) : null;
            // 手机号码
            String cPhone = !contactPhoneJsonArr.isEmpty() ? JSONObject.toJSONString(contactPhoneJsonArr) : null;
            String position = contactMap.get("position") != null ? String.valueOf(contactMap.get("position")) : null;
            // 性别 1.不设置 2.男 3.女
            Integer sex = 1;
            if (contactMap.containsKey("sex") && StringUtils.isNotBlank(contactMap.get("sex").toString())) {
                String sexStr = String.valueOf(contactMap.get("sex"));
                if (sexStr.equals("男")) {
                    sex = 2;
                } else if (sexStr.equals("女")) {
                    sex = 3;
                }
            }
            String contactRemarks = contactMap.get("contactRemarks") != null ? String.valueOf(contactMap.get("contactRemarks")) : null;

            CustomerContactAddOrUpdateDTO contact = new CustomerContactAddOrUpdateDTO();
            contact.setNickName(contactNickName);
            contact.setEmail(contactEmail);
            contact.setSocialPlatform(socialPlatform);
            contact.setPhone(cPhone);
            contact.setPosition(position);
            contact.setSex(sex);
            contact.setContactRemarks(contactRemarks);
            contactList.add(contact);
        });

        CustomerAddOrUpdateDTO customerAddOrUpdateDTO = new CustomerAddOrUpdateDTO();
        customerAddOrUpdateDTO.setCompanyName(companyName);
        customerAddOrUpdateDTO.setShortName(shortName);
        customerAddOrUpdateDTO.setSeaType(importType);
        customerAddOrUpdateDTO.setCustomerNo(customerNo);
        customerAddOrUpdateDTO.setCustomerNoType(StringUtils.isBlank(customerNo) ? 1 : 2);
        customerAddOrUpdateDTO.setCountryRegion(countryRegion);
        customerAddOrUpdateDTO.setTagIds(tagIds);
        customerAddOrUpdateDTO.setStageId(stageId);
        customerAddOrUpdateDTO.setSourceIds(sourceIds);
        customerAddOrUpdateDTO.setCompanyWebsite(companyWebsite);
        customerAddOrUpdateDTO.setPhonePrefix(phonePrefix);
        customerAddOrUpdateDTO.setPhone(phone);
        customerAddOrUpdateDTO.setAddress(address);
        customerAddOrUpdateDTO.setCompanyRemarks(companyRemarks);
        customerAddOrUpdateDTO.setContactList(contactList);

        return customerAddOrUpdateDTO;
    }

    /**
     * 获取阶段map
     * @param mapList
     * @return
     */
    private Map<String, Long> getStageMap(List<Map<String, Object>> mapList) {
        String stageNames = mapList.stream().filter(map -> map.get("stage") != null && !map.get("stage").toString().equals("")).map(map -> map.get("stage").toString()).collect(Collectors.joining(";"));
        if (StringUtils.isNotBlank(stageNames)) {
            List<String> stageNameList = Arrays.asList(stageNames.split(";"));
            List<Stage> stageList = stageMapper.selectByNames(stageNameList);
            Map<String, Long> stageMap = new HashMap<>();
            stageList.forEach(stage -> stageMap.put(stage.getName(), stage.getId()));
            return stageMap;
        }

        return new HashMap<>();
    }

    /**
     * 获取来源map
     * @param mapList
     * @return
     */
    private Map<String, Long> getSourceMap(List<Map<String, Object>> mapList) {
        String sourceNames = mapList.stream().filter(map -> map.get("source") != null && !map.get("source").toString().equals("")).map(map -> map.get("source").toString()).collect(Collectors.joining(";"));
        if (StringUtils.isNotBlank(sourceNames)) {
            List<String> sourceNameList = Arrays.asList(sourceNames.split(";"));
            List<Source> sourceList = sourceMapper.selectByNames(sourceNameList);
            Map<String, Long> sourceMap = new HashMap<>();
            sourceList.forEach(source -> sourceMap.put(source.getName(), source.getId()));
            return sourceMap;
        }

        return new HashMap<>();
    }

    /**
     * 获取标签map
     * @param mapList
     * @return
     */
    private Map<String, Long> getTagMap(List<Map<String, Object>> mapList) {
        String tagNames = mapList.stream().filter(map -> map.get("tag") != null && !map.get("tag").toString().equals("")).map(map -> map.get("tag").toString()).collect(Collectors.joining(";"));
        if (StringUtils.isNotBlank(tagNames)) {
            List<String> tagNameList = Arrays.asList(tagNames.split(";"));
            List<Tag> tagList = tagMapper.selectByNames(tagNameList);
            Map<String, Long> tagMap = new HashMap<>();
            tagList.forEach(tag -> tagMap.put(tag.getName(), tag.getId()));
            return tagMap;
        }

        return new HashMap<>();
    }

    /**
     * 获取excel数据
     * @param row
     * @return
     */
    private Map<String, Object> getExcelData(Row row) {
        Map<String, Object> map = new HashMap<>();

        map.put("companyName", getStringValue(row.getCell(0)));
        map.put("contactNickName", getStringValue(row.getCell(1)));
        map.put("contactEmail", getStringValue(row.getCell(2)));
        map.put("shortName", getStringValue(row.getCell(3)));
        map.put("countryRegion", getStringValue(row.getCell(4)));
        map.put("tag", getStringValue(row.getCell(5)));
        map.put("stage", getStringValue(row.getCell(6)));
        map.put("source", getStringValue(row.getCell(7)));
        map.put("companyWebsite", getStringValue(row.getCell(8)));
        map.put("phone", getStringValue(row.getCell(9)));
        map.put("address", getStringValue(row.getCell(10)));
        map.put("companyRemarks", getStringValue(row.getCell(11)));
        map.put("customerNo", getStringValue(row.getCell(12)));
        map.put("contactPhone", getStringValue(row.getCell(13)));
        map.put("facebook", getStringValue(row.getCell(14)));
        map.put("twitter", getStringValue(row.getCell(15)));
        map.put("linkedIn", getStringValue(row.getCell(16)));
        map.put("position", getStringValue(row.getCell(17)));
        map.put("sex", getStringValue(row.getCell(18)));
        map.put("contactRemarks", getStringValue(row.getCell(19)));

        return map;
    }

    private String getStringValue(Cell cell) {
        if (cell == null) {
            return null;
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return null;
        }
    }
}
