package com.ruoyi.email.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.email.domain.vo.signature.SignatureListVO;
import com.ruoyi.email.service.ISignatureService;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.SignatureMapper;
import com.ruoyi.email.domain.Signature;

import javax.annotation.Resource;

/**
 * 个性签名Service业务层处理
 * 
 * @author tangJM
 * @date 2023-07-31
 */
@Service
public class SignatureServiceImpl implements ISignatureService
{
    @Resource
    private SignatureMapper signatureMapper;

    /**
     * 查询个性签名
     * 
     * @param id 个性签名主键
     * @return 个性签名
     */
    @Override
    public Signature selectSignatureById(Long id)
    {
        return signatureMapper.selectSignatureById(id);
    }

    /**
     * 查询个性签名列表
     * 
     * @param signature 个性签名
     * @return 个性签名
     */
    @Override
    public List<Signature> selectSignatureList(Signature signature)
    {
        return signatureMapper.selectSignatureList(signature);
    }

    /**
     * 新增个性签名
     * 
     * @param signature 个性签名
     * @return 结果
     */
    @Override
    public int insertSignature(Signature signature)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        Date now = DateUtils.getNowDate();
        signature.setCreateId(userId);
        signature.setCreateBy(username);
        signature.setCreateTime(now);
        signature.setUpdateId(userId);
        signature.setUpdateBy(username);
        signature.setUpdateTime(now);

        return signatureMapper.insertSignature(signature);
    }

    /**
     * 修改个性签名
     * 
     * @param signature 个性签名
     * @return 结果
     */
    @Override
    public int updateSignature(Signature signature)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        signature.setUpdateId(userId);
        signature.setUpdateBy(username);
        signature.setUpdateTime(DateUtils.getNowDate());
        return signatureMapper.updateSignature(signature);
    }

    /**
     * 批量删除个性签名
     * 
     * @param ids 需要删除的个性签名主键
     * @return 结果
     */
    @Override
    public int deleteSignatureByIds(Long[] ids)
    {
        return signatureMapper.deleteSignatureByIds(ids);
    }

    /**
     * 删除个性签名信息
     * 
     * @param id 个性签名主键
     * @return 结果
     */
    @Override
    public int deleteSignatureById(Long id)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        return signatureMapper.deleteSignatureById(id, userId, username);
    }

    /**
     * 签名列表
     * @return
     */
    @Override
    public List<SignatureListVO> list() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        Signature signatureParam = new Signature();
        signatureParam.setCreateId(userId);
        List<Signature> signatureList = signatureMapper.selectSignatureList(signatureParam);
        List<SignatureListVO> signatureVOList = new ArrayList<>();
        for (Signature signature : signatureList) {
            SignatureListVO signatureVO = new SignatureListVO();
            signatureVO.setId(signature.getId());
            signatureVO.setTitle(signature.getTitle());
            signatureVO.setContent(signature.getContent());
            signatureVOList.add(signatureVO);
        }

        return signatureVOList;
    }
}
