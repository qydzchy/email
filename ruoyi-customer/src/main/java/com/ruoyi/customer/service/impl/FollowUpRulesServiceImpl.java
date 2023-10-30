package com.ruoyi.customer.service.impl;

import java.util.ArrayList;
import java.util.List;
import com.ruoyi.customer.domain.vo.FollowUpRulesListVO;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.FollowUpRulesMapper;
import com.ruoyi.customer.domain.FollowUpRules;
import com.ruoyi.customer.service.IFollowUpRulesService;

import javax.annotation.Resource;

/**
 * 客户跟进规则Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
@Service
public class FollowUpRulesServiceImpl implements IFollowUpRulesService 
{
    @Resource
    private FollowUpRulesMapper followUpRulesMapper;

    /**
     * 查询客户跟进规则列表
     * @return
     */
    @Override
    public List<FollowUpRulesListVO> list() {
        List<FollowUpRules> followUpRulesList = followUpRulesMapper.selectFollowUpRulesList(new FollowUpRules());
        List<FollowUpRulesListVO> followUpRulesVOList = new ArrayList<>();
        for (FollowUpRules followUpRules : followUpRulesList) {
            FollowUpRulesListVO followUpRulesVO = new FollowUpRulesListVO();
            followUpRulesVO.setId(followUpRules.getId());
            followUpRulesVO.setName(followUpRules.getName());
            followUpRulesVO.setType(followUpRules.getType());
            followUpRulesVO.setActiveFlag(followUpRules.getActiveFlag());
            followUpRulesVOList.add(followUpRulesVO);
        }

        return followUpRulesVOList;
    }

    @Override
    public int updateActiveFlag(Long id) {
        return followUpRulesMapper.updateActiveFlag(id);
    }
}
