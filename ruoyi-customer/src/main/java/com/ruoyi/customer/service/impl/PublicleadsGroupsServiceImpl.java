package com.ruoyi.customer.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.customer.domain.vo.PublicleadsGroupsListVO;
import com.ruoyi.system.domain.vo.UserInfoVO;
import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.PublicleadsGroupsMapper;
import com.ruoyi.customer.domain.PublicleadsGroups;
import com.ruoyi.customer.service.IPublicleadsGroupsService;

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
    private SysUserMapper sysUserMapper;

    /**
     * 新增公海分组
     * 
     * @param publicleadsGroups 公海分组
     * @return 结果
     */
    @Override
    public int insertPublicleadsGroups(PublicleadsGroups publicleadsGroups)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        publicleadsGroups.setDefaultGroupFlag(false);
        publicleadsGroups.setCreateId(userId);
        publicleadsGroups.setCreateBy(username);
        publicleadsGroups.setCreateTime(DateUtils.getNowDate());
        publicleadsGroups.setUpdateId(userId);
        publicleadsGroups.setUpdateBy(username);
        publicleadsGroups.setUpdateTime(DateUtils.getNowDate());
        return publicleadsGroupsMapper.insertPublicleadsGroups(publicleadsGroups);
    }

    /**
     * 修改公海分组
     * 
     * @param publicleadsGroups 公海分组
     * @return 结果
     */
    @Override
    public int updatePublicleadsGroups(PublicleadsGroups publicleadsGroups)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        publicleadsGroups.setUpdateId(userId);
        publicleadsGroups.setUpdateBy(username);
        publicleadsGroups.setUpdateTime(DateUtils.getNowDate());
        return publicleadsGroupsMapper.updatePublicleadsGroups(publicleadsGroups);
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
        List<PublicleadsGroups> publicleadsGroupsList = publicleadsGroupsMapper.selectPublicleadsGroupsList(new PublicleadsGroups());

        List<Long> userIdList = new ArrayList<>();
        publicleadsGroupsList.stream().forEach(publicleadsGroups -> {
            String userIds = publicleadsGroups.getUserIds();
            String[] userIdArray = userIds.split(",");
            for (String userId : userIdArray) {
                userIdList.add(Long.valueOf(userId));
            }
        });

        Map<Long, UserInfoVO> userInfoMap = new HashMap<>();
        if (!userIdList.isEmpty()) {
            List<UserInfoVO> userInfoList = sysUserMapper.listByIds(userIdList);
            userInfoMap = userInfoList.stream().collect(Collectors.toMap(userInfo -> userInfo.getUserId(), userInfo -> userInfo));
        }

        List<PublicleadsGroupsListVO> publicleadsGroupsVOList = new ArrayList<>();
        for (PublicleadsGroups publicleadsGroups : publicleadsGroupsList) {
            PublicleadsGroupsListVO publicleadsGroupsListVO = new PublicleadsGroupsListVO();
            publicleadsGroupsListVO.setId(publicleadsGroups.getId());
            publicleadsGroupsListVO.setName(publicleadsGroups.getName());
            publicleadsGroupsListVO.setDefaultGroupFlag(publicleadsGroups.getDefaultGroupFlag());

            List<UserInfoVO> userInfoList = new ArrayList<>();
            String userIds = publicleadsGroups.getUserIds();
            String[] userIdArray = userIds.split(",");
            for (String userId : userIdArray) {
                UserInfoVO userInfoVO = userInfoMap.get(Long.valueOf(userId));
                userInfoList.add(userInfoVO);
            }
            publicleadsGroupsListVO.setUserInfoList(userInfoList);
            publicleadsGroupsVOList.add(publicleadsGroupsListVO);
        }

        return publicleadsGroupsVOList;
    }
}
