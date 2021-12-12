package com.lucas.xim.auth.annotation;

import java.lang.annotation.*;

/**
 * @Description: Token验证注解，需要验证token的接口加上此注解即可
 * @Auther: LucasXu
 * @email: 18140041@bjtu.edu.cn
 * @github: https://github.com/LucasXu01
 * @Date: 2021/12/09/14:32 下午
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestUserAnnotation {
}
