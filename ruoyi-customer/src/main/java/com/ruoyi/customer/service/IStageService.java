package com.ruoyi.customer.service;

import java.util.List;
import com.ruoyi.customer.domain.Stage;
import com.ruoyi.customer.domain.vo.StageListVO;

/**
 * 客户阶段Service接口
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
public interface IStageService 
{
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
     * 删除客户阶段信息
     * 
     * @param id 客户阶段主键
     * @return 结果
     */
    public int deleteStageById(Long id);

    /**
     * 客户阶段列表
     * @return
     */
    List<StageListVO> list();
}
