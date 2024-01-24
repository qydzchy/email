package com.ruoyi.customer.service.impl;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.customer.CustomerActivityEnum;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.customer.domain.Source;
import com.ruoyi.customer.domain.bo.*;
import com.ruoyi.customer.domain.vo.*;
import com.ruoyi.customer.mapper.CustomerContactMapper;
import com.ruoyi.customer.mapper.SourceMapper;
import com.ruoyi.customer.service.ICustomerService;
import com.ruoyi.customer.service.ICustomerEmailService;
import com.ruoyi.customer.service.IPacketService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CustomerEmailServiceImpl implements ICustomerEmailService {

    @Resource
    private ICustomerService customerService;
    @Resource
    private IPacketService packetService;
    @Resource
    private SourceMapper sourceMapper;
    @Resource
    private CustomerContactMapper customerContactMapper;

    /**
     * 公海分组列表
     * @return
     */
    @Override
    public List<EmailPublicleadsGroupsListVO> publicleadsGroupsList() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        List<EmailPublicleadsGroupsListVO> emailPublicleadsGroupsVOList = getPublicleadsGroups(userId);

        return emailPublicleadsGroupsVOList;
    }

    /**
     * 获取公海分组
     * @param userId
     * @return
     */
    private List<EmailPublicleadsGroupsListVO> getPublicleadsGroups(Long userId) {
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
        List<EmailPacketListVO> emailPacketVOList = getPacket(userId);

        return emailPacketVOList;
    }

    /**
     * 获取客户分组
     * @param userId
     * @return
     */
    private List<EmailPacketListVO> getPacket(Long userId) {
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
        List<EmailRatingListVO> emailRatingVOList = getRating(userId);

        return emailRatingVOList;
    }

    /**
     * 获取客户星级
     * @param userId
     * @return
     */
    private List<EmailRatingListVO> getRating(Long userId) {
        // 客户邮件数量
        List<EmailCountGroupByRatingBO> emailCountGroupByList = customerService.selectEmailCountGroupByRating(userId);

        List<EmailRatingListVO> emailRatingVOList = new ArrayList<>();
        for (int i = 5; i >= 0; i--) {
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

        List<EmailSourceListVO> emailSourceListVO = getSource(userId);

        return emailSourceListVO;
    }

    /**
     * 获取客户来源
     * @param userId
     * @return
     */
    private List<EmailSourceListVO> getSource(Long userId) {
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
        List<EmailActivityListVO> emailActivityVOList = getActivity(userId);

        return emailActivityVOList;
    }

    /**
     * 获取客户活跃度
     * @param userId
     * @return
     */
    private List<EmailActivityListVO> getActivity(Long userId) {
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

        List<EmailActivityListVO> emailActivityVOList = new ArrayList<>();
        Map<Integer, List<EmailCountGroupByCustomerBO>> activityTypeMap = emailCountGroupByList.stream().collect(Collectors.groupingBy(emailCountGroupBy -> emailCountGroupBy.getActivityType()));
        activityTypeMap.forEach((activityType, emailCountGroupByCustomerList) -> {
            EmailCountGroupByCustomerBO firstEmailCountGroupByCustomerBO = emailCountGroupByCustomerList.get(0);
            String activityName = firstEmailCountGroupByCustomerBO.getActivityName();

            int totalUnReadEmailCount = 0;
            int totalCustomerCount = 0;
            List<EmailCustomerVO> customerList = new ArrayList<>();
            for (EmailCountGroupByCustomerBO emailCountGroupByCustomer : emailCountGroupByCustomerList) {
                EmailCustomerVO customer = new EmailCustomerVO();
                customer.setId(emailCountGroupByCustomer.getCustomerId());
                customer.setName(emailCountGroupByCustomer.getCompanyName());
                customer.setUnReadEmailCount(emailCountGroupByCustomer.getCount());
                customer.setCustomerCount(1);
                customerList.add(customer);
                totalUnReadEmailCount += emailCountGroupByCustomer.getCount();
                totalCustomerCount ++;
            }

            EmailActivityListVO emailActivityVO = new EmailActivityListVO();
            emailActivityVO.setId(activityType.longValue());
            emailActivityVO.setName(activityName);
            emailActivityVO.setUnReadEmailCount(totalUnReadEmailCount);
            emailActivityVO.setCustomerCount(totalCustomerCount);
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

        // 获取近7天往来
        EmailGeneralListVO recentInteractions7DaysEmailGeneralVO = getRecentInteractions7Days(userId);
        // 获取关注客户
        EmailGeneralListVO focusFlagEmailGeneralVO = getCustomerFocus(userId);

        List<EmailGeneralListVO> emailGeneralVOList = new ArrayList<>();
        emailGeneralVOList.add(recentInteractions7DaysEmailGeneralVO);
        emailGeneralVOList.add(focusFlagEmailGeneralVO);
        return emailGeneralVOList;
    }

    /**
     * 获取关注客户
     * @param userId
     * @return
     */
    private EmailGeneralListVO getCustomerFocus(Long userId) {
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
        focusFlagEmailGeneralVO.setId(-2L);
        focusFlagEmailGeneralVO.setName("关注客户");
        focusFlagEmailGeneralVO.setCustomerCount(focusFlagCustomerCount);
        focusFlagEmailGeneralVO.setUnReadEmailCount(focusFlagEmailCount);
        focusFlagEmailGeneralVO.setCustomerList(focusFlagCustomerList);
        return focusFlagEmailGeneralVO;
    }

    /**
     * 获取最近7天往来
     */
    private EmailGeneralListVO getRecentInteractions7Days(Long userId) {
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
        recentInteractions7DaysEmailGeneralVO.setId(-1L);
        recentInteractions7DaysEmailGeneralVO.setName("最近7天有往来");
        recentInteractions7DaysEmailGeneralVO.setCustomerCount(recentInteractions7DaysCustomerCount);
        recentInteractions7DaysEmailGeneralVO.setUnReadEmailCount(recentInteractions7DaysEmailCount);
        recentInteractions7DaysEmailGeneralVO.setCustomerList(recentInteractions7DaysCustomerList);
        return recentInteractions7DaysEmailGeneralVO;
    }

    /**
     * 阶段列表
     * @return
     */
    @Override
    public List<EmailStageListVO> stageList() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        List<EmailStageListVO> emailStageVOList = getStage(userId);

        return emailStageVOList;
    }

    /**
     * 获取客户阶段
     * @param userId
     * @return
     */
    private List<EmailStageListVO> getStage(Long userId) {
        // 客户邮件数量
        List<EmailCountGroupByStageBO> emailCountGroupByList = customerService.selectEmailCountGroupByStage(userId);

        List<EmailStageListVO> emailStageVOList = new ArrayList<>();
        List<PacketListVO> packetVOList = packetService.packetList();
        for (PacketListVO packetVO : packetVOList) {
            Long id = packetVO.getId();
            String name = packetVO.getName();
            int customerCount = 0;
            int emailCount = 0;

            List<EmailCustomerVO> customerList = new ArrayList<>();
            Iterator<EmailCountGroupByStageBO> iterator = emailCountGroupByList.iterator();
            while (iterator.hasNext()) {
                EmailCountGroupByStageBO emailCountGroupBy = iterator.next();
                Long stageId = emailCountGroupBy.getStageId();
                if (id.longValue() == stageId.longValue()) {
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

            EmailStageListVO emailStageListVO = new EmailStageListVO();
            emailStageListVO.setId(id);
            emailStageListVO.setName(name);
            emailStageListVO.setUnReadEmailCount(emailCount);
            emailStageListVO.setCustomerCount(customerCount);
            emailStageListVO.setCustomerList(customerList);
            emailStageVOList.add(emailStageListVO);
        }
        return emailStageVOList;
    }

    /**
     * 搜索列表
     * @param keyword
     * @return
     */
    @Override
    public List<EmailCustomerVO> searchList(String keyword) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        // 客户邮件数量
        return customerService.selectEmailCountGroupBySearch(userId, keyword);
    }

    /**
     * 联系人列表
     * @param customerId
     * @return
     */
    @Override
    public List<String> contactEmailList(Long customerId) {
        List<CustomerContactBO> customerContactBOList = customerContactMapper.selectCustomerContactByCustomerId(customerId);
        List<String> emailList = new ArrayList<>();
        for (CustomerContactBO customerContactBO : customerContactBOList) {
            String email = customerContactBO.getEmail();
            if (StringUtils.isNotBlank(email)) {
                emailList.add(email);
            }
        }

        return emailList;
    }

    /**
     * 客户类别列表
     * @param userId
     * @return
     */
    @Override
    public CategoryVO categoryList(Long userId) {
        ExecutorService executorService = Executors.newFixedThreadPool(3); //

        try {
            // 创建任务列表
            List<Callable<List<?>>> tasks = new ArrayList<>();
            tasks.add(() -> getRecentInteractions7Days(userId).getCustomerList());
            tasks.add(() -> getPublicleadsGroups(userId));
            tasks.add(() -> getPacket(userId));
            tasks.add(() -> getSource(userId));
            tasks.add(() -> getStage(userId));
            tasks.add(() -> getRating(userId));
            tasks.add(() -> getActivity(userId));

            // 并行执行任务
            List<Future<List<?>>> results = executorService.invokeAll(tasks);

            // 获取执行结果
            List<EmailCustomerVO> last7Days = (List<EmailCustomerVO>) results.get(0).get();
            List<EmailPublicleadsGroupsListVO> publicleadsGroups = (List<EmailPublicleadsGroupsListVO>) results.get(1).get();
            List<EmailPacketListVO> packet = (List<EmailPacketListVO>) results.get(2).get();
            List<EmailSourceListVO> source = (List<EmailSourceListVO>) results.get(3).get();
            List<EmailStageListVO> stage = (List<EmailStageListVO>) results.get(4).get();
            List<EmailRatingListVO> rating = (List<EmailRatingListVO>) results.get(5).get();
            List<EmailActivityListVO> activity = (List<EmailActivityListVO>) results.get(6).get();

            // 构建CategoryVO对象
            CategoryVO categoryVO = new CategoryVO();
            categoryVO.setLast7Days(last7Days);
            categoryVO.setPublicleadsGroups(publicleadsGroups);
            categoryVO.setPacket(packet);
            categoryVO.setSource(source);
            categoryVO.setStage(stage);
            categoryVO.setRating(rating);
            categoryVO.setActivity(activity);

            return categoryVO;

        } catch (InterruptedException | ExecutionException e) {
            // 处理异常
            log.error("获取客户类别列表异常", e);
            return new CategoryVO();
        } finally {
            // 关闭线程池
            executorService.shutdown();
        }
    }
}
