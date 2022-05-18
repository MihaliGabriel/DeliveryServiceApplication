package BusinessLogic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Interfata folosita in DeliveryService.
 */
public interface DeliveryServiceProcessing {

    public void importProducts(String path);
    public Order createOrder(ArrayList<MenuItem> items, int clientID);
    public void addItem(MenuItem item);
    public void editItem(MenuItem item);
    public List<MenuItem> searchItems(HashSet<MenuItem> products, String title, int price, int calories, Double rating);
}
