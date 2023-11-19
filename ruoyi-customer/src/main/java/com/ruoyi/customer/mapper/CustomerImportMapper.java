package com.ruoyi.customer.mapper;

import java.util.List;
import com.ruoyi.customer.domain.CustomerImport;
import com.ruoyi.customer.domain.vo.CustomerImportListVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

/**
 * 邮件导入
Mapper接口
 * 
 * @author tangJM.
 * @date 2023-11-19
 */
public interface CustomerImportMapper 
{
    /**
     * 查询邮件导入

     * 
     * @param id 邮件导入
主键
     * @return 邮件导入

     */
    public CustomerImport selectCustomerImportById(Long id);

    /**
     * 查询邮件导入
列表
     * 
     * @param customerImport 邮件导入

     * @return 邮件导入
集合
     */
    public List<CustomerImport> selectCustomerImportList(CustomerImport customerImport);

    /**
     * 新增邮件导入

     * 
     * @param customerImport 邮件导入

     * @return 结果
     */
    public int insertCustomerImport(CustomerImport customerImport);

    /**
     * 修改邮件导入

     * 
     * @param customerImport 邮件导入

     * @return 结果
     */
    public int updateCustomerImport(CustomerImport customerImport);

    /**
     * 删除邮件导入

     * 
     * @param id 邮件导入
主键
     * @return 结果
     */
    public int deleteCustomerImportById(Long id);

    /**
     * 批量删除邮件导入

     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCustomerImportByIds(Long[] ids);

    /**
     * 统计
     * @return
     */
    int count();

    /**
     * 列表
     * @param offset
     * @param limit
     * @return
     */
    List<CustomerImportListVO> list(@Param("offset") int offset, @Param("limit") int limit);
}
