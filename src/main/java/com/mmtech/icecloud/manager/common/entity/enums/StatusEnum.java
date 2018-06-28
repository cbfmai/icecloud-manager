package com.mmtech.icecloud.manager.common.entity.enums;

/**
 * @author Adam DENG
 * @Date 2018/6/15 15:05
 */
public enum StatusEnum {

    ON(1), OFF(0), DEL(-1);

    private int code;

    StatusEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
