package com.ruoyi.web.controller.email;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.email.domain.TaskEmail;
import com.ruoyi.email.domain.dto.email.BatchDeleteDTO;
import com.ruoyi.email.domain.dto.email.EmailQuickReplyDTO;
import com.ruoyi.email.domain.dto.email.EmailSendSaveDTO;
import com.ruoyi.email.domain.vo.email.EmailFolderMoveDTO;
import com.ruoyi.email.domain.vo.email.EmailListVO;
import com.ruoyi.email.domain.vo.email.EmailReadFlagBatchUpdateDTO;
import com.ruoyi.email.domain.vo.email.EmailSpamFlagBatchUpdateDTO;
import com.ruoyi.email.service.ITaskEmailService;
import com.ruoyi.email.service.ITaskService;
import org.springframework.core.io.UrlResource;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    public TableDataInfo list(Long taskId,
                                        Boolean readFlag,
                                        Boolean pendingFlag,
                                        Boolean delFlag,
                                        Boolean draftsFlag,
                                        Boolean spamFlag,
                                        Boolean traceFlag,
                                        Long folderId,
                                        Integer type,
                                        @NotNull(message = "页数不能为空") Integer pageNum,
                                        @NotNull(message = "页大小不能为空") Integer pageSize)
    {
        List<Long> taskIdList = taskId == null ? taskService.getTaskIdByUserId() : Arrays.asList(taskId);

        Pair<Integer, List<Map<String, List<EmailListVO>>>> pair = taskEmailService.list(taskIdList, type, readFlag, pendingFlag, spamFlag, Optional.ofNullable(delFlag).orElse(false) ? "2" : "0", draftsFlag, traceFlag, folderId, pageNum, pageSize);
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

        if (taskEmail.getFixedFlag() == null) {
            throw new ServiceException("是否固定不能为空");
        }

        return toAjax(taskEmailService.fixed(taskEmail.getId(), taskEmail.getFixedFlag()));
    }

    /**
     * 邮件删除
     */
    @PreAuthorize("@ss.hasPermi('email:delete')")
    @Log(title = "邮件删除", businessType = BusinessType.DELETE)
    @PostMapping("/delete")
    public AjaxResult delete(@RequestBody BatchDeleteDTO batchDeleteDTO)
    {
        return toAjax(taskEmailService.delete(batchDeleteDTO.getIds()));
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

    /**
     * 更新邮件是否为已读邮件
     */
    @PreAuthorize("@ss.hasPermi('email:read')")
    @Log(title = "邮件是否为已读邮件", businessType = BusinessType.UPDATE)
    @PostMapping("/read")
    public AjaxResult read(@RequestBody EmailReadFlagBatchUpdateDTO emailReadFlagBatchUpdateDTO)
    {
        return toAjax(taskEmailService.read(emailReadFlagBatchUpdateDTO.getIds(), emailReadFlagBatchUpdateDTO.getReadFlag()));
    }

    /**
     * 更新邮件是否为垃圾邮件
     */
    @PreAuthorize("@ss.hasPermi('email:spam')")
    @Log(title = "邮件是否为垃圾邮件", businessType = BusinessType.UPDATE)
    @PostMapping("/spam")
    public AjaxResult spam(@RequestBody EmailSpamFlagBatchUpdateDTO emailSpamFlagBatchUpdateDTO)
    {
        return toAjax(taskEmailService.spam(emailSpamFlagBatchUpdateDTO.getIds(), emailSpamFlagBatchUpdateDTO.getSpamFlag()));
    }

    /**
     * 移动文件夹
     */
    @PreAuthorize("@ss.hasPermi('email:move:folder')")
    @Log(title = "移动文件夹", businessType = BusinessType.UPDATE)
    @PostMapping("/move/folder")
    public AjaxResult moveFolder(@RequestBody EmailFolderMoveDTO emailFolderMoveDTO)
    {
        return toAjax(taskEmailService.moveFolder(emailFolderMoveDTO.getIds(), emailFolderMoveDTO.getFolderId()));
    }

    /**
     * 邮件导出
     */
    @PreAuthorize("@ss.hasPermi('email:export')")
    @GetMapping("/export/{id}")
    public ResponseEntity<org.springframework.core.io.Resource> export(@PathVariable Long id) {
        // 获取邮件路径
        String emailPath = taskEmailService.getEmailPath(id);
        Path filePath = Paths.get(emailPath).normalize();
        org.springframework.core.io.Resource resource = null;
        try {
            resource = new UrlResource(filePath.toUri());
        } catch (MalformedURLException e) {
            logger.error("获取资源异常：{}", e);
            throw new ServiceException();
        }

        if (!resource.exists()) {
            throw new ServiceException("文件不存在");
        }

        String encodedFilename = null;
        try {
            encodedFilename = URLEncoder.encode(resource.getFilename(), "UTF-8").replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            throw new ServiceException();
        }

        String contentDisposition = "attachment; filename*=UTF-8''" + encodedFilename;

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(resource);
    }

    @PreAuthorize("@ss.hasPermi('email:attachment:upload')")
    @Log(title = "上传邮件文件附件", businessType = BusinessType.INSERT)
    @PostMapping("/upload/attachment")
    public AjaxResult uploadAttachment(@RequestBody TaskEmail taskEmail) {
        if (taskEmail.getId() == null) {
            throw new ServiceException("id不能为空");
        }

        return AjaxResult.success(taskEmailService.uploadAttachment(taskEmail.getId()));
    }
}
