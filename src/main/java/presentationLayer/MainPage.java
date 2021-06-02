package presentationLayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Clasa pentru pagina de logare si inregistrare.
 */
public class MainPage extends JFrame {
    private JLabel l1 = new JLabel("Username");
    private JTextField username = new JTextField(25);

    private JLabel l2 = new JLabel("Parola");
    private JTextField parola = new JTextField(25);

    private JLabel l3 = new JLabel("Email");
    private JTextField email = new JTextField(25);

    private JLabel l4 = new JLabel("Functie cont nou");
    String[] userTypes = {"Admin", "Employee", "Client"};
    JComboBox user = new JComboBox(userTypes);


    private JButton butonRegister = new JButton("Register");
    private JButton butonLogin = new JButton("Login");

    public MainPage() {
        JPanel c = new JPanel();
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
        c.setPreferredSize(new Dimension(800, 450));
        c.setBackground(Color.blue);

        l1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        l2.setFont(new Font("Times New Roman", Font.BOLD, 20));
        l3.setFont(new Font("Times New Roman", Font.BOLD, 20));
        l4.setFont(new Font("Times New Roman", Font.BOLD, 20));

        butonRegister.setPreferredSize(new Dimension(100, 30));
        butonLogin.setPreferredSize(new Dimension(100, 30));

        JPanel linia1 = new JPanel();
        linia1.setBackground(Color.blue);
        linia1.setLayout(new FlowLayout());
        linia1.add(l3);
        linia1.add(email);

        JPanel linia2 = new JPanel();
        linia2.setBackground(Color.blue);
        linia2.setLayout(new FlowLayout());
        linia2.add(l1);
        linia2.add(username);

        JPanel linia3 = new JPanel();
        linia3.setBackground(Color.blue);
        linia3.setLayout(new FlowLayout());
        linia3.add(l2);
        linia3.add(parola);

        JPanel linia4 = new JPanel();
        linia4.setBackground(Color.blue);
        linia4.setLayout(new FlowLayout());
        linia4.add(l4);
        linia4.add(user);

        JPanel linia5 = new JPanel();
        linia5.setBackground(Color.blue);
        linia5.add(butonRegister);
        linia5.add(butonLogin);


        c.add(linia1);
        c.add(linia2);
        c.add(linia3);
        c.add(linia4);
        c.add(linia5);

        this.setContentPane(c);
        this.pack();

        this.setTitle("Main Page");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //Action Listeneri pentru toate butoanele
    public void butonRegisterListener(ActionListener bRegister) {
        butonRegister.addActionListener(bRegister);
    }

    public void butonLoginListener(ActionListener bLogin) {
        butonLogin.addActionListener(bLogin);
    }

    //setter si getteri din TextField-uri
    public String getUsername() {
        return username.getText();

    }

    public String getParola() {
        return parola.getText();

    }

    public String getEmail() {
        return email.getText();
    }

    public String geteFunctie() {
        return (String)user.getSelectedItem();

    }

}

