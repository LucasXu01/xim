package com.lucas.xim.config;

/**
 * @Description:
 * @Auther: LucasXu
 * @email: 18140041@bjtu.edu.cn
 * @github: https://github.com/LucasXu01
 * @Date: 2021/12/08/5:35 下午
 */
public class NetworkConfig {

    public static final String CHARACTER_ENCODING = "UTF-8";
    public static final String CONTENT_TYPE = "application/json;charset=utf-8";

    //接口
    public static final String USER_REGISTER = "/user/register";
    public static final String USER_LOGIN = "/user/login";


    public static final String FUNC_USER_GET_VERIFY_CODE = "/user/getVerifyCode.action";
    public static final String FUNC_USER_LOGIN = "/user/login.action";
    public static final String FUNC_USER_COMPLETE_INFO = "/user/completeInfo.action";

    public static final String FUNC_CONFIG_GET_OSS_CREDENTIALS = "/config/getOSSCredentials.action";

    public static final String PARAM_USER = "user";
    public static final String PARAM_USER_TOKEN = "token";
    public static final String PARAM_USER_ID = "userId";
    public static final String PARAM_USER_PHONE = "phone";
    public static final String PARAM_USER_VERIFY_CODE = "verifyCode";
    public static final String PARAM_USER_AVATAR = "avatar";
    public static final String PARAM_USER_NICKNAME = "nickname";
    public static final String PARAM_USER_GENDER = "gender";
    public static final String PARAM_USER_BIRTHDAY = "birthday";
    public static final String PARAM_USER_PROVINCE = "province";
    public static final String PARAM_USER_CITY = "city";
    public static final String PARAM_USER_AREA = "area";
    public static final String PARAM_USER_SIGNATURE = "signature";


    //自定义常量
    public static final String X_PARAM_NICKNAME = "nickname";
    public static final String X_PARAM_MOBILE = "mobile";
    public static final String X_PARAM_PASSWORD = "password";
    public static final String X_PARAM_USER_ID = "userId";
    public static final String X_PARAM_USER_TOKEN = "token";
}
