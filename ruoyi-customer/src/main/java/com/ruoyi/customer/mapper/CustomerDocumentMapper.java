package com.ruoyi.customer.mapper;

import java.util.List;
import com.ruoyi.customer.domain.CustomerDocument;
import com.ruoyi.customer.domain.dto.CustomerDocumentListDTO;
import com.ruoyi.customer.domain.vo.CustomerDocumentListVO;
import org.apache.ibatis.annotations.Param;

/**
 * 客户文档Mapper接口
 * 
 * @author tangJM.
 * @date 2023-11-16
 */
public interface CustomerDocumentMapper 
{
    /**
     * 查询客户文档
     * 
     * @param id 客户文档主键
     * @return 客户文档
     */
    public CustomerDocument selectCustomerDocumentById(Long id);

    /**
     * 查询客户文档列表
     * 
     * @param customerDocument 客户文档
     * @return 客户文档集合
     */
    public List<CustomerDocument> selectCustomerDocumentList(CustomerDocument customerDocument);

    /**
     * 新增客户文档
     * 
     * @param customerDocument 客户文档
     * @return 结果
     */
    public int insertCustomerDocument(CustomerDocument customerDocument);

    /**
     * 修改客户文档
     * 
     * @param customerDocument 客户文档
     * @return 结果
     */
    public int updateCustomerDocument(CustomerDocument customerDocument);

    /**
     * 删除客户文档
     * 
     * @param id 客户文档主键
     * @return 结果
     */
    public int deleteCustomerDocumentById(@Param("id") Long id, @Param("updateId") Long updateId, @Param("updateBy") String updateBy);

    /**
     * 批量删除客户文档
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCustomerDocumentByIds(Long[] ids);

    /**
     * 文档列表
     * @param customerDocumentListDTO
     * @return
     */
    List<CustomerDocumentListVO> list(@Param("customerDocumentListDTO") CustomerDocumentListDTO customerDocumentListDTO);

    /**
     * 批量新增客户文档
     * @param customerDocumentList
     */
    void batchInsertCustomerDocument(@Param("customerDocumentList") List<CustomerDocument> customerDocumentList);
}
