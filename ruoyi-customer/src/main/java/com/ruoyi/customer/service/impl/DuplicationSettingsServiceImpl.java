package com.ruoyi.customer.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.customer.domain.dto.DuplicationSettingsActiveFlagEditDTO;
import com.ruoyi.customer.domain.vo.DuplicationSettingsListVO;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.DuplicationSettingsMapper;
import com.ruoyi.customer.domain.DuplicationSettings;
import com.ruoyi.customer.service.IDuplicationSettingsService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 客户查重设置Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
@Service
public class DuplicationSettingsServiceImpl implements IDuplicationSettingsService 
{
    @Resource
    private DuplicationSettingsMapper duplicationSettingsMapper;

    /**
     * 查询客户查重设置
     * 
     * @param id 客户查重设置主键
     * @return 客户查重设置
     */
    @Override
    public DuplicationSettings selectDuplicationSettingsById(Long id)
    {
        return duplicationSettingsMapper.selectDuplicationSettingsById(id);
    }

    /**
     * 查询客户查重设置列表
     * 
     * @param duplicationSettings 客户查重设置
     * @return 客户查重设置
     */
    @Override
    public List<DuplicationSettings> selectDuplicationSettingsList(DuplicationSettings duplicationSettings)
    {
        return duplicationSettingsMapper.selectDuplicationSettingsList(duplicationSettings);
    }

    /**
     * 新增客户查重设置
     * 
     * @param duplicationSettings 客户查重设置
     * @return 结果
     */
    @Override
    public int insertDuplicationSettings(DuplicationSettings duplicationSettings)
    {
        duplicationSettings.setCreateTime(DateUtils.getNowDate());
        return duplicationSettingsMapper.insertDuplicationSettings(duplicationSettings);
    }

    /**
     * 修改客户查重设置
     * 
     * @param duplicationSettings 客户查重设置
     * @return 结果
     */
    @Override
    public int updateDuplicationSettings(DuplicationSettings duplicationSettings)
    {
        duplicationSettings.setUpdateTime(DateUtils.getNowDate());
        return duplicationSettingsMapper.updateDuplicationSettings(duplicationSettings);
    }

    /**
     * 批量删除客户查重设置
     * 
     * @param ids 需要删除的客户查重设置主键
     * @return 结果
     */
    @Override
    public int deleteDuplicationSettingsByIds(Long[] ids)
    {
        return duplicationSettingsMapper.deleteDuplicationSettingsByIds(ids);
    }

    /**
     * 删除客户查重设置信息
     * 
     * @param id 客户查重设置主键
     * @return 结果
     */
    @Override
    public int deleteDuplicationSettingsById(Long id)
    {
        return duplicationSettingsMapper.deleteDuplicationSettingsById(id);
    }

    /**
     * 查询客户查重设置列表
     * @return
     */
    @Override
    public List<DuplicationSettingsListVO> list() {
        return duplicationSettingsMapper.list();
    }

    /**
     * 更新标志
     * @param duplicationSettingsActiveFlagEditDTOList
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateActiveFlag(List<DuplicationSettingsActiveFlagEditDTO> duplicationSettingsActiveFlagEditDTOList) {
        List<Long> selectedIdList = duplicationSettingsActiveFlagEditDTOList.stream().filter(duplicationSettingsActiveFlagEditDTO -> duplicationSettingsActiveFlagEditDTO.getActiveFlag()).map(duplicationSettingsActiveFlagEditDTO -> duplicationSettingsActiveFlagEditDTO.getId()).collect(Collectors.toList());
        List<Long> unSelectedIdList = duplicationSettingsActiveFlagEditDTOList.stream().filter(duplicationSettingsActiveFlagEditDTO -> !duplicationSettingsActiveFlagEditDTO.getActiveFlag()).map(duplicationSettingsActiveFlagEditDTO -> duplicationSettingsActiveFlagEditDTO.getId()).collect(Collectors.toList());
        if (selectedIdList != null && !selectedIdList.isEmpty()) {
            duplicationSettingsMapper.batchUpdateActiveFlag(selectedIdList, true);
        }
        if (unSelectedIdList != null && !unSelectedIdList.isEmpty()) {
            duplicationSettingsMapper.batchUpdateActiveFlag(unSelectedIdList, false);
        }
        return true;
    }
}
