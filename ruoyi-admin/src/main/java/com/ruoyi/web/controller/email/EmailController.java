package com.ruoyi.web.controller.email;

import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.email.domain.vo.email.PullEmailInfoListVO;
import com.ruoyi.email.service.ITaskEmailPullService;
import org.springframework.data.util.Pair;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/email/info")
@Validated
public class EmailController extends BaseController {

    @Resource
    private ITaskEmailPullService taskEmailPullService;

    @PreAuthorize("@ss.hasPermi('email:task:pull:header:list')")
    @GetMapping("/list/pull/header")
    public TableDataInfo listPullHeader(Long taskId,
                                        Boolean readFlag,
                                        Boolean pendingFlag,
                                        @NotNull(message = "页数不能为空") Integer pageNum,
                                        @NotNull(message = "页大小不能为空") Integer pageSize)
    {
        Pair<Integer, List<Map<String, List<PullEmailInfoListVO>>>> pair = taskEmailPullService.listPullHeader(taskId, readFlag, pendingFlag, pageNum, pageSize);

        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(pair.getSecond());
        rspData.setTotal(pair.getFirst());
        return rspData;
    }



}
