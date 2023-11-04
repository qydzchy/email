package com.ruoyi.customer.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.customer.domain.CustomerScheduleParticipants;
import com.ruoyi.customer.domain.dto.CustomerScheduleAddOrUpdateDTO;
import com.ruoyi.customer.domain.vo.CustomerScheduleListVO;
import com.ruoyi.customer.mapper.CustomerScheduleParticipantsMapper;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.CustomerScheduleMapper;
import com.ruoyi.customer.domain.CustomerSchedule;
import com.ruoyi.customer.service.ICustomerScheduleService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 客户日程Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-11-03
 */
@Service
public class CustomerScheduleServiceImpl implements ICustomerScheduleService 
{
    @Resource
    private CustomerScheduleMapper customerScheduleMapper;
    @Resource
    private CustomerScheduleParticipantsMapper customerScheduleParticipantsMapper;

    /**
     * 查询客户日程
     * 
     * @param id 客户日程主键
     * @return 客户日程
     */
    @Override
    public CustomerSchedule selectCustomerScheduleById(Long id)
    {
        return customerScheduleMapper.selectCustomerScheduleById(id);
    }

    /**
     * 查询客户日程列表
     * 
     * @param customerSchedule 客户日程
     * @return 客户日程
     */
    @Override
    public List<CustomerSchedule> selectCustomerScheduleList(CustomerSchedule customerSchedule)
    {
        return customerScheduleMapper.selectCustomerScheduleList(customerSchedule);
    }

    /**
     * 新增客户日程
     * 
     * @param customerScheduleAddOrUpdateDTO 客户日程
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insertCustomerSchedule(CustomerScheduleAddOrUpdateDTO customerScheduleAddOrUpdateDTO)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        CustomerSchedule customerSchedule = new CustomerSchedule();
        BeanUtils.copyProperties(customerScheduleAddOrUpdateDTO, customerSchedule);

        customerSchedule.setCreateId(userId);
        customerSchedule.setCreateBy(username);
        customerSchedule.setCreateTime(DateUtils.getNowDate());
        customerSchedule.setUpdateId(userId);
        customerSchedule.setUpdateBy(username);
        customerSchedule.setUpdateTime(DateUtils.getNowDate());
        long id = customerScheduleMapper.insertCustomerSchedule(customerSchedule);

        // 新增参与人
        List<Long> userIds = customerScheduleAddOrUpdateDTO.getUserIds();
        List<CustomerScheduleParticipants> customerScheduleParticipantsList = new ArrayList<>();
        for (Long userIdParam : userIds) {
            CustomerScheduleParticipants customerScheduleParticipants = new CustomerScheduleParticipants();
            customerScheduleParticipants.setScheduleId(id);
            customerScheduleParticipants.setUserId(userIdParam);
            customerScheduleParticipants.setCreateId(userIdParam);
            customerScheduleParticipants.setCreateBy(username);
            customerScheduleParticipants.setCreateTime(DateUtils.getNowDate());
            customerScheduleParticipants.setUpdateId(userId);
            customerScheduleParticipants.setUpdateBy(username);
            customerScheduleParticipants.setUpdateTime(DateUtils.getNowDate());
            customerScheduleParticipantsList.add(customerScheduleParticipants);
        }

        customerScheduleParticipantsMapper.batchInsertCustomerScheduleParticipants(customerScheduleParticipantsList);
        return true;
    }

    /**
     * 修改客户日程
     * 
     * @param customerScheduleAddOrUpdateDTO 客户日程
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateCustomerSchedule(CustomerScheduleAddOrUpdateDTO customerScheduleAddOrUpdateDTO)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        CustomerSchedule customerSchedule = new CustomerSchedule();
        BeanUtils.copyProperties(customerScheduleAddOrUpdateDTO, customerSchedule);

        customerSchedule.setUpdateId(userId);
        customerSchedule.setUpdateBy(username);
        customerSchedule.setUpdateTime(DateUtils.getNowDate());
        customerScheduleMapper.updateCustomerSchedule(customerSchedule);

        // 删除参与人
        customerScheduleParticipantsMapper.deleteCustomerScheduleParticipantsByScheduleId(customerSchedule.getId(), userId, username);

        // 批量新增参与人
        List<Long> userIds = customerScheduleAddOrUpdateDTO.getUserIds();
        List<CustomerScheduleParticipants> customerScheduleParticipantsList = new ArrayList<>();
        for (Long userIdParam : userIds) {
            CustomerScheduleParticipants customerScheduleParticipants = new CustomerScheduleParticipants();
            customerScheduleParticipants.setScheduleId(customerSchedule.getId());
            customerScheduleParticipants.setUserId(userIdParam);
            customerScheduleParticipants.setCreateId(userIdParam);
            customerScheduleParticipants.setCreateBy(username);
            customerScheduleParticipants.setCreateTime(DateUtils.getNowDate());
            customerScheduleParticipants.setUpdateId(userId);
            customerScheduleParticipants.setUpdateBy(username);
            customerScheduleParticipants.setUpdateTime(DateUtils.getNowDate());
            customerScheduleParticipantsList.add(customerScheduleParticipants);
        }

        customerScheduleParticipantsMapper.batchInsertCustomerScheduleParticipants(customerScheduleParticipantsList);
        return true;
    }

    /**
     * 批量删除客户日程
     * 
     * @param ids 需要删除的客户日程主键
     * @return 结果
     */
    @Override
    public int deleteCustomerScheduleByIds(Long[] ids)
    {
        return customerScheduleMapper.deleteCustomerScheduleByIds(ids);
    }

    /**
     * 删除客户日程信息
     * 
     * @param id 客户日程主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteCustomerScheduleById(Long id)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        customerScheduleMapper.deleteCustomerScheduleById(id, userId, username);

        // 删除参与人
        customerScheduleParticipantsMapper.deleteCustomerScheduleParticipantsByScheduleId(id, userId, username);
        return true;
    }

    /**
     * 客户日程列表
     * @param startTime
     * @param endTime
     * @param userIds
     * @return
     */
    @Override
    public Pair<Integer, List<CustomerScheduleListVO>> list(String startTime, String endTime, List<Long> userIds, Integer pageNum, Integer pageSize) {
        Integer count = customerScheduleMapper.count(startTime, endTime, userIds);
        if (count == 0) {
            return Pair.of(0, new ArrayList<>());
        }

        List<CustomerScheduleListVO> customerScheduleListVOList = customerScheduleMapper.list(startTime, endTime, userIds, pageNum, pageSize);
        return Pair.of(count, customerScheduleListVOList);
    }

    @Override
    public boolean updateCustomerScheduleByFocusFlag(Long id) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        return customerScheduleMapper.updateFocusFlag(id, userId, username);
    }
}
