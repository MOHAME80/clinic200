/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clincmedical;

/**
 *
 * @author original
 */
import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;


    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author original
 */

    


public class Builder extends javax.swing.JFrame implements Runnable {
    private int hour, seconds, minutes;

    // Inner Patient class with Builder
    public static class Patient {
        private final String id;
        private final String name;
        private final String disease;
        private final String admitDate;
        private final String admitTime;

        private Patient(PatientBuilder builder) {
            this.id = builder.id;
            this.name = builder.name;
            this.disease = builder.disease;
            this.admitDate = builder.admitDate;
            this.admitTime = builder.admitTime;
        }

        public static class PatientBuilder {
            private String id;
            private String name;
            private String disease;
            private String admitDate;
            private String admitTime;

            public PatientBuilder setId(String id) {
                if (id == null || id.isEmpty()) {
                    throw new IllegalArgumentException("Patient ID cannot be empty.");
                }
                this.id = id;
                return this;
            }

            public PatientBuilder setName(String name) {
                if (name == null || name.isEmpty()) {
                    throw new IllegalArgumentException("Patient name cannot be empty.");
                }
                this.name = name;
                return this;
            }

            public PatientBuilder setDisease(String disease) {
                this.disease = disease;
                return this;
            }

            public PatientBuilder setAdmitDate(String admitDate) {
                this.admitDate = admitDate;
                return this;
            }

            public PatientBuilder setAdmitTime(String admitTime) {
                this.admitTime = admitTime;
                return this;
            }

            public Patient build() {
                if (id == null || name == null) {
                    throw new IllegalArgumentException("Patient ID and name are mandatory.");
                }
                return new Patient(this);
            }
        }
    }

    public Builder() {
        initComponents();
        showDate();
        Thread t = new Thread(this);
        t.start();
    }

    private void showDate() {
        Date d = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
        pad.setText(sd.format(d));
    }

    @Override
    public void run() {
        while (true) {
            Calendar cal = Calendar.getInstance();
            hour = cal.get(Calendar.HOUR_OF_DAY);
            minutes = cal.get(Calendar.MINUTE);
            seconds = cal.get(Calendar.SECOND);

            SimpleDateFormat sdf24 = new SimpleDateFormat("HH:mm:ss");
            String time24 = sdf24.format(cal.getTime());
            pat.setText(time24);

            try {
                Thread.sleep(1000); // Avoid excessive CPU usage
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    // Initialize the components and layout
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        pi = new javax.swing.JTextField();
        pn = new javax.swing.JTextField();
        pd = new javax.swing.JTextField();
        pad = new javax.swing.JTextField();
        pat = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Admit Patient");

        jLabel1.setText("Patient ID:");
        jLabel2.setText("Name:");
        jLabel3.setText("Disease:");
        jLabel4.setText("Admit Date:");
        jLabel5.setText("Admit Time:");
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel6.setText("Admit Patient");

        jButton1.setText("Admit");
        jButton1.addActionListener(evt -> jButton1ActionPerformed(evt));

        jButton2.setText("Back");
        jButton2.addActionListener(evt -> jButton2ActionPerformed(evt));

        jButton3.setText("Logout");
        jButton3.addActionListener(evt -> jButton3ActionPerformed(evt));

        // Layout setup with GroupLayout
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(jLabel6))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(pi, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(pn, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(pd, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(pad, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(pat, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(30, 30, 30)
                                .addComponent(jButton2)
                                .addGap(30, 30, 30)
                                .addComponent(jButton3))))))) ;
                
         
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel6)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(pi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(pn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(pd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(pad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(pat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null); // Center window
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            // Use the Builder to create the Patient object
            Patient patient = new Patient.PatientBuilder()
                    .setId(pi.getText())
                    .setName(pn.getText())
                    .setDisease(pd.getText())
                    .setAdmitDate(pad.getText())
                    .setAdmitTime(pat.getText())
                    .build();

            // Database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinc", "root", "");

            String sql = "INSERT INTO patient_record (ID, Name, Disease, AdmitDate, AdmitTime) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ptstmt = conn.prepareStatement(sql);
            ptstmt.setString(1, patient.id);
            ptstmt.setString(2, patient.name);
            ptstmt.setString(3, patient.disease);
            ptstmt.setString(4, patient.admitDate);
            ptstmt.setString(5, patient.admitTime);
            ptstmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Data inserted successfully.");
            conn.close();

            // Clear fields after insertion
            pi.setText("");
            pn.setText("");
            pd.setText("");
            pat.setText("");
            pad.setText("");

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        LoginPage obj = new LoginPage();
        obj.setVisible(true);
        dispose();
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        // Navigate back to a previous page, ensure it exists
        new viewrecordsPatient().setVisible(true);
        dispose();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new admitPatient().setVisible(true));
    }

    // Variables declaration
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField pad;
    private javax.swing.JTextField pat;
    private javax.swing.JTextField pd;
    private javax.swing.JTextField pi;
    private javax.swing.JTextField pn;
    // End of variables declaration
}


