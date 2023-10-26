package com.ruoyi.web.controller.customer;

import java.util.List;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.customer.domain.vo.PublicleadsReasonListVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.customer.domain.PublicleadsReason;
import com.ruoyi.customer.service.IPublicleadsReasonService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 移入公海原因Controller
 * 
 * @author tangJM.
 * @date 2023-10-25
 */

@RestController
@RequestMapping("/customer/public/leads/reason")
public class PublicleadsReasonController extends BaseController
{
    @Autowired
    private IPublicleadsReasonService publicleadsReasonService;

    /**
     * 查询移入公海原因列表
     */
    @PreAuthorize("@ss.hasPermi('customer:public:leads:reason:list')")
    @GetMapping("/list")
    public TableDataInfo list()
    {
        List<PublicleadsReasonListVO> list = publicleadsReasonService.list();
        return getDataTable(list);
    }

    /**
     * 新增移入公海原因
     */
    @PreAuthorize("@ss.hasPermi('customer:public:leads:reason:add')")
    @Log(title = "新增移入公海原因", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody PublicleadsReason publicleadsReason)
    {
        if (StringUtils.isBlank(publicleadsReason.getReason())) {
            throw new ServiceException("原因不能为空");
        }

        return toAjax(publicleadsReasonService.insertPublicleadsReason(publicleadsReason));
    }

    /**
     * 修改移入公海原因
     */
    @PreAuthorize("@ss.hasPermi('customer:public:leads:reason:edit')")
    @Log(title = "修改移入公海原因", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody PublicleadsReason publicleadsReason)
    {
        if (publicleadsReason.getId() == null) {
            throw new ServiceException("ID不能为空");
        }
        if (StringUtils.isBlank(publicleadsReason.getReason())) {
            throw new ServiceException("原因不能为空");
        }

        return toAjax(publicleadsReasonService.updatePublicleadsReason(publicleadsReason));
    }

    /**
     * 删除移入公海原因
     */
    @PreAuthorize("@ss.hasPermi('customer:public:leads:reason:delete')")
    @Log(title = "删除移入公海原因", businessType = BusinessType.DELETE)
	@PostMapping("/delete")
    public AjaxResult remove(@RequestBody PublicleadsReason publicleadsReason)
    {
        if (publicleadsReason.getId() == null) {
            throw new ServiceException("ID不能为空");
        }

        return toAjax(publicleadsReasonService.deletePublicleadsReasonById(publicleadsReason.getId()));
    }
}
