package com.ruoyi.email.service.impl;

import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;

import com.google.gson.Gson;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.enums.email.TransceiverRuleColumnNameEnum;
import com.ruoyi.common.enums.email.TransceiverRuleConditionTypeEnum;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.email.domain.Folder;
import com.ruoyi.email.domain.bo.ExecuteConditionContentBO;
import com.ruoyi.email.domain.vo.TransceiverRuleListVO;
import com.ruoyi.email.domain.vo.TransceiverRuleVO;
import com.ruoyi.email.mapper.FolderMapper;
import com.ruoyi.email.service.ITaskEmailService;
import com.ruoyi.email.service.ITransceiverRuleService;
import com.ruoyi.email.util.ThreadPoolTransceiverRuleService;
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
    private ITaskEmailService taskEmailService;
    @Resource
    private TransceiverRuleMapper transceiverRuleMapper;
    @Resource
    private FolderMapper folderMapper;

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
    public boolean insertTransceiverRule(TransceiverRule transceiverRule)
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
        transceiverRuleMapper.insertTransceiverRule(transceiverRule);

        // 是否应用于历史邮件
        applyToHistoryMail(transceiverRule);
        return true;
    }

    /**
     * 修改收发件规则
     * 
     * @param transceiverRule 收发件规则
     * @return 结果
     */
    @Override
    public boolean updateTransceiverRule(TransceiverRule transceiverRule)
    {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();
        Date now = DateUtils.getNowDate();

        transceiverRule.setUpdateId(userId);
        transceiverRule.setUpdateBy(username);
        transceiverRule.setUpdateTime(now);
        transceiverRuleMapper.updateTransceiverRule(transceiverRule);

        // 是否应用于历史邮件
        applyToHistoryMail(transceiverRule);
        return true;
    }

    /**
     * 是否应用于历史邮件（异步）
     */
    private void applyToHistoryMail(TransceiverRule transceiverRule) {
        // 状态 1.开启 0.关闭
        if (transceiverRule.getStatus().intValue() != 1) {
            return;
        }

        ThreadPoolExecutor instance = ThreadPoolTransceiverRuleService.getInstance();
        instance.execute(() -> {
            // 应用于历史邮件: 0.否 1.是
            if (!Optional.ofNullable(transceiverRule.getApplyToHistoryMailFlag()).orElse(false)) {
                return;
            }

            // 应用于历史邮件类型：1.针对收件箱的历史邮件 2.针对收件箱及所有文件夹的历史邮件（不包括已删除
            Integer applyToHistoryMailTrueType = transceiverRule.getApplyToHistoryMailTrueType();

            if (applyToHistoryMailTrueType.equals(1)) {
                // 针对收件箱的历史邮件
                taskEmailService.applyToHistoryMailForInbox(transceiverRule);
            } else if (applyToHistoryMailTrueType.equals(2)) {
                // 针对收件箱及所有文件夹的历史邮件（不包括已删除）
                taskEmailService.applyToHistoryMailForInboxAndAllFolder(transceiverRule);
            }
        });
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

        List<TransceiverRuleVO> transceiverRuleVOList = transceiverRuleMapper.list(userId);
        List<TransceiverRuleListVO> transceiverRuleListVOList = new ArrayList<>();
        for (TransceiverRuleVO transceiverRuleVO : transceiverRuleVOList) {
            TransceiverRuleListVO transceiverRuleListVO = new TransceiverRuleListVO();
            transceiverRuleListVO.setId(transceiverRuleVO.getId());
            transceiverRuleListVO.setRuleType(transceiverRuleVO.getRuleType());
            transceiverRuleListVO.setRuleName(transceiverRuleVO.getRuleName());
            transceiverRuleListVO.setStatus(transceiverRuleVO.getStatus());
            String executionRule = getExecutionRule(transceiverRuleVO, userId);
            transceiverRuleListVO.setExecutionRule(executionRule);
            transceiverRuleListVOList.add(transceiverRuleListVO);
        }

        return transceiverRuleListVOList;
    }


    /**
     * 获取执行规则
     */
    public String getExecutionRule(TransceiverRuleVO transceiverRuleVO, Long createId) {
        StringBuilder conditionContent = new StringBuilder();
        String executeConditionName = transceiverRuleVO.getExecuteCondition().intValue() == 1 ? "满足以下所有条件" : "满足以下任一条件";

        conditionContent.append(executeConditionName).append("：");

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

                        String packetIdStr = executeConditionContentBO.getPacketId();
                        if (StringUtils.isNotBlank(packetIdStr)) {
                            Long packetId = Long.parseLong(packetIdStr);
                            if (packetId != null) {
                                // 查询客户分组名称
                                String packetName = transceiverRuleMapper.getPacketNameByPacketId(packetId, createId);
                                value += packetName;
                            }
                        }

                    } else if (value.equals("2")) {
                        value = "公海";
                    }
                }

                conditionContent.append(columnAlias).append(" ").append(conditionAlias).append(value);
                if (index < executeConditionContentBOList.size() - 1) {
                    conditionContent.append("、");
                }
            }
        } catch (Exception e) {
            log.error("执行条件内容转换异常 ID：{}" +
                    "\n原因：{}", transceiverRuleVO.getId(), e);
        }

        conditionContent.append("，则：");

        if (Optional.ofNullable(transceiverRuleVO.getFolderFlag()).orElse(false) && transceiverRuleVO.getFolderId() != null) {
            Long folderId = transceiverRuleVO.getFolderId();
            Folder folder = folderMapper.getById(folderId, createId);
            if (folder != null) {
                conditionContent.append("移动到").append(folder.getName()).append("、");
            }
        }

        if (Optional.ofNullable(transceiverRuleVO.getLabelFlag()).orElse(false)) {
            conditionContent.append("标记为、");
        }

        if (Optional.ofNullable(transceiverRuleVO.getReadFlag()).orElse(false)) {
            conditionContent.append("标记为已读、");
        }

        if (Optional.ofNullable(transceiverRuleVO.getFixedFlag()).orElse(false)) {
            conditionContent.append("打图钉、");
        }

        if (Optional.ofNullable(transceiverRuleVO.getAutoResponseFlag()).orElse(false)) {
            conditionContent.append("自动回复、");
        }

        if (Optional.ofNullable(transceiverRuleVO.getPendingFlag()).orElse(false)) {
            conditionContent.append("标记为待处理");

            if (transceiverRuleVO.getPendingType() != null) {
                conditionContent.append("并设置稍后处理时间");
            }
        }

        return conditionContent.toString();
    }

    /**
     * 更新收发件规则状态
     * @param id
     * @return
     */
    @Override
    public boolean updateStatus(Long id) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        Long userId = loginUser.getUserId();
        String username = loginUser.getUsername();
        TransceiverRule transceiverRule = transceiverRuleMapper.selectTransceiverRuleById(id);
        if (transceiverRule == null) {
            throw new ServiceException("收发件规则不存在");
        }

        Integer status = transceiverRule.getStatus();
        Integer newStatus = status.intValue() == 1 ? 0 : 1;
        transceiverRule.setStatus(newStatus);
        transceiverRule.setUpdateId(userId);
        transceiverRule.setUpdateBy(username);
        transceiverRule.setUpdateTime(DateUtils.getNowDate());
        transceiverRuleMapper.updateTransceiverRule(transceiverRule);

        // 是否应用于历史邮件
        applyToHistoryMail(transceiverRule);
        return true;
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
