package com.Liang.service.user;

import com.Liang.pojo.User;

public interface UserService {
    //用户登录
    public User login(String userCode,String password);
}
