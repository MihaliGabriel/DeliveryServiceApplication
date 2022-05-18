package Presentation;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class ModifyProductGUI{

    private JLabel jcomp2;
    private JTextField jcomp3;
    private JTextField jcomp4;
    private JLabel jcomp5;
    private JTextField jcomp6;
    private JLabel jcomp7;
    private JTextField jcomp8;
    private JTextField jcomp9;
    private JTextField jcomp10;
    private JLabel jcomp11;
    private JLabel jcomp12;
    private JLabel jcomp13;
    private JTextField jcomp14;
    private JLabel jcomp15;
    private JFrame frame;
    private JButton modifybutton;

    public ModifyProductGUI() {
        modifybutton = new JButton("Modify product");
        frame = new JFrame("Add product");
        jcomp2 = new JLabel("Title");
        jcomp3 = new JTextField(5);
        jcomp4 = new JTextField(5);
        jcomp5 = new JLabel("Price");
        jcomp6 = new JTextField(5);
        jcomp7 = new JLabel("Calories");
        jcomp8 = new JTextField(5);
        jcomp9 = new JTextField(5);
        jcomp10 = new JTextField(5);
        jcomp11 = new JLabel("Protein");
        jcomp12 = new JLabel("Fat");
        jcomp13 = new JLabel("Sodium");
        jcomp14 = new JTextField(5);
        jcomp15 = new JLabel("Rating");


        frame.setSize(660, 471);
        frame.setLayout(null);
        frame.setVisible(true);

        frame.add(modifybutton);
        frame.add(jcomp2);
        frame.add(jcomp3);
        frame.add(jcomp4);
        frame.add(jcomp5);
        frame.add(jcomp6);
        frame.add(jcomp7);
        frame.add(jcomp8);
        frame.add(jcomp9);
        frame.add(jcomp10);
        frame.add(jcomp11);
        frame.add(jcomp12);
        frame.add(jcomp13);
        frame.add(jcomp14);
        frame.add(jcomp15);

        jcomp2.setBounds(75, 120, 100, 25);
        jcomp3.setBounds(75, 140, 125, 25);
        jcomp4.setBounds(75, 215, 100, 25);
        jcomp5.setBounds(75, 195, 100, 25);
        jcomp6.setBounds(75, 285, 100, 25);
        jcomp7.setBounds(75, 265, 100, 25);
        jcomp8.setBounds(435, 140, 100, 25);
        jcomp9.setBounds(435, 215, 100, 25);
        jcomp10.setBounds(435, 280, 100, 25);
        jcomp11.setBounds(435, 120, 100, 25);
        jcomp12.setBounds(435, 195, 100, 25);
        jcomp13.setBounds(435, 260, 100, 25);
        jcomp14.setBounds(260, 295, 100, 25);
        jcomp15.setBounds(260, 260, 100, 25);
        modifybutton.setBounds(260, 380, 150, 25);
    }

    public void setTitle(String s) {
        jcomp3.setText(s);
    }

    public void setPrice(String s) {
        jcomp4.setText(s);
    }

    public void setCalories(String s) {
        jcomp6.setText(s);
    }

    public void setProtein(String s) {
        jcomp8.setText(s);
    }

    public void setFat(String s) {
        jcomp9.setText(s);
    }

    public void setSodium(String s) {
        jcomp10.setText(s);
    }

    public void setRating(String s) {
        jcomp14.setText(s);
    }

    public String getTitle() {
        return jcomp3.getText();
    }

    public String getPrice() {
       return jcomp4.getText();
    }

    public String getCalories() {
        return jcomp6.getText();
    }

    public String getProtein() {
        return jcomp8.getText();
    }

    public String getFat() {
        return jcomp9.getText();
    }

    public String getSodium() {
        return jcomp10.getText();
    }

    public String getRating() {
        return jcomp14.getText();
    }

    public void closeView() {
        frame.setVisible(false);
    }
    public void addModifyListener(ActionListener a) {
        modifybutton.addActionListener(a);
    }
    public static void main (String[] args) {
        ModifyProductGUI add = new ModifyProductGUI();
    }
}
