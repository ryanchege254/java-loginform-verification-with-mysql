package com.voting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class superadmin extends JPanel{
    Connection con;
    Statement st;
    ResultSet rs;

    JLabel username = new JLabel(new ImageIcon("/home/ryan/Pictures/project/user3.jpg"));
JTextField usernametf = new JTextField();
JLabel pass = new JLabel(new ImageIcon("/home/ryan/Pictures/project/pass2.jpg"));
JLabel off = new JLabel("Switch off");
JButton login = new JButton(new ImageIcon("/home/ryan/Pictures/project/loginbt1.jpg"));

JButton back = new JButton("Back");
JPasswordField passwordField = new JPasswordField();
    JButton offbt = new JButton(new ImageIcon("/home/ryan/Pictures/project/power3.jpg"));

    String user = usernametf.getText();
    String passWord = passwordField.getPassword().toString();
    superadmin() {
        setBounds(300,100,1200,900);

        username.setBounds(320,190,45,40);
        usernametf.setBounds(420,190,120,30);

        pass.setBounds(320,280,45,45);
        passwordField.setBounds(420,280,120,30);

        offbt.setBounds(1100, 110, 50, 50);
        offbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
                try {
                    con.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        off.setBounds(1120,170,100,30);
        off.setForeground(Color.white);

        login.setBounds(430,350,100,35);

                login.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (usernametf.getText().length() == 0)  // Checking for empty field
                            JOptionPane.showMessageDialog(null, "Empty fields detected ! Please fill up all fields");
                        else if (passwordField.getPassword().length == 0)  // Checking for empty field
                            JOptionPane.showMessageDialog(null, "Empty fields detected ! Please fill up all fields");
                        else {

                            String userTf = usernametf.getText();   // Collecting the input
                            char[] passTf = passwordField.getPassword(); // Collecting the input
                            String pwd = String.copyValueOf(passTf);  // converting from array to string
                            if (validate_login(userTf, pwd)) {
                                // JOptionPane.showMessageDialog(null, "Correct Login Credentials");
                              swing.frame.setVisible(false);
                                new sidePane();


                            } else if (validate_login(userTf, pwd))
                                JOptionPane.showMessageDialog(null, "Incorrect Login Credentials");

                        }

                    }

                    }
                );



        setLayout(null);
        setBorder(BorderFactory.createLineBorder(Color.white.brighter()));
        setBackground(new Color(17,242,9));
        add(off);
        add(offbt);
        add(login);
        add(username);
        add(usernametf);
        add(passwordField);
        add(pass);
    }

    public boolean validate_login(String user, String passWord) {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Test", "root", "");

            String sql = "Select * from admin  where username=? and password=? ";

            st =con.createStatement();


            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, user);
            stmt.setString(2, passWord);
            rs = stmt.executeQuery();
//int count=0;

//while (rs.next()) {count =count +1;}
            if (rs.next()) {
                return true;
            } else {
                return false;
            }


        } catch (Exception ex) {
            ex.printStackTrace();
            return false;

        }


    }

}



