package com.ruoyi.customer.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.BlackListRecordsMapper;
import com.ruoyi.customer.domain.BlackListRecords;
import com.ruoyi.customer.service.IBlackListRecordsService;

/**
 * 建档黑名单Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
@Service
public class BlackListRecordsServiceImpl implements IBlackListRecordsService 
{
    @Autowired
    private BlackListRecordsMapper blackListRecordsMapper;

    /**
     * 查询建档黑名单
     * 
     * @param id 建档黑名单主键
     * @return 建档黑名单
     */
    @Override
    public BlackListRecords selectBlackListRecordsById(Long id)
    {
        return blackListRecordsMapper.selectBlackListRecordsById(id);
    }

    /**
     * 查询建档黑名单列表
     * 
     * @param blackListRecords 建档黑名单
     * @return 建档黑名单
     */
    @Override
    public List<BlackListRecords> selectBlackListRecordsList(BlackListRecords blackListRecords)
    {
        return blackListRecordsMapper.selectBlackListRecordsList(blackListRecords);
    }

    /**
     * 新增建档黑名单
     * 
     * @param blackListRecords 建档黑名单
     * @return 结果
     */
    @Override
    public int insertBlackListRecords(BlackListRecords blackListRecords)
    {
        blackListRecords.setCreateTime(DateUtils.getNowDate());
        return blackListRecordsMapper.insertBlackListRecords(blackListRecords);
    }

    /**
     * 修改建档黑名单
     * 
     * @param blackListRecords 建档黑名单
     * @return 结果
     */
    @Override
    public int updateBlackListRecords(BlackListRecords blackListRecords)
    {
        blackListRecords.setUpdateTime(DateUtils.getNowDate());
        return blackListRecordsMapper.updateBlackListRecords(blackListRecords);
    }

    /**
     * 批量删除建档黑名单
     * 
     * @param ids 需要删除的建档黑名单主键
     * @return 结果
     */
    @Override
    public int deleteBlackListRecordsByIds(Long[] ids)
    {
        return blackListRecordsMapper.deleteBlackListRecordsByIds(ids);
    }

    /**
     * 删除建档黑名单信息
     * 
     * @param id 建档黑名单主键
     * @return 结果
     */
    @Override
    public int deleteBlackListRecordsById(Long id)
    {
        return blackListRecordsMapper.deleteBlackListRecordsById(id);
    }
}
