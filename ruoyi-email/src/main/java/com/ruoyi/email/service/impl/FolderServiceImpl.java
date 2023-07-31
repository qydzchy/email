package com.ruoyi.email.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.FolderMapper;
import com.ruoyi.email.domain.Folder;
import com.ruoyi.email.service.IFolderService;

/**
 * 文件夹Service业务层处理
 * 
 * @author tangJM
 * @date 2023-07-31
 */
@Service
public class FolderServiceImpl implements IFolderService 
{
    @Autowired
    private FolderMapper folderMapper;

    /**
     * 查询文件夹
     * 
     * @param id 文件夹主键
     * @return 文件夹
     */
    @Override
    public Folder selectFolderById(Long id)
    {
        return folderMapper.selectFolderById(id);
    }

    /**
     * 查询文件夹列表
     * 
     * @param folder 文件夹
     * @return 文件夹
     */
    @Override
    public List<Folder> selectFolderList(Folder folder)
    {
        return folderMapper.selectFolderList(folder);
    }

    /**
     * 新增文件夹
     * 
     * @param folder 文件夹
     * @return 结果
     */
    @Override
    public int insertFolder(Folder folder)
    {
        folder.setCreateTime(DateUtils.getNowDate());
        return folderMapper.insertFolder(folder);
    }

    /**
     * 修改文件夹
     * 
     * @param folder 文件夹
     * @return 结果
     */
    @Override
    public int updateFolder(Folder folder)
    {
        folder.setUpdateTime(DateUtils.getNowDate());
        return folderMapper.updateFolder(folder);
    }

    /**
     * 批量删除文件夹
     * 
     * @param ids 需要删除的文件夹主键
     * @return 结果
     */
    @Override
    public int deleteFolderByIds(Long[] ids)
    {
        return folderMapper.deleteFolderByIds(ids);
    }

    /**
     * 删除文件夹信息
     * 
     * @param id 文件夹主键
     * @return 结果
     */
    @Override
    public int deleteFolderById(Long id)
    {
        return folderMapper.deleteFolderById(id);
    }
}
