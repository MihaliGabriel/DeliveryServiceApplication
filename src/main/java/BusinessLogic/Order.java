package BusinessLogic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Contine comenzile efectuate de catre clientii aplicatiei.
 * Este serializabila.
 * Face override la hashCode() deoarece va fi inclusa intr-un hashMap.
 */

public class Order implements Serializable {

    private static final long serialversionUID =
            239356938L;
    int orderID;
    int clientID;
    Date orderdate;
    public Order() {

    }
    public Order(ArrayList<MenuItem> items, int clientID, Date orderdate) {
        this.clientID = clientID;
        this.orderdate = orderdate;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderID, clientID);
    }
}
