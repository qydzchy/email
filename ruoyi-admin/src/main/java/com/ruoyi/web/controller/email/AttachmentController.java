package com.ruoyi.web.controller.email;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.email.domain.TaskAttachment;
import com.ruoyi.email.service.ITaskAttachmentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * 附件
 */
@RestController
@RequestMapping("/email/attachment")
@Validated
public class AttachmentController {

    @Resource
    private ITaskAttachmentService taskEmailAttachmentService;

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

        return AjaxResult.success(taskEmailAttachmentService.uploadAttachments(files));
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

        return AjaxResult.success(taskEmailAttachmentService.rename(taskAttachment.getId(), taskAttachment.getName()));
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

        return AjaxResult.success(taskEmailAttachmentService.delete(taskAttachment.getId()));
    }
}
