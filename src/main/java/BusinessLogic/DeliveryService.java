package BusinessLogic;

import DataAccess.AppendableObjectOutputStream;
import DataAccess.Serializator;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Se ocupa de actiunile pe datele aplicatiei.
 * Realizeaza rapoartele pe date.
 * Extinde Observable si notifica Observer-ul de crearea unei noi comenzi.
 * De asemenea creeaza map-uri de frecventa.
 */

public class DeliveryService extends Observable implements DeliveryServiceProcessing{
    ArrayList<User> users;
    HashSet<MenuItem> products;
    Serializator serializator;
    HashMap<Order, ArrayList<MenuItem>> orders;
    Map<MenuItem, Long> productFrequency;
    Map<User, Long> clientFrequency;

    /**
     * @custom.mytag Method to verify if produst and users are null.
     */
    private void Invariant() {
        assert products != null;
        assert users != null;
    }

    public Map<MenuItem, Long> getProductFrequency() {
        return productFrequency;
    }
    public Map<User, Long> getClientFrequency() {
        return clientFrequency;
    }
    public void setUsers(ArrayList<User> use) {
        users = use;
    }
    public ArrayList<User> getUsers() {
        return users;
    }
    public HashSet<MenuItem> getProducts() {
        return products;
    }

    public void setProducts(HashSet<MenuItem> products) {
        this.products = products;
    }

    public  HashMap<Order, ArrayList<MenuItem>> getOrders() {
        return orders;
    }

    public void setOrders( HashMap<Order, ArrayList<MenuItem>> orders) {
        this.orders = orders;
    }

    public Serializator getSerializator() {
        return serializator;
    }

    public void setSerializator(Serializator serializator) {
        this.serializator = serializator;
    }
    public void addUser(User u) {
        users.add(u);
    }

    public DeliveryService(Serializator serializator) {
        clientFrequency = new HashMap<User, Long>();
        this.serializator = serializator;
        products = new HashSet<MenuItem>();
        users = new ArrayList<User>();
        orders = new  HashMap<Order, ArrayList<MenuItem>>();
        Invariant();
    }

    public Order createOrder(ArrayList<MenuItem> items, int clientID) {
        users = new ArrayList<User>();
        Date date = new Date();
        Order order = new Order(items, clientID, date);
        order.setOrderID(orders.size());
        orders.put(order, items);
        notifyObservers(orders);
        return order;
    }
    public List<MenuItem> searchItems(HashSet<MenuItem> products, String title, int price, int calories, Double rating) {
        List<MenuItem> searchresults;
        if(title == null) {
                        searchresults = products.stream()
                    .filter(p -> p.getPrice() >= price && p.getCalories() >= calories && p.getRating() >= rating)
                    .collect(Collectors.toList());
        }
        else {
            searchresults = products.stream()
                    .filter(p -> p.getTitle().contains(title) && p.getPrice() >= price && p.getCalories() >= calories && p.getRating() >= rating)
                    .collect(Collectors.toList());
        }
        return searchresults;
    }
    @Override
    public void addItem(MenuItem item) {
        products.add(item);
    }
    public void deleteItem(MenuItem item) {
        products.remove(item);
    }
    public void editItem(MenuItem item) {
    }

