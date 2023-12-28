package com.ruoyi.email.mapper;

import java.util.List;
import com.ruoyi.email.domain.TransceiverRule;
import com.ruoyi.email.domain.vo.TransceiverRuleListVO;
import com.ruoyi.email.domain.vo.TransceiverRuleVO;
import org.apache.ibatis.annotations.Param;

/**
 * 收发件规则Mapper接口
 * 
 * @author tangJM
 * @date 2023-07-31
 */
public interface TransceiverRuleMapper 
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
     * 删除收发件规则
     * 
     * @param id 收发件规则主键
     * @return 结果
     */
    public int deleteTransceiverRuleById(@Param("id") Long id, @Param("updateId") Long updateId, @Param("updateBy") String updateBy);

    /**
     * 批量删除收发件规则
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTransceiverRuleByIds(Long[] ids);

    /**
     * 更新收发件规则状态
     * @param id
     * @param updateId
     * @param updateBy
     * @return
     */
    int updateStatus(@Param("id") Long id, @Param("updateId") Long updateId, @Param("updateBy") String updateBy);

    /**
     * 根据id查询收发件规则
     * @param id
     * @param createId
     * @return
     */
    TransceiverRuleVO getById(@Param("id") Long id, @Param("createId") Long createId);

    /**
     * 收发件规则列表
     * @param createId
     * @return
     */
    List<TransceiverRuleListVO> list(@Param("createId") Long createId);

    /**
     * 根据客户分组ID查询客户分组名称
     * @param packetId
     * @param createId
     * @return
     */
    String getPacketNameByPacketId(@Param("packetId") Long packetId, @Param("createId") Long createId);
}
