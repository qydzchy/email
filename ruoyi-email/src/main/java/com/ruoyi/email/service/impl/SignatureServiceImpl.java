package com.ruoyi.email.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.email.service.ISignatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.SignatureMapper;
import com.ruoyi.email.domain.Signature;

/**
 * 个性签名Service业务层处理
 * 
 * @author tangJM
 * @date 2023-07-31
 */
@Service
public class SignatureServiceImpl implements ISignatureService
{
    @Autowired
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
        signature.setCreateTime(DateUtils.getNowDate());
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
        return signatureMapper.deleteSignatureById(id);
    }
}
