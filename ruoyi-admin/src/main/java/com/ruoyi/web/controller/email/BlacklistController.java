package com.ruoyi.web.controller.email;

import java.util.List;
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
import com.ruoyi.email.domain.Blacklist;
import com.ruoyi.email.service.IBlacklistService;
import com.ruoyi.common.core.page.TableDataInfo;

import javax.annotation.Resource;

/**
 * 黑名单Controller
 * 
 * @author tangJM
 * @date 2023-12-22
 */
@RestController
@RequestMapping("/email/blacklist")
public class BlacklistController extends BaseController
{
    @Resource
    private IBlacklistService blacklistService;

    /**
     * 查询黑名单列表
     */
    @PreAuthorize("@ss.hasPermi('email:blacklist:list')")
    @GetMapping("/list")
    public TableDataInfo list(Blacklist blacklist)
    {
        startPage();
        List<Blacklist> list = blacklistService.selectBlacklistList(blacklist);
        return getDataTable(list);
    }

    /**
     * 新增黑名单
     */
    @PreAuthorize("@ss.hasPermi('email:blacklist:add')")
    @Log(title = "新增黑名单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody Blacklist blacklist)
    {
        return toAjax(blacklistService.insertBlacklist(blacklist));
    }

    /**
     * 修改黑名单
     */
    @PreAuthorize("@ss.hasPermi('email:blacklist:edit')")
    @Log(title = "修改黑名单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody Blacklist blacklist)
    {
        return toAjax(blacklistService.updateBlacklist(blacklist));
    }

    /**
     * 删除黑名单
     */
    @PreAuthorize("@ss.hasPermi('email:blacklist:delete')")
    @Log(title = "删除黑名单", businessType = BusinessType.DELETE)
	@PostMapping("/delete")
    public AjaxResult delete(@PathVariable Long[] ids)
    {
        return toAjax(blacklistService.deleteBlacklistByIds(ids));
    }
}
