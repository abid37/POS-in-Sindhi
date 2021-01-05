/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.BillDetailsBean;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author xyz
 */
public interface BillDetailsDAO {
    public ResultSet viewAllBillDetailsBeanResultSet(int bill);
    public ResultSet viewAllBillDetailsBeanResultSet2(String bill);
    public Integer addBillDetails(BillDetailsBean billDetailsBean);
    public BillDetailsBean setAllTxtForReturn(BillDetailsBean billDetailsBean);
    public Boolean checkIfProductExists(BillDetailsBean billDetailsBean);
    public Integer updateIfProductExists(BillDetailsBean billDetailsBean);
    public Integer addProductIfReturns(BillDetailsBean billDetailsBean);
    public Integer getProductIdByBillId(BillDetailsBean billDetailsBean);
}
