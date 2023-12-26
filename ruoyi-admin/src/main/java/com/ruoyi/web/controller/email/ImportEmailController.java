package com.ruoyi.web.controller.email;

import java.util.List;
import javax.annotation.Resource;

import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.email.domain.vo.email.ImportListVO;
import com.ruoyi.email.domain.vo.folder.FolderListVO;
import com.ruoyi.email.domain.vo.task.TaskListVO;
import com.ruoyi.email.service.IFolderService;
import org.springframework.data.util.Pair;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.email.service.IImportEmailService;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 导入邮件Controller
 * 
 * @author tangJM
 * @date 2023-12-22
 */
@RestController
@RequestMapping("/email/import")
public class ImportEmailController extends BaseController
{
    @Resource
    private IImportEmailService importEmailService;
    @Resource
    private IFolderService folderService;

    /**
     * 邮箱任务列表
     */
    @PreAuthorize("@ss.hasPermi('email:import:task:list')")
    @GetMapping("/task/list")
    public AjaxResult taskList()
    {
        List<TaskListVO> list = importEmailService.getTaskList();
        return success(list);
    }

    /**
     * 文件夹列表
     */
    @PreAuthorize("@ss.hasPermi('email:import:folder:list')")
    @GetMapping("/folder/list")
    public AjaxResult folderList()
    {
        List<FolderListVO> list = folderService.getFolderTree();
        return success(list);
    }

    /**
     * 上传
     */
    @PreAuthorize("@ss.hasPermi('email:import:upload')")
    @Log(title = "上传", businessType = BusinessType.INSERT)
    @PostMapping("/upload")
    public AjaxResult upload(@RequestParam("file") MultipartFile file,
                             @RequestParam Long taskId,
                             @RequestParam String taskName,
                             @RequestParam Long folderId,
                             @RequestParam String folderName,
                             @RequestParam Boolean filterEmailFlag)
    {
        if (taskId == null) {
            throw new ServiceException("目标邮箱不能为空");
        }
        if (folderId == null) {
            throw new ServiceException("目标文件夹不能为空");
        }

        return toAjax(importEmailService.insertImportEmail(file, taskId, taskName, folderId, folderName, filterEmailFlag));
    }

    /**
     * 导入邮件列表（分页）
     */
    @PreAuthorize("@ss.hasPermi('email:import:page')")
    @GetMapping("/page")
    public TableDataInfo page()
    {
        Pair<Integer, List<ImportListVO>> pair = importEmailService.page();
        Integer total = pair.getFirst();
        List<ImportListVO> rows = pair.getSecond();

        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(rows);
        rspData.setTotal(total);
        return rspData;
    }
}
