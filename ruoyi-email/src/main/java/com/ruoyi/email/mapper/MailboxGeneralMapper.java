package com.ruoyi.email.mapper;

import java.util.List;
import com.ruoyi.email.domain.MailboxGeneral;

/**
 * 邮箱常规Mapper接口
 * 
 * @author ruoyi
 * @date 2023-07-31
 */
public interface MailboxGeneralMapper 
{
    /**
     * 查询邮箱常规
     * 
     * @param id 邮箱常规主键
     * @return 邮箱常规
     */
    public MailboxGeneral selectMailboxGeneralById(Long id);

    /**
     * 查询邮箱常规列表
     * 
     * @param mailboxGeneral 邮箱常规
     * @return 邮箱常规集合
     */
    public List<MailboxGeneral> selectMailboxGeneralList(MailboxGeneral mailboxGeneral);

    /**
     * 新增邮箱常规
     * 
     * @param mailboxGeneral 邮箱常规
     * @return 结果
     */
    public int insertMailboxGeneral(MailboxGeneral mailboxGeneral);

    /**
     * 修改邮箱常规
     * 
     * @param mailboxGeneral 邮箱常规
     * @return 结果
     */
    public int updateMailboxGeneral(MailboxGeneral mailboxGeneral);

    /**
     * 删除邮箱常规
     * 
     * @param id 邮箱常规主键
     * @return 结果
     */
    public int deleteMailboxGeneralById(Long id);

    /**
     * 批量删除邮箱常规
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMailboxGeneralByIds(Long[] ids);
}
