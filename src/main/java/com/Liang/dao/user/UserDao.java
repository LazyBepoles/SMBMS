package com.Liang.dao.user;

import com.Liang.pojo.User;

import java.sql.Connection;

public interface UserDao {

    //获取登录用户信息
    public User getLoginUser(Connection connection,String userCode) throws Exception;
}
