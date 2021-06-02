package presentationLayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ViewProducts extends JFrame {
    private JLabel l1 = new JLabel("Vizualizare Produse");
    JComboBox products = new JComboBox();

    private JButton butonInapoi = new JButton("Inapoi");

    public ViewProducts(){
        JPanel c = new JPanel();
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
        c.setPreferredSize(new Dimension(900, 450));
        c.setBackground(Color.blue);

        l1.setFont(new Font("Times New Roman", Font.BOLD, 20));

        butonInapoi.setPreferredSize(new Dimension(100, 30));
        products.setPreferredSize(new Dimension(850, 30));

        JPanel linia1 = new JPanel();
        linia1.setBackground(Color.blue);
        linia1.setLayout(new FlowLayout());
        linia1.add(l1);

        JPanel linia2 = new JPanel();
        linia2.setBackground(Color.blue);
        linia2.setLayout(new FlowLayout());
        linia2.add(products);

        JPanel linia3 = new JPanel();
        linia3.setBackground(Color.blue);
        linia3.setLayout(new FlowLayout());
        linia3.add(butonInapoi);

        c.add(linia1);
        c.add(linia2);
        c.add(linia3);

        this.setContentPane(c);
        this.pack();

        this.setTitle("Main Page");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public void setComboBox(String s){
        products.addItem(s);
    }

    public void resetComboBox(){
      //  int index=products.getItemCount();
      //  System.out.println(index);
      //  for(int i=0;i<index;i++){
      //      products.removeItemAt(0);
      //  }
        products.removeAllItems();
    }

    public void butonInapoiListener(ActionListener bInapoi) {
        butonInapoi.addActionListener(bInapoi);
    }
}
