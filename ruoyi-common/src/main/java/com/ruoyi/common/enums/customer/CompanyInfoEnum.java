package com.ruoyi.common.enums.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum CompanyInfoEnum {

    COMPANY_NAME("company_name", "公司名称"),
    SHORT_NAME("short_name", "简称"),
    CUSTOMER_NO("customer_no", "客户编号"),
    CUSTOMER_TAG("customer_tag", "客户标签"),
    COUNTRY_REGION("country_region", "国家地区"),
    TIMEZONE("timezone", "时区"),
    CUSTOMER_STAGE("customer_stage", "客户阶段"),
    CUSTOMER_SOURCE("customer_source", "客户来源"),
    PACKET("customer_packet", "分组"),
    PUBLICLEADS_GROUPS("publicleads_groups", "公海分组"),
    CUSTOMER_RATING("customer_rating", "客户星级"),
    SCALE("scale", "规模"),
    COMPANY_WEBSITE("company_website", "公司网址"),
    FAX("fax", "传真"),
    PHONE("phone", "电话"),
    ADDRESS("address", "地址"),
    COMPANY_REMARKS("company_remarks", "公司备注"),
    FOLLOW_UP_PERSONNEL("follow_up_personnel", "跟进人"),
    SOURCE_FOLLOW_UP_PERSONNEL("source_follow_up_personnel", "源跟进人"),
    CREATOR("creator", "创建人"),
    LAST_MODIFIED_BY("last_modified_by", "最近修改人"),
    FOLLOW_UP_NUMBER("follow_up_number", "跟进人数"),
    ENTER_PUBLICLEADS_COUNT("enter_publicleads_count", "进入公海次数");

    private String columnName;
    private String nickName;
}
