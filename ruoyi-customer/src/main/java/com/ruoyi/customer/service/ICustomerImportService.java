package com.ruoyi.customer.service;

import java.util.List;
import com.ruoyi.customer.domain.CustomerImport;
import com.ruoyi.customer.domain.vo.CustomerImportListVO;
import org.springframework.data.util.Pair;

/**
 * 邮件导入
Service接口
 * 
 * @author tangJM.
 * @date 2023-11-19
 */
public interface ICustomerImportService 
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
     * 批量删除邮件导入

     * 
     * @param ids 需要删除的邮件导入
主键集合
     * @return 结果
     */
    public int deleteCustomerImportByIds(Long[] ids);

    /**
     * 删除邮件导入
信息
     * 
     * @param id 邮件导入
主键
     * @return 结果
     */
    public int deleteCustomerImportById(Long id);

    /**
     * 列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    Pair<Integer, List<CustomerImportListVO>> list(Integer pageNum, Integer pageSize);
}
