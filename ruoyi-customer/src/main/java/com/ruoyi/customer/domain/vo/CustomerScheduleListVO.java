package com.ruoyi.customer.domain.vo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ruoyi.common.enums.customer.RecurringScheduleEnum;
import lombok.Data;

import java.util.Date;
import java.util.Optional;

@Data
public class CustomerScheduleListVO {

    private Long id;

    /**
     * 客户ID
     */
    private Long customerId;

    /**
     * 公司名称（关联对象）
     */
    private String companyName;

    /** 日程内容 */
    private String scheduleContent;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 参与人
     */
    private String participants;

    /** 周期性日程 1.不设置 2.每天 3.每周 4.每月 5.自定义 */
    private Integer recurringSchedule;

    /** 日程开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date scheduleStartTime;

    /** 日程结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date scheduleEndTime;

    /** 全天 false.否 true.是 */
    private Boolean allDayFlag;

    /**
     * 备注
     */
    private String remark;

    /**
     * 提醒时间
     */
    @JsonIgnore
    private String reminderTime;

    /**
     * 重点关注 false否 true是
     */
    private Boolean focusFlag;

    /**
     * 重复
     * @return
     */
    public String getRepetition() {
        if (this.recurringSchedule.intValue() == RecurringScheduleEnum.NOT_SET.getType()) {
            return "否";
        }

        return "是";
    }

    /**
     * 全天
     * @return
     */
    public String getAllDayName() {
        if (Optional.ofNullable(this.allDayFlag).orElse(false)) {
            return "是";
        }

        return "否";
    }

    /**
     * 对象类型
     */
    public String getObjectType() {
        return "对象类型";
    }

    /**
     * 关联邮件
     */
    public String getRelatedEmail() {
        return "--";
    }

    public String getReminderTime() {
        if (this.reminderTime != null) {
            try {
                JSONArray jsonA = JSONObject.parseArray(this.reminderTime);

                JSONObject jsonO = (JSONObject) jsonA.get(0);
                return jsonO.getString("reminderTimeValue");
            } catch (Exception e) {}
        }

        return null;
    }
}
