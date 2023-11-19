package com.ruoyi.customer.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 邮件导入
对象 customer_customer_import
 * 
 * @author tangJM.
 * @date 2023-11-19
 */
public class CustomerImport extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 导入类型 1.客户列表 2.公海客户 */
    @Excel(name = "导入类型 1.客户列表 2.公海客户")
    private Integer importType;

    /** 导入状态 1.进行中 2.成功 3.失败 */
    @Excel(name = "导入状态 1.进行中 2.成功 3.失败")
    private Integer importStatus;

    /** 预见导入数 */
    @Excel(name = "预见导入数")
    private Integer expectedImportCount;

    /** 成功导入数 */
    @Excel(name = "成功导入数")
    private Integer successImportCount;

    /** 导入失败数 */
    @Excel(name = "导入失败数")
    private Integer failedImportCount;

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
    public void setImportType(Integer importType) 
    {
        this.importType = importType;
    }

    public Integer getImportType() 
    {
        return importType;
    }
    public void setImportStatus(Integer importStatus) 
    {
        this.importStatus = importStatus;
    }

    public Integer getImportStatus() 
    {
        return importStatus;
    }
    public void setExpectedImportCount(Integer expectedImportCount) 
    {
        this.expectedImportCount = expectedImportCount;
    }

    public Integer getExpectedImportCount() 
    {
        return expectedImportCount;
    }
    public void setSuccessImportCount(Integer successImportCount) 
    {
        this.successImportCount = successImportCount;
    }

    public Integer getSuccessImportCount() 
    {
        return successImportCount;
    }
    public void setFailedImportCount(Integer failedImportCount) 
    {
        this.failedImportCount = failedImportCount;
    }

    public Integer getFailedImportCount() 
    {
        return failedImportCount;
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
            .append("importType", getImportType())
            .append("importStatus", getImportStatus())
            .append("expectedImportCount", getExpectedImportCount())
            .append("successImportCount", getSuccessImportCount())
            .append("failedImportCount", getFailedImportCount())
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
