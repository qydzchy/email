package com.ruoyi.customer.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.customer.*;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.common.utils.customer.CustomerShuffleThreadPoolUtil;
import com.ruoyi.customer.domain.bo.UserDeptInfoBO;
import com.ruoyi.customer.domain.dto.SegmentAddOrUpdateDTO;
import com.ruoyi.customer.domain.vo.*;
import com.ruoyi.customer.mapper.*;
import com.ruoyi.customer.service.ICustomerService;
import com.ruoyi.customer.service.IUserDeptService;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.domain.Segment;
import com.ruoyi.customer.service.ISegmentService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 客群Service业务层处理
 * 
 * @author tangJM.
 * @date 2023-11-01
 */
@Service
public class SegmentServiceImpl implements ISegmentService 
{
    @Resource
    private SegmentMapper segmentMapper;
    @Resource
    private IUserDeptService userDeptService;
    @Resource
    private ICustomerService customerService;
    @Resource
    private TagMapper tagMapper;
    @Resource
    private StageMapper stageMapper;
    @Resource
    private SourceMapper sourceMapper;
    @Resource
    private PacketMapper packetMapper;
    @Resource
    private PublicleadsGroupsMapper publicleadsGroupsMapper;
    @Resource
    private CustomerFollowUpPersonnelMapper customerFollowUpPersonnelMapper;
    @Resource
    private CustomerMapper customerMapper;
    /**
     * 查询客群
     * 
     * @param id 客群主键
     * @return 客群
     */
    @Override
    public Segment selectSegmentById(Long id)
    {
        return segmentMapper.selectSegmentById(id);
    }

    /**
     * 查询客群列表
     * 
     * @param segment 客群
     * @return 客群
     */
    @Override
    public List<Segment> selectSegmentList(Segment segment)
    {
        return segmentMapper.selectSegmentList(segment);
    }

    /**
     * 新增客群
     * 
     * @param segmentAddOrUpdateDTO 客群
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insertSegment(SegmentAddOrUpdateDTO segmentAddOrUpdateDTO)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        Segment segment = new Segment();
        BeanUtils.copyProperties(segmentAddOrUpdateDTO, segment);
        segment.setCreateId(userId);
        segment.setCreateBy(username);
        segment.setCreateTime(DateUtils.getNowDate());
        segment.setUpdateId(userId);
        segment.setUpdateBy(username);
        segment.setUpdateTime(DateUtils.getNowDate());

        segmentMapper.insertSegment(segment);
        Long id = segment.getId();

        List<Segment> subSegmentList = new ArrayList<>();

        if (segmentAddOrUpdateDTO.getAdditionRule().intValue() == 1) {
            if (StringUtils.isBlank(segmentAddOrUpdateDTO.getSubGroupColumn())) {
                throw new ServiceException("二级分群字段不能为空");
            }

            // 判断二级分群字段列表是否有数据
            List<SubgroupColumnListVO> subgroupColumnVOList = subgroupColumnList(segmentAddOrUpdateDTO.getSubGroupColumn());
            if (subgroupColumnVOList == null || subgroupColumnVOList.isEmpty()) {
                return true;
            }

            subgroupColumnVOList.stream().forEach(subgroupColumnVO -> {
                String name = subgroupColumnVO.getName();
                Integer conditionRuleType = 1;
                String conditionRuleContent = generateConditionRuleContent(segmentAddOrUpdateDTO.getSubGroupColumn(), subgroupColumnVO.getId());
                subSegmentList.add(generateSubSegment(id, name, conditionRuleType, conditionRuleContent, userId, username));
            });

        } else if (segmentAddOrUpdateDTO.getAdditionRule().intValue() == 2) {
            List<SegmentAddOrUpdateDTO> subGroupList = segmentAddOrUpdateDTO.getChildren();
            if (subGroupList == null || subGroupList.isEmpty()) {
                return true;
            }

            subGroupList.stream().forEach(subGroup -> {
                String name = subGroup.getName();
                Integer conditionRuleType = subGroup.getConditionRuleType();
                String conditionRuleContent = subGroup.getConditionRuleContent();
                subSegmentList.add(generateSubSegment(id, name, conditionRuleType, conditionRuleContent, userId, username));
            });
        }

        // 批量新增子客群
        if (!subSegmentList.isEmpty()) {
            segmentMapper.batchInsertSegment(subSegmentList);
        }

        // 洗牌
        CustomerShuffleThreadPoolUtil.getThreadPool().execute(() -> customerService.shuffle(null, id));
        return true;
    }

    /**
     * 生成二级客群对象
     * @param id
     * @param name
     * @param conditionRuleType
     * @param conditionRuleContent
     * @param userId
     * @param username
     */
    private Segment generateSubSegment(Long id, String name, Integer conditionRuleType, String conditionRuleContent, Long userId, String username) {
        Segment subSegment = new Segment();
        subSegment.setParentId(id);
        subSegment.setName(name);
        subSegment.setConditionRuleType(conditionRuleType);
        subSegment.setConditionRuleContent(conditionRuleContent);
        subSegment.setDelFlag("0");
        subSegment.setCreateId(userId);
        subSegment.setCreateBy(username);
        subSegment.setCreateTime(DateUtils.getNowDate());
        subSegment.setUpdateId(userId);
        subSegment.setUpdateBy(username);
        subSegment.setUpdateTime(DateUtils.getNowDate());
        return subSegment;
    }

