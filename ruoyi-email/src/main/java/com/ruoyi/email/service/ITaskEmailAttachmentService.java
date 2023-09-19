package com.ruoyi.email.service;

import java.nio.file.Path;
import java.util.List;
import com.ruoyi.email.domain.TaskEmailAttachment;
import com.ruoyi.email.domain.vo.attachment.AttachmentListVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * 邮件附件Service接口
 * 
 * @author tangJM
 * @date 2023-07-31
 */
public interface ITaskEmailAttachmentService 
{
    /**
     * 查询邮件附件
     * 
     * @param id 邮件附件主键
     * @return 邮件附件
     */
    public TaskEmailAttachment selectTaskEmailAttachmentById(Long id);

    /**
     * 查询邮件附件列表
     * 
     * @param taskEmailAttachment 邮件附件
     * @return 邮件附件集合
     */
    public List<TaskEmailAttachment> selectTaskEmailAttachmentList(TaskEmailAttachment taskEmailAttachment);

    /**
     * 新增邮件附件
     * 
     * @param taskEmailAttachment 邮件附件
     * @return 结果
     */
    public int insertTaskEmailAttachment(TaskEmailAttachment taskEmailAttachment);

    /**
     * 修改邮件附件
     * 
     * @param taskEmailAttachment 邮件附件
     * @return 结果
     */
    public int updateTaskEmailAttachment(TaskEmailAttachment taskEmailAttachment);

    /**
     * 批量删除邮件附件
     * 
     * @param ids 需要删除的邮件附件主键集合
     * @return 结果
     */
    public int deleteTaskEmailAttachmentByIds(Long[] ids);

    /**
     * 删除邮件附件信息
     * 
     * @param id 邮件附件主键
     * @return 结果
     */
    public int deleteTaskEmailAttachmentById(Long id);

    /**
     * 批量新增
     * @param emailAttachments
     */
    void batchInsertTaskEmailAttachment(List<TaskEmailAttachment> emailAttachments);

    /**
     * 根据id去更新emailId
     * @param emailId
     * @param ids
     */
    void updateEmailIdByIds(Long emailId, List<Long> ids);

    /**
     * 根据邮件ID获取附件
     * @param id
     * @return
     */
    List<TaskEmailAttachment> selectByEmailId(Long id);

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
     * 查询任务上传附件
     * @param emailId
     * @return
     */
    List<AttachmentListVO> list(Long emailId);

    /**
     * 根据邮件ID查询附件
     * @param emailIds
     * @return
     */
    List<TaskEmailAttachment> listByEmailIds(List<Long> emailIds);

    /**
     * 文件上传
     * @param sourceFile
     * @return
     */
    List<TaskEmailAttachment> uploadAttachment(Path sourceFile);
}
