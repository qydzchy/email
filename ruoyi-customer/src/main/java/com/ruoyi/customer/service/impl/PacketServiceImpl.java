package com.ruoyi.customer.service.impl;

import java.util.*;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.customer.domain.bo.UserDeptInfoBO;
import com.ruoyi.customer.domain.vo.PacketListVO;
import com.ruoyi.customer.service.IUserDeptService;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.PacketMapper;
import com.ruoyi.customer.domain.Packet;
import com.ruoyi.customer.service.IPacketService;

import javax.annotation.Resource;

/**
 * 客户分组Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
@Service
public class PacketServiceImpl implements IPacketService 
{
    @Resource
    private PacketMapper packetMapper;
    @Resource
    private IUserDeptService userDeptService;

    /**
     * 新增客户分组
     * 
     * @param packet 客户分组
     * @return 结果
     */
    @Override
    public int insertPacket(Packet packet)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        packet.setCreateId(userId);
        packet.setCreateBy(username);
        packet.setCreateTime(DateUtils.getNowDate());
        packet.setUpdateId(userId);
        packet.setUpdateBy(username);
        packet.setUpdateTime(DateUtils.getNowDate());
        return packetMapper.insertPacket(packet);
    }

    /**
     * 修改客户分组
     * 
     * @param packet 客户分组
     * @return 结果
     */
    @Override
    public int updatePacket(Packet packet)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        packet.setUpdateId(userId);
        packet.setUpdateBy(username);
        packet.setUpdateTime(DateUtils.getNowDate());
        return packetMapper.updatePacket(packet);
    }

    /**
     * 删除客户分组信息
     * 
     * @param id 客户分组主键
     * @return 结果
     */
    @Override
    public int deletePacketById(Long id)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        return packetMapper.deletePacketById(id, userId, username);
    }

    @Override
    public List<PacketListVO> getPacketTree() {
        List<PacketListVO> packetListVOList = packetMapper.list();
        return buildTree(packetListVOList, -1L);
    }

    /**
     * 客户设置-分组列表
     * @return
     */
    @Override
    public List<PacketListVO> packetList() {
        List<PacketListVO> packetVOList = packetMapper.list();
        if (packetVOList == null || packetVOList.isEmpty()) {
            return Collections.emptyList();
        }

        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        Long deptId = loginUser.getDeptId();

        Iterator<PacketListVO> iterator = packetVOList.iterator();
        while (iterator.hasNext()) {
            PacketListVO packetVO = iterator.next();
            if (packetVO.getParentId().longValue() != -1L) {
                continue;
            }

            String designatedMember = packetVO.getDesignatedMember();
            boolean isMet = userDeptService.userDeptVerify(Arrays.asList(UserDeptInfoBO.builder().userId(userId).deptId(deptId).build()), designatedMember);
            if (!isMet) {
                iterator.remove();
            }
        }

        return buildTree(packetVOList, -1L);
    }

    private List<PacketListVO> buildTree(List<PacketListVO> packetListVOList, Long parentId) {
        List<PacketListVO> children = new ArrayList<>();

        for (PacketListVO packetListVO : packetListVOList) {
            if ((parentId == null && packetListVO.getParentId() == null)
                    || (parentId != null && parentId.longValue() == packetListVO.getParentId().longValue())) {
                List<PacketListVO> childFolders = buildTree(packetListVOList, packetListVO.getId());
                packetListVO.setChildren(childFolders);
                children.add(packetListVO);
            }
        }

        return children;
    }
}
