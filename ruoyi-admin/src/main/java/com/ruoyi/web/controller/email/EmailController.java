package com.ruoyi.web.controller.email;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.email.domain.TaskEmail;
import com.ruoyi.email.domain.dto.email.*;
import com.ruoyi.email.domain.vo.DealingEmailListVO;
import com.ruoyi.email.domain.vo.EmailListVO;
import com.ruoyi.email.service.ITaskEmailService;
import com.ruoyi.email.service.ITaskService;
import com.ruoyi.system.service.ISysConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.UrlResource;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Slf4j
@RestController
@RequestMapping("/email/info")
@Validated
public class EmailController extends BaseController {

    @Resource
    private ITaskService taskService;
    @Resource
    private ITaskEmailService taskEmailService;
    @Resource
    private ISysConfigService configService;

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
    public TableDataInfo list(EmailListDTO emailListDTO,
                                        @NotNull(message = "页数不能为空") Integer pageNum,
                                        @NotNull(message = "页大小不能为空") Integer pageSize)
    {
        Long taskId = emailListDTO.getTaskId();
        List<Long> taskIdList = taskId == null ? taskService.getTaskIdByUserId() : Arrays.asList(taskId);

        /*Pair<Integer, List<Map<String, List<EmailListVO>>>> pair = taskEmailService.list(taskIdList, type, readFlag, pendingFlag, spamFlag, Optional.ofNullable(delFlag).orElse(false) ? "2" : "0", draftsFlag, traceFlag, fixedFlag, attachmentFlag, customerFlag, folderId, labelId, pageNum, pageSize);*/
        Pair<Integer, List<Map<String, List<EmailListVO>>>> pair = taskEmailService.list(emailListDTO, pageNum, pageSize);
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
     * 邮件详情
     * @return
     */
    @PreAuthorize("@ss.hasPermi('email:detail')")
    @Log(title = "邮件详情", businessType = BusinessType.OTHER)
    @GetMapping("/detail")
    public AjaxResult detail(Long id) {
        return AjaxResult.success(taskEmailService.detail(id));
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
     * 更新操作类型
     * @param taskEmail
     * @return
     */
    @PreAuthorize("@ss.hasPermi('email:update:operation:type')")
    @Log(title = "更新操作类型", businessType = BusinessType.UPDATE)
    @PostMapping("/update/operation/type")
    public AjaxResult updateOperationType(@RequestBody TaskEmail taskEmail)
    {
        if (taskEmail.getId() == null) {
            throw new ServiceException("id不能为空");
        }

        if (taskEmail.getReplyFlag() == null && taskEmail.getForwardFlag() == null) {
            return toAjax(false);
        }

        return toAjax(taskEmailService.updateOperationType(taskEmail));
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
     * 移动邮件到文件夹
     */
    @PreAuthorize("@ss.hasPermi('email:move:folder')")
    @Log(title = "移动邮件到文件夹", businessType = BusinessType.UPDATE)
    @PostMapping("/move/email/to/folder")
    public AjaxResult moveEmailToFolder(@RequestBody @Valid EmailFolderMoveDTO emailFolderMoveDTO)
    {
        if (emailFolderMoveDTO.getFolderId() == null) {
            throw new ServiceException("文件夹id不能为空");
        }

        if (emailFolderMoveDTO.getIds() == null || emailFolderMoveDTO.getIds().isEmpty()) {
            throw new ServiceException("请选择邮件");
        }

        return toAjax(taskEmailService.moveEmailToFolder(emailFolderMoveDTO.getIds(), emailFolderMoveDTO.getFolderId()));
    }

    /**
     * 移动邮件到标签
     */
    @PreAuthorize("@ss.hasPermi('email:move:label')")
    @Log(title = "移动邮件到标签", businessType = BusinessType.UPDATE)
    @PostMapping("/move/email/to/label")
    public AjaxResult moveEmailToLabel(@RequestBody @Valid EmailLabelMoveDTO emailLabelMoveDTO)
    {
        return toAjax(taskEmailService.moveEmailToLabel(emailLabelMoveDTO.getIds(), emailLabelMoveDTO.getLabelId()));
    }

    /**
     * 标记待处理
     */
    @PreAuthorize("@ss.hasPermi('email:pending')")
    @Log(title = "标记待处理", businessType = BusinessType.UPDATE)
    @PostMapping("/pending")
    public AjaxResult pending(@RequestBody EmailPendingDTO dto)
    {
        if (dto.getIds() == null || dto.getIds().isEmpty()) {
            throw new ServiceException("邮件ID不能为空");
        }
        if (dto.getPendingFlag() == null) {
            throw new ServiceException("是否待处理不能为空");
        }

        return toAjax(taskEmailService.pending(dto));
    }

    /**
     * 邮件导出
     */
    @PreAuthorize("@ss.hasPermi('email:export')")
    @Log(title = "邮件导出", businessType = BusinessType.EXPORT)
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

    /**
     * 上传邮件附件
     * @param taskEmail
     * @return
     */
    @PreAuthorize("@ss.hasPermi('email:attachment:upload')")
    @Log(title = "上传邮件附件", businessType = BusinessType.INSERT)
    @PostMapping("/upload/attachment")
    public AjaxResult uploadAttachment(@RequestBody TaskEmail taskEmail) {
        if (taskEmail.getId() == null) {
            throw new ServiceException("id不能为空");
        }

        return AjaxResult.success(taskEmailService.uploadAttachment(taskEmail.getId()));
    }

    /**
     * 下载邮件附件
     * @param id
     * @return
     * @throws IOException
     */
    @PreAuthorize("@ss.hasPermi('email:attachment:download')")
    @Log(title = "下载邮件附件", businessType = BusinessType.EXPORT)
    @GetMapping("/attachment/download/{id}")
    public ResponseEntity<byte[]> attachmentDownload(@PathVariable("id") Long id) throws IOException {
        if (id == null) {
            throw new ServiceException("邮件ID不能为空");
        }

        TaskEmail taskEmail = taskEmailService.selectTaskEmailById(id);
        String zipFileName = taskEmail.getTitle() + ".zip";
        File zipFile = new File(System.getProperty("java.io.tmpdir"), zipFileName);

        Set<String> addedFiles = new HashSet<>();  // 用于防止重复添加文件

        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile))) {
            List<String> filePathList = taskEmailService.getAttachmentByEmailId(id);
            for (String filePath : filePathList) {
                File file = new File(filePath);
                if (file.exists() && !addedFiles.contains(file.getName())) {
                    addedFiles.add(file.getName());  // 记录已添加的文件
                    try (FileInputStream fis = new FileInputStream(file)) {
                        ZipEntry zipEntry = new ZipEntry(file.getName());
                        zos.putNextEntry(zipEntry);
                        byte[] buffer = new byte[1024];
                        int length;
                        while ((length = fis.read(buffer)) >= 0) {
                            zos.write(buffer, 0, length);
                        }
                        zos.closeEntry();
                    }
                } else if (!file.exists()) {
                    log.warn("文件不存在，路径: " + filePath);
                } else {
                    log.warn("重复的文件名，未添加到ZIP: " + file.getName());
                }
            }
        } catch (IOException e) {
            log.error("创建ZIP文件失败", e);
            throw new ServiceException("创建ZIP文件失败");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", URLEncoder.encode(zipFileName, "UTF-8"));

        byte[] zipContent = Files.readAllBytes(zipFile.toPath());  // 使用Files工具类读取文件内容

        return ResponseEntity.ok().headers(headers).body(zipContent);
    }

    /**
     * 删除邮件标签
     * @param dto
     * @return
     */
    @PreAuthorize("@ss.hasPermi('email:delete:label')")
    @Log(title = "删除邮件标签", businessType = BusinessType.DELETE)
    @PostMapping("/delete/label")
    public AjaxResult deleteLabel(@RequestBody @Valid EmailLabelDeleteDTO dto) {
        return AjaxResult.success(taskEmailService.deleteLabel(dto.getEmailId(), dto.getLabelId()));
    }

    /**
     * 统计菜单邮件数量
     * @return
     */
    @PreAuthorize("@ss.hasPermi('email:count:menu')")
    @Log(title = "统计菜单邮件数量", businessType = BusinessType.OTHER)
    @GetMapping("/count/menu")
    public AjaxResult countMenu() {
        return AjaxResult.success(taskEmailService.countMenu());
    }


    /**
     * 往来邮件列表
     */
    @PreAuthorize("@ss.hasPermi('email:dealing:email:list')")
    @GetMapping(value = "/dealing/email/list")
    public TableDataInfo dealingEmailList(@NotNull(message = "id不能为空") Long id,
                                       Boolean attachmentFlag,
                                       @NotNull(message = "页数不能为空") Integer pageNum,
                                       @NotNull(message = "页大小不能为空") Integer pageSize)
    {
        Pair<Integer, List<DealingEmailListVO>> resultPair = taskEmailService.emailDealingEmailList(id, attachmentFlag, pageNum, pageSize);

        long total = resultPair.getFirst();
        List<DealingEmailListVO> rows = resultPair.getSecond();

        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(rows);
        rspData.setTotal(total);
        return rspData;
    }

    /**
     * 翻译
     */
    @PreAuthorize("@ss.hasPermi('email:translate')")
    @Log(title = "邮件内容翻译", businessType = BusinessType.OTHER)
    @PostMapping(value = "/translate")
    public AjaxResult translate(@RequestBody @Valid EmailTranslateDTO dto)
    {
        String accessKeyId = configService.selectConfigByKey("translate.access.key.id");
        String accessKeySecret = configService.selectConfigByKey("translate.access.key.secret");

        String sourceLanguage = dto.getSourceLanguage();
        String targetLanguage = dto.getTargetLanguage();
        String sourceText = dto.getSourceText();
        if (StringUtils.isNotBlank(accessKeyId) && StringUtils.isNotBlank(accessKeySecret)) {
            sourceText = taskEmailService.translate(sourceLanguage, targetLanguage, sourceText, accessKeyId, accessKeySecret);
        }

        return success(sourceText);
    }
}
