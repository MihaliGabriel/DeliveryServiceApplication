package Controller;

import BusinessLogic.MenuItem;
import Presentation.*;
import BusinessLogic.*;
import DataAccess.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.lang.reflect.ParameterizedType;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * Din clasa Controller se porneste aplicatia.
 * Acesta are ca si campuri clasele din pachetul DataAccess
 * Interfetele si clasele din BusinessLogic.
 * Clasa Controller genereaza de asemenea bill-urile si rapoartele.
 */
public class Controller implements Observer {

    AdminGUI admin;
    ClientGUI client;
    EmployeeGUI employee;
    LoginGUI login;
    RegisterGUI register;
    ImportProductGUI importproduct;
    AddProductGUI addproduct;
    ViewProductGUI viewproduct;
    ModifyProductGUI modifyproduct;
    ReportsGUI reports;
    Reports report;

    FileOutputStream fout;
    AppendableObjectOutputStream out;
    Bills bills;



    ObjectInputStream input;
    FileInputStream file;
    User currentUser;
    ArrayList<String> items = new ArrayList<String>();
    Serializator serializator;
    Deserializator deserializator;
    DeliveryService deliveryService;
    CreateProductGUI createproduct;
    ArrayList<String> selectedItems;
    ArrayList<String> ordersEmployee;
    int itemindex;
    int user_id;

