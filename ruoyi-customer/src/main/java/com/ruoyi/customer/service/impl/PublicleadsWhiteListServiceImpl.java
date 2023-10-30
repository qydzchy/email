package com.ruoyi.customer.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.customer.domain.vo.PublicleadsWhiteListListVO;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.PublicleadsWhiteListMapper;
import com.ruoyi.customer.domain.PublicleadsWhiteList;
import com.ruoyi.customer.service.IPublicleadsWhiteListService;

import javax.annotation.Resource;

/**
 * 移入公海白名单Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
@Service
public class PublicleadsWhiteListServiceImpl implements IPublicleadsWhiteListService 
{
    @Resource
    private PublicleadsWhiteListMapper publicleadsWhiteListMapper;

    /**
     * 新增移入公海白名单
     *
     * @return 结果
     */
    @Override
    public int insertPublicleadsWhiteList(List<Long> userIdList)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        // 查询所有的白名单人员
        List<PublicleadsWhiteList> publicleadsWhiteLists = publicleadsWhiteListMapper.selectPublicleadsWhiteListList(new PublicleadsWhiteList());
        Set<Long> existUserIdSet = publicleadsWhiteLists.stream().map(PublicleadsWhiteList::getUserId).collect(Collectors.toSet());
        List<PublicleadsWhiteList> publicleadsWhiteListList = new ArrayList<>();
        for (Long userIdParam : userIdList) {
            if (!existUserIdSet.contains(userIdParam)) {
                PublicleadsWhiteList publicleadsWhiteList = new PublicleadsWhiteList();
                publicleadsWhiteList.setUserId(userIdParam);
                publicleadsWhiteList.setCreateId(userId);
                publicleadsWhiteList.setCreateBy(username);
                publicleadsWhiteList.setCreateTime(DateUtils.getNowDate());
                publicleadsWhiteList.setUpdateId(userId);
                publicleadsWhiteList.setUpdateBy(username);
                publicleadsWhiteList.setUpdateTime(DateUtils.getNowDate());
                publicleadsWhiteListList.add(publicleadsWhiteList);
            }
        }

        return publicleadsWhiteListMapper.batchInsertPublicleadsWhiteList(publicleadsWhiteListList);
    }

    /**
     * 删除移入公海白名单信息
     *
     * @param id 移入公海白名单主键
     * @return 结果
     */
    @Override
    public int deletePublicleadsWhiteListById(Long id)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        return publicleadsWhiteListMapper.deletePublicleadsWhiteListById(id, userId, username);
    }

    @Override
    public List<PublicleadsWhiteListListVO> list() {
        return publicleadsWhiteListMapper.list();
    }
}