    /**
     * 生成条件规则内容
     * @param columnName
     * @param value
     * @return
     */
    private String generateConditionRuleContent(String columnName, String value) {
        JSONArray jsonA = new JSONArray();
        JSONObject jsonO = new JSONObject();
        jsonO.put("columnName", columnName);
        jsonO.put("andOr", AndOrEnum.AND.getAndOr());
        jsonO.put("conditionType", 1);
        jsonO.put("value", value);
        jsonA.add(jsonO);
        return JSON.toJSONString(jsonA);
    }

    /**
     * 修改客群
     * 
     * @param segmentAddOrUpdateDTO 客群
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateSegment(SegmentAddOrUpdateDTO segmentAddOrUpdateDTO)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        Segment segment = new Segment();
        BeanUtils.copyProperties(segmentAddOrUpdateDTO, segment);
        segment.setUpdateId(userId);
        segment.setUpdateBy(username);
        segment.setUpdateTime(DateUtils.getNowDate());
        segmentMapper.updateSegment(segment);

        // 删除子客群
        Long id = segment.getId();
        segmentMapper.deleteSegmentByParentId(id);

        List<SegmentAddOrUpdateDTO> subGroupList = segmentAddOrUpdateDTO.getChildren();
        if (subGroupList != null && !subGroupList.isEmpty()) {
            List<Segment> segmentList = new ArrayList<>();
            subGroupList.stream().forEach(subGroup -> {
                Segment subSegment = new Segment();
                BeanUtils.copyProperties(subGroup, subSegment);
                subSegment.setParentId(segmentAddOrUpdateDTO.getId());
                subSegment.setDelFlag("0");
                subSegment.setCreateId(userId);
                subSegment.setCreateBy(username);
                subSegment.setCreateTime(DateUtils.getNowDate());
                subSegment.setUpdateId(userId);
                subSegment.setUpdateBy(username);
                subSegment.setUpdateTime(DateUtils.getNowDate());
                segmentList.add(subSegment);
            });

            // 批量新增子客群
            segmentMapper.batchInsertSegment(segmentList);
        }

        // 洗牌
        CustomerShuffleThreadPoolUtil.getThreadPool().execute(() -> customerService.shuffle(null, id));
        return true;
    }

    /**
     * 批量删除客群
     * 
     * @param ids 需要删除的客群主键
     * @return 结果
     */
    @Override
    public int deleteSegmentByIds(Long[] ids)
    {
        return segmentMapper.deleteSegmentByIds(ids);
    }

