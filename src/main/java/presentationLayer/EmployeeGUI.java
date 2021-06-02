package presentationLayer;

import businessLayer.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Clasa pentru Employee. Contine un textArea in care scriu informatii despre orderurile active. Dupa ce am intrat cu un
 * client, am citit datele din textArea si am apasat butonul de inapoi, resetez textArea-ul pentru a putea insera noile date.
 */
public class EmployeeGUI extends JFrame implements Observer {
    private JLabel l1 = new JLabel("Comenzile active:");
    private JTextArea comenzi = new JTextArea();

    private JButton butonInapoi = new JButton("Inapoi");

    public EmployeeGUI(){
        JPanel c = new JPanel();
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
        c.setPreferredSize(new Dimension(1300, 650));
        c.setBackground(Color.blue);

        l1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        comenzi.setFont(new Font("Times New Roman", Font.BOLD, 20));
        comenzi.setEditable(false);
        comenzi.setPreferredSize(new Dimension(1300, 550));

        butonInapoi.setPreferredSize(new Dimension(130, 30));

        JPanel linia1 = new JPanel();
        linia1.setBackground(Color.blue);
        linia1.setLayout(new FlowLayout());
        linia1.add(l1);
        //linia1.add(comenzi);

        JPanel linia2 = new JPanel();
        linia2.setBackground(Color.blue);
        linia2.setLayout(new FlowLayout());
        linia2.add(butonInapoi);

        JPanel linia3 = new JPanel();
        linia3.setBackground(Color.blue);
        linia3.setLayout(new FlowLayout());
        linia3.add(comenzi);

        c.add(linia1);
        c.add(linia3);
        c.add(linia2);

        this.setContentPane(c);
        this.pack();

        this.setTitle("Client Operations");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void butonInapoiListener(ActionListener bInapoi) {
        butonInapoi.addActionListener(bInapoi);
    }

    @Override
    public void update(Observable o, Object arg) {
        //System.out.println("Update");
        ArrayList<MenuItem> produse=(ArrayList<MenuItem>) arg;
        comenzi.append("Order:\n");
        for(MenuItem m:produse){
            comenzi.append(m.printMenuItems()+"\n");
        }
        comenzi.append("\n");
        //System.out.println((String)arg);
    }

    public void resetArea(){
        comenzi.setText("");
    }
}
