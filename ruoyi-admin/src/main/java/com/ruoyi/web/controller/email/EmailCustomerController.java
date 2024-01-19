package com.ruoyi.web.controller.email;

import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.customer.service.ICustomerEmailService;
import com.ruoyi.email.domain.vo.EmailListVO;
import com.ruoyi.email.service.ITaskEmailService;
import org.springframework.data.util.Pair;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
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
     * 客户模块-搜索的客户列表
     */
    @PreAuthorize("@ss.hasPermi('email:customer:search:list')")
    @GetMapping("/search/list")
    public AjaxResult searchList(String keyword) {
        if (StringUtils.isBlank(keyword)) {
            throw new ServiceException("搜索关键字不能为空");
        }

        return success(customerEmailService.searchList(keyword));
    }

    /**
     * 联系人列表
     */
    @PreAuthorize("@ss.hasPermi('email:customer:contact:email:list')")
    @GetMapping("/contact/email/list")
    public AjaxResult contactEmailList(Long customerId) {
        if (customerId == null) {
            throw new ServiceException("客户ID不能为空");
        }

        return success(customerEmailService.contactEmailList(customerId));
    }

    /**
     * 客户模块-客户邮件列表（分页）
     */
    @PreAuthorize("@ss.hasPermi('email:customer:email:list')")
    @GetMapping("/email/list")
    public TableDataInfo emailList(@NotNull(message = "客户ID不能为空") Long customerId,
                              Boolean attachmentFlag,
                              Boolean fixedFlag,
                              String emails,
                              Integer type,
                              String labelIds,
                              Integer keywordType,
                              String keyword,
                              @NotNull(message = "页数不能为空") Integer pageNum,
                              @NotNull(message = "页大小不能为空") Integer pageSize)
    {
        List<String> emailList = new ArrayList<>();
        if (StringUtils.isNotBlank(emails)) {
            String[] emailArr = emails.split(",");
            for (String email : emailArr) {
                emailList.add(email);
            }
        }

        List<Long> labelIdList = new ArrayList<>();
        if (StringUtils.isNotBlank(labelIds)) {
            String[] labelIdArr = labelIds.split(",");
            for (String labelId : labelIdArr) {
                labelIdList.add(Long.valueOf(labelId));
            }
        }

        Pair<Integer, List<Map<String, List<EmailListVO>>>> pair = taskEmailService.customerEmailList(customerId, fixedFlag, attachmentFlag, emailList, type, labelIdList, keywordType, keyword, pageNum, pageSize);
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
     * 客户往来邮件列表
     */
    @PreAuthorize("@ss.hasPermi('email:customer:dealing:email:list')")
    @GetMapping(value = "/dealing/email/list")
    public AjaxResult dealingEmailList(Long customerId, Boolean attachmentFlag)
    {
        if (customerId == null) {
            throw new ServiceException("客户ID不能为空");
        }

        return success(taskEmailService.customerDealingEmailList(customerId, attachmentFlag));
    }

}
