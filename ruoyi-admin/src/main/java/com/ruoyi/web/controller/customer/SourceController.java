package com.ruoyi.web.controller.customer;

import java.util.List;
import javax.annotation.Resource;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.customer.domain.vo.SourceListVO;
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
import com.ruoyi.customer.domain.Source;
import com.ruoyi.customer.service.ISourceService;

/**
 * 客户来源Controller
 * 
 * @author tangJM.
 * @date 2023-10-30
 */
@RestController
@RequestMapping("/customer/source")
public class SourceController extends BaseController
{
    @Resource
    private ISourceService sourceService;

    /**
     * 查询客户来源列表
     */
    @PreAuthorize("@ss.hasPermi('customer:source:list')")
    @GetMapping("/list")
    public AjaxResult list()
    {
        List<SourceListVO> sourceVOList = sourceService.list();
        return success(sourceVOList);
    }

    /**
     * 新增客户来源
     */
    @PreAuthorize("@ss.hasPermi('customer:source:add')")
    @Log(title = "新增客户来源", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody Source source)
    {
        if (StringUtils.isBlank(source.getName())) {
            throw new ServiceException("来源名称不能为空");
        }

        return toAjax(sourceService.insertSource(source));
    }

    /**
     * 修改客户来源
     */
    @PreAuthorize("@ss.hasPermi('customer:source:edit')")
    @Log(title = "修改客户来源", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody Source source)
    {
        if (source.getId() == null) {
            throw new ServiceException("ID不能为空");
        }
        if (StringUtils.isBlank(source.getName())) {
            throw new ServiceException("来源名称不能为空");
        }

        return toAjax(sourceService.updateSource(source));
    }

    /**
     * 删除客户来源
     */
    @PreAuthorize("@ss.hasPermi('customer:source:delete')")
    @Log(title = "删除客户来源", businessType = BusinessType.DELETE)
	@PostMapping("/delete")
    public AjaxResult delete(@RequestBody Source source)
    {
        if (source.getId() == null) {
            throw new ServiceException("ID不能为空");
        }

        return toAjax(sourceService.deleteSourceById(source.getId()));
    }
}
