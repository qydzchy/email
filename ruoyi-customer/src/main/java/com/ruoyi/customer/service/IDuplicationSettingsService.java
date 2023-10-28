package com.ruoyi.customer.service;

import java.util.List;
import com.ruoyi.customer.domain.DuplicationSettings;
import com.ruoyi.customer.domain.vo.DuplicationSettingsListVO;

/**
 * 客户查重设置Service接口
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
public interface IDuplicationSettingsService 
{
    /**
     * 查询客户查重设置
     * 
     * @param id 客户查重设置主键
     * @return 客户查重设置
     */
    public DuplicationSettings selectDuplicationSettingsById(Long id);

    /**
     * 查询客户查重设置列表
     * 
     * @param duplicationSettings 客户查重设置
     * @return 客户查重设置集合
     */
    public List<DuplicationSettings> selectDuplicationSettingsList(DuplicationSettings duplicationSettings);

    /**
     * 新增客户查重设置
     * 
     * @param duplicationSettings 客户查重设置
     * @return 结果
     */
    public int insertDuplicationSettings(DuplicationSettings duplicationSettings);

    /**
     * 修改客户查重设置
     * 
     * @param duplicationSettings 客户查重设置
     * @return 结果
     */
    public int updateDuplicationSettings(DuplicationSettings duplicationSettings);

    /**
     * 批量删除客户查重设置
     * 
     * @param ids 需要删除的客户查重设置主键集合
     * @return 结果
     */
    public int deleteDuplicationSettingsByIds(Long[] ids);

    /**
     * 删除客户查重设置信息
     * 
     * @param id 客户查重设置主键
     * @return 结果
     */
    public int deleteDuplicationSettingsById(Long id);

    /**
     * 查询客户查重设置列表
     * @return
     */
    List<DuplicationSettingsListVO> list();

    /**
     * 更新标志
     * @param id
     * @return
     */
    int updateActiveFlag(Long id);
}
