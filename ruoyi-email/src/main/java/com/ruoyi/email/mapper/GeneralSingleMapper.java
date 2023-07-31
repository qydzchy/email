package com.ruoyi.email.mapper;

import java.util.List;
import com.ruoyi.email.domain.GeneralSingle;

/**
 * 单个邮箱设置Mapper接口
 * 
 * @author tangJM
 * @date 2023-07-31
 */
public interface GeneralSingleMapper 
{
    /**
     * 查询单个邮箱设置
     * 
     * @param id 单个邮箱设置主键
     * @return 单个邮箱设置
     */
    public GeneralSingle selectGeneralSingleById(Long id);

    /**
     * 查询单个邮箱设置列表
     * 
     * @param generalSingle 单个邮箱设置
     * @return 单个邮箱设置集合
     */
    public List<GeneralSingle> selectGeneralSingleList(GeneralSingle generalSingle);

    /**
     * 新增单个邮箱设置
     * 
     * @param generalSingle 单个邮箱设置
     * @return 结果
     */
    public int insertGeneralSingle(GeneralSingle generalSingle);

    /**
     * 修改单个邮箱设置
     * 
     * @param generalSingle 单个邮箱设置
     * @return 结果
     */
    public int updateGeneralSingle(GeneralSingle generalSingle);

    /**
     * 删除单个邮箱设置
     * 
     * @param id 单个邮箱设置主键
     * @return 结果
     */
    public int deleteGeneralSingleById(Long id);

    /**
     * 批量删除单个邮箱设置
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGeneralSingleByIds(Long[] ids);
}
