/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;


import beans.ProductBean;
import dao.ProductDAO;
import daoImp.ProductDAOImpl;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author xyz
 */
public class ProductTableDialog extends javax.swing.JDialog {

    /**
     * Creates new form ProductTableDialog
     */
    public ProductTableDialog(java.awt.Frame parent, boolean modal, String val) {
        super(parent, modal);
        try {
            initComponents();
            setProductTable(val);
        } catch (SQLException ex) {
            Logger.getLogger(ProductTableDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void setProductTable(String val) throws SQLException {
        ProductDAO productDAO = new ProductDAOImpl();
        ProductBean pb = new ProductBean();
        pb.setProductName(val);
        tableProductChoose.setModel(buildTableModel(productDAO.viewAllProductResultSetForOrder(pb,0)));
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableProductChoose = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tableProductChoose.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        tableProductChoose.setModel(new javax.swing.table.DefaultTableModel(
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
        tableProductChoose.setRowHeight(30);
        tableProductChoose.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableProductChooseKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tableProductChoose);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 960, 500));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1000, 530));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tableProductChooseKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableProductChooseKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           
            int id = (int) tableProductChoose.getValueAt(tableProductChoose.getSelectedRow(), 0);
            String engProName = (String) tableProductChoose.getValueAt(tableProductChoose.getSelectedRow(), 2);
            int quan = (int) tableProductChoose.getValueAt(tableProductChoose.getSelectedRow(), 3);
            String size = (String) tableProductChoose.getValueAt(tableProductChoose.getSelectedRow(), 4);
            BigDecimal bigprice =  (BigDecimal) tableProductChoose.getValueAt(tableProductChoose.getSelectedRow(), 5);
            float price = bigprice.floatValue();
            String sinProName = (String) tableProductChoose.getValueAt(tableProductChoose.getSelectedRow(), 6);
            
//            ProductBean pb = new ProductBean();
//            pb.setProductId(id);
//            pb.setSize(size);
            
            OrderFrame.txtProEng.setText("");
            OrderFrame.txtProSindhi.setText("");
            
            //OrderFrame.pb2.setProductId(id);
            //OrderFrame.pb2.setSize(val);
            //OrderFrame.billDetailsBean.setProductBean(pb);
            //OrderFrame.bdb.setProductBean(pb);
            
//            OrderFrame.billDetailsBean.setProductBean(pb1);
//            OrderFrame.pb1.setSize(val);
            
            OrderFrame.probean.setProductId(id);
            OrderFrame.probean.setProductName(engProName);
            OrderFrame.probean.setQuantity(quan);
            OrderFrame.probean.setSize(size);
            OrderFrame.probean.setPrice(price);
            OrderFrame.probean.setProductNameSindhi(sinProName);
            
            OrderFrame.billdetailbean.setProductBean(OrderFrame.probean);
            
            this.hide();
            
            QuantityDialog qd = new QuantityDialog(null, rootPaneCheckingEnabled);
            qd.setVisible(true);
        }
    }//GEN-LAST:event_tableProductChooseKeyPressed

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
            java.util.logging.Logger.getLogger(ProductTableDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProductTableDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProductTableDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProductTableDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ProductTableDialog dialog = new ProductTableDialog(new javax.swing.JFrame(), true, "");
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableProductChoose;
    // End of variables declaration//GEN-END:variables
}