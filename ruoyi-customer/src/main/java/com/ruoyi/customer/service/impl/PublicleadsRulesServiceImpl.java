package com.ruoyi.customer.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.customer.domain.vo.PublicleadsRulesListVO;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.PublicleadsRulesMapper;
import com.ruoyi.customer.domain.PublicleadsRules;
import com.ruoyi.customer.service.IPublicleadsRulesService;

import javax.annotation.Resource;

/**
 * 移入公海规则Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
@Service
public class PublicleadsRulesServiceImpl implements IPublicleadsRulesService 
{
    @Resource
    private PublicleadsRulesMapper publicleadsRulesMapper;

    /**
     * 查询移入公海规则列表
     * 
     * @param publicleadsRules 移入公海规则
     * @return 移入公海规则
     */
    @Override
    public List<PublicleadsRules> selectPublicleadsRulesList(PublicleadsRules publicleadsRules)
    {
        return publicleadsRulesMapper.selectPublicleadsRulesList(publicleadsRules);
    }

    /**
     * 新增移入公海规则
     * 
     * @param publicleadsRules 移入公海规则
     * @return 结果
     */
    @Override
    public int insertPublicleadsRules(PublicleadsRules publicleadsRules)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        publicleadsRules.setCreateId(userId);
        publicleadsRules.setCreateBy(username);
        publicleadsRules.setCreateTime(DateUtils.getNowDate());
        publicleadsRules.setUpdateId(userId);
        publicleadsRules.setUpdateBy(username);
        publicleadsRules.setUpdateTime(DateUtils.getNowDate());
        return publicleadsRulesMapper.insertPublicleadsRules(publicleadsRules);
    }

    /**
     * 修改移入公海规则
     * 
     * @param publicleadsRules 移入公海规则
     * @return 结果
     */
    @Override
    public int updatePublicleadsRules(PublicleadsRules publicleadsRules)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        publicleadsRules.setUpdateId(userId);
        publicleadsRules.setUpdateBy(username);
        publicleadsRules.setUpdateTime(DateUtils.getNowDate());
        return publicleadsRulesMapper.updatePublicleadsRules(publicleadsRules);
    }

    /**
     * 删除移入公海规则信息
     * 
     * @param id 移入公海规则主键
     * @return 结果
     */
    @Override
    public int deletePublicleadsRulesById(Long id)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        return publicleadsRulesMapper.deletePublicleadsRulesById(id, userId, username);
    }

    @Override
    public List<PublicleadsRulesListVO> list() {
        List<PublicleadsRules> publicleadsRuleList = selectPublicleadsRulesList(new PublicleadsRules());
        List<PublicleadsRulesListVO> publicleadsRulesListVOList = new ArrayList<>();

        for (PublicleadsRules publicleadsRules : publicleadsRuleList) {
            PublicleadsRulesListVO publicleadsRulesListVO = new PublicleadsRulesListVO();
            BeanUtils.copyProperties(publicleadsRules, publicleadsRulesListVO);
            publicleadsRulesListVOList.add(publicleadsRulesListVO);
        }

        return publicleadsRulesListVOList;
    }
}
