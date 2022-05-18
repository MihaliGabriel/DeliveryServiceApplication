package Presentation;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class ReportsGUI{
    private JTextField starthourtext;
    private JTextField productfrequencytext;
    private JTextField orderfrequencytext;
    private JTextField endhourtext;
    private JLabel timeinterval;
    private JLabel starthour;
    private JLabel endhour;
    private JButton orderhourbutton;
    private JLabel frequency;
    private JButton productfrequencybutton;
    private JTextField pricetext;
    private JButton clientfrequencybutton;
    private JLabel jcomp13;
    private JLabel jcomp14;
    private JTextField daytext;
    private JLabel day;
    private JButton productdaybutton;
    private JFrame frame;

    public ReportsGUI() {

        frame = new JFrame("Reports");
        starthourtext = new JTextField (5);
        productfrequencytext = new JTextField (5);
        orderfrequencytext = new JTextField (5);
        endhourtext = new JTextField (5);
        timeinterval = new JLabel ("Time interval");
        starthour = new JLabel ("Start hour");
        endhour = new JLabel ("End hour");
        orderhourbutton = new JButton ("Generate order hour report");
        frequency = new JLabel ("Frequency");
        productfrequencybutton = new JButton ("Generate product frequency report");
        pricetext = new JTextField (5);
        clientfrequencybutton = new JButton ("Generate client frequency report");
        jcomp13 = new JLabel ("Order frequency");
        jcomp14 = new JLabel ("Minimum price");
        daytext = new JTextField (5);
        day = new JLabel ("Day");
        productdaybutton = new JButton ("Generate product day report");


        frame.setSize(421, 525);
        frame.setLayout(null);
        frame.setVisible(true);


        frame.add(starthourtext);
        frame.add(productfrequencytext);
        frame.add(orderfrequencytext);
        frame.add(endhourtext);
        frame.add(timeinterval);
        frame.add(starthour);
        frame.add(endhour);
        frame.add(orderhourbutton);
        frame.add(frequency);
        frame.add(productfrequencybutton);
        frame.add(pricetext);
        frame.add(clientfrequencybutton);
        frame.add(jcomp13);
        frame.add(jcomp14);
        frame.add(daytext);
        frame.add(day);
        frame.add(productdaybutton);


        starthourtext.setBounds (55, 65, 100, 25);
        productfrequencytext.setBounds (135, 170, 100, 25);
        orderfrequencytext.setBounds (70, 285, 100, 25);
        endhourtext.setBounds (230, 65, 100, 25);
        timeinterval.setBounds (150, 10, 100, 25);
        starthour.setBounds (55, 40, 100, 25);
        endhour.setBounds (235, 40, 100, 25);
        orderhourbutton.setBounds (80, 100, 225, 25);
        frequency.setBounds (155, 150, 100, 25);
        productfrequencybutton.setBounds (65, 205, 240, 30);
        pricetext.setBounds (225, 285, 100, 25);
        clientfrequencybutton.setBounds (75, 335, 245, 25);
        jcomp13.setBounds (70, 265, 100, 25);
        jcomp14.setBounds (225, 265, 100, 25);
        daytext.setBounds (145, 415, 100, 25);
        day.setBounds (180, 395, 100, 25);
        productdaybutton.setBounds (70, 450, 235, 30);
    }

    public void addHourReportListener(ActionListener a) {
        orderhourbutton.addActionListener(a);
    }
    public void addProductFrequencyListener(ActionListener b) {
        productfrequencybutton.addActionListener(b);
    }
    public void addClientFrequencyListener(ActionListener c) {
        clientfrequencybutton.addActionListener(c);
    }
    public void addProductDayListener(ActionListener d) {
        productdaybutton.addActionListener(d);
    }

    public String getStarthourtext() {
        return starthourtext.getText();
    }

    public void setStarthourtext(JTextField starthourtext) {
        this.starthourtext = starthourtext;
    }

    public String getProductfrequencytext() {
        return productfrequencytext.getText();
    }

    public void setProductfrequencytext(JTextField productfrequencytext) {
        this.productfrequencytext = productfrequencytext;
    }

    public String getOrderfrequencytext() {
        return orderfrequencytext.getText();
    }

    public void setOrderfrequencytext(JTextField orderfrequencytext) {
        this.orderfrequencytext = orderfrequencytext;
    }

    public String getEndhourtext() {
        return endhourtext.getText();
    }

    public void setEndhourtext(JTextField endhourtext) {
        this.endhourtext = endhourtext;
    }
    public String getDaytext() {
       return daytext.getText();
    }
    public String gerPricetext() {
        return pricetext.getText();
    }
    public static void main (String[] args) {
        ReportsGUI report = new ReportsGUI();
    }
}
