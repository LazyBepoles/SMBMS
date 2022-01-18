package com.Liang.service.user;

import com.Liang.dao.BaseDao;
import com.Liang.dao.user.UserDao;
import com.Liang.dao.user.UserDaoImpl;
import com.Liang.pojo.User;

import java.sql.Connection;

public class UserServiceImpl implements UserService {

    //业务层都会调用Dao层，先引入Dao层
    private UserDao userDao;

    public UserServiceImpl() {
        userDao = new UserDaoImpl();
    }

    //用户登录
    public User login(String userCode, String password) {
        Connection connection = null;
        User user = null;

        connection = BaseDao.getConnection();
        //通过业务层调用对应的具体的数据库操作
        try {
            user = userDao.getLoginUser(connection, userCode);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        if (user != null) {
            if (!password.equals(user.getUserPassword())) {
                user = null;
            }
        }
        return user;
    }

    //修改密码
    public boolean updatePwd(int id, String password) {
        Connection connection = null;
        boolean flag = false;
        //修改密码
        try {
            connection = BaseDao.getConnection();
            if (userDao.updatePwd(connection, id, password) > 0) {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(connection, null, null);
        }
        return flag;
    }
}
