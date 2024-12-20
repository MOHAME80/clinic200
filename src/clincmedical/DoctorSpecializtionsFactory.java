/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clincmedical;

/**
 *
 * @author user
 */
public class DoctorSpecializtionsFactory {
    
    public static Doctor createDoctor(String specialization) {
        if (specialization == null) {
            return null;
        }
        switch (specialization.toLowerCase()) 
        {
            case "cardiologist":
               return new DoctorSpecializations.Cardiologist();
            case "neurologist":
                return new DoctorSpecializations.Neurologist();
            case "generalpractitioner":
                return new DoctorSpecializations.GeneralPractitioner();
            default:
                return null;
        }
    }
    
}
    
   
    

