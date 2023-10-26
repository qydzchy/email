package com.ruoyi.customer.service;

import java.util.List;
import com.ruoyi.customer.domain.vo.FollowUpRulesListVO;

/**
 * 客户跟进规则Service接口
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
public interface IFollowUpRulesService 
{
    /**
     * 查询跟进规则列表
     * @return
     */
    List<FollowUpRulesListVO> list();

    /**
     * 更新标志状态
     * @param id
     * @return
     */
    int updateActiveFlag(Long id);
}
