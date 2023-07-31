package com.ruoyi.email.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.email.service.ITemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.TemplateMapper;
import com.ruoyi.email.domain.Template;

/**
 * 模板Service业务层处理
 * 
 * @author tangJM
 * @date 2023-07-31
 */
@Service
public class TemplateServiceImpl implements ITemplateService
{
    @Autowired
    private TemplateMapper templateMapper;

    /**
     * 查询模板
     * 
     * @param id 模板主键
     * @return 模板
     */
    @Override
    public Template selectTemplateById(Long id)
    {
        return templateMapper.selectTemplateById(id);
    }

    /**
     * 查询模板列表
     * 
     * @param template 模板
     * @return 模板
     */
    @Override
    public List<Template> selectTemplateList(Template template)
    {
        return templateMapper.selectTemplateList(template);
    }

    /**
     * 新增模板
     * 
     * @param template 模板
     * @return 结果
     */
    @Override
    public int insertTemplate(Template template)
    {
        template.setCreateTime(DateUtils.getNowDate());
        return templateMapper.insertTemplate(template);
    }

    /**
     * 修改模板
     * 
     * @param template 模板
     * @return 结果
     */
    @Override
    public int updateTemplate(Template template)
    {
        template.setUpdateTime(DateUtils.getNowDate());
        return templateMapper.updateTemplate(template);
    }

    /**
     * 批量删除模板
     * 
     * @param ids 需要删除的模板主键
     * @return 结果
     */
    @Override
    public int deleteTemplateByIds(Long[] ids)
    {
        return templateMapper.deleteTemplateByIds(ids);
    }

    /**
     * 删除模板信息
     * 
     * @param id 模板主键
     * @return 结果
     */
    @Override
    public int deleteTemplateById(Long id)
    {
        return templateMapper.deleteTemplateById(id);
    }
}
