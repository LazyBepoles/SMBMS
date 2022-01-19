package com.Liang.service.bill;

import com.Liang.pojo.Bill;

import java.util.List;

public interface BillService {

  // 添加订单
  boolean add(Bill bill);

  // 查询订单总数
  int getBillCount(Bill bill);

  // 通过查询条件获取供应商列表-模糊查询-getBillList
  List<Bill> getBillList(Bill bill, int currentPageNo, int pageSize);

  // 通过delId删除Bill
  boolean deleteBillById(String delId);

  // 通过billId获取Bill
  Bill getBillById(String id);

  // 修改订单信息
  boolean modify(Bill bill);
}
