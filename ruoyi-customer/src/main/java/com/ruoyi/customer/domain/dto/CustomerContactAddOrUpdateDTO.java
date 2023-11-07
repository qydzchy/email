package com.ruoyi.customer.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class CustomerContactAddOrUpdateDTO {

    private Long id;

    /** 昵称 */
    private String nickName;

    /** 邮箱 */
    private String email;

    /** 社交平台
     [{"type":1,"account":""}]
     */
    private String socialPlatform;

    /** 联系电话
     [{"phone_prefix":"","phone":""}]
     */
    private String phone;

    /** 职级 1.普通职员 2.中层管理者 3.高层管理者 */
    private Integer rank;

    /** 职位 */
    private String position;

    /** 生日 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    /** 性别 1.不设置 2.男 3.女 */
    private Integer sex;

    /** 头像/名片 */
    private String avatarOrBusinessCard;

    /** 联系人备注 */
    private String contactRemarks;

    /** 是否为主要联系人 true.是 false.否 */
    private Boolean primaryContactFlag;
}
