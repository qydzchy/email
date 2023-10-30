package com.ruoyi.customer.mapper;

import java.util.List;
import com.ruoyi.customer.domain.Source;
import org.apache.ibatis.annotations.Param;

/**
 * 客户来源Mapper接口
 * 
 * @author tangJM.
 * @date 2023-10-30
 */
public interface SourceMapper 
{
    /**
     * 查询客户来源
     * 
     * @param id 客户来源主键
     * @return 客户来源
     */
    public Source selectSourceById(Long id);

    /**
     * 查询客户来源列表
     * 
     * @param source 客户来源
     * @return 客户来源集合
     */
    public List<Source> selectSourceList(Source source);

    /**
     * 新增客户来源
     * 
     * @param source 客户来源
     * @return 结果
     */
    public int insertSource(Source source);

    /**
     * 修改客户来源
     * 
     * @param source 客户来源
     * @return 结果
     */
    public int updateSource(Source source);

    /**
     * 删除客户来源
     * 
     * @param id 客户来源主键
     * @return 结果
     */
    public int deleteSourceById(@Param("id") Long id, @Param("updateId") Long updateId, @Param("updateBy") String updateBy);

    /**
     * 批量删除客户来源
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSourceByIds(Long[] ids);
}
