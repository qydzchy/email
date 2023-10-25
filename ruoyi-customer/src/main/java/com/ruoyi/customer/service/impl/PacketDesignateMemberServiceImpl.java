package com.ruoyi.customer.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.PacketDesignateMemberMapper;
import com.ruoyi.customer.domain.PacketDesignateMember;
import com.ruoyi.customer.service.IPacketDesignateMemberService;

/**
 * 客户分组指定成员Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
@Service
public class PacketDesignateMemberServiceImpl implements IPacketDesignateMemberService 
{
    @Autowired
    private PacketDesignateMemberMapper packetDesignateMemberMapper;

    /**
     * 查询客户分组指定成员
     * 
     * @param id 客户分组指定成员主键
     * @return 客户分组指定成员
     */
    @Override
    public PacketDesignateMember selectPacketDesignateMemberById(Long id)
    {
        return packetDesignateMemberMapper.selectPacketDesignateMemberById(id);
    }

    /**
     * 查询客户分组指定成员列表
     * 
     * @param packetDesignateMember 客户分组指定成员
     * @return 客户分组指定成员
     */
    @Override
    public List<PacketDesignateMember> selectPacketDesignateMemberList(PacketDesignateMember packetDesignateMember)
    {
        return packetDesignateMemberMapper.selectPacketDesignateMemberList(packetDesignateMember);
    }

    /**
     * 新增客户分组指定成员
     * 
     * @param packetDesignateMember 客户分组指定成员
     * @return 结果
     */
    @Override
    public int insertPacketDesignateMember(PacketDesignateMember packetDesignateMember)
    {
        packetDesignateMember.setCreateTime(DateUtils.getNowDate());
        return packetDesignateMemberMapper.insertPacketDesignateMember(packetDesignateMember);
    }

    /**
     * 修改客户分组指定成员
     * 
     * @param packetDesignateMember 客户分组指定成员
     * @return 结果
     */
    @Override
    public int updatePacketDesignateMember(PacketDesignateMember packetDesignateMember)
    {
        packetDesignateMember.setUpdateTime(DateUtils.getNowDate());
        return packetDesignateMemberMapper.updatePacketDesignateMember(packetDesignateMember);
    }

    /**
     * 批量删除客户分组指定成员
     * 
     * @param ids 需要删除的客户分组指定成员主键
     * @return 结果
     */
    @Override
    public int deletePacketDesignateMemberByIds(Long[] ids)
    {
        return packetDesignateMemberMapper.deletePacketDesignateMemberByIds(ids);
    }

    /**
     * 删除客户分组指定成员信息
     * 
     * @param id 客户分组指定成员主键
     * @return 结果
     */
    @Override
    public int deletePacketDesignateMemberById(Long id)
    {
        return packetDesignateMemberMapper.deletePacketDesignateMemberById(id);
    }
}
