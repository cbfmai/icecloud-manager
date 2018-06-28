package com.mmtech.icecloud.manager.exception;

import com.mmtech.icecloud.manager.common.HttpStatus;

/**
 * @author Adam DENG
 * @Date 2018/6/15 15:34
 */
public class BusinessException extends RuntimeException {

    private Integer code;
    private String message;

    public static BusinessException of(HttpStatus httpStatus) {
        return new BusinessException(httpStatus.getCode(), httpStatus.getMessage());
    }

    public BusinessException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public BusinessException(String message, Integer code, String message1) {
        super(message);
        this.code = code;
        this.message = message1;
    }

    public BusinessException(String message, Throwable cause, Integer code, String message1) {
        super(message, cause);
        this.code = code;
        this.message = message1;
    }

    public BusinessException(Throwable cause, Integer code, String message) {
        super(cause);
        this.code = code;
        this.message = message;
    }

    public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Integer code, String message1) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
        this.message = message1;
    }
}
