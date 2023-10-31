package com.ruoyi.customer.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.customer.domain.DetailsContact;
import com.ruoyi.customer.domain.dto.DetailsAddOrUpdateDTO;
import com.ruoyi.customer.domain.dto.DetailsContactAddOrUpdateDTO;
import com.ruoyi.customer.mapper.DetailsContactMapper;
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
@Service
public class DetailsServiceImpl implements IDetailsService 
{
    @Resource
    private DetailsMapper detailsMapper;

    @Resource
    private DetailsContactMapper detailsContactMapper;

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

    @Override
    public List<Details> list(Integer seaType, Integer pageNum, Integer pageSize) {
        /*int count = detailsMapper.count(seaType);
        if (count <= 0) {
            return Pair.of(0, new ArrayList<>());
        }

        int offset = (pageNum - 1) * pageSize;
        int limit = pageSize;
        List<EmailListVO> emailListVOList = detailsMapper.selectCustomerDetailsPage(offset, limit);
        if (emailListVOList == null || emailListVOList.isEmpty()) {
            return Pair.of(count, new ArrayList<>());
        }
*/
        return null;
    }
}
