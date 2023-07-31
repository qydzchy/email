package com.ruoyi.email.service;

import java.util.List;
import com.ruoyi.email.domain.Host;

/**
 * 邮箱服务器查询Service接口
 * 
 * @author tangJM
 * @date 2023-07-31
 */
public interface IHostService 
{
    /**
     * 查询邮箱服务器查询
     * 
     * @param id 邮箱服务器查询主键
     * @return 邮箱服务器查询
     */
    public Host selectHostById(Long id);

    /**
     * 查询邮箱服务器查询列表
     * 
     * @param host 邮箱服务器查询
     * @return 邮箱服务器查询集合
     */
    public List<Host> selectHostList(Host host);

    /**
     * 新增邮箱服务器查询
     * 
     * @param host 邮箱服务器查询
     * @return 结果
     */
    public int insertHost(Host host);

    /**
     * 修改邮箱服务器查询
     * 
     * @param host 邮箱服务器查询
     * @return 结果
     */
    public int updateHost(Host host);

    /**
     * 批量删除邮箱服务器查询
     * 
     * @param ids 需要删除的邮箱服务器查询主键集合
     * @return 结果
     */
    public int deleteHostByIds(Long[] ids);

    /**
     * 删除邮箱服务器查询信息
     * 
     * @param id 邮箱服务器查询主键
     * @return 结果
     */
    public int deleteHostById(Long id);
}
