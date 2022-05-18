package Presentation;
import BusinessLogic.Order;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import javax.swing.event.*;

public class EmployeeGUI implements Observer {
    private JList orderslist;
    private JLabel orders;
    private JFrame frame;
    private JScrollPane scrollpane;
    private DefaultListModel<String> listmodel = new DefaultListModel<String>();
    public EmployeeGUI() {
        frame = new JFrame ("Employee");
        orderslist = new JList (listmodel);
        orders = new JLabel ("Orders");
        scrollpane = new JScrollPane(orderslist,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        frame.setSize(752, 580);
        frame.setLayout(null);
        frame.setVisible (true);

        frame.add(orders);
        frame.add(scrollpane);


        scrollpane.setBounds (105, 105, 560, 450);
        orders.setBounds (370, 80, 100, 25);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
    public void fillList(ArrayList<String> list) {
        for(String x : list) {
            listmodel.addElement(x);
        }
    }
    public void addList(String x) {
        listmodel.addElement(x);
    }

    public static void main (String[] args) {
    EmployeeGUI employee = new EmployeeGUI();

    }
}
