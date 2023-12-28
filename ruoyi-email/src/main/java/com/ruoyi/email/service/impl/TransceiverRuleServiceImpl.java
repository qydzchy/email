package com.ruoyi.email.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.google.gson.Gson;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.email.TransceiverRuleColumnNameEnum;
import com.ruoyi.common.enums.email.TransceiverRuleConditionTypeEnum;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.email.domain.bo.ExecuteConditionContentBO;
import com.ruoyi.email.domain.vo.TransceiverRuleListVO;
import com.ruoyi.email.domain.vo.TransceiverRuleVO;
import com.ruoyi.email.service.ITransceiverRuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.ruoyi.email.mapper.TransceiverRuleMapper;
import com.ruoyi.email.domain.TransceiverRule;

import javax.annotation.Resource;

/**
 * 收发件规则Service业务层处理
 * 
 * @author tangJM
 * @date 2023-07-31
 */
@Slf4j
@Service
public class TransceiverRuleServiceImpl implements ITransceiverRuleService
{
    @Resource
    private TransceiverRuleMapper transceiverRuleMapper;

    /**
     * 查询收发件规则
     * 
     * @param id 收发件规则主键
     * @return 收发件规则
     */
    @Override
    public TransceiverRule selectTransceiverRuleById(Long id)
    {
        return transceiverRuleMapper.selectTransceiverRuleById(id);
    }

    /**
     * 查询收发件规则列表
     * 
     * @param transceiverRule 收发件规则
     * @return 收发件规则
     */
    @Override
    public List<TransceiverRule> selectTransceiverRuleList(TransceiverRule transceiverRule)
    {
        return transceiverRuleMapper.selectTransceiverRuleList(transceiverRule);
    }

    /**
     * 新增收发件规则
     * 
     * @param transceiverRule 收发件规则
     * @return 结果
     */
    @Override
    public int insertTransceiverRule(TransceiverRule transceiverRule)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();
        Date now = DateUtils.getNowDate();

        transceiverRule.setCreateId(userId);
        transceiverRule.setCreateBy(username);
        transceiverRule.setCreateTime(now);
        transceiverRule.setUpdateId(userId);
        transceiverRule.setUpdateBy(username);
        transceiverRule.setUpdateTime(now);
        return transceiverRuleMapper.insertTransceiverRule(transceiverRule);
    }

    /**
     * 修改收发件规则
     * 
     * @param transceiverRule 收发件规则
     * @return 结果
     */
    @Override
    public int updateTransceiverRule(TransceiverRule transceiverRule)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();
        Date now = DateUtils.getNowDate();

        transceiverRule.setUpdateId(userId);
        transceiverRule.setUpdateBy(username);
        transceiverRule.setUpdateTime(now);
        return transceiverRuleMapper.updateTransceiverRule(transceiverRule);
    }

    /**
     * 批量删除收发件规则
     * 
     * @param ids 需要删除的收发件规则主键
     * @return 结果
     */
    @Override
    public int deleteTransceiverRuleByIds(Long[] ids)
    {
        return transceiverRuleMapper.deleteTransceiverRuleByIds(ids);
    }

    /**
     * 删除收发件规则信息
     * 
     * @param id 收发件规则主键
     * @return 结果
     */
    @Override
    public int deleteTransceiverRuleById(Long id)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        return transceiverRuleMapper.deleteTransceiverRuleById(id, userId, username);
    }

    /**
     * 收发件规则列表
     * @return
     */
    @Override
    public List<TransceiverRuleListVO> list() {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();

        List<TransceiverRuleListVO> transceiverRuleVOList = transceiverRuleMapper.list(userId);
        for (TransceiverRuleListVO transceiverRuleVO : transceiverRuleVOList) {
            String executionRule = getExecutionRule(transceiverRuleVO, userId);
            transceiverRuleVO.setExecutionRule(executionRule);
        }
        return transceiverRuleVOList;
    }


    /**
     * 获取执行规则
     */
    public String getExecutionRule(TransceiverRuleListVO transceiverRuleVO, Long createId) {
        String executeConditionName = transceiverRuleVO.getExecuteCondition().intValue() == 1 ? "满足以下所有条件" : "满足以下任一条件";

        executeConditionName += "：";
        StringBuilder conditionContent = new StringBuilder();
        List<ExecuteConditionContentBO> executeConditionContentBOList = null;
        Gson gson = new Gson();
        try {
            executeConditionContentBOList = Arrays.asList(gson.fromJson(transceiverRuleVO.getExecuteConditionContent(), ExecuteConditionContentBO[].class));

            for (int index = 0; index < executeConditionContentBOList.size(); index ++) {
                ExecuteConditionContentBO executeConditionContentBO = executeConditionContentBOList.get(index);
                String columnName = executeConditionContentBO.getColumnName();
                TransceiverRuleColumnNameEnum transceiverRuleColumnNameEnum = TransceiverRuleColumnNameEnum.getByColumnName(columnName);
                String columnAlias = transceiverRuleColumnNameEnum.getColumnAlias();

                Integer conditionType = executeConditionContentBO.getConditionType();
                TransceiverRuleConditionTypeEnum transceiverRuleConditionTypeEnum = TransceiverRuleConditionTypeEnum.getByType(conditionType);
                String conditionAlias = transceiverRuleConditionTypeEnum.getDesc();

                String value = executeConditionContentBO.getValue();

                if (transceiverRuleConditionTypeEnum == TransceiverRuleConditionTypeEnum.BELONGS_TO) {
                    if (value.equals("1")) {
                        value = "私海";

                        Long packetId = executeConditionContentBO.getPacketId();
                        if (packetId != null) {
                            // 查询客户分组名称
                            String packetName = transceiverRuleMapper.getPacketNameByPacketId(packetId, createId);
                            value += packetName;
                        }
                    } else if (value.equals("2")) {
                        value = "公海";
                    }

                    conditionContent.append(columnAlias).append(" ").append(conditionAlias).append(value);
                    if (index < executeConditionContentBOList.size() - 1) {
                        conditionContent.append("、");
                    }
                }
            }
        } catch (Exception e) {
            log.error("执行条件内容转换异常 ID：{}" +
                    "\n原因：{}", transceiverRuleVO.getId(), e);
        }

        executeConditionName += "，则：";

        if (Optional.ofNullable(transceiverRuleVO.getFixedFlag()).orElse(false)) {
            executeConditionName += "打图钉";
        }

        if (Optional.ofNullable(transceiverRuleVO.getReadFlag()).orElse(false)) {
            executeConditionName += "标记为已读";
        }

        // todo 未完成

        return executeConditionName;
    }

    /**
     * 更新收发件规则状态
     * @param id
     * @return
     */
    @Override
    public int updateStatus(Long id) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();

        return transceiverRuleMapper.updateStatus(id, userId, username);
    }

    /**
     * 根据id查询收发件规则
     * @param id
     * @return
     */
    @Override
    public TransceiverRuleVO getById(Long id) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();

        return transceiverRuleMapper.getById(id, userId);
    }
}
