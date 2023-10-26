package com.ruoyi.customer.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.customer.domain.dto.PublicleadsClaimLimitAddDTO;
import com.ruoyi.customer.domain.vo.PublicleadsClaimLimitListVO;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.PublicleadsClaimLimitMapper;
import com.ruoyi.customer.domain.PublicleadsClaimLimit;
import com.ruoyi.customer.service.IPublicleadsClaimLimitService;

import javax.annotation.Resource;

/**
 * 领取上限Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
@Service
public class PublicleadsClaimLimitServiceImpl implements IPublicleadsClaimLimitService 
{
    @Resource
    private PublicleadsClaimLimitMapper publicleadsClaimLimitMapper;

    /**
     * 新增领取上限
     *
     * @return 结果
     */
    @Override
    public int insertPublicleadsClaimLimit(PublicleadsClaimLimitAddDTO publicleadsClaimLimitAddDTO)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        // 查询所有的白名单人员
        List<PublicleadsClaimLimit> publicleadsClaimLimits = publicleadsClaimLimitMapper.selectPublicleadsClaimLimitList(new PublicleadsClaimLimit());
        Set<Long> existUserIdSet = publicleadsClaimLimits.stream().map(PublicleadsClaimLimit::getUserId).collect(Collectors.toSet());
        List<PublicleadsClaimLimit> publicleadsClaimLimitList = new ArrayList<>();
        for (Long userIdParam : publicleadsClaimLimitAddDTO.getUserIdList()) {
            if (!existUserIdSet.contains(userIdParam)) {
                PublicleadsClaimLimit publicleadsClaimLimit = new PublicleadsClaimLimit();
                publicleadsClaimLimit.setUserId(userIdParam);
                publicleadsClaimLimit.setClaimLimit(publicleadsClaimLimitAddDTO.getClaimLimit());
                publicleadsClaimLimit.setClaimPeriod(publicleadsClaimLimitAddDTO.getClaimPeriod());
                publicleadsClaimLimit.setCreateId(userId);
                publicleadsClaimLimit.setCreateBy(username);
                publicleadsClaimLimit.setCreateTime(DateUtils.getNowDate());
                publicleadsClaimLimit.setUpdateId(userId);
                publicleadsClaimLimit.setUpdateBy(username);
                publicleadsClaimLimit.setUpdateTime(DateUtils.getNowDate());
                publicleadsClaimLimitList.add(publicleadsClaimLimit);
            }
        }

        return publicleadsClaimLimitMapper.batchInsertPublicleadsClaimLimit(publicleadsClaimLimitList);
    }

    /**
     * 删除领取上限信息
     * 
     * @param id 领取上限主键
     * @return 结果
     */
    @Override
    public int deletePublicleadsClaimLimitById(Long id)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        return publicleadsClaimLimitMapper.deletePublicleadsClaimLimitById(id, userId, username);
    }

    @Override
    public List<PublicleadsClaimLimitListVO> list() {
        List<PublicleadsClaimLimit> publicleadsClaimLimitList = publicleadsClaimLimitMapper.selectPublicleadsClaimLimitList(new PublicleadsClaimLimit());
        List<PublicleadsClaimLimitListVO> publicleadsClaimLimitVOList = new ArrayList<>();
        for (PublicleadsClaimLimit publicleadsClaimLimit : publicleadsClaimLimitList) {
            PublicleadsClaimLimitListVO publicleadsClaimLimitVO = new PublicleadsClaimLimitListVO();
            BeanUtils.copyProperties(publicleadsClaimLimitVO, publicleadsClaimLimit);
            publicleadsClaimLimitVOList.add(publicleadsClaimLimitVO);
        }

        return publicleadsClaimLimitVOList;
    }
}
