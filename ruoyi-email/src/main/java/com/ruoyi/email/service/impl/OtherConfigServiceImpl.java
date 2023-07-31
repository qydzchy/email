package com.ruoyi.email.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.email.service.IOtherConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.OtherConfigMapper;
import com.ruoyi.email.domain.OtherConfig;

/**
 * 其他配置Service业务层处理
 * 
 * @author tangJM
 * @date 2023-07-31
 */
@Service
public class OtherConfigServiceImpl implements IOtherConfigService {
    @Autowired
    private OtherConfigMapper otherConfigMapper;

    /**
     * 查询其他配置
     * 
     * @param id 其他配置主键
     * @return 其他配置
     */
    @Override
    public OtherConfig selectOtherConfigById(Long id)
    {
        return otherConfigMapper.selectOtherConfigById(id);
    }

    /**
     * 查询其他配置列表
     * 
     * @param otherConfig 其他配置
     * @return 其他配置
     */
    @Override
    public List<OtherConfig> selectOtherConfigList(OtherConfig otherConfig)
    {
        return otherConfigMapper.selectOtherConfigList(otherConfig);
    }

    /**
     * 新增其他配置
     * 
     * @param otherConfig 其他配置
     * @return 结果
     */
    @Override
    public int insertOtherConfig(OtherConfig otherConfig)
    {
        otherConfig.setCreateTime(DateUtils.getNowDate());
        return otherConfigMapper.insertOtherConfig(otherConfig);
    }

    /**
     * 修改其他配置
     * 
     * @param otherConfig 其他配置
     * @return 结果
     */
    @Override
    public int updateOtherConfig(OtherConfig otherConfig)
    {
        otherConfig.setUpdateTime(DateUtils.getNowDate());
        return otherConfigMapper.updateOtherConfig(otherConfig);
    }

    /**
     * 批量删除其他配置
     * 
     * @param ids 需要删除的其他配置主键
     * @return 结果
     */
    @Override
    public int deleteOtherConfigByIds(Long[] ids)
    {
        return otherConfigMapper.deleteOtherConfigByIds(ids);
    }

    /**
     * 删除其他配置信息
     * 
     * @param id 其他配置主键
     * @return 结果
     */
    @Override
    public int deleteOtherConfigById(Long id)
    {
        return otherConfigMapper.deleteOtherConfigById(id);
    }
}
