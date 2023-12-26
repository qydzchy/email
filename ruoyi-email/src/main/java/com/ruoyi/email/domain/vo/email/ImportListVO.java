package com.ruoyi.email.domain.vo.email;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.enums.email.ImportStatusEnum;
import lombok.Data;

import java.util.Date;

/**
 * 导入邮件列表
 */
@Data
public class ImportListVO {

    /** 主键 */
    private Long id;

    /** 文件名 */
    private String fileName;

    /** 任务ID */
    private Long taskId;

    /** 任务名称 */
    private String taskName;

    /** 文件夹ID */
    private Long folderId;

    /** 文件夹名称 */
    private String folderName;

    /** 导入状态: 1.正在处理 2.导入成功 3.导入失败 */
    private Integer importStatus;

    /** 失败原因 */
    private String failureReasons;

    /** 邮件总数 */
    private Long mailTotal;

    /** 新增数 */
    private Integer addedCount;

    /** 重复数 */
    private Integer duplicateCount;

    /** 过滤数 */
    private Integer filteredCount;

    /** 失败数 */
    private Integer failureCount;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public String getImportStatusName() {
        return ImportStatusEnum.getByStatus(this.importStatus);
    }
}
