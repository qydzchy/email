package com.ruoyi.email.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.MailboxTaskEmailPullMapper;
import com.ruoyi.email.domain.MailboxTaskEmailPull;
import com.ruoyi.email.service.IMailboxTaskEmailPullService;

/**
 * 拉取邮件Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-07-31
 */
@Service
public class MailboxTaskEmailPullServiceImpl implements IMailboxTaskEmailPullService 
{
    @Autowired
    private MailboxTaskEmailPullMapper mailboxTaskEmailPullMapper;

    /**
     * 查询拉取邮件
     * 
     * @param id 拉取邮件主键
     * @return 拉取邮件
     */
    @Override
    public MailboxTaskEmailPull selectMailboxTaskEmailPullById(Long id)
    {
        return mailboxTaskEmailPullMapper.selectMailboxTaskEmailPullById(id);
    }

    /**
     * 查询拉取邮件列表
     * 
     * @param mailboxTaskEmailPull 拉取邮件
     * @return 拉取邮件
     */
    @Override
    public List<MailboxTaskEmailPull> selectMailboxTaskEmailPullList(MailboxTaskEmailPull mailboxTaskEmailPull)
    {
        return mailboxTaskEmailPullMapper.selectMailboxTaskEmailPullList(mailboxTaskEmailPull);
    }

    /**
     * 新增拉取邮件
     * 
     * @param mailboxTaskEmailPull 拉取邮件
     * @return 结果
     */
    @Override
    public int insertMailboxTaskEmailPull(MailboxTaskEmailPull mailboxTaskEmailPull)
    {
        mailboxTaskEmailPull.setCreateTime(DateUtils.getNowDate());
        return mailboxTaskEmailPullMapper.insertMailboxTaskEmailPull(mailboxTaskEmailPull);
    }

    /**
     * 修改拉取邮件
     * 
     * @param mailboxTaskEmailPull 拉取邮件
     * @return 结果
     */
    @Override
    public int updateMailboxTaskEmailPull(MailboxTaskEmailPull mailboxTaskEmailPull)
    {
        mailboxTaskEmailPull.setUpdateTime(DateUtils.getNowDate());
        return mailboxTaskEmailPullMapper.updateMailboxTaskEmailPull(mailboxTaskEmailPull);
    }

    /**
     * 批量删除拉取邮件
     * 
     * @param ids 需要删除的拉取邮件主键
     * @return 结果
     */
    @Override
    public int deleteMailboxTaskEmailPullByIds(Long[] ids)
    {
        return mailboxTaskEmailPullMapper.deleteMailboxTaskEmailPullByIds(ids);
    }

    /**
     * 删除拉取邮件信息
     * 
     * @param id 拉取邮件主键
     * @return 结果
     */
    @Override
    public int deleteMailboxTaskEmailPullById(Long id)
    {
        return mailboxTaskEmailPullMapper.deleteMailboxTaskEmailPullById(id);
    }
}
