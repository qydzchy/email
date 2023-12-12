package com.ruoyi.customer.service.impl;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.customer.domain.bo.CustomerCountGroupByPublicleadsGroupsBO;
import com.ruoyi.customer.domain.bo.CustomerCountGroupByRatingBO;
import com.ruoyi.customer.domain.bo.EmailCountGroupByPublicleadsGroupBO;
import com.ruoyi.customer.domain.bo.EmailCountGroupByRatingBO;
import com.ruoyi.customer.domain.vo.CustomerPublicleadsGroupListVO;
import com.ruoyi.customer.domain.vo.EmailPublicleadsGroupsVOList;
import com.ruoyi.customer.domain.vo.EmailRatingListVO;
import com.ruoyi.customer.service.ICustomerService;
import com.ruoyi.customer.service.ICustomerEmailService;
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

    /**
     * 公海分组列表
     * @return
     */
    @Override
    public List<EmailPublicleadsGroupsVOList> publicleadsGroupsList() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        // 查询客户星级客户数量
        List<CustomerCountGroupByPublicleadsGroupsBO> customerCountGroupByPublicleadsGroupsList = customerService.selectCustomerCountGroupByPublicleadsGroups(userId);
        Map<Long, Integer> customerMap = customerCountGroupByPublicleadsGroupsList.stream().collect(Collectors.toMap(customerCountGroupByPublicleadsGroups -> customerCountGroupByPublicleadsGroups.getPublicleadsGroupsId(), customerCountGroupByPublicleadsGroups -> customerCountGroupByPublicleadsGroups.getCount()));
        // 客户邮件数量
        List<EmailCountGroupByPublicleadsGroupBO> emailCountGroupByPublicleadsGroupList = customerService.selectEmailCountGroupByPublicleadsGroups(userId);
        Map<Long, Integer> emailMap = emailCountGroupByPublicleadsGroupList.stream().collect(Collectors.toMap(emailCountGroupByPublicleadsGroup -> emailCountGroupByPublicleadsGroup.getPublicleadsGroupsId(), emailCountGroupByPublicleadsGroup -> emailCountGroupByPublicleadsGroup.getCount()));

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
        // 查询客户星级客户数量
        List<CustomerCountGroupByRatingBO> customerCountGroupByRatingList = customerService.selectCustomerCountGroupByRating(userId);
        Map<Integer, Integer> customerMap = customerCountGroupByRatingList.stream().collect(Collectors.toMap(customerCountGroupByRating -> customerCountGroupByRating.getRating(), customerCountGroupByRating -> customerCountGroupByRating.getCount()));
        // 客户邮件数量
        List<EmailCountGroupByRatingBO> emailCountGroupByRatingList = customerService.selectEmailCountGroupByRating(userId);
        Map<String, Integer> emailMap = emailCountGroupByRatingList.stream().collect(Collectors.toMap(emailCountGroupByRating -> emailCountGroupByRating.getRating(), emailCountGroupByRating -> emailCountGroupByRating.getCount()));

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
}
