package com.Liang.service.user;

import com.Liang.dao.BaseDao;
import com.Liang.dao.user.UserDao;
import com.Liang.dao.user.UserDaoImpl;
import com.Liang.pojo.User;

import java.sql.Connection;
import java.util.List;

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

    //查询用户数
    public int getUserCount(String username, int userRole) {
        Connection connection = null;
        int userCount = 0;
        try {
            connection=BaseDao.getConnection();
            userCount = userDao.getUserCount(connection, username, userRole);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            BaseDao.closeResource(connection, null, null);
        }
        return userCount;
    }

    //查询用户列表
    public List<User> getUserList(String queryUserName, int queryUserRole, int currentPageNo, int pageSize) {
        Connection connection = null;
        List<User> userList = null;
        try {
            connection = BaseDao.getConnection();
            userList = userDao.getUserList(connection, queryUserName,queryUserRole,currentPageNo,pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            BaseDao.closeResource(connection, null, null);
        }
        return userList;
    }

    //查询用户是否存在
    public User userCodeExist(String userCode) {
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
        return user;
    }

    //添加用户
    public boolean addUser(User user) {
        Connection connection = null;
        boolean flag = false;
        //修改密码
        try {
            connection = BaseDao.getConnection();
            if (userDao.addUser(connection, user) > 0) {
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            BaseDao.closeResource(connection, null, null);
        }
        return flag;
    }

    //查看用户
    public User getUserById(int id) {
        Connection connection = null;
        User user = null;

        connection = BaseDao.getConnection();
        //通过业务层调用对应的具体的数据库操作
        try {
            user = userDao.getUserById(connection, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return user;
    }

    //删除用户
    public boolean delUserBy(int id) {
        Connection connection = null;
        boolean flag = false;
        //删除用户
        try {
            connection = BaseDao.getConnection();
            if (userDao.delUser(connection, id) > 0) {
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
