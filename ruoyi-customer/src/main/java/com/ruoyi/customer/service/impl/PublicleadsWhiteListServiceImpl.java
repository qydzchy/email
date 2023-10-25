package com.ruoyi.customer.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.PublicleadsWhiteListMapper;
import com.ruoyi.customer.domain.PublicleadsWhiteList;
import com.ruoyi.customer.service.IPublicleadsWhiteListService;

/**
 * 移入公海白名单Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
@Service
public class PublicleadsWhiteListServiceImpl implements IPublicleadsWhiteListService 
{
    @Autowired
    private PublicleadsWhiteListMapper publicleadsWhiteListMapper;

    /**
     * 查询移入公海白名单
     * 
     * @param id 移入公海白名单主键
     * @return 移入公海白名单
     */
    @Override
    public PublicleadsWhiteList selectPublicleadsWhiteListById(Long id)
    {
        return publicleadsWhiteListMapper.selectPublicleadsWhiteListById(id);
    }

    /**
     * 查询移入公海白名单列表
     * 
     * @param publicleadsWhiteList 移入公海白名单
     * @return 移入公海白名单
     */
    @Override
    public List<PublicleadsWhiteList> selectPublicleadsWhiteListList(PublicleadsWhiteList publicleadsWhiteList)
    {
        return publicleadsWhiteListMapper.selectPublicleadsWhiteListList(publicleadsWhiteList);
    }

    /**
     * 新增移入公海白名单
     * 
     * @param publicleadsWhiteList 移入公海白名单
     * @return 结果
     */
    @Override
    public int insertPublicleadsWhiteList(PublicleadsWhiteList publicleadsWhiteList)
    {
        publicleadsWhiteList.setCreateTime(DateUtils.getNowDate());
        return publicleadsWhiteListMapper.insertPublicleadsWhiteList(publicleadsWhiteList);
    }

    /**
     * 修改移入公海白名单
     * 
     * @param publicleadsWhiteList 移入公海白名单
     * @return 结果
     */
    @Override
    public int updatePublicleadsWhiteList(PublicleadsWhiteList publicleadsWhiteList)
    {
        publicleadsWhiteList.setUpdateTime(DateUtils.getNowDate());
        return publicleadsWhiteListMapper.updatePublicleadsWhiteList(publicleadsWhiteList);
    }

    /**
     * 批量删除移入公海白名单
     * 
     * @param ids 需要删除的移入公海白名单主键
     * @return 结果
     */
    @Override
    public int deletePublicleadsWhiteListByIds(Long[] ids)
    {
        return publicleadsWhiteListMapper.deletePublicleadsWhiteListByIds(ids);
    }

    /**
     * 删除移入公海白名单信息
     * 
     * @param id 移入公海白名单主键
     * @return 结果
     */
    @Override
    public int deletePublicleadsWhiteListById(Long id)
    {
        return publicleadsWhiteListMapper.deletePublicleadsWhiteListById(id);
    }
}
