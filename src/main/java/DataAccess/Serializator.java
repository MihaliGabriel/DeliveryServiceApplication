package DataAccess;

import BusinessLogic.MenuItem;
import BusinessLogic.Order;
import com.opencsv.CSVWriter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Serializator {
    AppendableObjectOutputStream output2;
    FileOutputStream fileoutput2;
    AppendableObjectOutputStream output3;
    FileOutputStream fileoutput3;
    boolean append;

    public Serializator() {
        try {
            File file = new File("products.txt");
            append = file.exists();
            fileoutput2 = new FileOutputStream("products.txt", append);
            output2 = new AppendableObjectOutputStream(fileoutput2, append);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> processCSV(String path) throws IOException {
        ArrayList<String> entries = new ArrayList<String>();
        Files.lines(Paths.get(path))
                .skip(0)
                .skip(1)
                .forEach(entries::add);
        return entries;
    }
    public void serializeProducts(HashSet<MenuItem> products){
        try {
            fileoutput2 = new FileOutputStream("products.txt", false);
            output2 = new AppendableObjectOutputStream(fileoutput2, false);
            for(MenuItem item : products) {
                output2.writeObject(item);
                output2.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void serializeOrders(HashMap<Order, ArrayList<MenuItem>> orders) {
        try {
            fileoutput3 = new FileOutputStream("orders.txt", false);
            output3 = new AppendableObjectOutputStream(fileoutput3, false);
            output3.writeObject(orders);
            output3.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addProduct(MenuItem item) {
        try {
            output2.writeObject(item);
            output2.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
