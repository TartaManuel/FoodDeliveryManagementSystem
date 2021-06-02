package businessLayer;

import java.util.ArrayList;

/**
 * Clasa in care stochez toate cele 3 liste de useri posibili, admin, client sau angajat. Le stochez toate aici pentru
 * a nu avea 3 liste separate in DeliveryService.
 * Ca metode, am doar getteri pentru liste si metode de add in cele 3 liste.
 */
public class GestionareConturi implements java.io.Serializable {

    ArrayList<AdminOperation> adList=new ArrayList<>();
    ArrayList<ClientOperation> clList=new ArrayList<>();
    ArrayList<EmployeeOperation> emList=new ArrayList<>();


    public GestionareConturi(){

    }

    public void addAdmin(AdminOperation a){
        adList.add(a);
    }

    public void addClient(ClientOperation c){
        clList.add(c);
    }

    public void addEmployee(EmployeeOperation e){
        emList.add(e);
    }

    public ArrayList<AdminOperation> getAdList() {
        return adList;
    }

    public ArrayList<ClientOperation> getClList() {
        return clList;
    }

    public ArrayList<EmployeeOperation> getEmList() {
        return emList;
    }

}
