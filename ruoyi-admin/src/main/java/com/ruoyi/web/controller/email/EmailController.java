package com.ruoyi.web.controller.email;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.email.domain.vo.email.PullEmailInfoListVO;
import com.ruoyi.email.service.ITaskEmailPullService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/email/info")
public class EmailController extends BaseController {

    @Resource
    private ITaskEmailPullService taskEmailPullService;

    @PreAuthorize("@ss.hasPermi('email:task:pull:header:list')")
    @GetMapping("/list/pull/header")
    public TableDataInfo listPullHeader(Long taskId)
    {
        startPage();
        List<PullEmailInfoListVO> list = taskEmailPullService.listPullHeader(taskId);
        return getDataTable(list);
    }
}
