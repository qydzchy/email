package com.ruoyi.email.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.email.domain.vo.folder.FolderListVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.FolderMapper;
import com.ruoyi.email.domain.Folder;
import com.ruoyi.email.service.IFolderService;

import javax.annotation.Resource;

/**
 * 文件夹Service业务层处理
 * 
 * @author tangJM
 * @date 2023-07-31
 */
@Service
public class FolderServiceImpl implements IFolderService 
{
    @Resource
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

    @Override
    public List<FolderListVO> getFolderTree() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();

        List<Folder> folderList = folderMapper.getFolderList(userId); //
        List<FolderListVO> folderListVOList = new ArrayList<>();
        for (Folder folder : folderList) {
            FolderListVO folderListVO = new FolderListVO();
            BeanUtils.copyProperties(folder, folderListVO);
            folderListVOList.add(folderListVO);
        }

        return buildTree(folderListVOList, -1L);
    }

    private List<FolderListVO> buildTree(List<FolderListVO> folders, Long parentId) {
        List<FolderListVO> children = new ArrayList<>();

        for (FolderListVO folder : folders) {
            if ((parentId == null && folder.getParentFolderId() == null)
                    || (parentId != null && parentId.equals(folder.getParentFolderId()))) {
                List<FolderListVO> childFolders = buildTree(folders, folder.getId());
                folder.setChildren(childFolders); // 假设FolderListVO有一个children字段
                children.add(folder);
            }
        }

        return children;
    }
}
