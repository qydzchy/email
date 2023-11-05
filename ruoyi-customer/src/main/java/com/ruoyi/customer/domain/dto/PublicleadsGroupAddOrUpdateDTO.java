package com.ruoyi.customer.domain.dto;

import com.ruoyi.common.annotation.Excel;
import lombok.Data;

@Data
public class PublicleadsGroupAddOrUpdateDTO {

    /** 主键 */
    private Long id;

    /** 分组名称 */
    @Excel(name = "分组名称")
    private String name;

    /** 分组成员ID（逗号拼接） */
    @Excel(name = "分组成员ID", readConverterExp = "逗号拼接")
    private String userIds;
}
