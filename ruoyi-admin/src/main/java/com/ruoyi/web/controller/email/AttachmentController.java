package com.ruoyi.web.controller.email;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileStreamUtil;
import com.ruoyi.email.domain.TaskAttachment;
import com.ruoyi.email.service.ITaskAttachmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.net.URLEncoder;


/**
 * 附件
 */
@Slf4j
@RestController
@RequestMapping("/email/attachment")
@Validated
public class AttachmentController {

    @Resource
    private ITaskAttachmentService taskAttachmentService;

    /**
     * 上传附件
     */
    @PreAuthorize("@ss.hasPermi('email:attachment:upload')")
    @Log(title = "上传附件", businessType = BusinessType.INSERT)
    @PostMapping("/upload")
    public AjaxResult uploadFile(@RequestParam("files") MultipartFile[] files) {
        if (files.length == 0) {
            throw new ServiceException("没有文件上传");
        }

        return AjaxResult.success(taskAttachmentService.uploadAttachments(files));
    }

    /**
     * 重命名
     */
    @PreAuthorize("@ss.hasPermi('email:attachment:rename')")
    @Log(title = "重命名", businessType = BusinessType.UPDATE)
    @PostMapping("/rename")
    public AjaxResult rename(@RequestBody TaskAttachment taskAttachment)
    {
        if (taskAttachment.getId() == null) {
            throw new ServiceException("id不能为空");
        }
        if (StringUtils.isEmpty(taskAttachment.getName())) {
            throw new ServiceException("名称不能为空");
        }

        return AjaxResult.success(taskAttachmentService.rename(taskAttachment.getId(), taskAttachment.getName()));
    }



    /**
     * 删除
     */
    @PreAuthorize("@ss.hasPermi('email:attachment:delete')")
    @Log(title = "删除", businessType = BusinessType.DELETE)
    @PostMapping("/delete")
    public AjaxResult delete(@RequestBody TaskAttachment taskAttachment)
    {
        if (taskAttachment.getId() == null) {
            throw new ServiceException("id不能为空");
        }

        return AjaxResult.success(taskAttachmentService.delete(taskAttachment.getId()));
    }


    /**
     * 附件下载
     */
    @PreAuthorize("@ss.hasPermi('email:attachment:download')")
    @Log(title = "附件下载", businessType = BusinessType.EXPORT)
    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> attachmentDownload(@PathVariable("id") Long id) {
        if (id == null) {
            throw new ServiceException("id不能为空");
        }

        TaskAttachment taskAttachment =  taskAttachmentService.selectTaskAttachmentById(id);
        if (taskAttachment == null) {
            throw new ServiceException("不存在附件");
        }

        String path = taskAttachment.getPath();
        if (StringUtils.isBlank(path)) {
            throw new ServiceException("不存在附件路径");
        }

        File file = new File(path);
        if (file.exists()) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            try {
                headers.setContentDispositionFormData("attachment", URLEncoder.encode(file.getName(), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                throw new ServiceException("附件下载失败");
            }

            byte[] fileContent = FileStreamUtil.file2byte(file);
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(fileContent);
        } else {
            // 处理文件不存在的逻辑
            throw new ServiceException();
        }
    }
}
