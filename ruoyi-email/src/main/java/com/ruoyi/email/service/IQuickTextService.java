package com.ruoyi.email.service;

import java.util.List;
import com.ruoyi.email.domain.QuickText;

/**
 * 快速文本Service接口
 * 
 * @author tangJM
 * @date 2023-07-31
 */
public interface IQuickTextService 
{
    /**
     * 查询快速文本
     * 
     * @param id 快速文本主键
     * @return 快速文本
     */
    public QuickText selectQuickTextById(Long id);

    /**
     * 查询快速文本列表
     * 
     * @param quickText 快速文本
     * @return 快速文本集合
     */
    public List<QuickText> selectQuickTextList(QuickText quickText);

    /**
     * 新增快速文本
     * 
     * @param quickText 快速文本
     * @return 结果
     */
    public int insertQuickText(QuickText quickText);

    /**
     * 修改快速文本
     * 
     * @param quickText 快速文本
     * @return 结果
     */
    public int updateQuickText(QuickText quickText);

    /**
     * 批量删除快速文本
     * 
     * @param ids 需要删除的快速文本主键集合
     * @return 结果
     */
    public int deleteQuickTextByIds(Long[] ids);

    /**
     * 删除快速文本信息
     * 
     * @param id 快速文本主键
     * @return 结果
     */
    public int deleteQuickTextById(Long id);
}
