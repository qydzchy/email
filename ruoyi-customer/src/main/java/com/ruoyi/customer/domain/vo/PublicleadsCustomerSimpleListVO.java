package com.ruoyi.customer.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.customer.domain.bo.CustomerContactBO;
import com.ruoyi.customer.domain.bo.CustomerRecentActivityBO;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class PublicleadsCustomerSimpleListVO {

    /**
     * 客户ID
     */
    private Long id;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 标签
     */
    @JsonIgnore
    private String tagStr;

    /**
     * 客户分组ID
     */
    private Long packetId;

    /**
     * 客户分组名称
     */
    private String packetName;

    /**
     * 客户阶段ID
     */
    private Long stageId;

    /**
     * 客户阶段名称
     */
    private String stageName;

    /**
     * 最近跟进
     */
    private CustomerRecentActivityBO recentFollowUp;

    /**
     * 最近动态
     */
    private CustomerRecentActivityBO recentActivity;

    /**
     * 原跟进人
     */
    private String originalFollowUp;

    /**
     * 国家地区
     */
    private String countryRegion;

    /**
     * 客户类型
     */
    private Integer customerType;

    /**
     * 客户评分
     */
    private Integer customerScore;

    /**
     * 最近联系时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastContactedAt;

    /**
     * 时区
     */
    private String timezone;

    /**
     * 联系人信息
     */
    private CustomerContactBO contact;

    /**
     * 关注 0.未关注 1.已关注
     * @return
     */
    private Integer focusFlag;

    public String getCustomerTypeName() {
        return null;
    }

    public List<TagListVO> getTagList() {
        if (StringUtils.isNotBlank(tagStr)) {
            String[] tagArr = tagStr.split(",");
            List<TagListVO> tagVOList = new ArrayList<>();
            for (String tag : tagArr) {
                String[] tagObj = tag.split("_");
                if (tagObj.length > 1) {
                    TagListVO tagVO = new TagListVO();
                    tagVO.setId(Long.parseLong(tagObj[0]));
                    tagVO.setName(tagObj[1]);
                    tagVOList.add(tagVO);
                }
            }
            return tagVOList;
        }

        return null;
    }
}
