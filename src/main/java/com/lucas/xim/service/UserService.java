package com.lucas.xim.service;

import com.lucas.xim.core.system.User;
import com.lucas.xim.dao.UserDao;
import com.lucas.xim.rqto.LoginRQTO;
import com.lucas.xim.rto.LoginRPTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Auther: LucasXu
 * @email: 18140041@bjtu.edu.cn
 * @github: https://github.com/LucasXu01
 * @Date: 2021/12/08/17:40 下午
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

    public boolean register(User user){
         return mUserDao.register(user);
    }

    public User queryUserByPhone(String mobile) {
        return mUserDao.queryUserByPhone(mobile);
    }

    public User queryUserByUid(String uid) {
        return mUserDao.queryUserByUid(uid);
    }
}
