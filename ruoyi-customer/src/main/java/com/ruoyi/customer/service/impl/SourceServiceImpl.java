package com.ruoyi.customer.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.customer.domain.vo.SourceListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.SourceMapper;
import com.ruoyi.customer.domain.Source;
import com.ruoyi.customer.service.ISourceService;

import javax.annotation.Resource;

/**
 * 客户来源Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-10-30
 */
@Service
public class SourceServiceImpl implements ISourceService 
{
    @Resource
    private SourceMapper sourceMapper;

    /**
     * 查询客户来源
     * 
     * @param id 客户来源主键
     * @return 客户来源
     */
    @Override
    public Source selectSourceById(Long id)
    {
        return sourceMapper.selectSourceById(id);
    }

    /**
     * 查询客户来源列表
     * 
     * @param source 客户来源
     * @return 客户来源
     */
    @Override
    public List<Source> selectSourceList(Source source)
    {
        return sourceMapper.selectSourceList(source);
    }

    /**
     * 新增客户来源
     * 
     * @param source 客户来源
     * @return 结果
     */
    @Override
    public int insertSource(Source source)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        source.setCreateId(userId);
        source.setCreateBy(username);
        source.setCreateTime(DateUtils.getNowDate());
        source.setUpdateId(userId);
        source.setUpdateBy(username);
        source.setUpdateTime(DateUtils.getNowDate());
        return sourceMapper.insertSource(source);
    }

    /**
     * 修改客户来源
     * 
     * @param source 客户来源
     * @return 结果
     */
    @Override
    public int updateSource(Source source)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        source.setUpdateId(userId);
        source.setUpdateBy(username);
        source.setUpdateTime(DateUtils.getNowDate());
        return sourceMapper.updateSource(source);
    }

    /**
     * 批量删除客户来源
     * 
     * @param ids 需要删除的客户来源主键
     * @return 结果
     */
    @Override
    public int deleteSourceByIds(Long[] ids)
    {
        return sourceMapper.deleteSourceByIds(ids);
    }

    /**
     * 删除客户来源信息
     * 
     * @param id 客户来源主键
     * @return 结果
     */
    @Override
    public int deleteSourceById(Long id)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        return sourceMapper.deleteSourceById(id, userId, username);
    }

    /**
     * 客户来源列表
     * @return
     */
    @Override
    public List<SourceListVO> list() {
        List<Source> sourceList = sourceMapper.selectSourceList(new Source());
        List<SourceListVO> sourceVOList = new ArrayList<>();
        for (Source source : sourceList) {
            SourceListVO sourceListVO = new SourceListVO();
            sourceListVO.setId(source.getId());
            sourceListVO.setName(source.getName());
            sourceVOList.add(sourceListVO);
        }
        return sourceVOList;
    }
}
