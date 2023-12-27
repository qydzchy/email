package com.ruoyi.email.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.email.domain.vo.TemplateTypeListVO;
import com.ruoyi.email.service.ITemplateTypeService;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.TemplateTypeMapper;
import com.ruoyi.email.domain.TemplateType;

import javax.annotation.Resource;

/**
 * 模板类型Service业务层处理
 * 
 * @author tangJM
 * @date 2023-07-31
 */
@Service
public class TemplateTypeServiceImpl implements ITemplateTypeService
{
    @Resource
    private TemplateTypeMapper templateTypeMapper;

    /**
     * 查询模板类型
     * 
     * @param id 模板类型主键
     * @return 模板类型
     */
    @Override
    public TemplateType selectTemplateTypeById(Long id)
    {
        return templateTypeMapper.selectTemplateTypeById(id);
    }

    /**
     * 查询模板类型列表
     * 
     * @param templateType 模板类型
     * @return 模板类型
     */
    @Override
    public List<TemplateType> selectTemplateTypeList(TemplateType templateType)
    {
        return templateTypeMapper.selectTemplateTypeList(templateType);
    }

    /**
     * 新增模板类型
     * 
     * @param templateType 模板类型
     * @return 结果
     */
    @Override
    public int insertTemplateType(TemplateType templateType)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();
        templateType.setCreateId(userId);
        templateType.setCreateBy(username);
        templateType.setCreateTime(DateUtils.getNowDate());
        templateType.setUpdateId(userId);
        templateType.setUpdateBy(username);
        templateType.setUpdateTime(DateUtils.getNowDate());
        return templateTypeMapper.insertTemplateType(templateType);
    }

    /**
     * 修改模板类型
     * 
     * @param templateType 模板类型
     * @return 结果
     */
    @Override
    public int updateTemplateType(TemplateType templateType)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();
        templateType.setUpdateId(userId);
        templateType.setUpdateBy(username);
        templateType.setUpdateTime(DateUtils.getNowDate());
        return templateTypeMapper.updateTemplateType(templateType);
    }

    /**
     * 批量删除模板类型
     * 
     * @param ids 需要删除的模板类型主键
     * @return 结果
     */
    @Override
    public int deleteTemplateTypeByIds(Long[] ids)
    {
        return templateTypeMapper.deleteTemplateTypeByIds(ids);
    }

    /**
     * 删除模板类型信息
     * 
     * @param id 模板类型主键
     * @return 结果
     */
    @Override
    public int deleteTemplateTypeById(Long id)
    {
        return templateTypeMapper.deleteTemplateTypeById(id);
    }

    /**
     * 模板类型列表
     * @return
     */
    @Override
    public List<TemplateTypeListVO> list() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        return templateTypeMapper.getByCreateId(userId);
    }

    @Override
    public boolean delete(Long id) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        templateTypeMapper.deleteById(id, userId, DateUtils.getNowDate());
        return true;
    }
}
