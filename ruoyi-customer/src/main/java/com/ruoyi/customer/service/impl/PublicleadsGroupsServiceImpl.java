package com.ruoyi.customer.service.impl;

import java.util.ArrayList;
import java.util.List;

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
        List<PublicleadsGroupsListVO> publicleadsGroupsListVOList = new ArrayList<>();
        for (PublicleadsGroups publicleadsGroups : publicleadsGroupsList) {
            PublicleadsGroupsListVO publicleadsGroupsVO = new PublicleadsGroupsListVO();
            publicleadsGroupsVO.setName(publicleadsGroups.getName());
            publicleadsGroupsVO.setUserIds(publicleadsGroups.getUserIds());
            publicleadsGroupsListVOList.add(publicleadsGroupsVO);
        }

        return publicleadsGroupsListVOList;
    }
}
