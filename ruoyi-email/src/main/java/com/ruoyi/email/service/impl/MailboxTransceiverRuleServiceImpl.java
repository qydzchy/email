package com.ruoyi.email.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.MailboxTransceiverRuleMapper;
import com.ruoyi.email.domain.MailboxTransceiverRule;
import com.ruoyi.email.service.IMailboxTransceiverRuleService;

/**
 * 收发件规则Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-07-31
 */
@Service
public class MailboxTransceiverRuleServiceImpl implements IMailboxTransceiverRuleService 
{
    @Autowired
    private MailboxTransceiverRuleMapper mailboxTransceiverRuleMapper;

    /**
     * 查询收发件规则
     * 
     * @param id 收发件规则主键
     * @return 收发件规则
     */
    @Override
    public MailboxTransceiverRule selectMailboxTransceiverRuleById(Long id)
    {
        return mailboxTransceiverRuleMapper.selectMailboxTransceiverRuleById(id);
    }

    /**
     * 查询收发件规则列表
     * 
     * @param mailboxTransceiverRule 收发件规则
     * @return 收发件规则
     */
    @Override
    public List<MailboxTransceiverRule> selectMailboxTransceiverRuleList(MailboxTransceiverRule mailboxTransceiverRule)
    {
        return mailboxTransceiverRuleMapper.selectMailboxTransceiverRuleList(mailboxTransceiverRule);
    }

    /**
     * 新增收发件规则
     * 
     * @param mailboxTransceiverRule 收发件规则
     * @return 结果
     */
    @Override
    public int insertMailboxTransceiverRule(MailboxTransceiverRule mailboxTransceiverRule)
    {
        mailboxTransceiverRule.setCreateTime(DateUtils.getNowDate());
        return mailboxTransceiverRuleMapper.insertMailboxTransceiverRule(mailboxTransceiverRule);
    }

    /**
     * 修改收发件规则
     * 
     * @param mailboxTransceiverRule 收发件规则
     * @return 结果
     */
    @Override
    public int updateMailboxTransceiverRule(MailboxTransceiverRule mailboxTransceiverRule)
    {
        mailboxTransceiverRule.setUpdateTime(DateUtils.getNowDate());
        return mailboxTransceiverRuleMapper.updateMailboxTransceiverRule(mailboxTransceiverRule);
    }

    /**
     * 批量删除收发件规则
     * 
     * @param ids 需要删除的收发件规则主键
     * @return 结果
     */
    @Override
    public int deleteMailboxTransceiverRuleByIds(Long[] ids)
    {
        return mailboxTransceiverRuleMapper.deleteMailboxTransceiverRuleByIds(ids);
    }

    /**
     * 删除收发件规则信息
     * 
     * @param id 收发件规则主键
     * @return 结果
     */
    @Override
    public int deleteMailboxTransceiverRuleById(Long id)
    {
        return mailboxTransceiverRuleMapper.deleteMailboxTransceiverRuleById(id);
    }
}
