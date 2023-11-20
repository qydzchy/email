package com.ruoyi.customer.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.customer.*;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.common.utils.customer.CustomerShuffleThreadPoolUtil;
import com.ruoyi.customer.domain.bo.UserDeptInfoBO;
import com.ruoyi.customer.domain.dto.SegmentAddOrUpdateDTO;
import com.ruoyi.customer.domain.vo.CustomerSegmentListVO;
import com.ruoyi.customer.domain.vo.SegmentListVO;
import com.ruoyi.customer.domain.vo.SegmentUserListVO;
import com.ruoyi.customer.service.ICustomerService;
import com.ruoyi.customer.service.IUserDeptService;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.SegmentMapper;
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
        List<SegmentAddOrUpdateDTO> subGroupList = segmentAddOrUpdateDTO.getChildren();
        if (subGroupList != null && !subGroupList.isEmpty()) {
            List<Segment> segmentList = new ArrayList<>();
            subGroupList.stream().forEach(subGroup -> {
                Segment subSegment = new Segment();
                BeanUtils.copyProperties(subGroup, subSegment);
                subSegment.setParentId(id);
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
        // 生成基础客群
        List<SegmentListVO> allSegmentVOList = new ArrayList<>();
        allSegmentVOList.addAll(initBasicSegment());

        List<SegmentListVO> segmentVOList = segmentMapper.list(createId);
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
        Map<Long, Integer> segmentCustomerCountMap = segmentMapper.selectSegmentCustomerCountByUserId(userId);

        List<CustomerSegmentListVO> customerSegmentVOList = new ArrayList<>();
        for (Segment segment : segmentList) {
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
