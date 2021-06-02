package businessLayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;

/**
 * Clasa DeliveryService in care am cele 3 liste, de produse, de useri si de orders.
 * Aici am toate operatiile pentru admin si pentru client pe care le implementez din interfata. Pentru asta, doar le
 * apelez pe cele din admin, respectiv din client.
 * Aici fac asserturile pentru pre post conditii si pentru invariant.
 */
public class DeliveryService extends Observable implements IdDeliveryServiceProcessing,java.io.Serializable{

    private ArrayList<MenuItem> menuItems=new ArrayList<>();
    private GestionareConturi conturi=new GestionareConturi();
    private HashMap<Order,ArrayList<MenuItem>> orders=new HashMap<>();

    public DeliveryService(){

    }

    public boolean isWellFormed(){
        if(conturi == null)
            return false;
        if(orders == null)
            return false;
        if(menuItems == null)
            return false;
        return true;
    }

    @Override
    public List<BaseProduct> importProducts(String path) {
        //pentru invariant
        assert isWellFormed() : "Listele sunt null";
        assert (path != null):"Path gresit";
        List<BaseProduct> produse= conturi.getAdList().get(conturi.getAdList().size()-1).importProducts(path);
        assert (produse.size()>=0) : "Nu am importat";
        return produse;
    }

    @Override
    public int addProduct(BaseProduct produs,ArrayList<MenuItem> produse) {
        assert isWellFormed() : "Listele sunt null";
        assert (produs != null) : "Produsul este null";
        assert (produse != null) : "Lista de produse in care sa inseram este null";
        int size=produse.size();
        int ok= conturi.getAdList().get(conturi.getAdList().size()-1).addProduct(produs,produse);
        //assert (size+1 == produse.size()) : "Produsul nu a fost inserat";
        return ok;
    }

    @Override
    public int deleteProduct(String name, ArrayList<MenuItem> aMenu) {
        assert isWellFormed() : "Listele sunt null";
        assert (name != null) : "Produsul este null";
        assert (aMenu != null) : "Lista de produse in care sa inseram este null";
        assert (aMenu.size()>0) : "Lista de produse este goala";
        int size=aMenu.size();
        int ok=conturi.getAdList().get(conturi.getAdList().size()-1).deleteProduct(name,aMenu);
        assert(aMenu.size()==size-1) : "Produsul nu a fost sters";
        return ok;
    }

    @Override
    public int editProduct(MenuItem m, ArrayList<MenuItem> aMenu) {
        assert isWellFormed() : "Listele sunt null";
        assert (m != null) : "Produsul este null";
        assert (aMenu != null) : "Lista de produse in care sa inseram este null";
        return conturi.getAdList().get(conturi.getAdList().size()-1).editProduct(m,aMenu);
    }

    @Override
    public int createNewProduct(CompositeProduct produs,ArrayList<MenuItem> produse) {
        assert isWellFormed() : "Listele sunt null";
        assert (produs != null) : "Produsul este null";
        assert (produse != null) : "Lista de produse in care sa inseram este null";
        int size=produse.size();
        int ok= conturi.getAdList().get(conturi.getAdList().size()-1).addProductComp(produs,produse);
        assert (size+1 == produse.size()) : "Produsul nu a fost inserat";
        return ok;
    }

    @Override
    public int generareRaportTimp(int oraI, int oraF, HashMap<Order,ArrayList<MenuItem>> map) {
        assert isWellFormed() : "Listele sunt null";
        assert (oraI >= 0 && oraI <=23) : "Ora de inceput nu este buna";
        assert (oraF >= 0 && oraF <=23) : "Ora de final nu este buna";
        assert (map != null) : "HashMap-ul este null";
        return conturi.getAdList().get(conturi.getAdList().size()-1).generareRaportTimp(oraI,oraF, map);
    }

    @Override
    public int generareRaportNrComenzi(int nrComenzi,HashMap<Order,ArrayList<MenuItem>> map) {
        assert isWellFormed() : "Listele sunt null";
        assert (nrComenzi >= 0) : "Numarul de comenzi trebuie sa fie cel putin 0";
        assert (map != null) : "HashMap-ul este null";
        return conturi.getAdList().get(conturi.getAdList().size()-1).generareRaportNrComenzi(nrComenzi, map);
    }

    @Override
    public void generareRaportClienti(int nrComenzi,int value,HashMap<Order,ArrayList<MenuItem>> map) {
        assert isWellFormed() : "Listele sunt null";
        assert (nrComenzi >= 0) : "Numarul de comenzi trebuie sa fie cel putin 0";
        assert (value >= 0) : "Valoarea nu este buna";
        assert (map != null) : "HashMap-ul este null";
        conturi.getAdList().get(conturi.getAdList().size()-1).generareRaportClienti(nrComenzi,value, map);
    }

    @Override
    public void generareRaportProduse(int Ziua,int luna,HashMap<Order,ArrayList<MenuItem>> map) {
        assert isWellFormed() : "Listele sunt null";
        assert (Ziua >= 1 && Ziua <=31) : "Ziua nu este buna";
        assert (luna >= 0 && luna <=12) : "Luna nu este buna";
        assert (map != null) : "HashMap-ul este null";
        conturi.getAdList().get(conturi.getAdList().size()-1).generareRaportProduse(Ziua,luna, map);
    }


    @Override
    public List<MenuItem> searchProduct(String keyword,String Keyword,float ratingDorit,int[] criterii,ArrayList<MenuItem> items) {
        assert isWellFormed() : "Listele sunt null";
        assert (keyword != null) : "Keyword este null";
        assert (ratingDorit >= -1) : "Ratingul trebuie cel putin 0";
        assert (criterii != null) : "Vectorul de criterii este null";
        assert (items != null) : "lista de iteme din care cautam este null";
        return conturi.getClList().get(conturi.getClList().size()-1).searchProduct(keyword,Keyword,ratingDorit, criterii,items);
    }


    @Override
    public void createOrder(ArrayList<MenuItem> produse, HashMap<Order,ArrayList<MenuItem>> map,ClientOperation client) {
        assert isWellFormed() : "Listele sunt null";
        assert (map!=null) : "HashMap-ul este null";
        assert (client!=null) : "Clientul este null";
        assert produse.size()>0 : "Lista de produse nu are niciun element";
        int size=map.size();
        conturi.getClList().get(conturi.getClList().size()-1).createOrder( produse, map, client);
        assert (size+1 == map.size()) : "Orderul nu a fost inserat";
        setChanged();
        notifyObservers(produse);
    }

    public ArrayList<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(ArrayList<MenuItem> menuItems) {
        this.menuItems=menuItems;
    }

    public GestionareConturi getConturi() {
        return conturi;
    }

    public HashMap<Order, ArrayList<MenuItem>> getOrders() {
        return orders;
    }

    public void setOrders(HashMap<Order, ArrayList<MenuItem>> orders) {
        this.orders = orders;
    }
}
