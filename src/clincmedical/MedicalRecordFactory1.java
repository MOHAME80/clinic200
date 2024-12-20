/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clincmedical;

import clincmedical.MedicalRecords.PatientHistory;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author original
 */
public class MedicalRecordFactory1 {
    // Factory class

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

    

