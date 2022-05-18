package Presentation;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class CreateProductGUI{
    private JLabel jcomp1;
    private JList itemslist;
    private JList wantedlist;
    private JTextField jcomp4;
    private JLabel title;
    private JLabel jcomp6;
    private JLabel jcomp7;
    private JButton jcomp8;
    private JFrame frame;
    private DefaultListModel listmodel1;
    private DefaultListModel listmodel2;
    private JButton jcomp9;
    private JScrollPane scrollpane1;
    private JScrollPane scrollpane2;

    public CreateProductGUI() {

        listmodel1 = new DefaultListModel();
        listmodel2 = new DefaultListModel();
        frame = new JFrame("Create product");
        jcomp1 = new JLabel ("Create new menu item");
        itemslist = new JList (listmodel1);
        wantedlist = new JList (listmodel2);
        jcomp4 = new JTextField (5);
        title = new JLabel ("Title");
        jcomp6 = new JLabel ("Items");
        jcomp7 = new JLabel ("Selected items");
        jcomp8 = new JButton ("Create item");
        jcomp9 = new JButton ("Add item");
        scrollpane1 = new JScrollPane(itemslist,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollpane2 = new JScrollPane(wantedlist,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        frame.setSize(660, 500);
        frame.setLayout(null);
        frame.setVisible (true);

        frame.add(jcomp1);
        frame.add(scrollpane1);
        frame.add(scrollpane2);
        frame.add(jcomp4);
        frame.add(title);
        frame.add(jcomp6);
        frame.add(jcomp7);
        frame.add(jcomp8);
        frame.add(jcomp9);

        jcomp1.setBounds (250, 0, 140, 40);
        scrollpane1.setBounds (20, 155, 220, 240);
        scrollpane2.setBounds (395, 155, 220, 240);
        jcomp4.setBounds (215, 110, 200, 30);
        title.setBounds (295, 85, 100, 25);
        jcomp6.setBounds (95, 135, 100, 25);
        jcomp7.setBounds (455, 130, 100, 25);
        jcomp8.setBounds (260, 410, 125, 30);
        jcomp9.setBounds (270, 170, 100, 25);
    }
    public void addList2(String x) {
        listmodel2.addElement(x);
    }
    public void addList1(String x) {
        listmodel1.addElement(x);
    }
    public String getfromList() {
        return (String)itemslist.getSelectedValue();
    }
    public String getTitle() {
        return jcomp4.getText();
    }
    public void addItemListener(ActionListener a) {
        jcomp9.addActionListener(a);
    }
    public void createItemListener(ActionListener b) {
        jcomp8.addActionListener(b);
    }
    public void closeView() {
        frame.setVisible(false);
    }
    public static void main (String[] args) {
        CreateProductGUI s = new CreateProductGUI();
    }
}
