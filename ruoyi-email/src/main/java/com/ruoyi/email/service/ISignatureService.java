package com.ruoyi.email.service;

import java.util.List;
import com.ruoyi.email.domain.Signature;
import com.ruoyi.email.domain.vo.SignatureListVO;

/**
 * 个性签名Service接口
 * 
 * @author tangJM
 * @date 2023-07-31
 */
public interface ISignatureService 
{
    /**
     * 查询个性签名
     * 
     * @param id 个性签名主键
     * @return 个性签名
     */
    public Signature selectSignatureById(Long id);

    /**
     * 查询个性签名列表
     * 
     * @param signature 个性签名
     * @return 个性签名集合
     */
    public List<Signature> selectSignatureList(Signature signature);

    /**
     * 新增个性签名
     * 
     * @param signature 个性签名
     * @return 结果
     */
    public int insertSignature(Signature signature);

    /**
     * 修改个性签名
     * 
     * @param signature 个性签名
     * @return 结果
     */
    public int updateSignature(Signature signature);

    /**
     * 批量删除个性签名
     * 
     * @param ids 需要删除的个性签名主键集合
     * @return 结果
     */
    public int deleteSignatureByIds(Long[] ids);

    /**
     * 删除个性签名信息
     * 
     * @param id 个性签名主键
     * @return 结果
     */
    public int deleteSignatureById(Long id);

    /**
     * 签名列表
     * @return
     */
    List<SignatureListVO> list();
}
