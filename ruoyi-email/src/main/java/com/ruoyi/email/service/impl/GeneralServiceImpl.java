package com.ruoyi.email.service.impl;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.email.domain.vo.GeneralVO;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.GeneralMapper;
import com.ruoyi.email.domain.General;
import com.ruoyi.email.service.IGeneralService;

import javax.annotation.Resource;

/**
 * 邮箱常规Service业务层处理
 * 
 * @author tangJM
 * @date 2023-07-31
 */
@Service
public class GeneralServiceImpl implements IGeneralService 
{
    @Resource
    private GeneralMapper generalMapper;

    /**
     * 查询邮箱常规
     * 
     * @param id 邮箱常规主键
     * @return 邮箱常规
     */
    @Override
    public General selectGeneralById(Long id)
    {
        return generalMapper.selectGeneralById(id);
    }

    /**
     * 查询邮箱常规列表
     * 
     * @param general 邮箱常规
     * @return 邮箱常规
     */
    @Override
    public List<General> selectGeneralList(General general)
    {
        return generalMapper.selectGeneralList(general);
    }

    /**
     * 新增邮箱常规
     * 
     * @param general 邮箱常规
     * @return 结果
     */
    @Override
    public int insertGeneral(General general)
    {
        general.setCreateTime(DateUtils.getNowDate());
        return generalMapper.insertGeneral(general);
    }

    /**
     * 修改邮箱常规
     * 
     * @param general 邮箱常规
     * @return 结果
     */
    @Override
    public int updateGeneral(General general)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();
        Date now = DateUtils.getNowDate();

        general.setUpdateId(userId);
        general.setUpdateBy(username);
        general.setUpdateTime(now);

        if (general.getId() == null) {
            general.setCreateId(userId);
            general.setCreateBy(username);
            general.setCreateTime(now);
            return generalMapper.insertGeneral(general);
        }

        return generalMapper.updateGeneral(general);
    }

    /**
     * 批量删除邮箱常规
     * 
     * @param ids 需要删除的邮箱常规主键
     * @return 结果
     */
    @Override
    public int deleteGeneralByIds(Long[] ids)
    {
        return generalMapper.deleteGeneralByIds(ids);
    }

    /**
     * 删除邮箱常规信息
     * 
     * @param id 邮箱常规主键
     * @return 结果
     */
    @Override
    public int deleteGeneralById(Long id)
    {
        return generalMapper.deleteGeneralById(id);
    }

    /**
     * 获取邮箱常规详细信息
     * @return
     */
    @Override
    public GeneralVO getInfo() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();

        return generalMapper.getByCreateId(userId);
    }
}
