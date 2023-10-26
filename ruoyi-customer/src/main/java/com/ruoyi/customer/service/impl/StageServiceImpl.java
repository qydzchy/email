package com.ruoyi.customer.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.customer.domain.vo.StageListVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.StageMapper;
import com.ruoyi.customer.domain.Stage;
import com.ruoyi.customer.service.IStageService;

import javax.annotation.Resource;

/**
 * 客户阶段Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
@Service
public class StageServiceImpl implements IStageService 
{
    @Resource
    private StageMapper stageMapper;

    /**
     * 新增客户阶段
     * 
     * @param stage 客户阶段
     * @return 结果
     */
    @Override
    public int insertStage(Stage stage)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        stage.setCreateId(userId);
        stage.setCreateBy(username);
        stage.setCreateTime(DateUtils.getNowDate());
        stage.setUpdateId(userId);
        stage.setUpdateBy(username);
        stage.setUpdateTime(DateUtils.getNowDate());
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
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        stage.setUpdateId(userId);
        stage.setUpdateBy(username);
        stage.setUpdateTime(DateUtils.getNowDate());
        return stageMapper.updateStage(stage);
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
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        return stageMapper.deleteStageById(id, userId, username);
    }

    @Override
    public List<StageListVO> list() {
        List<Stage> stageList = stageMapper.selectStageList(new Stage());
        List<StageListVO> stageListVOList = new ArrayList<>();

        for (Stage stage : stageList) {
            StageListVO stageListVO = new StageListVO();
            BeanUtils.copyProperties(stage, stageListVO);
            stageListVOList.add(stageListVO);
        }

        return stageListVOList;
    }
}
