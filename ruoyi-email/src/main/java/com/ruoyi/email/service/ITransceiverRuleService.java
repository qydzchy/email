package com.ruoyi.email.service;

import java.util.List;
import com.ruoyi.email.domain.TransceiverRule;
import com.ruoyi.email.domain.vo.TransceiverRuleListVO;
import com.ruoyi.email.domain.vo.TransceiverRuleVO;

/**
 * 收发件规则Service接口
 * 
 * @author tangJM
 * @date 2023-07-31
 */
public interface ITransceiverRuleService 
{
    /**
     * 查询收发件规则
     * 
     * @param id 收发件规则主键
     * @return 收发件规则
     */
    public TransceiverRule selectTransceiverRuleById(Long id);

    /**
     * 查询收发件规则列表
     * 
     * @param transceiverRule 收发件规则
     * @return 收发件规则集合
     */
    public List<TransceiverRule> selectTransceiverRuleList(TransceiverRule transceiverRule);

    /**
     * 新增收发件规则
     * 
     * @param transceiverRule 收发件规则
     * @return 结果
     */
    public int insertTransceiverRule(TransceiverRule transceiverRule);

    /**
     * 修改收发件规则
     * 
     * @param transceiverRule 收发件规则
     * @return 结果
     */
    public int updateTransceiverRule(TransceiverRule transceiverRule);

    /**
     * 批量删除收发件规则
     * 
     * @param ids 需要删除的收发件规则主键集合
     * @return 结果
     */
    public int deleteTransceiverRuleByIds(Long[] ids);

    /**
     * 删除收发件规则信息
     * 
     * @param id 收发件规则主键
     * @return 结果
     */
    public int deleteTransceiverRuleById(Long id);

    /**
     * 收发件规则列表
     * @return
     */
    List<TransceiverRuleListVO> list();

    /**
     * 更新收发件规则状态
     * @param id
     * @return
     */
    int updateStatus(Long id);

    /**
     * 根据ID查询收发件规则详情
     * @param id
     * @return
     */
    TransceiverRuleVO getById(Long id);
}
