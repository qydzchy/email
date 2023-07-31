package com.ruoyi.email.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.MailboxGeneralMapper;
import com.ruoyi.email.domain.MailboxGeneral;
import com.ruoyi.email.service.IMailboxGeneralService;

/**
 * 邮箱常规Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-07-31
 */
@Service
public class MailboxGeneralServiceImpl implements IMailboxGeneralService 
{
    @Autowired
    private MailboxGeneralMapper mailboxGeneralMapper;

    /**
     * 查询邮箱常规
     * 
     * @param id 邮箱常规主键
     * @return 邮箱常规
     */
    @Override
    public MailboxGeneral selectMailboxGeneralById(Long id)
    {
        return mailboxGeneralMapper.selectMailboxGeneralById(id);
    }

    /**
     * 查询邮箱常规列表
     * 
     * @param mailboxGeneral 邮箱常规
     * @return 邮箱常规
     */
    @Override
    public List<MailboxGeneral> selectMailboxGeneralList(MailboxGeneral mailboxGeneral)
    {
        return mailboxGeneralMapper.selectMailboxGeneralList(mailboxGeneral);
    }

    /**
     * 新增邮箱常规
     * 
     * @param mailboxGeneral 邮箱常规
     * @return 结果
     */
    @Override
    public int insertMailboxGeneral(MailboxGeneral mailboxGeneral)
    {
        mailboxGeneral.setCreateTime(DateUtils.getNowDate());
        return mailboxGeneralMapper.insertMailboxGeneral(mailboxGeneral);
    }

    /**
     * 修改邮箱常规
     * 
     * @param mailboxGeneral 邮箱常规
     * @return 结果
     */
    @Override
    public int updateMailboxGeneral(MailboxGeneral mailboxGeneral)
    {
        mailboxGeneral.setUpdateTime(DateUtils.getNowDate());
        return mailboxGeneralMapper.updateMailboxGeneral(mailboxGeneral);
    }

    /**
     * 批量删除邮箱常规
     * 
     * @param ids 需要删除的邮箱常规主键
     * @return 结果
     */
    @Override
    public int deleteMailboxGeneralByIds(Long[] ids)
    {
        return mailboxGeneralMapper.deleteMailboxGeneralByIds(ids);
    }

    /**
     * 删除邮箱常规信息
     * 
     * @param id 邮箱常规主键
     * @return 结果
     */
    @Override
    public int deleteMailboxGeneralById(Long id)
    {
        return mailboxGeneralMapper.deleteMailboxGeneralById(id);
    }
}
