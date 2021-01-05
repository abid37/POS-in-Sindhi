/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoImp;

import beans.AccountBean;
import connectivity.Connectivity;
import dao.AccountDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author xyz
 */
public class AccountDAOImpl implements AccountDAO{

    static Connection con = Connectivity.connect();
    @Override
    public ResultSet viewAllAccountResultSet() {
        ResultSet rst=null;
        try {
            Statement st = con.createStatement();
            rst = st.executeQuery("SELECT a.id AS Id,a.name AS نالو,a.contact AS موبائل,a.description AS Description FROM account a;");
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return rst;
    }

    @Override
    public Integer addAccountDetails(AccountBean accountBean) {
        int r=0;
        try {
            PreparedStatement ps = con.prepareStatement("insert into account(name,contact,description) values(?,?,?)");
            ps.setString(1, accountBean.getAccountName());
            ps.setString(2, accountBean.getAccountConnect());
            ps.setString(3, accountBean.getDescription());
            r = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return r;
    }

    @Override
    public Boolean checkAccount(AccountBean accountBean) {
        try {
            PreparedStatement ps = con.prepareStatement("select * from account u where u.name=?");
            ps.setString(1, accountBean.getAccountName());
            ResultSet rst = ps.executeQuery();
            while (rst.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<AccountBean> setAllTxt(AccountBean accountBean) {
        ArrayList<AccountBean> list = new ArrayList<>();
         try {
            PreparedStatement ps = con.prepareStatement("select * from account u where u.id=?");
            ps.setInt(1, accountBean.getAccountId());
            ResultSet rst = ps.executeQuery();
            while (rst.next()) {
                AccountBean a = new AccountBean();
                a.setAccountName(rst.getString("name"));
                a.setAccountConnect(rst.getString("contact"));
                a.setDescription(rst.getString("description"));
                list.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Integer updateAccountDetails(AccountBean accountBean) {
        int r=0;
        try {
            PreparedStatement ps = con.prepareStatement("update account set name=?,contact=?,description=? where id=?");
            ps.setString(1, accountBean.getAccountName());
            ps.setString(2, accountBean.getAccountConnect());
            ps.setString(3, accountBean.getDescription());
            ps.setInt(4, accountBean.getAccountId());
            r = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return r;
    }

    @Override
    public Integer deleteAccountDetails(AccountBean accountBean) {
        int r=0;
        try {
            PreparedStatement ps = con.prepareStatement("delete from account where id=?");
            ps.setInt(1, accountBean.getAccountId());
            r = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return r;
    }

    @Override
    public Integer getIdByAccount(AccountBean accountBean) {
        int r=0;
         try {
            PreparedStatement ps = con.prepareStatement("select u.id from account u where u.name=?");
            ps.setString(1, accountBean.getAccountName());
            ResultSet rst = ps.executeQuery();
            while (rst.next()) {
                r=rst.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return r;
    }

    @Override
    public List<String> getAllAccountNames() {
        List<String> li = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement("select u.name from account u");
            ResultSet rst = ps.executeQuery();
            while (rst.next()) {
                li.add(rst.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return li;
    }
}
