package com.ruoyi.web.controller.customer;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.customer.domain.dto.CustomerScheduleAddOrUpdateDTO;
import com.ruoyi.customer.domain.vo.CustomerScheduleListVO;
import org.springframework.data.util.Pair;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.customer.domain.CustomerSchedule;
import com.ruoyi.customer.service.ICustomerScheduleService;

import java.util.List;

/**
 * 客户日程Controller
 * 
 * @author tangJM.
 * @date 2023-11-03
 */
@RestController
@RequestMapping("/customer/customer/schedule")
public class CustomerScheduleController extends BaseController
{
    @Resource
    private ICustomerScheduleService customerScheduleService;


    @PreAuthorize("@ss.hasPermi('customer:customer:schedule:list')")
    @GetMapping("/list")
    public TableDataInfo list(
            String startTime,
            String endTime,
            List<Long> userIds,
            @NotNull(message = "页数不能为空") Integer pageNum,
            @NotNull(message = "页大小不能为空") Integer pageSize
    )
    {
        Pair<Integer, List<CustomerScheduleListVO>> pair = customerScheduleService.list(startTime, endTime, userIds, pageNum, pageSize);
        List<CustomerScheduleListVO> customerScheduleVOList = pair.getSecond();
        long total = pair.getFirst();

        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(customerScheduleVOList);
        rspData.setTotal(total);
        return rspData;
    }

    /**
     * 新增客户日程
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:schedule:add')")
    @Log(title = "新增客户日程", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CustomerScheduleAddOrUpdateDTO customerScheduleAddOrUpdateDTO)
    {
        checkParam(customerScheduleAddOrUpdateDTO);
        return toAjax(customerScheduleService.insertCustomerSchedule(customerScheduleAddOrUpdateDTO));
    }

    /**
     * 修改客户日程
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:schedule:edit')")
    @Log(title = "修改客户日程", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CustomerScheduleAddOrUpdateDTO customerScheduleAddOrUpdateDTO)
    {
        if (customerScheduleAddOrUpdateDTO.getId() == null) {
            throw new ServiceException("ID不能为空");
        }

        checkParam(customerScheduleAddOrUpdateDTO);
        return toAjax(customerScheduleService.updateCustomerSchedule(customerScheduleAddOrUpdateDTO));
    }

    /**
     * 删除客户日程
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:schedule:delete')")
    @Log(title = "删除客户日程", businessType = BusinessType.DELETE)
	@PostMapping("/delete")
    public AjaxResult delete(@RequestBody CustomerSchedule customerSchedule)
    {
        if (customerSchedule.getId() == null) {
            throw new ServiceException("ID不能为空");
        }
        return toAjax(customerScheduleService.deleteCustomerScheduleById(customerSchedule.getId()));
    }

    /**
     * 参数校验
     */
    private void checkParam(CustomerScheduleAddOrUpdateDTO customerScheduleAddOrUpdateDTO) {
        if (StringUtils.isBlank(customerScheduleAddOrUpdateDTO.getScheduleContent())) {
            throw new ServiceException("日程内容不能为空");
        }
        if (StringUtils.isBlank(customerScheduleAddOrUpdateDTO.getColor())) {
            throw new ServiceException("颜色不能为空");
        }
        if (customerScheduleAddOrUpdateDTO.getAllDayFlag() == null) {
            throw new ServiceException("是否全天不能为空");
        }
        if (customerScheduleAddOrUpdateDTO.getScheduleStartTime() == null || customerScheduleAddOrUpdateDTO.getScheduleEndTime() == null) {
            throw new ServiceException("日程时间不能为空");
        }
        if (customerScheduleAddOrUpdateDTO.getUserIds() == null || customerScheduleAddOrUpdateDTO.getUserIds().isEmpty()) {
            throw new ServiceException("参与人不能为空");
        }
    }
}
