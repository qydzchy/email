package com.ruoyi.customer.mapper;

import java.util.List;
import com.ruoyi.customer.domain.FollowUpQuickText;
import org.apache.ibatis.annotations.Param;

/**
 * 写跟进快捷文本Mapper接口
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
public interface FollowUpQuickTextMapper 
{
    /**
     * 查询写跟进快捷文本
     * 
     * @param id 写跟进快捷文本主键
     * @return 写跟进快捷文本
     */
    public FollowUpQuickText selectFollowUpQuickTextById(Long id);

    /**
     * 查询写跟进快捷文本列表
     * 
     * @param followUpQuickText 写跟进快捷文本
     * @return 写跟进快捷文本集合
     */
    public List<FollowUpQuickText> selectFollowUpQuickTextList(FollowUpQuickText followUpQuickText);

    /**
     * 新增写跟进快捷文本
     * 
     * @param followUpQuickText 写跟进快捷文本
     * @return 结果
     */
    public int insertFollowUpQuickText(FollowUpQuickText followUpQuickText);

    /**
     * 修改写跟进快捷文本
     * 
     * @param followUpQuickText 写跟进快捷文本
     * @return 结果
     */
    public int updateFollowUpQuickText(FollowUpQuickText followUpQuickText);

    /**
     * 删除写跟进快捷文本
     * 
     * @param id 写跟进快捷文本主键
     * @return 结果
     */
    public int deleteFollowUpQuickTextById(@Param("id") Long id, @Param("updateId") Long updateId, @Param("updateBy") String updateBy);

    /**
     * 批量删除写跟进快捷文本
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteFollowUpQuickTextByIds(Long[] ids);
}
