package com.ruoyi.email.service;

import java.nio.file.Path;
import java.util.List;
import com.ruoyi.email.domain.TaskAttachment;
import com.ruoyi.email.domain.bo.EmailAttachmentBO;
import com.ruoyi.email.domain.vo.attachment.AttachmentListVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * 邮件附件Service接口
 * 
 * @author tangJM
 * @date 2023-07-31
 */
public interface ITaskAttachmentService
{
    /**
     * 查询邮件附件
     * 
     * @param id 邮件附件主键
     * @return 邮件附件
     */
    public TaskAttachment selectTaskAttachmentById(Long id);

    /**
     * 查询邮件附件列表
     * 
     * @param taskAttachment 邮件附件
     * @return 邮件附件集合
     */
    public List<TaskAttachment> selectTaskAttachmentList(TaskAttachment taskAttachment);

    /**
     * 新增邮件附件
     * 
     * @param taskAttachment 邮件附件
     * @return 结果
     */
    public int insertTaskAttachment(TaskAttachment taskAttachment);

    /**
     * 修改邮件附件
     * 
     * @param taskAttachment 邮件附件
     * @return 结果
     */
    public int updateTaskAttachment(TaskAttachment taskAttachment);

    /**
     * 批量删除邮件附件
     * 
     * @param ids 需要删除的邮件附件主键集合
     * @return 结果
     */
    public int deleteTaskAttachmentByIds(Long[] ids);

    /**
     * 删除邮件附件信息
     * 
     * @param id 邮件附件主键
     * @return 结果
     */
    public int deleteTaskAttachmentById(Long id);

    /**
     * 批量新增
     * @param emailAttachments
     */
    void batchInsertTaskAttachment(List<TaskAttachment> emailAttachments);

    /**
     * @param files
     * @return
     */
    List<AttachmentListVO> uploadAttachments(MultipartFile[] files);

    /**
     * 重命名
     * @param id
     * @param name
     * @return
     */
    boolean rename(Long id, String name);

    /**
     * 删除
     * @param id
     * @return
     */
    boolean delete(Long id);

    /**
     * 根据邮件ID查询附件
     * @param emailIds
     * @return
     */
    List<EmailAttachmentBO> listByEmailIds(List<Long> emailIds);

    /**
     * 文件上传
     * @param sourceFile
     * @return
     */
    List<TaskAttachment> uploadAttachment(Path sourceFile);

    /**
     * 查询邮件的附件路径
     * @param emailId
     * @return
     */
    List<String> getPathByEmailId(Long emailId);
}
