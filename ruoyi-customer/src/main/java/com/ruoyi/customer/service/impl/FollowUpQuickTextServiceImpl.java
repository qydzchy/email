package com.ruoyi.customer.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.FollowUpQuickTextMapper;
import com.ruoyi.customer.domain.FollowUpQuickText;
import com.ruoyi.customer.service.IFollowUpQuickTextService;

/**
 * 写跟进快捷文本Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
@Service
public class FollowUpQuickTextServiceImpl implements IFollowUpQuickTextService 
{
    @Autowired
    private FollowUpQuickTextMapper followUpQuickTextMapper;

    /**
     * 查询写跟进快捷文本
     * 
     * @param id 写跟进快捷文本主键
     * @return 写跟进快捷文本
     */
    @Override
    public FollowUpQuickText selectFollowUpQuickTextById(Long id)
    {
        return followUpQuickTextMapper.selectFollowUpQuickTextById(id);
    }

    /**
     * 查询写跟进快捷文本列表
     * 
     * @param followUpQuickText 写跟进快捷文本
     * @return 写跟进快捷文本
     */
    @Override
    public List<FollowUpQuickText> selectFollowUpQuickTextList(FollowUpQuickText followUpQuickText)
    {
        return followUpQuickTextMapper.selectFollowUpQuickTextList(followUpQuickText);
    }

    /**
     * 新增写跟进快捷文本
     * 
     * @param followUpQuickText 写跟进快捷文本
     * @return 结果
     */
    @Override
    public int insertFollowUpQuickText(FollowUpQuickText followUpQuickText)
    {
        followUpQuickText.setCreateTime(DateUtils.getNowDate());
        return followUpQuickTextMapper.insertFollowUpQuickText(followUpQuickText);
    }

    /**
     * 修改写跟进快捷文本
     * 
     * @param followUpQuickText 写跟进快捷文本
     * @return 结果
     */
    @Override
    public int updateFollowUpQuickText(FollowUpQuickText followUpQuickText)
    {
        followUpQuickText.setUpdateTime(DateUtils.getNowDate());
        return followUpQuickTextMapper.updateFollowUpQuickText(followUpQuickText);
    }

    /**
     * 批量删除写跟进快捷文本
     * 
     * @param ids 需要删除的写跟进快捷文本主键
     * @return 结果
     */
    @Override
    public int deleteFollowUpQuickTextByIds(Long[] ids)
    {
        return followUpQuickTextMapper.deleteFollowUpQuickTextByIds(ids);
    }

    /**
     * 删除写跟进快捷文本信息
     * 
     * @param id 写跟进快捷文本主键
     * @return 结果
     */
    @Override
    public int deleteFollowUpQuickTextById(Long id)
    {
        return followUpQuickTextMapper.deleteFollowUpQuickTextById(id);
    }
}
