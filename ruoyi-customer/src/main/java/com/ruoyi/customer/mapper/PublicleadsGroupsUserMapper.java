package com.ruoyi.customer.mapper;

import java.util.List;
import com.ruoyi.customer.domain.PublicleadsGroupsUser;
import org.apache.ibatis.annotations.Param;

/**
 * 公海分组成员Mapper接口
 * 
 * @author tangJM.
 * @date 2023-11-05
 */
public interface PublicleadsGroupsUserMapper 
{
    /**
     * 查询公海分组成员
     * 
     * @param id 公海分组成员主键
     * @return 公海分组成员
     */
    public PublicleadsGroupsUser selectPublicleadsGroupsUserById(Long id);

    /**
     * 查询公海分组成员列表
     * 
     * @param publicleadsGroupsUser 公海分组成员
     * @return 公海分组成员集合
     */
    public List<PublicleadsGroupsUser> selectPublicleadsGroupsUserList(PublicleadsGroupsUser publicleadsGroupsUser);

    /**
     * 新增公海分组成员
     * 
     * @param publicleadsGroupsUser 公海分组成员
     * @return 结果
     */
    public int insertPublicleadsGroupsUser(PublicleadsGroupsUser publicleadsGroupsUser);

    /**
     * 修改公海分组成员
     * 
     * @param publicleadsGroupsUser 公海分组成员
     * @return 结果
     */
    public int updatePublicleadsGroupsUser(PublicleadsGroupsUser publicleadsGroupsUser);

    /**
     * 删除公海分组成员
     * 
     * @param id 公海分组成员主键
     * @return 结果
     */
    public int deletePublicleadsGroupsUserById(Long id);

    /**
     * 批量删除公海分组成员
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePublicleadsGroupsUserByIds(Long[] ids);

    /**
     * 批量插入分组成员
     * @param publicleadsGroupsUserList
     */
    void batchInsertPublicleadsGroupsUser(@Param("publicleadsGroupsUserList") List<PublicleadsGroupsUser> publicleadsGroupsUserList);

    /**
     * 根据分组id删除分组成员
     * @param publicleadsGroupsId
     */
    void deletePublicleadsGroupsUserByPublicleadsGroupsId(@Param("publicleadsGroupsId") Long publicleadsGroupsId);
}
