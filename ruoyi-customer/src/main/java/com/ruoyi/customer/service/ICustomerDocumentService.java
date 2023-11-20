package com.ruoyi.customer.service;

import java.io.File;
import java.util.List;
import com.ruoyi.customer.domain.dto.CustomerDocumentListDTO;
import com.ruoyi.customer.domain.vo.CustomerDocumentListVO;
import org.springframework.data.util.Pair;
import org.springframework.web.multipart.MultipartFile;

/**
 * 客户文档Service接口
 * 
 * @author tangJM.
 * @date 2023-11-16
 */
public interface ICustomerDocumentService 
{

    /**
     * 文档列表
     * @param customerDocumentListDTO
     * @return
     */
    List<CustomerDocumentListVO> list(CustomerDocumentListDTO customerDocumentListDTO);

    /**
     * 上传多个文件
     * @param customerId
     * @param files
     */
    boolean uploadMultiple(Long customerId, MultipartFile[] files);

    /**
     * 文件下载
     * @param id
     * @return
     */
    Pair<File, String> download(Long id);

    /**
     * 删除文档
     * @param id
     * @return
     */
    boolean delete(Long id);
}
