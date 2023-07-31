package com.ruoyi.email.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.MailboxOtherConfigMapper;
import com.ruoyi.email.domain.MailboxOtherConfig;
import com.ruoyi.email.service.IMailboxOtherConfigService;

/**
 * 其他配置Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-07-31
 */
@Service
public class MailboxOtherConfigServiceImpl implements IMailboxOtherConfigService 
{
    @Autowired
    private MailboxOtherConfigMapper mailboxOtherConfigMapper;

    /**
     * 查询其他配置
     * 
     * @param id 其他配置主键
     * @return 其他配置
     */
    @Override
    public MailboxOtherConfig selectMailboxOtherConfigById(Long id)
    {
        return mailboxOtherConfigMapper.selectMailboxOtherConfigById(id);
    }

    /**
     * 查询其他配置列表
     * 
     * @param mailboxOtherConfig 其他配置
     * @return 其他配置
     */
    @Override
    public List<MailboxOtherConfig> selectMailboxOtherConfigList(MailboxOtherConfig mailboxOtherConfig)
    {
        return mailboxOtherConfigMapper.selectMailboxOtherConfigList(mailboxOtherConfig);
    }

    /**
     * 新增其他配置
     * 
     * @param mailboxOtherConfig 其他配置
     * @return 结果
     */
    @Override
    public int insertMailboxOtherConfig(MailboxOtherConfig mailboxOtherConfig)
    {
        mailboxOtherConfig.setCreateTime(DateUtils.getNowDate());
        return mailboxOtherConfigMapper.insertMailboxOtherConfig(mailboxOtherConfig);
    }

    /**
     * 修改其他配置
     * 
     * @param mailboxOtherConfig 其他配置
     * @return 结果
     */
    @Override
    public int updateMailboxOtherConfig(MailboxOtherConfig mailboxOtherConfig)
    {
        mailboxOtherConfig.setUpdateTime(DateUtils.getNowDate());
        return mailboxOtherConfigMapper.updateMailboxOtherConfig(mailboxOtherConfig);
    }

    /**
     * 批量删除其他配置
     * 
     * @param ids 需要删除的其他配置主键
     * @return 结果
     */
    @Override
    public int deleteMailboxOtherConfigByIds(Long[] ids)
    {
        return mailboxOtherConfigMapper.deleteMailboxOtherConfigByIds(ids);
    }

    /**
     * 删除其他配置信息
     * 
     * @param id 其他配置主键
     * @return 结果
     */
    @Override
    public int deleteMailboxOtherConfigById(Long id)
    {
        return mailboxOtherConfigMapper.deleteMailboxOtherConfigById(id);
    }
}
