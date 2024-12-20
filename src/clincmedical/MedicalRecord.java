/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clincmedical;

/**
 *
 * @author user
 */
abstract public class MedicalRecord
{
    String Record;
    
    void DisplayRecord(String Record)
    {
        System.out.print(Record);
    }
    
    void SetRecord(String Record)
    {
        this.Record=Record;
    }
    
    String GetRecord()
    {
       return Record;
    }
}