    /**
     * 删除客群信息
     * 
     * @param id 客群主键
     * @return 结果
     */
    @Override
    public boolean deleteSegmentById(Long id)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        segmentMapper.deleteSegmentById(id, userId, username);
        segmentMapper.deleteSegmentByParentId(id);
        return true;
    }

    /**
     * 客群树
     * @param createId
     * @return
     */
    @Override
    public List<SegmentListVO> getSegmentTree(Long createId) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        // 生成基础客群
        List<SegmentListVO> allSegmentVOList = new ArrayList<>();
        if (userId.longValue() == createId.longValue()) {
            allSegmentVOList.addAll(initBasicSegment());
        }

        List<SegmentListVO> segmentVOList = segmentMapper.list(createId);
        if (segmentVOList == null || segmentVOList.isEmpty()) {
            return allSegmentVOList;
        }

        // 去除掉二级客群客户数为0的客群（不包含一级客群）
        Iterator<SegmentListVO> iterator = segmentVOList.iterator();
        while (iterator.hasNext()) {
            SegmentListVO segmentVO = iterator.next();
            if (segmentVO.getParentId().longValue() != -1L && segmentVO.getCustomerCount() == 0) {
                iterator.remove();
            }
        }

        allSegmentVOList.addAll(buildTree(segmentVOList, -1L));
        return allSegmentVOList;
    }

    /**
     * 获取条件规则字段
     * @return
     */
    @Override
    public List<Map<String, Object>> getConditionRuleColumn() {
        Map<CustomerColumnCategoryEnum, List<CustomerColumnEnum>> customerColumnCategoryMap = Arrays.stream(CustomerColumnEnum.values()).collect(Collectors.groupingBy(CustomerColumnEnum::getCustomerColumnCategoryEnum));

        List<CustomerColumnEnum> companyColumnEnumList = customerColumnCategoryMap.get(CustomerColumnCategoryEnum.COMPANY_INFO);
        List<CustomerColumnEnum> contactColumnEnumList = customerColumnCategoryMap.get(CustomerColumnCategoryEnum.CONTACT_INFO);
        List<CustomerColumnEnum> dateTimeColumnEnumList = customerColumnCategoryMap.get(CustomerColumnCategoryEnum.DATE_TIME);

        Map<String, Object> companyInfoMap = new HashMap<>();
        companyInfoMap.put("nickName", "公司名称");
        List<Map<String, String>> companyColumnMapList = new ArrayList<>();
        for (CustomerColumnEnum companyColumnEnum : companyColumnEnumList) {
            Map<String, String> companyColumnMap = new HashMap<>();
            companyColumnMap.put("columnName", companyColumnEnum.getColumnName());
            companyColumnMap.put("nickName", companyColumnEnum.getNickName());
            companyColumnMapList.add(companyColumnMap);
        }
        companyInfoMap.put("children", companyColumnMapList);

        Map<String, Object> contactInfoMap = new HashMap<>();
        contactInfoMap.put("nickName", "联系人信息");
        List<Map<String, String>> contackColumnMapList = new ArrayList<>();
        for (CustomerColumnEnum contactColumnEnum : contactColumnEnumList) {
            Map<String, String> contactColumnMap = new HashMap<>();
            contactColumnMap.put("columnName", contactColumnEnum.getColumnName());
            contactColumnMap.put("nickName", contactColumnEnum.getNickName());
            contackColumnMapList.add(contactColumnMap);
        }
        contactInfoMap.put("children", contackColumnMapList);

        Map<String, Object> dateTimeMap = new HashMap<>();
        dateTimeMap.put("nickName", "日期时间");
        List<Map<String, String>> dateTimeColumnMapList = new ArrayList<>();
        for (CustomerColumnEnum dateTimeColumnEnum : dateTimeColumnEnumList) {
            Map<String, String> dateTimeColumnMap = new HashMap<>();
            dateTimeColumnMap.put("columnName", dateTimeColumnEnum.getColumnName());
            dateTimeColumnMap.put("nickName", dateTimeColumnEnum.getNickName());
            dateTimeColumnMapList.add(dateTimeColumnMap);
        }
        dateTimeMap.put("children", dateTimeColumnMapList);

        List<Map<String, Object>> result = new ArrayList<>();
        result.add(companyInfoMap);
        result.add(contactInfoMap);
        result.add(dateTimeMap);
        return result;
    }

    @Override
    public List<SegmentUserListVO> userList() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();

        List<SegmentUserListVO> segmentUserList = segmentMapper.userList();
        SegmentUserListVO currentUser = new SegmentUserListVO();
        Iterator<SegmentUserListVO> iterator = segmentUserList.iterator();
        while (iterator.hasNext()) {
            SegmentUserListVO segmentUserVO = iterator.next();
            if (segmentUserVO.getUserId().longValue() == userId.longValue()) {
                currentUser = segmentUserVO;
                iterator.remove();
                break;
            }
        }

        currentUser.setNickName("我的");
        segmentUserList.add(0, currentUser);
        return segmentUserList;
    }

    /**
     * 客户列表-客群列表
     * @return
     */
    @Override
    public List<CustomerSegmentListVO> segmentList() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        Long deptId = loginUser.getDeptId();

        // 查询所有客群
        List<Segment> segmentList = segmentMapper.selectSegmentList(new Segment());

        // 不成立的客群ID
        Set<Long> notMetSegmentIdSet = new HashSet<>();
        segmentList.stream().forEach(segment -> {
            if (segment.getParentId().longValue() == -1L) {
                boolean visibleConditionMet = isVisibleConditionMet(Arrays.asList(UserDeptInfoBO.builder().userId(userId).deptId(deptId).build()), segment);
                if (!visibleConditionMet) {
                    notMetSegmentIdSet.add(segment.getId());
                }
            }
        });

        // 移除掉不成立的客群包括子客群
        Iterator<Segment> iterator = segmentList.iterator();
        while (iterator.hasNext()) {
            Segment segment = iterator.next();
            if (notMetSegmentIdSet.contains(segment.getId()) || notMetSegmentIdSet.contains(segment.getParentId())) {
                iterator.remove();
            }
        }

        // 查询当前用户的客群客户数
        List<Map<String, Object>> segmentCustomerCountMapList = segmentMapper.selectSegmentCustomerCountByUserId(userId);
        Map<Long, Integer> segmentCustomerCountMap = segmentCustomerCountMapList.stream().collect(Collectors.toMap(
                map -> Long.parseLong(String.valueOf(map.get("segmentId"))),
                map -> Integer.parseInt(String.valueOf(map.get("customerCount")))));

        List<CustomerSegmentListVO> customerSegmentVOList = new ArrayList<>();
        for (Segment segment : segmentList) {
            // 剔除掉客户数为0的客群，不包含一级客群
            if (segment.getParentId().longValue() != -1 && segmentCustomerCountMap.get(segment.getId()) == null) {
                continue;
            }

            CustomerSegmentListVO customerSegmentVO = new CustomerSegmentListVO();
            customerSegmentVO.setId(segment.getId());
            customerSegmentVO.setParentId(segment.getParentId());
            customerSegmentVO.setName(segment.getName());

            int customerCount = segmentCustomerCountMap.get(segment.getId()) != null ? segmentCustomerCountMap.get(segment.getId()) : 0;
            customerSegmentVO.setCustomerCount(customerCount);
            customerSegmentVOList.add(customerSegmentVO);
        }

        // 初始化客群
        List<CustomerSegmentListVO> allCustomerSegmentVOList = initBasicSegment(userId);
        allCustomerSegmentVOList.addAll(buildTree2(customerSegmentVOList, -1L));
        return allCustomerSegmentVOList;
    }

    private List<CustomerSegmentListVO> buildTree2(List<CustomerSegmentListVO> customerSegmentVOList, Long parentId) {
        List<CustomerSegmentListVO> children = new ArrayList<>();

        for (CustomerSegmentListVO customerSegmentVO : customerSegmentVOList) {
            if ((parentId == null && customerSegmentVO.getParentId() == null)
                    || (parentId != null && parentId.longValue() == customerSegmentVO.getParentId().longValue())) {
                List<CustomerSegmentListVO> childCustomerSegmentVOList = buildTree2(customerSegmentVOList, customerSegmentVO.getId());
                customerSegmentVO.setChildren(childCustomerSegmentVOList);
                children.add(customerSegmentVO);
            }
        }

        return children;
    }

    /**
     * 生成基础客群
     * @return
     */
    private List<CustomerSegmentListVO> initBasicSegment(Long userId) {
        List<CustomerSegmentListVO> basicSegment = new ArrayList<>();

        CustomerSegmentListVO allSegment = new CustomerSegmentListVO();
        allSegment.setId(-2L);
        allSegment.setParentId(-1L);
        allSegment.setName("全部客户");
        // 查询客户数量
        Integer allCustomerCount = segmentMapper.countCustomerCountByUserId(userId, null);
        allSegment.setCustomerCount(allCustomerCount);
        basicSegment.add(allSegment);

        CustomerSegmentListVO followSegment = new CustomerSegmentListVO();
        followSegment.setId(-1L);
        followSegment.setParentId(-1L);
        followSegment.setName("我的关注");
        // 查询客户数量
        Integer focusCustomerCount = segmentMapper.countCustomerCountByUserId(userId, true);
        followSegment.setCustomerCount(focusCustomerCount);
        basicSegment.add(followSegment);

        return basicSegment;
    }

    /**
     * 可见范围是否成立
     * @param userDeptInfoBOList
     * @param segment
     * @return
     */
    @Override
    public boolean isVisibleConditionMet(List<UserDeptInfoBO> userDeptInfoBOList, Segment segment) {
        Integer usageScope = segment.getUsageScope();
        // 公司可见
        if (usageScope.intValue() == 1) {
            String visibilityScope = segment.getVisibilityScope();
            if (StringUtils.isBlank(visibilityScope)) return false;

            return userDeptService.userDeptVerify(userDeptInfoBOList, segment.getVisibilityScope());

            // 个人可见
        } else if (usageScope.intValue() == 2) {
            // 判断当前客群创建人是否是该客户的跟进人
            if (!userDeptInfoBOList.isEmpty()) {
                List<Long> userIds = userDeptInfoBOList.stream().map(UserDeptInfoBO::getUserId).collect(Collectors.toList());
                if (userIds.contains(segment.getCreateId())) {
                    return true;
                }
            } else {
                return false;
            }
        }

        return false;
    }

    @Override
    public List<SegmentListVO> simpleList() {
        List<Segment> segmentList = segmentMapper.selectSegmentList(new Segment());

        List<SegmentListVO> segmentVOList = new ArrayList<>();
        for (Segment segment : segmentList) {
            SegmentListVO segmentVO = new SegmentListVO();
            segmentVO.setId(segment.getId());
            segmentVO.setParentId(segment.getParentId());
            segmentVO.setName(segment.getName());
            segmentVOList.add(segmentVO);
        }

        return buildTree(segmentVOList, -1L);
    }

    /**
     * 二级客群字段
     * @return
     */
    @Override
    public List<SubgroupColumnVO> subgroupColumn() {
        List<SubgroupColumnVO> subgroupColumnList = new ArrayList<>();

        subgroupColumnList.add(SubgroupColumnVO.builder().columnName(CustomerColumnEnum.CUSTOMER_TAG.getColumnName()).nickName(CustomerColumnEnum.CUSTOMER_TAG.getNickName()).build());
        subgroupColumnList.add(SubgroupColumnVO.builder().columnName(CustomerColumnEnum.COUNTRY_REGION.getColumnName()).nickName(CustomerColumnEnum.COUNTRY_REGION.getNickName()).build());
        subgroupColumnList.add(SubgroupColumnVO.builder().columnName(CustomerColumnEnum.TIMEZONE.getColumnName()).nickName(CustomerColumnEnum.TIMEZONE.getNickName()).build());
        subgroupColumnList.add(SubgroupColumnVO.builder().columnName(CustomerColumnEnum.CUSTOMER_STAGE.getColumnName()).nickName(CustomerColumnEnum.CUSTOMER_STAGE.getNickName()).build());
        subgroupColumnList.add(SubgroupColumnVO.builder().columnName(CustomerColumnEnum.CUSTOMER_SOURCE.getColumnName()).nickName(CustomerColumnEnum.CUSTOMER_SOURCE.getNickName()).build());
        subgroupColumnList.add(SubgroupColumnVO.builder().columnName(CustomerColumnEnum.PACKET.getColumnName()).nickName(CustomerColumnEnum.PACKET.getNickName()).build());
        subgroupColumnList.add(SubgroupColumnVO.builder().columnName(CustomerColumnEnum.PUBLICLEADS_GROUPS.getColumnName()).nickName(CustomerColumnEnum.PUBLICLEADS_GROUPS.getNickName()).build());
        subgroupColumnList.add(SubgroupColumnVO.builder().columnName(CustomerColumnEnum.CUSTOMER_RATING.getColumnName()).nickName(CustomerColumnEnum.CUSTOMER_RATING.getNickName()).build());
        subgroupColumnList.add(SubgroupColumnVO.builder().columnName(CustomerColumnEnum.SCALE.getColumnName()).nickName(CustomerColumnEnum.SCALE.getNickName()).build());
        subgroupColumnList.add(SubgroupColumnVO.builder().columnName(CustomerColumnEnum.FOLLOW_UP_PERSONNEL.getColumnName()).nickName(CustomerColumnEnum.FOLLOW_UP_PERSONNEL.getNickName()).build());
        return subgroupColumnList;
    }

    /**
     * 二级分群字段列表
     * @return
     */
    @Override
    public List<SubgroupColumnListVO> subgroupColumnList(String columnName) {
        List<SubgroupColumnListVO> subgroupColumnVOList = new ArrayList<>();
        CustomerColumnEnum customerColumnEnum = CustomerColumnEnum.getEnum(columnName);
        if (customerColumnEnum == null) return subgroupColumnVOList;

        switch (customerColumnEnum) {
            case CUSTOMER_TAG:
                subgroupColumnVOList = tagMapper.selectCustomerTagSimpleInfo();
                break;
            case CUSTOMER_STAGE:
                subgroupColumnVOList = stageMapper.selectCustomerStageSimpleInfo();
                break;
            case CUSTOMER_SOURCE:
                subgroupColumnVOList = sourceMapper.selectCustomerSourceSimpleInfo();
                break;
            case PACKET:
                subgroupColumnVOList = packetMapper.selectCustomerPacketSimpleInfo();
                break;
            case PUBLICLEADS_GROUPS:
                subgroupColumnVOList = publicleadsGroupsMapper.selectCustomerPublicleadsGroupsSimpleInfo();
                break;
            case CUSTOMER_RATING:
                subgroupColumnVOList = generateCustomerRating();
                break;
            case SCALE:
                subgroupColumnVOList = generateScale();
                break;
            case FOLLOW_UP_PERSONNEL:
                subgroupColumnVOList = customerFollowUpPersonnelMapper.selectCustomerFollowUpPersonnelSimpleInfo();
                break;
            case COUNTRY_REGION:
                subgroupColumnVOList = customerMapper.selectCountryRegion();
                break;
            case TIMEZONE:
                subgroupColumnVOList = customerMapper.selectTimeZone();
                break;
        }

        return subgroupColumnVOList;
    }

    /**
     * 客群详情
     * @param id
     * @return
     */
    @Override
    public SegmentListVO detail(Long id) {
        Segment segment = segmentMapper.selectSegmentById(id);
        SegmentListVO segmentVO = new SegmentListVO();
        BeanUtils.copyProperties(segment, segmentVO);
        List<SegmentListVO> children = new ArrayList<>();
        if (segment.getAdditionRule() != null && segment.getAdditionRule().intValue() == 2) {
            Segment segmentParam = new Segment();
            segmentParam.setParentId(id);
            List<Segment> subSegmentList = segmentMapper.selectSegmentList(segmentParam);
            for (Segment subSegment : subSegmentList) {
                SegmentListVO subSegmentVO = new SegmentListVO();
                BeanUtils.copyProperties(subSegment, subSegmentVO);
                children.add(subSegmentVO);
            }
        }
        segmentVO.setChildren(children);
        return segmentVO;
    }

    /**
     * 生成规模
     * @return
     */
    private List<SubgroupColumnListVO> generateScale() {
        // 规模 1.少于59人 2.60-149人 3.150-499人 4.500-999人 5.1000-4999人 6.5000人以上
        List<SubgroupColumnListVO> subgroupColumnVOList = new ArrayList<>();
        for (long scale = 1; scale <= 6; scale++) {
            SubgroupColumnListVO subgroupColumnVO = new SubgroupColumnListVO();
            subgroupColumnVO.setId(scale + "");
            switch ((int) scale) {
                case 1:
                    subgroupColumnVO.setName("少于59人");
                    break;
                case 2:
                    subgroupColumnVO.setName("60-149人");
                    break;
                case 3:
                    subgroupColumnVO.setName("150-499人");
                    break;
                case 4:
                    subgroupColumnVO.setName("500-999人");
                    break;
                case 5:
                    subgroupColumnVO.setName("1000-4999人");
                    break;
                case 6:
                    subgroupColumnVO.setName("5000人以上");
                    break;
            }
            subgroupColumnVOList.add(subgroupColumnVO);
        }

        return subgroupColumnVOList;
    }

    /**
     * 生成客户星级
     * @return
     */
    private List<SubgroupColumnListVO> generateCustomerRating() {
        List<SubgroupColumnListVO> subgroupColumnVOList = new ArrayList<>();
        for (long rating = 0; rating <= 5; rating++) {
            SubgroupColumnListVO subgroupColumnVO = new SubgroupColumnListVO();
            subgroupColumnVO.setId(rating + "");
            subgroupColumnVO.setName(rating + "星");
            subgroupColumnVOList.add(subgroupColumnVO);
        }
        return subgroupColumnVOList;
    }


    /**
     * 生成基础客群
     * @return
     */
    private List<SegmentListVO> initBasicSegment() {
        List<SegmentListVO> basicSegment = new ArrayList<>();

        SegmentListVO allSegment = new SegmentListVO();
        allSegment.setId(-2L);
        allSegment.setParentId(null);
        allSegment.setName("全部客户");
        allSegment.setUsageScope(2);
        // 查询客户数量
        Integer allCustomerCount = segmentMapper.countCustomerCount(null);
        allSegment.setCustomerCount(allCustomerCount);
        basicSegment.add(allSegment);

        SegmentListVO followSegment = new SegmentListVO();
        followSegment.setId(-1L);
        followSegment.setParentId(null);
        followSegment.setName("我的关注");
        followSegment.setUsageScope(2);
        // 查询客户数量
        Integer focusCustomerCount = segmentMapper.countCustomerCount(true);
        followSegment.setCustomerCount(focusCustomerCount);
        basicSegment.add(followSegment);

        return basicSegment;
    }

    private List<SegmentListVO> buildTree(List<SegmentListVO> segmentVOList, Long parentId) {
        List<SegmentListVO> children = new ArrayList<>();

        for (SegmentListVO segmentVO : segmentVOList) {
            if ((parentId == null && segmentVO.getParentId() == null)
                    || (parentId != null && parentId.longValue() == segmentVO.getParentId().longValue())) {
                List<SegmentListVO> childSegments = buildTree(segmentVOList, segmentVO.getId());
                segmentVO.setChildren(childSegments);
                children.add(segmentVO);
            }
        }

        return children;
    }
}
