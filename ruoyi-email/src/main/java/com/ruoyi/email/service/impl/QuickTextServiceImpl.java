package com.ruoyi.email.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.email.domain.vo.QuickTextListVO;
import com.ruoyi.email.service.IQuickTextService;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.QuickTextMapper;
import com.ruoyi.email.domain.QuickText;

import javax.annotation.Resource;

/**
 * 快速文本Service业务层处理
 * 
 * @author tangJM
 * @date 2023-07-31
 */
@Service
public class QuickTextServiceImpl implements IQuickTextService
{
    @Resource
    private QuickTextMapper quickTextMapper;

    /**
     * 查询快速文本
     * 
     * @param id 快速文本主键
     * @return 快速文本
     */
    @Override
    public QuickText selectQuickTextById(Long id)
    {
        return quickTextMapper.selectQuickTextById(id);
    }

    /**
     * 查询快速文本列表
     * 
     * @param quickText 快速文本
     * @return 快速文本
     */
    @Override
    public List<QuickText> selectQuickTextList(QuickText quickText)
    {
        return quickTextMapper.selectQuickTextList(quickText);
    }

    /**
     * 新增快速文本
     * 
     * @param quickText 快速文本
     * @return 结果
     */
    @Override
    public int insertQuickText(QuickText quickText)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();
        quickText.setCreateId(userId);
        quickText.setCreateBy(username);
        quickText.setCreateTime(DateUtils.getNowDate());
        quickText.setUpdateId(userId);
        quickText.setUpdateBy(username);
        quickText.setUpdateTime(DateUtils.getNowDate());
        return quickTextMapper.insertQuickText(quickText);
    }

    /**
     * 修改快速文本
     * 
     * @param quickText 快速文本
     * @return 结果
     */
    @Override
    public int updateQuickText(QuickText quickText)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();
        quickText.setUpdateId(userId);
        quickText.setUpdateBy(username);
        quickText.setUpdateTime(DateUtils.getNowDate());
        return quickTextMapper.updateQuickText(quickText);
    }

    /**
     * 批量删除快速文本
     * 
     * @param ids 需要删除的快速文本主键
     * @return 结果
     */
    @Override
    public int deleteQuickTextByIds(Long[] ids)
    {
        return quickTextMapper.deleteQuickTextByIds(ids);
    }

    /**
     * 删除快速文本信息
     * 
     * @param id 快速文本主键
     * @return 结果
     */
    @Override
    public int deleteQuickTextById(Long id)
    {
        return quickTextMapper.deleteQuickTextById(id);
    }

    @Override
    public List<QuickTextListVO> list() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        List<QuickTextListVO> quickTextListVOList = quickTextMapper.getByCreateId(userId);
        return quickTextListVOList;
    }

    @Override
    public boolean delete(Long id) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        quickTextMapper.deleteById(id, userId, DateUtils.getNowDate());
        return true;
    }
}
