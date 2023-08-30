package com.ruoyi.email.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.email.domain.TaskEmailSend;
import com.ruoyi.email.domain.dto.email.EmailSendSaveDTO;
import org.springframework.web.multipart.MultipartFile;

/**
 * 发送邮件Service接口
 * 
 * @author tangJM
 * @date 2023-07-31
 */
public interface ITaskEmailSendService 
{
    /**
     * 查询发送邮件
     * 
     * @param id 发送邮件主键
     * @return 发送邮件
     */
    public TaskEmailSend selectTaskEmailSendById(Long id);

    /**
     * 查询发送邮件列表
     * 
     * @param taskEmailSend 发送邮件
     * @return 发送邮件集合
     */
    public List<TaskEmailSend> selectTaskEmailSendList(TaskEmailSend taskEmailSend);

    /**
     * 新增发送邮件
     * 
     * @param taskEmailSend 发送邮件
     * @return 结果
     */
    public int insertTaskEmailSend(TaskEmailSend taskEmailSend);

    /**
     * 修改发送邮件
     * 
     * @param taskEmailSend 发送邮件
     * @return 结果
     */
    public int updateTaskEmailSend(TaskEmailSend taskEmailSend);

    /**
     * 批量删除发送邮件
     * 
     * @param ids 需要删除的发送邮件主键集合
     * @return 结果
     */
    public int deleteTaskEmailSendByIds(Long[] ids);

    /**
     * 删除发送邮件信息
     * 
     * @param id 发送邮件主键
     * @return 结果
     */
    public int deleteTaskEmailSendById(Long id);

    /**
     * 邮件保存-（写信）
     * @param dto
     * @return
     */
    Long save(EmailSendSaveDTO dto);

    /**
     * 邮件发送-（写信）
     * @param id
     * @return
     */
    boolean send(Long id);

    /**
     * 上传附件
     * @param files
     * @return
     */
    boolean uploadAttachments(MultipartFile[] files);
}
