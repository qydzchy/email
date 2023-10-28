package com.ruoyi.customer.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.customer.domain.LimitsListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.LimitsMapper;
import com.ruoyi.customer.domain.Limits;
import com.ruoyi.customer.service.ILimitsService;

import javax.annotation.Resource;

/**
 * 客户上限Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
@Service
public class LimitsServiceImpl implements ILimitsService 
{
    @Resource
    private LimitsMapper limitsMapper;

    /**
     * 查询客户上限
     * 
     * @param id 客户上限主键
     * @return 客户上限
     */
    @Override
    public Limits selectLimitsById(Long id)
    {
        return limitsMapper.selectLimitsById(id);
    }

    /**
     * 查询客户上限列表
     * 
     * @param limits 客户上限
     * @return 客户上限
     */
    @Override
    public List<Limits> selectLimitsList(Limits limits)
    {
        return limitsMapper.selectLimitsList(limits);
    }

    /**
     * 新增客户上限
     * 
     * @param limits 客户上限
     * @return 结果
     */
    @Override
    public int insertLimits(Limits limits)
    {
        limits.setCreateTime(DateUtils.getNowDate());
        return limitsMapper.insertLimits(limits);
    }

    /**
     * 修改客户上限
     * 
     * @param limits 客户上限
     * @return 结果
     */
    @Override
    public int updateLimits(Limits limits)
    {
        limits.setUpdateTime(DateUtils.getNowDate());
        return limitsMapper.updateLimits(limits);
    }

    /**
     * 批量删除客户上限
     * 
     * @param ids 需要删除的客户上限主键
     * @return 结果
     */
    @Override
    public int deleteLimitsByIds(Long[] ids)
    {
        return limitsMapper.deleteLimitsByIds(ids);
    }

    /**
     * 删除客户上限信息
     * 
     * @param id 客户上限主键
     * @return 结果
     */
    @Override
    public int deleteLimitsById(Long id)
    {
        return limitsMapper.deleteLimitsById(id);
    }

    /**
     * 客户上限列表
     * @return
     */
    @Override
    public List<LimitsListVO> list() {
        return limitsMapper.list();
    }
}
