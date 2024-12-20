/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clincmedical;



import java.util.Scanner;
import java.util.List;
import java.util.HashMap;
import java.util.Map;



/**
 *
 * @author user
 */
abstract public class Doctor
{   
   String Name,Specilization;
   
   public Doctor(String Name,String Specilization)
   {
         this.Name=Name;
         this.Specilization=Specilization;
   }
   
    public Doctor()
   {
   }
   
   
   
    void SetName(String Name)
   {
         this.Name=Name;
   }
      
      
   void SetSpecilization(String Specilization)
   {
         this.Specilization=Specilization;
   }
   
   
   String GetSpecilization()
   {
       return Specilization;
   }
   
   String GetName()
   {
       return Name;
   }   
   
    
}
