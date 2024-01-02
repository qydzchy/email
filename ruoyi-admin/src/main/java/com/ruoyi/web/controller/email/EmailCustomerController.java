package com.ruoyi.web.controller.email;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.customer.domain.CustomerFollowUpRecords;
import com.ruoyi.customer.domain.CustomerFollowUpRecordsComment;
import com.ruoyi.customer.domain.dto.CustomerFollowUpRecordsListDTO;
import com.ruoyi.customer.domain.vo.CustomerFollowUpRecordsListVO;
import com.ruoyi.customer.service.ICustomerEmailService;
import com.ruoyi.customer.service.ICustomerFollowUpRecordsCommentService;
import com.ruoyi.customer.service.ICustomerFollowUpRecordsService;
import com.ruoyi.customer.service.ICustomerService;
import com.ruoyi.email.domain.vo.EmailListVO;
import com.ruoyi.email.service.ITaskEmailService;
import org.springframework.data.util.Pair;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * 邮件系统-客户模块
 */
@RestController
@RequestMapping("/email/customer/")
public class EmailCustomerController extends BaseController {
    @Resource
    private ITaskEmailService taskEmailService;
    @Resource
    private ICustomerEmailService customerEmailService;
    @Resource
    private ICustomerService customerService;
    @Resource
    private ICustomerFollowUpRecordsService customerFollowUpRecordsService;
    @Resource
    private ICustomerFollowUpRecordsCommentService customerFollowUpRecordsCommentService;

    /**
     * 客户模块-通用列表
     */
    @PreAuthorize("@ss.hasPermi('email:customer:general:list')")
    @GetMapping("/general/list")
    public AjaxResult generalList()
    {
        return success(customerEmailService.generalList());
    }

    /**
     * 客户模块-公海分组列表
     * @return
     */
    @PreAuthorize("@ss.hasPermi('email:customer:publicleads:groups:list')")
    @GetMapping("/publicleads/groups/list")
    public AjaxResult publicleadsGroupsList()
    {
        return success(customerEmailService.publicleadsGroupsList());
    }

    /**
     * 客户模块-客户分组列表
     * @return
     */
    @PreAuthorize("@ss.hasPermi('email:customer:packet:list')")
    @GetMapping("/packet/list")
    public AjaxResult packetList() {
        return success(customerEmailService.packetList());
    }

    /**
     * 客户模块-客户来源列表
     */
    @PreAuthorize("@ss.hasPermi('email:customer:source:list')")
    @GetMapping("/source/list")
    public AjaxResult sourceList() {
        return success(customerEmailService.sourceList());
    }

    /**
     * 客户模块-客户状态列表
     */
    @PreAuthorize("@ss.hasPermi('email:customer:stage:list')")
    @GetMapping("/stage/list")
    public AjaxResult stageList() {
        return success(customerEmailService.stageList());
    }

    /**
     * 客户模块-客户星级列表
     */
    @PreAuthorize("@ss.hasPermi('email:customer:rating:list')")
    @GetMapping("/rating/list")
    public AjaxResult ratingList() {
        return success(customerEmailService.ratingList());
    }

    /**
     * 客户模块-客户活跃度列表
     */
    @PreAuthorize("@ss.hasPermi('email:customer:activity:list')")
    @GetMapping("/activity/list")
    public AjaxResult activityList() {
        return success(customerEmailService.activityList());
    }

    /**
     * 客户模块-客户邮件列表（分页）
     */
    @PreAuthorize("@ss.hasPermi('email:customer:email:list')")
    @GetMapping("/email/list")
    public TableDataInfo emailList(@NotNull(message = "客户ID不能为空") Long customerId,
                              @NotNull(message = "页数不能为空") Integer pageNum,
                              @NotNull(message = "页大小不能为空") Integer pageSize)
    {
        Pair<Integer, List<Map<String, List<EmailListVO>>>> pair = taskEmailService.customerEmailList(customerId, pageNum, pageSize);
        List<Map<String, List<EmailListVO>>> rows = pair.getSecond();
        long total = pair.getFirst();

        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(rows);
        rspData.setTotal(total);
        return rspData;
    }

    /**
     * 客户模块-获取客户详情详细信息
     */
    @PreAuthorize("@ss.hasPermi('email:customer:detail')")
    @GetMapping(value = "/detail")
    public AjaxResult getCustomerDetail(Long id)
    {
        if (id == null) {
            throw new ServiceException("ID不能为空");
        }

        return success(customerService.getCustomerDetail(id));
    }

    /**
     * 客户模块-写跟进列表
     */
    @PreAuthorize("@ss.hasPermi('email:customer:follow:up:records:list')")
    @GetMapping("/follow/up/records/list")
    public AjaxResult followUpRecordsList(CustomerFollowUpRecordsListDTO customerFollowUpRecordsListDTO)
    {
        if (customerFollowUpRecordsListDTO.getCustomerId() == null) {
            throw new ServiceException("客户ID不能为空");
        }

        List<CustomerFollowUpRecordsListVO> list = customerFollowUpRecordsService.list(customerFollowUpRecordsListDTO);
        return success(list);
    }

