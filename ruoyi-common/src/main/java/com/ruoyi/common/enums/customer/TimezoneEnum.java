package com.ruoyi.common.enums.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum TimezoneEnum {

    // 0.零时区 伦敦 1.东一区 罗马，巴黎 2.东二区 雅典，以色列 3.东三区 莫斯科，科威特 4.东四区 喀布尔 5.东五区 伊斯兰堡，卡拉奇 6.东六区 阿拉木图，科伦坡 7.东七区 曼谷，雅加达 8.东八区 北京，香港，台湾 9.东九区 东京 10.东十区 悉尼 11.东十一区 霍尼亚拉，马加丹 12.东西十二区 奥克兰 13.西十一区 帕果帕果，阿洛菲 14.西十区 夏威夷 15.西九区 阿拉斯加 16.西八区 洛杉矶，旧金山 17.西七区 盐湖城，丹佛，凤凰城 18.西六区 芝加哥，休斯顿，亚特兰大 19.西五区 纽约，华盛顿，波士顿 20.西四区 加拿大，加拉加斯 21.西三区 巴西利亚 22.西二区 协调时间时 23.西一区 佛得角群岛
    ZERO("0", "零时区 伦敦"),
    EAST_ONE("1", "东一区 罗马，巴黎"),
    EAST_TWO("2", "东二区 雅典，以色列"),
    EAST_THREE("3", "东三区 莫斯科，科威特"),
    EAST_FOUR("4", "东四区 喀布尔"),
    EAST_FIVE("5", "东五区 伊斯兰堡，卡拉奇"),
    EAST_SIX("6", "东六区 阿拉木图，科伦坡"),
    EAST_SEVEN("7", "东七区 曼谷，雅加达"),
    EAST_EIGHT("8", "东八区 北京，香港，台湾"),
    EAST_NINE("9", "东九区 东京"),
    EAST_TEN("10", "东十区 悉尼"),
    EAST_ELEVEN("11", "东十一区 霍尼亚拉，马加丹"),
    EAST_TWELVE("12", "东西十二区 奥克兰"),
    WEST_ELEVEN("13", "西十一区 帕果帕果，阿洛菲"),
    WEST_TEN("14", "西十区 夏威夷"),
    WEST_NINE("15", "西九区 阿拉斯加"),
    WEST_EIGHT("16", "西八区 洛杉矶，旧金山"),
    WEST_SEVEN("17", "西七区 盐湖城，丹佛，凤凰城"),
    WEST_SIX("18", "西六区 芝加哥，休斯顿，亚特兰大"),
    WEST_FIVE("19", "西五区 纽约，华盛顿，波士顿"),
    WEST_FOUR("20", "西四区 加拿大，加拉加斯"),
    WEST_THREE("21", "西三区 巴西利亚"),
    WEST_TWO("22", "西二区 协调时间时"),
    WEST_ONE("23", "西一区 佛得角群岛");

    private String timezone;
    private String desc;

    public static TimezoneEnum getTimezoneEnum(String timezoneStr) {
        if (StringUtils.isBlank(timezoneStr)) {
            return null;
        }

        for (TimezoneEnum timezoneEnum : TimezoneEnum.values()) {
            if (timezoneEnum.getDesc().contains(timezoneStr)) {
                return timezoneEnum;
            }
        }

        return null;
    }
}
