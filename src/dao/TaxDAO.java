/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.TaxBean;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author xyz
 */
public interface TaxDAO {
    public ResultSet viewAllTaxResultSet();
    public Integer addTaxDetails(TaxBean taxBean);
    public List<TaxBean> setAllTxt(TaxBean taxBean);
    public Integer updateTaxDetails(TaxBean taxBean);
    public Integer deleteTaxDetails(TaxBean taxBean);
    public TaxBean checkDatesForTax(String date);
}
