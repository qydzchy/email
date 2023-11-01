package com.ruoyi.customer.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.customer.domain.Details;
import com.ruoyi.customer.domain.dto.DetailsAddOrUpdateDTO;

/**
 * 客户详情Service接口
 * 
 * @author tangJM.
 * @date 2023-10-31
 */
public interface IDetailsService 
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
     * @param detailsAddOrUpdateDTO 客户详情
     * @return 结果
     */
    public boolean insertDetails(DetailsAddOrUpdateDTO detailsAddOrUpdateDTO);

    /**
     * 修改客户详情
     * 
     * @param detailsAddOrUpdateDTO 客户详情
     * @return 结果
     */
    public int updateDetails(DetailsAddOrUpdateDTO detailsAddOrUpdateDTO);

    /**
     * 批量删除客户详情
     * 
     * @param ids 需要删除的客户详情主键集合
     * @return 结果
     */
    public int deleteDetailsByIds(Long[] ids);

    /**
     * 删除客户详情信息
     * 
     * @param id 客户详情主键
     * @return 结果
     */
    public int deleteDetailsById(Long id);

    /**
     * 客户列表（分页）
     * @return
     */
    Map<String, Object> list(Integer seaType, Integer pageNum, Integer pageSize);
}
