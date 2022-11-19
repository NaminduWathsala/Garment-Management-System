/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import java.awt.Color;
import static java.awt.Color.black;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.MySQL;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author namindu
 */
public class UserHandling extends javax.swing.JPanel {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    static String filename;

    public void loaduserType() {

        try {
            ResultSet rs = MySQL.search("SELECT `name` FROM `user_type`");

            Vector v = new Vector();
            v.add("Select");

            while (rs.next()) {
                v.add(rs.getString("name"));
            }

            jComboBox1.setModel(new DefaultComboBoxModel(v));
            jComboBox2.setModel(new DefaultComboBoxModel(v));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void reset() {
        jLabel47.setText("");
        jLabel49.setText("");
        jLabel51.setText("");
        jLabel53.setText("");
        jLabel55.setText("");
        jLabel57.setText("");

        jLabel47.setText("");
        jTextField2.setText("");
        jTextField4.setText("");
        jComboBox1.setSelectedIndex(0);
    }

    public void reset2() {
        jLabel34.setText("");
        jLabel66.setText("");
        jLabel59.setText("");
        jLabel66.setText("");
        jLabel63.setText("");
        jLabel65.setText("");
        jLabel62.setText("");
        jComboBox2.setSelectedIndex(0);
        jTextField3.setText("");
        jTextField5.setText("");
    }

    public void loadEmployees() {
        try {
            ResultSet rs = MySQL.search("SELECT * FROM `Employee` INNER JOIN `address` ON `Employee`.`address_id`=`address`.`id`  INNER JOIN `city` ON `address`.`city_id`=`city`.`id` INNER JOIN `Marital_Status` ON `Employee`.`Marital_Status_id`=`Marital_Status`.`id` INNER JOIN `gender` ON `Employee`.`gender_id`=`gender`.`id` INNER JOIN `status` ON `Employee`.`status_id`=`status`.`id` WHERE `status`.`id` != 2   ORDER BY `Employee`.`id` ASC ;");

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

                dtm.addRow(v);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadEmployees(String text) {
        try {

            ResultSet rs = MySQL.search("SELECT * FROM `Employee` INNER JOIN `address` ON `Employee`.`address_id`=`address`.`id`  INNER JOIN `city` ON `address`.`city_id`=`city`.`id` INNER JOIN `Marital_Status` ON `Employee`.`Marital_Status_id`=`Marital_Status`.`id` INNER JOIN `gender` ON `Employee`.`gender_id`=`gender`.`id` INNER JOIN `status` ON `Employee`.`status_id`=`status`.`id`  WHERE `Employee`.`id` LIKE '" + text + "%' OR `Employee`.`full_name` LIKE '%" + text + "%' OR `Employee`.`name_with_initial` LIKE '" + text + "%' OR `Employee`.`nic` LIKE '" + text + "%' OR `Employee`.`email` LIKE '" + text + "%' OR `Employee`.`mobile1` LIKE '" + text + "%' AND `status`.`id` != 1   ORDER BY `Employee`.`id` ASC ;");

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

                dtm.addRow(v);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadUsers() {
        try {
            ResultSet rs = MySQL.search("SELECT * FROM `user` INNER JOIN `user_type` ON `user_type`.`id` = `user`.`user_type_id` INNER JOIN `Employee` ON `Employee`.`id` = `user`.`Employee_id` INNER JOIN `gender` ON `gender`.`id` =`Employee`.`gender_id` ORDER BY `Employee`.`id` ASC;");

            DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
            dtm.setRowCount(0);

            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("user.id"));
                v.add(rs.getString("Employee.id"));
                v.add(rs.getString("Employee.full_name"));
                v.add(rs.getString("Employee.nic"));
                v.add(rs.getString("Employee.mobile1"));
                v.add(rs.getString("Employee.email"));
                v.add(rs.getString("gender.name"));
                v.add(rs.getString("user_type.name"));
                v.add(rs.getString("user.username"));
                v.add(rs.getString("user.password"));

                dtm.addRow(v);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadUsers(String text) {
        try {
            ResultSet rs = MySQL.search("SELECT * FROM `user` INNER JOIN `user_type` ON `user_type`.`id` = `user`.`user_type_id` INNER JOIN `Employee` ON `Employee`.`id` = `user`.`Employee_id` INNER JOIN `gender` ON `gender`.`id` =`Employee`.`gender_id` "
                    + " WHERE `user`.`id` LIKE '" + text + "%' OR `Employee`.`id` LIKE '" + text + "%' OR `Employee`.`full_name` LIKE '%" + text + "%' OR "
                    + "`gender`.`name` LIKE '" + text + "%' OR `Employee`.`nic` LIKE '" + text + "%' OR `Employee`.`email` LIKE '" + text + "%' "
                    + "OR `Employee`.`mobile1` LIKE '" + text + "%' OR `user_type`.`name` LIKE '" + text + "%' OR `user`.`username` LIKE '" + text + "%' OR `user`.`password` LIKE '" + text + "%' ORDER BY `user`.`id` ASC;");

            DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();
            dtm.setRowCount(0);

            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("user.id"));
                v.add(rs.getString("Employee.id"));
                v.add(rs.getString("Employee.full_name"));
                v.add(rs.getString("Employee.nic"));
                v.add(rs.getString("Employee.mobile1"));
                v.add(rs.getString("Employee.email"));
                v.add(rs.getString("gender.name"));
                v.add(rs.getString("user_type.name"));
                v.add(rs.getString("user.username"));
                v.add(rs.getString("user.password"));

                dtm.addRow(v);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UserHandling() {
        initComponents();
        loaduserType();
        loadEmployees();
        loadUsers();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel8 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jTextField4 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jTextField7 = new javax.swing.JTextField();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton11 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jButton13 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jComboBox2 = new javax.swing.JComboBox<>();
        jTextField3 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(71, 181, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 1, new java.awt.Color(255, 255, 255)));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel1MouseExited(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Quicksand", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("User Register");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(71, 181, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 1, new java.awt.Color(255, 255, 255)));
        jPanel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel3MouseExited(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Quicksand", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Search Users");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel9.setBackground(new java.awt.Color(71, 181, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(255, 255, 255)));
        jPanel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel9MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel9MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel9MouseExited(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Quicksand", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Update User");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel10.setBackground(new java.awt.Color(71, 181, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 1, new java.awt.Color(255, 255, 255)));
        jPanel10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel10MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel10MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel10MouseExited(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Quicksand", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Search Employees");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1160, 60));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Quicksand", 0, 36)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Register New Users");

        jTextField2.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Quicksand", 1, 18)); // NOI18N
        jLabel7.setText("Username :");

        jLabel11.setFont(new java.awt.Font("Quicksand", 1, 18)); // NOI18N
        jLabel11.setText("Password :");

        jLabel14.setFont(new java.awt.Font("Quicksand", 1, 18)); // NOI18N
        jLabel14.setText("User Type :");

        jComboBox1.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N

        jButton1.setFont(new java.awt.Font("Quicksand", 0, 22)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/add user.png"))); // NOI18N
        jButton1.setText("USER REGISTER");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton12.setFont(new java.awt.Font("Quicksand", 0, 22)); // NOI18N
        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Select employee.png"))); // NOI18N
        jButton12.setText("Select Employee");
        jButton12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jLabel46.setFont(new java.awt.Font("Quicksand", 1, 18)); // NOI18N
        jLabel46.setText("Employee Id :");

        jLabel47.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        jLabel47.setText("None");

        jLabel48.setFont(new java.awt.Font("Quicksand", 1, 18)); // NOI18N
        jLabel48.setText("Employee Name :");

        jLabel49.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        jLabel49.setText("None");

        jLabel50.setFont(new java.awt.Font("Quicksand", 1, 18)); // NOI18N
        jLabel50.setText("Employee NIC :");

        jLabel51.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        jLabel51.setText("None");

        jLabel52.setFont(new java.awt.Font("Quicksand", 1, 18)); // NOI18N
        jLabel52.setText("Employee Email :");

        jLabel53.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        jLabel53.setText("None");

        jLabel54.setFont(new java.awt.Font("Quicksand", 1, 18)); // NOI18N
        jLabel54.setText("Employee Mobile :");

        jLabel55.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        jLabel55.setText("None");

        jLabel56.setFont(new java.awt.Font("Quicksand", 1, 18)); // NOI18N
        jLabel56.setText("Employee Gender :");

        jLabel57.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        jLabel57.setText("None");

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        jTextField4.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1046, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel56)
                                        .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(12, 12, 12))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                            .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addComponent(jButton12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1046, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(292, 292, 292)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                    .addComponent(jLabel11)
                                    .addGap(25, 25, 25)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(42, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel2)
                .addGap(30, 30, 30)
                .addComponent(jButton12)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel46)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel48)
                        .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel50)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel52)))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel54)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel56)
                        .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(48, 48, 48)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(192, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("User Register", jPanel5);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

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

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Full Name", "Name (Initial)", "NIC", "Mobile", "Landline", "Email", "Join Date", "Date of Birh", "Address", "City", "Maritul Status", "Experience", "Qulifications", "Gender"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
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
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(2).setHeaderValue("Name (Initial)");
            jTable1.getColumnModel().getColumn(5).setHeaderValue("Landline");
            jTable1.getColumnModel().getColumn(7).setHeaderValue("Join Date");
            jTable1.getColumnModel().getColumn(8).setHeaderValue("Date of Birh");
            jTable1.getColumnModel().getColumn(9).setHeaderValue("Address");
            jTable1.getColumnModel().getColumn(10).setHeaderValue("City");
            jTable1.getColumnModel().getColumn(11).setHeaderValue("Maritul Status");
            jTable1.getColumnModel().getColumn(12).setHeaderValue("Experience");
            jTable1.getColumnModel().getColumn(13).setHeaderValue("Qulifications");
        }

        jButton5.setFont(new java.awt.Font("Quicksand", 0, 22)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/add user.png"))); // NOI18N
        jButton5.setText("User Register");
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 953, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1109, Short.MAX_VALUE)
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Search Employees", jPanel4);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jTextField7.setFont(new java.awt.Font("Quicksand", 0, 20)); // NOI18N
        jTextField7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField7KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField7KeyTyped(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User Id", "Employee Id", "Full Name", "NIC", "Mobile", "Email", "Gender", "User Type", "User Name", "Password"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(jTable2);

        jButton11.setFont(new java.awt.Font("Quicksand", 0, 22)); // NOI18N
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/update user.png"))); // NOI18N
        jButton11.setText("User Update");
        jButton11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Quicksand", 1, 20)); // NOI18N
        jLabel19.setText("Search :");

        jButton14.setFont(new java.awt.Font("Quicksand", 0, 22)); // NOI18N
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Report.png"))); // NOI18N
        jButton14.setText("Make a Report");
        jButton14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel19)
                        .addGap(26, 26, 26)
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 953, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 1109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 1109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 1109, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 526, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Search Users", jPanel6);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jButton13.setFont(new java.awt.Font("Quicksand", 0, 22)); // NOI18N
        jButton13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Select User.png"))); // NOI18N
        jButton13.setText("Select User");
        jButton13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Quicksand", 0, 36)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Update User :");

        jLabel58.setFont(new java.awt.Font("Quicksand", 1, 18)); // NOI18N
        jLabel58.setText("Employee Name :");

        jLabel59.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        jLabel59.setText("None");

        jLabel60.setFont(new java.awt.Font("Quicksand", 1, 18)); // NOI18N
        jLabel60.setText("Employee Email :");

        jLabel61.setFont(new java.awt.Font("Quicksand", 1, 18)); // NOI18N
        jLabel61.setText("Employee Gender :");

        jLabel62.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        jLabel62.setText("None");

        jLabel63.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        jLabel63.setText("None");

        jLabel64.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        jLabel64.setText("None");

        jLabel65.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        jLabel65.setText("None");

        jLabel66.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        jLabel66.setText("None");

        jLabel67.setFont(new java.awt.Font("Quicksand", 1, 18)); // NOI18N
        jLabel67.setText("Employee Id :");

        jLabel68.setFont(new java.awt.Font("Quicksand", 1, 18)); // NOI18N
        jLabel68.setText("Employee NIC :");

        jLabel69.setFont(new java.awt.Font("Quicksand", 1, 18)); // NOI18N
        jLabel69.setText("Employee Mobile :");

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        jComboBox2.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N

        jTextField3.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Quicksand", 1, 18)); // NOI18N
        jLabel12.setText("Password :");

        jLabel8.setFont(new java.awt.Font("Quicksand", 1, 18)); // NOI18N
        jLabel8.setText("Username :");

        jLabel15.setFont(new java.awt.Font("Quicksand", 1, 18)); // NOI18N
        jLabel15.setText("User Type :");

        jButton4.setFont(new java.awt.Font("Quicksand", 0, 22)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/update user.png"))); // NOI18N
        jButton4.setText("UPDATE USER");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Quicksand", 0, 36)); // NOI18N

        jTextField5.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel61)
                                    .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                        .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jButton13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1046, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(292, 292, 292)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                                    .addComponent(jLabel12)
                                    .addGap(25, 25, 25)
                                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(42, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator2))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addComponent(jButton13)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel67)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel58)
                        .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel68)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel60)))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel69)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel61)
                        .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(192, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Update User", jPanel7);

        add(jTabbedPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 1160, 820));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        String id = jLabel47.getText();
        String userName = jTextField2.getText();
        String password = jTextField4.getText();
        String userType = jComboBox1.getSelectedItem().toString();

        if (id.equals("")) {
            JOptionPane.showMessageDialog(this, "Please Select Employee", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (userType.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please Select User Type", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (userName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Plese enter Username", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Plese enter Password", "Warning", JOptionPane.WARNING_MESSAGE);

        } else {

            try {

                ResultSet rs = MySQL.search("SELECT * FROM `user` WHERE `Employee_id` = '" + id + "' ");

                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "This Employee is already a User", "Warning", JOptionPane.WARNING_MESSAGE);

                } else {

                    ResultSet rs1 = MySQL.search("SELECT * FROM `user_type` WHERE  `name`='" + userType + "' ");
                    rs1.next();
                    String userType_id = rs1.getString("id");

                    MySQL.iud("INSERT INTO `user`( `Employee_id`, `user_type_id`, `username`, `password`) VALUES ('" + id + "','" + userType_id + "','" + userName + "','" + password + "')");

                    reset();
                    loadUsers();

                    JOptionPane.showMessageDialog(this, "New User Added", "Success", JOptionPane.INFORMATION_MESSAGE);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:

        int r = jTable1.getSelectedRow();
        if (r == -1) {
            JOptionPane.showMessageDialog(this, "Please select a Invoice", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            try {

                String id = jTable1.getValueAt(r, 0).toString();
                String fn = jTable1.getValueAt(r, 1).toString();
                String nic = jTable1.getValueAt(r, 3).toString();
                String mobile = jTable1.getValueAt(r, 4).toString();
                String email = jTable1.getValueAt(r, 6).toString();
                String gender = jTable1.getValueAt(r, 14).toString();

                jLabel47.setText(id);
                jLabel49.setText(fn);
                jLabel51.setText(nic);
                jLabel53.setText(email);
                jLabel55.setText(mobile);
                jLabel57.setText(gender);

                jTabbedPane2.setSelectedIndex(0);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:

        if (evt.getClickCount() == 2) {
            int r = jTable1.getSelectedRow();

            if (r == -1) {
                JOptionPane.showMessageDialog(this, "Please select a Invoice", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {

                try {

                    String id = jTable1.getValueAt(r, 0).toString();
                    String fn = jTable1.getValueAt(r, 1).toString();
                    String nic = jTable1.getValueAt(r, 3).toString();
                    String mobile = jTable1.getValueAt(r, 4).toString();
                    String email = jTable1.getValueAt(r, 6).toString();
                    String gender = jTable1.getValueAt(r, 14).toString();

                    jLabel47.setText(id);
                    jLabel49.setText(fn);
                    jLabel51.setText(nic);
                    jLabel53.setText(email);
                    jLabel55.setText(mobile);
                    jLabel57.setText(gender);

                    jTabbedPane2.setSelectedIndex(0);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

    }//GEN-LAST:event_jTable1MouseClicked

    private void jTextField6KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_jTextField6KeyTyped

    private void jTextField6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyReleased
        // TODO add your handling code here:
        String text = jTextField6.getText();
        loadEmployees(text);
    }//GEN-LAST:event_jTextField6KeyReleased

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        // TODO add your handling code here:
        jTabbedPane2.setSelectedIndex(0);
    }//GEN-LAST:event_jPanel1MouseClicked

    private void jPanel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseEntered
        // TODO add your handling code here:
        Color clr = new Color(223, 246, 255);
        jPanel1.setBackground(clr);
        jLabel1.setForeground(Color.BLACK);
    }//GEN-LAST:event_jPanel1MouseEntered

    private void jPanel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseExited
        // TODO add your handling code here:
        Color clr = new Color(71, 181, 255);
        jPanel1.setBackground(clr);
        jLabel1.setForeground(Color.WHITE);
    }//GEN-LAST:event_jPanel1MouseExited

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked
        // TODO add your handling code here:
        jTabbedPane2.setSelectedIndex(2);
    }//GEN-LAST:event_jPanel3MouseClicked

    private void jPanel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseEntered
        // TODO add your handling code here:
        Color clr = new Color(223, 246, 255);
        jPanel3.setBackground(clr);
        jLabel3.setForeground(Color.BLACK);
    }//GEN-LAST:event_jPanel3MouseEntered

    private void jPanel3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseExited
        // TODO add your handling code here:
        Color clr = new Color(71, 181, 255);
        jPanel3.setBackground(clr);
        jLabel3.setForeground(Color.WHITE);
    }//GEN-LAST:event_jPanel3MouseExited

    private void jPanel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseClicked
        // TODO add your handling code here:
        jTabbedPane2.setSelectedIndex(3);
    }//GEN-LAST:event_jPanel9MouseClicked

    private void jPanel9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseEntered
        // TODO add your handling code here:
        Color clr = new Color(223, 246, 255);
        jPanel9.setBackground(clr);
        jLabel4.setForeground(Color.BLACK);
    }//GEN-LAST:event_jPanel9MouseEntered

    private void jPanel9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseExited
        // TODO add your handling code here:
        Color clr = new Color(71, 181, 255);
        jPanel9.setBackground(clr);
        jLabel4.setForeground(Color.WHITE);
    }//GEN-LAST:event_jPanel9MouseExited

    private void jPanel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseClicked
        // TODO add your handling code here:
        jTabbedPane2.setSelectedIndex(1);
    }//GEN-LAST:event_jPanel10MouseClicked

    private void jPanel10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseEntered
        // TODO add your handling code here:
        Color clr = new Color(223, 246, 255);
        jPanel10.setBackground(clr);
        jLabel5.setForeground(Color.BLACK);
    }//GEN-LAST:event_jPanel10MouseEntered

    private void jPanel10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseExited
        // TODO add your handling code here:
        Color clr = new Color(71, 181, 255);
        jPanel10.setBackground(clr);
        jLabel5.setForeground(Color.WHITE);
    }//GEN-LAST:event_jPanel10MouseExited

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        jTabbedPane2.setSelectedIndex(1);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jTextField7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyReleased
        // TODO add your handling code here:
        String text = jTextField7.getText();
        loadUsers(text);
    }//GEN-LAST:event_jTextField7KeyReleased

    private void jTextField7KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7KeyTyped

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // TODO add your handling code here:

        if (evt.getClickCount() == 2) {
            int r = jTable2.getSelectedRow();

            if (r == -1) {
                JOptionPane.showMessageDialog(this, "Please select a Invoice", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {

                try {

                    String uid = jTable2.getValueAt(r, 0).toString();
                    String eid = jTable2.getValueAt(r, 1).toString();
                    String fn = jTable2.getValueAt(r, 2).toString();
                    String nic = jTable2.getValueAt(r, 3).toString();
                    String mobile = jTable2.getValueAt(r, 4).toString();
                    String email = jTable2.getValueAt(r, 5).toString();
                    String gender = jTable2.getValueAt(r, 6).toString();
                    String ut = jTable2.getValueAt(r, 7).toString();
                    String un = jTable2.getValueAt(r, 8).toString();
                    String p = jTable2.getValueAt(r, 9).toString();

                    jLabel34.setText(uid);
                    jLabel66.setText(eid);
                    jLabel59.setText(fn);
                    jLabel64.setText(nic);
                    jLabel63.setText(email);
                    jLabel65.setText(mobile);
                    jLabel62.setText(gender);
                    jComboBox2.setSelectedItem(ut);
                    jTextField3.setText(un);
                    jTextField5.setText(p);

                    jTabbedPane2.setSelectedIndex(3);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

    }//GEN-LAST:event_jTable2MouseClicked

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        int r = jTable2.getSelectedRow();

        if (r == -1) {
            JOptionPane.showMessageDialog(this, "Please select a Invoice", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            try {

                String uid = jTable2.getValueAt(r, 0).toString();
                String eid = jTable2.getValueAt(r, 1).toString();
                String fn = jTable2.getValueAt(r, 2).toString();
                String nic = jTable2.getValueAt(r, 3).toString();
                String mobile = jTable2.getValueAt(r, 4).toString();
                String email = jTable2.getValueAt(r, 5).toString();
                String gender = jTable2.getValueAt(r, 6).toString();
                String ut = jTable2.getValueAt(r, 7).toString();
                String un = jTable2.getValueAt(r, 8).toString();
                String p = jTable2.getValueAt(r, 9).toString();

                jLabel34.setText(uid);
                jLabel66.setText(eid);
                jLabel59.setText(fn);
                jLabel66.setText(nic);
                jLabel63.setText(email);
                jLabel65.setText(mobile);
                jLabel62.setText(gender);
                jComboBox2.setSelectedItem(ut);
                jTextField3.setText(un);
                jTextField5.setText(p);

                jTabbedPane2.setSelectedIndex(3);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        jTabbedPane2.setSelectedIndex(2);
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:

        String id = jLabel34.getText();
        String eid = jLabel66.getText();
        String userName = jTextField3.getText();
        String password = jTextField5.getText();
        String userType = jComboBox2.getSelectedItem().toString();

        if (id.equals("")) {
            JOptionPane.showMessageDialog(this, "Please Select User", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (userType.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please Select User Type", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (userName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Plese enter Username", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Plese enter Password", "Warning", JOptionPane.WARNING_MESSAGE);

        } else {

            try {

                ResultSet rs1 = MySQL.search("SELECT * FROM `user_type` WHERE  `name`='" + userType + "' ");
                rs1.next();
                String userType_id = rs1.getString("id");

                MySQL.iud("UPDATE `user` SET `user_type_id`='" + userType_id + "',`username`='" + userName + "',`password`='" + password + "' WHERE `id` = '" + id + "' ");
                jTabbedPane2.setSelectedIndex(2);

                reset2();
                loadUsers();

                JOptionPane.showMessageDialog(this, "New User Updated", "Success", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:

        try {
            
                  InputStream filepath = UserHandling.class.getResourceAsStream("/reports/users.jrxml");
            JasperReport jr = JasperCompileManager.compileReport(filepath);
            
//            JasperReport jr = JasperCompileManager.compileReport("src//reports//users.jrxml");

            HashMap parameters = new HashMap();


//                JREmptyDataSource dataSource = new JREmptyDataSource();
            TableModel tm = jTable2.getModel();
            JRTableModelDataSource dataSource = new JRTableModelDataSource(tm);

            JasperPrint jp = JasperFillManager.fillReport(jr, parameters, dataSource);

            JasperViewer.viewReport(jp, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton14ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    // End of variables declaration//GEN-END:variables
}
