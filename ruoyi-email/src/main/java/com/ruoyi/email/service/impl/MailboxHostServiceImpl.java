package com.ruoyi.email.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.MailboxHostMapper;
import com.ruoyi.email.domain.MailboxHost;
import com.ruoyi.email.service.IMailboxHostService;

/**
 * 邮箱服务器查询Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-07-31
 */
@Service
public class MailboxHostServiceImpl implements IMailboxHostService 
{
    @Autowired
    private MailboxHostMapper mailboxHostMapper;

    /**
     * 查询邮箱服务器查询
     * 
     * @param id 邮箱服务器查询主键
     * @return 邮箱服务器查询
     */
    @Override
    public MailboxHost selectMailboxHostById(Long id)
    {
        return mailboxHostMapper.selectMailboxHostById(id);
    }

    /**
     * 查询邮箱服务器查询列表
     * 
     * @param mailboxHost 邮箱服务器查询
     * @return 邮箱服务器查询
     */
    @Override
    public List<MailboxHost> selectMailboxHostList(MailboxHost mailboxHost)
    {
        return mailboxHostMapper.selectMailboxHostList(mailboxHost);
    }

    /**
     * 新增邮箱服务器查询
     * 
     * @param mailboxHost 邮箱服务器查询
     * @return 结果
     */
    @Override
    public int insertMailboxHost(MailboxHost mailboxHost)
    {
        return mailboxHostMapper.insertMailboxHost(mailboxHost);
    }

    /**
     * 修改邮箱服务器查询
     * 
     * @param mailboxHost 邮箱服务器查询
     * @return 结果
     */
    @Override
    public int updateMailboxHost(MailboxHost mailboxHost)
    {
        return mailboxHostMapper.updateMailboxHost(mailboxHost);
    }

    /**
     * 批量删除邮箱服务器查询
     * 
     * @param ids 需要删除的邮箱服务器查询主键
     * @return 结果
     */
    @Override
    public int deleteMailboxHostByIds(Long[] ids)
    {
        return mailboxHostMapper.deleteMailboxHostByIds(ids);
    }

    /**
     * 删除邮箱服务器查询信息
     * 
     * @param id 邮箱服务器查询主键
     * @return 结果
     */
    @Override
    public int deleteMailboxHostById(Long id)
    {
        return mailboxHostMapper.deleteMailboxHostById(id);
    }
}
