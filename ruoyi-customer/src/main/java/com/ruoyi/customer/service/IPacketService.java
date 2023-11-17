package com.ruoyi.customer.service;

import java.util.List;
import com.ruoyi.customer.domain.Packet;
import com.ruoyi.customer.domain.vo.PacketListVO;

/**
 * 客户分组Service接口
 *
 * @author tangJM.
 * @date 2023-10-25
 */
public interface IPacketService
{

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
     * 删除客户分组信息
     *
     * @param id 客户分组主键
     * @return 结果
     */
    public int deletePacketById(Long id);

    /**
     * 查询客户分组树列表
     * @return
     */
    List<PacketListVO> getPacketTree();

    /**
     * 客户设置-分组列表
     * @return
     */
    List<PacketListVO> packetList();
}
