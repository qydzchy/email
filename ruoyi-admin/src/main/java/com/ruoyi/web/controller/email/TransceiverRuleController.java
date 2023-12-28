package com.ruoyi.web.controller.email;

import java.util.List;
import javax.annotation.Resource;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.email.domain.vo.TransceiverRuleListVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.email.domain.TransceiverRule;
import com.ruoyi.email.service.ITransceiverRuleService;

/**
 * 收发件规则Controller
 * 
 * @author tangJM
 * @date 2023-12-28
 */
@RestController
@RequestMapping("/email/rule")
public class TransceiverRuleController extends BaseController
{
    @Resource
    private ITransceiverRuleService transceiverRuleService;

    /**
     * 查询收发件规则列表
     */
    @PreAuthorize("@ss.hasPermi('email:rule:list')")
    @GetMapping("/list")
    public AjaxResult list()
    {
        List<TransceiverRuleListVO> list = transceiverRuleService.list();
        return success(list);
    }

    /**
     * 获取收发件规则详细信息
     */
    @PreAuthorize("@ss.hasPermi('email:rule:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(transceiverRuleService.getById(id));
    }

    /**
     * 新增收发件规则
     */
    @PreAuthorize("@ss.hasPermi('email:rule:add')")
    @Log(title = "新增收发件规则", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody TransceiverRule transceiverRule)
    {
        // 参数校验
        checkParam(transceiverRule);

        return toAjax(transceiverRuleService.insertTransceiverRule(transceiverRule));
    }

    /**
     * 修改收发件规则
     */
    @PreAuthorize("@ss.hasPermi('email:rule:edit')")
    @Log(title = "修改收发件规则", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody TransceiverRule transceiverRule)
    {
        if (transceiverRule.getId() == null) {
            throw new ServiceException("id不能为空");
        }

        checkParam(transceiverRule);
        return toAjax(transceiverRuleService.updateTransceiverRule(transceiverRule));
    }

    /**
     * 删除收发件规则
     */
    @PreAuthorize("@ss.hasPermi('email:rule:delete')")
    @Log(title = "删除收发件规则", businessType = BusinessType.DELETE)
	@PostMapping("/delete")
    public AjaxResult delete(@RequestBody TransceiverRule transceiverRule)
    {
        if (transceiverRule.getId() == null) {
            throw new ServiceException("id不能为空");
        }
        return toAjax(transceiverRuleService.deleteTransceiverRuleById(transceiverRule.getId()));
    }

    /**
     * 更新收发件规则状态
     */
    @PreAuthorize("@ss.hasPermi('email:rule:update:status')")
    @Log(title = "更新收发件规则状态", businessType = BusinessType.UPDATE)
    @PostMapping("/update/status")
    public AjaxResult updateStatus(@RequestBody TransceiverRule transceiverRule)
    {
        if (transceiverRule.getId() == null) {
            throw new ServiceException("id不能为空");
        }

        return toAjax(transceiverRuleService.updateStatus(transceiverRule.getId()));
    }


    /**
     * 参数校验
     * @param transceiverRule
     */
    private void checkParam(TransceiverRule transceiverRule) {
        if (transceiverRule.getRuleType() == null) {
            throw new ServiceException("规则类型不能为空");
        }

        if (StringUtils.isBlank(transceiverRule.getRuleName())) {
            throw new ServiceException("规则名称不能为空");
        }

        if (transceiverRule.getExecuteCondition() == null) {
            throw new ServiceException("执行条件不能为空");
        }

        if (StringUtils.isBlank(transceiverRule.getExecuteConditionContent())) {
            throw new ServiceException("执行条件内容不能为空");
        }

        if (transceiverRule.getExecuteOperation() == null) {
            throw new ServiceException("执行操作不能为空");
        }

        if (transceiverRule.getApplyToHistoryMailFlag() == null) {
            throw new ServiceException("应用于历史邮件不能为空");
        }

        if (transceiverRule.getOtherSendingRules() == null) {
            throw new ServiceException("其他发件规则不能为空");
        }

        if (transceiverRule.getStatus() == null) {
            throw new ServiceException("状态不能为空");
        }
    }
}
