package com.ruoyi.web.controller.email;

import java.util.List;
import javax.annotation.Resource;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.email.domain.dto.folder.FolderSaveOrUpdateDTO;
import com.ruoyi.email.domain.vo.folder.FolderListVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
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
    @PostMapping("/add")
    public AjaxResult add(@RequestBody FolderSaveOrUpdateDTO dto)
    {
        return toAjax(folderService.insertFolder(dto));
    }

    /**
     * 修改文件夹
     */
    @PreAuthorize("@ss.hasPermi('email:folder:edit')")
    @Log(title = "文件夹", businessType = BusinessType.UPDATE)
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
    @Log(title = "文件夹", businessType = BusinessType.DELETE)
    @PostMapping("/delete")
    public AjaxResult delete(@RequestParam Long id)
    {
        return toAjax(folderService.deleteById(id));
    }
}
