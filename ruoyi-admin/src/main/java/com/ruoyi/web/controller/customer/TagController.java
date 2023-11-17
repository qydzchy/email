package com.ruoyi.web.controller.customer;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.enums.customer.TagTypeEnum;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.customer.domain.Tag;
import com.ruoyi.customer.service.ITagService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 客户标签Controller
 * 
 * @author tangJM.
 * @date 2023-11-17
 */
@RestController
@RequestMapping("/customer/tag")
public class TagController extends BaseController
{
    @Resource
    private ITagService tagService;

    /**
     * 查询客户标签列表
     */
    @PreAuthorize("@ss.hasPermi('customer:tag:list')")
    @GetMapping("/list")
    public AjaxResult list(Integer type)
    {
        return success(tagService.list(type));
    }

    /**
     * 新增个人标签
     */
    @PreAuthorize("@ss.hasPermi('customer:tag:personal:add')")
    @Log(title = "新增个人标签", businessType = BusinessType.INSERT)
    @PostMapping("/personal/add")
    public AjaxResult addPersonal(@RequestBody Tag tag)
    {
        checkParam(tag);
        tag.setType(TagTypeEnum.PERSONAL.getType());
        return toAjax(tagService.insertTag(tag));
    }

    /**
     * 新增公司标签
     */
    @PreAuthorize("@ss.hasPermi('customer:tag:company:add')")
    @Log(title = "新增公司标签", businessType = BusinessType.INSERT)
    @PostMapping("/company/add")
    public AjaxResult addCompany(@RequestBody Tag tag)
    {
        checkParam(tag);
        tag.setType(TagTypeEnum.COMPANY.getType());
        return toAjax(tagService.insertTag(tag));
    }

    /**
     * 修改个人标签
     */
    @PreAuthorize("@ss.hasPermi('customer:tag:personal:edit')")
    @Log(title = "修改个人标签", businessType = BusinessType.UPDATE)
    @PostMapping("/personal/edit")
    public AjaxResult editPersonal(@RequestBody Tag tag)
    {
        if (tag.getId() == null) {
            throw new ServiceException("ID不能为空");
        }
        checkParam(tag);

        return toAjax(tagService.updateTag(tag));
    }

    /**
     * 修改公司标签
     */
    @PreAuthorize("@ss.hasPermi('customer:tag:company:edit')")
    @Log(title = "修改公司标签", businessType = BusinessType.UPDATE)
    @PostMapping("/company/edit")
    public AjaxResult editCompany(@RequestBody Tag tag)
    {
        if (tag.getId() == null) {
            throw new ServiceException("ID不能为空");
        }
        checkParam(tag);

        return toAjax(tagService.updateTag(tag));
    }

    /**
     * 删除个人标签
     */
    @PreAuthorize("@ss.hasPermi('customer:tag:personal:delete')")
    @Log(title = "删除个人标签", businessType = BusinessType.DELETE)
	@PostMapping("/personal/delete")
    public AjaxResult deletePersonal(@RequestBody Tag tag)
    {
        if (tag.getId() == null) {
            throw new ServiceException("ID不能为空");
        }

        return toAjax(tagService.deleteTagById(tag.getId()));
    }

    /**
     * 删除公司标签
     */
    @PreAuthorize("@ss.hasPermi('customer:tag:company:delete')")
    @Log(title = "删除公司标签", businessType = BusinessType.DELETE)
    @PostMapping("/company/delete")
    public AjaxResult deleteCompany(@RequestBody Tag tag)
    {
        if (tag.getId() == null) {
            throw new ServiceException("ID不能为空");
        }

        return toAjax(tagService.deleteTagById(tag.getId()));
    }

    /**
     * 设为公司标签
     */
    @PreAuthorize("@ss.hasPermi('customer:tag:company:set:as:company:tag')")
    @Log(title = "删除公司标签", businessType = BusinessType.DELETE)
    @PostMapping("/set/as/company/tag")
    public AjaxResult setAsCompanyTag(@RequestBody Tag tag)
    {
        if (tag.getId() == null) {
            throw new ServiceException("ID不能为空");
        }

        return toAjax(tagService.setAsCompanyTag(tag.getId()));
    }

    /**
     * 校验参数
     * @param tag
     */
    private void checkParam(Tag tag) {

        if (StringUtils.isBlank(tag.getName())) {
            throw new ServiceException("标签名称不能为空");
        }

        if (StringUtils.isBlank(tag.getColor())) {
            throw new ServiceException("标签颜色不能为空");
        }
    }
}
