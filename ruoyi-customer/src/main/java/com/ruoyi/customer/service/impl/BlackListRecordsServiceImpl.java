package com.ruoyi.customer.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.customer.domain.vo.BlackListRecordsListVO;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.BlackListRecordsMapper;
import com.ruoyi.customer.domain.BlackListRecords;
import com.ruoyi.customer.service.IBlackListRecordsService;

import javax.annotation.Resource;

/**
 * 建档黑名单Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
@Service
public class BlackListRecordsServiceImpl implements IBlackListRecordsService 
{
    @Resource
    private BlackListRecordsMapper blackListRecordsMapper;

    /**
     * 建档黑名单
     * @return
     */
    @Override
    public List<BlackListRecordsListVO> list() {
        List<BlackListRecords> blackListRecordsList = blackListRecordsMapper.selectBlackListRecordsList(new BlackListRecords());
        List<BlackListRecordsListVO> blackListRecordsVOList = new ArrayList<>();
        for (BlackListRecords blackListRecords : blackListRecordsList) {
            BlackListRecordsListVO blackListRecordsVO = new BlackListRecordsListVO();
            blackListRecordsVO.setId(blackListRecords.getId());
            blackListRecordsVO.setDomain(blackListRecords.getDomain());
            blackListRecordsVOList.add(blackListRecordsVO);
        }

        return blackListRecordsVOList;
    }

    @Override
    public int batchDeleteByIds(List<Long> ids) {
        return blackListRecordsMapper.batchDeleteByIds(ids);
    }

    /**
     * 新建建档黑名单
     * @param blackListRecords
     * @return
     */
    @Override
    public int insertBlackListRecords(BlackListRecords blackListRecords) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        blackListRecords.setCreateId(userId);
        blackListRecords.setCreateBy(username);
        blackListRecords.setCreateTime(DateUtils.getNowDate());
        blackListRecords.setUpdateId(userId);
        blackListRecords.setUpdateBy(username);
        blackListRecords.setUpdateTime(DateUtils.getNowDate());
        return blackListRecordsMapper.insertBlackListRecords(blackListRecords);
    }
}
