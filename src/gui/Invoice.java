package gui;

import java.awt.Color;
import static java.awt.Color.black;
import java.io.InputStream;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.MySQL;
import net.sf.jasperreports.engine.JREmptyDataSource;
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
public class Invoice extends javax.swing.JPanel {

    double total;
    DecimalFormat df = new DecimalFormat("0.00");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public void updateTotal() {
        double total = 0;

        for (int i = 0; i < jTable4.getRowCount(); i++) {
            String t = jTable4.getValueAt(i, 11).toString();
            total = total + Double.parseDouble(t);
        }

        jLabel88.setText(df.format(total));
        Double totalcost = total * Double.parseDouble(jTextField1.getText());
        jLabel65.setText(df.format(totalcost));

    }

    public void loadPaymentType() {

        try {
            ResultSet rs = MySQL.search("SELECT `name` FROM `payment_type`");

            Vector v = new Vector();
            v.add("Select");

            while (rs.next()) {
                v.add(rs.getString("name"));
            }

            jComboBox6.setModel(new DefaultComboBoxModel(v));
            jComboBox14.setModel(new DefaultComboBoxModel(v));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loadAType() {

        try {
            ResultSet rs = MySQL.search("SELECT `name` FROM `customer_age_type`");

            Vector v = new Vector();
            v.add("Select");

            while (rs.next()) {
                v.add(rs.getString("name"));
            }

            jComboBox1.setModel(new DefaultComboBoxModel(v));
            jComboBox7.setModel(new DefaultComboBoxModel(v));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loadCWType() {

        try {
            ResultSet rs = MySQL.search("SELECT `name` FROM `customer_cloth_ware_type`");

            Vector v = new Vector();
            v.add("Select");

            while (rs.next()) {
                v.add(rs.getString("name"));
            }

            jComboBox3.setModel(new DefaultComboBoxModel(v));
            jComboBox13.setModel(new DefaultComboBoxModel(v));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loadCType() {

        try {
            ResultSet rs = MySQL.search("SELECT `name` FROM `customer_cloth_type`");

            Vector v = new Vector();
            v.add("Select");

            while (rs.next()) {
                v.add(rs.getString("name"));
            }

            jComboBox4.setModel(new DefaultComboBoxModel(v));
            jComboBox10.setModel(new DefaultComboBoxModel(v));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loadCTPType() {

        try {
            ResultSet rs = MySQL.search("SELECT `name` FROM `customer_cloth_time_period`");

            Vector v = new Vector();
            v.add("Select");

            while (rs.next()) {
                v.add(rs.getString("name"));
            }

            jComboBox5.setModel(new DefaultComboBoxModel(v));
            jComboBox12.setModel(new DefaultComboBoxModel(v));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loadSTType() {

        try {
            ResultSet rs = MySQL.search("SELECT `name` FROM `customer_sex_type`");

            Vector v = new Vector();
            v.add("Select");

            while (rs.next()) {
                v.add(rs.getString("name"));
            }

            jComboBox2.setModel(new DefaultComboBoxModel(v));
            jComboBox11.setModel(new DefaultComboBoxModel(v));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void reset() {

        jLabel15.setText("");
        jTextField1.setText("");
        jTextField3.setText("");

    }

    public void resetFields() {

        jTextField4.setText("");
        jLabel33.setText("None");
        jLabel72.setText("None");
        jLabel71.setText("None");
        jLabel68.setText("None");
        jLabel75.setText("None");
        jLabel63.setText("None");
        jLabel67.setText("None");
        jLabel80.setText("None");
        jLabel78.setText("None");
        jLabel76.setText("None");
        jTextField4.grabFocus();
    }

    public void resetFields2() {

        jTextField1.setText("");
        jTextField3.setText("");
        jTextField1.setEnabled(true);
        jTextField3.setEnabled(true);
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        jComboBox3.setSelectedIndex(0);
        jComboBox4.setSelectedIndex(0);
        jComboBox5.setSelectedIndex(0);
        jTextArea1.setText("");
        jDateChooser2.setDate(null);

    }

    public void loadInvoices() {
        try {
            ResultSet rs = MySQL.search("SELECT * FROM `customer_order` INNER JOIN `customer` ON `customer`.`id` = `customer_order`.`customer_id` INNER JOIN `customer_company_branch` ON `customer`.`customer_company_branch_id` = `customer_company_branch`.`id` INNER JOIN `customer_order_payment` ON `customer_order_payment`.`customer_order_id` = `customer_order`.`id` ORDER BY `customer_order`.`id` ASC ;");

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("customer_order.id"));
                v.add(rs.getString("customer_order.customer_id"));
                v.add(rs.getString("customer.name"));
                v.add(rs.getString("customer.mobile"));
                v.add(rs.getString("customer.email"));
                v.add(rs.getString("customer.nic"));
                v.add(rs.getString("customer_company_branch.name"));
                v.add(rs.getString("customer_order.unit_count"));
                v.add(rs.getString("customer_order.order_date"));
                v.add(rs.getString("customer_order.deadline_date"));
                v.add(rs.getString("customer_order_payment.id"));
                v.add(rs.getString("customer_order.advance"));
                v.add(rs.getString("customer_order_payment.payment"));
                v.add(rs.getString("customer_order_payment.balance"));

                dtm.addRow(v);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadInvoices(String text) {
        try {

            ResultSet rs = MySQL.search("SELECT * FROM `customer_order` INNER JOIN `customer` ON `customer`.`id` = `customer_order`.`customer_id` INNER JOIN `customer_company_branch` ON `customer`.`customer_company_branch_id` = `customer_company_branch`.`id` INNER JOIN `customer_order_payment` ON `customer_order_payment`.`customer_order_id` = `customer_order`.`id` "
                    + "WHERE `customer_order`.`id` LIKE '" + text + "%' OR `customer_order`.`customer_id` LIKE '" + text + "%' OR `customer`.`name` LIKE '" + text + "%' OR"
                    + " `customer`.`mobile` LIKE '" + text + "%' OR `customer`.`email` LIKE '" + text + "%' OR `customer`.`nic` LIKE '" + text + "%'"
                    + " OR `customer_company_branch`.`name` LIKE '" + text + "%' OR `customer_order`.`unit_count` LIKE '" + text + "%'"
                    + " OR `customer_order`.`advance` LIKE '" + text + "%' ORDER BY `customer_order`.`id` ASC ;");

            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("customer_order.id"));
                v.add(rs.getString("customer_order.customer_id"));
                v.add(rs.getString("customer.name"));
                v.add(rs.getString("customer.mobile"));
                v.add(rs.getString("customer.email"));
                v.add(rs.getString("customer.nic"));
                v.add(rs.getString("customer_company_branch.name"));
                v.add(rs.getString("customer_order.unit_count"));
                v.add(rs.getString("customer_order.order_date"));
                v.add(rs.getString("customer_order.deadline_date"));
                v.add(rs.getString("customer_order_payment.id"));
                v.add(rs.getString("customer_order.advance"));
                v.add(rs.getString("customer_order_payment.payment"));
                v.add(rs.getString("customer_order_payment.balance"));

                dtm.addRow(v);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Invoice() {
        initComponents();
        loadInvoices();
        loadSTType();
        loadAType();
        loadCWType();
        loadCType();
        loadCTPType();
        loadPaymentType();
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
        jPanel10 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jSeparator4 = new javax.swing.JSeparator();
        jButton6 = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jButton8 = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox<>();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jButton11 = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        jComboBox7 = new javax.swing.JComboBox<>();
        jComboBox10 = new javax.swing.JComboBox<>();
        jLabel102 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jLabel104 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        jLabel106 = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        jComboBox11 = new javax.swing.JComboBox<>();
        jComboBox12 = new javax.swing.JComboBox<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel108 = new javax.swing.JLabel();
        jComboBox13 = new javax.swing.JComboBox<>();
        jLabel109 = new javax.swing.JLabel();
        jLabel110 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jTextField11 = new javax.swing.JTextField();
        jLabel131 = new javax.swing.JLabel();
        jLabel132 = new javax.swing.JLabel();
        jLabel133 = new javax.swing.JLabel();
        jLabel134 = new javax.swing.JLabel();
        jLabel135 = new javax.swing.JLabel();
        jLabel136 = new javax.swing.JLabel();
        jComboBox14 = new javax.swing.JComboBox<>();
        jLabel137 = new javax.swing.JLabel();
        jLabel138 = new javax.swing.JLabel();
        jLabel139 = new javax.swing.JLabel();
        jLabel140 = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel141 = new javax.swing.JLabel();
        jLabel142 = new javax.swing.JLabel();
        jLabel143 = new javax.swing.JLabel();
        jLabel144 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();

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
        jLabel1.setFont(new java.awt.Font("Quicksand", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Create Invoice");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
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
        jLabel3.setFont(new java.awt.Font("Quicksand", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Update Invoice");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        jLabel5.setFont(new java.awt.Font("Quicksand", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Search Invoice");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
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
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 60));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jButton1.setFont(new java.awt.Font("Quicksand", 0, 22)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/print.png"))); // NOI18N
        jButton1.setText("Print Invoice");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/Select customer.png"))); // NOI18N
        jButton4.setText(" Select Customer");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Quicksand", 1, 16)); // NOI18N
        jLabel7.setText("Customer Id :");

        jLabel10.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        jLabel10.setText("None");

        jLabel16.setFont(new java.awt.Font("Quicksand", 1, 16)); // NOI18N
        jLabel16.setText("Customer Mobile :");

        jLabel15.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        jLabel15.setText("None");

        jLabel14.setFont(new java.awt.Font("Quicksand", 1, 16)); // NOI18N
        jLabel14.setText("Customer email :");

        jLabel17.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        jLabel17.setText("None");

        jLabel12.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        jLabel12.setText("None");

        jLabel11.setFont(new java.awt.Font("Quicksand", 1, 16)); // NOI18N
        jLabel11.setText("Customer Name :");

        jLabel20.setFont(new java.awt.Font("Quicksand", 1, 16)); // NOI18N
        jLabel20.setText("Customer NIC :");

        jLabel22.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        jLabel22.setText("None");

        jLabel23.setFont(new java.awt.Font("Quicksand", 1, 16)); // NOI18N
        jLabel23.setText("Customer C. Branch :");

        jLabel24.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        jLabel24.setText("None");

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));

        jLabel8.setFont(new java.awt.Font("Quicksand", 1, 16)); // NOI18N
        jLabel8.setText("Age Type :");

        jLabel25.setFont(new java.awt.Font("Quicksand", 1, 16)); // NOI18N
        jLabel25.setText("Gender Type :");

        jComboBox1.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N

        jComboBox2.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Quicksand", 1, 16)); // NOI18N
        jLabel9.setText("Weir Type :");

        jComboBox3.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N

        jLabel26.setFont(new java.awt.Font("Quicksand", 1, 16)); // NOI18N
        jLabel26.setText("Cloth Type :");

        jComboBox4.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N

        jLabel19.setFont(new java.awt.Font("Quicksand", 1, 16)); // NOI18N
        jLabel19.setText("Time Period :");

        jComboBox5.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N

        jTextField1.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Quicksand", 1, 16)); // NOI18N
        jLabel28.setText("Deadline :");

        jTextField3.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField3KeyTyped(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Quicksand", 1, 16)); // NOI18N
        jLabel29.setText("Description :");

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane3.setViewportView(jTextArea1);

        jLabel30.setFont(new java.awt.Font("Quicksand", 1, 16)); // NOI18N
        jLabel30.setText("Unit Count :");

        jLabel31.setFont(new java.awt.Font("Quicksand", 1, 16)); // NOI18N
        jLabel31.setText("Advance :");

        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));

        jButton6.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/select supplies.png"))); // NOI18N
        jButton6.setText(" Select Supplies");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Quicksand", 1, 16)); // NOI18N
        jLabel32.setText("SID :");

        jLabel33.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        jLabel33.setText("None");

        jLabel62.setFont(new java.awt.Font("Quicksand", 1, 16)); // NOI18N
        jLabel62.setText("Colour :");

        jLabel63.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        jLabel63.setText("None");

        jLabel66.setFont(new java.awt.Font("Quicksand", 1, 16)); // NOI18N
        jLabel66.setText("Name :");

        jLabel67.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        jLabel67.setText("None");

        jLabel68.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        jLabel68.setText("None");

        jLabel69.setFont(new java.awt.Font("Quicksand", 1, 16)); // NOI18N
        jLabel69.setText("Type :");

        jLabel70.setFont(new java.awt.Font("Quicksand", 1, 16)); // NOI18N
        jLabel70.setText("Brand :");

        jLabel71.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        jLabel71.setText("None");

        jLabel72.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        jLabel72.setText("None");

        jLabel73.setFont(new java.awt.Font("Quicksand", 1, 16)); // NOI18N
        jLabel73.setText("PID :");

        jLabel74.setFont(new java.awt.Font("Quicksand", 1, 16)); // NOI18N
        jLabel74.setText("Cloth Type :");

        jLabel75.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        jLabel75.setText("None");

        jLabel76.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        jLabel76.setText("None");

        jLabel77.setFont(new java.awt.Font("Quicksand", 1, 16)); // NOI18N
        jLabel77.setText("Price :");

        jLabel78.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        jLabel78.setText("None");

        jLabel79.setFont(new java.awt.Font("Quicksand", 1, 16)); // NOI18N
        jLabel79.setText("Count or Yards :");

        jLabel80.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        jLabel80.setText("None");

        jLabel81.setFont(new java.awt.Font("Quicksand", 1, 16)); // NOI18N
        jLabel81.setText("Weight :");

        jLabel82.setFont(new java.awt.Font("Quicksand", 1, 16)); // NOI18N
        jLabel82.setText("Yards or Count for the Product :");

        jTextField4.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField4KeyTyped(evt);
            }
        });

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Type", "Stock Id", "Product Id", "Brand", "Cloth Type", "Colour", "Name", "Yards or Count", "Weight", "Total Yards or Count", "Total Weight", "Cost"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(jTable4);
        if (jTable4.getColumnModel().getColumnCount() > 0) {
            jTable4.getColumnModel().getColumn(7).setHeaderValue("Yards or Count");
            jTable4.getColumnModel().getColumn(8).setHeaderValue("Weight");
        }

        jButton8.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/add to invoice.png"))); // NOI18N
        jButton8.setText("Add To Invoice");
        jButton8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));

        jLabel64.setFont(new java.awt.Font("Quicksand", 1, 16)); // NOI18N
        jLabel64.setText("Total Cost :");

        jLabel65.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        jLabel65.setText("0.00");

        jComboBox6.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        jComboBox6.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox6ItemStateChanged(evt);
            }
        });

        jLabel83.setFont(new java.awt.Font("Quicksand", 1, 16)); // NOI18N
        jLabel83.setText("Payment Method :");

        jLabel84.setFont(new java.awt.Font("Quicksand", 1, 16)); // NOI18N
        jLabel84.setText("Balance :");

        jLabel85.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        jLabel85.setText("0.00");

        jTextField7.setEditable(false);
        jTextField7.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        jTextField7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField7KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField7KeyTyped(evt);
            }
        });

        jLabel86.setFont(new java.awt.Font("Quicksand", 1, 16)); // NOI18N
        jLabel86.setText("Payment :");

        jLabel87.setFont(new java.awt.Font("Quicksand", 1, 16)); // NOI18N
        jLabel87.setText("Cost Per Items :");

        jLabel88.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        jLabel88.setText("0.00");

        jLabel89.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        jLabel89.setText("0.00");

        jLabel90.setFont(new java.awt.Font("Quicksand", 1, 16)); // NOI18N
        jLabel90.setText("Profit :");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 1057, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jLabel7)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel11)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel16)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jLabel14)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel20)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel23)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 1049, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jLabel8)
                        .addGap(40, 40, 40)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel9)
                        .addGap(12, 12, 12)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 1057, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 605, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel82)
                        .addGap(12, 12, 12)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel33)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel73)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel72)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel70)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel71, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel69)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel74)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel62)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel81)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel79)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel77)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 1057, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1056, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 1057, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel87)
                            .addComponent(jLabel83)
                            .addComponent(jLabel90))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel64)
                            .addComponent(jLabel86)
                            .addComponent(jLabel84))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addComponent(jLabel26)
                                .addGap(29, 29, 29)
                                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel31))
                                .addGap(3, 3, 3)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel29)
                            .addComponent(jLabel19))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jScrollPane3)
                                .addGap(1, 1, 1))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(jLabel28)
                                .addGap(18, 18, 18)
                                .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(55, 55, 55))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel16)
                    .addComponent(jLabel15))
                .addGap(6, 6, 6)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel17)
                    .addComponent(jLabel20)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24))
                .addGap(6, 6, 6)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel25)
                            .addComponent(jLabel9))))
                .addGap(9, 9, 9)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26)
                            .addComponent(jLabel19))))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel30)
                        .addGap(13, 13, 13)
                        .addComponent(jLabel31))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel82))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel32)
                            .addComponent(jLabel73)
                            .addComponent(jLabel72)
                            .addComponent(jLabel70)
                            .addComponent(jLabel71)
                            .addComponent(jLabel69)
                            .addComponent(jLabel68)
                            .addComponent(jLabel74)
                            .addComponent(jLabel75)
                            .addComponent(jLabel62)
                            .addComponent(jLabel63))))
                .addGap(6, 6, 6)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel66)
                    .addComponent(jLabel67)
                    .addComponent(jLabel81)
                    .addComponent(jLabel80)
                    .addComponent(jLabel79)
                    .addComponent(jLabel78)
                    .addComponent(jLabel77)
                    .addComponent(jLabel76))
                .addGap(12, 12, 12)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel87)
                                .addGap(11, 11, 11)
                                .addComponent(jLabel83)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel90))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel64)
                                .addGap(11, 11, 11)
                                .addComponent(jLabel86)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel84))))))
        );

        jTabbedPane2.addTab("Invoice Register", jPanel5);

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
                "Order Id", "Customer Id", "Customer Name", "Customer Mobile", "Customer Email", "Customer NIC", "Company Branch Name", "Unit Count", "Order Date", "Deadline Date", "Payment Id", "Advance", "Payment", "Balance"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
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

        jButton5.setFont(new java.awt.Font("Quicksand", 0, 22)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/update invoice.png"))); // NOI18N
        jButton5.setText("Update Invoice");
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
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 1109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGap(55, 55, 55)
                            .addComponent(jLabel18)
                            .addGap(18, 18, 18)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 953, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGap(24, 24, 24)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1109, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Search Invoice", jPanel4);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jButton11.setFont(new java.awt.Font("Quicksand", 0, 18)); // NOI18N
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/select Invoice.png"))); // NOI18N
        jButton11.setText(" Select Invoice");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Quicksand", 1, 16)); // NOI18N
        jLabel27.setText("Customer Id :");

        jLabel91.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        jLabel91.setText("None");

        jLabel92.setFont(new java.awt.Font("Quicksand", 1, 16)); // NOI18N
        jLabel92.setText("Customer Name :");

        jLabel93.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        jLabel93.setText("None");

        jLabel94.setFont(new java.awt.Font("Quicksand", 1, 16)); // NOI18N
        jLabel94.setText("Customer Mobile :");

        jLabel95.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        jLabel95.setText("None");

        jLabel96.setFont(new java.awt.Font("Quicksand", 1, 16)); // NOI18N
        jLabel96.setText("Customer C. Branch :");

        jLabel97.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        jLabel97.setText("None");

        jLabel98.setFont(new java.awt.Font("Quicksand", 1, 16)); // NOI18N
        jLabel98.setText("Customer NIC :");

        jLabel99.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        jLabel99.setText("None");

        jLabel100.setFont(new java.awt.Font("Quicksand", 1, 16)); // NOI18N
        jLabel100.setText("Customer email :");

        jLabel101.setFont(new java.awt.Font("Quicksand", 1, 16)); // NOI18N
        jLabel101.setText("Age Type :");

        jComboBox7.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N

        jComboBox10.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N

        jLabel102.setFont(new java.awt.Font("Quicksand", 1, 16)); // NOI18N
        jLabel102.setText("Cloth Type :");

        jLabel103.setFont(new java.awt.Font("Quicksand", 1, 16)); // NOI18N
        jLabel103.setText("Unit Count :");

        jTextField8.setEditable(false);
        jTextField8.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N

        jTextField9.setEditable(false);
        jTextField9.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N

        jLabel104.setFont(new java.awt.Font("Quicksand", 1, 16)); // NOI18N
        jLabel104.setText("Advance :");

        jLabel105.setFont(new java.awt.Font("Quicksand", 1, 16)); // NOI18N
        jLabel105.setText("Description :");

        jLabel106.setFont(new java.awt.Font("Quicksand", 1, 16)); // NOI18N
        jLabel106.setText("Time Period :");

        jLabel107.setFont(new java.awt.Font("Quicksand", 1, 16)); // NOI18N
        jLabel107.setText("Gender Type :");

        jComboBox11.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N

        jComboBox12.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N

        jTextArea2.setEditable(false);
        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        jTextArea2.setRows(5);
        jScrollPane6.setViewportView(jTextArea2);

        jLabel108.setFont(new java.awt.Font("Quicksand", 1, 16)); // NOI18N
        jLabel108.setText("Order Date :");

        jComboBox13.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N

        jLabel109.setFont(new java.awt.Font("Quicksand", 1, 16)); // NOI18N
        jLabel109.setText("Wair Type :");

        jLabel110.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        jLabel110.setText("None");

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Type", "Stock Id", "Product Id", "Brand", "Cloth Type", "Colour", "Name", "Total Yards or Count", "Total Weight", "Cost"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(jTable5);

        jTextField11.setEditable(false);
        jTextField11.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        jTextField11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField11KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField11KeyTyped(evt);
            }
        });

        jLabel131.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        jLabel131.setText("0.00");

        jLabel132.setFont(new java.awt.Font("Quicksand", 1, 16)); // NOI18N
        jLabel132.setText("Total Cost :");

        jLabel133.setFont(new java.awt.Font("Quicksand", 1, 16)); // NOI18N
        jLabel133.setText("Payment :");

        jLabel134.setFont(new java.awt.Font("Quicksand", 1, 16)); // NOI18N
        jLabel134.setText("Balance :");

        jLabel135.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        jLabel135.setText("0.00");

        jLabel136.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        jLabel136.setText("0.00");

        jComboBox14.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        jComboBox14.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox14ItemStateChanged(evt);
            }
        });

        jLabel137.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        jLabel137.setText("0.00");

        jLabel138.setFont(new java.awt.Font("Quicksand", 1, 16)); // NOI18N
        jLabel138.setText("Cost Per Items :");

        jLabel139.setFont(new java.awt.Font("Quicksand", 1, 16)); // NOI18N
        jLabel139.setText("Payment Method :");

        jLabel140.setFont(new java.awt.Font("Quicksand", 1, 16)); // NOI18N
        jLabel140.setText("Profit :");

        jButton14.setFont(new java.awt.Font("Quicksand", 0, 22)); // NOI18N
        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/print.png"))); // NOI18N
        jButton14.setText("Print Invoice");
        jButton14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Quicksand", 1, 24)); // NOI18N
        jLabel2.setText("Invoice Customer Order Payment");

        jLabel6.setFont(new java.awt.Font("Quicksand", 1, 24)); // NOI18N

        jLabel21.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N

        jLabel111.setFont(new java.awt.Font("Quicksand", 1, 16)); // NOI18N
        jLabel111.setText("Deadline :");

        jLabel34.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N

        jLabel141.setFont(new java.awt.Font("Quicksand", 1, 16)); // NOI18N
        jLabel141.setText("Total :");

        jLabel142.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        jLabel142.setText("0.00");

        jLabel143.setFont(new java.awt.Font("Quicksand", 0, 16)); // NOI18N
        jLabel143.setText("0.00");

        jLabel144.setFont(new java.awt.Font("Quicksand", 1, 16)); // NOI18N
        jLabel144.setText("New Balance :");

        jSeparator6.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator7.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator8.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addGap(173, 173, 173)
                        .addComponent(jScrollPane6))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                            .addGap(56, 56, 56)
                                            .addComponent(jLabel102)
                                            .addGap(29, 29, 29)
                                            .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                            .addGap(59, 59, 59)
                                            .addComponent(jLabel103, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(3, 3, 3)
                                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(38, 38, 38)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel106)
                                        .addComponent(jLabel104))
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                            .addGap(28, 28, 28)
                                            .addComponent(jComboBox12, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                            .addGap(29, 29, 29)
                                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(24, 24, 24)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                            .addComponent(jLabel111)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel6Layout.createSequentialGroup()
                                            .addComponent(jLabel108, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel138)
                                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 1056, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 1057, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addComponent(jLabel101)
                                .addGap(40, 40, 40)
                                .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(jLabel107, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox11, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(jLabel109)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox13, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(28, 28, 28)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel27)
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel92)
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)
                                        .addComponent(jLabel94)
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 1057, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel100)
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel98)
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel96)
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel110, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 1057, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(jLabel105)
                                        .addGap(960, 960, 960))
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel139)
                                            .addComponent(jLabel134, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel141, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel137, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                                            .addComponent(jComboBox14, 0, 235, Short.MAX_VALUE)
                                            .addComponent(jLabel135, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                                            .addComponent(jLabel136, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel132, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel133))
                                                .addComponent(jLabel144, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addComponent(jLabel140))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel131, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                                            .addComponent(jTextField11, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                                            .addComponent(jLabel143, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                                            .addComponent(jLabel142, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(29, 29, 29)
                                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(1, 1, 1)))
                .addGap(294, 294, 294))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton11)
                        .addComponent(jLabel2))
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27)
                    .addComponent(jLabel91)
                    .addComponent(jLabel92)
                    .addComponent(jLabel93)
                    .addComponent(jLabel94)
                    .addComponent(jLabel95))
                .addGap(6, 6, 6)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel100)
                    .addComponent(jLabel99)
                    .addComponent(jLabel98)
                    .addComponent(jLabel97)
                    .addComponent(jLabel96)
                    .addComponent(jLabel110))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel109))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel101)
                            .addComponent(jLabel107))))
                .addGap(9, 9, 9)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel108))
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel102)
                            .addComponent(jLabel106))))
                .addGap(7, 7, 7)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel103))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel34, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel111))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel104)
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel105, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel131, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel132)
                            .addComponent(jLabel137, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel138))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel133)
                            .addComponent(jComboBox14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel139))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel143, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel144)
                            .addComponent(jLabel135, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel134))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel140)
                                .addComponent(jLabel142, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel136, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel141)))
                    .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(70, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Update Supplier", jPanel6);

        add(jTabbedPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 1170, 820));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:

        int r = jTable1.getSelectedRow();

        if (r == -1) {
            JOptionPane.showMessageDialog(this, "Please select a Invoice", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            try {

                String orderId = jTable1.getValueAt(r, 0).toString();
                String CustomerId = jTable1.getValueAt(r, 1).toString();
                String CustomerName = jTable1.getValueAt(r, 2).toString();
                String CustomerMobile = jTable1.getValueAt(r, 3).toString();
                String CustomerEmail = jTable1.getValueAt(r, 4).toString();
                String CustomerNic = jTable1.getValueAt(r, 5).toString();
                String CustomerCompanyBranch = jTable1.getValueAt(r, 6).toString();
                String unitCount = jTable1.getValueAt(r, 7).toString();
                String OrderDate = jTable1.getValueAt(r, 8).toString();
                String Deadline = jTable1.getValueAt(r, 9).toString();
                String PaymentId = jTable1.getValueAt(r, 10).toString();
                String Advance = jTable1.getValueAt(r, 11).toString();
                String Payment = jTable1.getValueAt(r, 12).toString();
                String Balance = jTable1.getValueAt(r, 13).toString();

                jLabel6.setText(orderId);
                jLabel91.setText(CustomerId);
                jLabel93.setText(CustomerName);
                jLabel95.setText(CustomerMobile);
                jLabel99.setText(CustomerEmail);
                jLabel97.setText(CustomerNic);
                jLabel110.setText(CustomerCompanyBranch);

                ResultSet rs1 = MySQL.search("SELECT * FROM `customer_order` WHERE `id`='" + orderId + "' ");
                rs1.next();
                String ageT = rs1.getString("customer_age_type_id");
                String WairT = rs1.getString("customer_cloth_ware_type_id");
                String GenderT = rs1.getString("customer_sex_type_id");
                String ClothT = rs1.getString("customer_cloth_type_id");
                String TimeP = rs1.getString("customer_cloth_time_period_id");
                String description = rs1.getString("Description");

                ResultSet rss1 = MySQL.search("SELECT `name` FROM `customer_age_type` WHERE `id`='" + ageT + "' ");
                rss1.next();
                String ageT_id = rss1.getString("name");

                ResultSet rss2 = MySQL.search("SELECT `name` FROM `customer_cloth_ware_type` WHERE `id`='" + WairT + "' ");
                rss2.next();
                String WairT_id = rss2.getString("name");

                ResultSet rss3 = MySQL.search("SELECT `name` FROM `customer_sex_type` WHERE `id`='" + GenderT + "' ");
                rss3.next();
                String GenderT_id = rss3.getString("name");

                ResultSet rss4 = MySQL.search("SELECT `name` FROM `customer_cloth_type` WHERE `id`='" + ClothT + "' ");
                rss4.next();
                String ClothT_id = rss4.getString("name");

                ResultSet rss5 = MySQL.search("SELECT `name` FROM `customer_cloth_time_period` WHERE `id`='" + TimeP + "' ");
                rss5.next();
                String TimeP_id = rss5.getString("name");

                jComboBox7.setSelectedItem(ageT_id);
                jComboBox11.setSelectedItem(GenderT_id);
                jComboBox13.setSelectedItem(WairT_id);
                jComboBox10.setSelectedItem(ClothT_id);
                jComboBox12.setSelectedItem(TimeP_id);
                jLabel34.setText(Deadline);
                jLabel21.setText(OrderDate);
                jTextField8.setText(unitCount);
                jTextField9.setText(Advance);
                jTextArea2.setText(description);

                jComboBox7.setEnabled(false);
                jComboBox11.setEnabled(false);
                jComboBox13.setEnabled(false);
                jComboBox10.setEnabled(false);
                jComboBox12.setEnabled(false);

                ResultSet rs = MySQL.search("SELECT * FROM `customer_order_has_stock` INNER JOIN `stock` ON `stock`.`id` = `customer_order_has_stock`.`stock_id` INNER JOIN `product` ON `product`.`id` = `stock`.`product_id` INNER JOIN `product_colour` ON `product`.`product_colour_id` = `product_colour`.`id` INNER JOIN `product_cloth_type` ON `product`.`product_cloth_type_id` = `product_cloth_type`.`id` INNER JOIN `product_type` ON `product`.`product_type_id` = `product_type`.`id` INNER JOIN `product_brand` ON `product`.`product_brand_id` = `product_brand`.`id` WHERE `customer_order_has_stock`.`customer_order_id` =  '" + orderId + "' ;");

                DefaultTableModel dtm = (DefaultTableModel) jTable5.getModel();
                dtm.setRowCount(0);

                while (rs.next()) {
                    Vector v = new Vector();
                    v.add(rs.getString("product_type.name"));
                    v.add(rs.getString("stock.id"));
                    v.add(rs.getString("product.id"));
                    v.add(rs.getString("product_brand.name"));
                    v.add(rs.getString("product_cloth_type.name"));
                    v.add(rs.getString("product_colour.name"));
                    v.add(rs.getString("product.name"));
                    v.add(rs.getString("customer_order_has_stock.estimate_yards_or_count"));
                    v.add(rs.getString("customer_order_has_stock.estimate_weight"));
                    double itemtotal = Double.parseDouble(rs.getString("customer_order_has_stock.estimate_price")) / Double.parseDouble(unitCount);
                    v.add(df.format(itemtotal));

                    dtm.addRow(v);

                }

                ResultSet rs2 = MySQL.search("SELECT * FROM `customer_order` INNER JOIN `cost_and_profit` ON `cost_and_profit`.`customer_order_id` = `customer_order`.`id` INNER JOIN `customer_order_payment` ON `customer_order_payment`.`customer_order_id` = `customer_order`.`id` WHERE `customer_order`.`id` = '" + orderId + "'");
                rs2.next();

                double itemtotalCost = Double.parseDouble(rs2.getString("cost_and_profit.cost")) / Double.parseDouble(unitCount);

                jLabel137.setText(df.format(itemtotalCost));
                jLabel131.setText(rs2.getString("cost_and_profit.cost"));
                jLabel136.setText(rs2.getString("customer_order_payment.payment"));
                jLabel142.setText(rs2.getString("cost_and_profit.profit"));
                jLabel135.setText(rs2.getString("customer_order_payment.balance"));

                jLabel143.setText("0.00");
                jTextField11.setText("");
                jTextField11.setEditable(false);
                jComboBox14.setSelectedIndex(0);

                jTabbedPane2.setSelectedIndex(2);

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

                    String orderId = jTable1.getValueAt(r, 0).toString();
                    String CustomerId = jTable1.getValueAt(r, 1).toString();
                    String CustomerName = jTable1.getValueAt(r, 2).toString();
                    String CustomerMobile = jTable1.getValueAt(r, 3).toString();
                    String CustomerEmail = jTable1.getValueAt(r, 4).toString();
                    String CustomerNic = jTable1.getValueAt(r, 5).toString();
                    String CustomerCompanyBranch = jTable1.getValueAt(r, 6).toString();
                    String unitCount = jTable1.getValueAt(r, 7).toString();
                    String OrderDate = jTable1.getValueAt(r, 8).toString();
                    String Deadline = jTable1.getValueAt(r, 9).toString();
                    String PaymentId = jTable1.getValueAt(r, 10).toString();
                    String Advance = jTable1.getValueAt(r, 11).toString();
                    String Payment = jTable1.getValueAt(r, 12).toString();
                    String Balance = jTable1.getValueAt(r, 13).toString();

                    jLabel6.setText(orderId);
                    jLabel91.setText(CustomerId);
                    jLabel93.setText(CustomerName);
                    jLabel95.setText(CustomerMobile);
                    jLabel99.setText(CustomerEmail);
                    jLabel97.setText(CustomerNic);
                    jLabel110.setText(CustomerCompanyBranch);

                    ResultSet rs1 = MySQL.search("SELECT * FROM `customer_order` WHERE `id`='" + orderId + "' ");
                    rs1.next();
                    String ageT = rs1.getString("customer_age_type_id");
                    String WairT = rs1.getString("customer_cloth_ware_type_id");
                    String GenderT = rs1.getString("customer_sex_type_id");
                    String ClothT = rs1.getString("customer_cloth_type_id");
                    String TimeP = rs1.getString("customer_cloth_time_period_id");
                    String description = rs1.getString("Description");

                    ResultSet rss1 = MySQL.search("SELECT `name` FROM `customer_age_type` WHERE `id`='" + ageT + "' ");
                    rss1.next();
                    String ageT_id = rss1.getString("name");

                    ResultSet rss2 = MySQL.search("SELECT `name` FROM `customer_cloth_ware_type` WHERE `id`='" + WairT + "' ");
                    rss2.next();
                    String WairT_id = rss2.getString("name");

                    ResultSet rss3 = MySQL.search("SELECT `name` FROM `customer_sex_type` WHERE `id`='" + GenderT + "' ");
                    rss3.next();
                    String GenderT_id = rss3.getString("name");

                    ResultSet rss4 = MySQL.search("SELECT `name` FROM `customer_cloth_type` WHERE `id`='" + ClothT + "' ");
                    rss4.next();
                    String ClothT_id = rss4.getString("name");

                    ResultSet rss5 = MySQL.search("SELECT `name` FROM `customer_cloth_time_period` WHERE `id`='" + TimeP + "' ");
                    rss5.next();
                    String TimeP_id = rss5.getString("name");

                    jComboBox7.setSelectedItem(ageT_id);
                    jComboBox11.setSelectedItem(GenderT_id);
                    jComboBox13.setSelectedItem(WairT_id);
                    jComboBox10.setSelectedItem(ClothT_id);
                    jComboBox12.setSelectedItem(TimeP_id);
                    jLabel34.setText(Deadline);
                    jLabel21.setText(OrderDate);
                    jTextField8.setText(unitCount);
                    jTextField9.setText(Advance);
                    jTextArea2.setText(description);

                    jComboBox7.setEnabled(false);
                    jComboBox11.setEnabled(false);
                    jComboBox13.setEnabled(false);
                    jComboBox10.setEnabled(false);
                    jComboBox12.setEnabled(false);

                    ResultSet rs = MySQL.search("SELECT * FROM `customer_order_has_stock` INNER JOIN `stock` ON `stock`.`id` = `customer_order_has_stock`.`stock_id` INNER JOIN `product` ON `product`.`id` = `stock`.`product_id` INNER JOIN `product_colour` ON `product`.`product_colour_id` = `product_colour`.`id` INNER JOIN `product_cloth_type` ON `product`.`product_cloth_type_id` = `product_cloth_type`.`id` INNER JOIN `product_type` ON `product`.`product_type_id` = `product_type`.`id` INNER JOIN `product_brand` ON `product`.`product_brand_id` = `product_brand`.`id` WHERE `customer_order_has_stock`.`customer_order_id` =  '" + orderId + "' ;");

                    DefaultTableModel dtm = (DefaultTableModel) jTable5.getModel();
                    dtm.setRowCount(0);

                    while (rs.next()) {
                        Vector v = new Vector();
                        v.add(rs.getString("product_type.name"));
                        v.add(rs.getString("stock.id"));
                        v.add(rs.getString("product.id"));
                        v.add(rs.getString("product_brand.name"));
                        v.add(rs.getString("product_cloth_type.name"));
                        v.add(rs.getString("product_colour.name"));
                        v.add(rs.getString("product.name"));
                        v.add(rs.getString("customer_order_has_stock.estimate_yards_or_count"));
                        v.add(rs.getString("customer_order_has_stock.estimate_weight"));
                        double itemtotal = Double.parseDouble(rs.getString("customer_order_has_stock.estimate_price")) / Double.parseDouble(unitCount);
                        v.add(df.format(itemtotal));

                        dtm.addRow(v);

                    }

                    ResultSet rs2 = MySQL.search("SELECT * FROM `customer_order` INNER JOIN `cost_and_profit` ON `cost_and_profit`.`customer_order_id` = `customer_order`.`id` INNER JOIN `customer_order_payment` ON `customer_order_payment`.`customer_order_id` = `customer_order`.`id` WHERE `customer_order`.`id` = '" + orderId + "'");
                    rs2.next();

                    double itemtotalCost = Double.parseDouble(rs2.getString("cost_and_profit.cost")) / Double.parseDouble(unitCount);

                    jLabel137.setText(df.format(itemtotalCost));
                    jLabel131.setText(rs2.getString("cost_and_profit.cost"));
                    jLabel136.setText(rs2.getString("customer_order_payment.payment"));
                    jLabel142.setText(rs2.getString("cost_and_profit.profit"));
                    jLabel135.setText(rs2.getString("customer_order_payment.balance"));

                    jLabel143.setText("0.00");
                    jTextField11.setText("");
                    jTextField11.setEditable(false);
                    jComboBox14.setSelectedIndex(0);

                    jTabbedPane2.setSelectedIndex(2);

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
        loadInvoices(text);
    }//GEN-LAST:event_jTextField6KeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        String payment = jTextField7.getText();
        String balance = jLabel85.getText();
        String cost = jLabel65.getText();
        String profit = jLabel89.getText();
        String paymentType = jComboBox6.getSelectedItem().toString();

//        Details
        String ageT = jComboBox1.getSelectedItem().toString();
        String GenderT = jComboBox2.getSelectedItem().toString();
        String WairT = jComboBox3.getSelectedItem().toString();
        String ClothT = jComboBox4.getSelectedItem().toString();
        String TimeP = jComboBox5.getSelectedItem().toString();
        Date Deadline = jDateChooser2.getDate();
        String UCount = jTextField1.getText();
        String Advance = jTextField3.getText();
        String Description = jTextArea1.getText();
//        Details

        if (jTable4.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Please add Supplies", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (paymentType.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Invalid payment Type", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!Pattern.compile("([0])|([1-9][0-9]*)|(([1-9][0-9]*)[.]([0]*[1-9][0-9]*))|([0][.]([0]*[1-9][0-9]*))").matcher(payment).matches()) {
            JOptionPane.showMessageDialog(this, "Invalid payment", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
//            Customer Order Insert
                long mTime = System.currentTimeMillis();

                String uniqueId = mTime + "-" + SingIn.userId;

                String cid = jLabel10.getText();

                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dNow = sdf2.format(new Date());

                ResultSet rss1 = MySQL.search("SELECT `id` FROM `customer_age_type` WHERE `name`='" + ageT + "' ");
                rss1.next();
                String ageT_id = rss1.getString("id");

                ResultSet rss2 = MySQL.search("SELECT `id` FROM `customer_cloth_ware_type` WHERE `name`='" + WairT + "' ");
                rss2.next();
                String WairT_id = rss2.getString("id");

                ResultSet rss3 = MySQL.search("SELECT `id` FROM `customer_sex_type` WHERE `name`='" + GenderT + "' ");
                rss3.next();
                String GenderT_id = rss3.getString("id");

                ResultSet rss4 = MySQL.search("SELECT `id` FROM `customer_cloth_type` WHERE `name`='" + ClothT + "' ");
                rss4.next();
                String ClothT_id = rss4.getString("id");

                ResultSet rss5 = MySQL.search("SELECT `id` FROM `customer_cloth_time_period` WHERE `name`='" + TimeP + "' ");
                rss5.next();
                String TimeP_id = rss5.getString("id");

                MySQL.iud("INSERT INTO `customer_order`(`order_date`, `deadline_date`, `unit_count`, `advance`, `Description`, `customer_id`, `customer_age_type_id`, `customer_cloth_ware_type_id`, `customer_sex_type_id`, `customer_cloth_type_id`, `customer_cloth_time_period_id`, `user_id`, `unique_id`) VALUES"
                        + "('" + dNow + "','" + sdf.format(Deadline) + "','" + UCount + "','" + Advance + "','" + Description + "','" + cid + "','" + ageT_id + "','" + WairT_id + "','" + GenderT_id + "','" + ClothT_id + "','" + TimeP_id + "','" + SingIn.userId + "','" + uniqueId + "') ");
//            Customer Order Insert

//            Customer Order payment Insert
                ResultSet rs = MySQL.search("SELECT * FROM `customer_order` WHERE `unique_id`='" + uniqueId + "'");
                rs.next();
                String id = rs.getString("id");

                ResultSet rs2 = MySQL.search("SELECT * FROM `payment_type` WHERE `name`='" + paymentType + "'");
                rs2.next();
                String paymentTypeId = rs2.getString("id");

                MySQL.iud("INSERT INTO `customer_order_payment`(`customer_order_id`,`payment_type_id`,`payment`,`balance`) VALUES('" + id + "','" + paymentTypeId + "','" + payment + "','" + balance + "') ");

                MySQL.iud("INSERT INTO `cost_and_profit`(`cost`, `profit`, `customer_order_id`)  VALUES('" + cost + "','" + profit + "','" + id + "') ");
//            Customer Order payment Insert

//            customer_order_has_stock Insert & stock Insert or Update
                for (int i = 0; i < jTable4.getRowCount(); i++) {

                    String stockId = jTable4.getValueAt(i, 1).toString();
                    String count = jTable4.getValueAt(i, 9).toString();
                    String weight = jTable4.getValueAt(i, 10).toString();
                    String costt = jTable4.getValueAt(i, 11).toString();

                    ResultSet rs3 = MySQL.search("SELECT * FROM `stock` WHERE `id`='" + stockId + "' ");

                    rs3.next();

                    String stock_Count = rs3.getString("yards_or_count");
                    String stock_Weight = rs3.getString("weight");

                    Double updateCount = Double.parseDouble(stock_Count) - Double.parseDouble(count);
                    Double updateWeight = Double.parseDouble(stock_Weight) - Double.parseDouble(weight);
                    Double TCostPayment = Double.parseDouble(UCount) * Double.parseDouble(costt);

                    MySQL.iud("UPDATE `stock` SET `yards_or_count`='" + updateCount + "',`weight`='" + updateWeight + "' WHERE `id`='" + stockId + "' ");

                    MySQL.iud("INSERT INTO `customer_order_has_stock`(`estimate_price`, `estimate_yards_or_count`, `estimate_weight`, `customer_order_id`, `stock_id`) VALUES('" + TCostPayment + "','" + count + "','" + weight + "','" + id + "','" + stockId + "') ");

                }

                JOptionPane.showMessageDialog(this, "New Invoice created", "Success", JOptionPane.INFORMATION_MESSAGE);

                //            customer_order_has_stock Insert & stock Insert or Update
//                jasper Report
                String cusid = jLabel10.getText();
                String cusName = jLabel12.getText();
                String cusEmail = jLabel17.getText();
                String cusMobile = jLabel17.getText();

                ResultSet rsc = MySQL.search("SELECT * FROM `customer` INNER JOIN `customer_company_branch` ON `customer_company_branch`.`id` = `customer`.`customer_company_branch_id` INNER JOIN `address` ON `address`.`id` = `customer_company_branch`.`address_id` INNER JOIN `city` ON `city`.`id` = `address`.`city_id`  WHERE `customer`.`id`='" + cusid + "' ");
                rsc.next();

                String cib = rsc.getString("customer_company_branch.name");
                String address = rsc.getString("address.line");
                String city = rsc.getString("city.name");
                
                    InputStream filepath = Invoice.class.getResourceAsStream("/reports/garment.jrxml");
            JasperReport jr = JasperCompileManager.compileReport(filepath);

//                JasperReport jr = JasperCompileManager.compileReport("src//reports//garment.jrxml");

                HashMap parameters = new HashMap();
                parameters.put("Parameter1", cusName);
                parameters.put("Parameter2", cusEmail);
                parameters.put("Parameter3", cib);
                parameters.put("Parameter4", address + " " + city);
                parameters.put("Parameter5", cusMobile);
                parameters.put("Parameter6", id);
                parameters.put("Parameter7", dNow);
                parameters.put("Parameter8", ageT);
                parameters.put("Parameter9", GenderT);
                parameters.put("Parameter10", WairT);
                parameters.put("Parameter11", ClothT);
                parameters.put("Parameter12", TimeP);
                parameters.put("Parameter13", Description);
                parameters.put("Parameter14", Advance);
                parameters.put("Parameter15", UCount);
                parameters.put("Parameter16", payment);
                parameters.put("Parameter17", payment);
                parameters.put("Parameter18", Advance);
                parameters.put("Parameter19", balance);
                parameters.put("Parameter20", sdf.format(Deadline));

                JREmptyDataSource dataSource = new JREmptyDataSource();

                JasperPrint jp = JasperFillManager.fillReport(jr, parameters, dataSource);

                JasperViewer.viewReport(jp, false);

//                2nd Report

 InputStream filepath2 = Invoice.class.getResourceAsStream("/reports/invoiceus.jrxml");
            JasperReport jr2 = JasperCompileManager.compileReport(filepath2);

//                JasperReport jr2 = JasperCompileManager.compileReport("src//reports//invoiceus.jrxml");

                HashMap parameters2 = new HashMap();
                parameters2.put("Parameter1", cusName);
                parameters2.put("Parameter2", cusEmail);
                parameters2.put("Parameter3", cib);
                parameters2.put("Parameter4", address + " " + city);
                parameters2.put("Parameter5", cusMobile);
                parameters2.put("Parameter6", id);
                parameters2.put("Parameter7", dNow);
               
                parameters2.put("Parameter17", cost);
                parameters2.put("Parameter18", payment);
                parameters2.put("Parameter19", balance);
                parameters2.put("Parameter20", sdf.format(Deadline));
                parameters2.put("Parameter21", profit);

                TableModel tm = jTable4.getModel();
                JRTableModelDataSource dataSource2 = new JRTableModelDataSource(tm);

                JasperPrint jp2 = JasperFillManager.fillReport(jr2, parameters2, dataSource2);

                JasperViewer.viewReport(jp2, false);

//                jasper Report
                resetFields();
                resetFields2();

//                Customer
                jButton4.setEnabled(true);
                jButton1.setText("Select Customer");
                jLabel10.setText("None");
                jLabel15.setText("None");
                jLabel12.setText("None");
                jLabel17.setText("None");
                jLabel22.setText("None");
                jLabel24.setText("None");
//                Customer

//                Payment
                jLabel88.setText("0.00");
                jLabel65.setText("0.00");
                jLabel89.setText("0.00");
                jLabel85.setText("0.00");
                jTextField7.setText("");
                jTextField7.setEditable(false);
                jComboBox6.setSelectedIndex(0);
//                Payment

                jTextField1.setEditable(true);
                jTextField3.setEditable(true);

                DefaultTableModel dtm = (DefaultTableModel) jTable4.getModel();
                dtm.setRowCount(0);

                loadInvoices();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jPanel3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseExited
        // TODO add your handling code here:
        Color clr = new Color(71, 181, 255);
        jPanel3.setBackground(clr);
        jLabel3.setForeground(Color.WHITE);
    }//GEN-LAST:event_jPanel3MouseExited

    private void jPanel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseEntered
        // TODO add your handling code here:
        Color clr = new Color(223, 246, 255);
        jPanel3.setBackground(clr);
        jLabel3.setForeground(Color.BLACK);
    }//GEN-LAST:event_jPanel3MouseEntered

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked
        // TODO add your handling code here:
        jTabbedPane2.setSelectedIndex(2);
    }//GEN-LAST:event_jPanel3MouseClicked

    private void jPanel10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseExited
        // TODO add your handling code here:
        Color clr = new Color(71, 181, 255);
        jPanel10.setBackground(clr);
        jLabel5.setForeground(Color.WHITE);
    }//GEN-LAST:event_jPanel10MouseExited

    private void jPanel10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseEntered
        // TODO add your handling code here:
        Color clr = new Color(223, 246, 255);
        jPanel10.setBackground(clr);
        jLabel5.setForeground(Color.BLACK);
    }//GEN-LAST:event_jPanel10MouseEntered

    private void jPanel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseClicked
        // TODO add your handling code here:
        jTabbedPane2.setSelectedIndex(1);
    }//GEN-LAST:event_jPanel10MouseClicked

    private void jPanel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseExited
        // TODO add your handling code here:
        Color clr = new Color(71, 181, 255);
        jPanel1.setBackground(clr);
        jLabel1.setForeground(Color.WHITE);
    }//GEN-LAST:event_jPanel1MouseExited

    private void jPanel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseEntered
        // TODO add your handling code here:
        Color clr = new Color(223, 246, 255);
        jPanel1.setBackground(clr);
        jLabel1.setForeground(Color.BLACK);
    }//GEN-LAST:event_jPanel1MouseEntered

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        // TODO add your handling code here:
        jTabbedPane2.setSelectedIndex(0);
    }//GEN-LAST:event_jPanel1MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        SelectCustomer sc = new SelectCustomer(this);
        sc.setVisible(true);

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        SelectStock ss = new SelectStock(this);
        ss.setVisible(true);

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
//        Cistomer
        String customerId = jLabel10.getText();
