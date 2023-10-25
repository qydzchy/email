package com.ruoyi.customer.service;

import java.util.List;
import com.ruoyi.customer.domain.PacketDesignateMember;

/**
 * 客户分组指定成员Service接口
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
public interface IPacketDesignateMemberService 
{
    /**
     * 查询客户分组指定成员
     * 
     * @param id 客户分组指定成员主键
     * @return 客户分组指定成员
     */
    public PacketDesignateMember selectPacketDesignateMemberById(Long id);

    /**
     * 查询客户分组指定成员列表
     * 
     * @param packetDesignateMember 客户分组指定成员
     * @return 客户分组指定成员集合
     */
    public List<PacketDesignateMember> selectPacketDesignateMemberList(PacketDesignateMember packetDesignateMember);

    /**
     * 新增客户分组指定成员
     * 
     * @param packetDesignateMember 客户分组指定成员
     * @return 结果
     */
    public int insertPacketDesignateMember(PacketDesignateMember packetDesignateMember);

    /**
     * 修改客户分组指定成员
     * 
     * @param packetDesignateMember 客户分组指定成员
     * @return 结果
     */
    public int updatePacketDesignateMember(PacketDesignateMember packetDesignateMember);

    /**
     * 批量删除客户分组指定成员
     * 
     * @param ids 需要删除的客户分组指定成员主键集合
     * @return 结果
     */
    public int deletePacketDesignateMemberByIds(Long[] ids);

    /**
     * 删除客户分组指定成员信息
     * 
     * @param id 客户分组指定成员主键
     * @return 结果
     */
    public int deletePacketDesignateMemberById(Long id);
}
