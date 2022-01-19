package com.Liang.service.bill;

import com.Liang.dao.BaseDao;
import com.Liang.dao.bill.BillDao;
import com.Liang.dao.bill.BillDaoImpl;
import com.Liang.pojo.Bill;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BillServiceImpl implements BillService {

  private BillDao billDao;

  public BillServiceImpl() {
    billDao = new BillDaoImpl();
  }
  // 添加订单
  public boolean add(Bill bill) {
    boolean flag = false;
    Connection connection = null;
    try {
      connection = BaseDao.getConnection();
      connection.setAutoCommit(false); // 开启JDBC事务管理
      if (billDao.add(connection, bill) > 0) {
        flag = true;
      }
      connection.commit();
    } catch (Exception e) {
      e.printStackTrace();
      try {
        connection.rollback();
      } catch (SQLException e1) {
        e1.printStackTrace();
      }
    } finally {
      BaseDao.closeResource(connection, null, null);
    }
    return flag;
  }

  // 查询订单总数
  public int getBillCount(Bill bill) {
    Connection connection = null;
    int count = 0;

    try {
      connection = BaseDao.getConnection();
      count = billDao.getBillCount(connection, bill);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      BaseDao.closeResource(connection, null, null);
    }
    return count;
  }

  // 通过查询条件获取供应商列表-模糊查询-getBillList
  public List<Bill> getBillList(Bill bill, int currentPageNo, int pageSize) {
    Connection connection = null;
    List<Bill> billList = null;

    try {
      connection = BaseDao.getConnection();
      billList = billDao.getBillList(connection, bill, currentPageNo, pageSize);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      BaseDao.closeResource(connection, null, null);
    }
    return billList;
  }

  // 通过delId删除Bill
  public boolean deleteBillById(String delId) {
    Connection connection = null;
    boolean flag = false;
    try {
      connection = BaseDao.getConnection();
      if (billDao.deleteBillById(connection, delId) > 0) flag = true;
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      BaseDao.closeResource(connection, null, null);
    }
    return flag;
  }

  // 通过billId获取Bill
  public Bill getBillById(String id) {
    Bill bill = null;
    Connection connection = null;
    try {
      connection = BaseDao.getConnection();
      bill = billDao.getBillById(connection, id);
    } catch (Exception e) {
      e.printStackTrace();
      bill = null;
    } finally {
      BaseDao.closeResource(connection, null, null);
    }
    return bill;
  }

  // 修改订单信息
  public boolean modify(Bill bill) {
    Connection connection = null;
    boolean flag = false;
    try {
      connection = BaseDao.getConnection();
      if (billDao.modify(connection, bill) > 0) flag = true;
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      BaseDao.closeResource(connection, null, null);
    }
    return flag;
  }
}
