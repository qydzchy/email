package com.ruoyi.email.service;

import java.util.List;
import com.ruoyi.email.domain.General;
import com.ruoyi.email.domain.dto.email.GeneralSaveOrUpdateDTO;
import com.ruoyi.email.domain.vo.GeneralVO;
import com.ruoyi.email.domain.vo.TaskListVO;

/**
 * 邮箱常规Service接口
 * 
 * @author tangJM
 * @date 2023-07-31
 */
public interface IGeneralService 
{
    /**
     * 查询邮箱常规
     * 
     * @param id 邮箱常规主键
     * @return 邮箱常规
     */
    public General selectGeneralById(Long id);

    /**
     * 查询邮箱常规列表
     * 
     * @param general 邮箱常规
     * @return 邮箱常规集合
     */
    public List<General> selectGeneralList(General general);

    /**
     * 新增邮箱常规
     * 
     * @param general 邮箱常规
     * @return 结果
     */
    public int insertGeneral(General general);

    /**
     * 修改邮箱常规
     * 
     * @param generalSaveOrUpdateDTO 邮箱常规
     * @return 结果
     */
    public int updateGeneral(GeneralSaveOrUpdateDTO generalSaveOrUpdateDTO);

    /**
     * 批量删除邮箱常规
     * 
     * @param ids 需要删除的邮箱常规主键集合
     * @return 结果
     */
    public int deleteGeneralByIds(Long[] ids);

    /**
     * 删除邮箱常规信息
     * 
     * @param id 邮箱常规主键
     * @return 结果
     */
    public int deleteGeneralById(Long id);

    /**
     * 获取邮箱常规详细信息
     * @return
     */
    GeneralVO getInfo();

    /**
     * 默认邮箱任务列表
     * @return
     */
    List<TaskListVO> defaultTaskList();
}
