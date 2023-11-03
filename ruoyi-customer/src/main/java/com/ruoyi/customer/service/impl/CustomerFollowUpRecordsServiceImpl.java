package com.ruoyi.customer.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.customer.domain.bo.CustomerFollowUpRecordsListBO;
import com.ruoyi.customer.domain.dto.CustomerFollowUpRecordsListDTO;
import com.ruoyi.customer.domain.vo.CustomerFollowUpRecordsListVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.CustomerFollowUpRecordsMapper;
import com.ruoyi.customer.domain.CustomerFollowUpRecords;
import com.ruoyi.customer.service.ICustomerFollowUpRecordsService;

import javax.annotation.Resource;

/**
 * 客户写跟进Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-11-02
 */
@Service
public class CustomerFollowUpRecordsServiceImpl implements ICustomerFollowUpRecordsService 
{
    @Resource
    private CustomerFollowUpRecordsMapper customerFollowUpRecordsMapper;

    /**
     * 查询客户写跟进
     * 
     * @param id 客户写跟进主键
     * @return 客户写跟进
     */
    @Override
    public CustomerFollowUpRecords selectCustomerFollowUpRecordsById(Long id)
    {
        return customerFollowUpRecordsMapper.selectCustomerFollowUpRecordsById(id);
    }

    /**
     * 查询客户写跟进列表
     * 
     * @param customerFollowUpRecords 客户写跟进
     * @return 客户写跟进
     */
    @Override
    public List<CustomerFollowUpRecords> selectCustomerFollowUpRecordsList(CustomerFollowUpRecords customerFollowUpRecords)
    {
        return customerFollowUpRecordsMapper.selectCustomerFollowUpRecordsList(customerFollowUpRecords);
    }

    /**
     * 新增客户写跟进
     * 
     * @param customerFollowUpRecords 客户写跟进
     * @return 结果
     */
    @Override
    public int insertCustomerFollowUpRecords(CustomerFollowUpRecords customerFollowUpRecords)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        Date submissionTime = customerFollowUpRecords.getSubmissionTime();
        if (submissionTime == null) {
            customerFollowUpRecords.setSubmissionTime(DateUtils.getNowDate());
        }

        customerFollowUpRecords.setCreateId(userId);
        customerFollowUpRecords.setCreateBy(username);
        customerFollowUpRecords.setCreateTime(DateUtils.getNowDate());
        customerFollowUpRecords.setUpdateId(userId);
        customerFollowUpRecords.setUpdateBy(username);
        customerFollowUpRecords.setUpdateTime(DateUtils.getNowDate());
        return customerFollowUpRecordsMapper.insertCustomerFollowUpRecords(customerFollowUpRecords);
    }

    /**
     * 修改客户写跟进
     * 
     * @param customerFollowUpRecords 客户写跟进
     * @return 结果
     */
    @Override
    public int updateCustomerFollowUpRecords(CustomerFollowUpRecords customerFollowUpRecords)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        Date submissionTime = customerFollowUpRecords.getSubmissionTime();
        if (submissionTime == null) {
            customerFollowUpRecords.setSubmissionTime(DateUtils.getNowDate());
        } else {
            // 获取当前时间
            Date currentTime = new Date();
            // 计算时间差
            long timeDifference = currentTime.getTime() - submissionTime.getTime();
            long hoursDifference = timeDifference / (60 * 60 * 1000); // 毫秒转小时
            if (hoursDifference >= 24) {
                throw new ServiceException("超过24小时发布的跟进，不可编辑");
            }
        }

        customerFollowUpRecords.setUpdateId(userId);
        customerFollowUpRecords.setUpdateBy(username);
        customerFollowUpRecords.setUpdateTime(DateUtils.getNowDate());
        return customerFollowUpRecordsMapper.updateCustomerFollowUpRecords(customerFollowUpRecords);
    }

    /**
     * 批量删除客户写跟进
     * 
     * @param ids 需要删除的客户写跟进主键
     * @return 结果
     */
    @Override
    public int deleteCustomerFollowUpRecordsByIds(Long[] ids)
    {
        return customerFollowUpRecordsMapper.deleteCustomerFollowUpRecordsByIds(ids);
    }

    /**
     * 删除客户写跟进信息
     * 
     * @param id 客户写跟进主键
     * @return 结果
     */
    @Override
    public int deleteCustomerFollowUpRecordsById(Long id)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        CustomerFollowUpRecords customerFollowUpRecords = customerFollowUpRecordsMapper.selectCustomerFollowUpRecordsById(id);
        Date submissionTime = customerFollowUpRecords.getSubmissionTime();
        if (submissionTime != null) {
            // 获取当前时间
            Date currentTime = new Date();
            // 计算时间差
            long timeDifference = currentTime.getTime() - submissionTime.getTime();
            long hoursDifference = timeDifference / (60 * 60 * 1000); // 毫秒转小时
            if (hoursDifference >= 24) {
                throw new ServiceException("超过24小时发布的跟进，不可删除");
            }
        }

        return customerFollowUpRecordsMapper.deleteCustomerFollowUpRecordsById(id, userId, username);
    }

    /**
     * 写跟进列表
     * @param customerFollowUpRecordsListDTO
     * @return
     */
    @Override
    public List<CustomerFollowUpRecordsListVO> list(CustomerFollowUpRecordsListDTO customerFollowUpRecordsListDTO) {
        List<CustomerFollowUpRecordsListBO> customerFollowUpRecordsBOList =  customerFollowUpRecordsMapper.list(customerFollowUpRecordsListDTO.getCustomerId(), customerFollowUpRecordsListDTO.getSearchText());
        Map<Long, List<CustomerFollowUpRecordsListBO>> customerFollowUpRecordsListBOMap = customerFollowUpRecordsBOList.stream().collect(Collectors.groupingBy(CustomerFollowUpRecordsListBO::getId));

        List<CustomerFollowUpRecordsListVO> customerFollowUpRecordsVOList = new ArrayList<>();
        customerFollowUpRecordsListBOMap.forEach((k,v)->{
            CustomerFollowUpRecordsListBO customerFollowUpRecordsListBO = v.get(0);
            CustomerFollowUpRecordsListVO customerFollowUpRecordsListVO = new CustomerFollowUpRecordsListVO();
            BeanUtils.copyProperties(customerFollowUpRecordsListBO, customerFollowUpRecordsListVO);

            List<String> commentList = new ArrayList<>();
            v.stream().forEach(item->{
                commentList.add(item.getComment());
            });
            customerFollowUpRecordsListVO.setCommentList(commentList);

            customerFollowUpRecordsVOList.add(customerFollowUpRecordsListVO);
        });

        return customerFollowUpRecordsVOList;
    }
}
