package com.ruoyi.customer.service;

import com.ruoyi.customer.domain.Settings;

/**
 * 客户设置Service接口
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
public interface ISettingsService 
{
    /**
     * 查询客户设置
     *
     * @return 客户设置
     */
    public Settings selectSettings();

    /**
     * 修改客户设置
     * 
     * @param settings 客户设置
     * @return 结果
     */
    public boolean updateSettings(Settings settings);
}
