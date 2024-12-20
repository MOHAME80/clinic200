/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clincmedical;

/**
 *
 * @author user
 */
public class MedicalRecordsFactory 
{
 // MedicalRecordFactory.java
    public static MedicalRecord createRecord(String recordType) {
        if (recordType == null) {
            return null;
        }
                
        switch (recordType.toLowerCase()) {
            case "patienthistory":
                return new MedicalRecords.PatientHistory();
            case "prescription":
                return new MedicalRecords.Prescription();
            case "labresult":
                return new MedicalRecords.LabResult();
            default:
                return null;
        }
    }
}
   

