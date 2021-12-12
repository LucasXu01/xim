package com.lucas.xim.utils;

import java.util.Map;

/**
 * @Description:
 * @Auther: LucasXu
 * @email: 18140041@bjtu.edu.cn
 * @github: https://github.com/LucasXu01
 * @Date: 2021/12/08/5:55 下午
 */
public class MapUtil {

    public static boolean isEmpty(Map params) {
        return params == null || params.isEmpty();
    }

    public static boolean isNotEmpty(Map params) {
        return !isEmpty(params);
    }
}
