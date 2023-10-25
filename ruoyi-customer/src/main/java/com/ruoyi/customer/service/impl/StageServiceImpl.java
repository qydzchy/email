package com.ruoyi.customer.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.StageMapper;
import com.ruoyi.customer.domain.Stage;
import com.ruoyi.customer.service.IStageService;

/**
 * 客户阶段Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
@Service
public class StageServiceImpl implements IStageService 
{
    @Autowired
    private StageMapper stageMapper;

    /**
     * 查询客户阶段
     * 
     * @param id 客户阶段主键
     * @return 客户阶段
     */
    @Override
    public Stage selectStageById(Long id)
    {
        return stageMapper.selectStageById(id);
    }

    /**
     * 查询客户阶段列表
     * 
     * @param stage 客户阶段
     * @return 客户阶段
     */
    @Override
    public List<Stage> selectStageList(Stage stage)
    {
        return stageMapper.selectStageList(stage);
    }

    /**
     * 新增客户阶段
     * 
     * @param stage 客户阶段
     * @return 结果
     */
    @Override
    public int insertStage(Stage stage)
    {
        stage.setCreateTime(DateUtils.getNowDate());
        return stageMapper.insertStage(stage);
    }

    /**
     * 修改客户阶段
     * 
     * @param stage 客户阶段
     * @return 结果
     */
    @Override
    public int updateStage(Stage stage)
    {
        stage.setUpdateTime(DateUtils.getNowDate());
        return stageMapper.updateStage(stage);
    }

    /**
     * 批量删除客户阶段
     * 
     * @param ids 需要删除的客户阶段主键
     * @return 结果
     */
    @Override
    public int deleteStageByIds(Long[] ids)
    {
        return stageMapper.deleteStageByIds(ids);
    }

    /**
     * 删除客户阶段信息
     * 
     * @param id 客户阶段主键
     * @return 结果
     */
    @Override
    public int deleteStageById(Long id)
    {
        return stageMapper.deleteStageById(id);
    }
}
