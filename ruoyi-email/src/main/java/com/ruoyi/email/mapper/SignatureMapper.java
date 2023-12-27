package com.ruoyi.email.mapper;

import java.util.List;
import com.ruoyi.email.domain.Signature;
import org.apache.ibatis.annotations.Param;

/**
 * 个性签名Mapper接口
 * 
 * @author tangJM
 * @date 2023-07-31
 */
public interface SignatureMapper 
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
     * 删除个性签名
     * 
     * @param id 个性签名主键
     * @return 结果
     */
    public int deleteSignatureById(@Param("id") Long id, @Param("updateId") Long updateId, @Param("updateBy") String updateBy);

    /**
     * 批量删除个性签名
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSignatureByIds(Long[] ids);
}
