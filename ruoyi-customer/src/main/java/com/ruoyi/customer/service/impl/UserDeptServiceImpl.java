package com.ruoyi.customer.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.customer.domain.bo.SegmentVisibilityScopeBO;
import com.ruoyi.customer.domain.bo.SegmentVisibilityScopeDeptBO;
import com.ruoyi.customer.domain.bo.SegmentVisibilityScopeUserBO;
import com.ruoyi.customer.domain.bo.UserDeptInfoBO;
import com.ruoyi.customer.domain.vo.SubgroupColumnListVO;
import com.ruoyi.customer.mapper.CustomerMapper;
import com.ruoyi.customer.service.IUserDeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDeptServiceImpl implements IUserDeptService {

    @Resource
    private CustomerMapper customerMapper;

    /**
     * 用户部门校验
     * @param userDeptInfoList
     * @param content
     * @return
     */
    @Override
    public boolean userDeptVerify(List<UserDeptInfoBO> userDeptInfoList, String content) {
        if (StringUtils.isBlank(content)) return false;

        ObjectMapper objectMapper = new ObjectMapper();
        // 将 JSON 字符串转换为 Java 对象
        try {
            SegmentVisibilityScopeBO segmentVisibilityScopeBO = objectMapper.readValue(content, SegmentVisibilityScopeBO.class);
            SegmentVisibilityScopeDeptBO dept = segmentVisibilityScopeBO.getDept();
            SegmentVisibilityScopeUserBO user = segmentVisibilityScopeBO.getUser();
            // 用户维度判断可见范围是否成立
            if (user.getAllFlag()) {
                return true;
            } else {
                List<Long> userIds = user.getUserIds();
                if (!userIds.isEmpty()) {
                    List<Long> followUpPersonnelUserIds = userDeptInfoList.stream().map(UserDeptInfoBO::getUserId).collect(Collectors.toList());

                    boolean userContainsAny = containsAnyElement(userIds, followUpPersonnelUserIds);
                    if (userContainsAny) return true;
                }
            }

            // 部门维度判断可见范围是否成立
            if (dept.getAllFlag()) {
                // 存在一个部门，全选部门，条件成立
                long count = userDeptInfoList.stream().map(UserDeptInfoBO::getDeptId).count();
                if (count > 0) return true;

            } else {
                List<Long> deptIds = dept.getDeptIds();
                if (!deptIds.isEmpty()) {
                    List<Long> followUpPersonnelDeptIds = userDeptInfoList.stream().map(UserDeptInfoBO::getDeptId).collect(Collectors.toList());

                    boolean deptContainsAny = containsAnyElement(deptIds, followUpPersonnelDeptIds);
                    if (deptContainsAny) return true;
                }
            }
        } catch (Exception e) {
            return false;
        }

        return false;
    }

    /**
     * 根据
     * @param userId
     * @return
     */
    @Override
    public Long getDeptIdByUserId(Long userId) {
        return customerMapper.getDeptIdByUserId(userId);
    }

    public boolean containsAnyElement(List<Long> a, List<Long> b) {
        for (Long element : b) {
            if (a.contains(element)) {
                return true;
            }
        }
        return false;
    }
}
