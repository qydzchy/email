package com.ruoyi.email.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 导入邮件对象 mailbox_import_email
 *
 * @author tangJM
 * @date 2023-12-26
 */
public class ImportEmail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 文件名 */
    @Excel(name = "文件名")
    private String fileName;

    /** 任务ID */
    @Excel(name = "任务ID")
    private Long taskId;

    /** 任务名称 */
    @Excel(name = "任务名称")
    private String taskName;

    /** 文件夹ID */
    @Excel(name = "文件夹ID")
    private Long folderId;

    /** 文件夹名称 */
    @Excel(name = "文件夹名称")
    private String folderName;

    /** 导入状态: 1.正在处理 2.导入成功 3.导入失败 */
    @Excel(name = "导入状态: 1.正在处理 2.导入成功 3.导入失败")
    private Integer importStatus;

    /** 失败原因 */
    @Excel(name = "失败原因")
    private String failureReasons;

    /** 邮件总数 */
    @Excel(name = "邮件总数")
    private Integer mailTotal;

    /** 新增数 */
    @Excel(name = "新增数")
    private Integer addedCount;

    /** 重复数 */
    @Excel(name = "重复数")
    private Integer duplicateCount;

    /** 过滤数 */
    @Excel(name = "过滤数")
    private Integer filteredCount;

    /** 失败数 */
    @Excel(name = "失败数")
    private Integer failureCount;

    /** 删除标志(0代表存在2代表删除) */
    private String delFlag;

    /** 创建者ID */
    @Excel(name = "创建者ID")
    private Long createId;

    /** 更新者ID */
    @Excel(name = "更新者ID")
    private Long updateId;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getFileName()
    {
        return fileName;
    }
    public void setTaskId(Long taskId)
    {
        this.taskId = taskId;
    }

    public Long getTaskId()
    {
        return taskId;
    }
    public void setTaskName(String taskName)
    {
        this.taskName = taskName;
    }

    public String getTaskName()
    {
        return taskName;
    }
    public void setFolderId(Long folderId)
    {
        this.folderId = folderId;
    }

    public Long getFolderId()
    {
        return folderId;
    }
    public void setFolderName(String folderName)
    {
        this.folderName = folderName;
    }

    public String getFolderName()
    {
        return folderName;
    }
    public void setImportStatus(Integer importStatus)
    {
        this.importStatus = importStatus;
    }

    public Integer getImportStatus()
    {
        return importStatus;
    }
    public void setFailureReasons(String failureReasons)
    {
        this.failureReasons = failureReasons;
    }

    public String getFailureReasons()
    {
        return failureReasons;
    }

    public Integer getMailTotal() {
        return mailTotal;
    }

    public void setMailTotal(Integer mailTotal) {
        this.mailTotal = mailTotal;
    }

    public Integer getAddedCount() {
        return addedCount;
    }

    public void setAddedCount(Integer addedCount) {
        this.addedCount = addedCount;
    }

    public Integer getDuplicateCount() {
        return duplicateCount;
    }

    public void setDuplicateCount(Integer duplicateCount) {
        this.duplicateCount = duplicateCount;
    }

    public Integer getFilteredCount() {
        return filteredCount;
    }

    public void setFilteredCount(Integer filteredCount) {
        this.filteredCount = filteredCount;
    }

    public Integer getFailureCount() {
        return failureCount;
    }

    public void setFailureCount(Integer failureCount) {
        this.failureCount = failureCount;
    }

    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag()
    {
        return delFlag;
    }
    public void setCreateId(Long createId)
    {
        this.createId = createId;
    }

    public Long getCreateId()
    {
        return createId;
    }
    public void setUpdateId(Long updateId)
    {
        this.updateId = updateId;
    }

    public Long getUpdateId()
    {
        return updateId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("fileName", getFileName())
                .append("taskId", getTaskId())
                .append("taskName", getTaskName())
                .append("folderId", getFolderId())
                .append("folderName", getFolderName())
                .append("importStatus", getImportStatus())
                .append("failureReasons", getFailureReasons())
                .append("mailTotal", getMailTotal())
                .append("addedCount", getAddedCount())
                .append("duplicateCount", getDuplicateCount())
                .append("filteredCount", getFilteredCount())
                .append("failureCount", getFailureCount())
                .append("delFlag", getDelFlag())
                .append("createId", getCreateId())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateId", getUpdateId())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
