package com.mouse.bms.customer.biz.enums;

import lombok.Getter;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : TagSourceEnum
 * @date : 2019/3/28 14:32
 * @description :
 */
@Getter
public enum TagSourceEnum {

    MANUAL_TAG(0,"手动打标"),
    AUTOMATION_TAG(1,"自动打标");

    private final int value;
    private final String message;

    TagSourceEnum(int value, String message) {
        this.value = value;
        this.message = message;
    }

    public static TagSourceEnum sourceOf(int value) {
        for (TagSourceEnum name : TagSourceEnum.values()) {
            if (value == name.getValue()) {
                return name;
            }
        }
        return null;
    }

}
