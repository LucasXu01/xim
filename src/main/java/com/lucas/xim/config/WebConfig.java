package com.lucas.xim.config;

import com.lucas.xim.auth.RequestUserHandlerMethodArgReslover;
import com.lucas.xim.interceptor.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;


/**
 * @Description:
 * @Auther: LucasXu
 * @email: 18140041@bjtu.edu.cn
 * @github: https://github.com/LucasXu01
 * @Date: 2021/12/09/15:01 下午
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public RequestInterceptor initRequestInterceptor() {
        return new RequestInterceptor();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new RequestUserHandlerMethodArgReslover());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(initRequestInterceptor()).addPathPatterns("/**")/*.excludePathPatterns("/user/login")*/;
    }
}
