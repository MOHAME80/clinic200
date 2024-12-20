package clincmedical;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

// Main GUI class with Factory integrated
public class viewrecordsP extends javax.swing.JFrame {

    public viewrecordsP() {
        initComponents();
    }

    // Initialize components (GUI elements)
    private void initComponents() {

        // Define the components (buttons, table, panel, etc.)
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        recordTypeComboBox = new JComboBox<>(new String[]{"PatientHistory", "Prescriptions", "LabResults"});

        // Set window settings
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        // "View Records" button to load data
        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18));
        jButton1.setText("VIEW RECORDS");
        jButton1.addActionListener(evt -> viewButtonActionPerformed(evt));

        // Table to display the records
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][]{},
            new String[]{"ID", "Patient Name", "Disease", "Date", "Time"}
        ));
        jScrollPane1.setViewportView(jTable1);

        // Logout button
        jButton3.setText("LOGOUT");
        jButton3.addActionListener(evt -> logoutButtonActionPerformed(evt));

        // Back button
        jButton2.setText("BACK");
        jButton2.addActionListener(evt -> backButtonActionPerformed(evt));

        // Panel and label setup
        jPanel1.setBackground(new java.awt.Color(51, 51, 0));
        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24));
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setText("VIEW DETAILS");
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGap(199, 199, 199).addComponent(jLabel1).addContainerGap(240, Short.MAX_VALUE)));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGap(31, 31, 31).addComponent(jLabel1).addContainerGap(41, Short.MAX_VALUE)));

        // Layout setup
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addGap(260, 260, 260).addComponent(jButton1).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE).addComponent(jButton2).addGap(177, 177, 177).addComponent(jButton3).addGap(99, 99, 99))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 647, Short.MAX_VALUE).addGroup(layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE).addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))).addGap(33, 33, 33)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup().addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jButton1).addGap(55, 55, 55).addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(84, 84, 84).addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jButton3).addComponent(jButton2)).addContainerGap(86, Short.MAX_VALUE)));

        pack();
    }

    // Action for "VIEW RECORDS" button
    private void viewButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String selectedType = recordTypeComboBox.getSelectedItem().toString();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinc", "root", "");
            String sql = "SELECT * FROM patient_record";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            DefaultTableModel tm = (DefaultTableModel) jTable1.getModel();
            tm.setRowCount(0);

            while(rs.next()){
                Object o[]={rs.getInt("ID"),rs.getString("Name"),rs.getString("Disease"),rs.getString("Date"),rs.getString("Time")};
                tm.addRow(o);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    // Action for "LOGOUT" button
    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {
        LoginPage obj = new LoginPage();
        obj.setVisible(true);
        dispose();
    }

    // Action for "BACK" button
    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {
        Patient obj = new Patient();
        obj.setVisible(true);
        dispose();
    }

    // Main method to run the application
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new viewrecordsP().setVisible(true));
    }

    // Factory class for creating different medical record types
    public static class MedicalRecordFactory {

        public static MedicalRecord createRecord(String recordType, ResultSet rs) throws SQLException {
            switch (recordType.toLowerCase()) {
                case "patienthistory":
                    return new PatientHistory(rs.getInt("ID"), rs.getString("Name"), rs.getString("Disease"), rs.getString("Date"), rs.getString("Time"));
                case "prescriptions":
                    return new Prescriptions(rs.getInt("ID"), rs.getString("Name"), rs.getString("PrescriptionDetails"), rs.getString("Date"), rs.getString("Time"));
                case "labresults":
                    return new LabResults(rs.getInt("ID"), rs.getString("Name"), rs.getString("LabResult"), rs.getString("Date"), rs.getString("Time"));
                default:
                    throw new IllegalArgumentException("Invalid record type: " + recordType);
            }
        }
    }

    // Common interface for all medical records
    interface MedicalRecord {
        Object[] toTableRow(); // Convert the record to a row for the table
    }

    // Patient History class
    static class PatientHistory implements MedicalRecord {
        private int id;
        private String name;
        private String disease;
        private String date;
        private String time;

        public PatientHistory(int id, String name, String disease, String date, String time) {
            this.id = id;
            this.name = name;
            this.disease = disease;
            this.date = date;
            this.time = time;
        }

        @Override
        public Object[] toTableRow() {
            return new Object[]{id, name, disease, date, time};
        }
    }

    // Prescriptions class
    static class Prescriptions implements MedicalRecord {
        private int id;
        private String name;
        private String prescriptionDetails;
        private String date;
        private String time;

        public Prescriptions(int id, String name, String prescriptionDetails, String date, String time) {
            this.id = id;
            this.name = name;
            this.prescriptionDetails = prescriptionDetails;
            this.date = date;
            this.time = time;
        }

        @Override
        public Object[] toTableRow() {
            return new Object[]{id, name, prescriptionDetails, date, time};
        }
    }

    // Lab Results class
    static class LabResults implements MedicalRecord {
        private int id;
        private String name;
        private String labResult;
        private String date;
        private String time;

        public LabResults(int id, String name, String labResult, String date, String time) {
            this.id = id;
            this.name = name;
            this.labResult = labResult;
            this.date = date;
            this.time = time;
        }

        @Override
        public Object[] toTableRow() {
            return new Object[]{id, name, labResult, date, time};
        }
    }

    // Variables declaration
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> recordTypeComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
}
