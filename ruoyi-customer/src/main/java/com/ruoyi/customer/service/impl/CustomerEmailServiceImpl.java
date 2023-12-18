package com.ruoyi.customer.service.impl;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.customer.CustomerActivityEnum;
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
import java.time.LocalDate;
import java.util.*;
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
    public List<EmailPublicleadsGroupsListVO> publicleadsGroupsList() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        // 客户邮件数量
        List<EmailCountGroupByPublicleadsGroupsBO> emailCountGroupByList = customerService.selectEmailCountGroupByPublicleadsGroups(userId);

        // 查询公海分组列表
        List<CustomerPublicleadsGroupListVO> customerPublicleadsGroupVOList = customerService.publicleadsGroupsList();

        List<EmailPublicleadsGroupsListVO> emailPublicleadsGroupsVOList = new ArrayList<>();
        for (CustomerPublicleadsGroupListVO customerPublicleadsGroupVO : customerPublicleadsGroupVOList) {
            Long id = customerPublicleadsGroupVO.getId();
            String name = customerPublicleadsGroupVO.getName();
            int customerCount = 0;
            int emailCount = 0;

            List<EmailCustomerVO> customerList = new ArrayList<>();
            Iterator<EmailCountGroupByPublicleadsGroupsBO> iterator = emailCountGroupByList.iterator();
            while (iterator.hasNext()) {
                EmailCountGroupByPublicleadsGroupsBO emailCountGroupBy = iterator.next();
                Long publicleadsGroupsId = emailCountGroupBy.getPublicleadsGroupsId();
                if (id.longValue() == publicleadsGroupsId.longValue()) {
                    customerCount ++;
                    emailCount += emailCountGroupBy.getCount();

                    EmailCustomerVO emailCustomerVO = new EmailCustomerVO();
                    emailCustomerVO.setId(emailCountGroupBy.getCustomerId());
                    emailCustomerVO.setName(emailCountGroupBy.getCompanyName());
                    emailCustomerVO.setCustomerCount(1);
                    emailCustomerVO.setUnReadEmailCount(emailCountGroupBy.getCount());
                    customerList.add(emailCustomerVO);
                    iterator.remove();
                }
            }

            EmailPublicleadsGroupsListVO emailPublicleadsGroupsVO = new EmailPublicleadsGroupsListVO();
            emailPublicleadsGroupsVO.setId(id);
            emailPublicleadsGroupsVO.setName(name);
            emailPublicleadsGroupsVO.setUnReadEmailCount(emailCount);
            emailPublicleadsGroupsVO.setCustomerCount(customerCount);
            emailPublicleadsGroupsVO.setCustomerList(customerList);
            emailPublicleadsGroupsVOList.add(emailPublicleadsGroupsVO);
        }

        return emailPublicleadsGroupsVOList;
    }

    /**
     * 客户分组列表
     * @return
     */
    @Override
    public List<EmailPacketListVO> packetList() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        // 客户邮件数量
        List<EmailCountGroupByPacketBO> emailCountGroupByList = customerService.selectEmailCountGroupByPacket(userId);

        List<EmailPacketListVO> emailPacketVOList = new ArrayList<>();
        List<PacketListVO> packetVOList = packetService.packetList();
        for (PacketListVO packetVO : packetVOList) {
            Long id = packetVO.getId();
            String name = packetVO.getName();
            int customerCount = 0;
            int emailCount = 0;

            List<EmailCustomerVO> customerList = new ArrayList<>();
            Iterator<EmailCountGroupByPacketBO> iterator = emailCountGroupByList.iterator();
            while (iterator.hasNext()) {
                EmailCountGroupByPacketBO emailCountGroupBy = iterator.next();
                Long packetId = emailCountGroupBy.getPacketId();
                if (id.longValue() == packetId.longValue()) {
                    customerCount ++;
                    emailCount += emailCountGroupBy.getCount();

                    EmailCustomerVO emailCustomerVO = new EmailCustomerVO();
                    emailCustomerVO.setId(emailCountGroupBy.getCustomerId());
                    emailCustomerVO.setName(emailCountGroupBy.getCompanyName());
                    emailCustomerVO.setCustomerCount(1);
                    emailCustomerVO.setUnReadEmailCount(emailCountGroupBy.getCount());
                    customerList.add(emailCustomerVO);
                    iterator.remove();
                }
            }

            EmailPacketListVO emailPacketListVO = new EmailPacketListVO();
            emailPacketListVO.setId(id);
            emailPacketListVO.setName(name);
            emailPacketListVO.setUnReadEmailCount(emailCount);
            emailPacketListVO.setCustomerCount(customerCount);
            emailPacketListVO.setCustomerList(customerList);
            emailPacketVOList.add(emailPacketListVO);
        }

        return emailPacketVOList;
    }

    /**
     * 星级列表
     * @return
     */
    @Override
    public List<EmailRatingListVO> ratingList() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        // 客户邮件数量
        List<EmailCountGroupByRatingBO> emailCountGroupByList = customerService.selectEmailCountGroupByRating(userId);

        List<EmailRatingListVO> emailRatingVOList = new ArrayList<>();
        for (int i = 5; i > 0; i--) {
            Long id = Long.valueOf(i);
            String name = String.valueOf(i);
            int customerCount = 0;
            int emailCount = 0;

            List<EmailCustomerVO> customerList = new ArrayList<>();
            Iterator<EmailCountGroupByRatingBO> iterator = emailCountGroupByList.iterator();
            while (iterator.hasNext()) {
                EmailCountGroupByRatingBO emailCountGroupBy = iterator.next();
                Long rating = emailCountGroupBy.getRating() != null ? Long.valueOf(emailCountGroupBy.getRating()) : 0L;
                if (id.longValue() == rating.longValue()) {
                    customerCount ++;
                    emailCount += emailCountGroupBy.getCount();

                    EmailCustomerVO emailCustomerVO = new EmailCustomerVO();
                    emailCustomerVO.setId(emailCountGroupBy.getCustomerId());
                    emailCustomerVO.setName(emailCountGroupBy.getCompanyName());
                    emailCustomerVO.setCustomerCount(1);
                    emailCustomerVO.setUnReadEmailCount(emailCountGroupBy.getCount());
                    customerList.add(emailCustomerVO);
                }
            }

            EmailRatingListVO emailRatingListVO = new EmailRatingListVO();
            emailRatingListVO.setId(id);
            emailRatingListVO.setName(name);
            emailRatingListVO.setUnReadEmailCount(emailCount);
            emailRatingListVO.setCustomerCount(customerCount);
            emailRatingListVO.setCustomerList(customerList);
            emailRatingVOList.add(emailRatingListVO);
        }

        return emailRatingVOList;
    }

    /**
     * 客户来源列表
     * @return
     */
    @Override
    public List<EmailSourceListVO> sourceList() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();

        // 客户邮件数量
        List<EmailCountGroupBySourceBO> emailCountGroupByList = customerService.selectEmailCountGroupBySource(userId);

        // 客户来源列表
        List<Source> sourceList = sourceMapper.selectSourceList(new Source());
        List<EmailSourceListVO> emailSourceListVO = new ArrayList<>();
        for (Source source : sourceList) {
            Long id = source.getId();
            String name = source.getName();
            int customerCount = 0;
            int emailCount = 0;

            List<EmailCustomerVO> customerList = new ArrayList<>();
            Iterator<EmailCountGroupBySourceBO> iterator = emailCountGroupByList.iterator();
            while (iterator.hasNext()) {
                EmailCountGroupBySourceBO emailCountGroupBy = iterator.next();
                Long sourceId = emailCountGroupBy.getSourceId();
                if (id.longValue() == sourceId.longValue()) {
                    customerCount ++;
                    emailCount += emailCountGroupBy.getCount();

                    EmailCustomerVO emailCustomerVO = new EmailCustomerVO();
                    emailCustomerVO.setId(emailCountGroupBy.getCustomerId());
                    emailCustomerVO.setName(emailCountGroupBy.getCompanyName());
                    emailCustomerVO.setCustomerCount(1);
                    emailCustomerVO.setUnReadEmailCount(emailCountGroupBy.getCount());
                    customerList.add(emailCustomerVO);
                }
            }

            EmailSourceListVO emailSourceVO = new EmailSourceListVO();
            emailSourceVO.setId(id);
            emailSourceVO.setName(name);
            emailSourceVO.setUnReadEmailCount(emailCount);
            emailSourceVO.setCustomerCount(customerCount);
            emailSourceVO.setCustomerList(customerList);
            emailSourceListVO.add(emailSourceVO);
        }

        return emailSourceListVO;
    }

    /**
     * 客户活跃度列表
     * @return
     */
    @Override
    public List<EmailActivityListVO> activityList() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        // 客户邮件数量
        List<EmailCountGroupByCustomerBO> emailCountGroupByList = customerService.selectEmailCountGroupByCustomer(userId);
        // 假设现在是当前日期，你可以根据实际情况设置这个日期
        LocalDate currentDate = LocalDate.now();

        for (EmailCountGroupByCustomerBO emailCountGroupBy : emailCountGroupByList) {
            Date lastContactedAt = emailCountGroupBy.getLastContactedAt();
            // 将 Date 转换为 LocalDate
            LocalDate convertedContactDate = lastContactedAt.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();

            // 计算日期差异
            long daysDifference = java.time.temporal.ChronoUnit.DAYS.between(convertedContactDate, currentDate);

            // 判断归属类别
            CustomerActivityEnum customerActivityEnum;
            if (daysDifference <= 3) {
                customerActivityEnum = CustomerActivityEnum.WITHIN_3_DAYS;
            } else if (daysDifference <= 7) {
                customerActivityEnum = CustomerActivityEnum.BETWEEN_3_AND_7_DAYS;
            } else if (daysDifference <= 30) {
                customerActivityEnum = CustomerActivityEnum.BETWEEN_7_AND_30_DAYS;
            } else if (daysDifference <= 90) {
                customerActivityEnum = CustomerActivityEnum.BETWEEN_30_AND_90_DAYS;
            } else {
                customerActivityEnum = CustomerActivityEnum.OVER_90_DAYS;
            }

            emailCountGroupBy.setActivityType(customerActivityEnum.getType());
            emailCountGroupBy.setActivityName(customerActivityEnum.getName());
        }

        Map<Long, List<EmailCountGroupByCustomerBO>> customerIdMap = emailCountGroupByList.stream().collect(Collectors.groupingBy(emailCountGroupBy -> emailCountGroupBy.getCustomerId()));

        List<EmailActivityListVO> emailActivityVOList = new ArrayList<>();
        customerIdMap.forEach((customerId, list) -> {
            EmailCountGroupByCustomerBO firstEmailCountGroupByCustomerBO = list.get(0);
            Integer activityType = firstEmailCountGroupByCustomerBO.getActivityType();
            String activityName = firstEmailCountGroupByCustomerBO.getActivityName();

            EmailActivityListVO emailActivityVO = new EmailActivityListVO();
            emailActivityVO.setId(activityType.longValue());
            emailActivityVO.setName(activityName);
            int customerCount = 0;
            int emailCount = 0;
            List<EmailActivityListVO> customerList = new ArrayList<>();
            for (EmailCountGroupByCustomerBO emailCountGroupByCustomerBO : list) {
                EmailActivityListVO customerVO = new EmailActivityListVO();
                customerVO.setId(emailCountGroupByCustomerBO.getCustomerId());
                customerVO.setName(emailCountGroupByCustomerBO.getCompanyName());
                customerVO.setCustomerCount(1);
                customerVO.setUnReadEmailCount(emailCountGroupByCustomerBO.getCount());
                customerCount++;
                emailCount += emailCountGroupByCustomerBO.getCount();
            }

            emailActivityVO.setCustomerCount(customerCount);
            emailActivityVO.setUnReadEmailCount(emailCount);
            emailActivityVO.setCustomerList(customerList);
            emailActivityVOList.add(emailActivityVO);
        });

        return emailActivityVOList;
    }

    /**
     * 通用列表
     * @return
     */
    @Override
    public List<EmailGeneralListVO> generalList() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();

        // 最近7天有往来
        List<EmailCountByRecentInteractions7DaysBO> emailCountByRecentInteractions7DaysBOList = customerService.selectRecentInteractions7Days(userId);
        int recentInteractions7DaysEmailCount = 0;
        int recentInteractions7DaysCustomerCount = 0;
        List<EmailCustomerVO> recentInteractions7DaysCustomerList = new ArrayList<>();
        for (EmailCountByRecentInteractions7DaysBO emailCountByRecentInteractions7DaysBO : emailCountByRecentInteractions7DaysBOList) {
            recentInteractions7DaysCustomerCount ++;
            recentInteractions7DaysEmailCount += emailCountByRecentInteractions7DaysBO.getCount();

            EmailCustomerVO emailCustomerVO = new EmailCustomerVO();
            emailCustomerVO.setId(emailCountByRecentInteractions7DaysBO.getCustomerId());
            emailCustomerVO.setName(emailCountByRecentInteractions7DaysBO.getCompanyName());
            emailCustomerVO.setCustomerCount(1);
            emailCustomerVO.setUnReadEmailCount(emailCountByRecentInteractions7DaysBO.getCount());
            recentInteractions7DaysCustomerList.add(emailCustomerVO);
        }

        EmailGeneralListVO recentInteractions7DaysEmailGeneralVO = new EmailGeneralListVO();
        recentInteractions7DaysEmailGeneralVO.setId(0L);
        recentInteractions7DaysEmailGeneralVO.setName("最近7天有往来");
        recentInteractions7DaysEmailGeneralVO.setCustomerCount(recentInteractions7DaysCustomerCount);
        recentInteractions7DaysEmailGeneralVO.setUnReadEmailCount(recentInteractions7DaysEmailCount);
        recentInteractions7DaysEmailGeneralVO.setCustomerList(recentInteractions7DaysCustomerList);

        // 关注客户
        List<EmailCountGroupByFocusFlagBO> emailCountGroupByFocusFlagBOList = customerService.selectEmailCountGroupByFocusFlag(userId);
        int focusFlagEmailCount = 0;
        int focusFlagCustomerCount = 0;
        List<EmailCustomerVO> focusFlagCustomerList = new ArrayList<>();
        for (EmailCountGroupByFocusFlagBO emailCountGroupByFocusFlagBO : emailCountGroupByFocusFlagBOList) {
            focusFlagCustomerCount ++;
            focusFlagEmailCount += emailCountGroupByFocusFlagBO.getCount();

            EmailCustomerVO emailCustomerVO = new EmailCustomerVO();
            emailCustomerVO.setId(emailCountGroupByFocusFlagBO.getCustomerId());
            emailCustomerVO.setName(emailCountGroupByFocusFlagBO.getCompanyName());
            emailCustomerVO.setCustomerCount(1);
            emailCustomerVO.setUnReadEmailCount(emailCountGroupByFocusFlagBO.getCount());
            focusFlagCustomerList.add(emailCustomerVO);
        }

        EmailGeneralListVO focusFlagEmailGeneralVO = new EmailGeneralListVO();
        focusFlagEmailGeneralVO.setId(0L);
        focusFlagEmailGeneralVO.setName("关注客户");
        focusFlagEmailGeneralVO.setCustomerCount(focusFlagCustomerCount);
        focusFlagEmailGeneralVO.setUnReadEmailCount(focusFlagEmailCount);
        focusFlagEmailGeneralVO.setCustomerList(focusFlagCustomerList);

        List<EmailGeneralListVO> emailGeneralVOList = new ArrayList<>();
        emailGeneralVOList.add(recentInteractions7DaysEmailGeneralVO);
        emailGeneralVOList.add(focusFlagEmailGeneralVO);
        return emailGeneralVOList;
    }
}
