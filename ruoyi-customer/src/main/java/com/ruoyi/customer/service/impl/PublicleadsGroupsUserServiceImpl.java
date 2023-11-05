package com.ruoyi.customer.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.PublicleadsGroupsUserMapper;
import com.ruoyi.customer.domain.PublicleadsGroupsUser;
import com.ruoyi.customer.service.IPublicleadsGroupsUserService;

/**
 * 公海分组成员Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-11-05
 */
@Service
public class PublicleadsGroupsUserServiceImpl implements IPublicleadsGroupsUserService 
{
    @Autowired
    private PublicleadsGroupsUserMapper publicleadsGroupsUserMapper;

    /**
     * 查询公海分组成员
     * 
     * @param id 公海分组成员主键
     * @return 公海分组成员
     */
    @Override
    public PublicleadsGroupsUser selectPublicleadsGroupsUserById(Long id)
    {
        return publicleadsGroupsUserMapper.selectPublicleadsGroupsUserById(id);
    }

    /**
     * 查询公海分组成员列表
     * 
     * @param publicleadsGroupsUser 公海分组成员
     * @return 公海分组成员
     */
    @Override
    public List<PublicleadsGroupsUser> selectPublicleadsGroupsUserList(PublicleadsGroupsUser publicleadsGroupsUser)
    {
        return publicleadsGroupsUserMapper.selectPublicleadsGroupsUserList(publicleadsGroupsUser);
    }

    /**
     * 新增公海分组成员
     * 
     * @param publicleadsGroupsUser 公海分组成员
     * @return 结果
     */
    @Override
    public int insertPublicleadsGroupsUser(PublicleadsGroupsUser publicleadsGroupsUser)
    {
        return publicleadsGroupsUserMapper.insertPublicleadsGroupsUser(publicleadsGroupsUser);
    }

    /**
     * 修改公海分组成员
     * 
     * @param publicleadsGroupsUser 公海分组成员
     * @return 结果
     */
    @Override
    public int updatePublicleadsGroupsUser(PublicleadsGroupsUser publicleadsGroupsUser)
    {
        return publicleadsGroupsUserMapper.updatePublicleadsGroupsUser(publicleadsGroupsUser);
    }

    /**
     * 批量删除公海分组成员
     * 
     * @param ids 需要删除的公海分组成员主键
     * @return 结果
     */
    @Override
    public int deletePublicleadsGroupsUserByIds(Long[] ids)
    {
        return publicleadsGroupsUserMapper.deletePublicleadsGroupsUserByIds(ids);
    }

    /**
     * 删除公海分组成员信息
     * 
     * @param id 公海分组成员主键
     * @return 结果
     */
    @Override
    public int deletePublicleadsGroupsUserById(Long id)
    {
        return publicleadsGroupsUserMapper.deletePublicleadsGroupsUserById(id);
    }
}
