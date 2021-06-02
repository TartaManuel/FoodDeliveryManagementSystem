package presentationLayer;

import businessLayer.*;
import businessLayer.MenuItem;
import dataLayer.Serializator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Tarta Manuel
 * Clasa Controllerr in care fac logica pentru butoane.
 */
public class Controller {

    MainPage m;
    AdministratorGUI admin;
    ClientGUI client;
    EmployeeGUI employee;
    ViewProducts viewPr;

    DeliveryService service;
    ClientOperation clientLogat;
    Serializator ser;

    ArrayList<MenuItem> produseOrder=new ArrayList<MenuItem>();
    ArrayList<BaseProduct> produseComposite=new ArrayList<>();

    float ratingDorit;
    int criterii[]=new int[5];
    String keyword;

    public Controller(MainPage m,AdministratorGUI admin,DeliveryService service,ClientGUI client,
                      EmployeeGUI employee,ViewProducts viewPr){
        this.m=m;
        this.admin=admin;
        this.service=service;
        this.client=client;
        this.employee=employee;
        this.viewPr=viewPr;
        ratingDorit=-1;
        keyword="";
        for(int i=0;i<5;i++){
            criterii[i]=-1;
        }

        service.addObserver(employee);
        ser=new Serializator();

        m.butonLoginListener(new ButonLogin());
        m.butonRegisterListener(new ButonRegister());

        admin.butonAddListener(new ButonAdd());
        admin.butonDeleteListener(new ButonDelete());
        admin.butonEditListener(new ButonEdit());
        admin.butonImportListener(new ButonImport());
        admin.butonInapoiListener(new ButonInapoi());
        admin.butonAddProductListener(new ButonAddPr());
        admin.butonCreateListener(new ButonCreate());
        admin.butonRaport1Listener(new ButonRaport1());
        admin.butonRaport2Listener(new ButonRaport2());
        admin.butonRaport3Listener(new ButonRaport3());
        admin.butonRaport4Listener(new ButonRaport4());


        client.butonInapoiListener(new ButonInapoiClient());
        client.butonViewProductsListener(new ButonViewProduct());
        client.butonAddCriteriuListener(new ButonAddCriteriu());
        client.butonSearchProductListener(new ButonSearchProduct());
        client.butonAddProductListener(new ButonAddProduct());
        client.butonAddOrderListener(new ButonAddOrder());

        employee.butonInapoiListener(new ButonInapoiEmployee());

        viewPr.butonInapoiListener(new ButonInapoiViewPr());

    }

    /**
     * Butonul de Login. Iau datele de pe gui, verific daca pentru functia respectiva exista un user care sa corespunda, si daca
     * exista trec la interfata reprezentativa pentru acea functie. Daca nu exista, afisez un mesaj de eroare.
     */
    class ButonLogin implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            String username = m.getUsername();
            String parola = m.getParola();
            String functie = m.geteFunctie();

