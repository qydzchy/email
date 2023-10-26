package com.ruoyi.customer.service;

import java.util.List;
import com.ruoyi.customer.domain.dto.PublicleadsClaimLimitAddDTO;
import com.ruoyi.customer.domain.vo.PublicleadsClaimLimitListVO;

/**
 * 领取上限Service接口
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
public interface IPublicleadsClaimLimitService 
{

    /**
     * 新增领取上限
     *
     * @return 结果
     */
    public int insertPublicleadsClaimLimit(PublicleadsClaimLimitAddDTO publicleadsClaimLimitAddDTO);

    /**
     * 删除领取上限信息
     * 
     * @param id 领取上限主键
     * @return 结果
     */
    public int deletePublicleadsClaimLimitById(Long id);

    /**
     * 领取上限列表
     * @return
     */
    List<PublicleadsClaimLimitListVO> list();
}
