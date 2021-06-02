
import businessLayer.DeliveryService;
import dataLayer.Serializator;
import presentationLayer.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Main {
    public static void main(String[] args){

        Serializator ser=new Serializator();
        MainPage mainPage=new MainPage();
        mainPage.setVisible(true);

        AdministratorGUI admin=new AdministratorGUI();

        ClientGUI client=new ClientGUI();
        EmployeeGUI employee=new EmployeeGUI();
        ViewProducts viewPr=new ViewProducts();
        DeliveryService service=new DeliveryService();

        //service.addObserver(employee);

        try {
            FileInputStream fileIn = new FileInputStream("delivery.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            service=(DeliveryService) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Delivery class not found");
            c.printStackTrace();
        }


        Controller c=new Controller(mainPage,admin,service,client,employee,viewPr);

    }
}
