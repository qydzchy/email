package com.ruoyi.email.service;

import java.util.List;
import com.ruoyi.email.domain.MailboxFolder;

/**
 * 文件夹Service接口
 * 
 * @author ruoyi
 * @date 2023-07-31
 */
public interface IMailboxFolderService 
{
    /**
     * 查询文件夹
     * 
     * @param id 文件夹主键
     * @return 文件夹
     */
    public MailboxFolder selectMailboxFolderById(Long id);

    /**
     * 查询文件夹列表
     * 
     * @param mailboxFolder 文件夹
     * @return 文件夹集合
     */
    public List<MailboxFolder> selectMailboxFolderList(MailboxFolder mailboxFolder);

    /**
     * 新增文件夹
     * 
     * @param mailboxFolder 文件夹
     * @return 结果
     */
    public int insertMailboxFolder(MailboxFolder mailboxFolder);

    /**
     * 修改文件夹
     * 
     * @param mailboxFolder 文件夹
     * @return 结果
     */
    public int updateMailboxFolder(MailboxFolder mailboxFolder);

    /**
     * 批量删除文件夹
     * 
     * @param ids 需要删除的文件夹主键集合
     * @return 结果
     */
    public int deleteMailboxFolderByIds(Long[] ids);

    /**
     * 删除文件夹信息
     * 
     * @param id 文件夹主键
     * @return 结果
     */
    public int deleteMailboxFolderById(Long id);
}
