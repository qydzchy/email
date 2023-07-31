package com.ruoyi.email.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.ImportEmailMapper;
import com.ruoyi.email.domain.ImportEmail;
import com.ruoyi.email.service.IImportEmailService;

/**
 * 导入邮件Service业务层处理
 * 
 * @author tangJM
 * @date 2023-07-31
 */
@Service
public class ImportEmailServiceImpl implements IImportEmailService 
{
    @Autowired
    private ImportEmailMapper importEmailMapper;

    /**
     * 查询导入邮件
     * 
     * @param id 导入邮件主键
     * @return 导入邮件
     */
    @Override
    public ImportEmail selectImportEmailById(Long id)
    {
        return importEmailMapper.selectImportEmailById(id);
    }

    /**
     * 查询导入邮件列表
     * 
     * @param importEmail 导入邮件
     * @return 导入邮件
     */
    @Override
    public List<ImportEmail> selectImportEmailList(ImportEmail importEmail)
    {
        return importEmailMapper.selectImportEmailList(importEmail);
    }

    /**
     * 新增导入邮件
     * 
     * @param importEmail 导入邮件
     * @return 结果
     */
    @Override
    public int insertImportEmail(ImportEmail importEmail)
    {
        importEmail.setCreateTime(DateUtils.getNowDate());
        return importEmailMapper.insertImportEmail(importEmail);
    }

    /**
     * 修改导入邮件
     * 
     * @param importEmail 导入邮件
     * @return 结果
     */
    @Override
    public int updateImportEmail(ImportEmail importEmail)
    {
        importEmail.setUpdateTime(DateUtils.getNowDate());
        return importEmailMapper.updateImportEmail(importEmail);
    }

    /**
     * 批量删除导入邮件
     * 
     * @param ids 需要删除的导入邮件主键
     * @return 结果
     */
    @Override
    public int deleteImportEmailByIds(Long[] ids)
    {
        return importEmailMapper.deleteImportEmailByIds(ids);
    }

    /**
     * 删除导入邮件信息
     * 
     * @param id 导入邮件主键
     * @return 结果
     */
    @Override
    public int deleteImportEmailById(Long id)
    {
        return importEmailMapper.deleteImportEmailById(id);
    }
}
