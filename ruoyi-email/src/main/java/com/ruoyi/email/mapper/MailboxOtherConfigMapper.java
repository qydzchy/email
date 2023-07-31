package com.ruoyi.email.mapper;

import java.util.List;
import com.ruoyi.email.domain.MailboxOtherConfig;

/**
 * 其他配置Mapper接口
 * 
 * @author ruoyi
 * @date 2023-07-31
 */
public interface MailboxOtherConfigMapper 
{
    /**
     * 查询其他配置
     * 
     * @param id 其他配置主键
     * @return 其他配置
     */
    public MailboxOtherConfig selectMailboxOtherConfigById(Long id);

    /**
     * 查询其他配置列表
     * 
     * @param mailboxOtherConfig 其他配置
     * @return 其他配置集合
     */
    public List<MailboxOtherConfig> selectMailboxOtherConfigList(MailboxOtherConfig mailboxOtherConfig);

    /**
     * 新增其他配置
     * 
     * @param mailboxOtherConfig 其他配置
     * @return 结果
     */
    public int insertMailboxOtherConfig(MailboxOtherConfig mailboxOtherConfig);

    /**
     * 修改其他配置
     * 
     * @param mailboxOtherConfig 其他配置
     * @return 结果
     */
    public int updateMailboxOtherConfig(MailboxOtherConfig mailboxOtherConfig);

    /**
     * 删除其他配置
     * 
     * @param id 其他配置主键
     * @return 结果
     */
    public int deleteMailboxOtherConfigById(Long id);

    /**
     * 批量删除其他配置
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMailboxOtherConfigByIds(Long[] ids);
}
