package com.ruoyi.customer.service;

import java.util.List;
import com.ruoyi.customer.domain.Tag;
import com.ruoyi.customer.domain.vo.TagListVO;

/**
 * 客户标签Service接口
 * 
 * @author tangJM.
 * @date 2023-11-17
 */
public interface ITagService 
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
     * 批量删除客户标签
     * 
     * @param ids 需要删除的客户标签主键集合
     * @return 结果
     */
    public int deleteTagByIds(Long[] ids);

    /**
     * 删除客户标签信息
     * 
     * @param id 客户标签主键
     * @return 结果
     */
    public int deleteTagById(Long id);

    /**
     * 标签列表
     * @param type
     * @return
     */
    List<TagListVO> list(Integer type);

    /**
     * 设为公司标签
     * @param id
     * @return
     */
    boolean setAsCompanyTag(Long id);
}
