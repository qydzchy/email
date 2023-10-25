package com.ruoyi.customer.service;

import java.util.List;
import com.ruoyi.customer.domain.Stage;

/**
 * 客户阶段Service接口
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
public interface IStageService 
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
     * 批量删除客户阶段
     * 
     * @param ids 需要删除的客户阶段主键集合
     * @return 结果
     */
    public int deleteStageByIds(Long[] ids);

    /**
     * 删除客户阶段信息
     * 
     * @param id 客户阶段主键
     * @return 结果
     */
    public int deleteStageById(Long id);
}
