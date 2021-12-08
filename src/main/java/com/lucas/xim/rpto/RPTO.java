package com.lucas.xim.rpto;

import com.lucas.xim.core.ActionCode;
import lombok.Data;

/**
 */
@Data
public class RPTO<T> {
    private int code;
    private String message;
    private T data;

    public RPTO(String message){
        this(ActionCode.ERROR,message,null);
    }
    public RPTO(ActionCode action){
        this(action,null);
    }
    public RPTO(ActionCode action, T data){
        this(action.code,action.message,data);
    }
    public RPTO(ActionCode action, String message, T data){
        this(action.code,message,data);
    }
    public RPTO(int code, String message) {
        this(code,message,null);
    }
    public RPTO(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
