package com.ruoyi.email.mapper;

import java.util.List;
import com.ruoyi.email.domain.General;
import com.ruoyi.email.domain.vo.GeneralVO;
import org.apache.ibatis.annotations.Param;

/**
 * 邮箱常规Mapper接口
 * 
 * @author tangJM
 * @date 2023-07-31
 */
public interface GeneralMapper 
{
    /**
     * 查询邮箱常规
     * 
     * @param id 邮箱常规主键
     * @return 邮箱常规
     */
    public General selectGeneralById(Long id);

    /**
     * 查询邮箱常规列表
     * 
     * @param general 邮箱常规
     * @return 邮箱常规集合
     */
    public List<General> selectGeneralList(General general);

    /**
     * 新增邮箱常规
     * 
     * @param general 邮箱常规
     * @return 结果
     */
    public int insertGeneral(General general);

    /**
     * 修改邮箱常规
     * 
     * @param general 邮箱常规
     * @return 结果
     */
    public int updateGeneral(General general);

    /**
     * 删除邮箱常规
     * 
     * @param id 邮箱常规主键
     * @return 结果
     */
    public int deleteGeneralById(Long id);

    /**
     * 批量删除邮箱常规
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGeneralByIds(Long[] ids);

    /**
     * 根据创建人id查询
     * @param createId
     * @return
     */
    GeneralVO getByCreateId(@Param("createId") Long createId);
}
