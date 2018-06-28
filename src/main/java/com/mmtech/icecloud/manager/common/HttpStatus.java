package com.mmtech.icecloud.manager.common;

/**
 * @author Adam DENG
 * @Date 2018/6/15 11:00
 * <p>
 * <p>
 * CATEGORY	DESCRIPTION
 * 1xx: Informational	Communicates transfer protocol-level information.
 * 2xx: Success	Indicates that the client’s request was accepted successfully.
 * 3xx: Redirection	Indicates that the client must take some additional action in order to complete their request.
 * 4xx: Client Error	This category of error status codes points the finger at clients.
 * 5xx: Server Error	The server takes responsibility for these error status codes.
 */
public enum HttpStatus {

    INVALID_PARAM_ERROR(100, "参数错误"),

    SUCCESS(200, "成功"),

    UNAUTHORIZED(401, "未授权"),
    FORBIDDEN(403, "禁止访问"),

    INTERNAL_SERVER_ERROR(500, "服务器异常"),
    NETWORK_ANOMALY(501, "网络异常"),
    DB_UPDATE_FAIL(502, "数据跟新异常");


    private Integer code;
    private String message;

    HttpStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
