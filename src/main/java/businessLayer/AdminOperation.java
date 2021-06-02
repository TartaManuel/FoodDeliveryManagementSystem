package businessLayer;

import dataLayer.Admin;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Tarta Manuel
 * Clasa in care realizez operatiile specifice pentru administrator, si anume import produse din csv, adaug, sterg sau modific un
 * produs, adaug un produs composite, generez cele 4 rapoarte.
 */
public class AdminOperation extends Admin{

   public AdminOperation(int id,String username,String parola, String email){
        super(id,username,parola,email);
    }

    public AdminOperation(){
        super();

    }


    /**
     *
     * @param path care reprezinta calea spre fisierul csv
     * @return returnez lista de produse importate; acea lista urmeaza sa fie inserata in lista din deliveryService, in controller
     *
     * Pentru extragerea din fisierul csv iau linie cu linie si pentru fiecare linie construiesc un obiect de tipul BaseProduct cu
     * datele de pe acea linie. Fiecare linie are cele 7 date reprezentative pentru un produs separate prin virgula, deci inainte de
     * crearea obiectului fac split in functie de virgula si dupa mapez acel vector de Stringuri in obiectul dorit
     * Toate aceste operatii le realizez cu streamuri si lambda processing
     */
    public List<BaseProduct> importProducts(String path){
        List<BaseProduct> inputList = new ArrayList<>();

        try{

            File inputF = new File(path);
            InputStream input = new FileInputStream(inputF);
            BufferedReader br = new BufferedReader(new InputStreamReader(input));

            inputList =br.lines()
                    .skip(1)
                    .map((line) -> { String[] p = line.split(",");BaseProduct item = new BaseProduct(p[0],Float.parseFloat(p[1]),Integer.parseInt(p[2]),Integer.parseInt(p[3]), Integer.parseInt(p[4]), Integer.parseInt(p[5]),Integer.parseInt(p[6]));return item; })
                    .distinct().collect(Collectors.toList());

            br.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return inputList ;
    }

    /**
     *
     * @param produs produsul pe care il vreau adaugat
     * @param produse lista in care sa adaug produsul
     * @return 1 daca s-a facut adaugarea, altfel 0
     *
     * Pentru adaugare, verific initial daca exista deja un produs cu numele celui pe care vreau sa il inserea. Daca
     * exista, returnez 0, altfel il adaug si returnez 1.
     */
    public int addProduct(BaseProduct produs, ArrayList<MenuItem> produse){

        int contor=produse.size();
        for(int i=0;i<contor;i++){
            if(produse.get(i).getTitle().equals(produs.getTitle())){
                return 0;
            }
        }
        produse.add(produs);
        return 1;
    }

    /**
     *
     * @param produs Produsul composite pe care vreau sa il adaug
     * @param produse Lista de produse in care il adaug
     * @return 1 daca nu mai exista un produs cu acel nume si il pot insera cu succes, altfel 0
     */
    public int addProductComp(CompositeProduct produs, ArrayList<MenuItem> produse){

        int contor=produse.size();
        for(int i=0;i<contor;i++){
            if(produse.get(i).getTitle().equals(produs.getTitle())){
                return 0;
            }
        }
        produse.add(produs);
        return 1;
    }

    /**
     *
     * @param name Numele produsului pe care doresc sa il sterg
     * @param aMenu Lista din care vreau sa fac stergerea
     * @return 1 pentru succes, 0 altfel
     *
     * Pentru stergere, caut produsul cu numele dat ca parametru. Daca gasesc un produs cu acel nume, il sterg si returnez 1,
     * altfel returnez 0.
     */
    public int deleteProduct(String name, ArrayList<MenuItem> aMenu) {
        ArrayList<MenuItem> aux=aMenu;
        int contor=aMenu.size();
        for(int i=0;i<contor;i++){
            if(aux.get(i).getTitle().equals(name)){
                aMenu.remove(i);
                return 1;
            }
        }
        return 0;
    }

    /**
     *
     * @param m produsul pe care doresc sa il editez, cu campurile noi pe care le doresc
     * @param aMenu Lista de produse
     * @return 1 daca am putut face modificarea, altfel 0
     *
     * Pentru inceput caut in lista de produse, un produs care are numele egal cu numele din m. Daca il gasesc, ii inlocuiesc
     * campurile cu cele din produsul m si returnez 1, altfel returnez 0.
     */
    public int editProduct(MenuItem m, ArrayList<MenuItem> aMenu) {
        int contor=aMenu.size();
        for(int i=0;i<contor;i++){
            if(aMenu.get(i).getTitle().equals(m.getTitle())){
                aMenu.get(i).setCalories(m.getCalories());
                aMenu.get(i).setRating(m.getRating());
                aMenu.get(i).setProtein(m.getProtein());
                aMenu.get(i).setFat(m.getFat());
                aMenu.get(i).setSodium(m.getSodium());
                aMenu.get(i).setPrice(m.getPrice());
                return 1;
            }
        }
        return 0;
    }


    /**
     *
     * @param oraI ora minima
     * @param oraF ora maxiam
     * @param map HashMap-ul in care se afla orderurile
     * @return 1 daca a gasit un order cel putin, altfel 0
     *
     * Trebuie generat un raport care contine toate orderurile realizate intre 2 ore date ca parametru. Pentru asta
     * folosesc streamuri. Prima data filtrez orderurile in functie de acele ore si creez o lista de orderuri care au
     * ora corecta. Pe acestea le afisez intr-un fisier txt.
     *
     */
    public int generareRaportTimp(int oraI, int oraF, HashMap<Order,ArrayList<MenuItem>> map) {

        List<Order> orders=map.entrySet().stream().filter(m->m.getKey().getOrderDate().getHour()>=oraI&&m.getKey().getOrderDate().getHour()<=oraF)
                .map(Map.Entry::getKey).collect(Collectors.toList());

        java.io.FileWriter fileWriter = null;
        try {
            fileWriter = new java.io.FileWriter("raportOre.txt");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PrintWriter printWriter = new PrintWriter(fileWriter);

        int ok=0;
        for(Order o:orders){
            ok=1;
            printWriter.println("Orderul "+o.getOrderId()+" a fost facut la ora "+o.getOrderDate().getHour());
        }

        printWriter.close();

        return ok;

    }

    /**
     * @param nrComenzi Numarul minim de comenzi in care sa apara un produs
     * @param map HashMap-ul in care avem orderurile
     * @return 1 daca am gasit cel putin un produs, altfel 0.
     *
     * Cu streams, filtrez orderurile care au campul nrComenzi cel putin egal cu parametrul nrComenzi. Fac distinct pentru
     * a nu avea dubluri si le adaug intr-o lista de MenuItems.
     * Parcurg aceasta lista si o afisez intr-un txt.
     */
    public int generareRaportNrComenzi(int nrComenzi,HashMap<Order,ArrayList<MenuItem>> map) {

        List<MenuItem> produse=new ArrayList<>();
        map.entrySet().stream().forEach(value->value.getValue().stream().filter(p->p.getNrComenzi()>=nrComenzi)
        .distinct().forEach(p->produse.add(p)));

        List<MenuItem> produseFinal=produse.stream().distinct().collect(Collectors.toList());

        java.io.FileWriter fileWriter = null;
        try {
            fileWriter = new java.io.FileWriter("raportNrComenzi.txt");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PrintWriter printWriter = new PrintWriter(fileWriter);

        int ok=0;
        for(MenuItem m:produseFinal){
            ok=1;
            printWriter.println("Produsul "+m.getTitle()+" a fost comandat de "+m.getNrComenzi()+" ori");
        }
        printWriter.close();
        return ok;
    }

    /**
     *
     * @param nrComenzi Numarul minim de comenzi pe care sa le fi facut un client
     * @param valoare Valoarea minima a comenzilor
     * @param map HashMap-ul in care am stocate comenzile
     *
     * Parcurg orderurile, le filtrez astfel incat sa le am doar pe cele cu pretul cel putin egal cu parametrul valoare.
     * Dupa, cu map grupez aceste orderuri in functie de id-ul clientului care le-a realizat. Parcurg acum acest map care
     * are ca cheie id-ul clientilor si filtrez astfel incat sa obtin doar clientii care au size-ul listei de orderuri realizate
     * cel putin egal cu nrComenzi si pentru fiecare fac o afisare intr-un txt.
     */
    public void generareRaportClienti(int nrComenzi,int valoare,HashMap<Order,ArrayList<MenuItem>> map) {

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("raportClienti.txt");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println("Clientii care au comandat de cel putin "+nrComenzi+" ori cu valoarea orderului "+valoare+" sunt:");

        Map<Integer, List<Order>> ordersId = map.entrySet().stream().filter(m -> m.getKey().getPretTotal() >= valoare)
                .map(Map.Entry::getKey).collect(Collectors.toList())
                .stream()
                .collect(Collectors.groupingBy(p -> p.getClientId()));

        ordersId
                .entrySet().stream().filter(p->p.getValue().size()>=nrComenzi).
                //forEach(p->printWriter.println(p.getValue().size()));
                 forEach(p->printWriter.println(p.getValue().get(0).getClientId()));

        printWriter.close();
    }

    /**
     *Parcurg HashMap-ul si il filtrez astfel incat sa ramana doar comenzile care au ziua si luna egale cu cele din parametrii.
     *Creez o lista cu produsele care au aparut in acele orderuri, parcurg aceasta lista dupa ce am facut distinct pentru a scapa
     * de duplicate si afisez informatiile despre fiecare produs intr-un fisier txt.
     */
    public void generareRaportProduse(int Ziua,int luna,HashMap<Order,ArrayList<MenuItem>> map) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("raportProduse.txt");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PrintWriter printWriter = new PrintWriter(fileWriter);

        printWriter.println("In ziua "+Ziua+", luna "+luna+" au fost comandate:");

        List<MenuItem> items=new ArrayList<>();
        map.entrySet()
                .stream()
                .filter(p->((p.getKey().getOrderDate().getDayOfMonth()==Ziua)&&(p.getKey().getOrderDate().getMonthValue()==luna)))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList())
                .stream()
                .forEach(p->p.stream().distinct().forEach(x->items.add(x)));

        items
                .stream()
                .collect(Collectors.groupingBy(p->p.getTitle())).entrySet().stream().
                forEach(p->printWriter.println(p.getKey()+" a aparut de "+p.getValue().size()+" ori"));

        printWriter.close();
    }
}
