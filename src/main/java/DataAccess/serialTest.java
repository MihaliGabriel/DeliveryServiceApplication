package DataAccess;

import java.io.IOException;
import java.util.ArrayList;

public class serialTest {
    public static void main(String[] args) {
        Serializator serial = new Serializator();
        ArrayList<String> entries = new ArrayList<String>();
        ArrayList<String> csventries = new ArrayList<String>();
        ArrayList<String> csventries2 = new ArrayList<String>();
        csventries.add("test1");
        csventries.add("test2");
        csventries.add("test3");
        csventries2.add("test4");
        csventries2.add("test5");
        csventries2.add("test6");

        try {
            entries = serial.processCSV("C:\\Users\\Gobi\\Downloads\\products.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(String x : entries) {
            System.out.println(x);
        }
    }
}
