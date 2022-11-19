/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import model.MySQL;

/**
 *
 * @author namindu
 */
public class EmployeeStatus extends javax.swing.JPanel {

    /**
     * Creates new form EmployeeStatus
     */
    public void loadEmployees() {
        try {
            ResultSet rs = MySQL.search("SELECT * FROM `Employee` INNER JOIN `address` ON `Employee`.`address_id`=`address`.`id`  INNER JOIN `city` ON `address`.`city_id`=`city`.`id` INNER JOIN `Marital_Status` ON `Employee`.`Marital_Status_id`=`Marital_Status`.`id` INNER JOIN `gender` ON `Employee`.`gender_id`=`gender`.`id` INNER JOIN `status` ON `Employee`.`status_id`=`status`.`id`  ORDER BY `Employee`.`id` ASC ;");

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("id"));
                v.add(rs.getString("full_name"));
                v.add(rs.getString("name_with_initial"));
                v.add(rs.getString("nic"));
                v.add(rs.getString("mobile1"));
                v.add(rs.getString("mobile2"));
                v.add(rs.getString("email"));
                v.add(rs.getString("join_date"));
                v.add(rs.getString("date_of_birth"));
                v.add(rs.getString("line"));
                v.add(rs.getString("city.name"));
                v.add(rs.getString("Marital_Status.name"));
                v.add(rs.getString("Experience"));
                v.add(rs.getString("qualification"));
                v.add(rs.getString("gender.name"));
                v.add(rs.getString("status.name"));

                dtm.addRow(v);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadEmployees(String text) {
        try {

            ResultSet rs = MySQL.search("SELECT * FROM `Employee` INNER JOIN `address` ON `Employee`.`address_id`=`address`.`id`  INNER JOIN `city` ON `address`.`city_id`=`city`.`id` INNER JOIN `Marital_Status` ON `Employee`.`Marital_Status_id`=`Marital_Status`.`id` INNER JOIN `gender` ON `Employee`.`gender_id`=`gender`.`id` INNER JOIN `status` ON `Employee`.`status_id`=`status`.`id` WHERE `Employee`.`id` LIKE '" + text + "%' OR `Employee`.`full_name` LIKE '%" + text + "%' OR `Employee`.`name_with_initial` LIKE '" + text + "%' OR `Employee`.`nic` LIKE '" + text + "%' OR `Employee`.`email` LIKE '" + text + "%' OR `Employee`.`mobile1` LIKE '" + text + "%' ORDER BY `Employee`.`id` ASC ;");

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("id"));
                v.add(rs.getString("full_name"));
                v.add(rs.getString("name_with_initial"));
                v.add(rs.getString("nic"));
                v.add(rs.getString("mobile1"));
                v.add(rs.getString("mobile2"));
                v.add(rs.getString("email"));
                v.add(rs.getString("join_date"));
                v.add(rs.getString("date_of_birth"));
                v.add(rs.getString("line"));
                v.add(rs.getString("city.name"));
                v.add(rs.getString("Marital_Status.name"));
                v.add(rs.getString("Experience"));
                v.add(rs.getString("qualification"));
                v.add(rs.getString("gender.name"));
                v.add(rs.getString("status.name"));

                dtm.addRow(v);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
      public void setStatusButtonListner() {

        ListSelectionListener lsl = new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                int selectedRow = jTable1.getSelectedRow();

                if (selectedRow != -1) {

                    String id = jTable1.getValueAt(selectedRow, 0).toString();

                    if (id.equals("1")) {
                        jButton1.setEnabled(false);
                    } else {
                        jButton1.setEnabled(true);

                    }

                }

            }

        };
        jTable1.getSelectionModel().addListSelectionListener(lsl);

    }

    public EmployeeStatus() {
        initComponents();
        loadEmployees();
        setStatusButtonListner();
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
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Full Name", "Name (Initial)", "NIC", "Mobile", "Landline", "Email", "Join Date", "Date of Birh", "Address", "City", "Maritul Status", "Experience", "Qulifications", "Gender", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable1);

        jLabel18.setFont(new java.awt.Font("Quicksand", 1, 20)); // NOI18N
        jLabel18.setText("Search :");

        jTextField6.setFont(new java.awt.Font("Quicksand", 0, 20)); // NOI18N
        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField6KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField6KeyTyped(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Quicksand", 0, 22)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/EmployeeUpdate.png"))); // NOI18N
        jButton1.setText("Change Status");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Quicksand", 0, 36)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Change Employee Status");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(53, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1058, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField6)))
                .addGap(52, 52, 52))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(396, 396, 396)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel2)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTextField6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyReleased
        // TODO add your handling code here:
        String text = jTextField6.getText();
        loadEmployees(text);
    }//GEN-LAST:event_jTextField6KeyReleased

    private void jTextField6KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6KeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        int r = jTable1.getSelectedRow();

        if (r == -1) {
            JOptionPane
                    .showMessageDialog(this, "Please select a Employee", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            String id = jTable1.getValueAt(r, 0).toString();
            String status = jTable1.getValueAt(r, 15).toString();

            int status_id;

            if (status.equals("Active")) {
                status_id = 2;
            } else {
                status_id = 1;

            }

            MySQL.iud("UPDATE `Employee` SET `status_id`='"+status_id+"' WHERE `id` = '" + id + "'");

            loadEmployees();
        }

    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables
}
