package com.ruoyi.web.controller.customer;

import java.util.List;
import javax.annotation.Resource;

import com.ruoyi.customer.domain.vo.BlackListRecordsListVO;
import com.ruoyi.email.domain.dto.email.BatchDeleteDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.customer.service.IBlackListRecordsService;

/**
 * 建档黑名单Controller
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
@RestController
@RequestMapping("/customer/black/list/records")
public class BlackListRecordsController extends BaseController
{
    @Resource
    private IBlackListRecordsService blackListRecordsService;

    /**
     * 查询建档黑名单列表
     */
    @PreAuthorize("@ss.hasPermi('customer:black:list:records:list')")
    @GetMapping("/list")
    public AjaxResult list()
    {
        List<BlackListRecordsListVO> list = blackListRecordsService.list();
        return success(list);
    }


    /**
     * 删除建档黑名单
     */
    @PreAuthorize("@ss.hasPermi('customer:black:list:records:delete')")
    @Log(title = "建档黑名单", businessType = BusinessType.DELETE)
	@PostMapping("/batch/delete")
    public AjaxResult delete(@RequestBody BatchDeleteDTO batchDeleteDTO)
    {
        return toAjax(blackListRecordsService.batchDeleteByIds(batchDeleteDTO.getIds()));
    }
}
