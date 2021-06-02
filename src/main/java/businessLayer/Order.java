package businessLayer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @author Tarta Manuel
 * Clasa Order care reprezinta o comanda facuta de un client. Ca si campuri are un id, un id al clientului care a realizat
 * comanda, o data si un pret total.
 *
 */
public class Order implements java.io.Serializable{
    int orderId;
    int clientId;
    LocalDateTime orderDate;
    int pretTotal;

    public Order(){

    }

    public Order(int orderId, int clientId, LocalDateTime orderDate){
        this.orderId=orderId;
        this.clientId=clientId;
        this.orderDate=orderDate;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.orderId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public int getPretTotal() {
        return pretTotal;
    }

    public void setPretTotal(ArrayList<MenuItem> items) {
        for(MenuItem m:items){
            pretTotal=pretTotal+m.getPrice();
        }
    }
}
