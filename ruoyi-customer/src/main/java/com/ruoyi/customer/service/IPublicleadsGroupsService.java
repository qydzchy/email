package com.ruoyi.customer.service;

import java.util.List;
import com.ruoyi.customer.domain.PublicleadsGroups;

/**
 * 公海分组Service接口
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
public interface IPublicleadsGroupsService 
{
    /**
     * 查询公海分组
     * 
     * @param id 公海分组主键
     * @return 公海分组
     */
    public PublicleadsGroups selectPublicleadsGroupsById(Long id);

    /**
     * 查询公海分组列表
     * 
     * @param publicleadsGroups 公海分组
     * @return 公海分组集合
     */
    public List<PublicleadsGroups> selectPublicleadsGroupsList(PublicleadsGroups publicleadsGroups);

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
     * 批量删除公海分组
     * 
     * @param ids 需要删除的公海分组主键集合
     * @return 结果
     */
    public int deletePublicleadsGroupsByIds(Long[] ids);

    /**
     * 删除公海分组信息
     * 
     * @param id 公海分组主键
     * @return 结果
     */
    public int deletePublicleadsGroupsById(Long id);
}
