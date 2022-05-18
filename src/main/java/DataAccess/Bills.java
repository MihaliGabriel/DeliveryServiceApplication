package DataAccess;

import BusinessLogic.MenuItem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Clasa care se ocupa de generarea bill-urilor
 * Pentru comenzi.
 */
public class Bills {
    FileWriter filewriter;

    public void generateOrderBill(int order_id, String client_username, String date, ArrayList<MenuItem> products, int totalPrice){
        File file = new File("Order" + order_id);
        try {
            filewriter = new FileWriter(file);
            filewriter.append("Order " + order_id + " placed by " + client_username + " at " + date + "." + "\n" + " Products ordered : \n");
            for(MenuItem m : products) {
                filewriter.append(m.getTitle() + " " + m.getPrice() + "\n");
            }
            filewriter.append(" Total price : " + totalPrice);
            filewriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
