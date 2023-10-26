package com.ruoyi.customer.mapper;

import java.util.List;
import com.ruoyi.customer.domain.FollowUpTemplates;
import org.apache.ibatis.annotations.Param;

/**
 * 写跟进模板Mapper接口
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
public interface FollowUpTemplatesMapper 
{
    /**
     * 查询写跟进模板
     * 
     * @param id 写跟进模板主键
     * @return 写跟进模板
     */
    public FollowUpTemplates selectFollowUpTemplatesById(Long id);

    /**
     * 查询写跟进模板列表
     * 
     * @param followUpTemplates 写跟进模板
     * @return 写跟进模板集合
     */
    public List<FollowUpTemplates> selectFollowUpTemplatesList(FollowUpTemplates followUpTemplates);

    /**
     * 新增写跟进模板
     * 
     * @param followUpTemplates 写跟进模板
     * @return 结果
     */
    public int insertFollowUpTemplates(FollowUpTemplates followUpTemplates);

    /**
     * 修改写跟进模板
     * 
     * @param followUpTemplates 写跟进模板
     * @return 结果
     */
    public int updateFollowUpTemplates(FollowUpTemplates followUpTemplates);

    /**
     * 删除写跟进模板
     * 
     * @param id 写跟进模板主键
     * @return 结果
     */
    public int deleteFollowUpTemplatesById(@Param("id") Long id, @Param("updateId") Long updateId, @Param("updateBy") String updateBy);

    /**
     * 批量删除写跟进模板
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFollowUpTemplatesByIds(Long[] ids);
}
