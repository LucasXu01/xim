package com.lucas.xim.dao;

import com.lucas.xim.core.system.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by cc_want on 2018/6/10.
 */
@Mapper
public interface UserDao {
    User login(@Param("nickname") String nickname, @Param("password")String password);
}