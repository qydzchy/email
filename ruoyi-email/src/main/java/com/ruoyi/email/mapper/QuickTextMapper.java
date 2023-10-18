package com.ruoyi.email.mapper;

import java.util.Date;
import java.util.List;
import com.ruoyi.email.domain.QuickText;
import com.ruoyi.email.domain.vo.quicktext.QuickTextListVO;
import org.apache.ibatis.annotations.Param;

/**
 * 快速文本Mapper接口
 * 
 * @author tangJM
 * @date 2023-07-31
 */
public interface QuickTextMapper 
{
    /**
     * 查询快速文本
     * 
     * @param id 快速文本主键
     * @return 快速文本
     */
    public QuickText selectQuickTextById(Long id);

    /**
     * 查询快速文本列表
     * 
     * @param quickText 快速文本
     * @return 快速文本集合
     */
    public List<QuickText> selectQuickTextList(QuickText quickText);

    /**
     * 新增快速文本
     * 
     * @param quickText 快速文本
     * @return 结果
     */
    public int insertQuickText(QuickText quickText);

    /**
     * 修改快速文本
     * 
     * @param quickText 快速文本
     * @return 结果
     */
    public int updateQuickText(QuickText quickText);

    /**
     * 删除快速文本
     * 
     * @param id 快速文本主键
     * @return 结果
     */
    public int deleteQuickTextById(Long id);

    /**
     * 批量删除快速文本
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteQuickTextByIds(Long[] ids);

    /**
     * 根据创建人id查询快速文本列表
     * @param userId
     * @return
     */
    List<QuickTextListVO> getByCreateId(@Param("createId") Long userId);

    /**
     * 根据id删除快速文本
     * @param id
     * @param userId
     * @param nowDate
     */
    void deleteById(@Param("id") Long id, @Param("createId") Long userId, @Param("updateTime") Date nowDate);
}
