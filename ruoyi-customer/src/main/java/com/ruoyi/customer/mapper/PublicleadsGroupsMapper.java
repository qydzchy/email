package com.ruoyi.customer.mapper;

import java.util.List;
import com.ruoyi.customer.domain.PublicleadsGroups;
import org.apache.ibatis.annotations.Param;

/**
 * 公海分组Mapper接口
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
public interface PublicleadsGroupsMapper 
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
     * 删除公海分组
     * 
     * @param id 公海分组主键
     * @return 结果
     */
    public int deletePublicleadsGroupsById(@Param("id") Long id, @Param("updateId") Long updateId, @Param("updateBy") String updateBy);

    /**
     * 批量删除公海分组
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePublicleadsGroupsByIds(Long[] ids);
}
