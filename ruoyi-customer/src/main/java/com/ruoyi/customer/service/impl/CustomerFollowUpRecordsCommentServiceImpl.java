package com.ruoyi.customer.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.CustomerFollowUpRecordsCommentMapper;
import com.ruoyi.customer.domain.CustomerFollowUpRecordsComment;
import com.ruoyi.customer.service.ICustomerFollowUpRecordsCommentService;

/**
 * 写跟进评论Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-11-03
 */
@Service
public class CustomerFollowUpRecordsCommentServiceImpl implements ICustomerFollowUpRecordsCommentService 
{
    @Autowired
    private CustomerFollowUpRecordsCommentMapper customerFollowUpRecordsCommentMapper;

    /**
     * 查询写跟进评论
     * 
     * @param id 写跟进评论主键
     * @return 写跟进评论
     */
    @Override
    public CustomerFollowUpRecordsComment selectCustomerFollowUpRecordsCommentById(Long id)
    {
        return customerFollowUpRecordsCommentMapper.selectCustomerFollowUpRecordsCommentById(id);
    }

    /**
     * 查询写跟进评论列表
     * 
     * @param customerFollowUpRecordsComment 写跟进评论
     * @return 写跟进评论
     */
    @Override
    public List<CustomerFollowUpRecordsComment> selectCustomerFollowUpRecordsCommentList(CustomerFollowUpRecordsComment customerFollowUpRecordsComment)
    {
        return customerFollowUpRecordsCommentMapper.selectCustomerFollowUpRecordsCommentList(customerFollowUpRecordsComment);
    }

    /**
     * 新增写跟进评论
     * 
     * @param customerFollowUpRecordsComment 写跟进评论
     * @return 结果
     */
    @Override
    public int insertCustomerFollowUpRecordsComment(CustomerFollowUpRecordsComment customerFollowUpRecordsComment)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        customerFollowUpRecordsComment.setCreateId(userId);
        customerFollowUpRecordsComment.setCreateBy(username);
        customerFollowUpRecordsComment.setCreateTime(DateUtils.getNowDate());
        customerFollowUpRecordsComment.setUpdateId(userId);
        customerFollowUpRecordsComment.setUpdateBy(username);
        customerFollowUpRecordsComment.setUpdateTime(DateUtils.getNowDate());
        return customerFollowUpRecordsCommentMapper.insertCustomerFollowUpRecordsComment(customerFollowUpRecordsComment);
    }

    /**
     * 修改写跟进评论
     * 
     * @param customerFollowUpRecordsComment 写跟进评论
     * @return 结果
     */
    @Override
    public int updateCustomerFollowUpRecordsComment(CustomerFollowUpRecordsComment customerFollowUpRecordsComment)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        customerFollowUpRecordsComment.setUpdateId(userId);
        customerFollowUpRecordsComment.setUpdateBy(username);
        customerFollowUpRecordsComment.setUpdateTime(DateUtils.getNowDate());
        return customerFollowUpRecordsCommentMapper.updateCustomerFollowUpRecordsComment(customerFollowUpRecordsComment);
    }

    /**
     * 批量删除写跟进评论
     * 
     * @param ids 需要删除的写跟进评论主键
     * @return 结果
     */
    @Override
    public int deleteCustomerFollowUpRecordsCommentByIds(Long[] ids)
    {
        return customerFollowUpRecordsCommentMapper.deleteCustomerFollowUpRecordsCommentByIds(ids);
    }

    /**
     * 删除写跟进评论信息
     * 
     * @param id 写跟进评论主键
     * @return 结果
     */
    @Override
    public int deleteCustomerFollowUpRecordsCommentById(Long id)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        return customerFollowUpRecordsCommentMapper.deleteCustomerFollowUpRecordsCommentById(id, userId, username);
    }
}
