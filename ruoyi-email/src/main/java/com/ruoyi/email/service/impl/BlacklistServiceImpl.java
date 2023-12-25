package com.ruoyi.email.service.impl;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.EmailUtil;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.BlacklistMapper;
import com.ruoyi.email.domain.Blacklist;
import com.ruoyi.email.service.IBlacklistService;

import javax.annotation.Resource;

/**
 * 黑名单Service业务层处理
 * 
 * @author tangJM
 * @date 2023-07-31
 */
@Service
public class BlacklistServiceImpl implements IBlacklistService 
{
    @Resource
    private BlacklistMapper blacklistMapper;

    /**
     * 查询黑名单
     * 
     * @param id 黑名单主键
     * @return 黑名单
     */
    @Override
    public Blacklist selectBlacklistById(Long id)
    {
        return blacklistMapper.selectBlacklistById(id);
    }

    /**
     * 查询黑名单列表
     * 
     * @param blacklist 黑名单
     * @return 黑名单
     */
    @Override
    public List<Blacklist> selectBlacklistList(Blacklist blacklist)
    {
        return blacklistMapper.selectBlacklistList(blacklist);
    }

    /**
     * 新增黑名单
     * 
     * @param blacklist 黑名单
     * @return 结果
     */
    @Override
    public int insertBlacklist(Blacklist blacklist)
    {
        if (blacklist.getType() == 1) {
            if (!EmailUtil.isValidEmail(blacklist.getContent())) {
                throw new ServiceException("邮箱格式不正确");
            }
        } else if (blacklist.getType() == 2) {
            if (!blacklist.getContent().startsWith("@")) {
                throw new ServiceException("域名格式不正确");
            }
        }

        // 判断内容是否有重复
        int count = blacklistMapper.countByTypeAndContent(blacklist.getType(), blacklist.getContent());
        if (count > 0) {
            if (blacklist.getType() == 1) {
                throw new ServiceException("邮箱已存在");
            } else if (blacklist.getType() == 2) {
                throw new ServiceException("域名已存在");
            }
        }

        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();
        Date now = DateUtils.getNowDate();

        blacklist.setCreateId(userId);
        blacklist.setCreateBy(username);
        blacklist.setCreateTime(now);
        blacklist.setUpdateId(userId);
        blacklist.setUpdateBy(username);
        blacklist.setUpdateTime(now);
        return blacklistMapper.insertBlacklist(blacklist);
    }

    /**
     * 修改黑名单
     * 
     * @param blacklist 黑名单
     * @return 结果
     */
    @Override
    public int updateBlacklist(Blacklist blacklist)
    {
        blacklist.setUpdateTime(DateUtils.getNowDate());
        return blacklistMapper.updateBlacklist(blacklist);
    }

    /**
     * 批量删除黑名单
     * 
     * @param ids 需要删除的黑名单主键
     * @return 结果
     */
    @Override
    public int deleteBlacklistByIds(Long[] ids)
    {
        return blacklistMapper.deleteBlacklistByIds(ids);
    }

    /**
     * 删除黑名单信息
     * 
     * @param id 黑名单主键
     * @return 结果
     */
    @Override
    public int deleteBlacklistById(Long id)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        return blacklistMapper.deleteBlacklistById(id, userId, username);
    }
}
