package com.mouse.bms.customer.biz.enums;

import lombok.Getter;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : GenderEnum
 * @date : 2019/3/28 11:53
 * @description :
 */
@Getter
public enum GenderEnum {

    UNKNOWN(0, "未知"),
    MALE(1, "男"),
    FEMALE(2, "女");

    private final int value;
    private final String message;

    GenderEnum(int value, String message) {
        this.value = value;
        this.message = message;
    }

    public static GenderEnum sourceOf(int value) {
        for (GenderEnum name : GenderEnum.values()) {
            if (value == name.getValue()) {
                return name;
            }
        }
        return null;
    }

}
