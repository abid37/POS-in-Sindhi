/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;


import beans.AccountBean;
import beans.BillBean;
import beans.BillDetailsBean;
import beans.ProductBean;
import connectivity.Connectivity;
import dao.AccountDAO;
import dao.BillDAO;
import dao.BillDetailsDAO;
import dao.ProductDAO;
import daoImp.AccountDAOImpl;
import daoImp.BillDAOImpl;
import daoImp.BillDetailsDAOImpl;
import daoImp.ProductDAOImpl;
import com.sun.xml.internal.bind.api.impl.NameConverter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class OrderFrame extends javax.swing.JFrame {
    
    public static ProductBean probean = new ProductBean();
    public static BillBean billbean = new BillBean();
    public static BillDetailsBean billdetailbean = new BillDetailsBean();
    public static AccountBean accountbean = new AccountBean();
    
    public OrderFrame() throws SQLException {
        initComponents();
        setProductTable();
        setDate();
        setCustomerCombo();
        setBillNo();
        setAllBillTable();
    }
    
    private void setCustomerCombo(){
        AccountDAO accountDAO = new AccountDAOImpl();
        List<String> li = accountDAO.getAllAccountNames();
        for (String s : li) {
            comboCustomer.addItem(s);
        }
        comboCustomer.setSelectedItem("Walking Away");
    }
    
    private void setBillNo(){
        Date d = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("ddMMyy");
        String date = sd.format(d);
        date = date+"1";
        
        int billNo;
        SimpleDateFormat sd1 = new SimpleDateFormat("yyyy-MM-dd");
        String dateorg = sd1.format(d);
        BillDAO billDAO = new BillDAOImpl();
        BillBean bb = billDAO.getBillNo(dateorg);
        int billno = bb.getBillNumber();
        if(billno==0){
            billNo=Integer.parseInt(date);
        }else{
            billno++;
            billNo = billno;
        }
        lblBillNo.setText(String.valueOf(billNo));
        
    }
    
    private void setDate(){
        Date d = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat sd1 = new SimpleDateFormat("yyyy-MM-dd");
        String date = sd.format(d);
        String date1 = sd1.format(d);
        lblDate.setText(date);
        billbean.setDate(date1);
        //bb1.setDate(date);
    }
    
    public  void setProductTable() throws SQLException {
        ProductDAO productDAO = new ProductDAOImpl();
        ProductBean pb = new ProductBean();
   
        pb.setProductName("");
        tableProduct.setModel(buildTableModel(productDAO.viewAllProductResultSetForOrder(pb,0)));
    
        pb.setProductNameSindhi("");
        tableProduct.setModel(buildTableModel(productDAO.viewAllProductResultSetForOrder(pb,1)));
    }
    
    private void setAllBillTable() throws SQLException{
        BillDAO billDAO = new BillDAOImpl();
        tableBill.setModel(buildTableModel(billDAO.viewAllBillResultSet()));
    }
    
    private static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }
        return new DefaultTableModel(data, columnNames);
    }
   
    public static void setInvoiceTable(){
        try {       
            BillDetailsDAO bddao = new BillDetailsDAOImpl();
            int num = Integer.parseInt(lblBillNo.getText());
            tableInvoice.setModel(buildTableModel(bddao.viewAllBillDetailsBeanResultSet(Integer.parseInt(lblBillNo.getText()))));
            setTotal();
        } catch (SQLException ex) {
            Logger.getLogger(OrderFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static void setTotal() {
        //sum start
        lblTotal.setText("");
        int len = tableInvoice.getRowCount() - 1;
        double total = 0;
        while (len >= 0) {
            double val = (double) tableInvoice.getValueAt(len, 5);
            total = total + val;
            len--;
        }
        lblTotal.setText(String.valueOf(total));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtProEng = new javax.swing.JTextField();
        txtPaid = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableBill = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        lblDate = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableProduct = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        lblBillNo = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblMsgForReturn = new javax.swing.JLabel();
        lblReturn = new javax.swing.JLabel();
        btnUpdate = new javax.swing.JButton();
        btnUpdate1 = new javax.swing.JButton();
        btnNewOrder = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtOrderNo = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableInvoice = new javax.swing.JTable();
        Category1 = new javax.swing.JLabel();
        txtProSindhi = new javax.swing.JTextField();
        lblTotal1 = new javax.swing.JLabel();
        lblReturn1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtCustomerName = new javax.swing.JTextField();
        comboCustomer = new javax.swing.JComboBox();
        lblTotal2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
        });
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtProEng.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        txtProEng.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtProEngFocusGained(evt);
            }
        });
        txtProEng.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProEngKeyReleased(evt);
            }
        });
        jPanel2.add(txtProEng, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 150, 270, -1));

        txtPaid.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtPaid.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtPaid.setText("0");
        txtPaid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPaidKeyReleased(evt);
            }
        });
        jPanel2.add(txtPaid, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 820, 130, -1));

        tableBill.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tableBill.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableBill.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableBillMouseClicked(evt);
            }
        });
        tableBill.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableBillKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tableBill);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1560, 130, 340, 840));

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jButton4.setText("< Back");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 920, 180, -1));

        lblDate.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        lblDate.setText("date");
        jPanel2.add(lblDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 20, 200, 40));

        tableProduct.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tableProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableProductMouseClicked(evt);
            }
        });
        tableProduct.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableProductKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tableProduct);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 930, 680));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel6.setText("بل نمبر");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, 170, 40));

        lblBillNo.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        lblBillNo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBillNo.setText("1");
        jPanel2.add(lblBillNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 270, 40));

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jButton5.setText("Return All");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1330, 710, 210, -1));

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jButton7.setText("<< Return");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 710, 210, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("ٽوٽل");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1330, 770, 190, 40));

        lblTotal.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblTotal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTotal.setText("0.0");
        jPanel2.add(lblTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 770, 130, 40));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setText("مليل");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1330, 820, 190, 40));

        lblMsgForReturn.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblMsgForReturn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMsgForReturn.setText("واپس");
        jPanel2.add(lblMsgForReturn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1330, 870, 190, 40));

        lblReturn.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblReturn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel2.add(lblReturn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 870, 130, 40));

        btnUpdate.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.setEnabled(false);
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        jPanel2.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 920, 150, -1));

        btnUpdate1.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        btnUpdate1.setText("Generate Receit");
        btnUpdate1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdate1ActionPerformed(evt);
            }
        });
        jPanel2.add(btnUpdate1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 920, 290, -1));

        btnNewOrder.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        btnNewOrder.setText("New Order");
        btnNewOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewOrderActionPerformed(evt);
            }
        });
        jPanel2.add(btnNewOrder, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 150, -1, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel13.setText("Bill No.");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(1680, 10, 190, 40));

        txtOrderNo.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        txtOrderNo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtOrderNoKeyReleased(evt);
            }
        });
        jPanel2.add(txtOrderNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(1590, 60, 270, -1));

        tableInvoice.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tableInvoice.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "No.", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        tableInvoice.setRowHeight(24);
        tableInvoice.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableInvoiceMouseClicked(evt);
            }
        });
        tableInvoice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableInvoiceKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tableInvoice);

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 20, 570, 670));

        Category1.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        Category1.setText("آئٽم");
        jPanel2.add(Category1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 150, 70, 40));

        txtProSindhi.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        txtProSindhi.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtProSindhiFocusGained(evt);
            }
        });
        txtProSindhi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProSindhiKeyReleased(evt);
            }
        });
        jPanel2.add(txtProSindhi, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 270, -1));

        lblTotal1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblTotal1.setText("Rs.");
        jPanel2.add(lblTotal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 820, 40, 40));

        lblReturn1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblReturn1.setText("Rs.");
        jPanel2.add(lblReturn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 870, 40, 40));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel11.setText("گراهڪ");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 90, 170, 40));

        txtCustomerName.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        txtCustomerName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCustomerNameKeyReleased(evt);
            }
        });
        jPanel2.add(txtCustomerName, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 90, 270, -1));

        comboCustomer.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        comboCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCustomerActionPerformed(evt);
            }
        });
        jPanel2.add(comboCustomer, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 270, 40));

        lblTotal2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblTotal2.setText("Rs.");
        jPanel2.add(lblTotal2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 770, 40, 40));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1910, 980));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1930, 1000));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtProEngKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProEngKeyReleased
        try {
            ProductDAO productDAO = new ProductDAOImpl();
            ProductBean pb = new ProductBean();       
            String nameEng = txtProEng.getText();
            pb.setProductName(nameEng);
            
            tableProduct.setModel(buildTableModel(productDAO.viewAllProductResultSetForOrder(pb,0)));
            
            if(tableProduct.getRowCount()>0){
                String sval = (String) tableProduct.getValueAt(0,2);
                String eval = (String) tableProduct.getValueAt(tableProduct.getRowCount()-1,2);

                if(sval.equals(eval)){
                    ProductTableDialog ptd = new ProductTableDialog(this, rootPaneCheckingEnabled,sval);
                   
                    String billno = lblBillNo.getText();
                    billbean.setBillNumber(Integer.parseInt(billno)); //bill no
                    billbean.setAccountBean(accountbean); //customer id
                    String name = txtCustomerName.getText();
                    billbean.setName(name); //name
                    billdetailbean.setBillBean(billbean);
                    
                    ptd.setVisible(true);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtProEngKeyReleased

    private void txtPaidKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPaidKeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            try {
                billbean.setBillNumber(Integer.parseInt(lblBillNo.getText()));
                
                float paid = Float.parseFloat(txtPaid.getText());
                float total = Float.parseFloat(lblTotal.getText());
                
                if(paid>=total){
                    lblMsgForReturn.setText("واپس");
                    float ret = paid-total;
                    lblReturn.setText(String.valueOf(ret));
                    paid = total;
                }else{
                    lblMsgForReturn.setText("بقايا");
                    lblReturn.setText(String.valueOf(total-paid));
                }
                
                billbean.setTotal(total);
                billbean.setPaid(paid);
                BillDAO bdao = new BillDAOImpl();
                bdao.updateBillDetails(billbean);
                
                setAllBillTable();
            } catch (SQLException ex) {
                Logger.getLogger(OrderFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtPaidKeyReleased

    private void setInvoiceTableForCheck(){
        int billno = (int) tableBill.getValueAt(tableBill.getSelectedRow(), 0);
        BillDetailsDAO bddao = new BillDetailsDAOImpl();
        try {
            tableInvoice.setModel(buildTableModel(bddao.viewAllBillDetailsBeanResultSet(billno)));
        } catch (Exception ex) {
            
        }
        
        BillDAO bdao = new BillDAOImpl();
        BillBean bb = new BillBean();
        bb.setBillNumber(billno);
        BillBean bean = bdao.getBillDetails(bb);
        
        lblBillNo.setText(String.valueOf(billno));
        
        if(bean.getAccountBean().getAccountId()==4){
            comboCustomer.setSelectedItem("Walking Away");
            txtCustomerName.setText(bean.getName());
            txtCustomerName.setEnabled(true);
        }else{
            comboCustomer.setSelectedItem(bean.getName());
            txtCustomerName.setEnabled(false);
        }
        
        lblTotal.setText(String.valueOf(bean.getTotal()));
        txtPaid.setText(String.valueOf(bean.getPaid()));
        
        float paid = Float.parseFloat(txtPaid.getText());
        float total = Float.parseFloat(lblTotal.getText());
        
        if(paid>=total){
            lblMsgForReturn.setText("واپس");
            float ret = paid-total;
            lblReturn.setText(String.valueOf(ret));
        }else{
            lblMsgForReturn.setText("بقايا");
            lblReturn.setText(String.valueOf(total-paid));
        }
        
    }
    private void tableBillMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableBillMouseClicked
        setInvoiceTableForCheck();
    }//GEN-LAST:event_tableBillMouseClicked

    private void tableBillKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableBillKeyReleased
        setInvoiceTableForCheck();
    }//GEN-LAST:event_tableBillKeyReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

    }//GEN-LAST:event_jButton4ActionPerformed

    private void tableProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableProductMouseClicked

    }//GEN-LAST:event_tableProductMouseClicked

    private void tableProductKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableProductKeyReleased

    }//GEN-LAST:event_tableProductKeyReleased

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        
        
    }//GEN-LAST:event_jButton7ActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed

    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnUpdate1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdate1ActionPerformed
        try{
            Connection con = Connectivity.connect();
            InputStream in = new FileInputStream("C:\\Users\\xyz\\Documents\\NetBeansProjects\\Business Report\\src\\business\\report\\Invoice.jrxml");
            JasperDesign jd = JRXmlLoader.load(in);
            String sql = "SELECT b.billdetails_id,p.productsindhi,p.size,b.quantity,b.price,(b.quantity*b.price),bi.customer_name,bi.total,bi.paid,bi.date,bi.customer_id,bi.bill_no FROM billdetails b,product p,bill bi WHERE b.product_id=p.id AND bi.bill_no=b.bill_no AND b.bill_no="+lblBillNo.getText();
            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(sql);
            jd.setQuery(newQuery);
            JasperReport jr = JasperCompileManager.compileReport(jd);
            HashMap para = new HashMap();
            JasperPrint j = JasperFillManager.fillReport(jr, para ,con);
            JasperViewer.viewReport(j, false);
            //File f = new File("C:\\Users\\xyz\\Desktop\\report\\ab.pdf");
            //OutputStreamWriter os = new OutputStreamWriter(new FileOutputStream(f), StandardCharsets.UTF_8);
            OutputStream os = new FileOutputStream(new File("C:\\Users\\xyz\\Desktop\\report\\ab.pdf"));

            JasperExportManager.exportReportToPdfStream(j, os);
            //JasperExportManager.exportReportToPdfFile(j,"C:\\Users\\xyz\\Desktop\\report\\hala.pdf");
            
            in.close();
            os.close();
        }catch(Exception x){
            
        }
    }//GEN-LAST:event_btnUpdate1ActionPerformed

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked

    }//GEN-LAST:event_jPanel2MouseClicked

    private void txtOrderNoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtOrderNoKeyReleased

    }//GEN-LAST:event_txtOrderNoKeyReleased

    private void btnNewOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewOrderActionPerformed

    }//GEN-LAST:event_btnNewOrderActionPerformed

    private void tableInvoiceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableInvoiceMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tableInvoiceMouseClicked

    private void tableInvoiceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableInvoiceKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tableInvoiceKeyReleased

    private void txtProSindhiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProSindhiKeyReleased
        try {
            ProductDAO productDAO = new ProductDAOImpl();
            ProductBean pb = new ProductBean();
            String nameSin = txtProSindhi.getText();
            pb.setProductNameSindhi(nameSin);
            tableProduct.setModel(buildTableModel(productDAO.viewAllProductResultSetForOrder(pb,1)));
        
            if(tableProduct.getRowCount()>0){
                String sval = (String) tableProduct.getValueAt(0,2);
                String eval = (String) tableProduct.getValueAt(tableProduct.getRowCount()-1,2);

                if(sval.equals(eval)){
                    ProductTableDialog ptd = new ProductTableDialog(this, rootPaneCheckingEnabled,sval);
                   
                    String billno = lblBillNo.getText();
                    billbean.setBillNumber(Integer.parseInt(billno)); //bill no
                    billbean.setAccountBean(accountbean); //customer id
                    String name = txtCustomerName.getText();
                    billbean.setName(name); //name
                    billdetailbean.setBillBean(billbean);
                    
                    ptd.setVisible(true);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtProSindhiKeyReleased

    private void txtProEngFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtProEngFocusGained
        try {
            setProductTable();
            setInvoiceTable();
            setAllBillTable();
               
            billbean.setTotal(Float.parseFloat(lblTotal.getText()));
            billbean.setPaid(Float.parseFloat(txtPaid.getText()));
            BillDAO bdao = new BillDAOImpl();
            bdao.updateBillDetails(billbean);
        
            setInvoiceTable();
            setAllBillTable();
        } catch (SQLException ex) {
            Logger.getLogger(OrderFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtProEngFocusGained

    private void txtProSindhiFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtProSindhiFocusGained
        try {
            setProductTable();
            setInvoiceTable();
            setAllBillTable();
               
            billbean.setTotal(Float.parseFloat(lblTotal.getText()));
            billbean.setPaid(Float.parseFloat(txtPaid.getText()));
            BillDAO bdao = new BillDAOImpl();
            bdao.updateBillDetails(billbean);
        
            setInvoiceTable();
            setAllBillTable();
        } catch (SQLException ex) {
            Logger.getLogger(OrderFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtProSindhiFocusGained

    private void txtCustomerNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCustomerNameKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCustomerNameKeyReleased

    private void comboCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCustomerActionPerformed
        AccountBean accountBean =  new AccountBean();
        String name = (String) comboCustomer.getSelectedItem();
        if(name.equals("Walking Away")){
            txtCustomerName.setText("");
            txtCustomerName.setEnabled(true);
        }else{
            txtCustomerName.setText(name);
            txtCustomerName.setEnabled(false);
        }
        accountBean.setAccountName(name);
        AccountDAO accountDAO = new AccountDAOImpl();
        int id = accountDAO.getIdByAccount(accountBean);
        accountbean.setAccountId(id);
    }//GEN-LAST:event_comboCustomerActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(OrderFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OrderFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OrderFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OrderFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new OrderFrame().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(OrderFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Category1;
    private javax.swing.JButton btnNewOrder;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnUpdate1;
    private javax.swing.JComboBox comboCustomer;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private static javax.swing.JLabel lblBillNo;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblMsgForReturn;
    private javax.swing.JLabel lblReturn;
    private javax.swing.JLabel lblReturn1;
    public static javax.swing.JLabel lblTotal;
    private static javax.swing.JLabel lblTotal1;
    private static javax.swing.JLabel lblTotal2;
    private javax.swing.JTable tableBill;
    public static javax.swing.JTable tableInvoice;
    private javax.swing.JTable tableProduct;
    private javax.swing.JTextField txtCustomerName;
    private javax.swing.JTextField txtOrderNo;
    public static javax.swing.JTextField txtPaid;
    public static javax.swing.JTextField txtProEng;
    public static javax.swing.JTextField txtProSindhi;
    // End of variables declaration//GEN-END:variables
}
