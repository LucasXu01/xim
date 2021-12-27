package com.lucas.xim.dao;

import com.lucas.xim.core.system.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 */
@Mapper
public interface UserDao {

    /**
     * 用户登陆
     */
    User login(@Param("nickname") String nickname, @Param("password")String password);

    /**
     * 注册用户
     * @param user
     */
    boolean register(User user);

    /**
     * 根据手机号查询用户
     * @param mobile
     * @return
     */
    User queryUserByPhone(String mobile);


    /**
     * 根据uid
     * @param uid
     * @return
     */
    User queryUserByUid(String uid);
}