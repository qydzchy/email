package com.ruoyi.customer.mapper;

import java.util.List;
import com.ruoyi.customer.domain.PublicleadsRulesSegment;
import org.apache.ibatis.annotations.Param;

/**
 * 公海规则客群Mapper接口
 * 
 * @author tangJM.
 * @date 2023-11-15
 */
public interface PublicleadsRulesSegmentMapper 
{
    /**
     * 查询公海规则客群
     * 
     * @param id 公海规则客群主键
     * @return 公海规则客群
     */
    public PublicleadsRulesSegment selectPublicleadsRulesSegmentById(Long id);

    /**
     * 查询公海规则客群列表
     * 
     * @param publicleadsRulesSegment 公海规则客群
     * @return 公海规则客群集合
     */
    public List<PublicleadsRulesSegment> selectPublicleadsRulesSegmentList(PublicleadsRulesSegment publicleadsRulesSegment);

    /**
     * 新增公海规则客群
     * 
     * @param publicleadsRulesSegment 公海规则客群
     * @return 结果
     */
    public int insertPublicleadsRulesSegment(PublicleadsRulesSegment publicleadsRulesSegment);

    /**
     * 修改公海规则客群
     * 
     * @param publicleadsRulesSegment 公海规则客群
     * @return 结果
     */
    public int updatePublicleadsRulesSegment(PublicleadsRulesSegment publicleadsRulesSegment);

    /**
     * 删除公海规则客群
     * 
     * @param id 公海规则客群主键
     * @return 结果
     */
    public int deletePublicleadsRulesSegmentById(Long id);

    /**
     * 批量删除公海规则客群
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePublicleadsRulesSegmentByIds(Long[] ids);

    /**
     * 批量新增公海规则客群
     * @param publicleadsRulesSegmentList
     */
    void batchInsertPublicleadsRulesSegment(@Param("publicleadsRulesSegmentList") List<PublicleadsRulesSegment> publicleadsRulesSegmentList);

    /**
     * 根据公海规则id删除
     * @param publicleadsRulesId
     */
    void deleteByPublicleadsRulesId(@Param("publicleadsRulesId") Long publicleadsRulesId);
}
