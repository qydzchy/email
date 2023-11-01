package com.ruoyi.customer.service;

import java.util.List;
import com.ruoyi.customer.domain.PublicleadsGroups;
import com.ruoyi.customer.domain.vo.PublicleadsGroupsListVO;

/**
 * 公海分组Service接口
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
public interface IPublicleadsGroupsService 
{
    /**
     * 新增公海分组
     * 
     * @param publicleadsGroups 公海分组
     * @return 结果
     */
    public int insertPublicleadsGroups(PublicleadsGroups publicleadsGroups);

    /**
     * 修改公海分组
     * 
     * @param publicleadsGroups 公海分组
     * @return 结果
     */
    public int updatePublicleadsGroups(PublicleadsGroups publicleadsGroups);

    /**
     * 删除公海分组信息
     * 
     * @param id 公海分组主键
     * @return 结果
     */
    public int deletePublicleadsGroupsById(Long id);

    /**
     * 公海分组列表
     * @return
     */
    List<PublicleadsGroupsListVO> list();
}