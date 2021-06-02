package presentationLayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Clasa care reprezinta interfata grafica pentru operatiile administratorului.
 * Am un buton de import, 7 textFields pentru add, 7 textFields pentru edit, un textField in care dau numele produsului
 * pe care vreau sa il sterg si tot asa pentru toate operatiile
 */
public class AdministratorGUI extends JFrame {

    private JLabel l1 = new JLabel("title");
    private JTextField title = new JTextField(20);

    private JLabel l2 = new JLabel("rating");
    private JTextField rating = new JTextField(5);

    private JLabel l3 = new JLabel("calories");
    private JTextField calories = new JTextField(5);

    private JLabel l4 = new JLabel("protein");
    private JTextField protein = new JTextField(5);

    private JLabel l5 = new JLabel("fat");
    private JTextField fat = new JTextField(5);

    private JLabel l6 = new JLabel("sodium");
    private JTextField sodium = new JTextField(5);

    private JLabel l7 = new JLabel("price");
    private JTextField price = new JTextField(5);


    //pentru edit
    private JLabel l11 = new JLabel("title");
    private JTextField title1 = new JTextField(20);

    private JLabel l21 = new JLabel("rating");
    private JTextField rating1 = new JTextField(5);

    private JLabel l31 = new JLabel("calories");
    private JTextField calories1 = new JTextField(5);

    private JLabel l41 = new JLabel("protein");
    private JTextField protein1 = new JTextField(5);

    private JLabel l51 = new JLabel("fat");
    private JTextField fat1 = new JTextField(5);

    private JLabel l61 = new JLabel("sodium");
    private JTextField sodium1 = new JTextField(5);

    private JLabel l71 = new JLabel("price");
    private JTextField price1 = new JTextField(5);

    //pentru delete
    private JLabel l12 = new JLabel("title");
    private JTextField title2 = new JTextField(25);


    private JLabel lr1 = new JLabel("Raportul in functie de time interval:");
    private JLabel lr2 = new JLabel("Raportul in functie de numarul de comenzi:");
    private JLabel lr3 = new JLabel("Clientii care au comandat de n ori:");
    private JLabel lr4 = new JLabel("Produsele comandate in ziua anume:");

    private JLabel oraI = new JLabel("OraI");
    private JTextField oraInceput = new JTextField(5);
    private JLabel oraF = new JLabel("OraF");
    private JTextField oraFinal = new JTextField(5);

    private JLabel nrComenzi = new JLabel("Numar minim comenzi");
    private JTextField Comenzi = new JTextField(5);

    private JLabel nrComenzi1 = new JLabel("Numar minim comenzi");
    private JTextField Comenzi1 = new JTextField(5);

    private JLabel value1 = new JLabel("Valoare");
    private JTextField Value1 = new JTextField(5);

    private JLabel ziua = new JLabel("Ziua");
    private JTextField zi = new JTextField(5);

    private JLabel luna = new JLabel("Luna");
    private JTextField Luna = new JTextField(5);



    private JLabel nume = new JLabel("Nume");
    private JTextField Nume = new JTextField(15);

    JComboBox produse = new JComboBox();


    private JButton butonImport = new JButton("Import products");
    private JButton butonAdd = new JButton("Add product");
    private JButton butonDelete = new JButton("Delete product");
    private JButton butonEdit = new JButton("Edit product");
    private JButton butonAddProduct = new JButton("Add");
    private JButton butonCreate = new JButton("Create menuItem");
    private JButton butonInapoi = new JButton("Inapoi");
    private JButton butonRaport1 = new JButton("Generate");
    private JButton butonRaport2 = new JButton("Generate");
    private JButton butonRaport3 = new JButton("Generate");
    private JButton butonRaport4 = new JButton("Generate");

