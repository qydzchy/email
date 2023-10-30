package com.ruoyi.customer.mapper;

import java.util.List;
import com.ruoyi.customer.domain.PublicleadsClaimLimit;
import com.ruoyi.customer.domain.vo.PublicleadsClaimLimitListVO;
import org.apache.ibatis.annotations.Param;

/**
 * 领取上限Mapper接口
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
public interface PublicleadsClaimLimitMapper 
{
    /**
     * 查询领取上限
     * 
     * @param id 领取上限主键
     * @return 领取上限
     */
    public PublicleadsClaimLimit selectPublicleadsClaimLimitById(Long id);

    /**
     * 查询领取上限列表
     * 
     * @param publicleadsClaimLimit 领取上限
     * @return 领取上限集合
     */
    public List<PublicleadsClaimLimit> selectPublicleadsClaimLimitList(PublicleadsClaimLimit publicleadsClaimLimit);

    /**
     * 新增领取上限
     * 
     * @param publicleadsClaimLimit 领取上限
     * @return 结果
     */
    public int insertPublicleadsClaimLimit(PublicleadsClaimLimit publicleadsClaimLimit);

    /**
     * 修改领取上限
     * 
     * @param publicleadsClaimLimit 领取上限
     * @return 结果
     */
    public int updatePublicleadsClaimLimit(PublicleadsClaimLimit publicleadsClaimLimit);

    /**
     * 删除领取上限
     * 
     * @param id 领取上限主键
     * @return 结果
     */
    public int deletePublicleadsClaimLimitById(@Param("id") Long id, @Param("updateId") Long updateId, @Param("updateBy") String updateBy);

    /**
     * 批量删除领取上限
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePublicleadsClaimLimitByIds(Long[] ids);

    /**
     * 批量新增领取上限
     * @param publicleadsClaimLimitList
     * @return
     */
    int batchInsertPublicleadsClaimLimit(@Param("publicleadsClaimLimitList") List<PublicleadsClaimLimit> publicleadsClaimLimitList);

    /**
     * 领取上限列表
     * @return
     */
    List<PublicleadsClaimLimitListVO> list();
}
