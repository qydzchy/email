package com.ruoyi.customer.service.impl;

import java.util.ArrayList;
import java.util.List;
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
}
