package com.ruoyi.email.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.MailboxGeneralSingleMapper;
import com.ruoyi.email.domain.MailboxGeneralSingle;
import com.ruoyi.email.service.IMailboxGeneralSingleService;

/**
 * 单个邮箱设置Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-07-31
 */
@Service
public class MailboxGeneralSingleServiceImpl implements IMailboxGeneralSingleService 
{
    @Autowired
    private MailboxGeneralSingleMapper mailboxGeneralSingleMapper;

    /**
     * 查询单个邮箱设置
     * 
     * @param id 单个邮箱设置主键
     * @return 单个邮箱设置
     */
    @Override
    public MailboxGeneralSingle selectMailboxGeneralSingleById(Long id)
    {
        return mailboxGeneralSingleMapper.selectMailboxGeneralSingleById(id);
    }

    /**
     * 查询单个邮箱设置列表
     * 
     * @param mailboxGeneralSingle 单个邮箱设置
     * @return 单个邮箱设置
     */
    @Override
    public List<MailboxGeneralSingle> selectMailboxGeneralSingleList(MailboxGeneralSingle mailboxGeneralSingle)
    {
        return mailboxGeneralSingleMapper.selectMailboxGeneralSingleList(mailboxGeneralSingle);
    }

    /**
     * 新增单个邮箱设置
     * 
     * @param mailboxGeneralSingle 单个邮箱设置
     * @return 结果
     */
    @Override
    public int insertMailboxGeneralSingle(MailboxGeneralSingle mailboxGeneralSingle)
    {
        mailboxGeneralSingle.setCreateTime(DateUtils.getNowDate());
        return mailboxGeneralSingleMapper.insertMailboxGeneralSingle(mailboxGeneralSingle);
    }

    /**
     * 修改单个邮箱设置
     * 
     * @param mailboxGeneralSingle 单个邮箱设置
     * @return 结果
     */
    @Override
    public int updateMailboxGeneralSingle(MailboxGeneralSingle mailboxGeneralSingle)
    {
        mailboxGeneralSingle.setUpdateTime(DateUtils.getNowDate());
        return mailboxGeneralSingleMapper.updateMailboxGeneralSingle(mailboxGeneralSingle);
    }

    /**
     * 批量删除单个邮箱设置
     * 
     * @param ids 需要删除的单个邮箱设置主键
     * @return 结果
     */
    @Override
    public int deleteMailboxGeneralSingleByIds(Long[] ids)
    {
        return mailboxGeneralSingleMapper.deleteMailboxGeneralSingleByIds(ids);
    }

    /**
     * 删除单个邮箱设置信息
     * 
     * @param id 单个邮箱设置主键
     * @return 结果
     */
    @Override
    public int deleteMailboxGeneralSingleById(Long id)
    {
        return mailboxGeneralSingleMapper.deleteMailboxGeneralSingleById(id);
    }
}
