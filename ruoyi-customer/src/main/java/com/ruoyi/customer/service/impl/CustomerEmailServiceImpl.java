package com.ruoyi.customer.service.impl;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.customer.domain.Source;
import com.ruoyi.customer.domain.bo.*;
import com.ruoyi.customer.domain.vo.*;
import com.ruoyi.customer.mapper.SourceMapper;
import com.ruoyi.customer.service.ICustomerService;
import com.ruoyi.customer.service.ICustomerEmailService;
import com.ruoyi.customer.service.IPacketService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CustomerEmailServiceImpl implements ICustomerEmailService {

    @Resource
    private ICustomerService customerService;
    @Resource
    private IPacketService packetService;
    @Resource
    private SourceMapper sourceMapper;

    /**
     * 公海分组列表
     * @return
     */
    @Override
    public List<EmailPublicleadsGroupsVOList> publicleadsGroupsList() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        // 查询客户星级客户数量
        List<CustomerCountGroupByPublicleadsGroupsBO> customerCountGroupByList = customerService.selectCustomerCountGroupByPublicleadsGroups(userId);
        Map<Long, Integer> customerMap = customerCountGroupByList.stream().collect(Collectors.toMap(customerCountGroupBy -> customerCountGroupBy.getPublicleadsGroupsId(), customerCountGroupBy -> customerCountGroupBy.getCount()));
        // 客户邮件数量
        List<EmailCountGroupByPublicleadsGroupBO> emailCountGroupByList = customerService.selectEmailCountGroupByPublicleadsGroups(userId);
        Map<Long, Integer> emailMap = emailCountGroupByList.stream().collect(Collectors.toMap(emailCountGroupBy -> emailCountGroupBy.getPublicleadsGroupsId(), emailCountGroupBy -> emailCountGroupBy.getCount()));

        // 查询公海分组列表
        List<CustomerPublicleadsGroupListVO> customerPublicleadsGroupVOList = customerService.publicleadsGroupsList();

        List<EmailPublicleadsGroupsVOList> emailPublicleadsGroupsVOList = new ArrayList<>();
        for (CustomerPublicleadsGroupListVO customerPublicleadsGroupVO : customerPublicleadsGroupVOList) {
            EmailPublicleadsGroupsVOList emailPublicleadsGroupsVO = new EmailPublicleadsGroupsVOList();
            emailPublicleadsGroupsVO.setId(customerPublicleadsGroupVO.getId());
            emailPublicleadsGroupsVO.setName(customerPublicleadsGroupVO.getName());
            emailPublicleadsGroupsVO.setCustomerCount(customerMap.get(customerPublicleadsGroupVO.getId()));
            emailPublicleadsGroupsVO.setEmailCount(emailMap.get(customerPublicleadsGroupVO.getId()));
            emailPublicleadsGroupsVOList.add(emailPublicleadsGroupsVO);
        }

        return emailPublicleadsGroupsVOList;
    }

    /**
     * 客户分组列表
     * @return
     */
    @Override
    public List<EmailPacketVOList> packetList() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        // 客户数量
        List<CustomerCountGroupByPacketBO> customerCountGroupByList = customerService.selectCustomerCountGroupByPacket(userId);
        Map<Long, Integer> customerMap = customerCountGroupByList.stream().collect(Collectors.toMap(customerCountGroupBy -> customerCountGroupBy.getPacketId(), customerCountGroupBy -> customerCountGroupBy.getCount()));
        // 客户邮件数量
        List<EmailCountGroupByPublicleadsGroupBO> emailCountGroupByList = customerService.selectEmailCountGroupByPacket(userId);
        Map<Long, Integer> emailMap = emailCountGroupByList.stream().collect(Collectors.toMap(emailCountGroupBy -> emailCountGroupBy.getPublicleadsGroupsId(), emailCountGroupBy -> emailCountGroupBy.getCount()));

        List<PacketListVO> packetVOList = packetService.packetList();
        if (packetVOList != null && packetVOList.size() > 0) {
            List<EmailPacketVOList> emailPacketVOList = new ArrayList<>();
            for (PacketListVO packetVO : packetVOList) {
                EmailPacketVOList emailPacketVO = new EmailPacketVOList();
                emailPacketVO.setId(packetVO.getId());
                emailPacketVO.setName(packetVO.getName());
                emailPacketVO.setCustomerCount(customerMap.get(packetVO.getId()));
                emailPacketVO.setEmailCount(emailMap.get(packetVO.getId()));
                emailPacketVOList.add(emailPacketVO);
            }
            return emailPacketVOList;
        }

        return null;
    }

    /**
     * 星级列表
     * @return
     */
    @Override
    public List<EmailRatingListVO> ratingList() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        // 客户数量
        List<CustomerCountGroupByRatingBO> customerCountGroupByList = customerService.selectCustomerCountGroupByRating(userId);
        Map<Integer, Integer> customerMap = customerCountGroupByList.stream().collect(Collectors.toMap(customerCountGroupBy -> customerCountGroupBy.getRating(), customerCountGroupBy -> customerCountGroupBy.getCount()));
        // 客户邮件数量
        List<EmailCountGroupByRatingBO> emailCountGroupByList = customerService.selectEmailCountGroupByRating(userId);
        Map<String, Integer> emailMap = emailCountGroupByList.stream().collect(Collectors.toMap(emailCountGroupBy -> emailCountGroupBy.getRating(), emailCountGroupBy -> emailCountGroupBy.getCount()));

        List<EmailRatingListVO> emailRatingVOList = new ArrayList<>();
        for (int i = 5; i > 0; i--) {
            EmailRatingListVO ratingListVO = new EmailRatingListVO();
            ratingListVO.setRating(i);
            ratingListVO.setCustomerCount(customerMap.containsKey(i) ? customerMap.get(i) : 0);
            ratingListVO.setEmailCount(emailMap.containsKey(i) ? emailMap.get(i) : 0);
            emailRatingVOList.add(ratingListVO);
        }

        return emailRatingVOList;
    }

    /**
     * 客户来源列表
     * @return
     */
    @Override
    public List<EmailSourceVOList> sourceList() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        // 客户数量
        List<CustomerCountGroupBySourceBO> customerCountGroupByList = customerService.selectCustomerCountGroupBySource(userId);
        Map<Long, Integer> customerMap = customerCountGroupByList.stream().collect(Collectors.toMap(customerCountGroupBy -> customerCountGroupBy.getSourceId(), customerCountGroupBy -> customerCountGroupBy.getCount()));
        // 客户邮件数量
        List<EmailCountGroupBySourceBO> emailCountGroupByList = customerService.selectEmailCountGroupBySource(userId);
        Map<Long, Integer> emailMap = emailCountGroupByList.stream().collect(Collectors.toMap(emailCountGroupBy -> emailCountGroupBy.getSourceId(), emailCountGroupBy -> emailCountGroupBy.getCount()));

        List<EmailSourceVOList> emailSourceVOList = new ArrayList<>();
        // 客户来源列表
        List<Source> sourceList = sourceMapper.selectSourceList(new Source());
        for (Source source : sourceList) {
            EmailSourceVOList emailSourceVO = new EmailSourceVOList();
            emailSourceVO.setId(source.getId());
            emailSourceVO.setName(source.getName());
            emailSourceVO.setCustomerCount(customerMap.get(source.getId()));
            emailSourceVO.setEmailCount(emailMap.get(source.getId()));
            emailSourceVOList.add(emailSourceVO);
        }

        return emailSourceVOList;
    }
}
