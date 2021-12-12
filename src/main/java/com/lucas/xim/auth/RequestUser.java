package com.lucas.xim.auth;

/**
 * @Description: Token验证注解，需要验证token的接口加上此注解即可
 * @Auther: LucasXu
 * @email: 18140041@bjtu.edu.cn
 * @github: https://github.com/LucasXu01
 * @Date: 2021/12/09/14:45 下午
 */
public class RequestUser {

    private String userId;
    private String token;

    public static final String KEY_REQUEST_USER = "key_request_user";

    public RequestUser(String userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return "RequestUser{" +
                "userId=" + userId +
                ", token='" + token + '\'' +
                '}';
    }
}
