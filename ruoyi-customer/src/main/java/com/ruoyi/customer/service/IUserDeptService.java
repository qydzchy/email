package com.ruoyi.customer.service;

import com.ruoyi.customer.domain.bo.UserDeptInfoBO;
import com.ruoyi.customer.domain.vo.SubgroupColumnListVO;

import java.util.List;

public interface IUserDeptService {

    /**
     * 用户部门校验
     * @param userDeptInfoList
     * @param content
     * @return
     */

    boolean userDeptVerify(List<UserDeptInfoBO> userDeptInfoList, String content);

    /**
     * 跟进用户ID查询部门ID
     * @param userId
     * @return
     */
    Long getDeptIdByUserId(Long userId);
}
