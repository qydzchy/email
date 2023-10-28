package com.ruoyi.customer.service;

import java.util.List;
import com.ruoyi.customer.domain.FieldMetadata;

/**
 * 客户字段元数据Service接口
 * 
 * @author tangJM.
 * @date 2023-10-28
 */
public interface IFieldMetadataService 
{
    /**
     * 查询客户字段元数据
     * 
     * @param id 客户字段元数据主键
     * @return 客户字段元数据
     */
    public FieldMetadata selectFieldMetadataById(Long id);

    /**
     * 查询客户字段元数据列表
     * 
     * @param fieldMetadata 客户字段元数据
     * @return 客户字段元数据集合
     */
    public List<FieldMetadata> selectFieldMetadataList(FieldMetadata fieldMetadata);

    /**
     * 新增客户字段元数据
     * 
     * @param fieldMetadata 客户字段元数据
     * @return 结果
     */
    public int insertFieldMetadata(FieldMetadata fieldMetadata);

    /**
     * 修改客户字段元数据
     * 
     * @param fieldMetadata 客户字段元数据
     * @return 结果
     */
    public int updateFieldMetadata(FieldMetadata fieldMetadata);

    /**
     * 批量删除客户字段元数据
     * 
     * @param ids 需要删除的客户字段元数据主键集合
     * @return 结果
     */
    public int deleteFieldMetadataByIds(Long[] ids);

    /**
     * 删除客户字段元数据信息
     * 
     * @param id 客户字段元数据主键
     * @return 结果
     */
    public int deleteFieldMetadataById(Long id);
}
