package com.ruoyi.email.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.email.domain.vo.TemplateListVO;
import com.ruoyi.email.service.ITemplateService;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.TemplateMapper;
import com.ruoyi.email.domain.Template;

import javax.annotation.Resource;

/**
 * 模板Service业务层处理
 * 
 * @author tangJM
 * @date 2023-07-31
 */
@Service
public class TemplateServiceImpl implements ITemplateService
{
    @Resource
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
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();
        template.setCreateId(userId);
        template.setCreateBy(username);
        template.setCreateTime(DateUtils.getNowDate());
        template.setUpdateId(userId);
        template.setUpdateBy(username);
        template.setUpdateTime(DateUtils.getNowDate());
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
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();
        template.setUpdateId(userId);
        template.setUpdateBy(username);
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

    /**
     * 模板列表
     * @return
     */
    @Override
    public List<TemplateListVO> list() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        return templateMapper.getByCreateId(userId);
    }

    @Override
    public boolean delete(Long id) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        templateMapper.deleteById(id, userId, DateUtils.getNowDate());
        return true;
    }
}
