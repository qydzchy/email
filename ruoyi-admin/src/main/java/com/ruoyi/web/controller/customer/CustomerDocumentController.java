package com.ruoyi.web.controller.customer;

import javax.annotation.Resource;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.file.FileStreamUtil;
import com.ruoyi.customer.domain.CustomerDocument;
import com.ruoyi.customer.domain.dto.CustomerDocumentListDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.customer.service.ICustomerDocumentService;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 客户文档Controller
 * 
 * @author tangJM.
 * @date 2023-11-16
 */
@RestController
@RequestMapping("/customer/customer/document")
public class CustomerDocumentController extends BaseController
{
    @Resource
    private ICustomerDocumentService customerDocumentService;

    /**
     * 查询客户文档列表
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:document:list')")
    @GetMapping("/list")
    public AjaxResult list(CustomerDocumentListDTO customerDocumentListDTO)
    {
        return success(customerDocumentService.list(customerDocumentListDTO));
    }

    /**
     * 上传多个文件
     * @param files
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:document:upload:multiple')")
    @Log(title = "上传多个文件", businessType = BusinessType.UPDATE)
    @PostMapping("/uploadMultiple")
    public AjaxResult uploadMultiple(Long customerId, MultipartFile[] files) {
        return success(customerDocumentService.uploadMultiple(customerId, files));
    }

    /**
     * 下载文件
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:document:download')")
    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable("id") Long id) {
        if (id == null) {
            throw new ServiceException("文件ID不能为空");
        }

        File file = customerDocumentService.download(id);
        if (file.exists()) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            try {
                headers.setContentDispositionFormData("attachment", URLEncoder.encode(file.getName(), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                logger.error("账单附件下载失败", e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }

            byte[] fileContent = FileStreamUtil.file2byte(file);
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(fileContent);
        }

        return ResponseEntity.notFound().build();
    }

    /**
     * 删除文档
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:document:delete')")
    @Log(title = "删除文档", businessType = BusinessType.DELETE)
    @PostMapping("/delete")
    public AjaxResult delete(@RequestBody CustomerDocument customerDocument)
    {
        if (customerDocument.getId() == null) {
            throw new ServiceException("ID不能为空");
        }

        return toAjax(customerDocumentService.delete(customerDocument.getId()));
    }
}
