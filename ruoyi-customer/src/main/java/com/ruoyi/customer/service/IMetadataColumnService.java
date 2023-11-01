package com.ruoyi.customer.service;

import java.util.List;
import com.ruoyi.customer.domain.MetadataColumn;

/**
 * 客户元数据列Service接口
 * 
 * @author tangJM.
 * @date 2023-11-01
 */
public interface IMetadataColumnService 
{
    /**
     * 查询客户元数据列
     * 
     * @param id 客户元数据列主键
     * @return 客户元数据列
     */
    public MetadataColumn selectMetadataColumnById(Long id);

    /**
     * 查询客户元数据列列表
     * 
     * @param metadataColumn 客户元数据列
     * @return 客户元数据列集合
     */
    public List<MetadataColumn> selectMetadataColumnList(MetadataColumn metadataColumn);

    /**
     * 新增客户元数据列
     * 
     * @param metadataColumn 客户元数据列
     * @return 结果
     */
    public int insertMetadataColumn(MetadataColumn metadataColumn);

    /**
     * 修改客户元数据列
     * 
     * @param metadataColumn 客户元数据列
     * @return 结果
     */
    public int updateMetadataColumn(MetadataColumn metadataColumn);

    /**
     * 批量删除客户元数据列
     * 
     * @param ids 需要删除的客户元数据列主键集合
     * @return 结果
     */
    public int deleteMetadataColumnByIds(Long[] ids);

    /**
     * 删除客户元数据列信息
     * 
     * @param id 客户元数据列主键
     * @return 结果
     */
    public int deleteMetadataColumnById(Long id);
}
