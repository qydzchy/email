package com.ruoyi.customer.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.customer.domain.vo.FollowUpTemplatesListVO;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.FollowUpTemplatesMapper;
import com.ruoyi.customer.domain.FollowUpTemplates;
import com.ruoyi.customer.service.IFollowUpTemplatesService;

import javax.annotation.Resource;

/**
 * 写跟进模板Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
@Service
public class FollowUpTemplatesServiceImpl implements IFollowUpTemplatesService 
{
    @Resource
    private FollowUpTemplatesMapper followUpTemplatesMapper;

    /**
     * 新增写跟进模板
     * 
     * @param followUpTemplates 写跟进模板
     * @return 结果
     */
    @Override
    public int insertFollowUpTemplates(FollowUpTemplates followUpTemplates)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        followUpTemplates.setCreateId(userId);
        followUpTemplates.setCreateBy(username);
        followUpTemplates.setCreateTime(DateUtils.getNowDate());
        followUpTemplates.setUpdateId(userId);
        followUpTemplates.setUpdateBy(username);
        followUpTemplates.setUpdateTime(DateUtils.getNowDate());
        return followUpTemplatesMapper.insertFollowUpTemplates(followUpTemplates);
    }

    /**
     * 修改写跟进模板
     * 
     * @param followUpTemplates 写跟进模板
     * @return 结果
     */
    @Override
    public int updateFollowUpTemplates(FollowUpTemplates followUpTemplates)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        followUpTemplates.setUpdateId(userId);
        followUpTemplates.setUpdateBy(username);
        followUpTemplates.setUpdateTime(DateUtils.getNowDate());
        return followUpTemplatesMapper.updateFollowUpTemplates(followUpTemplates);
    }

    /**
     * 删除写跟进模板信息
     * 
     * @param id 写跟进模板主键
     * @return 结果
     */
    @Override
    public int deleteFollowUpTemplatesById(Long id)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        return followUpTemplatesMapper.deleteFollowUpTemplatesById(id, userId, username);
    }

    /**
     * 跟进模板列表
     * @return
     */
    @Override
    public List<FollowUpTemplatesListVO> list() {
        List<FollowUpTemplates> followUpTemplatesList = followUpTemplatesMapper.selectFollowUpTemplatesList(new FollowUpTemplates());
        List<FollowUpTemplatesListVO> followUpTemplatesVOList = new ArrayList<>();
        for (FollowUpTemplates followUpTemplates : followUpTemplatesList) {
            FollowUpTemplatesListVO followUpTemplatesVO = new FollowUpTemplatesListVO();
            followUpTemplatesVO.setId(followUpTemplates.getId());
            followUpTemplatesVO.setName(followUpTemplates.getName());
            followUpTemplatesVO.setContent(followUpTemplates.getContent());
            followUpTemplatesVOList.add(followUpTemplatesVO);
        }

        return followUpTemplatesVOList;
    }
}
