package com.ruoyi.web.controller.customer;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.customer.domain.Customer;
import com.ruoyi.customer.domain.dto.*;
import com.ruoyi.customer.domain.vo.PrivateleadsCustomerSimpleListVO;
import com.ruoyi.customer.domain.vo.PublicleadsCustomerSimpleListVO;
import com.ruoyi.customer.service.ISegmentService;
import org.springframework.data.util.Pair;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.customer.service.ICustomerService;

import java.util.List;

/**
 * 客户详情Controller
 *
 * @author tangJM.
 * @date 2023-11-02
 */
@RestController
@RequestMapping("/customer/customer")
@Validated
public class CustomerController extends BaseController
{
    @Resource
    private ICustomerService customerService;
    @Resource
    private ISegmentService segmentService;

    /**
     * 客群列表
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:segment:list')")
    @GetMapping("/segment/list")
    public AjaxResult segmentList()
    {
        return success(segmentService.segmentList());
    }


    /**
     * 获取团队成员列表
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:team:members:get')")
    @GetMapping("/get/team/members")
    public AjaxResult getTeamMembers()
    {
        return success(customerService.getTeamMembers());
    }

    /**
     * 查询私海客户列表
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:privateleads:list')")
    @GetMapping("/privateleads/list")
    public TableDataInfo privateleadsList(
            @NotNull(message = "客群不能为空") Long segmentId,
            @NotNull(message = "类型不能为空") Integer type,
            Long userId,
            @NotNull(message = "页数不能为空") Integer pageNum,
            @NotNull(message = "页大小不能为空") Integer pageSize)
    {
        Pair<Integer, List<PrivateleadsCustomerSimpleListVO>> pair = customerService.privateleadsList(segmentId, type, userId, pageNum, pageSize);
        List<PrivateleadsCustomerSimpleListVO> rows = pair.getSecond();
        long total = pair.getFirst();

        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(rows);
        rspData.setTotal(total);
        return rspData;
    }

    /**
     * 查询公海客户列表
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:publicleads:list')")
    @GetMapping("/publicleads/list")
    public TableDataInfo publicleadsList(
            Long publicleadsGroupsId,
            Long packetId,
            @NotNull(message = "页数不能为空") Integer pageNum,
            @NotNull(message = "页大小不能为空") Integer pageSize)
    {
        Pair<Integer, List<PublicleadsCustomerSimpleListVO>> pair = customerService.publicleadsList(publicleadsGroupsId, packetId, pageNum, pageSize);
        List<PublicleadsCustomerSimpleListVO> rows = pair.getSecond();
        long total = pair.getFirst();

        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(rows);
        rspData.setTotal(total);
        return rspData;
    }

    /**
     * 获取客户详情详细信息
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:detail')")
    @GetMapping(value = "/detail")
    public AjaxResult getCustomerDetail(Long id)
    {
        if (id == null) {
            throw new ServiceException("ID不能为空");
        }

        return success(customerService.getCustomerDetail(id));
    }

    /**
     * 新增客户
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:add')")
    @Log(title = "新增客户", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody CustomerAddOrUpdateDTO customerAddOrUpdateDTO)
    {
        return toAjax(customerService.insertCustomer(customerAddOrUpdateDTO));
    }

    /**
     * 修改客户
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:edit')")
    @Log(title = "修改客户", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody CustomerAddOrUpdateDTO customerAddOrUpdateDTO)
    {
        return toAjax(customerService.updateCustomer(customerAddOrUpdateDTO));
    }

    /**
     * 移入客户至分组
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:move:packet')")
    @Log(title = "移入客户至分组", businessType = BusinessType.UPDATE)
    @PostMapping("/move/customer/to/packet")
    public AjaxResult moveCustomerToPacket(@RequestBody CustomerPacketMoveDTO customerPacketMoveDTO)
    {
        if (customerPacketMoveDTO.getId() == null) {
            throw new ServiceException("ID不能为空");
        }
        if (customerPacketMoveDTO.getPacketId() == null) {
            throw new ServiceException("分组ID不能为空");
        }
        return toAjax(customerService.moveCustomerToPacket(customerPacketMoveDTO));
    }


    /**
     * todo 合并客户待开发
     * 合并客户
     */

