package com.ruoyi.email.mapper;

import java.util.List;
import com.ruoyi.email.domain.Folder;
import org.apache.ibatis.annotations.Param;

/**
 * 文件夹Mapper接口
 * 
 * @author tangJM
 * @date 2023-07-31
 */
public interface FolderMapper 
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
     * @param folder 文件夹
     * @return 结果
     */
    public int insertFolder(Folder folder);

    /**
     * 修改文件夹
     * 
     * @param folder 文件夹
     * @return 结果
     */
    public int updateFolder(Folder folder);

    /**
     * 删除文件夹
     * 
     * @param id 文件夹主键
     * @return 结果
     */
    public int deleteFolderById(Long id);

    /**
     * 批量删除文件夹
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFolderByIds(Long[] ids);

    /**
     * 获取文件夹列表
     * @param userId
     * @return
     */
    List<Folder> getFolderList(@Param("createId") Long userId);

    /**
     * 是否存在同一文件夹下相同的文件夹名称
     * @param id
     * @param parentFolderId
     * @param name
     * @param userId
     */
    int countByParentFolderIdAndName(@Param("id") Long id, @Param("parentFolderId") Long parentFolderId, @Param("name") String name, @Param("createId") Long userId);

    /**
     * 根据id查询文件夹信息
     * @param id
     * @param userId
     * @return
     */
    Folder getById(@Param("id") Long id, @Param("createId") Long userId);

    /**
     * 根据id删除
     * @param id
     * @param userId
     * @return
     */
    int deleteById(@Param("id") Long id, @Param("createId") Long userId);
}
