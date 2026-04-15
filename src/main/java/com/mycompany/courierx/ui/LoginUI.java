package com.mycompany.courierx.ui;

import com.mycompany.courierx.db.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginUI extends JFrame {

    JTextField username;
    JPasswordField password;
    JButton login, btnSignUp;

    public LoginUI() {
        setTitle("CourierX");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // fullscreen
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel lblTitle = new JLabel("THE NAME OF TRUST");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 35));
        lblTitle.setBounds(515, 160, 500, 40);

        JLabel lblUser = new JLabel("Username");
        lblUser.setBounds(600, 220, 400, 40);
        username = new JTextField();
        username.setBounds(600, 250, 200, 25);

        JLabel lblPass = new JLabel("Password");
        lblPass.setBounds(600, 280, 400, 40);
        password = new JPasswordField();
        password.setBounds(600, 310, 200, 25);

        login = new JButton("Login");
        login.setBounds(600, 370, 200, 35);
        login.setBackground(new Color(59,130,246));
        login.setForeground(Color.WHITE);

        btnSignUp = new JButton("Sign Up");
        btnSignUp.setBounds(600, 420, 200, 35);
        btnSignUp.setBackground(new Color(121, 85, 72));
        btnSignUp.setForeground(Color.WHITE);

        // ===== Login Action =====
        login.addActionListener(e -> {
            String user = username.getText().trim();
            String pass = new String(password.getPassword()).trim();

            if(user.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter username and password!");
                return;
            }

            try (Connection con = DBConnection.getConnection()) {
                PreparedStatement ps = con.prepareStatement(
                        "SELECT * FROM users WHERE username=? AND password=?"
                );
                ps.setString(1, user);
                ps.setString(2, pass);
                ResultSet rs = ps.executeQuery();

                if(rs.next()) {
                    // Login successful
                    JOptionPane.showMessageDialog(this, "Welcome, " + rs.getString("full_name") + "!");
                    new DashboardUI();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Credentials");
                }

            } catch(Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
            }
        });

        // ===== Sign Up Action =====
        btnSignUp.addActionListener(e -> {
            new SignUpUI();
            dispose();
        });

        add(lblTitle);
        add(lblUser); add(username);
        add(lblPass); add(password);
        add(login);
        add(btnSignUp);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
