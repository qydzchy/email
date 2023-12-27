package com.ruoyi.web.controller.email;

import java.util.List;
import javax.annotation.Resource;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.email.domain.vo.signature.SignatureListVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.email.domain.Signature;
import com.ruoyi.email.service.ISignatureService;

/**
 * 个性签名Controller
 * 
 * @author tangJM
 * @date 2023-12-27
 */
@RestController
@RequestMapping("/email/signature")
public class SignatureController extends BaseController
{
    @Resource
    private ISignatureService signatureService;

    /**
     * 查询个性签名列表
     */
    @PreAuthorize("@ss.hasPermi('email:signature:list')")
    @GetMapping("/list")
    public AjaxResult list()
    {
        List<SignatureListVO> list = signatureService.list();
        return success(list);
    }

    /**
     * 新增个性签名
     */
    @PreAuthorize("@ss.hasPermi('email:signature:add')")
    @Log(title = "新增个性签名", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody Signature signature)
    {
        checkParam(signature);
        return toAjax(signatureService.insertSignature(signature));
    }

    /**
     * 修改个性签名
     */
    @PreAuthorize("@ss.hasPermi('email:signature:edit')")
    @Log(title = "修改个性签名", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody Signature signature)
    {
        if (signature.getId() == null) {
            throw new ServiceException("id不能为空");
        }

        checkParam(signature);
        return toAjax(signatureService.updateSignature(signature));
    }

    /**
     * 删除个性签名
     */
    @PreAuthorize("@ss.hasPermi('email:signature:delete')")
    @Log(title = "个性签名", businessType = BusinessType.DELETE)
	@PostMapping("/delete")
    public AjaxResult delete(@RequestBody Signature signature)
    {
        if (signature.getId() == null) {
            throw new ServiceException("id不能为空");
        }

        return toAjax(signatureService.deleteSignatureById(signature.getId()));
    }

    /**
     * 校验参数
     * @param signature
     */
    private void checkParam(Signature signature) {
        if (StringUtils.isBlank(signature.getTitle())) {
            throw new ServiceException("名称不能为空");
        }

        if (StringUtils.isBlank(signature.getContent())) {
            throw new ServiceException("内容不能为空");
        }
    }
}
