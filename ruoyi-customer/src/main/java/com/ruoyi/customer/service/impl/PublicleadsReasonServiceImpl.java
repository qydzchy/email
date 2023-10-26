package com.ruoyi.customer.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.customer.domain.vo.PublicleadsReasonListVO;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.PublicleadsReasonMapper;
import com.ruoyi.customer.domain.PublicleadsReason;
import com.ruoyi.customer.service.IPublicleadsReasonService;

import javax.annotation.Resource;

/**
 * 移入公海原因Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
@Service
public class PublicleadsReasonServiceImpl implements IPublicleadsReasonService 
{
    @Resource
    private PublicleadsReasonMapper publicleadsReasonMapper;

    /**
     * 新增移入公海原因
     * 
     * @param publicleadsReason 移入公海原因
     * @return 结果
     */
    @Override
    public int insertPublicleadsReason(PublicleadsReason publicleadsReason)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        publicleadsReason.setCreateId(userId);
        publicleadsReason.setCreateBy(username);
        publicleadsReason.setCreateTime(DateUtils.getNowDate());
        publicleadsReason.setUpdateId(userId);
        publicleadsReason.setUpdateBy(username);
        publicleadsReason.setUpdateTime(DateUtils.getNowDate());
        return publicleadsReasonMapper.insertPublicleadsReason(publicleadsReason);
    }

    /**
     * 修改移入公海原因
     * 
     * @param publicleadsReason 移入公海原因
     * @return 结果
     */
    @Override
    public int updatePublicleadsReason(PublicleadsReason publicleadsReason)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        publicleadsReason.setUpdateId(userId);
        publicleadsReason.setUpdateBy(username);
        publicleadsReason.setUpdateTime(DateUtils.getNowDate());
        return publicleadsReasonMapper.updatePublicleadsReason(publicleadsReason);
    }

    /**
     * 删除移入公海原因信息
     * 
     * @param id 移入公海原因主键
     * @return 结果
     */
    @Override
    public int deletePublicleadsReasonById(Long id)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        return publicleadsReasonMapper.deletePublicleadsReasonById(id, userId, username);
    }

    /**
     * 查询移入公海原因列表
     * @return
     */
    @Override
    public List<PublicleadsReasonListVO> list() {
        List<PublicleadsReason> publicleadsReasonList = publicleadsReasonMapper.selectPublicleadsReasonList(new PublicleadsReason());
        List<PublicleadsReasonListVO> publicleadsReasonVOList = new ArrayList<>();
        for (PublicleadsReason publicleadsReason : publicleadsReasonList) {
            PublicleadsReasonListVO publicleadsReasonVO = new PublicleadsReasonListVO();
            publicleadsReasonVO.setId(publicleadsReason.getId());
            publicleadsReasonVO.setReason(publicleadsReason.getReason());
            publicleadsReasonVOList.add(publicleadsReasonVO);
        }

        return publicleadsReasonVOList;
    }
}
