package com.ruoyi.email.mapper;

import java.util.Date;
import java.util.List;
import com.ruoyi.email.domain.Label;
import com.ruoyi.email.domain.bo.EmailLabelBO;
import com.ruoyi.email.domain.vo.LabelListVO;
import org.apache.ibatis.annotations.Param;

/**
 * 标签Mapper接口
 * 
 * @author tangJM
 * @date 2023-07-31
 */
public interface LabelMapper 
{
    /**
     * 查询标签
     * 
     * @param id 标签主键
     * @return 标签
     */
    public Label selectLabelById(Long id);

    /**
     * 查询标签列表
     * 
     * @param label 标签
     * @return 标签集合
     */
    public List<Label> selectLabelList(Label label);

    /**
     * 新增标签
     * 
     * @param label 标签
     * @return 结果
     */
    public int insertLabel(Label label);

    /**
     * 修改标签
     * 
     * @param label 标签
     * @return 结果
     */
    public int updateLabel(Label label);

    /**
     * 删除标签
     * 
     * @param id 标签主键
     * @return 结果
     */
    public int deleteLabelById(Long id);

    /**
     * 批量删除标签
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteLabelByIds(Long[] ids);

    /**
     * 查询标签列表
     * @param userId
     * @return
     */
    List<LabelListVO> getByCreateId(@Param("createId") Long userId);

    /**
     * 修改标签颜色
     * @param id
     * @param color
     * @param createId
     * @param nowDate
     * @return
     */
    void updateColor(@Param("id") Long id, @Param("color") String color, @Param("createId") Long createId, @Param("updateTime") Date nowDate);

    /**
     * 修改标签名称
     * @param id
     * @param name
     * @param createId
     * @param nowDate
     */
    void updateName(@Param("id") Long id, @Param("name") String name, @Param("createId") Long createId, @Param("updateTime") Date nowDate);

    /**
     * 删除标签
     * @param id
     * @param createId
     */
    void deleteById(@Param("id") Long id, @Param("createId") Long createId, @Param("updateTime") Date nowDate);

    /**
     * 查询标签
     * @param emailIds
     * @return
     */
    List<EmailLabelBO> listByEmailIds(@Param("emailIds") List<Long> emailIds);
}
