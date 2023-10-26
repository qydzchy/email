package com.ruoyi.customer.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.customer.domain.vo.FollowUpQuickTextListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.FollowUpQuickTextMapper;
import com.ruoyi.customer.domain.FollowUpQuickText;
import com.ruoyi.customer.service.IFollowUpQuickTextService;

import javax.annotation.Resource;

/**
 * 写跟进快捷文本Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
@Service
public class FollowUpQuickTextServiceImpl implements IFollowUpQuickTextService 
{
    @Resource
    private FollowUpQuickTextMapper followUpQuickTextMapper;

    /**
     * 新增写跟进快捷文本
     * 
     * @param followUpQuickText 写跟进快捷文本
     * @return 结果
     */
    @Override
    public int insertFollowUpQuickText(FollowUpQuickText followUpQuickText)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        followUpQuickText.setCreateId(userId);
        followUpQuickText.setCreateBy(username);
        followUpQuickText.setCreateTime(DateUtils.getNowDate());
        followUpQuickText.setUpdateId(userId);
        followUpQuickText.setUpdateBy(username);
        followUpQuickText.setUpdateTime(DateUtils.getNowDate());
        return followUpQuickTextMapper.insertFollowUpQuickText(followUpQuickText);
    }

    /**
     * 修改写跟进快捷文本
     * 
     * @param followUpQuickText 写跟进快捷文本
     * @return 结果
     */
    @Override
    public int updateFollowUpQuickText(FollowUpQuickText followUpQuickText)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        followUpQuickText.setUpdateId(userId);
        followUpQuickText.setUpdateBy(username);
        followUpQuickText.setUpdateTime(DateUtils.getNowDate());
        return followUpQuickTextMapper.updateFollowUpQuickText(followUpQuickText);
    }

    /**
     * 删除写跟进快捷文本信息
     * 
     * @param id 写跟进快捷文本主键
     * @return 结果
     */
    @Override
    public int deleteFollowUpQuickTextById(Long id)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        return followUpQuickTextMapper.deleteFollowUpQuickTextById(id, userId, username);
    }

    @Override
    public List<FollowUpQuickTextListVO> list() {
        List<FollowUpQuickText> followUpQuickTextList = followUpQuickTextMapper.selectFollowUpQuickTextList(new FollowUpQuickText());
        List<FollowUpQuickTextListVO> followUpQuickTextVOList = new ArrayList<>();
        for (FollowUpQuickText followUpQuickText : followUpQuickTextList) {
            FollowUpQuickTextListVO followUpQuickTextVO = new FollowUpQuickTextListVO();
            followUpQuickTextVO.setId(followUpQuickText.getId());
            followUpQuickTextVO.setName(followUpQuickText.getName());
            followUpQuickTextVO.setLabel(followUpQuickTextVO.getLabel());
            followUpQuickTextVOList.add(followUpQuickTextVO);
        }

        return followUpQuickTextVOList;
    }
}
