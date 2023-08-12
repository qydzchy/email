package com.ruoyi.web.controller.email;

import java.util.List;
import java.util.Optional;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.email.domain.dto.EditTaskDTO;
import com.ruoyi.email.domain.vo.ListTaskVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.email.domain.Task;
import com.ruoyi.email.service.ITaskService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 邮箱任务Controller
 * 
 * @author tangJM
 * @date 2023-08-01
 */
@RestController
@RequestMapping("/email/task")
public class TaskController extends BaseController
{
    @Autowired
    private ITaskService taskService;

    /**
     * 查询邮箱任务列表
     */
    @PreAuthorize("@ss.hasPermi('email:task:list')")
    @GetMapping("/list")
    public TableDataInfo list()
    {
        List<ListTaskVO> list = taskService.listTask();
        return getDataTable(list);
    }

    /**
     * 获取邮箱任务详细信息
     */
    @PreAuthorize("@ss.hasPermi('email:task:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(taskService.selectTaskById(id));
    }

    /**
     * 新增邮箱任务
     */
    @PreAuthorize("@ss.hasPermi('email:task:add')")
    @Log(title = "邮箱任务", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody Task task)
    {
        // 新建任务时，检查参数
        addParamCheck(task);
        return toAjax(taskService.insertTask(task));
    }

    /**
     * 邮箱检测
     * @param id
     * @return
     */
    @PreAuthorize("@ss.hasPermi('email:task:test')")
    @Log(title = "邮箱检测", businessType = BusinessType.TEST)
    @PostMapping("test")
    public AjaxResult test(@RequestBody Long id)
    {
        return toAjax(taskService.testTask(id));
    }

    /**
     * 修改邮箱任务
     */
    @PreAuthorize("@ss.hasPermi('email:task:edit')")
    @Log(title = "邮箱任务", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody EditTaskDTO editTaskDTO)
    {
        return toAjax(taskService.updateTask(editTaskDTO));
    }

    /**
     * 删除邮箱任务
     */
    @PreAuthorize("@ss.hasPermi('email:task:remove')")
    @Log(title = "邮箱任务", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(taskService.deleteTaskByIds(ids));
    }

    /**
     * 新建任务时，检查参数
     * @param task
     */
    private void addParamCheck(Task task) {
        if (task.getProtocolType() != null) {
            if (task.getReceivingServer() == null) {
                throw new ServiceException("接收服务器不能为空");
            }
            if (task.getReceivingPort() == null) {
                throw new ServiceException("接收端口不能为空");
            }
            if (task.getOutgoingServer() == null) {
                throw new ServiceException("发送服务器不能为空");
            }
            if (task.getOutgoingPort() == null) {
                throw new ServiceException("发送端口不能为空");
            }
        }

        if (Optional.ofNullable(task.getCustomProxyFlag()).orElse(false)) {
            if (task.getProxyServer() == null) {
                throw new ServiceException("代理服务器不能为空");
            }
            if (task.getProxyPort() == null) {
                throw new ServiceException("代理端口不能为空");
            }
        }
    }
}
