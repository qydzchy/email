package com.ruoyi.email.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.email.service.ITransceiverRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.TransceiverRuleMapper;
import com.ruoyi.email.domain.TransceiverRule;

/**
 * 收发件规则Service业务层处理
 * 
 * @author tangJM
 * @date 2023-07-31
 */
@Service
public class TransceiverRuleServiceImpl implements ITransceiverRuleService
{
    @Autowired
    private TransceiverRuleMapper transceiverRuleMapper;

    /**
     * 查询收发件规则
     * 
     * @param id 收发件规则主键
     * @return 收发件规则
     */
    @Override
    public TransceiverRule selectTransceiverRuleById(Long id)
    {
        return transceiverRuleMapper.selectTransceiverRuleById(id);
    }

    /**
     * 查询收发件规则列表
     * 
     * @param transceiverRule 收发件规则
     * @return 收发件规则
     */
    @Override
    public List<TransceiverRule> selectTransceiverRuleList(TransceiverRule transceiverRule)
    {
        return transceiverRuleMapper.selectTransceiverRuleList(transceiverRule);
    }

    /**
     * 新增收发件规则
     * 
     * @param transceiverRule 收发件规则
     * @return 结果
     */
    @Override
    public int insertTransceiverRule(TransceiverRule transceiverRule)
    {
        transceiverRule.setCreateTime(DateUtils.getNowDate());
        return transceiverRuleMapper.insertTransceiverRule(transceiverRule);
    }

    /**
     * 修改收发件规则
     * 
     * @param transceiverRule 收发件规则
     * @return 结果
     */
    @Override
    public int updateTransceiverRule(TransceiverRule transceiverRule)
    {
        transceiverRule.setUpdateTime(DateUtils.getNowDate());
        return transceiverRuleMapper.updateTransceiverRule(transceiverRule);
    }

    /**
     * 批量删除收发件规则
     * 
     * @param ids 需要删除的收发件规则主键
     * @return 结果
     */
    @Override
    public int deleteTransceiverRuleByIds(Long[] ids)
    {
        return transceiverRuleMapper.deleteTransceiverRuleByIds(ids);
    }

    /**
     * 删除收发件规则信息
     * 
     * @param id 收发件规则主键
     * @return 结果
     */
    @Override
    public int deleteTransceiverRuleById(Long id)
    {
        return transceiverRuleMapper.deleteTransceiverRuleById(id);
    }
}
