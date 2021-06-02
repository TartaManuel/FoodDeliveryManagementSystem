package businessLayer;

import dataLayer.Employee;

/**
 * @author Tarta Manuel
 * Clasa in care am doar informatii despre Employee, mostenite din clasa Employee. Angajatul nu are nicio operatie
 * specifica, dar am facut aceasta clasa pentru a fi uniform cu administratorul si cu clientul.
 * Am doar un constructor in care apelez super() cu datele date ca parametrii.
 */
public class EmployeeOperation extends Employee implements java.io.Serializable{

    public EmployeeOperation(int id,String username, String parola, String email){
        super(id,username,parola,email);
    }
}
