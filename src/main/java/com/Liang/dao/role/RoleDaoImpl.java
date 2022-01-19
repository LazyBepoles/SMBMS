package com.Liang.dao.role;

import com.Liang.dao.BaseDao;
import com.Liang.pojo.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl implements RoleDao {

  // 获取角色列表
  public List<Role> getRoleList(Connection connection) throws Exception {
    PreparedStatement ps = null;
    ResultSet rs = null;
    List<Role> roleList = new ArrayList<Role>();
    if (connection != null) {
      String sql = "select * from smbms_role";
      Object[] params = {};
      rs = BaseDao.execute(connection, ps, rs, sql, params);

      while (rs.next()) {
        Role _role = new Role();
        _role.setId(rs.getInt("id"));
        _role.setRoleCode(rs.getString("roleCode"));
        _role.setRoleName(rs.getString("roleName"));
        roleList.add(_role);
      }
      BaseDao.closeResource(null, ps, rs);
    }
    return roleList;
  }
}
