package businessLayer;

import dataLayer.Client;
import dataLayer.FileWriter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Tarta Manuel
 * Clasa care contine operatiile specifice clientului, si anume crearea unui order, cautarea unor produse cu anumite criterii.
 * Metodele din aceasta clasa vor fi apelate in clasa DeliveryService care contine atat metodele specifice clientului, cat si metodele
 * pentru administrator.
 */
public class ClientOperation extends Client implements java.io.Serializable {

    public ClientOperation(int id,String username, String parola, String email){
        super(id,username,parola,email);
    }

    /**
     *
     * In aceasta metoda, adaug in HaspMap un order nou care contine produsele date ca parametru si a fost realizat de clientul
     * dat ca parametru.
     * In order am un camp pretTotal care reprezinta pretul total al produselor din acea comanda. Il setez cu setPretTotal.
     * Pentru adaugarea in HashMap apelez put.
     * Dupa ce am adaugat orderul, fac si un bill pentru acel order in care scriu despre id, client si produsele din acest order,
     * intr-un fisier txt.
     * Aceasta metoda va fi apelata in clasa DeliveryService
     */
    public void createOrder(ArrayList<MenuItem> produse, HashMap<Order,ArrayList<MenuItem>> map,ClientOperation client) {
        Order o=new Order(map.size()+1,client.getId(), LocalDateTime.now());
        int pret=0;
        o.setPretTotal(produse);
        map.put(o,produse);

        FileWriter f=new FileWriter();
        f.printBill(o,produse,this);
    }

    /**
     * Metoda cu care clientul cauta produsele care indeplinesc anumite criterii specificate ca parametrii. Aceste criterii
     * pot fi in numar de 1 pana la 7: keyword in numele produsului, rating, calorii, proteine, fats, sodium sau pret.
     * Cautarea o fac cu ajutorul streamurilor. Prima data fac o filtrare in functie de keyword (doar daca keyword este diferit
     * de "", adica daca am trimis un keyword ca parametru). Dupa fac o filtrare in functie de toate celelalte 6 criterii. Aceasta
     * filtrare o fac doar daca criteriile sunt diferite de -1. In cazul in care un criteriu este egal cu -1, acea filtrare nu va
     * face nimic si toate produsele vor trece spre urmatoarele filtrari.
     */
    public List<MenuItem> searchProduct(String keyword,String Keyword,float ratingDorit,int[] criterii,ArrayList<MenuItem> aMenu) {

        List<MenuItem> items=aMenu
                .stream()
                .filter(m->(m.getTitle().contains(keyword)||m.getTitle().contains(Keyword)||keyword.equals("")))
                .collect(Collectors.toList())
                .stream()
                .filter(p->(p.getRating()==ratingDorit||ratingDorit==-1)&&(p.getCalories()==criterii[0]||criterii[0]==-1)
                &&(p.getProtein()==criterii[1]||criterii[1]==-1)&&(p.getFat()==criterii[2]||criterii[2]==-1)
                &&(p.getSodium()==criterii[3]||criterii[3]==-1)&&(p.getPrice()==criterii[4]||criterii[4]==-1))
                .collect(Collectors.toList());

        return items;

    }
}
