package com.ruoyi.customer.service;

import java.util.List;
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
     * @param id 客户设置主键
     * @return 客户设置
     */
    public Settings selectSettingsById(Long id);

    /**
     * 查询客户设置列表
     * 
     * @param settings 客户设置
     * @return 客户设置集合
     */
    public List<Settings> selectSettingsList(Settings settings);

    /**
     * 新增客户设置
     * 
     * @param settings 客户设置
     * @return 结果
     */
    public int insertSettings(Settings settings);

    /**
     * 修改客户设置
     * 
     * @param settings 客户设置
     * @return 结果
     */
    public int updateSettings(Settings settings);

    /**
     * 批量删除客户设置
     * 
     * @param ids 需要删除的客户设置主键集合
     * @return 结果
     */
    public int deleteSettingsByIds(Long[] ids);

    /**
     * 删除客户设置信息
     * 
     * @param id 客户设置主键
     * @return 结果
     */
    public int deleteSettingsById(Long id);
}
