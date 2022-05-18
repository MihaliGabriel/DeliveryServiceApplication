package DataAccess;

import BusinessLogic.MenuItem;
import BusinessLogic.Order;
import BusinessLogic.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Aceasta clasa genereaza rapoartele specifice
 * administratorului.
 */
public class Reports {
    public void generateHourReport(List<Order> orders, int hour1, int hour2) {
        File file = new File("Report hour");
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.append("Orders placed between " + hour1 + " " + hour2+ " : " + "\n");
            for(Order order : orders) {
                fileWriter.append("Order " + order.getOrderID() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void generateFrequencyProductReport(List<MenuItem> items, int frequency) {
        File file = new File("Report products");
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.append("Products ordered more than " + frequency + " " + " times: " + "\n");
            for(MenuItem item : items) {
                fileWriter.append(item.toString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generateDayProductReport(Map<MenuItem, Integer> products, int day) {
        File file = new File("Report products day");
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.append("Products ordered in day " + day +  ": " + "\n");
            for(MenuItem item : products.keySet()) {
                fileWriter.append(item.toString() + products.get(item) + " times " + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void generateUserOrderFrequencyReport(Map<User, Integer> users, int frequency, int minprice) {
        File file = new File("Report order frequency");
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.append("Users that ordered more than " + frequency + " times with a minimum order price of : " + minprice + " : " + "\n");
            for(User x : users.keySet()) {
                fileWriter.append(x.getUsername() + " ordered " + users.get(x) + " times " + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
