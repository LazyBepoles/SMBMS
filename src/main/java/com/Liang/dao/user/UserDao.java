package com.Liang.dao.user;

import com.Liang.pojo.User;

import java.sql.Connection;
import java.util.List;

public interface UserDao {

    //获取登录用户信息
    public User getLoginUser(Connection connection,String userCode) throws Exception;

    //修改用户密码
    public int updatePwd(Connection connection,int id,String password) throws Exception;

    //查询用户总数
    public int getUserCount(Connection connection,String username,int userRole) throws Exception;

    //获取用户列表
    public List<User> getUserList(Connection connection,String userName,int userRole,int currentPageNo,int pageSize)
        throws Exception;

    //添加用户
    public int addUser(Connection connection,User user) throws Exception;

    //查看用户
    public User getUserById(Connection connection,int id) throws Exception;

    //删除用户
    public int delUser(Connection connection,int id) throws Exception;
}
