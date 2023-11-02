package com.ruoyi.customer.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.customer.MetadataColumnAppTypeEnum;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.customer.domain.*;
import com.ruoyi.customer.domain.dto.CustomerAddOrUpdateDTO;
import com.ruoyi.customer.domain.dto.CustomerContactAddOrUpdateDTO;
import com.ruoyi.customer.domain.vo.CustomerSimpleListVO;
import com.ruoyi.customer.domain.vo.MetadataColumnListVO;
import com.ruoyi.customer.mapper.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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
    public Map<String, Object> list(Long segmentId, Integer seaType, Integer pageNum, Integer pageSize) {
        try {
            // 查询字段信息
            MetadataColumn metadataColumnParam = new MetadataColumn();
            metadataColumnParam.setAppType(MetadataColumnAppTypeEnum.CUSTOMER_LIST.getAppType());
            List<MetadataColumn> metadataColumnList = metadataColumnMapper.selectMetadataColumnList(metadataColumnParam);
            List<MetadataColumnListVO> metadataColumnListVOList = new ArrayList<>();
            metadataColumnList.stream().forEach(metadataColumn -> {
                MetadataColumnListVO metadataColumnListVO = new MetadataColumnListVO();
                metadataColumnListVO.setId(metadataColumn.getId());
                metadataColumnListVO.setColumnName(metadataColumn.getColumnName());
                metadataColumnListVO.setColumnAlias(metadataColumn.getColumnAlias());
                metadataColumnListVO.setColumnType(metadataColumn.getColumnType());
                metadataColumnListVOList.add(metadataColumnListVO);
            });

            Map<String, Object> result = new HashMap<>();
            result.put("column", metadataColumnParam);

            int count = customerMapper.count(seaType);
            result.put("total", count);
            if (count <= 0) {
                result.put("data", new ArrayList<>());
                return result;
            }

            int offset = (pageNum - 1) * pageSize;
            int limit = pageSize;
            List<CustomerSimpleListVO> customerSimpleListVOList = customerMapper.selectCustomerPage(segmentId, seaType, offset, limit);
            if (customerSimpleListVOList == null || customerSimpleListVOList.isEmpty()) {
                result.put("data", new ArrayList<>());
                return result;
            }

        } catch (Exception e) {
            log.error("查询客户列表（分页）异常：{}", e);
            return new HashMap<>();
        }

        return null;
    }
}
