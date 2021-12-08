package com.lucas.xim.controller;


import com.lucas.xim.core.ActionCode;
import com.lucas.xim.rqto.LoginRQTO;
import com.lucas.xim.rto.LoginRPTO;
import com.lucas.xim.rto.RPTO;
import com.lucas.xim.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
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

}
