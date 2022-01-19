package com.Liang.service.provider;

import com.Liang.pojo.Provider;

import java.util.List;

public interface ProviderService {
  // 增加供应商
  boolean add(Provider provider);

  // 查询供应商总数
  int getProviderCount(String proName, String proCode);

  // 通过供应商名称、编码获取供应商列表-模糊查询-providerList
  List<Provider> getProviderList(String proName, String proCode, int currentPageNo, int pageSize);

  List<Provider> getProviderListforBill(String proName, String proCode);

  // 通过proId删除Provider
  int deleteProviderById(String delId);

  // 通过proId获取Provider
  Provider getProviderById(String id);

  // 修改用户信息
  boolean modify(Provider provider);
}
