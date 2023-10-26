package com.ruoyi.customer.service;

import java.util.List;
import com.ruoyi.customer.domain.FollowUpQuickText;
import com.ruoyi.customer.domain.vo.FollowUpQuickTextListVO;

/**
 * 写跟进快捷文本Service接口
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
public interface IFollowUpQuickTextService 
{
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
     * 删除写跟进快捷文本信息
     * 
     * @param id 写跟进快捷文本主键
     * @return 结果
     */
    public int deleteFollowUpQuickTextById(Long id);

    /**
     * 跟进快捷文本列表
     * @return
     */
    List<FollowUpQuickTextListVO> list();
}
