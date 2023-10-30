package com.ruoyi.customer.service.impl;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.customer.domain.vo.SettingsGetVO;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.SettingsMapper;
import com.ruoyi.customer.domain.Settings;
import com.ruoyi.customer.service.ISettingsService;

import javax.annotation.Resource;

/**
 * 客户设置Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
@Service
public class SettingsServiceImpl implements ISettingsService 
{
    @Resource
    private SettingsMapper settingsMapper;

    @Override
    public SettingsGetVO selectSettings() {
        SettingsGetVO settingsGetVO = settingsMapper.selectSettings();
        return settingsGetVO != null ? settingsGetVO : new SettingsGetVO();
    }

    /**
     * 修改客户设置
     * 
     * @param settings 客户设置
     * @return 结果
     */
    @Override
    public boolean updateSettings(Settings settings)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        settings.setUpdateId(userId);
        settings.setUpdateBy(username);
        settings.setUpdateTime(DateUtils.getNowDate());

        if (settings.getId() == null) {
            settings.setCreateId(userId);
            settings.setCreateBy(username);
            settings.setCreateTime(DateUtils.getNowDate());

            settingsMapper.insertSettings(settings);
        } else {
            settingsMapper.updateSettings(settings);
        }

        return true;
    }

}
