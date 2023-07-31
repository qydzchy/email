package com.ruoyi.email.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.GeneralSingleMapper;
import com.ruoyi.email.domain.GeneralSingle;
import com.ruoyi.email.service.IGeneralSingleService;

/**
 * 单个邮箱设置Service业务层处理
 * 
 * @author tangJM
 * @date 2023-07-31
 */
@Service
public class GeneralSingleServiceImpl implements IGeneralSingleService 
{
    @Autowired
    private GeneralSingleMapper generalSingleMapper;

    /**
     * 查询单个邮箱设置
     * 
     * @param id 单个邮箱设置主键
     * @return 单个邮箱设置
     */
    @Override
    public GeneralSingle selectGeneralSingleById(Long id)
    {
        return generalSingleMapper.selectGeneralSingleById(id);
    }

    /**
     * 查询单个邮箱设置列表
     * 
     * @param generalSingle 单个邮箱设置
     * @return 单个邮箱设置
     */
    @Override
    public List<GeneralSingle> selectGeneralSingleList(GeneralSingle generalSingle)
    {
        return generalSingleMapper.selectGeneralSingleList(generalSingle);
    }

    /**
     * 新增单个邮箱设置
     * 
     * @param generalSingle 单个邮箱设置
     * @return 结果
     */
    @Override
    public int insertGeneralSingle(GeneralSingle generalSingle)
    {
        generalSingle.setCreateTime(DateUtils.getNowDate());
        return generalSingleMapper.insertGeneralSingle(generalSingle);
    }

    /**
     * 修改单个邮箱设置
     * 
     * @param generalSingle 单个邮箱设置
     * @return 结果
     */
    @Override
    public int updateGeneralSingle(GeneralSingle generalSingle)
    {
        generalSingle.setUpdateTime(DateUtils.getNowDate());
        return generalSingleMapper.updateGeneralSingle(generalSingle);
    }

    /**
     * 批量删除单个邮箱设置
     * 
     * @param ids 需要删除的单个邮箱设置主键
     * @return 结果
     */
    @Override
    public int deleteGeneralSingleByIds(Long[] ids)
    {
        return generalSingleMapper.deleteGeneralSingleByIds(ids);
    }

    /**
     * 删除单个邮箱设置信息
     * 
     * @param id 单个邮箱设置主键
     * @return 结果
     */
    @Override
    public int deleteGeneralSingleById(Long id)
    {
        return generalSingleMapper.deleteGeneralSingleById(id);
    }
}
