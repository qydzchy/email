package com.ruoyi.email.mapper;

import java.util.List;
import com.ruoyi.email.domain.MailboxHost;

/**
 * 邮箱服务器查询Mapper接口
 * 
 * @author ruoyi
 * @date 2023-07-31
 */
public interface MailboxHostMapper 
{
    /**
     * 查询邮箱服务器查询
     * 
     * @param id 邮箱服务器查询主键
     * @return 邮箱服务器查询
     */
    public MailboxHost selectMailboxHostById(Long id);

    /**
     * 查询邮箱服务器查询列表
     * 
     * @param mailboxHost 邮箱服务器查询
     * @return 邮箱服务器查询集合
     */
    public List<MailboxHost> selectMailboxHostList(MailboxHost mailboxHost);

    /**
     * 新增邮箱服务器查询
     * 
     * @param mailboxHost 邮箱服务器查询
     * @return 结果
     */
    public int insertMailboxHost(MailboxHost mailboxHost);

    /**
     * 修改邮箱服务器查询
     * 
     * @param mailboxHost 邮箱服务器查询
     * @return 结果
     */
    public int updateMailboxHost(MailboxHost mailboxHost);

    /**
     * 删除邮箱服务器查询
     * 
     * @param id 邮箱服务器查询主键
     * @return 结果
     */
    public int deleteMailboxHostById(Long id);

    /**
     * 批量删除邮箱服务器查询
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMailboxHostByIds(Long[] ids);
}
