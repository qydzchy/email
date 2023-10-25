package com.ruoyi.customer.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.SettingsMapper;
import com.ruoyi.customer.domain.Settings;
import com.ruoyi.customer.service.ISettingsService;

/**
 * 客户设置Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
@Service
public class SettingsServiceImpl implements ISettingsService 
{
    @Autowired
    private SettingsMapper settingsMapper;

    /**
     * 查询客户设置
     * 
     * @param id 客户设置主键
     * @return 客户设置
     */
    @Override
    public Settings selectSettingsById(Long id)
    {
        return settingsMapper.selectSettingsById(id);
    }

    /**
     * 查询客户设置列表
     * 
     * @param settings 客户设置
     * @return 客户设置
     */
    @Override
    public List<Settings> selectSettingsList(Settings settings)
    {
        return settingsMapper.selectSettingsList(settings);
    }

    /**
     * 新增客户设置
     * 
     * @param settings 客户设置
     * @return 结果
     */
    @Override
    public int insertSettings(Settings settings)
    {
        settings.setCreateTime(DateUtils.getNowDate());
        return settingsMapper.insertSettings(settings);
    }

    /**
     * 修改客户设置
     * 
     * @param settings 客户设置
     * @return 结果
     */
    @Override
    public int updateSettings(Settings settings)
    {
        settings.setUpdateTime(DateUtils.getNowDate());
        return settingsMapper.updateSettings(settings);
    }

    /**
     * 批量删除客户设置
     * 
     * @param ids 需要删除的客户设置主键
     * @return 结果
     */
    @Override
    public int deleteSettingsByIds(Long[] ids)
    {
        return settingsMapper.deleteSettingsByIds(ids);
    }

    /**
     * 删除客户设置信息
     * 
     * @param id 客户设置主键
     * @return 结果
     */
    @Override
    public int deleteSettingsById(Long id)
    {
        return settingsMapper.deleteSettingsById(id);
    }
}
