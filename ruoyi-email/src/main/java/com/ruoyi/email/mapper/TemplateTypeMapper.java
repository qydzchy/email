package com.ruoyi.email.mapper;

import java.util.List;
import com.ruoyi.email.domain.TemplateType;

/**
 * 模板类型Mapper接口
 * 
 * @author tangJM
 * @date 2023-07-31
 */
public interface TemplateTypeMapper 
{
    /**
     * 查询模板类型
     * 
     * @param id 模板类型主键
     * @return 模板类型
     */
    public TemplateType selectTemplateTypeById(Long id);

    /**
     * 查询模板类型列表
     * 
     * @param templateType 模板类型
     * @return 模板类型集合
     */
    public List<TemplateType> selectTemplateTypeList(TemplateType templateType);

    /**
     * 新增模板类型
     * 
     * @param templateType 模板类型
     * @return 结果
     */
    public int insertTemplateType(TemplateType templateType);

    /**
     * 修改模板类型
     * 
     * @param templateType 模板类型
     * @return 结果
     */
    public int updateTemplateType(TemplateType templateType);

    /**
     * 删除模板类型
     * 
     * @param id 模板类型主键
     * @return 结果
     */
    public int deleteTemplateTypeById(Long id);

    /**
     * 批量删除模板类型
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTemplateTypeByIds(Long[] ids);
}