    /**
     * 客户模块-新增客户写跟进
     */
    @PreAuthorize("@ss.hasPermi('email:customer:follow:up:records:add')")
    @Log(title = "新增客户写跟进", businessType = BusinessType.INSERT)
    @PostMapping("/follow/up/records/add")
    public AjaxResult followUpRecordsAdd(@RequestBody CustomerFollowUpRecords customerFollowUpRecords)
    {
        if (customerFollowUpRecords.getCustomerId() == null) {
            throw new ServiceException("客户ID不能为空");
        }

        checkParam(customerFollowUpRecords);
        return toAjax(customerFollowUpRecordsService.insertCustomerFollowUpRecords(customerFollowUpRecords));
    }

    /**
     * 客户模块-修改客户写跟进
     */
    @PreAuthorize("@ss.hasPermi('email:customer:follow:up:records:edit')")
    @Log(title = "修改客户写跟进", businessType = BusinessType.UPDATE)
    @PostMapping("/follow/up/records/edit")
    public AjaxResult followUpRecordsEdit(@RequestBody CustomerFollowUpRecords customerFollowUpRecords)
    {
        if (customerFollowUpRecords.getId() == null) {
            throw new ServiceException("ID不能为空");
        }

        checkParam(customerFollowUpRecords);
        return toAjax(customerFollowUpRecordsService.updateCustomerFollowUpRecords(customerFollowUpRecords));
    }

    /**
     * 删除客户写跟进
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:follow:up:records:delete')")
    @Log(title = "删除客户写跟进", businessType = BusinessType.DELETE)
    @PostMapping("/follow/up/records/delete")
    public AjaxResult followUpRecordsDelete(@RequestBody CustomerFollowUpRecords customerFollowUpRecords)
    {
        if (customerFollowUpRecords.getId() == null) {
            throw new ServiceException("ID不能为空");
        }

        return toAjax(customerFollowUpRecordsService.deleteCustomerFollowUpRecordsById(customerFollowUpRecords.getId()));
    }

    /**
     * 新增写跟进评论
     */
    @PreAuthorize("@ss.hasPermi('email:customer:follow:up:records:comment:add')")
    @Log(title = "新增写跟进评论", businessType = BusinessType.INSERT)
    @PostMapping("/follow/up/records/comment/add")
    public AjaxResult followUpRecordsCommentAdd(@RequestBody CustomerFollowUpRecordsComment customerFollowUpRecordsComment)
    {
        if (customerFollowUpRecordsComment.getFollowUpRecordsId() == null) {
            throw new ServiceException("跟进记录id不能为空");
        }

        if (StringUtils.isBlank(customerFollowUpRecordsComment.getComment())) {
            throw new ServiceException("评论内容不能为空");
        }

        return toAjax(customerFollowUpRecordsCommentService.insertCustomerFollowUpRecordsComment(customerFollowUpRecordsComment));
    }

    /**
     * 修改写跟进评论
     */
    @PreAuthorize("@ss.hasPermi('email:customer:follow:up:records:comment:edit')")
    @Log(title = "修改写跟进评论", businessType = BusinessType.UPDATE)
    @PostMapping("/follow/up/records/comment/edit")
    public AjaxResult followUpRecordsCommentEdit(@RequestBody CustomerFollowUpRecordsComment customerFollowUpRecordsComment)
    {
        if (customerFollowUpRecordsComment.getId() == null) {
            throw new ServiceException("ID不能为空");
        }
        if (StringUtils.isBlank(customerFollowUpRecordsComment.getComment())) {
            throw new ServiceException("评论内容不能为空");
        }

        return toAjax(customerFollowUpRecordsCommentService.updateCustomerFollowUpRecordsComment(customerFollowUpRecordsComment));
    }

    /**
     * 删除写跟进评论
     */
    @PreAuthorize("@ss.hasPermi('email:customer:follow:up:records:comment:delete')")
    @Log(title = "删除写跟进评论", businessType = BusinessType.DELETE)
    @PostMapping("/follow/up/records/comment/delete")
    public AjaxResult followUpRecordsCommentDelete(@RequestBody CustomerFollowUpRecordsComment customerFollowUpRecordsComment)
    {
        if (customerFollowUpRecordsComment.getId() == null) {
            throw new ServiceException("ID不能为空");
        }

        return toAjax(customerFollowUpRecordsCommentService.deleteCustomerFollowUpRecordsCommentById(customerFollowUpRecordsComment.getId()));
    }

    /**
     * 参数校验
     * @param customerFollowUpRecords
     */
    private void checkParam(CustomerFollowUpRecords customerFollowUpRecords) {
        if (customerFollowUpRecords.getFollowUpType() == null) {
            throw new ServiceException("跟进类型不能为空");
        }
        if (StringUtils.isBlank(customerFollowUpRecords.getFollowUpContent())) {
            throw new ServiceException("跟进内容不能为空");
        }
        if (customerFollowUpRecords.getSubmissionTime() == null) {
            throw new ServiceException("提交时间不能为空");
        }
        if (customerFollowUpRecords.getFollowUpContactId() == null) {
            throw new ServiceException("跟进联系人不能为空");
        }
    }
}
