package DataAccess;

import BusinessLogic.MenuItem;
import BusinessLogic.Order;
import BusinessLogic.User;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Clasa care deserializeaza fisierele aplicatiei.
 * Se oloseste de ObjectInputStream si FileInputStream
 */
public class Deserializator {

    public HashSet<MenuItem> deserializeProducts(String filename) {
        File file2 = new File("products.txt");
        ObjectInputStream input2;
        FileInputStream fileinput2;

        ArrayList<MenuItem> items = new ArrayList<MenuItem>();
       HashSet<MenuItem> wantedItems = null;
        boolean objects = true;
        try {
            fileinput2 = new FileInputStream("products.txt");
            input2 = new ObjectInputStream(fileinput2);
            while(objects && fileinput2.available() > 0) {
                MenuItem obj = (MenuItem)input2.readObject();
                if(obj != null) {
                    items.add(obj);
                }
                else {
                    objects = false;
                }
            }
            wantedItems = new HashSet<MenuItem>(items);
        }
        catch (IOException | ClassNotFoundException ex) {
        ex.printStackTrace();
        }
        return wantedItems;
}
    public HashMap<Order, ArrayList<MenuItem>> deserializeOrders() {
        ObjectInputStream input3;
        FileInputStream fileinput3;
        HashMap<Order, ArrayList<MenuItem>> orders = new HashMap<Order, ArrayList<MenuItem>>();
        try {
            File file = new File("orders.txt");
            boolean append = file.exists();
            if(append == true) {
                fileinput3 = new FileInputStream("orders.txt");
                input3 = new ObjectInputStream(fileinput3);
                orders = (HashMap<Order, ArrayList<MenuItem>>)input3.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return orders;
    }
    public ArrayList<User> deserializeUsers() {
        ObjectInputStream input4;
        FileInputStream fileinput4;
        ArrayList<User> users = new ArrayList<User>();
        boolean objects = true;
        try {
            fileinput4 = new FileInputStream("userspasswords.txt");
            input4 = new ObjectInputStream(fileinput4);
            while(objects && fileinput4.available() > 0) {
                User obj = (User)input4.readObject();
                if(obj != null) {
                    users.add(obj);
                }
                else {
                    objects = false;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return users;

    }

}
