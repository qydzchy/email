package com.ruoyi.web.controller.customer;

import java.util.List;
import javax.annotation.Resource;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.customer.domain.dto.PublicleadsWhiteListAddDTO;
import com.ruoyi.customer.domain.vo.PublicleadsWhiteListListVO;
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
import com.ruoyi.customer.domain.PublicleadsWhiteList;
import com.ruoyi.customer.service.IPublicleadsWhiteListService;

/**
 * 移入公海白名单Controller
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
@RestController
@RequestMapping("/customer/public/leads/white/list")
public class PublicleadsWhiteListController extends BaseController
{
    @Resource
    private IPublicleadsWhiteListService publicleadsWhiteListService;

    /**
     * 查询移入公海白名单列表
     */
    @PreAuthorize("@ss.hasPermi('customer:public:leads:white:list:list')")
    @GetMapping("/list")
    public AjaxResult list()
    {
        List<PublicleadsWhiteListListVO> list = publicleadsWhiteListService.list();
        return success(list);
    }

    /**
     * 新增移入公海白名单
     */
    @PreAuthorize("@ss.hasPermi('customer:public:leads:white:list:add')")
    @Log(title = "新增移入公海白名单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody PublicleadsWhiteListAddDTO publicleadsWhiteListAddDTO)
    {

        if (publicleadsWhiteListAddDTO.getUserIdList() == null) {
            throw new ServiceException("请选择人员");
        }

        return toAjax(publicleadsWhiteListService.insertPublicleadsWhiteList(publicleadsWhiteListAddDTO.getUserIdList()));
    }

    /**
     * 删除移入公海白名单
     */
    @PreAuthorize("@ss.hasPermi('customer:public:leads:white:list:remove')")
    @Log(title = "删除移入公海白名单", businessType = BusinessType.DELETE)
	@PostMapping("/delete")
    public AjaxResult remove(@RequestBody PublicleadsWhiteList publicleadsWhiteList)
    {
        if (publicleadsWhiteList.getId() == null) {
            throw new ServiceException("id不能为空");
        }

        return toAjax(publicleadsWhiteListService.deletePublicleadsWhiteListById(publicleadsWhiteList.getId()));
    }
}
