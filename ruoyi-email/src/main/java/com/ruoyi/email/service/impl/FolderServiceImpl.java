package com.ruoyi.email.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.email.FolderTypeEnum;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.email.domain.dto.folder.FolderSaveOrUpdateDTO;
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
     * @param dto 文件夹
     * @return 结果
     */
    @Override
    public int insertFolder(FolderSaveOrUpdateDTO dto)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        // 查询是否已经存在文件夹名称
        boolean exist = existFolderName(null, dto.getParentFolderId(), dto.getName(), userId);
        if (exist) {
            throw new ServiceException("文件夹名称已存在");
        }

        Folder folder = new Folder();
        folder.setParentFolderId(dto.getParentFolderId());
        folder.setName(dto.getName());
        folder.setType(FolderTypeEnum.CUSTOM.getType());
        folder.setCreateId(userId);
        folder.setCreateBy(username);
        folder.setCreateTime(DateUtils.getNowDate());
        folder.setUpdateId(userId);
        folder.setUpdateBy(username);
        folder.setUpdateTime(DateUtils.getNowDate());
        return folderMapper.insertFolder(folder);
    }

    /**
     * 是否存在文件夹名称
     * @param parentFolderId
     * @param name
     * @param userId
     * @return
     */
    private boolean existFolderName(Long id, Long parentFolderId, String name, Long userId) {
        int count = folderMapper.countByParentFolderIdAndName(id, parentFolderId, name, userId);
        return count > 0 ? true : false;
    }

    /**
     * 修改文件夹
     * 
     * @param dto 文件夹
     * @return 结果
     */
    @Override
    public int updateFolder(FolderSaveOrUpdateDTO dto)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();
        Folder folder = folderMapper.getById(dto.getId(), userId);

        // 查询是否已经存在文件夹名称
        boolean exist = existFolderName(null, dto.getParentFolderId(), dto.getName(), userId);
        if (exist) {
            throw new ServiceException("文件夹名称已存在");
        }

        folder.setName(dto.getName());
        folder.setUpdateId(userId);
        folder.setUpdateBy(username);
        folder.setUpdateTime(new Date());
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

    @Override
    public int deleteById(Long id) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        return folderMapper.deleteById(id, userId);
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
