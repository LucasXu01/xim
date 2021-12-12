package com.lucas.xim.core;

public enum ActionCode {
    ERROR(400,"请求失败"),
    SUCCESS(200,"请求成功"),
    EXCEPTION(500,"服务器异常"),
    VALID(401,"参数校验异常"),

    PARAMS_IS_EMPTY(1001, "参数为空"),
    PHONE_IS_EMPTY(1002, "手机号为空"),
    PHONE_IS_REGISTERD(1003, "手机号已被注册"),
    ILLEGAL_PHONE(1004, "手机号不合法"),
    VERIFICATION_CODE_IS_EMPTY(1005, "验证码为空"),
    VERIFICATION_CODE_IS_INCORRECT(1006, "验证码不正确"),
    TOKEN_IS_EMPTY(2001, "token为空"),
    USER_DOES_NOT_EXIST(2002, "用户不存在"),
    TOKEN_EXPIRED(2003, "token已过期，请重新登录"),
    TOKEN_INVALID(2004, "token无效，请重新登录"),
    GET_OSS_CREDENTIALS_FAILED(3001, "获取oss credentials失败");

    public int code;
    public String message;


    ActionCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}