package Presentation;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class RegisterGUI{
    private JTextField usernametext;
    private JTextField passwordtext;
    private JLabel username;
    private JLabel password;
    private JButton registerbutton;
    private JLabel register;
    private JComboBox jcomp7;
    private JLabel usertype;
    private JFrame frame;

    public RegisterGUI() {

        String[] jcomp7Items = {"Administrator", "Client", "Employee"};

        frame = new JFrame("Register");
        usernametext = new JTextField (5);
        passwordtext = new JTextField (5);
        username = new JLabel ("Username");
        password = new JLabel ("Password");
        registerbutton = new JButton ("Register");
        register = new JLabel ("Register");
        jcomp7 = new JComboBox (jcomp7Items);
        usertype = new JLabel ("Register as :");


        frame.setSize(404, 342);
        frame.setLayout(null);
        frame.setVisible(true);


        frame.add(usernametext);
        frame.add(passwordtext);
        frame.add(username);
        frame.add(password);
        frame.add(registerbutton);
        frame.add(register);
        frame.add(jcomp7);
        frame.add(usertype);


        usernametext.setBounds (45, 75, 200, 30);
        passwordtext.setBounds (45, 135, 200, 30);
        username.setBounds (45, 55, 100, 25);
        password.setBounds (45, 110, 100, 25);
        registerbutton.setBounds (140, 260, 100, 25);
        register.setBounds (45, 5, 120, 25);
        jcomp7.setBounds (45, 215, 100, 25);
        usertype.setBounds (45, 190, 100, 25);

    }

    public String getUsernametext() {
        return usernametext.getText();
    }

    public String getPasswordtext() {
        return passwordtext.getText();
    }

    public String getusertype() {
        return (String)jcomp7.getSelectedItem();
    }

    public void addRegister2Listener(ActionListener b) {
        registerbutton.addActionListener(b);
    }

    public void closeView() {
        frame.setVisible(false);
    }

    public static void main (String[] args) {
        RegisterGUI register = new RegisterGUI();
    }
}
