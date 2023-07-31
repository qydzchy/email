package com.ruoyi.email.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.GeneralMapper;
import com.ruoyi.email.domain.General;
import com.ruoyi.email.service.IGeneralService;

/**
 * 邮箱常规Service业务层处理
 * 
 * @author tangJM
 * @date 2023-07-31
 */
@Service
public class GeneralServiceImpl implements IGeneralService 
{
    @Autowired
    private GeneralMapper generalMapper;

    /**
     * 查询邮箱常规
     * 
     * @param id 邮箱常规主键
     * @return 邮箱常规
     */
    @Override
    public General selectGeneralById(Long id)
    {
        return generalMapper.selectGeneralById(id);
    }

    /**
     * 查询邮箱常规列表
     * 
     * @param general 邮箱常规
     * @return 邮箱常规
     */
    @Override
    public List<General> selectGeneralList(General general)
    {
        return generalMapper.selectGeneralList(general);
    }

    /**
     * 新增邮箱常规
     * 
     * @param general 邮箱常规
     * @return 结果
     */
    @Override
    public int insertGeneral(General general)
    {
        general.setCreateTime(DateUtils.getNowDate());
        return generalMapper.insertGeneral(general);
    }

    /**
     * 修改邮箱常规
     * 
     * @param general 邮箱常规
     * @return 结果
     */
    @Override
    public int updateGeneral(General general)
    {
        general.setUpdateTime(DateUtils.getNowDate());
        return generalMapper.updateGeneral(general);
    }

    /**
     * 批量删除邮箱常规
     * 
     * @param ids 需要删除的邮箱常规主键
     * @return 结果
     */
    @Override
    public int deleteGeneralByIds(Long[] ids)
    {
        return generalMapper.deleteGeneralByIds(ids);
    }

    /**
     * 删除邮箱常规信息
     * 
     * @param id 邮箱常规主键
     * @return 结果
     */
    @Override
    public int deleteGeneralById(Long id)
    {
        return generalMapper.deleteGeneralById(id);
    }
}
