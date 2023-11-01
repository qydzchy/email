package com.ruoyi.customer.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.customer.MetadataColumnAppTypeEnum;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.customer.domain.DetailsContact;
import com.ruoyi.customer.domain.MetadataColumn;
import com.ruoyi.customer.domain.dto.DetailsAddOrUpdateDTO;
import com.ruoyi.customer.domain.dto.DetailsContactAddOrUpdateDTO;
import com.ruoyi.customer.domain.vo.CustomerSimpleListVO;
import com.ruoyi.customer.domain.vo.MetadataColumnListVO;
import com.ruoyi.customer.mapper.DetailsContactMapper;
import com.ruoyi.customer.mapper.MetadataColumnMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.DetailsMapper;
import com.ruoyi.customer.domain.Details;
import com.ruoyi.customer.service.IDetailsService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 客户详情Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-10-31
 */
@Slf4j
@Service
public class DetailsServiceImpl implements IDetailsService 
{
    @Resource
    private DetailsMapper detailsMapper;

    @Resource
    private DetailsContactMapper detailsContactMapper;

    @Resource
    private MetadataColumnMapper metadataColumnMapper;

    /**
     * 查询客户详情
     * 
     * @param id 客户详情主键
     * @return 客户详情
     */
    @Override
    public Details selectDetailsById(Long id)
    {
        return detailsMapper.selectDetailsById(id);
    }

    /**
     * 查询客户详情列表
     * 
     * @param details 客户详情
     * @return 客户详情
     */
    @Override
    public List<Details> selectDetailsList(Details details)
    {
        return detailsMapper.selectDetailsList(details);
    }

    /**
     * 新增客户详情
     * 
     * @param detailsAddOrUpdateDTO 客户详情
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insertDetails(DetailsAddOrUpdateDTO detailsAddOrUpdateDTO)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        Details details = new Details();
        BeanUtils.copyProperties(detailsAddOrUpdateDTO, details);

        details.setCreateId(userId);
        details.setCreateBy(username);
        details.setCreateTime(DateUtils.getNowDate());
        details.setUpdateId(userId);
        details.setUpdateBy(username);
        details.setUpdateTime(DateUtils.getNowDate());
        Long id = detailsMapper.insertDetails(details);

        // 批量新增客户联系人
        batchInsertDetailsContact(detailsAddOrUpdateDTO.getContactList(), userId, username, id);
        return true;
    }

    /**
     * 批量新增客户联系人
     * @param contactDtoList
     * @param userId
     * @param username
     * @param id
     */
    private void batchInsertDetailsContact(List<DetailsContactAddOrUpdateDTO> contactDtoList, Long userId, String username, Long id) {
        List<DetailsContact> detailsContactList = new ArrayList<>();
        for (DetailsContactAddOrUpdateDTO detailsContactAddOrUpdateDTO : contactDtoList) {
            DetailsContact detailsContact = new DetailsContact();
            BeanUtils.copyProperties(detailsContactAddOrUpdateDTO, detailsContact);
            detailsContact.setCustomerDetailsId(id);
            detailsContact.setCreateId(userId);
            detailsContact.setCreateBy(username);
            detailsContact.setCreateTime(DateUtils.getNowDate());
            detailsContact.setUpdateId(userId);
            detailsContact.setUpdateBy(username);
            detailsContact.setUpdateTime(DateUtils.getNowDate());
            detailsContactList.add(detailsContact);
        }

        // 批量新增客户联系人
        detailsContactMapper.batchInsertDetailsContact(detailsContactList);
    }

    /**
     * 修改客户详情
     * 
     * @param detailsAddOrUpdateDTO 客户详情
     * @return 结果
     */
    @Override
    public int updateDetails(DetailsAddOrUpdateDTO detailsAddOrUpdateDTO)
    {

        Long id = detailsAddOrUpdateDTO.getId();
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        Details details = new Details();
        BeanUtils.copyProperties(detailsAddOrUpdateDTO, details);

        details.setUpdateId(userId);
        details.setUpdateBy(username);
        details.setUpdateTime(DateUtils.getNowDate());
        detailsMapper.updateDetails(details);

        // 删除该客户现有的客户联系人
        detailsContactMapper.deleteDetailsContactByCustomerDetailsId(id, userId, username);

        // 批量新增客户联系人
        batchInsertDetailsContact(detailsAddOrUpdateDTO.getContactList(), userId, username, id);
        return detailsMapper.updateDetails(details);
    }

    /**
     * 批量删除客户详情
     * 
     * @param ids 需要删除的客户详情主键
     * @return 结果
     */
    @Override
    public int deleteDetailsByIds(Long[] ids)
    {
        return detailsMapper.deleteDetailsByIds(ids);
    }

    /**
     * 删除客户详情信息
     * 
     * @param id 客户详情主键
     * @return 结果
     */
    @Override
    public int deleteDetailsById(Long id)
    {
        return detailsMapper.deleteDetailsById(id);
    }

    /**
     * 客户列表（分页）
     * @param seaType
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Map<String, Object> list(Integer seaType, Integer pageNum, Integer pageSize) {
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

            int count = detailsMapper.count(seaType);
            result.put("total", count);
            if (count <= 0) {
                result.put("data", new ArrayList<>());
                return result;
            }

            int offset = (pageNum - 1) * pageSize;
            int limit = pageSize;
            List<CustomerSimpleListVO> customerSimpleListVOList = detailsMapper.selectCustomerDetailsPage(seaType, offset, limit);
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
