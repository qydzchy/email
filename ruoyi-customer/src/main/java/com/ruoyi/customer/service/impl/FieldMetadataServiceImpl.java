package com.ruoyi.customer.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.FieldMetadataMapper;
import com.ruoyi.customer.domain.FieldMetadata;
import com.ruoyi.customer.service.IFieldMetadataService;

import javax.annotation.Resource;

/**
 * 客户字段元数据Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-10-28
 */
@Service
public class FieldMetadataServiceImpl implements IFieldMetadataService 
{
    @Resource
    private FieldMetadataMapper fieldMetadataMapper;

    /**
     * 查询客户字段元数据
     * 
     * @param id 客户字段元数据主键
     * @return 客户字段元数据
     */
    @Override
    public FieldMetadata selectFieldMetadataById(Long id)
    {
        return fieldMetadataMapper.selectFieldMetadataById(id);
    }

    /**
     * 查询客户字段元数据列表
     * 
     * @param fieldMetadata 客户字段元数据
     * @return 客户字段元数据
     */
    @Override
    public List<FieldMetadata> selectFieldMetadataList(FieldMetadata fieldMetadata)
    {
        return fieldMetadataMapper.selectFieldMetadataList(fieldMetadata);
    }

    /**
     * 新增客户字段元数据
     * 
     * @param fieldMetadata 客户字段元数据
     * @return 结果
     */
    @Override
    public int insertFieldMetadata(FieldMetadata fieldMetadata)
    {
        fieldMetadata.setCreateTime(DateUtils.getNowDate());
        return fieldMetadataMapper.insertFieldMetadata(fieldMetadata);
    }

    /**
     * 修改客户字段元数据
     * 
     * @param fieldMetadata 客户字段元数据
     * @return 结果
     */
    @Override
    public int updateFieldMetadata(FieldMetadata fieldMetadata)
    {
        fieldMetadata.setUpdateTime(DateUtils.getNowDate());
        return fieldMetadataMapper.updateFieldMetadata(fieldMetadata);
    }

    /**
     * 批量删除客户字段元数据
     * 
     * @param ids 需要删除的客户字段元数据主键
     * @return 结果
     */
    @Override
    public int deleteFieldMetadataByIds(Long[] ids)
    {
        return fieldMetadataMapper.deleteFieldMetadataByIds(ids);
    }

    /**
     * 删除客户字段元数据信息
     * 
     * @param id 客户字段元数据主键
     * @return 结果
     */
    @Override
    public int deleteFieldMetadataById(Long id)
    {
        return fieldMetadataMapper.deleteFieldMetadataById(id);
    }
}
