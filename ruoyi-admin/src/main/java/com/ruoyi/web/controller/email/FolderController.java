package com.ruoyi.web.controller.email;

import javax.annotation.Resource;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.email.domain.dto.folder.FolderSaveOrUpdateDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.email.domain.Folder;
import com.ruoyi.email.service.IFolderService;

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
    @Log(title = "新增文件夹", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody FolderSaveOrUpdateDTO dto)
    {
        return toAjax(folderService.insertFolder(dto));
    }

    /**
     * 修改文件夹
     */
    @PreAuthorize("@ss.hasPermi('email:folder:edit')")
    @Log(title = "修改文件夹", businessType = BusinessType.UPDATE)
    @PostMapping("edit")
    public AjaxResult edit(@RequestBody FolderSaveOrUpdateDTO dto)
    {
        if (dto.getId() == null) {
            throw new ServiceException("id不能为空");
        }

        return toAjax(folderService.updateFolder(dto));
    }

    /**
     * 删除文件夹
     */
    @PreAuthorize("@ss.hasPermi('email:folder:delete')")
    @Log(title = "删除文件夹", businessType = BusinessType.DELETE)
    @PostMapping("/delete")
    public AjaxResult delete(@RequestBody Folder folder)
    {
        if (folder.getId() == null) {
            throw new ServiceException("id不能为空");
        }

        return toAjax(folderService.deleteById(folder.getId()));
    }

    /**
     * 获取类型文件夹列表
     */
    @PreAuthorize("@ss.hasPermi('email:folder:list:type')")
    @GetMapping("/type/list")
    public AjaxResult typeList()
    {
        return AjaxResult.success(folderService.typeList());
    }
}
