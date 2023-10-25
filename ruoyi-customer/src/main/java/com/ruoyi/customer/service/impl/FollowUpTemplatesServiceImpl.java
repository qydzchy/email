package com.ruoyi.customer.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.FollowUpTemplatesMapper;
import com.ruoyi.customer.domain.FollowUpTemplates;
import com.ruoyi.customer.service.IFollowUpTemplatesService;

/**
 * 写跟进模板Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
@Service
public class FollowUpTemplatesServiceImpl implements IFollowUpTemplatesService 
{
    @Autowired
    private FollowUpTemplatesMapper followUpTemplatesMapper;

    /**
     * 查询写跟进模板
     * 
     * @param id 写跟进模板主键
     * @return 写跟进模板
     */
    @Override
    public FollowUpTemplates selectFollowUpTemplatesById(Long id)
    {
        return followUpTemplatesMapper.selectFollowUpTemplatesById(id);
    }

    /**
     * 查询写跟进模板列表
     * 
     * @param followUpTemplates 写跟进模板
     * @return 写跟进模板
     */
    @Override
    public List<FollowUpTemplates> selectFollowUpTemplatesList(FollowUpTemplates followUpTemplates)
    {
        return followUpTemplatesMapper.selectFollowUpTemplatesList(followUpTemplates);
    }

    /**
     * 新增写跟进模板
     * 
     * @param followUpTemplates 写跟进模板
     * @return 结果
     */
    @Override
    public int insertFollowUpTemplates(FollowUpTemplates followUpTemplates)
    {
        followUpTemplates.setCreateTime(DateUtils.getNowDate());
        return followUpTemplatesMapper.insertFollowUpTemplates(followUpTemplates);
    }

    /**
     * 修改写跟进模板
     * 
     * @param followUpTemplates 写跟进模板
     * @return 结果
     */
    @Override
    public int updateFollowUpTemplates(FollowUpTemplates followUpTemplates)
    {
        followUpTemplates.setUpdateTime(DateUtils.getNowDate());
        return followUpTemplatesMapper.updateFollowUpTemplates(followUpTemplates);
    }

    /**
     * 批量删除写跟进模板
     * 
     * @param ids 需要删除的写跟进模板主键
     * @return 结果
     */
    @Override
    public int deleteFollowUpTemplatesByIds(Long[] ids)
    {
        return followUpTemplatesMapper.deleteFollowUpTemplatesByIds(ids);
    }

    /**
     * 删除写跟进模板信息
     * 
     * @param id 写跟进模板主键
     * @return 结果
     */
    @Override
    public int deleteFollowUpTemplatesById(Long id)
    {
        return followUpTemplatesMapper.deleteFollowUpTemplatesById(id);
    }
}
