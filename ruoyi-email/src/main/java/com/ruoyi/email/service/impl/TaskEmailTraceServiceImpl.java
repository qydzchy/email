package com.ruoyi.email.service.impl;

import java.util.Date;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.TaskEmailTraceMapper;
import com.ruoyi.email.domain.TaskEmailTrace;
import com.ruoyi.email.service.ITaskEmailTraceService;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * 邮件追踪Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-09-17
 */
@Service
public class TaskEmailTraceServiceImpl implements ITaskEmailTraceService 
{
    @Resource
    private TaskEmailTraceMapper taskEmailTraceMapper;
    @Resource
    private RestTemplate restTemplate;

    @Value("${email.trace.latitude-server}")
    private String traceLatitudeServer;

    /**
     * 查询邮件追踪
     * 
     * @param id 邮件追踪主键
     * @return 邮件追踪
     */
    @Override
    public TaskEmailTrace selectTaskEmailTraceById(Long id)
    {
        return taskEmailTraceMapper.selectTaskEmailTraceById(id);
    }

    /**
     * 查询邮件追踪列表
     * 
     * @param taskEmailTrace 邮件追踪
     * @return 邮件追踪
     */
    @Override
    public List<TaskEmailTrace> selectTaskEmailTraceList(TaskEmailTrace taskEmailTrace)
    {
        return taskEmailTraceMapper.selectTaskEmailTraceList(taskEmailTrace);
    }

    /**
     * 新增邮件追踪
     * 
     * @param taskEmailTrace 邮件追踪
     * @return 结果
     */
    @Override
    public int insertTaskEmailTrace(TaskEmailTrace taskEmailTrace)
    {
        taskEmailTrace.setCreateTime(DateUtils.getNowDate());
        return taskEmailTraceMapper.insertTaskEmailTrace(taskEmailTrace);
    }

    /**
     * 修改邮件追踪
     * 
     * @param taskEmailTrace 邮件追踪
     * @return 结果
     */
    @Override
    public int updateTaskEmailTrace(TaskEmailTrace taskEmailTrace)
    {
        taskEmailTrace.setUpdateTime(DateUtils.getNowDate());
        return taskEmailTraceMapper.updateTaskEmailTrace(taskEmailTrace);
    }

    /**
     * 批量删除邮件追踪
     * 
     * @param ids 需要删除的邮件追踪主键
     * @return 结果
     */
    @Override
    public int deleteTaskEmailTraceByIds(Long[] ids)
    {
        return taskEmailTraceMapper.deleteTaskEmailTraceByIds(ids);
    }

    /**
     * 删除邮件追踪信息
     * 
     * @param id 邮件追踪主键
     * @return 结果
     */
    @Override
    public int deleteTaskEmailTraceById(Long id)
    {
        return taskEmailTraceMapper.deleteTaskEmailTraceById(id);
    }

    @Override
    public void getLocationToSave(Long id, String ipAddr) {
        String result = restTemplate.getForObject(traceLatitudeServer + ipAddr, String.class);
        String[] parts = result.split("\\|");
        String location = null;
        if (parts.length > 8) {
            String country = parts[5];   // 国家
            String region = parts[6];    // 省份
            String city = parts[7];      // 城市
            String district = parts[8]; // 区县

            location = country + " " + region + " " + city + " " + district;
        }

        TaskEmailTrace taskEmailTrace = new TaskEmailTrace();
        taskEmailTrace.setEmailId(id);
        taskEmailTrace.setBeijingTime(new Date());
        taskEmailTrace.setLocalTime(new Date());
        taskEmailTrace.setIp(ipAddr);
        taskEmailTrace.setLocation(location);
        taskEmailTraceMapper.insertTaskEmailTrace(taskEmailTrace);
    }
}
