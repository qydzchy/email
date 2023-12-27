package com.ruoyi.email.service;

import java.util.List;
import com.ruoyi.email.domain.Folder;
import com.ruoyi.email.domain.dto.folder.FolderSaveOrUpdateDTO;
import com.ruoyi.email.domain.vo.FolderListVO;

/**
 * 文件夹Service接口
 * 
 * @author tangJM
 * @date 2023-07-31
 */
public interface IFolderService 
{
    /**
     * 查询文件夹
     * 
     * @param id 文件夹主键
     * @return 文件夹
     */
    public Folder selectFolderById(Long id);

    /**
     * 查询文件夹列表
     * 
     * @param folder 文件夹
     * @return 文件夹集合
     */
    public List<Folder> selectFolderList(Folder folder);

    /**
     * 新增文件夹
     * 
     * @param dto
     * @return 结果
     */
    public int insertFolder(FolderSaveOrUpdateDTO dto);

    /**
     * 修改文件夹
     * 
     * @param dto 文件夹
     * @return 结果
     */
    public int updateFolder(FolderSaveOrUpdateDTO dto);

    /**
     * 批量删除文件夹
     * 
     * @param ids 需要删除的文件夹主键集合
     * @return 结果
     */
    public int deleteFolderByIds(Long[] ids);

    /**
     * 删除文件夹信息
     * 
     * @param id 文件夹主键
     * @return 结果
     */
    public int deleteFolderById(Long id);

    /**
     * 获取文件夹列表(树型)
     * @return
     */
    List<FolderListVO> getFolderTree();

    /**
     * 删除
     * @param id
     * @return
     */
    int deleteById(Long id);
}
