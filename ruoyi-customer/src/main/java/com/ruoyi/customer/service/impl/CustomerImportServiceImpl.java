package com.ruoyi.customer.service.impl;

import java.util.Collections;
import java.util.List;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.customer.domain.vo.CustomerImportListVO;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.CustomerImportMapper;
import com.ruoyi.customer.domain.CustomerImport;
import com.ruoyi.customer.service.ICustomerImportService;

import javax.annotation.Resource;

/**
 * 邮件导入
Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-11-19
 */
@Service
public class CustomerImportServiceImpl implements ICustomerImportService 
{
    @Resource
    private CustomerImportMapper customerImportMapper;

    /**
     * 查询邮件导入

     * 
     * @param id 邮件导入
主键
     * @return 邮件导入

     */
    @Override
    public CustomerImport selectCustomerImportById(Long id)
    {
        return customerImportMapper.selectCustomerImportById(id);
    }

    /**
     * 查询邮件导入
列表
     * 
     * @param customerImport 邮件导入

     * @return 邮件导入

     */
    @Override
    public List<CustomerImport> selectCustomerImportList(CustomerImport customerImport)
    {
        return customerImportMapper.selectCustomerImportList(customerImport);
    }

    /**
     * 新增邮件导入

     * 
     * @param customerImport 邮件导入

     * @return 结果
     */
    @Override
    public int insertCustomerImport(CustomerImport customerImport)
    {
        customerImport.setCreateTime(DateUtils.getNowDate());
        return customerImportMapper.insertCustomerImport(customerImport);
    }

    /**
     * 修改邮件导入

     * 
     * @param customerImport 邮件导入

     * @return 结果
     */
    @Override
    public int updateCustomerImport(CustomerImport customerImport)
    {
        customerImport.setUpdateTime(DateUtils.getNowDate());
        return customerImportMapper.updateCustomerImport(customerImport);
    }

    /**
     * 批量删除邮件导入

     * 
     * @param ids 需要删除的邮件导入
主键
     * @return 结果
     */
    @Override
    public int deleteCustomerImportByIds(Long[] ids)
    {
        return customerImportMapper.deleteCustomerImportByIds(ids);
    }

    /**
     * 删除邮件导入
信息
     * 
     * @param id 邮件导入
主键
     * @return 结果
     */
    @Override
    public int deleteCustomerImportById(Long id)
    {
        return customerImportMapper.deleteCustomerImportById(id);
    }

    /**
     * 列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Pair<Integer, List<CustomerImportListVO>> list(Integer pageNum, Integer pageSize) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();

        int count = customerImportMapper.count(userId);
        if (count == 0) {
            return Pair.of(0, Collections.emptyList());
        }
        int offset = (pageNum - 1) * pageSize;
        int limit = pageSize;
        List<CustomerImportListVO> customerImportVOList = customerImportMapper.list(userId, offset, limit);
        return Pair.of(count, customerImportVOList);
    }
}