    public Controller() {
        currentUser = new User();
        report = new Reports();
        ordersEmployee = new ArrayList<String>();
        bills = new Bills();
        selectedItems = new ArrayList<String>();
        itemindex = 0;
        user_id = 0;
        login = new LoginGUI();
        deserializator = new Deserializator();
        serializator = new Serializator();
        deliveryService = new DeliveryService(serializator);
        login.addLoginListener(new LoginListener());
        login.addRegisterListener(new RegisterListener());
        deliveryService.setOrders(deserializator.deserializeOrders());
        deliveryService.setUsers(deserializator.deserializeUsers());
        try {
            File file = new File("userspasswords.txt");

            boolean append = file.exists();
            fout = new FileOutputStream("userspasswords.txt", append);
            out = new AppendableObjectOutputStream(fout, append);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void update(Observable o, Object arg) {
        HashMap<Order, ArrayList<java.awt.MenuItem>> orders = new HashMap<Order, ArrayList<java.awt.MenuItem>>();
        orders = (HashMap<Order, ArrayList<java.awt.MenuItem>>)arg;
        for(Order x : orders.keySet()) {
            String toadd = "Order " + x.getOrderID() + " placed " + " at " + x.getOrderdate() + "." + "\n";
            System.out.println("Order " + x.getOrderID() + " placed " + " at " + x.getOrderdate() + "." + "\n");
            ordersEmployee.add(toadd);
        }
    }
    public class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean objects = true;
            boolean found = true;
            currentUser = new User(login.getUsernametext(), login.getPasswordtext());
            deliveryService.setUsers(deserializator.deserializeUsers());
            for(User x : deliveryService.getUsers()) {
                System.out.println(x.getUser_id() + " " + x.getUsername());
                if(x.getUsername().equals(login.getUsernametext()) && x.getPassword().equals(login.getPasswordtext()))
                    currentUser.setUser_id(x.getUser_id());
            }
            try {
                file = new FileInputStream("userspasswords.txt");
                input = new ObjectInputStream(file);
                while(objects && file.available() > 0) {
                    User obj = (User)input.readObject();
                    if(obj != null) {
                        if(obj.getUsername().equals(currentUser.getUsername()) && obj.getPassword().equals(currentUser.getPassword())) {
                            currentUser.setUser_type(obj.getUser_type());
                            found = false;
                        }
                    }
                    else {
                        objects = false;
                    }
                }
                input.close();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        if(found) {
            login.showError();
        }
        else {
            if(currentUser.getUser_type().equals("Administrator")) {
                admin = new AdminGUI();
                HashSet<MenuItem> items = deserializator.deserializeProducts("products.txt");
                for(MenuItem m : items) {
                    String toadd = m.toString();
                    admin.addList(toadd);
                }
                deliveryService.setProducts(items);
                admin.addImportProductsListener(new ImportProductListener());
                admin.addAddProductListener(new AddProductListener());
                admin.addDeleteProductListener(new DeleteProductListener());
                admin.addViewProductListener(new ViewProductListener());
                admin.addCreateProductListener(new CreateProductListener());
                admin.addEditProductListener(new EditProductListener());
                admin.generateReportsListener(new ReportListener());
                deliveryService.createMaps(deliveryService.getOrders());
            }
            if(currentUser.getUser_type().equals("Client")) {
                client = new ClientGUI();
                selectedItems = new ArrayList<String>();
                HashSet<MenuItem> items = deserializator.deserializeProducts("products.txt");
                for(MenuItem m : items) {
                    String toadd = m.toString();
                    client.addList1(toadd);
                }
                deliveryService.setProducts(items);
                client.addCreateOrderListener(new CreateOrderListener());
                client.addAddItemListener(new AddClientItemListener());
                client.viewClientProductListener(new ViewProductClientListener());
                client.addSearchItemListener(new SearchItemListener());
                deliveryService.createMaps(deliveryService.getOrders());
            }
            if(currentUser.getUser_type().equals("Employee")) {
                employee = new EmployeeGUI();
                employee.fillList(ordersEmployee);
                deliveryService.createMaps(deliveryService.getOrders());
            }
        }
        }
    }
    public class ReportListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            reports = new ReportsGUI();
            reports.addHourReportListener(new HourReportListener());
            reports.addProductFrequencyListener(new ProductFrequencyListener());
            reports.addProductDayListener(new ProductDayListener());
            reports.addClientFrequencyListener(new ClientFrequencyListener());
        }
    }
    public class ClientFrequencyListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Map<User, Integer> usersfrequency = deliveryService.reportOrderFrequency(deliveryService.getClientFrequency(), deliveryService.getOrders(), Integer.valueOf(reports.getOrderfrequencytext()), Integer.valueOf(reports.gerPricetext()));
            report.generateUserOrderFrequencyReport(usersfrequency, Integer.valueOf(reports.getOrderfrequencytext()),Integer.valueOf(reports.gerPricetext()) );
        }
    }
    public class ProductDayListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
          Map<MenuItem, Integer> dayproducts = deliveryService.reportDay(deliveryService.getOrders(), deliveryService.getProductFrequency(), Integer.valueOf(reports.getDaytext()));
          report.generateDayProductReport(dayproducts, Integer.valueOf(reports.getDaytext()));
        }
    }
    public class ProductFrequencyListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<MenuItem> frequencyproducts = deliveryService.reportFrequencyProduct(Integer.valueOf(reports.getProductfrequencytext()));
            report.generateFrequencyProductReport(frequencyproducts, Integer.valueOf(reports.getProductfrequencytext()));
        }
    }
    public class HourReportListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
           List<Order> hourorders = deliveryService.reportHour(deliveryService.getOrders(), Integer.valueOf(reports.getStarthourtext()), Integer.valueOf(reports.getEndhourtext()));
            report.generateHourReport(hourorders, Integer.valueOf(reports.getStarthourtext()), Integer.valueOf(reports.getEndhourtext()));
        }
    }
    public class CreateOrderListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int price = 0;
            ArrayList<MenuItem> wantedProducts = new ArrayList<MenuItem>();
            for(String x : selectedItems) {
                for(MenuItem item : deliveryService.getProducts()) {
                    if(item.getTitle().equals(x)) {
                        wantedProducts.add(item);
                        price += item.getPrice();
                    }
                }
            }
            Order order = new Order();
            order = deliveryService.createOrder(wantedProducts, currentUser.getUser_id());
            update(deliveryService, deliveryService.getOrders());
            System.out.println(order.getOrderID());
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            bills.generateOrderBill(order.getOrderID(), currentUser.getUsername(), formatter.format(order.getOrderdate()), wantedProducts, price);
            serializator.serializeOrders(deliveryService.getOrders());
            deliveryService.createMaps(deliveryService.getOrders());
            JOptionPane.showMessageDialog(null,"Order succesful! Total : " + price, "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public class AddClientItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            client.addList2(client.getfromList1());
            selectedItems.add(client.getfromList1());
        }
    }
    public class SearchItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String title = null;
            int price = 0;
            int calories = 0;
            Double rating = 0.00;
            if(!client.getPrice().isEmpty()) {
                price = Integer.valueOf(client.getPrice());
            }
            if(!client.getCalories().isEmpty()) {
                calories = Integer.valueOf(client.getCalories());
            }
            if(!client.getRating().isEmpty()) {
                rating = Double.valueOf(client.getRating());
            }
            if(!client.getTitle().isEmpty()) {
                title = client.getTitle();
            }
            client.clearList1();
            HashSet<MenuItem> products = deliveryService.getProducts();
            List<MenuItem> searchresults = deliveryService.searchItems(products, title, price, calories, rating);
            for(MenuItem x : searchresults) {
                client.addList1(x.getTitle());
            }
        }
    }
    public class CreateProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            selectedItems = new ArrayList<String>();
            createproduct = new CreateProductGUI();
            createproduct.addItemListener(new AddItemListener());
            for(MenuItem m : deliveryService.getProducts()) {
                String toadd = m.toString();
                createproduct.addList1(toadd);
            }
            createproduct.createItemListener(new CreateItemListener());
        }
    }
    public class AddItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            createproduct.addList2(createproduct.getfromList());
            selectedItems.add(createproduct.getfromList());
        }
    }

    public class CreateItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<MenuItem> wantedProducts = new ArrayList<MenuItem>();
            String title;
            int price = 0;
            int calories = 0;
            int sodium = 0;
            Double rating = 0.00;
            int i = 1;
            int fat = 0;
            int protein = 0;

            for(String x : selectedItems) {
                   for(MenuItem item : deliveryService.getProducts()) {
                       if(item.getTitle().equals(x)) {
                           wantedProducts.add(item);
                       }
                   }
            }
            for(MenuItem x : wantedProducts) {
                price += x.getPrice();
                calories += x.getCalories();
                sodium += x.getSodium();
                rating += x.getRating();
                i++;
                fat += x.getFat();
                protein += x.getProtein();
            }
            title = createproduct.getTitle();
            rating = rating / i;
            CompositeProduct item = new CompositeProduct(title, rating, calories, protein, fat, sodium);
            item.setProducts(wantedProducts);
            item.computePrice();
            deliveryService.addItem(item);
            admin.addList(item.getTitle());
            serializator.serializeProducts(deliveryService.getProducts());
        }
    }

    public class EditProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String x = admin.getfromList();
            MenuItem wantedProduct = null;
            for(MenuItem item : deliveryService.getProducts()) {
                if(item.getTitle().equals(x)) {
                    wantedProduct = item;
                    break;
                }
                itemindex++;
            }
            modifyproduct = new ModifyProductGUI();
            modifyproduct.addModifyListener(new ModifyProductListener());
            modifyproduct.setTitle(wantedProduct.getTitle());
            modifyproduct.setFat(String.valueOf(wantedProduct.getFat()));
            modifyproduct.setCalories(String.valueOf(wantedProduct.getCalories()));
            modifyproduct.setPrice(String.valueOf(wantedProduct.getPrice()));
            modifyproduct.setSodium(String.valueOf(wantedProduct.getSodium()));
            modifyproduct.setProtein(String.valueOf(wantedProduct.getProtein()));
            modifyproduct.setRating(String.valueOf(wantedProduct.getRating()));

        }
    }
    public class ViewProductClientListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            viewproduct = new ViewProductGUI();
            String x = null;
            if(client.getfromList1() == null) {
                x = client.getfromList2();
            }
            if(client.getfromList2() == null) {
                x = client.getfromList1();
            }
            System.out.println(x);
            MenuItem wantedProduct = null;

            for(MenuItem item : deliveryService.getProducts()) {
                if(item.getTitle().equals(x)) {
                    wantedProduct = item;
                    break;
                }
            }
            viewproduct.setTitle(wantedProduct.getTitle());
            viewproduct.setFat(String.valueOf(wantedProduct.getFat()));
            viewproduct.setCalories(String.valueOf(wantedProduct.getCalories()));
            viewproduct.setPrice(String.valueOf(wantedProduct.getPrice()));
            viewproduct.setSodium(String.valueOf(wantedProduct.getSodium()));
            viewproduct.setProtein(String.valueOf(wantedProduct.getProtein()));
            viewproduct.setRating(String.valueOf(wantedProduct.getRating()));
        }
    }
    public class ModifyProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            String title = modifyproduct.getTitle();
            int wantedindex = 0;
            int fat = Integer.valueOf(modifyproduct.getFat());
            int calories = Integer.valueOf(modifyproduct.getCalories());
            int price = Integer.valueOf(modifyproduct.getPrice());
            int sodium = Integer.valueOf(modifyproduct.getSodium());
            int protein = Integer.valueOf(modifyproduct.getProtein());
            Double rating = Double.valueOf(modifyproduct.getRating());
            ArrayList<MenuItem> searchitems = new ArrayList<MenuItem>(deliveryService.getProducts());
            for(int i = 0; i < searchitems.size(); i++) {
                if(searchitems.get(i).equals(title)) {
                    wantedindex = i;
                    break;
                }
            }
            searchitems.get(wantedindex).setCalories(calories);
            searchitems.get(wantedindex).setFat(fat);
            searchitems.get(wantedindex).setPrice(price);
            searchitems.get(wantedindex).setSodium(sodium);
            searchitems.get(wantedindex).setProtein(protein);
            searchitems.get(wantedindex).setTitle(title);
            searchitems.get(wantedindex).setRating(rating);
            HashSet<MenuItem> searchitemsdone = new HashSet<MenuItem>(searchitems);
            deliveryService.setProducts(searchitemsdone);
            JOptionPane.showMessageDialog(null,"Produs modificat cu succes!", "Info", JOptionPane.INFORMATION_MESSAGE);
            modifyproduct.closeView();
        }
    }
    public class ViewProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            viewproduct = new ViewProductGUI();
            String x = admin.getfromList();
            MenuItem wantedProduct = null;
            for(MenuItem item : deliveryService.getProducts()) {
                if(item.getTitle().equals(x)) {
                    wantedProduct = item;
                    break;
                }
            }
            viewproduct.setTitle(wantedProduct.getTitle());
            viewproduct.setFat(String.valueOf(wantedProduct.getFat()));
            viewproduct.setCalories(String.valueOf(wantedProduct.getCalories()));
            viewproduct.setPrice(String.valueOf(wantedProduct.getPrice()));
            viewproduct.setSodium(String.valueOf(wantedProduct.getSodium()));
            viewproduct.setProtein(String.valueOf(wantedProduct.getProtein()));
            viewproduct.setRating(String.valueOf(wantedProduct.getRating()));
        }
    }
    public class DeleteProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String x = admin.getfromList();
            System.out.println(x);
            MenuItem wantedProduct = null;
            HashSet<MenuItem> items = deliveryService.getProducts();
            for(MenuItem item : items) {
                if(item.getTitle().equals(x)) {
                    wantedProduct = item;
                    break;
                }
            }
            items.remove(wantedProduct);
            deliveryService.setProducts(items);
            serializator.serializeProducts(items);
            admin.removeList(wantedProduct.getTitle());
        }
    }
    public class AddProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            addproduct = new AddProductGUI();
            addproduct.addProductListener(new AddProductListener2());
        }
    }
    public class AddProductListener2 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(addproduct.getRating().isEmpty() || addproduct.getCalories().isEmpty() || addproduct.getFat().isEmpty() || addproduct.getTitle().isEmpty() || addproduct.getPrice().isEmpty() || addproduct.getProtein().isEmpty() || addproduct.getSodium().isEmpty()) {
                JOptionPane.showMessageDialog(null,"Date introduse gresit!", "Error", JOptionPane.ERROR_MESSAGE);
            }
            else {
                Double rating = Double.valueOf(addproduct.getRating());
                int calories = Integer.valueOf(addproduct.getCalories());
                int price = Integer.valueOf(addproduct.getPrice());
                int fat = Integer.valueOf(addproduct.getFat());
                int protein = Integer.valueOf(addproduct.getProtein());
                int sodium = Integer.valueOf(addproduct.getSodium());
                BaseProduct base = new BaseProduct(addproduct.getTitle(), rating, calories, protein, fat, sodium, price);
                deliveryService.addItem(base);
                System.out.println(base.toString());
                admin.addList(base.toString());
                serializator.addProduct(base);
                JOptionPane.showMessageDialog(null,"Produs adaugat cu succes!", "Info", JOptionPane.INFORMATION_MESSAGE);
                addproduct.closeView();
            }
        }
    }
    public class ImportProductListener implements  ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            importproduct = new ImportProductGUI();
            importproduct.addImportProductListener2(new ImportProductListener2());
        }
    }

    public class ImportProductListener2 implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            deliveryService.importProducts(importproduct.getPath());
            HashSet<MenuItem> items = deliveryService.getProducts();
            for(MenuItem m : items) {
                String toadd = m.toString();
                admin.addList(toadd);
            }
            importproduct.closeView();
        }
    }

    public class RegisterListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            register = new RegisterGUI();
            register.addRegister2Listener(new Register2Listener());
        }
    }

    public class Register2Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = register.getUsernametext();
            String password = register.getPasswordtext();
            String usertype = register.getusertype();
            User user = new User(username, password);
            user.setUser_type(usertype);
            user.setUser_id(deliveryService.getUsers().size());
            deliveryService.addUser(user);
            try {
                out.writeObject(user);
                out.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            register.closeView();
        }
    }

    public void closeStreams() {
        try {
            input.close();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Controller c = new Controller();
    }
}
