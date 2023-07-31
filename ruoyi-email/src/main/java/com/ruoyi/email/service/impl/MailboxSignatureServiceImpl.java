package com.ruoyi.email.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.MailboxSignatureMapper;
import com.ruoyi.email.domain.MailboxSignature;
import com.ruoyi.email.service.IMailboxSignatureService;

/**
 * 个性签名Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-07-31
 */
@Service
public class MailboxSignatureServiceImpl implements IMailboxSignatureService 
{
    @Autowired
    private MailboxSignatureMapper mailboxSignatureMapper;

    /**
     * 查询个性签名
     * 
     * @param id 个性签名主键
     * @return 个性签名
     */
    @Override
    public MailboxSignature selectMailboxSignatureById(Long id)
    {
        return mailboxSignatureMapper.selectMailboxSignatureById(id);
    }

    /**
     * 查询个性签名列表
     * 
     * @param mailboxSignature 个性签名
     * @return 个性签名
     */
    @Override
    public List<MailboxSignature> selectMailboxSignatureList(MailboxSignature mailboxSignature)
    {
        return mailboxSignatureMapper.selectMailboxSignatureList(mailboxSignature);
    }

    /**
     * 新增个性签名
     * 
     * @param mailboxSignature 个性签名
     * @return 结果
     */
    @Override
    public int insertMailboxSignature(MailboxSignature mailboxSignature)
    {
        mailboxSignature.setCreateTime(DateUtils.getNowDate());
        return mailboxSignatureMapper.insertMailboxSignature(mailboxSignature);
    }

    /**
     * 修改个性签名
     * 
     * @param mailboxSignature 个性签名
     * @return 结果
     */
    @Override
    public int updateMailboxSignature(MailboxSignature mailboxSignature)
    {
        mailboxSignature.setUpdateTime(DateUtils.getNowDate());
        return mailboxSignatureMapper.updateMailboxSignature(mailboxSignature);
    }

    /**
     * 批量删除个性签名
     * 
     * @param ids 需要删除的个性签名主键
     * @return 结果
     */
    @Override
    public int deleteMailboxSignatureByIds(Long[] ids)
    {
        return mailboxSignatureMapper.deleteMailboxSignatureByIds(ids);
    }

    /**
     * 删除个性签名信息
     * 
     * @param id 个性签名主键
     * @return 结果
     */
    @Override
    public int deleteMailboxSignatureById(Long id)
    {
        return mailboxSignatureMapper.deleteMailboxSignatureById(id);
    }
}
