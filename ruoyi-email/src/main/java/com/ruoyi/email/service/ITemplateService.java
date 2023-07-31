package com.ruoyi.email.service;

import java.util.List;
import com.ruoyi.email.domain.Template;

/**
 * 模板Service接口
 * 
 * @author tangJM
 * @date 2023-07-31
 */
public interface ITemplateService 
{
    /**
     * 查询模板
     * 
     * @param id 模板主键
     * @return 模板
     */
    public Template selectTemplateById(Long id);

    /**
     * 查询模板列表
     * 
     * @param template 模板
     * @return 模板集合
     */
    public List<Template> selectTemplateList(Template template);

    /**
     * 新增模板
     * 
     * @param template 模板
     * @return 结果
     */
    public int insertTemplate(Template template);

    /**
     * 修改模板
     * 
     * @param template 模板
     * @return 结果
     */
    public int updateTemplate(Template template);

    /**
     * 批量删除模板
     * 
     * @param ids 需要删除的模板主键集合
     * @return 结果
     */
    public int deleteTemplateByIds(Long[] ids);

    /**
     * 删除模板信息
     * 
     * @param id 模板主键
     * @return 结果
     */
    public int deleteTemplateById(Long id);
}