    public AdministratorGUI() {
        JPanel c = new JPanel();
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
        c.setPreferredSize(new Dimension(1200, 450));
        c.setBackground(Color.blue);

        l1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        l2.setFont(new Font("Times New Roman", Font.BOLD, 20));
        l3.setFont(new Font("Times New Roman", Font.BOLD, 20));
        l4.setFont(new Font("Times New Roman", Font.BOLD, 20));
        l5.setFont(new Font("Times New Roman", Font.BOLD, 20));
        l6.setFont(new Font("Times New Roman", Font.BOLD, 20));
        l7.setFont(new Font("Times New Roman", Font.BOLD, 20));

        //pentru edit
        l11.setFont(new Font("Times New Roman", Font.BOLD, 20));
        l21.setFont(new Font("Times New Roman", Font.BOLD, 20));
        l31.setFont(new Font("Times New Roman", Font.BOLD, 20));
        l41.setFont(new Font("Times New Roman", Font.BOLD, 20));
        l51.setFont(new Font("Times New Roman", Font.BOLD, 20));
        l61.setFont(new Font("Times New Roman", Font.BOLD, 20));
        l71.setFont(new Font("Times New Roman", Font.BOLD, 20));

        lr1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lr2.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lr3.setFont(new Font("Times New Roman", Font.BOLD, 20));
        lr4.setFont(new Font("Times New Roman", Font.BOLD, 20));
        oraI.setFont(new Font("Times New Roman", Font.BOLD, 20));
        oraF.setFont(new Font("Times New Roman", Font.BOLD, 20));
        nrComenzi.setFont(new Font("Times New Roman", Font.BOLD, 20));
        nrComenzi1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        value1.setFont(new Font("Times New Roman", Font.BOLD, 20));
      //  ziua.setFont(new Font("Times New Roman", Font.BOLD, 20));

        l12.setFont(new Font("Times New Roman", Font.BOLD, 20));
        zi.setFont(new Font("Times New Roman", Font.BOLD, 20));
        Luna.setFont(new Font("Times New Roman", Font.BOLD, 20));
        ziua.setFont(new Font("Times New Roman", Font.BOLD, 20));
        luna.setFont(new Font("Times New Roman", Font.BOLD, 20));
        nume.setFont(new Font("Times New Roman", Font.BOLD, 20));

        butonAdd.setPreferredSize(new Dimension(130, 30));
        butonDelete.setPreferredSize(new Dimension(130, 30));
        butonImport.setPreferredSize(new Dimension(130, 30));
        butonEdit.setPreferredSize(new Dimension(130, 30));
        butonRaport1.setPreferredSize(new Dimension(130, 30));
        butonRaport2.setPreferredSize(new Dimension(130, 30));
        butonRaport3.setPreferredSize(new Dimension(130, 30));
        butonRaport4.setPreferredSize(new Dimension(130, 30));
        butonCreate.setPreferredSize(new Dimension(150, 30));
        butonAddProduct.setPreferredSize(new Dimension(130, 30));
        produse.setPreferredSize(new Dimension(500, 30));


        JPanel linia1 = new JPanel();
        linia1.setBackground(Color.blue);
        linia1.setLayout(new FlowLayout());
        linia1.add(butonImport);

        JPanel linia2 = new JPanel();
        linia2.setBackground(Color.blue);
        linia2.setLayout(new FlowLayout());
        linia2.add(l1);
        linia2.add(title);
        linia2.add(Box.createRigidArea(new Dimension(10, 0)));
        linia2.add(l2);
        linia2.add(rating);
        linia2.add(Box.createRigidArea(new Dimension(10, 0)));
        linia2.add(l3);
        linia2.add(calories);
        linia2.add(Box.createRigidArea(new Dimension(10, 0)));
        linia2.add(l4);
        linia2.add(protein);
        linia2.add(Box.createRigidArea(new Dimension(10, 0)));
        linia2.add(l5);
        linia2.add(fat);
        linia2.add(Box.createRigidArea(new Dimension(10, 0)));
        linia2.add(l6);
        linia2.add(sodium);
        linia2.add(Box.createRigidArea(new Dimension(10, 0)));
        linia2.add(l7);
        linia2.add(price);
        linia2.add(Box.createRigidArea(new Dimension(10, 0)));
        linia2.add(butonAdd);

        JPanel linia3 = new JPanel();
        linia3.setBackground(Color.blue);
        linia3.setLayout(new FlowLayout());
        linia3.add(l11);
        linia3.add(title1);
        linia3.add(Box.createRigidArea(new Dimension(10, 0)));
        linia3.add(l21);
        linia3.add(rating1);
        linia3.add(Box.createRigidArea(new Dimension(10, 0)));
        linia3.add(l31);
        linia3.add(calories1);
        linia3.add(Box.createRigidArea(new Dimension(10, 0)));
        linia3.add(l41);
        linia3.add(protein1);
        linia3.add(Box.createRigidArea(new Dimension(10, 0)));
        linia3.add(l51);
        linia3.add(fat1);
        linia3.add(Box.createRigidArea(new Dimension(10, 0)));
        linia3.add(l61);
        linia3.add(sodium1);
        linia3.add(Box.createRigidArea(new Dimension(10, 0)));
        linia3.add(l71);
        linia3.add(price1);
        linia3.add(Box.createRigidArea(new Dimension(10, 0)));
        linia3.add(butonEdit);

        JPanel linia4 = new JPanel();
        linia4.setBackground(Color.blue);
        linia4.setLayout(new FlowLayout());
        linia4.add(l12);
        linia3.add(Box.createRigidArea(new Dimension(10, 0)));
        linia4.add(title2);
        linia4.add(Box.createRigidArea(new Dimension(10, 0)));
        linia4.add(butonDelete);

        JPanel linia = new JPanel();
        linia.setBackground(Color.blue);
        linia.setLayout(new FlowLayout());
        linia.add(nume);
        linia.add(Nume);
        linia.add(Box.createRigidArea(new Dimension(10, 0)));
        linia.add(produse);
        linia.add(Box.createRigidArea(new Dimension(10, 0)));
        linia.add(butonAddProduct);
        linia.add(Box.createRigidArea(new Dimension(10, 0)));
        linia.add(butonCreate);

        JPanel linia5 = new JPanel();
        linia5.setBackground(Color.blue);
        linia5.setLayout(new FlowLayout());
        linia5.add(lr1);
        linia5.add(Box.createRigidArea(new Dimension(20, 0)));
        linia5.add(oraI);
        linia5.add(oraInceput);
        linia5.add(oraF);
        linia5.add(oraFinal);
        linia5.add(butonRaport1);

        JPanel linia6 = new JPanel();
        linia6.setBackground(Color.blue);
        linia6.setLayout(new FlowLayout());
        linia6.add(lr2);
        linia6.add(Box.createRigidArea(new Dimension(20, 0)));
        linia6.add(nrComenzi);
        linia6.add(Comenzi);
        linia6.add(butonRaport2);

        JPanel linia7 = new JPanel();
        linia7.setBackground(Color.blue);
        linia7.setLayout(new FlowLayout());
        linia7.add(lr3);
        linia7.add(Box.createRigidArea(new Dimension(20, 0)));
        linia7.add(nrComenzi1);
        linia7.add(Comenzi1);
        linia7.add(value1);
        linia7.add(Value1);
        linia7.add(butonRaport3);

        JPanel linia8 = new JPanel();
        linia8.setBackground(Color.blue);
        linia8.setLayout(new FlowLayout());
        linia8.add(lr4);
        linia8.add(Box.createRigidArea(new Dimension(20, 0)));
        linia8.add(ziua);
        linia8.add(zi);
        linia8.add(Box.createRigidArea(new Dimension(20, 0)));
        linia8.add(luna);
        linia8.add(Luna);
        linia8.add(Box.createRigidArea(new Dimension(20, 0)));
        linia8.add(butonRaport4);

        JPanel linia9 = new JPanel();
        linia9.setBackground(Color.blue);
        linia9.setLayout(new FlowLayout());
        linia9.add(butonInapoi);

        c.add(linia1);
        c.add(linia2);
        c.add(linia3);
        c.add(linia4);
        c.add(linia);
        c.add(linia5);
        c.add(linia6);
        c.add(linia7);
        c.add(linia8);
        c.add(linia9);

        this.setContentPane(c);
        this.pack();

        this.setTitle("Admin Operations");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public String getTitleAdd() {
        return title.getText();
    }

    public String getRatingAdd() {
        return rating.getText();
    }

    public String getCaloriesAdd() {
        return calories.getText();
    }

    public String getProteinAdd() {
        return protein.getText();
    }

    public String getFatAdd() {
        return fat.getText();
    }

    public String getSodiumAdd() {
        return sodium.getText();
    }

    public String getPriceAdd() {
        return price.getText();
    }

    public String getTitleEdit() {
        return title1.getText();
    }

    public String getRatingEdit() {
        return rating1.getText();
    }

    public String getCaloriesEdit() {
        return calories1.getText();
    }

    public String getProteinEdit() {
        return protein1.getText();
    }

    public String getFatEdit() {
        return fat1.getText();
    }

    public String getSodiumEdit() {
        return sodium1.getText();
    }

    public String getPriceEdit() {
        return price1.getText();
    }

    public String getTitleDelete() {
        return title2.getText();
    }


    public String getOraI() {
        return oraInceput.getText();
    }

    public String getOraF() {
        return oraFinal.getText();
    }

    public String getNrComenzi(){
        return Comenzi.getText();
    }

    public String getNrComenzi1(){
        return Comenzi1.getText();
    }

    public String getZi(){
        return zi.getText();
    }

    public String getLuna(){
        return Luna.getText();
    }

    public String getProduse(){
        return String.valueOf(produse.getSelectedItem());
    }

    public void setComboBox(String s){
        produse.addItem(s);
    }

    public void resetComboBox(){
        produse.removeAllItems();
    }

    public String getNume(){return Nume.getText();}

    public String getValoare()
    {
        return String.valueOf(Value1.getText());

    }



    public void butonImportListener(ActionListener bImport) {
        butonImport.addActionListener(bImport);
    }

    public void butonAddListener(ActionListener bAdd) {
        butonAdd.addActionListener(bAdd);
    }

    public void butonEditListener(ActionListener bEdit) {
        butonEdit.addActionListener(bEdit);
    }

    public void butonDeleteListener(ActionListener bDelete) {
        butonDelete.addActionListener(bDelete);
    }

    public void butonInapoiListener(ActionListener bInapoi) {
        butonInapoi.addActionListener(bInapoi);
    }

    public void butonAddProductListener(ActionListener bAddP) {
        butonAddProduct.addActionListener(bAddP);
    }

    public void butonCreateListener(ActionListener bCreate) {
        butonCreate.addActionListener(bCreate);
    }

    public void butonRaport1Listener(ActionListener bRaport1) {
        butonRaport1.addActionListener(bRaport1);
    }

    public void butonRaport2Listener(ActionListener bRaport2) {
        butonRaport2.addActionListener(bRaport2);
    }

    public void butonRaport3Listener(ActionListener bRaport3) {
        butonRaport3.addActionListener(bRaport3);
    }

    public void butonRaport4Listener(ActionListener bRaport4) {
        butonRaport4.addActionListener(bRaport4);
    }

}
