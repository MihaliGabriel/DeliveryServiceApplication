
package Presentation;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class ClientGUI{
    private JList itemsList;
    private JList wanteditemsList;
    private JLabel items;
    private JLabel wanteditems;
    private JLabel calories;
    private JLabel price;
    private JLabel rating;
    private JButton createorderbutton;
    private JFrame frame;
    private DefaultListModel listModel;
    private DefaultListModel wantedlistModel;
    private JScrollPane scrollpane;
    private JScrollPane wantedscrollpane;
    private JButton searchbutton;
    private JButton viewproductbutton;
    private JTextField titletext;
    private JTextField caloriestext;
    private JTextField pricetext;
    private JTextField ratingtext;
    private JLabel title;
    private JButton additembutton;

    public ClientGUI() {
        viewproductbutton = new JButton("View product");
        listModel = new DefaultListModel();
        wantedlistModel = new DefaultListModel();
        frame = new JFrame("ADMIN");
        itemsList = new JList (listModel);
        wanteditemsList = new JList(wantedlistModel);
        scrollpane = new JScrollPane(itemsList,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        wantedscrollpane = new JScrollPane(wanteditemsList,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        items = new JLabel ("Items");
        wanteditems = new JLabel("Shopping cart");
        calories = new JLabel ("Calories");
        price = new JLabel ("Price");
        rating = new JLabel ("Rating");
        createorderbutton = new JButton ("Create order");
        searchbutton = new JButton("Search");
        titletext = new JTextField(5);
        pricetext = new JTextField(5);
        caloriestext = new JTextField(5);
        ratingtext = new JTextField(5);
        title = new JLabel("Title");
        additembutton = new JButton("Add item");

        frame.setSize(1100, 600);
        frame.setLayout(null);
        frame.setVisible(true);

        frame.add(titletext);
        frame.add(pricetext);
        frame.add(caloriestext);
        frame.add(ratingtext);
        frame.add(viewproductbutton);
        frame.add(items);
        frame.add(calories);
        frame.add(price);
        frame.add(rating);
        frame.add(createorderbutton);
        frame.add(scrollpane);
        frame.add(searchbutton);
        frame.add(wantedscrollpane);
        frame.add(wanteditems);
        frame.add(title);
        frame.add(additembutton);

        additembutton.setBounds(450, 150, 135, 35);
        title.setBounds(450, 205, 135, 35);
        titletext.setBounds(450, 235, 160, 30);
        viewproductbutton.setBounds(120, 45, 135, 25);
        scrollpane.setBounds (15, 80, 375, 400);
        items.setBounds (170, 15, 100, 25);
        calories.setBounds (450, 270, 135, 35);
        caloriestext.setBounds(450, 300, 160, 30);
        price.setBounds (450, 335, 135, 40);
        pricetext.setBounds(450, 365, 160, 30);
        rating.setBounds (450, 400, 135, 40);
        ratingtext.setBounds(450, 430, 160, 30);
        createorderbutton.setBounds (450, 500, 150, 40);
        searchbutton.setBounds(450, 85, 135, 35);
        wantedscrollpane.setBounds(645, 80, 375, 400);
        wanteditems.setBounds(800, 50, 100, 25);
    }
    public void addList1(String x) {
        listModel.addElement(x);
    }
    public void addList2(String x) {
        wantedlistModel.addElement(x);
    }
    public String getTitle() {
        return titletext.getText();
    }
    public String getPrice() {
        return pricetext.getText();
    }
    public String getRating() {
        return ratingtext.getText();
    }
    public String getCalories() {
        return caloriestext.getText();
    }
    public void addSearchItemListener(ActionListener d) {
        searchbutton.addActionListener(d);
    }
    public void addCreateOrderListener(ActionListener a) {
        createorderbutton.addActionListener(a);
    }
    public void addAddItemListener(ActionListener b) {
        additembutton.addActionListener(b);
    }
    public void viewClientProductListener(ActionListener c) {
        viewproductbutton.addActionListener(c);
    }
    public String getfromList1() {
        return (String)itemsList.getSelectedValue();
    }
    public String getfromList2() {
        return (String)wanteditemsList.getSelectedValue();
    }
    public void clearList1() {
        listModel.removeAllElements();
    }
    public static void main (String[] args) {
        ClientGUI admin = new ClientGUI();
    }
}
