package com.ruoyi.email.mapper;

import java.util.List;
import com.ruoyi.email.domain.ImportEmail;

/**
 * 导入邮件Mapper接口
 * 
 * @author tangJM
 * @date 2023-07-31
 */
public interface ImportEmailMapper 
{
    /**
     * 查询导入邮件
     * 
     * @param id 导入邮件主键
     * @return 导入邮件
     */
    public ImportEmail selectImportEmailById(Long id);

    /**
     * 查询导入邮件列表
     * 
     * @param importEmail 导入邮件
     * @return 导入邮件集合
     */
    public List<ImportEmail> selectImportEmailList(ImportEmail importEmail);

    /**
     * 新增导入邮件
     * 
     * @param importEmail 导入邮件
     * @return 结果
     */
    public int insertImportEmail(ImportEmail importEmail);

    /**
     * 修改导入邮件
     * 
     * @param importEmail 导入邮件
     * @return 结果
     */
    public int updateImportEmail(ImportEmail importEmail);

    /**
     * 删除导入邮件
     * 
     * @param id 导入邮件主键
     * @return 结果
     */
    public int deleteImportEmailById(Long id);

    /**
     * 批量删除导入邮件
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteImportEmailByIds(Long[] ids);
}
