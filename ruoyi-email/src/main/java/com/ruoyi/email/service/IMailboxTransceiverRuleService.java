package com.ruoyi.email.service;

import java.util.List;
import com.ruoyi.email.domain.MailboxTransceiverRule;

/**
 * 收发件规则Service接口
 * 
 * @author ruoyi
 * @date 2023-07-31
 */
public interface IMailboxTransceiverRuleService 
{
    /**
     * 查询收发件规则
     * 
     * @param id 收发件规则主键
     * @return 收发件规则
     */
    public MailboxTransceiverRule selectMailboxTransceiverRuleById(Long id);

    /**
     * 查询收发件规则列表
     * 
     * @param mailboxTransceiverRule 收发件规则
     * @return 收发件规则集合
     */
    public List<MailboxTransceiverRule> selectMailboxTransceiverRuleList(MailboxTransceiverRule mailboxTransceiverRule);

    /**
     * 新增收发件规则
     * 
     * @param mailboxTransceiverRule 收发件规则
     * @return 结果
     */
    public int insertMailboxTransceiverRule(MailboxTransceiverRule mailboxTransceiverRule);

    /**
     * 修改收发件规则
     * 
     * @param mailboxTransceiverRule 收发件规则
     * @return 结果
     */
    public int updateMailboxTransceiverRule(MailboxTransceiverRule mailboxTransceiverRule);

    /**
     * 批量删除收发件规则
     * 
     * @param ids 需要删除的收发件规则主键集合
     * @return 结果
     */
    public int deleteMailboxTransceiverRuleByIds(Long[] ids);

    /**
     * 删除收发件规则信息
     * 
     * @param id 收发件规则主键
     * @return 结果
     */
    public int deleteMailboxTransceiverRuleById(Long id);
}