    /**
     * 查询所有用户
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:get:all:users')")
    @GetMapping(value = "/get/all/users")
    public AjaxResult getAllUsers()
    {
        return success(customerService.getAllUsers());
    }

    /**
     * 转移给
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:transferred:to')")
    @Log(title = "转移给", businessType = BusinessType.UPDATE)
    @PostMapping("/transferred/to")
    public AjaxResult transferredTo(@RequestBody TransferredToDTO transferredToDTO)
    {
        if (transferredToDTO.getId() == null) {
            throw new ServiceException("ID不能为空");
        }
        if (transferredToDTO.getUserId() == null) {
            throw new ServiceException("转移给不能为空");
        }

        return toAjax(customerService.transferredTo(transferredToDTO));
    }

    /**
     * 共享给
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:share:to')")
    @Log(title = "共享给", businessType = BusinessType.UPDATE)
    @PostMapping("/share/to")
    public AjaxResult shareTo(@RequestBody ShareToDTO shareToDTO)
    {
        if (shareToDTO.getId() == null) {
            throw new ServiceException("ID不能为空");
        }
        if (shareToDTO.getUserIds() == null || shareToDTO.getUserIds().isEmpty()) {
            throw new ServiceException("请选择跟进人");
        }

        return toAjax(customerService.shareTo(shareToDTO));
    }

    /**
     * 取消跟进
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:cancel:follow:up')")
    @Log(title = "取消跟进", businessType = BusinessType.UPDATE)
    @PostMapping("/cancel/follow/up")
    public AjaxResult cancelFollowUp(@RequestBody Customer customer)
    {
        if (customer.getId() == null) {
            throw new ServiceException("ID不能为空");
        }

        return toAjax(customerService.cancelFollowUp(customer.getId()));
    }

    /**
     * 移入公海
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:move:to:publicleads')")
    @Log(title = "移入公海", businessType = BusinessType.UPDATE)
    @PostMapping("/move/to/publicleads")
    public AjaxResult moveToPublicleads(@RequestBody MoveToPublicleadsDTO moveToPublicleadsDTO)
    {
        if (moveToPublicleadsDTO.getId() == null) {
            throw new ServiceException("ID不能为空");
        }

        return toAjax(customerService.moveToPublicleads(moveToPublicleadsDTO));
    }

    /**
     * 重新分配
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:reassign:to')")
    @Log(title = "重新分配", businessType = BusinessType.UPDATE)
    @PostMapping("/reassign/to")
    public AjaxResult reassignTo(@RequestBody ReassignToDTO reassignToDTO)
    {
        if (reassignToDTO.getId() == null) {
            throw new ServiceException("ID不能为空");
        }
        if (reassignToDTO.getUserIds() == null || reassignToDTO.getUserIds().isEmpty()) {
            throw new ServiceException("请选择跟进人");
        }

        return toAjax(customerService.reassignTo(reassignToDTO));
    }

    /**
     * 查询客户跟进人
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:follow:up:personnel:list')")
    @GetMapping("/follow/up/personnel/list")
    public AjaxResult followUpPersonnelList(Long id)
    {
        if (id == null) {
            throw new ServiceException("ID不能为空");
        }

        return success(customerService.followUpPersonnelList(id));
    }

    /**
     * 取消跟进人
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:unassign:follow:up')")
    @Log(title = "取消跟进人", businessType = BusinessType.UPDATE)
    @PostMapping("/unassign/follow/up")
    public AjaxResult unassignFollowUp(@RequestBody UnassignFollowUpDTO unassignFollowUpDTO)
    {
        if (unassignFollowUpDTO.getId() == null) {
            throw new ServiceException("ID不能为空");
        }
        if (unassignFollowUpDTO.getUserId() == null) {
            throw new ServiceException("跟进人不能为空");
        }

        return toAjax(customerService.unassignFollowUp(unassignFollowUpDTO));
    }

    /**
     * 修改重点关注
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:edit:focusFlag')")
    @Log(title = "修改重点关注", businessType = BusinessType.UPDATE)
    @PostMapping("/edit/focusFlag")
    public AjaxResult editFocusFlag(@RequestBody Customer customer)
    {
        if (customer.getId() == null) {
            throw new ServiceException("ID不能为空");
        }

        return toAjax(customerService.editFocusFlag(customer.getId()));
    }

    /**
     * 查询公海分组
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:publicleads:groups:list')")
    @GetMapping("/publicleads/groups/list")
    public AjaxResult publicleadsGroupsList()
    {
        return success(customerService.publicleadsGroupsList());
    }

    /**
     * 变更公海分组
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:change:publicleads:groups')")
    @Log(title = "变更公海分组", businessType = BusinessType.UPDATE)
    @PostMapping("/change/publicleads/groups")
    public AjaxResult changePublicleadsGroups(@RequestBody ChangePublicleadsGroupsDTO changePublicleadsGroupsDTO)
    {
        if (changePublicleadsGroupsDTO.getId() == null) {
            throw new ServiceException("ID不能为空");
        }
        if (changePublicleadsGroupsDTO.getPublicleadsGroupsId() == null) {
            throw new ServiceException("公海分组ID不能为空");
        }

        return toAjax(customerService.changePublicleadsGroups(changePublicleadsGroupsDTO.getId(), changePublicleadsGroupsDTO.getPublicleadsGroupsId()));
    }

    /**
     * 移入私海
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:move:to:privateleads')")
    @Log(title = "移入私海", businessType = BusinessType.UPDATE)
    @PostMapping("/move/to/privateleads")
    public AjaxResult moveToPrivateleads(@RequestBody Customer customer)
    {
        if (customer.getId() == null) {
            throw new ServiceException("ID不能为空");
        }

        return toAjax(customerService.moveToPrivateleads(customer.getId(), customer.getPacketId()));
    }
}
