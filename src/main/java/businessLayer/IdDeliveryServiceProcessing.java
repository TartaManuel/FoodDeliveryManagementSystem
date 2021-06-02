package businessLayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface IdDeliveryServiceProcessing {
    //pentru admin

    /**
     * @param path care e un string ce reprezinta calea spre fisier
     * @return o lista de BaseProducts extrase din fisierul csv de la acel path
     * @inv isWellFormed()
     * @pre path != null
     * @post menuItems.size() > 0
     */
    public List<BaseProduct> importProducts(String path);

    /**
     * @param produs e acel produs pe care vrem sa il adaugam
     * @param produse lista in care vrem sa adaugam produsul
     * @pre produs != null
     * @pre produse !=null
     * @post produse.size() == produse.size()@pre + 1
     * @return 1 in caz de succes, 0 daca nu a putut adauga
     */
    public int addProduct(BaseProduct produs,ArrayList<MenuItem> produse);

    /**
     * @param name numele produsului care se doreste sters
     * @param aMenu lista din care sa stergem
     * @return 1 in caz de succes, 0 daca nu se poate sterge
     * @inv isWellFormed()
     * @pre name != null
     * @pre aMenu != null
     * @pre aMenu.size() > 0
     * @post produse.size() == produse.size()@pre - 1
     */
    public int deleteProduct(String name,ArrayList<MenuItem> aMenu);

    /**
     * @param m acel menuItem pe care vrem sa il modificam
     * @param aMenu lista in care se afla produsul
     * @return 1 in caz de succes, 0 in caz de eroare
     * @inv isWellFormed()
     * @pre m != null
     * @pre aMenu != null
     */
    public int editProduct(MenuItem m,ArrayList<MenuItem> aMenu);

    /**
     * @param produs produsul Composite care vrem sa il adaugam
     * @param produse lista de produse unde il adaugam
     * @return 1 in caz de succes, 0 in caz de eroare
     * @inv isWellFormed()
     * @pre produs != null
     * @pre produse != null
     * @post produse.size() = produse.size()@pre + 1
     */
    public int createNewProduct(CompositeProduct produs,ArrayList<MenuItem> produse);

    /**
     * @param oraI ora minima pentru intervalul in care sa se afle orderurile
     * @param oraF ora maxima pentru interval
     * @param map HashMap-ul unde le avem stocate
     * @return 1 in caz de succes, 0 in caz de eroare
     * @inv isWellFormed()
     * @pre oraI >= 0
     * @pre 23 >= oraI
     * @pre 23 >= oraF
     * @pre oraF >=0
     * @pre map != null
     */
    public int generareRaportTimp(int oraI, int oraF, HashMap<Order,ArrayList<MenuItem>> map);

    /**
     * @param nrComenzi numarul minim de comenzi in care sa apara acel produs
     * @param map HashMap-ul in care se afla orderurile
     * @return 1 in caz de succes, 0 in caz de eroare
     * @inv isWellFormed()
     * @pre nrComenzi >= 0
     * @pre map != null
     */
    public int generareRaportNrComenzi(int nrComenzi,HashMap<Order,ArrayList<MenuItem>> map);

    /**
     * @param nrComenzi numarul minim de comenzi pe care sa le fi facut clientul
     * @param value valoarea minima a comenzilor
     * @param map HashMap-ul in care sunt stocate orderurile
     * @inv isWellFormed()
     * @pre nrComenzi >= 0
     * @pre value >= 0
     * @pre map != null
     */
    public void generareRaportClienti(int nrComenzi,int value,HashMap<Order,ArrayList<MenuItem>> map);

    /**
     * @param Ziua ziua din luna in care sa se fi facut comanda
     * @param luna luna in care a fost facuta comanda
     * @param map HashMap-ul in care avem orderurile
     * @inv isWellFormed()
     * @pre Ziua >= 1
     * @pre 31 >= Ziua
     * @pre luna >=1
     * @pre 12 >= luna
     * @pre map != null
     */
    public void generareRaportProduse(int Ziua,int luna,HashMap<Order,ArrayList<MenuItem>> map);

    //pentru client

    /**
     *
     * @param keyword cuvantul pe care trebuie sa il contina numele produsului pe care il cautam
     * @param ratingDorit ratingul dorit
     * @param criterii un vector de criterii care contine toate criteriile de tip int(calorii, proteine, fats, sodium si price)
     * @param items lista de meniuri din care sa caute
     * @return returneaza lista de produse care indeplinesc acele criterii
     * @inv isWellFormed()
     * @pre keyword != null
     * @pre ratingDorit >= 0
     * @pre criterii != null
     * @pre items.size() > 0
     * @post itemeGasite != null
     */
    public List<MenuItem> searchProduct(String keyword,String Keyword,float ratingDorit,int[] criterii,ArrayList<MenuItem> items);

    /**
     * @param produse lista de produse ce vor fi adaugate la acest order
     * @param map HashMap-ul in care sa adaugam orderul
     * @param client Clientul care a facut comanda
     * @inv isWellFormed()
     * @pre produse.size() > 0
     * @pre map != null
     * @pre client != null
     * @post map.size() == map.size()@pre + 1
     */
    public void createOrder(ArrayList<MenuItem> produse, HashMap<Order,ArrayList<MenuItem>> map,ClientOperation client);

}
