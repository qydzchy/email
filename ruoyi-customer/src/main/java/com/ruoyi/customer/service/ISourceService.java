package com.ruoyi.customer.service;

import java.util.List;
import com.ruoyi.customer.domain.Source;
import com.ruoyi.customer.domain.vo.SourceListVO;

/**
 * 客户来源Service接口
 * 
 * @author tangJM.
 * @date 2023-10-30
 */
public interface ISourceService 
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
     * 批量删除客户来源
     * 
     * @param ids 需要删除的客户来源主键集合
     * @return 结果
     */
    public int deleteSourceByIds(Long[] ids);

    /**
     * 删除客户来源信息
     * 
     * @param id 客户来源主键
     * @return 结果
     */
    public int deleteSourceById(Long id);

    /**
     * 客户来源列表
     * @return
     */
    List<SourceListVO> list();
}
