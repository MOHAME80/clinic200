package clincmedical;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */



/**
 *
 * @author user
 * 
 * 
 * 
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// Doctor.java (Interface)



public class DoctorSpecializations {
    
// Cardiologist.java
static public class Cardiologist extends Doctor 
{
    public Cardiologist()
    {
      super.SetSpecilization("Cardiologist");
    }
    
    public void performDiagnosis() 
    {
        System.out.println("Performing Cardiological Diagnosis");
    }
}

// Neurologist.java
static public class Neurologist extends Doctor
{
      public Neurologist()
    {
      super.SetSpecilization("Neurologist");
    }
    
    
    public void performDiagnosis() {
        System.out.println("Performing Neurological Diagnosis");
    }
}



// GeneralPractitioner.java
static public class GeneralPractitioner extends Doctor
{
     public GeneralPractitioner()
    {
      super.SetSpecilization("GeneralPractitioner");
    }
    
    
    public void performDiagnosis() {
        System.out.println("Performing General Diagnosis");
    }
    
        
}
    
}
