/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.BillBean;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author xyz
 */
public interface BillDAO {
    public ResultSet viewAllBillResultSet();
    public Integer addBillDetails(BillBean billBean);
    public Integer updateBillDetails(BillBean billBean);
    public Integer updateBillDetails2(BillBean billBean);
    public Integer deleteBillDetails(BillBean billBean);
    public BillBean getBillDetails(BillBean billBean);
    public BillBean getBillNo(String date);
}
