package dataLayer;

import businessLayer.DeliveryService;

import java.io.*;

public class Serializator {

    /**
     *
     * @param e obiectul pe care il serializez
     * Pentru serializare, creez un fisier in care printez datele despre acel obiect, date pe care dupa o sa le deserializez
     * pentru a reconstrui obiectul.
     * Am serializat clasa DeliveryService pentru ca acolo am toate datele pe care doresc sa le salvez si sa le refolosesc. Am
     * userii, produsele si orderurile.
     */
    public void serialize(Object e) {
        try {
            FileOutputStream fileOut = new FileOutputStream("delivery.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(e);
            out.close();
            fileOut.close();
           // System.out.printf("Serialized data is saved in delivery.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

}
