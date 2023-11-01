package com.ruoyi.customer.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.MetadataColumnMapper;
import com.ruoyi.customer.domain.MetadataColumn;
import com.ruoyi.customer.service.IMetadataColumnService;

import javax.annotation.Resource;

/**
 * 客户元数据列Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-11-01
 */
@Service
public class MetadataColumnServiceImpl implements IMetadataColumnService 
{
    @Resource
    private MetadataColumnMapper metadataColumnMapper;

    /**
     * 查询客户元数据列
     * 
     * @param id 客户元数据列主键
     * @return 客户元数据列
     */
    @Override
    public MetadataColumn selectMetadataColumnById(Long id)
    {
        return metadataColumnMapper.selectMetadataColumnById(id);
    }

    /**
     * 查询客户元数据列列表
     * 
     * @param metadataColumn 客户元数据列
     * @return 客户元数据列
     */
    @Override
    public List<MetadataColumn> selectMetadataColumnList(MetadataColumn metadataColumn)
    {
        return metadataColumnMapper.selectMetadataColumnList(metadataColumn);
    }

    /**
     * 新增客户元数据列
     * 
     * @param metadataColumn 客户元数据列
     * @return 结果
     */
    @Override
    public int insertMetadataColumn(MetadataColumn metadataColumn)
    {
        metadataColumn.setCreateTime(DateUtils.getNowDate());
        return metadataColumnMapper.insertMetadataColumn(metadataColumn);
    }

    /**
     * 修改客户元数据列
     * 
     * @param metadataColumn 客户元数据列
     * @return 结果
     */
    @Override
    public int updateMetadataColumn(MetadataColumn metadataColumn)
    {
        metadataColumn.setUpdateTime(DateUtils.getNowDate());
        return metadataColumnMapper.updateMetadataColumn(metadataColumn);
    }

    /**
     * 批量删除客户元数据列
     * 
     * @param ids 需要删除的客户元数据列主键
     * @return 结果
     */
    @Override
    public int deleteMetadataColumnByIds(Long[] ids)
    {
        return metadataColumnMapper.deleteMetadataColumnByIds(ids);
    }

    /**
     * 删除客户元数据列信息
     * 
     * @param id 客户元数据列主键
     * @return 结果
     */
    @Override
    public int deleteMetadataColumnById(Long id)
    {
        return metadataColumnMapper.deleteMetadataColumnById(id);
    }
}
