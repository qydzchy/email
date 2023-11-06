package com.ruoyi.customer.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.customer.CustomerSeaTypeEnum;
import com.ruoyi.common.enums.customer.MetadataColumnAppTypeEnum;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.customer.domain.*;
import com.ruoyi.customer.domain.dto.*;
import com.ruoyi.customer.domain.vo.CustomerFollowUpPersonnelListVO;
import com.ruoyi.customer.domain.vo.CustomerSimpleListVO;
import com.ruoyi.customer.domain.vo.MetadataColumnListVO;
import com.ruoyi.customer.domain.vo.PublicleadsGroupsListVO;
import com.ruoyi.customer.mapper.*;
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
    private MetadataColumnMapper metadataColumnMapper;
    @Resource
    private CustomerSourceMapper customerSourceMapper;
    @Resource
    private CustomerTagMapper customerTagMapper;
    @Resource
    private CustomerFollowUpPersonnelMapper customerFollowUpPersonnelMapper;
    @Resource
    private CustomerPublicleadsMapper customerPublicleadsMapper;


    /**
     * 查询客户详情
     * 
     * @param id 客户详情主键
     * @return 客户详情
     */
    @Override
    public Customer selectCustomerById(Long id)
    {
        return customerMapper.selectCustomerById(id);
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

        customer.setCreateId(userId);
        customer.setCreateBy(username);
        customer.setCreateTime(DateUtils.getNowDate());
        customer.setUpdateId(userId);
        customer.setUpdateBy(username);
        customer.setUpdateTime(DateUtils.getNowDate());
        Long id = customerMapper.insertCustomer(customer);

        // 新增客户来源的关联关系
        batchInsertCustomerSource(id, customerAddOrUpdateDTO.getSourceIds());
        // 新增客户标签的关联关系
        batchInsertCustomerTag(id, customerAddOrUpdateDTO.getTagIds());
        // 新增跟进人 todo

        // 批量新增客户联系人
        batchInsertCustomerContact(customerAddOrUpdateDTO.getContactList(), userId, username, id);
        return true;
    }

    /**
     * 批量新增客户标签
     * @param id
     * @param tagIds
     */
    private void batchInsertCustomerTag(Long id, List<Long> tagIds) {
        List<CustomerTag> customerTagList = new ArrayList<>();
        for (Long tagId : tagIds) {
            CustomerTag customerTag = new CustomerTag();
            customerTag.setCustomerId(id);
            customerTag.setTagId(tagId);
            customerTagList.add(customerTag);
        }

        // 批量新增客户标签
        customerTagMapper.batchInsertCustomerTag(customerTagList);
    }

    /**
     * 批量新增客户来源
     * @param id
     * @param sourceIds
     */
    private void batchInsertCustomerSource(Long id, List<Long> sourceIds) {
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
        List<CustomerContact> customerContactList = new ArrayList<>();
        for (CustomerContactAddOrUpdateDTO customerContactAddOrUpdateDTO : contactDtoList) {
            CustomerContact customerContact = new CustomerContact();
            BeanUtils.copyProperties(customerContactAddOrUpdateDTO, customerContact);
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

        // 删除客户来源的关联关系
        customerSourceMapper.deleteCustomerSourceByCustomerId(id);
        // 删除客户标签的关联关系
        customerTagMapper.deleteCustomerTagByCustomerId(id);

        // 删除该客户现有的客户联系人
        customerContactMapper.deleteCustomerContactByCustomerId(id, userId, username);

        // 新增客户来源的关联关系
        batchInsertCustomerSource(id, customerAddOrUpdateDTO.getSourceIds());
        // 新增客户标签的关联关系
        batchInsertCustomerTag(id, customerAddOrUpdateDTO.getTagIds());

        // 批量新增客户联系人
        batchInsertCustomerContact(customerAddOrUpdateDTO.getContactList(), userId, username, id);
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
    public Pair<Integer, List<CustomerSimpleListVO>> list(Long segmentId, Integer seaType, Integer pageNum, Integer pageSize) {
        try {
            int count = customerMapper.count(seaType);
            if (count <= 0) {
                return Pair.of(count, new ArrayList<>());
            }

            int offset = (pageNum - 1) * pageSize;
            int limit = pageSize;
            List<CustomerSimpleListVO> customerSimpleListVOList = customerMapper.selectCustomerPage(segmentId, seaType, offset, limit);
            if (customerSimpleListVOList == null || customerSimpleListVOList.isEmpty()) {
                return Pair.of(count, new ArrayList<>());
            }

            return Pair.of(count, customerSimpleListVOList);
        } catch (Exception e) {
            log.error("查询客户列表（分页）异常：{}", e);
            return Pair.of(0, new ArrayList<>());
        }
    }

    @Override
    public boolean moveCustomerToPacket(CustomerPacketMoveDTO customerPacketMoveDTO) {
        customerMapper.moveCustomerToPacket(customerPacketMoveDTO.getId(), customerPacketMoveDTO.getPacketId());
        return false;
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
    public List<PublicleadsGroupsListVO> publicleadsGroupsList(Long customerId) {
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

}
