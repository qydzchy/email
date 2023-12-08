package com.ruoyi.customer.service.impl;

import java.util.*;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.customer.domain.vo.PublicleadsGroupsListVO;
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

    /**
     * 新增公海分组
     * 
     * @param publicleadsGroups
     * @return 结果
     */
    @Override
    public boolean insertPublicleadsGroups(PublicleadsGroups publicleadsGroups)
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

        publicleadsGroupsMapper.insertPublicleadsGroups(publicleadsGroups);
        return true;
    }

    /**
     * 修改公海分组
     * 
     * @param publicleadsGroups
     * @return 结果
     */
    @Override
    public boolean updatePublicleadsGroups(PublicleadsGroups publicleadsGroups)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        publicleadsGroups.setUpdateId(userId);
        publicleadsGroups.setUpdateBy(username);
        publicleadsGroups.setUpdateTime(DateUtils.getNowDate());
        publicleadsGroupsMapper.updatePublicleadsGroups(publicleadsGroups);

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
        List<PublicleadsGroups> publicleadsGroupsList = publicleadsGroupsMapper.selectPublicleadsGroupsList(new PublicleadsGroups());
        List<PublicleadsGroupsListVO> publicleadsGroupsVOList = new ArrayList<>();
        for (PublicleadsGroups publicleadsGroups : publicleadsGroupsList) {
            PublicleadsGroupsListVO publicleadsGroupsVO = new PublicleadsGroupsListVO();

            publicleadsGroupsVO.setId(publicleadsGroups.getId());
            publicleadsGroupsVO.setName(publicleadsGroups.getName());
            publicleadsGroupsVO.setDefaultGroupFlag(publicleadsGroups.getDefaultGroupFlag());
            publicleadsGroupsVO.setGroupMember(publicleadsGroups.getGroupMember());
            publicleadsGroupsVOList.add(publicleadsGroupsVO);
        }

        return publicleadsGroupsVOList;
    }

    @Override
    public PublicleadsGroupsListVO selectPublicleadsGroupsByCustomerId(Long id) {
        List<PublicleadsGroupsListVO> publicleadsGroupsVOList = publicleadsGroupsMapper.selectPublicleadsGroupsByCustomerId(id);
        return publicleadsGroupsVOList != null && !publicleadsGroupsVOList.isEmpty() ? publicleadsGroupsVOList.get(0) : null;
    }
}
