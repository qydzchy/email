package com.ruoyi.web.controller.email;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.email.domain.TaskEmail;
import com.ruoyi.email.domain.dto.email.EmailQuickReplyDTO;
import com.ruoyi.email.domain.dto.email.EmailSendSaveDTO;
import com.ruoyi.email.domain.vo.email.EmailListVO;
import com.ruoyi.email.service.ITaskEmailService;
import com.ruoyi.email.service.ITaskService;
import org.springframework.data.util.Pair;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/email/info")
@Validated
public class EmailController extends BaseController {

    @Resource
    private ITaskService taskService;

    @Resource
    private ITaskEmailService taskEmailService;

    /**
     * 获取邮件列表-（首页）
     * @param taskId
     * @param readFlag
     * @param pendingFlag
     * @param pageNum
     * @param pageSize
     * @return
     */
    @PreAuthorize("@ss.hasPermi('email:list')")
    @GetMapping("/list")
    public TableDataInfo listHeader(Long taskId,
                                        Boolean readFlag,
                                        Boolean pendingFlag,
                                        Boolean delFlag,
                                        Boolean draftsFlag,
                                        @NotNull(message = "类型不能为空") Integer type,
                                        @NotNull(message = "页数不能为空") Integer pageNum,
                                        @NotNull(message = "页大小不能为空") Integer pageSize)
    {
        List<Long> taskIdList = taskId == null ? taskService.getTaskIdByUserId() : Arrays.asList(taskId);

        Pair<Integer, List<Map<String, List<EmailListVO>>>> pair = taskEmailService.list(taskIdList, type, readFlag, pendingFlag, Optional.ofNullable(delFlag).orElse(false) ? "2" : "0", draftsFlag, pageNum, pageSize);
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
     * 邮件保存-（写信）
     */
    @PreAuthorize("@ss.hasPermi('email:save')")
    @Log(title = "邮件保存-写信", businessType = BusinessType.INSERT)
    @PostMapping("/save")
    public AjaxResult save(@RequestBody EmailSendSaveDTO dto)
    {
        return AjaxResult.success(taskEmailService.save(dto));
    }


    /**
     * 邮件发送-（写信）
     */
    @PreAuthorize("@ss.hasPermi('email:send')")
    @Log(title = "邮件发送-写信", businessType = BusinessType.UPDATE)
    @PostMapping("/send")
    public AjaxResult send(@RequestBody TaskEmail taskEmail)
    {
        if (taskEmail.getId() == null) {
            throw new ServiceException("id不能为空");
        }

        return toAjax(taskEmailService.send(taskEmail.getId()));
    }


    /**
     * 邮件固定
     */
    @PreAuthorize("@ss.hasPermi('email:fixed')")
    @Log(title = "邮件固定", businessType = BusinessType.UPDATE)
    @PostMapping("/fixed")
    public AjaxResult fixed(@RequestBody TaskEmail taskEmail)
    {
        if (taskEmail.getId() == null) {
            throw new ServiceException("id不能为空");
        }

        if (taskEmail.getFixedFlag() != null) {
            throw new ServiceException("是否固定不能为空");
        }

        return toAjax(taskEmailService.fixed(taskEmail.getId(), taskEmail.getFixedFlag()));
    }

    /**
     * 快速回复
     */
    @PreAuthorize("@ss.hasPermi('email:send:quick:reply')")
    @Log(title = "快速回复", businessType = BusinessType.UPDATE)
    @PostMapping("/quick/reply")
    public AjaxResult quickReply(@RequestBody EmailQuickReplyDTO emailQuickReplyDTO)
    {
        return toAjax(taskEmailService.quickReply(emailQuickReplyDTO));
    }

}
