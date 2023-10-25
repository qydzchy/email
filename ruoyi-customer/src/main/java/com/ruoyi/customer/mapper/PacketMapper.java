package com.ruoyi.customer.mapper;

import java.util.List;
import com.ruoyi.customer.domain.Packet;

/**
 * 客户分组Mapper接口
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
public interface PacketMapper 
{
    /**
     * 查询客户分组
     * 
     * @param id 客户分组主键
     * @return 客户分组
     */
    public Packet selectPacketById(Long id);

    /**
     * 查询客户分组列表
     * 
     * @param packet 客户分组
     * @return 客户分组集合
     */
    public List<Packet> selectPacketList(Packet packet);

    /**
     * 新增客户分组
     * 
     * @param packet 客户分组
     * @return 结果
     */
    public int insertPacket(Packet packet);

    /**
     * 修改客户分组
     * 
     * @param packet 客户分组
     * @return 结果
     */
    public int updatePacket(Packet packet);

    /**
     * 删除客户分组
     * 
     * @param id 客户分组主键
     * @return 结果
     */
    public int deletePacketById(Long id);

    /**
     * 批量删除客户分组
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePacketByIds(Long[] ids);
}
