package com.ruoyi.web.controller.customer;

import java.util.List;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.customer.domain.dto.PublicleadsGroupAddOrUpdateDTO;
import com.ruoyi.customer.domain.vo.PublicleadsGroupsListVO;
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
import com.ruoyi.customer.domain.PublicleadsGroups;
import com.ruoyi.customer.service.IPublicleadsGroupsService;

/**
 * 公海分组Controller
 * 
 * @author tangJM.
 * @date 2023-10-25
 */
@RestController
@RequestMapping("/customer/public/leads/groups")
public class PublicleadsGroupsController extends BaseController
{
    @Autowired
    private IPublicleadsGroupsService publicleadsGroupsService;

    /**
     * 查询公海分组列表
     */
    @PreAuthorize("@ss.hasPermi('customer:public:leads:groups:list')")
    @GetMapping("/list")
    public AjaxResult list()
    {
        List<PublicleadsGroupsListVO> list = publicleadsGroupsService.list();
        return success(list);
    }

    /**
     * 新增公海分组
     */
    @PreAuthorize("@ss.hasPermi('customer:public:leads:groups:add')")
    @Log(title = "新增公海分组", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody PublicleadsGroupAddOrUpdateDTO publicleadsGroupAddOrUpdateDTO)
    {
        if (StringUtils.isBlank(publicleadsGroupAddOrUpdateDTO.getName())) {
            throw new ServiceException("分组名称不能为空");
        }
        if (StringUtils.isBlank(publicleadsGroupAddOrUpdateDTO.getUserIds())) {
            throw new ServiceException("分组成员不能为空");
        }
        return toAjax(publicleadsGroupsService.insertPublicleadsGroups(publicleadsGroupAddOrUpdateDTO));
    }

    /**
     * 修改公海分组
     */
    @PreAuthorize("@ss.hasPermi('customer:public:leads:groups:edit')")
    @Log(title = "修改公海分组", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody PublicleadsGroupAddOrUpdateDTO publicleadsGroupAddOrUpdateDTO)
    {
        if (publicleadsGroupAddOrUpdateDTO.getId() == null) {
            throw new ServiceException("ID不能为空");
        }
        if (StringUtils.isBlank(publicleadsGroupAddOrUpdateDTO.getName())) {
            throw new ServiceException("分组名称不能为空");
        }
        if (StringUtils.isBlank(publicleadsGroupAddOrUpdateDTO.getUserIds())) {
            throw new ServiceException("分组成员不能为空");
        }
        return toAjax(publicleadsGroupsService.updatePublicleadsGroups(publicleadsGroupAddOrUpdateDTO));
    }

    /**
     * 删除公海分组
     */
    @PreAuthorize("@ss.hasPermi('customer:public:leads:groups:delete')")
    @Log(title = "删除公海分组", businessType = BusinessType.DELETE)
	@PostMapping("/delete")
    public AjaxResult remove(@RequestBody PublicleadsGroups publicleadsGroups)
    {
        if (publicleadsGroups.getId() == null) {
            throw new ServiceException("ID不能为空");
        }

        return toAjax(publicleadsGroupsService.deletePublicleadsGroupsById(publicleadsGroups.getId()));
    }
}
