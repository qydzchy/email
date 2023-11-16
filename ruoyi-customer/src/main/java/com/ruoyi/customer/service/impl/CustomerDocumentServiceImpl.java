package com.ruoyi.customer.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.customer.CustomerDocumentTypeEnum;
import com.ruoyi.common.enums.customer.FollowUpRulesTypeEnum;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.customer.domain.dto.CustomerDocumentListDTO;
import com.ruoyi.customer.domain.vo.CustomerDocumentListVO;
import com.ruoyi.customer.service.ICustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.CustomerDocumentMapper;
import com.ruoyi.customer.domain.CustomerDocument;
import com.ruoyi.customer.service.ICustomerDocumentService;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * 客户文档Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-11-16
 */
@Slf4j
@Service
public class CustomerDocumentServiceImpl implements ICustomerDocumentService 
{
    @Resource
    private CustomerDocumentMapper customerDocumentMapper;
    @Resource
    private ICustomerService customerService;

    @Value("${customer.document.upload-dir:D:\\back\\Desktop\\file\\}")
    private String uploadDirectory;

    /**
     * 文档列表
     * @param customerDocumentListDTO
     * @return
     */
    @Override
    public List<CustomerDocumentListVO> list(CustomerDocumentListDTO customerDocumentListDTO) {
        return customerDocumentMapper.list(customerDocumentListDTO);
    }

    /**
     * 删除多个文件
     * @param customerId
     * @param files
     */
    @Override
    public boolean uploadMultiple(Long customerId, MultipartFile[] files) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String userName = loginUser.getUsername();
        List<CustomerDocument> customerDocumentList = new ArrayList<>();
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue;
            }

            String fileName = file.getOriginalFilename();
            String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
            String filePath = uploadDirectory + File.separator + fileExtension;
            long fileSize = file.getSize();

            CustomerDocument customerDocument = new CustomerDocument();
            customerDocument.setCustomerId(customerId);
            customerDocument.setName(fileName);
            customerDocument.setExtension(fileExtension);
            customerDocument.setPath(filePath);
            customerDocument.setSize(fileSize);
            customerDocument.setType(CustomerDocumentTypeEnum.MANUAL_UPLOAD.getType());
            customerDocument.setDelFlag("0");
            customerDocument.setCreateId(userId);
            customerDocument.setCreateBy(userName);
            customerDocument.setCreateTime(DateUtils.getNowDate());
            customerDocument.setUpdateId(userId);
            customerDocument.setUpdateBy(userName);
            customerDocument.setUpdateTime(DateUtils.getNowDate());

            File directory = new File(uploadDirectory);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // 将上传文件到指定目录
            File targetFile = new File(filePath);
            try {
                file.transferTo(targetFile);
                log.info("Saved to: " + targetFile.getAbsolutePath());
            } catch (IOException e) {
                log.error("上传文件失败：{}", e);
                throw new ServiceException("上传文件失败");
            }
        }

        // 批量插入客户文档
        customerDocumentMapper.batchInsertCustomerDocument(customerDocumentList);
        // 客户跟进规则处理
        customerService.customerFollowUpRulesHandler(customerId, FollowUpRulesTypeEnum.UPLOAD_CUSTOMER_DOCUMENT);
        return true;
    }

    /**
     * 下载文档
     * @param id
     * @return
     */
    @Override
    public File download(Long id) {
        CustomerDocument customerDocument = customerDocumentMapper.selectCustomerDocumentById(id);
        if (customerDocument == null) {
            throw new ServiceException("文档不存在");
        }

        return new File(customerDocument.getPath());
    }

    /**
     * 删除文档
     * @param id
     * @return
     */
    @Override
    public boolean delete(Long id) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String userName = loginUser.getUsername();

        customerDocumentMapper.deleteCustomerDocumentById(id, userId, userName);
        return true;
    }
}
