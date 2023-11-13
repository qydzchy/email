package com.ruoyi.customer.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.customer.domain.PublicleadsGroupsUser;
import com.ruoyi.customer.domain.bo.PublicleadsGroupsListBO;
import com.ruoyi.customer.domain.dto.PublicleadsGroupAddOrUpdateDTO;
import com.ruoyi.customer.domain.vo.PublicleadsGroupsListVO;
import com.ruoyi.customer.mapper.PublicleadsGroupsUserMapper;
import com.ruoyi.system.domain.vo.UserInfoVO;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.PublicleadsGroupsMapper;
import com.ruoyi.customer.domain.PublicleadsGroups;
import com.ruoyi.customer.service.IPublicleadsGroupsService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 公海分组Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
@Service
public class PublicleadsGroupsServiceImpl implements IPublicleadsGroupsService 
{
    @Resource
    private PublicleadsGroupsMapper publicleadsGroupsMapper;
    @Resource
    private PublicleadsGroupsUserMapper publicleadsGroupsUserMapper;

    /**
     * 新增公海分组
     * 
     * @param publicleadsGroupAddOrUpdateDTO 公海分组
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insertPublicleadsGroups(PublicleadsGroupAddOrUpdateDTO publicleadsGroupAddOrUpdateDTO)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        PublicleadsGroups publicleadsGroups = new PublicleadsGroups();
        publicleadsGroups.setName(publicleadsGroupAddOrUpdateDTO.getName());
        publicleadsGroups.setDefaultGroupFlag(false);
        publicleadsGroups.setCreateId(userId);
        publicleadsGroups.setCreateBy(username);
        publicleadsGroups.setCreateTime(DateUtils.getNowDate());
        publicleadsGroups.setUpdateId(userId);
        publicleadsGroups.setUpdateBy(username);
        publicleadsGroups.setUpdateTime(DateUtils.getNowDate());

        publicleadsGroupsMapper.insertPublicleadsGroups(publicleadsGroups);
        Long id = publicleadsGroups.getId();

        String userIds = publicleadsGroupAddOrUpdateDTO.getUserIds();
        String[] userIdArray = userIds.split(",");
        List<PublicleadsGroupsUser> publicleadsGroupsUserList = new ArrayList<>();
        for (String userIdStr : userIdArray) {
            Long userIdLong = Long.valueOf(userIdStr);
            PublicleadsGroupsUser publicleadsGroupsUser = new PublicleadsGroupsUser();
            publicleadsGroupsUser.setPublicleadsGroupsId(id);
            publicleadsGroupsUser.setUserId(userIdLong);
            publicleadsGroupsUserList.add(publicleadsGroupsUser);
        }

        if (!publicleadsGroupsUserList.isEmpty()) {
            // 批量插入分组成员
            publicleadsGroupsUserMapper.batchInsertPublicleadsGroupsUser(publicleadsGroupsUserList);
        }
        return true;
    }

    /**
     * 修改公海分组
     * 
     * @param publicleadsGroupAddOrUpdateDTO 公海分组
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updatePublicleadsGroups(PublicleadsGroupAddOrUpdateDTO publicleadsGroupAddOrUpdateDTO)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        PublicleadsGroups publicleadsGroups = new PublicleadsGroups();
        publicleadsGroups.setId(publicleadsGroupAddOrUpdateDTO.getId());
        publicleadsGroups.setName(publicleadsGroupAddOrUpdateDTO.getName());
        publicleadsGroups.setUpdateId(userId);
        publicleadsGroups.setUpdateBy(username);
        publicleadsGroups.setUpdateTime(DateUtils.getNowDate());
        publicleadsGroupsMapper.updatePublicleadsGroups(publicleadsGroups);

        // 删除分组成员
        publicleadsGroupsUserMapper.deletePublicleadsGroupsUserByPublicleadsGroupsId(publicleadsGroupAddOrUpdateDTO.getId());

        String userIds = publicleadsGroupAddOrUpdateDTO.getUserIds();
        String[] userIdArray = userIds.split(",");
        List<PublicleadsGroupsUser> publicleadsGroupsUserList = new ArrayList<>();
        for (String userIdStr : userIdArray) {
            Long userIdLong = Long.valueOf(userIdStr);
            PublicleadsGroupsUser publicleadsGroupsUser = new PublicleadsGroupsUser();
            publicleadsGroupsUser.setPublicleadsGroupsId(publicleadsGroupAddOrUpdateDTO.getId());
            publicleadsGroupsUser.setUserId(userIdLong);
            publicleadsGroupsUserList.add(publicleadsGroupsUser);
        }

        if (!publicleadsGroupsUserList.isEmpty()) {
            // 批量插入分组成员
            publicleadsGroupsUserMapper.batchInsertPublicleadsGroupsUser(publicleadsGroupsUserList);
        }
        return true;
    }

    /**
     * 删除公海分组信息
     * 
     * @param id 公海分组主键
     * @return 结果
     */
    @Override
    public int deletePublicleadsGroupsById(Long id)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        return publicleadsGroupsMapper.deletePublicleadsGroupsById(id, userId, username);
    }

    @Override
    public List<PublicleadsGroupsListVO> list() {
        List<PublicleadsGroupsListBO> publicleadsGroupsBOList = publicleadsGroupsMapper.list();

        Map<Long, List<PublicleadsGroupsListBO>> publicleadsGroupsIdMap = publicleadsGroupsBOList.stream().collect(Collectors.groupingBy(PublicleadsGroupsListBO::getId));

        List<PublicleadsGroupsListVO> publicleadsGroupsVOList = new ArrayList<>();
        publicleadsGroupsIdMap.forEach((publicleadsGroupsId, publicleadsGroupsBOGroupingList) -> {

            PublicleadsGroupsListBO publicleadsGroupsBO = publicleadsGroupsBOGroupingList.get(0);
            PublicleadsGroupsListVO publicleadsGroupsVO = new PublicleadsGroupsListVO();
            publicleadsGroupsVO.setId(publicleadsGroupsId);
            publicleadsGroupsVO.setName(publicleadsGroupsBO.getName());
            publicleadsGroupsVO.setDefaultGroupFlag(publicleadsGroupsBO.getDefaultGroupFlag());
            List<UserInfoVO> userInfoVOList = new ArrayList<>();
            for (PublicleadsGroupsListBO publicleadsGroupsListBO : publicleadsGroupsBOGroupingList) {
                UserInfoVO userInfoVO = new UserInfoVO();
                userInfoVO.setUserId(publicleadsGroupsListBO.getUserId());
                userInfoVO.setUserName(publicleadsGroupsListBO.getUserName());
                userInfoVO.setNickName(publicleadsGroupsListBO.getNickName());
                userInfoVOList.add(userInfoVO);
            }

            publicleadsGroupsVO.setUserInfoList(userInfoVOList);
            publicleadsGroupsVOList.add(publicleadsGroupsVO);
        });

        return publicleadsGroupsVOList;
    }

    @Override
    public PublicleadsGroupsListVO selectPublicleadsGroupsByCustomerId(Long id) {
        List<PublicleadsGroupsListBO> publicleadsGroupsBOList = publicleadsGroupsMapper.selectPublicleadsGroupsByCustomerId(id);

        PublicleadsGroupsListBO publicleadsGroupsBO = publicleadsGroupsBOList.get(0);
        PublicleadsGroupsListVO publicleadsGroupsVO = new PublicleadsGroupsListVO();
        publicleadsGroupsVO.setId(publicleadsGroupsBO.getId());
        publicleadsGroupsVO.setName(publicleadsGroupsBO.getName());
        publicleadsGroupsVO.setDefaultGroupFlag(publicleadsGroupsBO.getDefaultGroupFlag());
        List<UserInfoVO> userInfoVOList = new ArrayList<>();
        for (PublicleadsGroupsListBO publicleadsGroupsListBO : publicleadsGroupsBOList) {
            UserInfoVO userInfoVO = new UserInfoVO();
            userInfoVO.setUserId(publicleadsGroupsListBO.getUserId());
            userInfoVO.setUserName(publicleadsGroupsListBO.getUserName());
            userInfoVO.setNickName(publicleadsGroupsListBO.getNickName());
            userInfoVOList.add(userInfoVO);
        }

        publicleadsGroupsVO.setUserInfoList(userInfoVOList);
        return publicleadsGroupsVO;
    }
}
