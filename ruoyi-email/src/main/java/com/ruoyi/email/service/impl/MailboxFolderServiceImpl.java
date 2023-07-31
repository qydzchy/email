package com.ruoyi.email.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.MailboxFolderMapper;
import com.ruoyi.email.domain.MailboxFolder;
import com.ruoyi.email.service.IMailboxFolderService;

/**
 * 文件夹Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-07-31
 */
@Service
public class MailboxFolderServiceImpl implements IMailboxFolderService 
{
    @Autowired
    private MailboxFolderMapper mailboxFolderMapper;

    /**
     * 查询文件夹
     * 
     * @param id 文件夹主键
     * @return 文件夹
     */
    @Override
    public MailboxFolder selectMailboxFolderById(Long id)
    {
        return mailboxFolderMapper.selectMailboxFolderById(id);
    }

    /**
     * 查询文件夹列表
     * 
     * @param mailboxFolder 文件夹
     * @return 文件夹
     */
    @Override
    public List<MailboxFolder> selectMailboxFolderList(MailboxFolder mailboxFolder)
    {
        return mailboxFolderMapper.selectMailboxFolderList(mailboxFolder);
    }

    /**
     * 新增文件夹
     * 
     * @param mailboxFolder 文件夹
     * @return 结果
     */
    @Override
    public int insertMailboxFolder(MailboxFolder mailboxFolder)
    {
        mailboxFolder.setCreateTime(DateUtils.getNowDate());
        return mailboxFolderMapper.insertMailboxFolder(mailboxFolder);
    }

    /**
     * 修改文件夹
     * 
     * @param mailboxFolder 文件夹
     * @return 结果
     */
    @Override
    public int updateMailboxFolder(MailboxFolder mailboxFolder)
    {
        mailboxFolder.setUpdateTime(DateUtils.getNowDate());
        return mailboxFolderMapper.updateMailboxFolder(mailboxFolder);
    }

    /**
     * 批量删除文件夹
     * 
     * @param ids 需要删除的文件夹主键
     * @return 结果
     */
    @Override
    public int deleteMailboxFolderByIds(Long[] ids)
    {
        return mailboxFolderMapper.deleteMailboxFolderByIds(ids);
    }

    /**
     * 删除文件夹信息
     * 
     * @param id 文件夹主键
     * @return 结果
     */
    @Override
    public int deleteMailboxFolderById(Long id)
    {
        return mailboxFolderMapper.deleteMailboxFolderById(id);
    }
}
