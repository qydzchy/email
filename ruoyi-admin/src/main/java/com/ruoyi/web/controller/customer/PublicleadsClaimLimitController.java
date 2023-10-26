package com.ruoyi.web.controller.customer;

import java.util.List;
import javax.annotation.Resource;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.customer.domain.dto.PublicleadsClaimLimitAddDTO;
import com.ruoyi.customer.domain.vo.PublicleadsClaimLimitListVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.customer.domain.PublicleadsClaimLimit;
import com.ruoyi.customer.service.IPublicleadsClaimLimitService;

/**
 * 领取上限Controller
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
@RestController
@RequestMapping("/customer/public/leads/claim/limit")
public class PublicleadsClaimLimitController extends BaseController
{
    @Resource
    private IPublicleadsClaimLimitService publicleadsClaimLimitService;

    /**
     * 查询领取上限列表
     */
    @PreAuthorize("@ss.hasPermi('customer:public:leads:claim:limit:list')")
    @GetMapping("/list")
    public AjaxResult list()
    {
        List<PublicleadsClaimLimitListVO> list = publicleadsClaimLimitService.list();
        return success(list);
    }

    /**
     * 新增领取上限
     */
    @PreAuthorize("@ss.hasPermi('customer:public:leads:claim:limit:add')")
    @Log(title = "新增领取上限", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody PublicleadsClaimLimitAddDTO publicleadsClaimLimitAddDTO)
    {
        if (publicleadsClaimLimitAddDTO.getUserIdList() == null || publicleadsClaimLimitAddDTO.getUserIdList().isEmpty()) {
            throw new ServiceException("领取人不能为空");
        }
        if (publicleadsClaimLimitAddDTO.getClaimLimit() == null) {
            throw new ServiceException("领取上限不能为空");
        }
        if (publicleadsClaimLimitAddDTO.getClaimPeriod() == null) {
            throw new ServiceException("领取周期不能为空");
        }

        return toAjax(publicleadsClaimLimitService.insertPublicleadsClaimLimit(publicleadsClaimLimitAddDTO));
    }

    /**
     * 删除领取上限
     */
    @PreAuthorize("@ss.hasPermi('customer:public:leads:claim:limit:delete')")
    @Log(title = "删除领取上限", businessType = BusinessType.DELETE)
	@PostMapping("/delete")
    public AjaxResult remove(@RequestBody PublicleadsClaimLimit publicleadsClaimLimit)
    {
        if (publicleadsClaimLimit.getId() == null) {
            throw new ServiceException("ID不能为空");
        }

        return toAjax(publicleadsClaimLimitService.deletePublicleadsClaimLimitById(publicleadsClaimLimit.getId()));
    }
}
