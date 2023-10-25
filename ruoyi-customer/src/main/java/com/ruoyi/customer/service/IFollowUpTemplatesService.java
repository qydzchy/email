package com.ruoyi.customer.service;

import java.util.List;
import com.ruoyi.customer.domain.FollowUpTemplates;

/**
 * 写跟进模板Service接口
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
public interface IFollowUpTemplatesService 
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
     * 批量删除写跟进模板
     * 
     * @param ids 需要删除的写跟进模板主键集合
     * @return 结果
     */
    public int deleteFollowUpTemplatesByIds(Long[] ids);

    /**
     * 删除写跟进模板信息
     * 
     * @param id 写跟进模板主键
     * @return 结果
     */
    public int deleteFollowUpTemplatesById(Long id);
}
