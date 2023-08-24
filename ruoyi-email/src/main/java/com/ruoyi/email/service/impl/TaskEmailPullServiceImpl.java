package com.ruoyi.email.service.impl;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.email.domain.vo.email.PullEmailInfoListVO;
import com.ruoyi.email.service.ITaskEmailPullService;
import com.ruoyi.email.service.ITaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.TaskEmailPullMapper;
import com.ruoyi.email.domain.TaskEmailPull;

import javax.annotation.Resource;

/**
 * 拉取邮件Service业务层处理
 * 
 * @author tangJM
 * @date 2023-07-31
 */
@Slf4j
@Service
public class TaskEmailPullServiceImpl implements ITaskEmailPullService
{
    @Resource
    private TaskEmailPullMapper taskEmailPullMapper;
    @Resource
    private ITaskService taskService;

    /**
     * 查询拉取邮件
     * 
     * @param id 拉取邮件主键
     * @return 拉取邮件
     */
    @Override
    public TaskEmailPull selectTaskEmailPullById(Long id)
    {
        return taskEmailPullMapper.selectTaskEmailPullById(id);
    }

    /**
     * 查询拉取邮件列表
     * 
     * @param taskEmailPull 拉取邮件
     * @return 拉取邮件
     */
    @Override
    public List<TaskEmailPull> selectTaskEmailPullList(TaskEmailPull taskEmailPull)
    {
        return taskEmailPullMapper.selectTaskEmailPullList(taskEmailPull);
    }

    /**
     * 新增拉取邮件
     * 
     * @param taskEmailPull 拉取邮件
     * @return 结果
     */
    @Override
    public int insertTaskEmailPull(TaskEmailPull taskEmailPull)
    {
        taskEmailPull.setCreateTime(DateUtils.getNowDate());
        return taskEmailPullMapper.insertTaskEmailPull(taskEmailPull);
    }

    /**
     * 修改拉取邮件
     * 
     * @param taskEmailPull 拉取邮件
     * @return 结果
     */
    @Override
    public int updateTaskEmailPull(TaskEmailPull taskEmailPull)
    {
        taskEmailPull.setUpdateTime(DateUtils.getNowDate());
        return taskEmailPullMapper.updateTaskEmailPull(taskEmailPull);
    }

    /**
     * 批量删除拉取邮件
     * 
     * @param ids 需要删除的拉取邮件主键
     * @return 结果
     */
    @Override
    public int deleteTaskEmailPullByIds(Long[] ids)
    {
        return taskEmailPullMapper.deleteTaskEmailPullByIds(ids);
    }

    /**
     * 删除拉取邮件信息
     * 
     * @param id 拉取邮件主键
     * @return 结果
     */
    @Override
    public int deleteTaskEmailPullById(Long id)
    {
        return taskEmailPullMapper.deleteTaskEmailPullById(id);
    }

    /**
     * 获取邮箱拉取的邮件数量
     * @param ids
     * @return
     */
    @Override
    public Map<Long, Integer> getPullEmailQuantityByIds(List<Long> ids) {
        List<Map<String, Object>> pullEmailQuantityByIds = taskEmailPullMapper.getPullEmailQuantityByIds(ids);
        if (pullEmailQuantityByIds == null || pullEmailQuantityByIds.size() == 0) {
            return new HashMap<>();
        }

        return pullEmailQuantityByIds.stream().collect(Collectors.toMap(
                map -> Long.valueOf(map.get("taskId").toString()),
                map -> Integer.valueOf(map.get("quantity").toString()))
        );
    }

    @Override
    public Pair<Integer, List<Map<String, List<PullEmailInfoListVO>>>> listPullHeader(Long taskId, Integer pageNum, Integer pageSize) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();

        boolean exist = taskService.existById(taskId, userId);
        if (!exist) {
            log.info("任务不存在，taskId:{}", taskId);
            throw new ServiceException("任务不存在");
        }

        int count = countTaskEmailPullTask(taskId);
        if (count <= 0) {
            return Pair.of(0, new ArrayList<>());
        }

        int offset = (pageNum - 1) * pageSize;
        int limit = pageSize;
        List<PullEmailInfoListVO> pullEmailInfoListVOList = taskEmailPullMapper.selectTaskEmailPullByTaskIdPage(taskId, offset, limit);

        Map<String, List<PullEmailInfoListVO>> data = new LinkedHashMap<>();
        pullEmailInfoListVOList.stream().forEach(pullEmailInfoListVO -> {
            Date sendDate = pullEmailInfoListVO.getSendDate();
            LocalDateTime localDateTime = LocalDateTime.ofInstant(sendDate.toInstant(), ZoneId.systemDefault());
            String dynamicLabel = getDynamicLabel(localDateTime);

            if (data.containsKey(dynamicLabel)) {
                data.get(dynamicLabel).add(pullEmailInfoListVO);
            } else {
                data.put(dynamicLabel, new ArrayList<PullEmailInfoListVO>() {{
                    add(pullEmailInfoListVO);
                }});
            }
        });

        List<Map<String, List<PullEmailInfoListVO>>> dataList = new ArrayList<>();
        data.forEach((key, value) -> {
            dataList.add(new HashMap<String, List<PullEmailInfoListVO>>() {{
                put(key, value);
            }});
        });

        return Pair.of(count, dataList);
    }

    /**
     * 获取任务下拉取的邮件数量
     * @param taskId
     * @return
     */
    private int countTaskEmailPullTask(Long taskId) {
        return taskEmailPullMapper.countByTaskId(taskId);
    }

    private String getDynamicLabel(LocalDateTime mailDateTime) {
        LocalDateTime now = LocalDateTime.now();

        long daysBetween = ChronoUnit.DAYS.between(mailDateTime, now);
        if (daysBetween == 0) {
            return "今天";
        } else if (daysBetween == 1) {
            return "昨天";
        } else if (daysBetween > 1 && daysBetween <= 7) {
            DayOfWeek dayOfWeek = mailDateTime.getDayOfWeek();
            return "上周" + dayOfWeek.getDisplayName(java.time.format.TextStyle.FULL, java.util.Locale.CHINESE);
        } else if (mailDateTime.getYear() == now.getYear()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月");
            return mailDateTime.format(formatter);
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月");
            return mailDateTime.format(formatter);
        }
    }


}