//        Cistomer

//        Details      
        String ageT = jComboBox1.getSelectedItem().toString();
        String GenderT = jComboBox2.getSelectedItem().toString();
        String WairT = jComboBox3.getSelectedItem().toString();
        String ClothT = jComboBox4.getSelectedItem().toString();
        String TimeP = jComboBox5.getSelectedItem().toString();
        Date Deadline = jDateChooser2.getDate();
        String UCount = jTextField1.getText();
        String Advance = jTextField3.getText();
        String Description = jTextArea1.getText();

//        Details      
//        Supply      
        String stockid = jLabel33.getText();
        String ProductCount = jTextField4.getText();
        String PID = jLabel72.getText();
        String brand = jLabel71.getText();
        String type = jLabel68.getText();
        String clType = jLabel75.getText();
        String Colour = jLabel63.getText();
        String name = jLabel67.getText();
        String wei = jLabel80.getText();
        String coy = jLabel78.getText();
        String price = jLabel76.getText();
//        Supply      

        if (customerId.equals("None")) {
            JOptionPane.showMessageDialog(this, "Please Select the Cutomer", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (ageT.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please Select Age Type", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (GenderT.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please Select Gender Type", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (WairT.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please Select Wair Type", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (ClothT.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please Select Cloth Type", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (TimeP.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please Select Time Period", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (Deadline == null) {
            JOptionPane.showMessageDialog(this, "Please Add Deadline Date", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (!Pattern.compile("(0)|([1-9][0-9]*)|(([1-9][0-9]*)[.]([0]*[1-9][0-9]*))|([0][.]([0]*[1-9][0-9]*))").matcher(UCount).matches()) {
            JOptionPane.showMessageDialog(this, "Invalid Unit Count", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (!Pattern.compile("(0)|([1-9][0-9]*)|(([1-9][0-9]*)[.]([0]*[1-9][0-9]*))|([0][.]([0]*[1-9][0-9]*))").matcher(Advance).matches()) {
            JOptionPane.showMessageDialog(this, "Invalid Advanced", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (Description.equals("None")) {
            JOptionPane.showMessageDialog(this, "Please Add Description", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (stockid.equals("None")) {
            JOptionPane.showMessageDialog(this, "Please Select the Supplies", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (!Pattern.compile("(0)|([1-9][0-9]*)|(([1-9][0-9]*)[.]([0]*[1-9][0-9]*))|([0][.]([0]*[1-9][0-9]*))").matcher(ProductCount).matches()) {
            JOptionPane.showMessageDialog(this, "Invalid Yards or Count for the Product :", "Warning", JOptionPane.WARNING_MESSAGE);

        } else {

            jTextField1.setEditable(false);
            jTextField3.setEditable(false);

            DefaultTableModel dtm = (DefaultTableModel) jTable4.getModel();

            double newWeight = (Double.parseDouble(coy) / Double.parseDouble(wei)) * Double.parseDouble(ProductCount);

            boolean isFound = false;
            int x = -1;

            for (int i = 0; i < dtm.getRowCount(); i++) {
                String id = jTable4.getValueAt(i, 1).toString();

                if (id.equals(stockid)) {
                    isFound = true;
                    x = i;
                    break;
                }
            }

            if (isFound) {
                int option = JOptionPane.showConfirmDialog(this, "This Stock is already added. Do yoou want to update?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);

                if (option == JOptionPane.YES_OPTION) {

                    double oldCount = Double.parseDouble(jTable4.getValueAt(x, 7).toString());
                    double finalProductCount = oldCount + Double.parseDouble(ProductCount);

                    double oldWeight = Double.parseDouble(jTable4.getValueAt(x, 8).toString());
                    double finalWeight = oldWeight + newWeight;

                    jTable4.setValueAt(String.valueOf(finalProductCount), x, 7);
                    jTable4.setValueAt(String.valueOf(finalWeight), x, 8);

                    double updatetotalCount = finalProductCount * Double.parseDouble(UCount);
                    jTable4.setValueAt(String.valueOf(updatetotalCount), x, 9);

                    double updatetotalWeight = finalWeight * Double.parseDouble(UCount);
                    jTable4.setValueAt(String.valueOf(updatetotalWeight), x, 10);

                    double updateItemCost = finalProductCount * Double.parseDouble(price);
                    jTable4.setValueAt(String.valueOf(updateItemCost), x, 11);
                    updateTotal();

                }

                resetFields();

            } else {

                Vector v = new Vector();
                v.add(type);
                v.add(stockid);
                v.add(PID);
                v.add(brand);
                v.add(clType);
                v.add(Colour);
                v.add(name);
                v.add(Double.parseDouble(ProductCount));
                v.add(df.format(newWeight));

                double totalCount = Double.parseDouble(ProductCount) * Double.parseDouble(UCount);
                v.add(df.format(totalCount));

                double totalWeight = newWeight * Double.parseDouble(UCount);
                v.add(df.format(totalWeight));

                double itemtotal = Double.parseDouble(ProductCount) * Double.parseDouble(price);
                v.add(df.format(itemtotal));

                dtm.addRow(v);

                updateTotal();
//
                resetFields();
                JOptionPane.showMessageDialog(this, "Product added to the GRN", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jComboBox6ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox6ItemStateChanged
        // TODO add your handling code here:
        String text = jComboBox6.getSelectedItem().toString();

        if (text.equals("Select")) {
            jTextField7.setEditable(false);
            jTextField7.setText("");
            jLabel85.setText("0.00");
            jLabel85.setForeground(Color.black);

        } else {
            jTextField7.setEditable(true);
        }
    }//GEN-LAST:event_jComboBox6ItemStateChanged

    private void jTextField7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyReleased
        // TODO add your handling code here:
        if (jTextField7.getText().isEmpty()) {

            jLabel85.setText("0.00");
            jLabel85.setForeground(Color.blue);
            jLabel89.setText("0.00");
            jLabel89.setForeground(Color.blue);

        } else {
            String advance = jTextField3.getText();
            String payment = jTextField7.getText();
            String cost = jLabel65.getText();

            Double balance = Double.parseDouble(payment) - Double.parseDouble(advance);
            Double profit = Double.parseDouble(payment) - Double.parseDouble(cost);

            if (balance < 0) {
                jLabel85.setForeground(Color.red);
            } else {
                jLabel85.setForeground(Color.green);
            }

            if (profit < 0) {
                jLabel89.setForeground(Color.red);
            } else {
                jLabel89.setForeground(Color.green);
            }

            jLabel85.setText(String.valueOf(df.format(balance)));
            jLabel89.setText(String.valueOf(df.format(profit)));
        }

    }//GEN-LAST:event_jTextField7KeyReleased

    private void jTextField7KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyTyped
        // TODO add your handling code here:
        String price = jTextField7.getText();
        String text = price + evt.getKeyChar();

        if (!Pattern.compile("(0|0[.]|0[.][0-9]*)|[1-9]|[1-9][0-9]*|[1-9][0-9]*[.]|[1-9][0-9]*[.][0-9]*").matcher(text).matches()) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField7KeyTyped

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        jTabbedPane2.setSelectedIndex(1);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jTextField11KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField11KeyReleased
        // TODO add your handling code here:
        if (jTextField11.getText().isEmpty()) {

            jLabel143.setText("0.00");
            jLabel143.setForeground(Color.black);

        } else {
            String advance = jTextField9.getText();
            String payment = jTextField11.getText();
            String balance = jLabel135.getText();

            Double NewBalance = Double.parseDouble(balance) - Double.parseDouble(payment);

            if (NewBalance < 0) {
                jLabel143.setForeground(Color.red);
            } else {
                jLabel143.setForeground(Color.blue);
            }

            jLabel143.setText(String.valueOf(df.format(NewBalance)));
        }
    }//GEN-LAST:event_jTextField11KeyReleased

    private void jTextField11KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField11KeyTyped
        // TODO add your handling code here:
        String price = jTextField11.getText();
        String text = price + evt.getKeyChar();

        if (!Pattern.compile("(0|0[.]|0[.][0-9]*)|[1-9]|[1-9][0-9]*|[1-9][0-9]*[.]|[1-9][0-9]*[.][0-9]*").matcher(text).matches()) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField11KeyTyped

    private void jComboBox14ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox14ItemStateChanged
        // TODO add your handling code here:
        String text = jComboBox14.getSelectedItem().toString();

        if (text.equals("Select")) {
            jTextField11.setEditable(false);
            jTextField11.setText("");
            jLabel135.setText("0.00");
            jLabel135.setForeground(Color.black);

        } else {
            jTextField11.setEditable(true);
        }

    }//GEN-LAST:event_jComboBox14ItemStateChanged

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:

        String Invoiceid = jLabel6.getText();
        String paymentType = jComboBox14.getSelectedItem().toString();
        String payment = jTextField11.getText();
        String newBalance = jLabel143.getText();

        if (Invoiceid.equals("")) {
            JOptionPane.showMessageDialog(this, "Please Select a Invoice", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (paymentType.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Invalid payment Type", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!Pattern.compile("([0])|([1-9][0-9]*)|(([1-9][0-9]*)[.]([0]*[1-9][0-9]*))|([0][.]([0]*[1-9][0-9]*))").matcher(payment).matches()) {
            JOptionPane.showMessageDialog(this, "Invalid payment", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {

                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dNow = sdf2.format(new Date());

                MySQL.iud("UPDATE `customer_order_payment` SET `balance`='" + newBalance + "' WHERE `customer_order_id` = '" + Invoiceid + "'");

                MySQL.iud("INSERT INTO `final_payment`(`payment_date`, `balance_payment`, `customer_order_id`) VALUES ('" + dNow + "','" + payment + "','" + Invoiceid + "') ");

//                
                jLabel6.setText("None");
                jLabel91.setText("None");
                jLabel93.setText("None");
                jLabel95.setText("None");
                jLabel99.setText("None");
                jLabel97.setText("None");
                jLabel110.setText("None");

                jComboBox7.setSelectedIndex(0);
                jComboBox11.setSelectedIndex(0);
                jComboBox13.setSelectedIndex(0);
                jComboBox10.setSelectedIndex(0);
                jComboBox12.setSelectedIndex(0);
                jLabel34.setText("");
                jLabel21.setText("");
                jTextField8.setText("");
                jTextField9.setText("");
                jTextArea2.setText("");

                jComboBox7.setEnabled(true);
                jComboBox11.setEnabled(true);
                jComboBox13.setEnabled(true);
                jComboBox10.setEnabled(true);
                jComboBox12.setEnabled(true);

                jLabel137.setText("0.00");
                jLabel131.setText("0.00");
                jLabel136.setText("0.00");
                jLabel142.setText("0.00");
                jLabel135.setText("0.00");

                jLabel143.setText("0.00");
                jTextField11.setText("");
                jTextField11.setEditable(false);
                jComboBox14.setSelectedIndex(0);

                DefaultTableModel dtm = (DefaultTableModel) jTable5.getModel();
                dtm.setRowCount(0);
//

                loadInvoices();
                jTabbedPane2.setSelectedIndex(1);

                JOptionPane.showMessageDialog(this, "Invoice Update", "Success", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }//GEN-LAST:event_jButton14ActionPerformed

    private void jTextField3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyTyped
        // TODO add your handling code here:
        String price = jTextField3.getText();
        String text = price + evt.getKeyChar();

        if (!Pattern.compile("(0|0[.]|0[.][0-9]*)|[1-9]|[1-9][0-9]*|[1-9][0-9]*[.]|[1-9][0-9]*[.][0-9]*").matcher(text).matches()) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField3KeyTyped

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        // TODO add your handling code here:
        String price = jTextField1.getText();
        String text = price + evt.getKeyChar();

        if (!Pattern.compile("(0|0[.]|0[.][0-9]*)|[1-9]|[1-9][0-9]*|[1-9][0-9]*[.]|[1-9][0-9]*[.][0-9]*").matcher(text).matches()) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jTextField4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyTyped
        // TODO add your handling code here:
        String price = jTextField4.getText();
        String text = price + evt.getKeyChar();

        if (!Pattern.compile("(0|0[.]|0[.][0-9]*)|[1-9]|[1-9][0-9]*|[1-9][0-9]*[.]|[1-9][0-9]*[.][0-9]*").matcher(text).matches()) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField4KeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    public javax.swing.JButton jButton11;
    private javax.swing.JButton jButton14;
    public javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    public javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox10;
    private javax.swing.JComboBox<String> jComboBox11;
    private javax.swing.JComboBox<String> jComboBox12;
    private javax.swing.JComboBox<String> jComboBox13;
    private javax.swing.JComboBox<String> jComboBox14;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    public javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    public javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    public javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel133;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel138;
    private javax.swing.JLabel jLabel139;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel140;
    private javax.swing.JLabel jLabel141;
    private javax.swing.JLabel jLabel142;
    private javax.swing.JLabel jLabel143;
    private javax.swing.JLabel jLabel144;
    public javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    public javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    public javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    public javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    public javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel62;
    public javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    public javax.swing.JLabel jLabel67;
    public javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    public javax.swing.JLabel jLabel71;
    public javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    public javax.swing.JLabel jLabel75;
    public javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    public javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    public javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    public javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    public javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    public javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    public javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    public javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables

    void loadBrands() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
