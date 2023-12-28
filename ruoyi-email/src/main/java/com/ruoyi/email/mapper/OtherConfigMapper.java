package com.ruoyi.email.mapper;

import java.util.List;
import com.ruoyi.email.domain.OtherConfig;
import com.ruoyi.email.domain.vo.OtherConfigVO;

/**
 * 其他配置Mapper接口
 * 
 * @author tangJM
 * @date 2023-07-31
 */
public interface OtherConfigMapper 
{
    /**
     * 查询其他配置
     * 
     * @param id 其他配置主键
     * @return 其他配置
     */
    public OtherConfig selectOtherConfigById(Long id);

    /**
     * 查询其他配置列表
     * 
     * @param otherConfig 其他配置
     * @return 其他配置集合
     */
    public List<OtherConfig> selectOtherConfigList(OtherConfig otherConfig);

    /**
     * 新增其他配置
     * 
     * @param otherConfig 其他配置
     * @return 结果
     */
    public int insertOtherConfig(OtherConfig otherConfig);

    /**
     * 修改其他配置
     * 
     * @param otherConfig 其他配置
     * @return 结果
     */
    public int updateOtherConfig(OtherConfig otherConfig);

    /**
     * 删除其他配置
     * 
     * @param id 其他配置主键
     * @return 结果
     */
    public int deleteOtherConfigById(Long id);

    /**
     * 批量删除其他配置
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteOtherConfigByIds(Long[] ids);

    /**
     * 根据创建人id查询
     * @param userId
     * @return
     */
    OtherConfigVO getByCreateId(Long userId);
}