    @Override
    public void importProducts(String path) {
        File productFile = new File("products.txt");
        boolean append2 = productFile.exists();
        AppendableObjectOutputStream out2;
        FileOutputStream fout2;
        try {
            ArrayList<String> fields = serializator.processCSV(path);
            fout2 = new FileOutputStream("products.txt", append2);
            out2 = new AppendableObjectOutputStream(fout2, append2);
            for(String x : fields) {
                String[] separated = x.split(",");
                BaseProduct menuItem = new BaseProduct(separated[0], Double.valueOf(separated[1]), Integer.valueOf(separated[2]), Integer.valueOf(separated[3]), Integer.valueOf(separated[4]), Integer.valueOf(separated[5]), Integer.valueOf(separated[6]));
                products.add(menuItem);
                out2.writeObject(menuItem);
                out2.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<Order> reportHour(HashMap<Order, ArrayList<MenuItem>> orders, int hour1, int hour2) {
        Set<Order> ordersList = orders.keySet();
        assert !ordersList.isEmpty();
        List<Order> wantedOrders = ordersList.stream()
                .filter(p -> p.getOrderdate().getHours() <= hour2 && p.getOrderdate().getHours() >= hour1)
                .collect(Collectors.toList());
        return wantedOrders;
    }
    public List<MenuItem> reportFrequencyProduct(int frequency) {
        List<MenuItem> wantedItems = new ArrayList<MenuItem>();
        for(MenuItem item : productFrequency.keySet()) {
            if(productFrequency.get(item) >= frequency) {
                wantedItems.add(item);
            }
        }
        return wantedItems;
    }

   public Map<MenuItem, Integer> reportDay(HashMap<Order, ArrayList<MenuItem>> orders, Map<MenuItem, Long> produsFrequency, int day) {
        Map<MenuItem, Integer> wantedProducts = new HashMap<MenuItem, Integer>();
        List<Order> coll = orders.keySet().stream()
                .filter(x -> x.getOrderdate().getDate() == day)
                .toList();
        for(Order order : coll) {
            for(MenuItem item : orders.get(order)) {
                wantedProducts.put(item, produsFrequency.get(item).intValue());
            }
        }
        assert wantedProducts == null;
        return wantedProducts;
    }
    public Map<User, Integer> reportOrderFrequency(Map<User, Long> clientfrequency, HashMap<Order, ArrayList<MenuItem>> orders, int frequency, int minprice) {
        Map<User, Integer> wantedClients = new HashMap<User, Integer>();
        Map<Order, Integer> ordersPrice = new HashMap<Order, Integer>();

        for(Order x : orders.keySet()) {
            int price = 0;
            for(MenuItem item : orders.get(x)) {
                price += item.getPrice();
            }
            ordersPrice.put(x, price);
        }
        for(User x : clientfrequency.keySet()) {
            if(clientfrequency.get(x).intValue() >= frequency) {
                int userID = x.getUser_id();
                int higherValue = 1;
                for(Order o : orders.keySet()) {
                    if(o.getClientID() == userID && ordersPrice.get(o) < minprice) {
                        higherValue = 0;
                        break;
                    }
                }
                if(higherValue == 1) {
                    wantedClients.put(x, clientfrequency.get(x).intValue());
                }
            }
        }
        return wantedClients;
    }
    public void createMaps(HashMap<Order, ArrayList<MenuItem>> orders) {
        ArrayList<MenuItem> itemsOrdered = new ArrayList<MenuItem>();
        ArrayList<Order> ordersList = new ArrayList<Order>();
        assert orders != null;
        Collection coll = orders.values();
        ArrayList<ArrayList<MenuItem>> items =  new ArrayList<ArrayList<MenuItem>>(coll);
        Map<Integer, Long> idClientFrequency;
        for(ArrayList<MenuItem> item : items) {
            for(MenuItem x : item) {
                itemsOrdered.add(x);
            }
        }
        for(Order ord : orders.keySet()) {
            ordersList.add(ord);
            System.out.println(ord.getOrderID() + " " + ord.getClientID() + " " + ord.getOrderdate().getDate());
        }
        productFrequency =  itemsOrdered.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        idClientFrequency = new HashMap<Integer, Long>();
        for(int i = 1; i <= users.size(); i++) {
            Integer frequencyOrder = 0;
            for(Order o : orders.keySet()) {
                if(o.getClientID() == i) {
                    frequencyOrder++;
                }
            }
            idClientFrequency.put(i, frequencyOrder.longValue());
        }
        for(int i = 1; i <= idClientFrequency.size(); i++) {
            System.out.println(i + " " + idClientFrequency.get(i));
            for(User x : users) {
                if(x.getUser_id() == i) {
                    User wantedUser = x;
                    clientFrequency.put(wantedUser, idClientFrequency.get(i));
                    break;
                }
            }
        }
    }
}
