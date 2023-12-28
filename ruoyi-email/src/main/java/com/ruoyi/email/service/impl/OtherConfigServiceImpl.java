package com.ruoyi.email.service.impl;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.email.domain.vo.OtherConfigVO;
import com.ruoyi.email.service.IOtherConfigService;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.OtherConfigMapper;
import com.ruoyi.email.domain.OtherConfig;

import javax.annotation.Resource;

/**
 * 其他配置Service业务层处理
 * 
 * @author tangJM
 * @date 2023-07-31
 */
@Service
public class OtherConfigServiceImpl implements IOtherConfigService {

    @Resource
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
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();
        Date now = DateUtils.getNowDate();

        otherConfig.setUpdateId(userId);
        otherConfig.setUpdateBy(username);
        otherConfig.setUpdateTime(now);
        if (otherConfig.getId() == null) {
            otherConfig.setCreateId(userId);
            otherConfig.setCreateBy(username);
            otherConfig.setCreateTime(now);
            return otherConfigMapper.insertOtherConfig(otherConfig);
        }

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

    /**
     * 获取其他配置信息
     * @return
     */
    @Override
    public OtherConfigVO getInfo() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();

        OtherConfigVO otherConfigVO = otherConfigMapper.getByCreateId(userId);
        if (otherConfigVO == null) {
            otherConfigVO = new OtherConfigVO();
        }

        return otherConfigVO;
    }
}
