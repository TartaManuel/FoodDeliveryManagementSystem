package dataLayer;

import businessLayer.MenuItem;
import businessLayer.Order;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * @author Tarta Manuel
 * Clasa in care am metoda cu care printez informatiile despre un order intr-un bill in format txt.
 */
public class FileWriter {

    public FileWriter(){

    }

    /**
     *
     * @param o reprezinta orderul pentru care printez chitanta
     * @param items produsele din acest order
     * @param c clientul care a facut comanda
     */
    public void printBill(Order o, ArrayList<MenuItem> items, Client c){
        java.io.FileWriter fileWriter = null;
        try {
            fileWriter = new java.io.FileWriter("Order"+o.getOrderId()+".txt");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println("Orderul numarul "+o.getOrderId()+" comandat in data de "+o.getOrderDate().getDayOfMonth()
                +"/"+o.getOrderDate().getMonthValue()+"/"+o.getOrderDate().getYear()+"\n");
        printWriter.println("Date despre client: Id "+c.getId()+", Email "+c.getEmail());
        printWriter.println(("Produsele comandate sunt:"));
        for(MenuItem m:items){
            printWriter.println(m.printMenuItems());
        }
        printWriter.println("\nPret total: "+o.getPretTotal());

        printWriter.close();
    }
}
