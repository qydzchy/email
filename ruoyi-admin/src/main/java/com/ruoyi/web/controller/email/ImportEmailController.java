package com.ruoyi.web.controller.email;

import java.util.List;
import javax.annotation.Resource;

import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.email.domain.vo.email.ImportListVO;
import com.ruoyi.email.domain.vo.folder.FolderListVO;
import com.ruoyi.email.domain.vo.task.TaskListVO;
import com.ruoyi.email.service.IFolderService;
import org.springframework.data.util.Pair;
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
import com.ruoyi.email.domain.ImportEmail;
import com.ruoyi.email.service.IImportEmailService;
import com.ruoyi.common.core.page.TableDataInfo;

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
    public AjaxResult upload(@RequestBody ImportEmail importEmail)
    {
        return toAjax(importEmailService.insertImportEmail(importEmail));
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
