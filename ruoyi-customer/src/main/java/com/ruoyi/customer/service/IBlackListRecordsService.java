package com.ruoyi.customer.service;

import java.util.List;

import com.ruoyi.customer.domain.BlackListRecords;
import com.ruoyi.customer.domain.vo.BlackListRecordsListVO;

/**
 * 建档黑名单Service接口
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
public interface IBlackListRecordsService 
{
    /**
     * 查询建档黑名单列表
     * @return
     */
    List<BlackListRecordsListVO> list();

    /**
     * 批量删除建档黑名单
     * @param ids
     * @return
     */
    int batchDeleteByIds(List<Long> ids);

    /**
     * 新建建档黑名单
     * @param blackListRecords
     * @return
     */
    int insertBlackListRecords(BlackListRecords blackListRecords);
}
