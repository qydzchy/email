package com.ruoyi.customer.service;

import java.util.List;
import com.ruoyi.customer.domain.BlackListRecords;

/**
 * 建档黑名单Service接口
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
public interface IBlackListRecordsService 
{
    /**
     * 查询建档黑名单
     * 
     * @param id 建档黑名单主键
     * @return 建档黑名单
     */
    public BlackListRecords selectBlackListRecordsById(Long id);

    /**
     * 查询建档黑名单列表
     * 
     * @param blackListRecords 建档黑名单
     * @return 建档黑名单集合
     */
    public List<BlackListRecords> selectBlackListRecordsList(BlackListRecords blackListRecords);

    /**
     * 新增建档黑名单
     * 
     * @param blackListRecords 建档黑名单
     * @return 结果
     */
    public int insertBlackListRecords(BlackListRecords blackListRecords);

    /**
     * 修改建档黑名单
     * 
     * @param blackListRecords 建档黑名单
     * @return 结果
     */
    public int updateBlackListRecords(BlackListRecords blackListRecords);

    /**
     * 批量删除建档黑名单
     * 
     * @param ids 需要删除的建档黑名单主键集合
     * @return 结果
     */
    public int deleteBlackListRecordsByIds(Long[] ids);

    /**
     * 删除建档黑名单信息
     * 
     * @param id 建档黑名单主键
     * @return 结果
     */
    public int deleteBlackListRecordsById(Long id);
}
