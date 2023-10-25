package com.ruoyi.web.controller.customer;

import java.util.List;
import javax.annotation.Resource;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.customer.domain.vo.PacketListVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.customer.domain.Packet;
import com.ruoyi.customer.service.IPacketService;

/**
 * 客户分组Controller
 *
 * @author tangJM.
 * @date 2023-10-25
 */
@RestController
@RequestMapping("/customer/packet")
public class PacketController extends BaseController
{
    @Resource
    private IPacketService packetService;

    /**
     * 查询客户分组列表
     */
    @PreAuthorize("@ss.hasPermi('customer:packet:list')")
    @GetMapping("/list")
    public AjaxResult list()
    {
        List<PacketListVO> list = packetService.getPacketTree();
        return success(list);
    }

    /**
     * 新增客户分组
     */
    @PreAuthorize("@ss.hasPermi('customer:packet:add')")
    @Log(title = "新增客户分组", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Packet packet)
    {
        if (StringUtils.isBlank(packet.getName())) {
            throw new ServiceException("分组名称不能为空");
        }

        if (packet.getAvailableMember() == null) {
            throw new ServiceException("可用客户数不能为空");
        }

        return toAjax(packetService.insertPacket(packet));
    }

    /**
     * 修改客户分组
     */
    @PreAuthorize("@ss.hasPermi('customer:packet:edit')")
    @Log(title = "修改客户分组", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Packet packet)
    {
        if (packet.getId() == null) {
            throw new ServiceException("ID不能为空");
        }

        if (StringUtils.isBlank(packet.getName())) {
            throw new ServiceException("分组名称不能为空");
        }

        if (packet.getAvailableMember() == null) {
            throw new ServiceException("可用客户数不能为空");
        }

        return toAjax(packetService.updatePacket(packet));
    }

    /**
     * 删除客户分组
     */
    @PreAuthorize("@ss.hasPermi('customer:packet:delete')")
    @Log(title = "删除客户分组", businessType = BusinessType.DELETE)
    @PostMapping("/{id}")
    public AjaxResult delete(@RequestBody Packet packet)
    {
        if (packet.getId() == null) {
            throw new ServiceException("ID不能为空");
        }

        return toAjax(packetService.deletePacketById(packet.getId()));
    }
}
