package com.ruoyi.customer.mapper;

import java.util.List;
import com.ruoyi.customer.domain.Details;
import org.apache.ibatis.annotations.Param;

/**
 * 客户详情Mapper接口
 * 
 * @author tangJM.
 * @date 2023-10-31
 */
public interface DetailsMapper 
{
    /**
     * 查询客户详情
     * 
     * @param id 客户详情主键
     * @return 客户详情
     */
    public Details selectDetailsById(Long id);

    /**
     * 查询客户详情列表
     * 
     * @param details 客户详情
     * @return 客户详情集合
     */
    public List<Details> selectDetailsList(Details details);

    /**
     * 新增客户详情
     * 
     * @param details 客户详情
     * @return 结果
     */
    public Long insertDetails(Details details);

    /**
     * 修改客户详情
     * 
     * @param details 客户详情
     * @return 结果
     */
    public int updateDetails(Details details);

    /**
     * 删除客户详情
     * 
     * @param id 客户详情主键
     * @return 结果
     */
    public int deleteDetailsById(Long id);

    /**
     * 批量删除客户详情
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDetailsByIds(Long[] ids);

    /**
     * 统计客户数量
     * @return
     */
    int count(@Param("seaType") Integer seaType);
}
