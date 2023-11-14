package com.ruoyi.customer.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.customer.domain.bo.CustomerRecentActivityBO;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class PrivateleadsCustomerSimpleListVO {

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
     * 主要联系人
     */
    private String primaryContact;

    /**
     * 最近动态
     */
    private CustomerRecentActivityBO recentActivity;

    /**
     * 最近联系时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastContactedAt;

    /**
     * 国家地区
     */
    private String countryRegion;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 客户编号
     * @return
     */
    private String customerNo;

    /**
     * 简称
     */
    private String shortName;

    /**
     * 关注 0.未关注 1.已关注
     * @return
     */
    private Integer focusFlag;

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
