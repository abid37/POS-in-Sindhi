/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoImp;

import beans.AccountBean;
import connectivity.Connectivity;
import dao.CustRecordDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author xyz
 */
public class CustRecordDAOImpl implements CustRecordDAO{

    static Connection con = Connectivity.connect();
    
    @Override
    public ResultSet getAllBills(AccountBean ab) {
        ResultSet rst=null;
        try {
            PreparedStatement pst = con.prepareStatement("SELECT o.bill_id,o.bill_no,a.name,o.customer_name,o.total,o.paid,(o.total-o.paid),o.date FROM bill o, account a WHERE o.customer_id=a.id AND a.id=?");
            pst.setInt(1, ab.getAccountId());
            rst = pst.executeQuery();
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return rst;
    }
    
}
