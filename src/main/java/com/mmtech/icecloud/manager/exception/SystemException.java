package com.mmtech.icecloud.manager.exception;

import com.mmtech.icecloud.manager.common.HttpStatus;

/**
 * @author Adam DENG
 * @Date 2018/6/15 9:57
 */
public class SystemException extends RuntimeException {

    private Integer code;
    private String message;

    public SystemException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public SystemException(String message, Integer code, String message1) {
        super(message);
        this.code = code;
        this.message = message1;
    }

    public SystemException(String message, Throwable cause, Integer code, String message1) {
        super(message, cause);
        this.code = code;
        this.message = message1;
    }

    public SystemException(Throwable cause, Integer code, String message) {
        super(cause);
        this.code = code;
        this.message = message;
    }

    public SystemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Integer code, String message1) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
        this.message = message1;
    }

    public static SystemException of(HttpStatus httpStatus) {
        return new SystemException(httpStatus.getCode(), httpStatus.getMessage());
    }


}
