package com.ruoyi.customer.service;

import java.util.List;
import com.ruoyi.customer.domain.FollowUpTemplates;
import com.ruoyi.customer.domain.vo.FollowUpTemplatesListVO;

/**
 * 写跟进模板Service接口
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
public interface IFollowUpTemplatesService 
{
    /**
     * 新增写跟进模板
     * 
     * @param followUpTemplates 写跟进模板
     * @return 结果
     */
    public int insertFollowUpTemplates(FollowUpTemplates followUpTemplates);

    /**
     * 修改写跟进模板
     * 
     * @param followUpTemplates 写跟进模板
     * @return 结果
     */
    public int updateFollowUpTemplates(FollowUpTemplates followUpTemplates);

    /**
     * 删除写跟进模板信息
     * 
     * @param id 写跟进模板主键
     * @return 结果
     */
    public int deleteFollowUpTemplatesById(Long id);

    /**
     * 跟进模板列表
     * @return
     */
    List<FollowUpTemplatesListVO> list();
}
