package com.ruoyi.customer.service;

import java.util.List;
import com.ruoyi.customer.domain.vo.PublicleadsWhiteListListVO;

/**
 * 移入公海白名单Service接口
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
public interface IPublicleadsWhiteListService 
{
    /**
     * 新增移入公海白名单
     *
     * @return 结果
     */
    public int insertPublicleadsWhiteList(List<Long> userIdList);

    /**
     * 删除移入公海白名单信息
     *
     * @param id 移入公海白名单主键
     * @return 结果
     */
    public int deletePublicleadsWhiteListById(Long id);

    /**
     * 移入公海白名单列表
     * @return
     */
    List<PublicleadsWhiteListListVO> list();
}
