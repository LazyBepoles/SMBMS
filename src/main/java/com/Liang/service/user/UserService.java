package com.Liang.service.user;

import com.Liang.pojo.User;

import java.util.List;

public interface UserService {

    //用户登录
    User login(String userCode,String password);

    //修改用户密码
    boolean updatePwd(int id,String password);

    //查询用户数
    int getUserCount(String username,int userRole);

    //查询用户列表
    List<User> getUserList(String queryUserName, int queryUserRole, int currentPageNo, int pageSize);

    //查询用户是否存在
    User userCodeExist(String userCode);

    //添加用户
    boolean addUser(User user);

    //查看用户
    User getUserById(int id);

    //删除用户
    boolean delUserBy(int id);

    //修改用户
    boolean modify(User user);
}
