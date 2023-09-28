package com.ruoyi.email.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.email.domain.vo.label.LabelListVO;
import com.ruoyi.email.service.ILabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.LabelMapper;
import com.ruoyi.email.domain.Label;

import javax.annotation.Resource;

/**
 * 标签Service业务层处理
 * 
 * @author tangJM
 * @date 2023-07-31
 */
@Service
public class LabelServiceImpl implements ILabelService
{
    @Resource
    private LabelMapper labelMapper;

    /**
     * 查询标签
     * 
     * @param id 标签主键
     * @return 标签
     */
    @Override
    public Label selectLabelById(Long id)
    {
        return labelMapper.selectLabelById(id);
    }

    /**
     * 查询标签列表
     * 
     * @param label 标签
     * @return 标签
     */
    @Override
    public List<Label> selectLabelList(Label label)
    {
        return labelMapper.selectLabelList(label);
    }

    /**
     * 新增标签
     * 
     * @param label 标签
     * @return 结果
     */
    @Override
    public int insertLabel(Label label)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();
        label.setCreateId(userId);
        label.setCreateBy(username);
        label.setCreateTime(DateUtils.getNowDate());
        return labelMapper.insertLabel(label);
    }

    /**
     * 修改标签
     * 
     * @param label 标签
     * @return 结果
     */
    @Override
    public int updateLabel(Label label)
    {
        label.setUpdateTime(DateUtils.getNowDate());
        return labelMapper.updateLabel(label);
    }

    /**
     * 批量删除标签
     * 
     * @param ids 需要删除的标签主键
     * @return 结果
     */
    @Override
    public int deleteLabelByIds(Long[] ids)
    {
        return labelMapper.deleteLabelByIds(ids);
    }

    /**
     * 删除标签信息
     * 
     * @param id 标签主键
     * @return 结果
     */
    @Override
    public int deleteLabelById(Long id)
    {
        return labelMapper.deleteLabelById(id);
    }

    @Override
    public List<LabelListVO> list() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        List<LabelListVO> labelListVOList = labelMapper.getByCreateId(userId);
        return labelListVOList;
    }

    @Override
    public boolean editColor(Long id, String color) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        labelMapper.updateColor(id, color, userId, DateUtils.getNowDate());
        return true;
    }

    @Override
    public boolean editName(Long id, String name) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        labelMapper.updateName(id, name, userId, DateUtils.getNowDate());
        return false;
    }

    @Override
    public boolean delete(Long id) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        labelMapper.deleteById(id, userId, DateUtils.getNowDate());
        return false;
    }
}
