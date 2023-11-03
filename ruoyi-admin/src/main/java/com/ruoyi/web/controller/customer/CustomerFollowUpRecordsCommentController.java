package com.ruoyi.web.controller.customer;

import javax.annotation.Resource;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.customer.domain.CustomerFollowUpRecordsComment;
import com.ruoyi.customer.service.ICustomerFollowUpRecordsCommentService;

/**
 * 写跟进评论Controller
 * 
 * @author tangJM.
 * @date 2023-11-03
 */
@RestController
@RequestMapping("/customer/customer/follow/up/records/comment")
public class CustomerFollowUpRecordsCommentController extends BaseController
{
    @Resource
    private ICustomerFollowUpRecordsCommentService customerFollowUpRecordsCommentService;

    /**
     * 新增写跟进评论
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:follow:up:records:comment:add')")
    @Log(title = "新增写跟进评论", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody CustomerFollowUpRecordsComment customerFollowUpRecordsComment)
    {
        checkParam(customerFollowUpRecordsComment);
        return toAjax(customerFollowUpRecordsCommentService.insertCustomerFollowUpRecordsComment(customerFollowUpRecordsComment));
    }

    /**
     * 修改写跟进评论
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:follow:up:records:comment:edit')")
    @Log(title = "修改写跟进评论", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody CustomerFollowUpRecordsComment customerFollowUpRecordsComment)
    {
        if (customerFollowUpRecordsComment.getId() == null) {
            throw new ServiceException("ID不能为空");
        }

        checkParam(customerFollowUpRecordsComment);
        return toAjax(customerFollowUpRecordsCommentService.updateCustomerFollowUpRecordsComment(customerFollowUpRecordsComment));
    }

    /**
     * 删除写跟进评论
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:follow:up:records:comment:delete')")
    @Log(title = "删除写跟进评论", businessType = BusinessType.DELETE)
	@PostMapping("/delete")
    public AjaxResult delete(@RequestBody CustomerFollowUpRecordsComment customerFollowUpRecordsComment)
    {
        if (customerFollowUpRecordsComment.getId() == null) {
            throw new ServiceException("ID不能为空");
        }

        return toAjax(customerFollowUpRecordsCommentService.deleteCustomerFollowUpRecordsCommentById(customerFollowUpRecordsComment.getId()));
    }

    /**
     * 参数校验
     * @param customerFollowUpRecordsComment
     */
    private void checkParam(CustomerFollowUpRecordsComment customerFollowUpRecordsComment) {
        if (customerFollowUpRecordsComment.getFollowUpRecordsId() == null) {
            throw new ServiceException("跟进记录id不能为空");
        }

        if (StringUtils.isBlank(customerFollowUpRecordsComment.getComment())) {
            throw new ServiceException("评论内容不能为空");
        }
    }
}
