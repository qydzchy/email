package com.ruoyi.customer.mapper;

import java.util.List;
import com.ruoyi.customer.domain.CustomerFollowUpRecordsComment;
import org.apache.ibatis.annotations.Param;

/**
 * 写跟进评论Mapper接口
 * 
 * @author tangJM.
 * @date 2023-11-03
 */
public interface CustomerFollowUpRecordsCommentMapper 
{
    /**
     * 查询写跟进评论
     * 
     * @param id 写跟进评论主键
     * @return 写跟进评论
     */
    public CustomerFollowUpRecordsComment selectCustomerFollowUpRecordsCommentById(Long id);

    /**
     * 查询写跟进评论列表
     * 
     * @param customerFollowUpRecordsComment 写跟进评论
     * @return 写跟进评论集合
     */
    public List<CustomerFollowUpRecordsComment> selectCustomerFollowUpRecordsCommentList(CustomerFollowUpRecordsComment customerFollowUpRecordsComment);

    /**
     * 新增写跟进评论
     * 
     * @param customerFollowUpRecordsComment 写跟进评论
     * @return 结果
     */
    public int insertCustomerFollowUpRecordsComment(CustomerFollowUpRecordsComment customerFollowUpRecordsComment);

    /**
     * 修改写跟进评论
     * 
     * @param customerFollowUpRecordsComment 写跟进评论
     * @return 结果
     */
    public int updateCustomerFollowUpRecordsComment(CustomerFollowUpRecordsComment customerFollowUpRecordsComment);

    /**
     * 删除写跟进评论
     * 
     * @param id 写跟进评论主键
     * @return 结果
     */
    public int deleteCustomerFollowUpRecordsCommentById(@Param("id") Long id, @Param("updateId") Long updateId, @Param("updateBy") String updateBy);

    /**
     * 批量删除写跟进评论
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCustomerFollowUpRecordsCommentByIds(Long[] ids);
}
