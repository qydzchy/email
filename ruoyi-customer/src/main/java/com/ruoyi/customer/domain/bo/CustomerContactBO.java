package com.ruoyi.customer.domain.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class CustomerContactBO {

    /**
     * 客户联系人ID
     */
    private Long id;

    /**
     * 客户ID
     */
    private Long customerId;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 社交平台
     */
    private String socialPlatform;

    /**
     * 生日
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;
}
