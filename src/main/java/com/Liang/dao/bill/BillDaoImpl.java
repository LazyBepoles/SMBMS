package com.Liang.dao.bill;

import com.Liang.dao.BaseDao;
import com.Liang.pojo.Bill;
import com.mysql.cj.util.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BillDaoImpl implements BillDao{

    // 添加订单
    public int add(Connection connection, Bill bill) throws Exception {
        PreparedStatement ps = null;
        int execute = 0;
        if(connection != null){
            String sql = "insert into smbms_bill (billCode,productName,productDesc," +
                    "productUnit,productCount,totalPrice,isPayment,providerId,createdBy,creationDate) " +
                    "values(?,?,?,?,?,?,?,?,?,?)";
            Object[] params = {bill.getBillCode(),bill.getProductName(),bill.getProductDesc(),
                    bill.getProductUnit(),bill.getProductCount(),bill.getTotalPrice(),bill.getIsPayment(),
                    bill.getProviderId(),bill.getCreatedBy(),bill.getCreationDate()};
            execute = BaseDao.execute(connection, ps, sql, params);
            BaseDao.closeResource(null, ps, null);
        }
        return execute;
    }

    // 通过查询条件获取供应商列表-模糊查询-getBillList
    public List<Bill> getBillList(Connection connection, Bill bill) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Bill> billList = new ArrayList<Bill>();
        if(connection != null){
            StringBuffer sql = new StringBuffer();
            sql.append("select b.*,p.proName as providerName from smbms_bill b, smbms_provider p where b.providerId = p.id");
            List<Object> list = new ArrayList<Object>();
            if(!StringUtils.isNullOrEmpty(bill.getProductName())){
                sql.append(" and productName like ?");
                list.add("%"+bill.getProductName()+"%");
            }
            if(bill.getProviderId() > 0){
                sql.append(" and providerId = ?");
                list.add(bill.getProviderId());
            }
            if(bill.getIsPayment() > 0){
                sql.append(" and isPayment = ?");
                list.add(bill.getIsPayment());
            }
            Object[] params = list.toArray();
            rs = BaseDao.execute(connection, ps, rs, sql.toString(), params);
            while(rs.next()){
                Bill _bill = new Bill();
                _bill.setId(rs.getInt("id"));
                _bill.setBillCode(rs.getString("billCode"));
                _bill.setProductName(rs.getString("productName"));
                _bill.setProductDesc(rs.getString("productDesc"));
                _bill.setProductUnit(rs.getString("productUnit"));
                _bill.setProductCount(rs.getBigDecimal("productCount"));
                _bill.setTotalPrice(rs.getBigDecimal("totalPrice"));
                _bill.setIsPayment(rs.getInt("isPayment"));
                _bill.setProviderId(rs.getInt("providerId"));
                _bill.setProviderName(rs.getString("providerName"));
                _bill.setCreationDate(rs.getTimestamp("creationDate"));
                _bill.setCreatedBy(rs.getInt("createdBy"));
                billList.add(_bill);
            }
            BaseDao.closeResource(null, ps, rs);
        }
        return billList;
    }

    // 通过delId删除Bill
    public int deleteBillById(Connection connection, String delId) throws Exception {
        PreparedStatement ps = null;
        int execute = 0;
        if(connection != null){
            String sql = "delete from smbms_bill where id=?";
            Object[] params = {delId};
            execute = BaseDao.execute(connection, ps, sql, params);
            BaseDao.closeResource(null, ps, null);
        }
        return execute;
    }

    // 通过billId获取Bill
    public Bill getBillById(Connection connection, String id) throws Exception {
        Bill bill = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        if(connection != null){
            String sql = "select b.*,p.proName as providerName from smbms_bill b, smbms_provider p " +
                    "where b.providerId = p.id and b.id=?";
            Object[] params = {id};
            rs = BaseDao.execute(connection, ps, rs, sql, params);
            if(rs.next()){
                bill = new Bill();
                bill.setId(rs.getInt("id"));
                bill.setBillCode(rs.getString("billCode"));
                bill.setProductName(rs.getString("productName"));
                bill.setProductDesc(rs.getString("productDesc"));
                bill.setProductUnit(rs.getString("productUnit"));
                bill.setProductCount(rs.getBigDecimal("productCount"));
                bill.setTotalPrice(rs.getBigDecimal("totalPrice"));
                bill.setIsPayment(rs.getInt("isPayment"));
                bill.setProviderId(rs.getInt("providerId"));
                bill.setProviderName(rs.getString("providerName"));
                bill.setModifyBy(rs.getInt("modifyBy"));
                bill.setModifyDate(rs.getTimestamp("modifyDate"));
            }
            BaseDao.closeResource(null, ps, rs);
        }
        return bill;
    }

    // 修改订单信息
    public int modify(Connection connection, Bill bill) throws Exception {
        int execute = 0;
        PreparedStatement ps = null;
        if(connection != null){
            String sql = "update smbms_bill set productName=?," +
                    "productDesc=?,productUnit=?,productCount=?,totalPrice=?," +
                    "isPayment=?,providerId=?,modifyBy=?,modifyDate=? where id = ? ";
            Object[] params = {bill.getProductName(),bill.getProductDesc(),
                    bill.getProductUnit(),bill.getProductCount(),bill.getTotalPrice(),bill.getIsPayment(),
                    bill.getProviderId(),bill.getModifyBy(),bill.getModifyDate(),bill.getId()};
            execute = BaseDao.execute(connection, ps, sql, params);
            BaseDao.closeResource(null, ps, null);
        }
        return execute;
    }

    // 根据供应商ID查询订单数量
    public int getBillCountByProviderId(Connection connection, String providerId) throws Exception {
        int count = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        if(connection != null){
            String sql = "select count(1) as billCount from smbms_bill where" +
                    "	providerId = ?";
            Object[] params = {providerId};
            rs = BaseDao.execute(connection, ps, rs, sql, params);
            if(rs.next()){
                count = rs.getInt("billCount");
            }
            BaseDao.closeResource(null, ps, rs);
        }

        return count;
    }
}
