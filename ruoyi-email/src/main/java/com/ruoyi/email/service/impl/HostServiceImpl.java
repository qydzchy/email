package com.ruoyi.email.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.HostMapper;
import com.ruoyi.email.domain.Host;
import com.ruoyi.email.service.IHostService;

/**
 * 邮箱服务器查询Service业务层处理
 * 
 * @author tangJM
 * @date 2023-07-31
 */
@Service
public class HostServiceImpl implements IHostService 
{
    @Autowired
    private HostMapper hostMapper;

    /**
     * 查询邮箱服务器查询
     * 
     * @param id 邮箱服务器查询主键
     * @return 邮箱服务器查询
     */
    @Override
    public Host selectHostById(Long id)
    {
        return hostMapper.selectHostById(id);
    }

    /**
     * 查询邮箱服务器查询列表
     * 
     * @param host 邮箱服务器查询
     * @return 邮箱服务器查询
     */
    @Override
    public List<Host> selectHostList(Host host)
    {
        return hostMapper.selectHostList(host);
    }

    /**
     * 新增邮箱服务器查询
     * 
     * @param host 邮箱服务器查询
     * @return 结果
     */
    @Override
    public int insertHost(Host host)
    {
        return hostMapper.insertHost(host);
    }

    /**
     * 修改邮箱服务器查询
     * 
     * @param host 邮箱服务器查询
     * @return 结果
     */
    @Override
    public int updateHost(Host host)
    {
        return hostMapper.updateHost(host);
    }

    /**
     * 批量删除邮箱服务器查询
     * 
     * @param ids 需要删除的邮箱服务器查询主键
     * @return 结果
     */
    @Override
    public int deleteHostByIds(Long[] ids)
    {
        return hostMapper.deleteHostByIds(ids);
    }

    /**
     * 删除邮箱服务器查询信息
     * 
     * @param id 邮箱服务器查询主键
     * @return 结果
     */
    @Override
    public int deleteHostById(Long id)
    {
        return hostMapper.deleteHostById(id);
    }
}
