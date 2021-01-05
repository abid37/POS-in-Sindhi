/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoImp;

import beans.AccountBean;
import beans.BillBean;
import beans.TaxBean;
import connectivity.Connectivity;
import dao.BillDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author xyz
 */
public class BillDAOImpl implements BillDAO{

    static Connection con = Connectivity.connect();
//    @Override
//    public ResultSet viewAllOrderResultSet(BillBean orderBean) {
//        ResultSet rst=null;
//        try {
//            PreparedStatement ps = con.prepareStatement("SELECT o.order_id,o.order_no,a.name,t.tax,o.total_price,o.paid,o.date "
//                    + "FROM order_ o,account a,tax t WHERE o.customer_id=a.id AND t.tax_id=o.tax_id "
//                    + "AND o.order_no LIKE '%"+orderBean.getOrderNumber()+"%'");
//            //ps.setString(1, orderBean.getOrderNumber());
//            rst = ps.executeQuery();
//        } catch (SQLException e) {
//                e.printStackTrace();
//        }
//        return rst;    
//    }
//
//    @Override
//    public Integer addOrderDetails(BillBean orderBean) {
//        int r=0;
//        try {
//            PreparedStatement ps = con.prepareStatement("insert into order_(order_no,customer_id,tax_id,total_price,date) values(?,?,?,?,?)");
//            ps.setString(1, orderBean.getOrderNumber());
//            ps.setInt(2, orderBean.getAccountBean().getAccountId());
//            ps.setInt(3, orderBean.getTaxBean().getTaxId());
//            ps.setFloat(4, orderBean.getTotalPrice());
//            ps.setString(5, orderBean.getDate());
//            r = ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return r;
//    }
//
//    @Override
//    public Integer updateOrderDetails(BillBean orderBean) {
//        int r=0;
//        try {
//            PreparedStatement ps = con.prepareStatement("update order_ set total_price=?,paid=? where order_no=?");
//            ps.setFloat(1, orderBean.getTotalPrice());
//            ps.setFloat(2, orderBean.getPaidPrice());
//            ps.setString(3, orderBean.getOrderNumber());
//            r = ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return r;
//    }
//    
//    @Override
//    public Integer updateOrderDetails2(BillBean orderBean) {
//        int r=0;
//        try {
//            PreparedStatement ps = con.prepareStatement("update order_ set customer_id=?,tax_id=?,total_price=?,paid=?,date=? where order_no=?");
//            ps.setInt(1, orderBean.getAccountBean().getAccountId());
//            ps.setInt(2, orderBean.getTaxBean().getTaxId());
//            ps.setFloat(3, orderBean.getTotalPrice());
//            ps.setFloat(4, orderBean.getPaidPrice());
//            ps.setString(5, orderBean.getDate());
//            ps.setString(6, orderBean.getOrderNumber());
//            r = ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return r;
//    }
//
//    @Override
//    public Integer deleteOrderDetails(BillBean orderBean) {
//        int r=0;
//        try {
//            PreparedStatement ps = con.prepareStatement("delete from order_ where order_no=?");
//            ps.setString(1, orderBean.getOrderNumber());
//            r = ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return r;
//    }
//
//    @Override
//    public BillBean getOrderDetails(BillBean orderBean) {
//        BillBean ob = null;
//        try {
//            PreparedStatement ps = con.prepareStatement("SELECT o.order_id,o.order_no,a.name,t.tax,o.total_price,"
//                    + "o.paid,o.date FROM order_ o, account a,tax t WHERE o.customer_id=a.id "
//                    + "AND t.tax_id=o.tax_id AND o.order_no=?");
//            ps.setString(1, orderBean.getOrderNumber());
//            ResultSet rst = ps.executeQuery();
//            while (rst.next()) {
//                ob = new BillBean();
//                ob.setOrderId(rst.getInt("order_id"));
//                    AccountBean accountBean = new AccountBean();
//                    accountBean.setAccountName(rst.getString("name"));
//                ob.setAccountBean(accountBean);
//                    TaxBean taxBean = new TaxBean();
//                    taxBean.setTax(rst.getFloat("tax"));
//                ob.setTaxBean(taxBean);
//                ob.setTotalPrice(rst.getFloat("total_price"));
//                ob.setPaidPrice(rst.getFloat("paid"));
//                ob.setDate(rst.getString("date"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return ob;
//    }

    @Override
    public ResultSet viewAllBillResultSet() {
        ResultSet rst=null;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT o.bill_no,o.customer_name,o.total,o.paid FROM bill o, "
                    + "account a WHERE o.customer_id=a.id");
            rst = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rst;
    }

    @Override
    public Integer addBillDetails(BillBean billBean) {
        int r=0;
        try {
            PreparedStatement ps = con.prepareStatement("insert into bill(bill_no,customer_id,customer_name,total,paid,date) values(?,?,?,?,?,?)");
            ps.setInt(1, billBean.getBillNumber());
            ps.setInt(2, billBean.getAccountBean().getAccountId());
            ps.setString(3, billBean.getName());
            ps.setFloat(4, billBean.getTotal());
            ps.setFloat(5, billBean.getPaid());
            ps.setString(6, billBean.getDate());
            r = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return r;
    }

    @Override
    public Integer updateBillDetails(BillBean billBean) {
        int r=0;
        try {
            PreparedStatement ps = con.prepareStatement("update bill set total=?,paid=? where bill_no=?");
            ps.setFloat(1, billBean.getTotal());
            ps.setFloat(2, billBean.getPaid());
            ps.setInt(3, billBean.getBillNumber());
            r = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return r;
    }

    @Override
    public Integer updateBillDetails2(BillBean billBean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer deleteBillDetails(BillBean billBean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BillBean getBillDetails(BillBean billBean) {
        BillBean ob = null;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT o.bill_no,a.id,o.customer_name,o.total,o.paid,o.date FROM bill o, "
                    + "account a WHERE o.customer_id=a.id AND o.bill_no=?");
            ps.setInt(1, billBean.getBillNumber());
            ResultSet rst = ps.executeQuery();
            while (rst.next()) {
                ob = new BillBean();
                ob.setBillNumber(rst.getInt("bill_no"));
                    AccountBean accountBean = new AccountBean();
                    accountBean.setAccountId(rst.getInt("id"));
                ob.setAccountBean(accountBean);
                ob.setName(rst.getString("customer_name"));
                ob.setTotal(rst.getFloat("total"));
                ob.setPaid(rst.getFloat("paid"));
                ob.setDate(rst.getString("date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ob;
    }

    @Override
    public BillBean getBillNo(String date) {
        BillBean bb=null;
        int billno=0;
        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM bill WHERE DATE='"+date+"'");
            //ps.setString(1, date);
            ResultSet rst = ps.executeQuery();
            bb = new BillBean();    
            while(rst.next()){
                billno = rst.getInt("bill_no");
                bb.setBillNumber(billno);
            }
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return bb; 
    }

}
