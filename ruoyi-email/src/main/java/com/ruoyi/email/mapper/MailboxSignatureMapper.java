package com.ruoyi.email.mapper;

import java.util.List;
import com.ruoyi.email.domain.MailboxSignature;

/**
 * 个性签名Mapper接口
 * 
 * @author ruoyi
 * @date 2023-07-31
 */
public interface MailboxSignatureMapper 
{
    /**
     * 查询个性签名
     * 
     * @param id 个性签名主键
     * @return 个性签名
     */
    public MailboxSignature selectMailboxSignatureById(Long id);

    /**
     * 查询个性签名列表
     * 
     * @param mailboxSignature 个性签名
     * @return 个性签名集合
     */
    public List<MailboxSignature> selectMailboxSignatureList(MailboxSignature mailboxSignature);

    /**
     * 新增个性签名
     * 
     * @param mailboxSignature 个性签名
     * @return 结果
     */
    public int insertMailboxSignature(MailboxSignature mailboxSignature);

    /**
     * 修改个性签名
     * 
     * @param mailboxSignature 个性签名
     * @return 结果
     */
    public int updateMailboxSignature(MailboxSignature mailboxSignature);

    /**
     * 删除个性签名
     * 
     * @param id 个性签名主键
     * @return 结果
     */
    public int deleteMailboxSignatureById(Long id);

    /**
     * 批量删除个性签名
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMailboxSignatureByIds(Long[] ids);
}
