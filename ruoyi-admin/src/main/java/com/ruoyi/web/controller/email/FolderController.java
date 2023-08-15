package com.ruoyi.web.controller.email;

import java.util.List;
import javax.annotation.Resource;

import com.ruoyi.email.domain.vo.folder.FolderListVO;
import org.springframework.security.access.prepost.PreAuthorize;
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
import com.ruoyi.email.domain.Folder;
import com.ruoyi.email.service.IFolderService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 文件夹Controller
 *
 * @author tangJM
 * @date 2023-08-15
 */
@RestController
@RequestMapping("/email/folder")
public class FolderController extends BaseController
{
    @Resource
    private IFolderService folderService;

    /**
     * 查询文件夹列表
     */
    @PreAuthorize("@ss.hasPermi('email:folder:list')")
    @GetMapping("/list")
    public AjaxResult list()
    {
        return AjaxResult.success(folderService.getFolderTree());
    }

    /**
     * 新增文件夹
     */
    @PreAuthorize("@ss.hasPermi('email:folder:add')")
    @Log(title = "文件夹", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Folder folder)
    {
        return toAjax(folderService.insertFolder(folder));
    }

    /**
     * 修改文件夹
     */
    @PreAuthorize("@ss.hasPermi('email:folder:edit')")
    @Log(title = "文件夹", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Folder folder)
    {
        return toAjax(folderService.updateFolder(folder));
    }

    /**
     * 删除文件夹
     */
    @PreAuthorize("@ss.hasPermi('email:folder:remove')")
    @Log(title = "文件夹", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(folderService.deleteFolderByIds(ids));
    }
}
