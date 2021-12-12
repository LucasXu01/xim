package com.lucas.xim.controller;


import com.lucas.xim.auth.annotation.AuthTokenAnnotation;
import com.lucas.xim.config.NetworkConfig;
import com.lucas.xim.core.ActionCode;
import com.lucas.xim.core.system.User;
import com.lucas.xim.rqto.LoginRQTO;
import com.lucas.xim.rto.LoginRPTO;
import com.lucas.xim.rto.RPTO;
import com.lucas.xim.service.UserService;
import com.lucas.xim.utils.JWTUtil;
import com.lucas.xim.utils.MapUtil;
import com.lucas.xim.utils.StringUtil;
import com.lucas.xim.ximcore.nettyimcore.util.IDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

import java.util.Map;

import static com.lucas.xim.config.NetworkConfig.*;


/**
 * @Description:
 * @Auther: LucasXu
 * @email: 18140041@bjtu.edu.cn
 * @github: https://github.com/LucasXu01
 * @Date: 2021/12/08/17:35 下午
 */

@Slf4j
@Controller
//@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService mUserService;

    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public RPTO login(@RequestBody @Valid LoginRQTO login) {
        LoginRPTO rpto = mUserService.login(login);
        if(rpto == null){
            return new RPTO<>("用户名密码错误");
        }
        return new RPTO<>(ActionCode.SUCCESS,rpto);
    }

    @ResponseBody
    @RequestMapping(value = USER_REGISTER, method = RequestMethod.POST)
    public RPTO<Object> register(@RequestBody Map<String, Object> params) {
        log.debug("register() params = " + params);
        if (MapUtil.isEmpty(params)) {
            return new RPTO<>(ActionCode.PARAMS_IS_EMPTY);
        }

        if (null == mUserService.queryUserByPhone(X_PARAM_MOBILE)) {// 手机已被注册
            return new RPTO<>(ActionCode.PHONE_IS_REGISTERD);
        }

        User user = new User();
        user.setNickname((String)params.get(X_PARAM_NICKNAME));
        user.setPassword((String)params.get(X_PARAM_PASSWORD));
        user.setMobile((String) params.get(X_PARAM_MOBILE));
        user.setCreate_time(System.currentTimeMillis());
        user.set_id(IDUtil.randomId());
        mUserService.register(user);

        // 生成token
        String token = JWTUtil.sign(user.get_id(), user.getMobile());
        user.setToken(token);

        return new RPTO<>(ActionCode.SUCCESS, user);
    }

    @ResponseBody
    @RequestMapping(value = USER_LOGIN, method = RequestMethod.POST)
    public RPTO<Object> login(@RequestBody Map<String, Object> params) {
        log.debug("login() params = " + params);

        // TODO: 2021/12/9 只做了mobile校验，其余参数的校验 暂不添加了
        if (MapUtil.isEmpty(params)) {
            return new RPTO<>(ActionCode.PARAMS_IS_EMPTY);
        }

        //手机号校验
        String mobile = (String) params.get(NetworkConfig.X_PARAM_MOBILE);
        if (StringUtil.isEmpty(mobile)) {
            return new RPTO<>(ActionCode.PHONE_IS_EMPTY);
        }
        if (!StringUtil.isPhone(mobile)) {
            return new RPTO<>(ActionCode.ILLEGAL_PHONE);
        }

        User user = mUserService.queryUserByPhone(mobile);
        if (user == null) {// 如果不存在，返回用户不存在
            return new RPTO<>(ActionCode.USER_DOES_NOT_EXIST);
        }

        // 生成token
        String token = JWTUtil.sign(user.get_id(), user.getMobile());
        user.setToken(token);

        //登陆xim，建立netty连接

        return new RPTO<>(ActionCode.SUCCESS, user);
    }

    @AuthTokenAnnotation(required = true)
    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public String test() {
        return "123";
    }

}
