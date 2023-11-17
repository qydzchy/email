package com.ruoyi.customer.mapper;

import java.util.List;
import com.ruoyi.customer.domain.Tag;
import org.apache.ibatis.annotations.Param;

/**
 * 客户标签Mapper接口
 * 
 * @author tangJM.
 * @date 2023-11-17
 */
public interface TagMapper 
{
    /**
     * 查询客户标签
     * 
     * @param id 客户标签主键
     * @return 客户标签
     */
    public Tag selectTagById(Long id);

    /**
     * 查询客户标签列表
     * 
     * @param tag 客户标签
     * @return 客户标签集合
     */
    public List<Tag> selectTagList(Tag tag);

    /**
     * 新增客户标签
     * 
     * @param tag 客户标签
     * @return 结果
     */
    public int insertTag(Tag tag);

    /**
     * 修改客户标签
     * 
     * @param tag 客户标签
     * @return 结果
     */
    public int updateTag(Tag tag);

    /**
     * 删除客户标签
     * 
     * @param id 客户标签主键
     * @return 结果
     */
    public int deleteTagById(@Param("id") Long id, @Param("updateId") Long updateId, @Param("updateBy") String updateBy);

    /**
     * 批量删除客户标签
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTagByIds(Long[] ids);
}
