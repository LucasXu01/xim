package com.lucas.xim.interceptor;

import com.lucas.xim.auth.RequestUser;
import com.lucas.xim.auth.annotation.AuthTokenAnnotation;
import com.lucas.xim.config.NetworkConfig;
import com.lucas.xim.core.ActionCode;
import com.lucas.xim.rto.RPTO;
import com.lucas.xim.utils.JWTUtil;
import com.lucas.xim.utils.JsonMapper;
import com.lucas.xim.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Auther: LucasXu
 * @email: 18140041@bjtu.edu.cn
 * @github: https://github.com/LucasXu01
 * @Date: 2021/12/08/14:22 下午
 */
public class RequestInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding(NetworkConfig.CHARACTER_ENCODING);
        response.setContentType(NetworkConfig.CONTENT_TYPE);

        if(!(handler instanceof HandlerMethod)) {
            return true;
        }

        // 如果不需要token验证，直接放行
        // 如果需要token验证，则流程如下：
        // 1.判断请求头的token是否为空，如果为空，返回失败（token为空），否则执行2
        // 2.从请求头的token里取出userId，如果userId为空，返回失败（用户不存在），否则执行3
        // 3.根据userId在redis查询token，若redis不存在此token，返回失败（token过期），否则执行4
        // 4.通过JWT验证token，验证不通过，返回失败（token无效），否则执行5
        // 5.验证通过，对token进行续期，也就是更新redis里token的过期时间，返回成功
        String token = request.getHeader(NetworkConfig.X_PARAM_USER_TOKEN);
        AuthTokenAnnotation annotation = ((HandlerMethod) handler).getMethodAnnotation(AuthTokenAnnotation.class);
        if(annotation != null && annotation.required()) {
            if(StringUtil.isEmpty(token)) {
                response.getWriter().println(JsonMapper.toJSONString(new RPTO<>(ActionCode.TOKEN_IS_EMPTY)));
                return false;
            }

            try {
                String userId = JWTUtil.getUserId(token);
                if(StringUtil.isEmpty(userId)) {
                    response.getWriter().println(JsonMapper.toJSONString(new RPTO<>(ActionCode.USER_DOES_NOT_EXIST)));
                    return false;
                }

                if(StringUtil.isEmpty(token)) {
                    response.getWriter().println(JsonMapper.toJSONString(new RPTO<>(ActionCode.TOKEN_EXPIRED)));
                    return false;
                }

                boolean verified = JWTUtil.verify(token);
                if(!verified) {
                    response.getWriter().println(JsonMapper.toJSONString(new RPTO<>(ActionCode.TOKEN_INVALID)));
                    return false;
                }

                RequestUser requestUser = new RequestUser(userId, token);
                request.getSession().setAttribute(RequestUser.KEY_REQUEST_USER, requestUser);

                // TODO: 2021/12/9 token续期
//                redisTemplateService.expire(redisTokenKey, JWTUtil.EXPIRE_TIME, TimeUnit.MILLISECONDS);
                return true;
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

        return true;
    }
}