            if (username.equals("") == true || parola.equals("") == true) {
                JOptionPane.showMessageDialog(m, "Introduceti parametrii!", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                if (functie.equals("Admin")) {
                    for (AdminOperation a : service.getConturi().getAdList()) {
                        if (a.getUsername().equals(username) && a.getParola().equals(parola)) {
                            admin.setVisible(true);
                            m.setVisible(false);

                            admin.resetComboBox();
                            for(MenuItem m:service.getMenuItems()){
                                if(m instanceof BaseProduct) {
                                    admin.setComboBox(m.printMenuItems());
                                }
                            }

                            return;
                        }
                    }
                    JOptionPane.showMessageDialog(m, "Userul nu este admin!", "Error", JOptionPane.INFORMATION_MESSAGE);

                }
                if (functie.equals("Client")) {
                    for (ClientOperation c : service.getConturi().getClList()) {
                        if (c.getUsername().equals(username) && c.getParola().equals(parola)) {
                            clientLogat=c;
                            //System.out.println(clientLogat.getId()+" "+clientLogat.getUsername());
                            client.setVisible(true);
                            m.setVisible(false);

                            client.resetComboBox();
                            for(MenuItem m:service.getMenuItems()){
                                client.setComboBox(m.printMenuItems());
                            }

                            return;
                        }
                    }
                    JOptionPane.showMessageDialog(m, "Userul nu este client!", "Error", JOptionPane.INFORMATION_MESSAGE);

                }
                if (functie.equals("Employee")) {
                    for (EmployeeOperation em : service.getConturi().getEmList()) {
                        if (em.getUsername().equals(username) && em.getParola().equals(parola)) {
                            employee.setVisible(true);
                            m.setVisible(false);
                            return;
                        }
                    }
                    JOptionPane.showMessageDialog(m, "Userul nu este employee!", "Error", JOptionPane.INFORMATION_MESSAGE);

                }
            }
        }
    }

    /**
     * Butonul de register cu care creez un user cu datele date pe interfata si functia selectata.
     */
    class ButonRegister implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String username=m.getUsername();
            String parola=m.getParola();
            String functie=m.geteFunctie();
            String email=m.getEmail();

            if(username.equals("")==true||parola.equals("")==true||email.equals("")==true){
                JOptionPane.showMessageDialog(m, "Introduceti parametrii!","Error", JOptionPane.INFORMATION_MESSAGE);
            }
            else {

                if (functie.equals("Admin")) {
                    AdminOperation a=new AdminOperation(service.getConturi().getAdList().size()+1,username, parola, email);
                    service.getConturi().addAdmin(a);
                    JOptionPane.showMessageDialog(m, "Adminul cu id "+a.getId()+" adaugat cu succes", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
                if (functie.equals(("Client"))) {
                    ClientOperation c=new ClientOperation(service.getConturi().getClList().size()+1,username, parola, email);
                    service.getConturi().addClient(c);
                    JOptionPane.showMessageDialog(m, "Client cu id "+c.getId()+" adaugat cu succes!", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
                if (functie.equals("Employee")) {
                    EmployeeOperation em=new EmployeeOperation(service.getConturi().getEmList().size()+1,username, parola, email);
                    service.getConturi().addEmployee(em);
                    JOptionPane.showMessageDialog(m, "Employee cu id "+em.getId()+" adaugat cu succes!", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            ser.serialize(service);
        }
    }

    class ButonInapoi implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            admin.setVisible(false);
            m.setVisible(true);
        }
    }

    /**
     * Butonul cu care fac import la produsele din csv. Am facut distinct din nou si aici, ca sa nu pot da import la aceleasi
     * produse de mai multe ori.
     * Produsele la care dau import sunt de tipul BaseProduct.
     */
    class ButonImport implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            List<BaseProduct> products=service.importProducts("products.csv");

            for(BaseProduct pr:products){
                service.getMenuItems().add(pr);
            }

            List<MenuItem> pro=service.getMenuItems()
                    .stream()
                    .distinct()
                    .collect(Collectors.toList());

            service.getMenuItems().removeAll(service.getMenuItems());
            for(MenuItem pr:pro){
                service.getMenuItems().add((BaseProduct)pr);
            }

            admin.resetComboBox();
            for(MenuItem m:service.getMenuItems()){
                if(m instanceof BaseProduct) {
                    admin.setComboBox(m.printMenuItems());
                }
            }
            JOptionPane.showMessageDialog(m, "Au fost importate cu succes "+products.size()+" de produse", "Error", JOptionPane.INFORMATION_MESSAGE);
            ser.serialize(service);
        }
    }

    /**
     * Butonul cu care adminul poate adauga BaseProducts noi. Doar iau datele de pe interfata si apelez metoda din
     * deliveryService da addProduct pentru un BaseProduct cu aceste fielduri. Daca nu a putut fi adaugat, afisez un mesaj
     * de eroare. Daca campurile de pe interfata nu sunt completate corect, afisez un mesaj de eroare.
     */
    class ButonAdd implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                String title= admin.getTitleAdd();
                if(title.equals("")==true){
                    JOptionPane.showMessageDialog(m, "Datele nu au fost introduse corect", "Error", JOptionPane.INFORMATION_MESSAGE);
                    return;

                }
                float rating=Float.parseFloat(admin.getRatingAdd());
                int calories=Integer.parseInt(admin.getCaloriesAdd());
                int protein=Integer.parseInt(admin.getProteinAdd());
                int fat=Integer.parseInt(admin.getFatAdd());
                int Sodium=Integer.parseInt(admin.getSodiumAdd());
                int price=Integer.parseInt(admin.getPriceAdd());

                int ok=service.addProduct(new BaseProduct(title+" ",rating,calories,protein,fat,Sodium,price),
                service.getMenuItems());
                if(ok==1) {
                    admin.setComboBox(new BaseProduct(title+" ",rating,calories,protein,fat,Sodium,price).printMenuItems());
                    JOptionPane.showMessageDialog(m, "Produs adaugat cu succes", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(m, "Exista deja un produs cu acest nume", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
            }catch(Exception ex){
                JOptionPane.showMessageDialog(m, "Datele nu au fost introduse corect", "Error", JOptionPane.INFORMATION_MESSAGE);
            }

            ser.serialize(service);
        }
    }

    /**
     * Butonul de edit. Primeste aceleasi date ca si la add, doar ca nu adaug un produs nou, ci caut un produs existent la care ii
     * modific datele. Daca nu am gasit produsul cu acel nume, afisez un mesaj de eroare. Am un mesaj de eroare si in cazul in care
     * nu se introduc corect datele.
     */
    class ButonEdit implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                String title= admin.getTitleEdit()+" ";
                if(title.equals("")==true){
                    JOptionPane.showMessageDialog(m, "Datele nu au fost introduse corect", "Error", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                float rating=Float.parseFloat(admin.getRatingEdit());
                int calories=Integer.parseInt(admin.getCaloriesEdit());
                int protein=Integer.parseInt(admin.getProteinEdit());
                int fat=Integer.parseInt(admin.getFatEdit());
                int Sodium=Integer.parseInt(admin.getSodiumEdit());
                int price=Integer.parseInt(admin.getPriceEdit());

                int edit=service.editProduct(new MenuItem(title,rating,calories,protein,fat,Sodium
                ,price),service.getMenuItems());

                if(edit==1) {
                    admin.resetComboBox();
                    for(MenuItem m:service.getMenuItems()){
                        if(m instanceof BaseProduct) {
                            admin.setComboBox(m.printMenuItems());
                        }
                    }
                    JOptionPane.showMessageDialog(m, "Produs editat cu succes", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(m, "Produs nu a fost gasit", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
            }catch(Exception ex){
                JOptionPane.showMessageDialog(m, "Datele nu au fost introduse corect", "Error", JOptionPane.INFORMATION_MESSAGE);
            }

            ser.serialize(service);
        }
    }

    /**
     * Butonul de Delete in care primesc un nume de pe gui si apelez metoda din DeliveryService de deleteProduct. Daca nu este gasit
     * produsul, se afiseaza un mesaj de eroare.
     */
    class ButonDelete implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String title= admin.getTitleDelete()+" ";
            if(title.equals("")==true){
                JOptionPane.showMessageDialog(m, "Datele nu au fost introduse corect", "Error", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            int sters=service.deleteProduct(title,service.getMenuItems());

            if(sters==1) {
                admin.resetComboBox();
                for(MenuItem m:service.getMenuItems()){
                    if(m instanceof BaseProduct) {
                        admin.setComboBox(m.printMenuItems());
                    }
                }
                JOptionPane.showMessageDialog(m, "Produs sters cu succes", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(m, "Produs nu a fost gasit", "Error", JOptionPane.INFORMATION_MESSAGE);
            }

            ser.serialize(service);
        }
    }

    /**
     * Butonul de adaugare a unui produs cu care sa se creeze un CompositeProduct. Tot adaug produse la o lista globala
     * pana cand apas butonul de Create pentru un produs Composite. Produsele care sa fie folosite pentru crearea unui Composite
     * sunt alese dintr-un ComboBox.
     */
    class ButonAddPr implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            String produs=admin.getProduse();
            int indexVirgula=produs.indexOf(",");
            String numeProdus=produs.substring(0 , indexVirgula);
            for(MenuItem me:service.getMenuItems()){
                if(me.getTitle().equals(numeProdus)){
                    me.printMenuItems();
                    produseComposite.add((BaseProduct)me);
                    JOptionPane.showMessageDialog(m, "Selectat cu succes!", "Error", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
            }
        }
    }

    /**
     * Butonul de creare a unui produs Composite. Folosesc lista de produse Base creata cu butonul de mai sus. Daca exista deja
     * un produs cu numele dat pe interfata, se va afisa o eroare.
     * Dupa ce am creat produsul composite, se vor sterge elementele din acea lista globala de produse base pentru a putea fi
     * refolosita.
     */
    class ButonCreate implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String nume = admin.getNume();
            System.out.println(nume);
            CompositeProduct produs = new CompositeProduct();

            if(produseComposite.size()==0){
                JOptionPane.showMessageDialog(m, "Alegeti produsele!", "Error", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            produs.addProducts(produseComposite, nume);
            int ok = service.createNewProduct(produs, service.getMenuItems());

            if(ok==1) {
                JOptionPane.showMessageDialog(m, "Produs adaugat cu succes", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                JOptionPane.showMessageDialog(m, "Exista deja un produs cu acest nume", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
            produseComposite.removeAll(produseComposite);
            ser.serialize(service);
        }
    }

    class ButonInapoiClient implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            client.setVisible(false);
            m.setVisible(true);
        }
    }

    class ButonViewProduct implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            int contor=1;
            viewPr.resetComboBox();
            for(MenuItem m:service.getMenuItems()){
                viewPr.setComboBox(contor+" "+m.printMenuItems());
                contor++;
            }
            viewPr.setVisible(true);
            client.setVisible(false);

        }
    }

    /**
     * Buton de adaugare a criteriilor pentru Search. Criteriile sunt initializate cu -1 pana cand se vor lua alte valori de pe
     * interfata grafica. Aceste criterii sunt declarate global si vor fi folosite in celalalt buton.
     */
    class ButonAddCriteriu implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            keyword=client.getKeyword();
            String criteriu=client.getCriteriiCautare();

            try {
                if (criteriu.equals("rating") == true) {
                    ratingDorit = Float.parseFloat(client.getValoare());
                }
                if(criteriu.equals("calories")==true) {
                    criterii[0] = Integer.parseInt(client.getValoare());
                }
                if(criteriu.equals("protein")==true) {
                    criterii[1] = Integer.parseInt(client.getValoare());
                }
                if(criteriu.equals("fat")==true) {
                    criterii[2] = Integer.parseInt(client.getValoare());
                }
                if(criteriu.equals("sodium")==true) {
                    criterii[3] = Integer.parseInt(client.getValoare());
                }
                if(criteriu.equals("price")==true) {
                    criterii[4] = Integer.parseInt(client.getValoare());
                }
                JOptionPane.showMessageDialog(m, "Criteriu adaugat cu succes", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
            catch (Exception ex){
                ex.printStackTrace();
                JOptionPane.showMessageDialog(m, "Datele nu au fost introduse corect", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    /**
     * Butonul de search in care afisez o lista filtrata a produselor in functie de anumite criterii selectate mai sus.
     */
    class ButonSearchProduct implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String Keyword;
            if(keyword.length()!=0) {
                char[] ch = keyword.toCharArray();
                ch[0] = (char) (ch[0] - 32);
                Keyword = new String(ch);
            }
            else{ Keyword=""; }
            List<MenuItem> itemsGasite=service.searchProduct(keyword,Keyword,ratingDorit,criterii,service.getMenuItems());

            if(itemsGasite.size()==0){
                JOptionPane.showMessageDialog(m, "Nu am gasit niciun produs care sa indeplineasca criteriile", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                viewPr.resetComboBox();
                int contor=1;
                for(MenuItem m:itemsGasite) {
                    viewPr.setComboBox(contor+" "+m.printMenuItems());
                    contor++;
                }
                viewPr.setVisible(true);
                client.setVisible(false);
            }
            //resetare criterii de cautare dupa o cautare
            ratingDorit=-1;
            for(int i=0;i<5;i++){
                criterii[i]=-1;
            }
            keyword="";
        }
    }

    /**
     * Butonul cu care adaug produse la o lista globala. Aceste produse (base sau composite) vor fi adaugate la un order
     * cu butonul de createOrder. Produsele sunt selectate dintr-un ComboBox.
     */
    class ButonAddProduct implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            String produs=client.getProduse();
            int indexVirgula=produs.indexOf(",");
            String numeProdus=produs.substring(0 , indexVirgula);
            for(MenuItem me:service.getMenuItems()){
                if(me.getTitle().equals(numeProdus)){
                    me.setNrComenzi(me.getNrComenzi()+1);
                    produseOrder.add(me);
                    JOptionPane.showMessageDialog(m, "Selectat cu succes!", "Error", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
            }

        }
    }

    /**
     * Butonul cu care iau produsele de mai sus si le adaug la un order. Folosesc metoda din DeliveryService de createOrder la care
     * ii trimit Lista de produse, HashMap-ul in care sa adauge orderul si clientul care a realizat orderul.
     */
    class ButonAddOrder implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            if(produseOrder.size()==0){
                JOptionPane.showMessageDialog(m, "Alegeti produsele!", "Error", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            ArrayList<MenuItem> iteme=new ArrayList<>(produseOrder);
            service.createOrder(iteme,service.getOrders(),clientLogat);
            JOptionPane.showMessageDialog(m, "Orderul "+service.getOrders().size()+" adaugat cu succes", "Error", JOptionPane.INFORMATION_MESSAGE);
            produseOrder.removeAll(produseOrder);

            ser.serialize(service);
        }
    }

    class ButonInapoiEmployee implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            employee.setVisible(false);
            m.setVisible(true);
            employee.resetArea();
        }
    }

    class ButonInapoiViewPr implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            viewPr.setVisible(false);
            client.setVisible(true);
            viewPr.resetComboBox();
            //client.resetComboBox();
        }
    }

    /**
     * Butonul cu care generez primul raport. Iau ora de inceput si de final de pe gui si apelez metoda de generareRaportTimp()
     * din DeliveryService.
     */
    class ButonRaport1 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int oraI = Integer.parseInt(admin.getOraI());
                int oraF = Integer.parseInt(admin.getOraF());

                int ok=service.generareRaportTimp(oraI,oraF,service.getOrders());
                service.getConturi().getAdList().get(0).generareRaportTimp(oraI,oraF,service.getOrders());
                if(ok==1)
                    JOptionPane.showMessageDialog(m, "Raportul a fost generat", "Error", JOptionPane.INFORMATION_MESSAGE);
                else{
                    JOptionPane.showMessageDialog(m, "Nu sunt date de inserat in raport", "Error", JOptionPane.INFORMATION_MESSAGE);
                }

            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(m, "Datele nu au fost introduse corect", "Error", JOptionPane.INFORMATION_MESSAGE);

            }
        }
    }

    /**
     * Butonul cu care generez raportul despre produsele comandate de cel putin n ori.
     */
    class ButonRaport2 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                int nrComenzi=Integer.parseInt(admin.getNrComenzi());
                int ok=service.generareRaportNrComenzi(nrComenzi,service.getOrders());
                if(ok==1)
                    JOptionPane.showMessageDialog(m, "Raportul a fost generat", "Error", JOptionPane.INFORMATION_MESSAGE);
                else{
                    JOptionPane.showMessageDialog(m, "Nu sunt date de inserat in raport", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
            }catch(Exception ex){
                JOptionPane.showMessageDialog(m, "Datele nu au fost introduse corect", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    /**
     * Butonul cu care generez raportul despre clientii care au comandat de cel putin n ori si orderurile au avut o valoare minima
     * data
     */
    class ButonRaport3 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                int nrComenzi=Integer.parseInt(admin.getNrComenzi1());
                int valoare=Integer.parseInt(admin.getValoare());
                service.generareRaportClienti(nrComenzi,valoare,service.getOrders());
                JOptionPane.showMessageDialog(m, "Raportul a fost generat", "Error", JOptionPane.INFORMATION_MESSAGE);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(m, "Datele nu au fost introduse corect", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    /**
     * Butonul cu care generez raportul despre produsele comandate intr-o anumita zi
     */
    class ButonRaport4 implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                int ziua=Integer.parseInt(admin.getZi());
                int luna=Integer.parseInt(admin.getLuna());
                service.generareRaportProduse(ziua,luna,service.getOrders());
                JOptionPane.showMessageDialog(m, "Raportul a fost generat", "Error", JOptionPane.INFORMATION_MESSAGE);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(m, "Datele nu au fost introduse corect", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
