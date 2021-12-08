package com.lucas.xim.service;

import com.lucas.xim.core.system.User;
import com.lucas.xim.dao.UserDao;
import com.lucas.xim.rqto.LoginRQTO;
import com.lucas.xim.rto.LoginRPTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by cc_want on 2018/6/10.
 */
@Slf4j
@Service
public class UserService {

    @Autowired
    private UserDao mUserDao;

    public LoginRPTO login(LoginRQTO login){
        User user = mUserDao.login(login.getNickname(),login.getPassword());
        if(user == null){
            return null;
        }
        LoginRPTO rpto = new LoginRPTO();
        rpto.setUser_id(user.get_id());
        rpto.setNickname(login.getNickname());
        return rpto;
    }
}
