package com.ruoyi.common.enums.email;

/**
 * 标签类型
 */
public enum LabelTypeEnum {

    // 1.系统标签 2.自定义标签
    SYSTEM(1), CUSTOM(2);

    private int type;

    LabelTypeEnum(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
