package com.ruoyi.web.controller.customer;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.customer.service.ICustomerService;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * 客户导入
 */
@RestController
@RequestMapping("/customer/customer/import")
public class CustomerImportController extends BaseController {

    @Resource
    private ICustomerService customerService;

    private final ResourceLoader resourceLoader;

    public CustomerImportController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    /**
     * 下载客户模板
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:template:download')")
    @GetMapping("/template/download")
    public ResponseEntity<Resource> downloadTemplate(HttpServletResponse response) throws IOException
    {
        // 使用ResourceLoader加载模板资源
        org.springframework.core.io.Resource resource = resourceLoader.getResource("classpath:templates/客户导入模板.xlsx");

        // 设置响应的Content-Type和头部信息
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + resource.getFilename());

        // 将资源的输入流复制到响应的输出流中
        try (InputStream inputStream = resource.getInputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                response.getOutputStream().write(buffer, 0, bytesRead);
            }
        }

        return ResponseEntity.ok().build();
    }

    /**
     * 导入客户
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:import')")
    @Log(title = "导入客户", businessType = BusinessType.IMPORT)
    @PostMapping("/add")
    public AjaxResult add(@RequestParam("file") MultipartFile file)
    {
        // 检查文件类型是否是Excel
        if (!file.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            throw new ServiceException("只允许导入EXCEL文件");
        }

        return toAjax(customerService.importCustomer(file));
    }
}
