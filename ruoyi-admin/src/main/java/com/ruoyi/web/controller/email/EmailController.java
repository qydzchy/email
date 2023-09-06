package com.ruoyi.web.controller.email;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.email.domain.TaskEmailSend;
import com.ruoyi.email.domain.dto.email.EmailSendSaveDTO;
import com.ruoyi.email.domain.vo.email.PullEmailInfoListVO;
import com.ruoyi.email.domain.vo.email.SendEmailInfoListVO;
import com.ruoyi.email.service.ITaskEmailPullService;
import com.ruoyi.email.service.ITaskEmailSendService;
import org.springframework.data.util.Pair;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/email/info")
@Validated
public class EmailController extends BaseController {

    @Resource
    private ITaskEmailPullService taskEmailPullService;

    @Resource
    private ITaskEmailSendService taskEmailSendService;

    /**
     * 获取收件列表-（首页）
     * @param taskId
     * @param readFlag
     * @param pendingFlag
     * @param pageNum
     * @param pageSize
     * @return
     */
    @PreAuthorize("@ss.hasPermi('email:pull:header:list')")
    @GetMapping("/list/pull/header")
    public TableDataInfo listPullHeader(Long taskId,
                                        Boolean readFlag,
                                        Boolean pendingFlag,
                                        @NotNull(message = "页数不能为空") Integer pageNum,
                                        @NotNull(message = "页大小不能为空") Integer pageSize)
    {
        Pair<Integer, List<Map<String, List<PullEmailInfoListVO>>>> pair = taskEmailPullService.listPullHeader(taskId, readFlag, pendingFlag, pageNum, pageSize);

        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(pair.getSecond());
        rspData.setTotal(pair.getFirst());
        return rspData;
    }

    /**
     * 获取发件列表-（首页）
     * @param taskId
     * @param delFlag 已删除邮件
     * @param draftsFlag 草稿箱邮件
     * @param pageNum
     * @param pageSize
     * @return
     */
    @PreAuthorize("@ss.hasPermi('email:send:header:list')")
    @GetMapping("/list/send/header")
    public TableDataInfo listSendHeader(Long taskId,
                                        Boolean delFlag,
                                        Boolean draftsFlag,
                                        @NotNull(message = "页数不能为空") Integer pageNum,
                                        @NotNull(message = "页大小不能为空") Integer pageSize)
    {
        Pair<Integer, List<Map<String, List<SendEmailInfoListVO>>>> pair = taskEmailSendService.listSendHeader(taskId, delFlag, draftsFlag, pageNum, pageSize);

        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(pair.getSecond());
        rspData.setTotal(pair.getFirst());
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
        return AjaxResult.success(taskEmailSendService.save(dto));
    }


    /**
     * 邮件发送-（写信）
     */
    @PreAuthorize("@ss.hasPermi('email:send')")
    @Log(title = "邮件发送-写信", businessType = BusinessType.UPDATE)
    @PostMapping("/send")
    public AjaxResult send(@RequestBody TaskEmailSend taskEmailSend)
    {
        if (taskEmailSend.getId() == null) {
            throw new ServiceException("id不能为空");
        }

        return toAjax(taskEmailSendService.send(taskEmailSend.getId()));
    }
}
