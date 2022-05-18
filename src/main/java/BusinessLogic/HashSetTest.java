package BusinessLogic;

import java.util.ArrayList;
import java.util.HashSet;

public class HashSetTest {
    public static void main(String[] args) {
        ArrayList<MenuItem> items = new ArrayList<MenuItem>();
        BaseProduct item = new BaseProduct("test", 10.00, 15, 20, 30, 35, 10);
        BaseProduct item2 = new BaseProduct("test", 15.00, 20, 35, 32, 40, 10);
        items.add(item);
        items.add(item2);
        HashSet<MenuItem> set = new HashSet<MenuItem>(items);
        for(MenuItem m : set) {
            System.out.println(m.toString());
        }
    }
}
