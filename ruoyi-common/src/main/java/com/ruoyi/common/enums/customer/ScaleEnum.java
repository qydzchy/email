package com.ruoyi.common.enums.customer;

import com.ruoyi.common.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ScaleEnum {
    // 规模 1.少于59人 2.60-149人 3.150-499人 4.500-999人 5.1000-4999人 6.5000人以上

    LESS_THAN_59(1, "少于59人"),
    BETWEEN_60_AND_149(2, "60-149人"),
    BETWEEN_150_AND_499(3, "150-499人"),
    BETWEEN_500_AND_999(4, "500-999人"),
    BETWEEN_1000_AND_4999(5, "1000-4999人"),
    MORE_THAN_5000(6, "5000人以上");

    private Integer code;
    private String desc;

    public static ScaleEnum getScaleEnum(String scaleStr) {
        if (StringUtils.isBlank(scaleStr)) {
            return null;
        }
        for (ScaleEnum scaleEnum : ScaleEnum.values()) {
            if (scaleEnum.getDesc().equals(scaleStr)) {
                return scaleEnum;
            }
        }

        return null;
    }
}
