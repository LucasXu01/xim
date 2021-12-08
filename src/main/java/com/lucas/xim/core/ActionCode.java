package com.lucas.xim.core;

public enum ActionCode {
    ERROR(400,"请求失败"),
    SUCCESS(200,"请求成功"),
    EXCEPTION(500,"服务器异常"),
    VALID(401,"参数校验异常");

    public int code;
    public String message;


    ActionCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}