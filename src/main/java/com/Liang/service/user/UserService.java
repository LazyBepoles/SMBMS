package com.Liang.service.user;

import com.Liang.pojo.User;

public interface UserService {

    //用户登录
    public User login(String userCode,String password);

    //修改用户密码
    public boolean updatePwd(int id,String password);
}
