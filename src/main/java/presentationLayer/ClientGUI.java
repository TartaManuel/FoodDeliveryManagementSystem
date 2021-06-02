package presentationLayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @author Tarta Manuel
 * Clasa care reprezinta interfata grafica pentru operatiile clientului.
 */
public class ClientGUI extends JFrame {
    private JLabel l1 = new JLabel("View All Products");
    private JButton butonView = new JButton("View");

    private JLabel l2 = new JLabel("Criterii cautare:");
    private JLabel l21 = new JLabel("Keyword");
    private JTextField keyword = new JTextField(10);
    String[] criterii = {"","rating", "calories","protein","fat","sodium","price"};
    JComboBox criteriiCautare = new JComboBox(criterii);
    private JTextField valoare = new JTextField(10);
    private JButton butonAdd = new JButton("Add criteriu");
    private JButton butonSearch = new JButton("Search");

    JComboBox produse = new JComboBox();
    private JButton butonAddProduct = new JButton("Add product");
    private JButton butonAddOrder = new JButton("Add Order");

    private JButton butonInapoi = new JButton("Inapoi");

    public ClientGUI() {
        JPanel c = new JPanel();
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
        c.setPreferredSize(new Dimension(1200, 450));
        c.setBackground(Color.blue);

        l1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        l2.setFont(new Font("Times New Roman", Font.BOLD, 20));
        l21.setFont(new Font("Times New Roman", Font.BOLD, 20));
        keyword.setFont(new Font("Times New Roman", Font.BOLD, 20));
        valoare.setFont(new Font("Times New Roman", Font.BOLD, 20));

        butonAdd.setPreferredSize(new Dimension(130, 30));
        butonSearch.setPreferredSize(new Dimension(130, 30));
        butonView.setPreferredSize(new Dimension(130, 30));
        butonAddProduct.setPreferredSize(new Dimension(130, 30));
        butonAddOrder.setPreferredSize(new Dimension(130, 30));

        produse.setPreferredSize(new Dimension(850,30));


        JPanel linia1 = new JPanel();
        linia1.setBackground(Color.blue);
        linia1.setLayout(new FlowLayout());
        linia1.add(l1);
        linia1.add(butonView);

        JPanel linia2 = new JPanel();
        linia2.setBackground(Color.blue);
        linia2.setLayout(new FlowLayout());
        linia2.add(l2);
        linia2.add(Box.createRigidArea(new Dimension(10, 0)));
        linia2.add(l21);
        linia2.add(Box.createRigidArea(new Dimension(10, 0)));
        linia2.add(keyword);
        linia2.add(Box.createRigidArea(new Dimension(10, 0)));
        linia2.add(criteriiCautare);
        linia2.add(Box.createRigidArea(new Dimension(10, 0)));
        linia2.add(valoare);
        linia2.add(Box.createRigidArea(new Dimension(10, 0)));
        linia2.add(butonAdd);
        linia2.add(Box.createRigidArea(new Dimension(10, 0)));
        linia2.add(butonSearch);

        JPanel linia3 = new JPanel();
        linia3.setBackground(Color.blue);
        linia3.setLayout(new FlowLayout());
        linia3.add(Box.createRigidArea(new Dimension(10, 0)));
        linia3.add(produse);
        linia3.add(Box.createRigidArea(new Dimension(10, 0)));
        linia3.add(butonAddProduct);
        linia3.add(Box.createRigidArea(new Dimension(10, 0)));
        linia3.add(butonAddOrder);

        JPanel linia4 = new JPanel();
        linia4.setBackground(Color.blue);
        linia4.setLayout(new FlowLayout());
        linia4.add(butonInapoi);

        c.add(linia1);
        c.add(linia2);
        c.add(linia3);
        c.add(linia4);

        this.setContentPane(c);
        this.pack();

        this.setTitle("Client Operations");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public void setComboBox(String s){
        produse.addItem(s);
    }

    public void resetComboBox(){
        produse.removeAllItems();
    }

    public String getKeyword() {
        return keyword.getText();
    }

    public String getCriteriiCautare() {
        return String.valueOf(criteriiCautare.getSelectedItem());
        //criteriiCautare.setSelectedItem(Luna.getItemAt(0));
    }


    public String getProduse() {
        return (String)produse.getSelectedItem();
    }

    public int getIndexProdus(){
        return produse.getSelectedIndex();
    }

    public String getValoare(){
        return valoare.getText();
    }

    public void butonAddCriteriuListener(ActionListener bAddCriteriu) {
        butonAdd.addActionListener(bAddCriteriu);
    }

    public void butonSearchProductListener(ActionListener bSearchProduct) {
        butonSearch.addActionListener(bSearchProduct);
    }

    public void butonViewProductsListener(ActionListener bViewProducts) {
        butonView.addActionListener(bViewProducts);
    }

    public void butonAddProductListener(ActionListener bAddProduct) {
        butonAddProduct.addActionListener(bAddProduct);
    }
    public void butonAddOrderListener(ActionListener bAddOrder) {
        butonAddOrder.addActionListener(bAddOrder);
    }

    public void butonInapoiListener(ActionListener bInapoi) {
        butonInapoi.addActionListener(bInapoi);
    }
}
