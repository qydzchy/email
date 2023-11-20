package com.ruoyi.web.controller.customer;

import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.customer.domain.vo.CustomerDuplicateListVO;
import com.ruoyi.customer.service.ICustomerService;
import org.springframework.data.util.Pair;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/customer/customer/duplication")
public class CustomerDuplicationController extends BaseController {

    @Resource
    private ICustomerService customerService;

    /**
     * 客户查重筛选字段列表
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:duplicate:filter:column:list')")
    @GetMapping("/filter/column/list")
    public AjaxResult customerDuplicateFilterColumnList() {
        return success(customerService.customerDuplicateFilterColumnList());
    }

    /**
     * 客户查重列表（分页）
     */
    @PreAuthorize("@ss.hasPermi('customer:customer:duplicate:list')")
    @GetMapping("/list")
    public TableDataInfo duplicateList(
            @NotBlank(message = "字段名称不能为空") String columnName,
            String searchText,
            @NotNull(message = "页数不能为空") Integer pageNum,
            @NotNull(message = "页大小不能为空") Integer pageSize)
    {
        Pair<Integer, List<CustomerDuplicateListVO>> pair = customerService.duplicateList(columnName, searchText, pageNum, pageSize);
        List<CustomerDuplicateListVO> rows = pair.getSecond();
        long total = pair.getFirst();

        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(rows);
        rspData.setTotal(total);
        return rspData;
    }
}
