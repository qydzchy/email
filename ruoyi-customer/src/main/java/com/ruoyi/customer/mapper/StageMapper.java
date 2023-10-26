package com.ruoyi.customer.mapper;

import java.util.List;
import com.ruoyi.customer.domain.Stage;
import org.apache.ibatis.annotations.Param;

/**
 * 客户阶段Mapper接口
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
public interface StageMapper 
{
    /**
     * 查询客户阶段
     * 
     * @param id 客户阶段主键
     * @return 客户阶段
     */
    public Stage selectStageById(Long id);

    /**
     * 查询客户阶段列表
     * 
     * @param stage 客户阶段
     * @return 客户阶段集合
     */
    public List<Stage> selectStageList(Stage stage);

    /**
     * 新增客户阶段
     * 
     * @param stage 客户阶段
     * @return 结果
     */
    public int insertStage(Stage stage);

    /**
     * 修改客户阶段
     * 
     * @param stage 客户阶段
     * @return 结果
     */
    public int updateStage(Stage stage);

    /**
     * 删除客户阶段
     * 
     * @param id 客户阶段主键
     * @return 结果
     */
    public int deleteStageById(@Param("id") Long id, @Param("updateId") Long updateId, @Param("updateBy") String updateBy);

    /**
     * 批量删除客户阶段
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStageByIds(Long[] ids);
}
