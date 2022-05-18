
package Presentation;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class AdminGUI {
    private JList itemsList;
    private JLabel items;
    private JButton addproductbutton;
    private JButton jcomp4;
    private JButton jcomp5;
    private JButton jcomp6;
    private JFrame frame;
    private DefaultListModel listModel;
    private JScrollPane scrollpane;
    private JButton importbutton;
    private JButton viewproductbutton;
    private JButton generatereportsbutton;

    public AdminGUI() {
        generatereportsbutton = new JButton("Generate reports");
        viewproductbutton = new JButton("View product");
        listModel = new DefaultListModel();
        JFrame frame = new JFrame("ADMIN");
        itemsList = new JList (listModel);
        scrollpane = new JScrollPane(itemsList,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        items = new JLabel ("Items");
        addproductbutton = new JButton ("Add product");
        jcomp4 = new JButton ("Create product");
        jcomp5 = new JButton ("Delete product");
        jcomp6 = new JButton ("Modify product");
        importbutton = new JButton("Import products");

        frame.setSize(new Dimension (730, 502));
        frame.setLayout(null);
        frame.setVisible(true);

        frame.add(generatereportsbutton);
        frame.add(viewproductbutton);
        frame.add(items);
        frame.add(addproductbutton);
        frame.add(jcomp4);
        frame.add(jcomp5);
        frame.add(jcomp6);
        frame.add(scrollpane);
        frame.add(importbutton);

        generatereportsbutton.setBounds(300, 45, 135, 25);
        viewproductbutton.setBounds(120, 45, 135, 25);
        scrollpane.setBounds (15, 80, 375, 400);
        items.setBounds (170, 15, 100, 25);
        addproductbutton.setBounds (495, 150, 135, 35);
        jcomp4.setBounds (500, 230, 135, 40);
        jcomp5.setBounds (500, 315, 135, 40);
        jcomp6.setBounds (500, 400, 135, 40);
        importbutton.setBounds(500, 65, 135, 35);
    }
    public void addList(String x) {
        listModel.addElement(x);
    }
    public String getfromList() {
        return (String)itemsList.getSelectedValue();
    }
    public void removeList(String x) {
        listModel.removeElement(x);
    }
    public void generateReportsListener(ActionListener i) {
        generatereportsbutton.addActionListener(i);
    }
    public void addViewProductListener(ActionListener f) {
        viewproductbutton.addActionListener(f);
    }
    public void addCreateProductListener(ActionListener a) {
        jcomp4.addActionListener(a);
    }
    public void addEditProductListener(ActionListener b) {
        jcomp6.addActionListener(b);
    }
    public void addDeleteProductListener(ActionListener c) {
        jcomp5.addActionListener(c);
    }
    public void addAddProductListener(ActionListener d) {
        addproductbutton.addActionListener(d);
    }
    public void addImportProductsListener(ActionListener e) {
        importbutton.addActionListener(e);
    }
    public static void main (String[] args) {
        AdminGUI admin = new AdminGUI();
    }
}
