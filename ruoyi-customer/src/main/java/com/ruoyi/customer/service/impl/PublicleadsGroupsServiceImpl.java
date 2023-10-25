package com.ruoyi.customer.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.PublicleadsGroupsMapper;
import com.ruoyi.customer.domain.PublicleadsGroups;
import com.ruoyi.customer.service.IPublicleadsGroupsService;

/**
 * 公海分组Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
@Service
public class PublicleadsGroupsServiceImpl implements IPublicleadsGroupsService 
{
    @Autowired
    private PublicleadsGroupsMapper publicleadsGroupsMapper;

    /**
     * 查询公海分组
     * 
     * @param id 公海分组主键
     * @return 公海分组
     */
    @Override
    public PublicleadsGroups selectPublicleadsGroupsById(Long id)
    {
        return publicleadsGroupsMapper.selectPublicleadsGroupsById(id);
    }

    /**
     * 查询公海分组列表
     * 
     * @param publicleadsGroups 公海分组
     * @return 公海分组
     */
    @Override
    public List<PublicleadsGroups> selectPublicleadsGroupsList(PublicleadsGroups publicleadsGroups)
    {
        return publicleadsGroupsMapper.selectPublicleadsGroupsList(publicleadsGroups);
    }

    /**
     * 新增公海分组
     * 
     * @param publicleadsGroups 公海分组
     * @return 结果
     */
    @Override
    public int insertPublicleadsGroups(PublicleadsGroups publicleadsGroups)
    {
        publicleadsGroups.setCreateTime(DateUtils.getNowDate());
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
        publicleadsGroups.setUpdateTime(DateUtils.getNowDate());
        return publicleadsGroupsMapper.updatePublicleadsGroups(publicleadsGroups);
    }

    /**
     * 批量删除公海分组
     * 
     * @param ids 需要删除的公海分组主键
     * @return 结果
     */
    @Override
    public int deletePublicleadsGroupsByIds(Long[] ids)
    {
        return publicleadsGroupsMapper.deletePublicleadsGroupsByIds(ids);
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
        return publicleadsGroupsMapper.deletePublicleadsGroupsById(id);
    }
}
