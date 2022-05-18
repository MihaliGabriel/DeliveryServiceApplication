package Presentation;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class LoginGUI {
    private JTextField usernametext;
    private JTextField passwordtext;
    private JLabel username;
    private JLabel password;
    private JButton loginbutton;
    private JButton registerbutton;
    private JLabel foodelivery;
    private JFrame frame;

    public LoginGUI() {
        frame = new JFrame("Login");
        usernametext = new JTextField (5);
        passwordtext = new JTextField (5);
        username = new JLabel ("Username");
        password = new JLabel ("Password");
        loginbutton = new JButton ("Login");
        registerbutton = new JButton ("Register");
        foodelivery = new JLabel ("Food delivery");

        frame.setSize(404, 342);
        frame.setLayout(null);
        frame.setVisible (true);

        frame.add(usernametext);
        frame.add(passwordtext);
        frame.add(username);
        frame.add(password);
        frame.add(loginbutton);
        frame.add(registerbutton);
        frame.add(foodelivery);


        usernametext.setBounds (100, 125, 200, 30);
        passwordtext.setBounds (100, 195, 200, 30);
        username.setBounds (100, 100, 100, 25);
        password.setBounds (100, 170, 100, 25);
        loginbutton.setBounds (60, 265, 100, 25);
        registerbutton.setBounds (225, 265, 100, 25);
        foodelivery.setBounds (125, 20, 120, 25);
    }

    public void addLoginListener(ActionListener a) {
        loginbutton.addActionListener(a);
    }

    public void addRegisterListener(ActionListener b) {
        registerbutton.addActionListener(b);
    }

    public String getUsernametext() {
        return usernametext.getText();
    }

    public String getPasswordtext() {
        return passwordtext.getText();
    }

    public void showError() {
        JOptionPane.showMessageDialog(null, "Wrong username or password!", "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main (String[] args) {
        LoginGUI login = new LoginGUI();
    }
}
