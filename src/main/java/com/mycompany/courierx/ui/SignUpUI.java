package com.mycompany.courierx.ui;

import com.mycompany.courierx.db.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SignUpUI extends JFrame {

    JTextField txtFullName, txtUsername;
    JPasswordField txtPassword, txtConfirm;
    JButton btnSignUp, btnBack;

    public SignUpUI() {
        setTitle("Sign Up - The Name of Trust");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // ===== Title =====
        JLabel lblTitle = new JLabel("Create New Account", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 30));
        lblTitle.setBounds(0, 80, 1280, 40);
        lblTitle.setForeground(new Color(121, 85, 72));
        add(lblTitle);

        // ===== Full Name =====
        JLabel lblName = new JLabel("Full Name:");
        lblName.setBounds(500, 160, 120, 25);
        lblName.setFont(new Font("Segoe UI", Font.BOLD, 16));
        txtFullName = new JTextField();
        txtFullName.setBounds(650, 160, 250, 25);

        // ===== Username =====
        JLabel lblUser = new JLabel("Username:");
        lblUser.setBounds(500, 210, 120, 25);
        lblUser.setFont(new Font("Segoe UI", Font.BOLD, 16));
        txtUsername = new JTextField();
        txtUsername.setBounds(650, 210, 250, 25);

        // ===== Password =====
        JLabel lblPass = new JLabel("Password:");
        lblPass.setBounds(500, 260, 120, 25);
        lblPass.setFont(new Font("Segoe UI", Font.BOLD, 16));
        txtPassword = new JPasswordField();
        txtPassword.setBounds(650, 260, 250, 25);

        // ===== Confirm Password =====
        JLabel lblConfirm = new JLabel("Confirm Password:");
        lblConfirm.setBounds(500, 310, 140, 25);
        lblConfirm.setFont(new Font("Segoe UI", Font.BOLD, 16));
        txtConfirm = new JPasswordField();
        txtConfirm.setBounds(650, 310, 250, 25);

        // ===== Sign Up Button =====
        btnSignUp = new JButton("Sign Up");
        btnSignUp.setBounds(650, 360, 250, 35);
        btnSignUp.setBackground(new Color(59, 130, 246));
        btnSignUp.setForeground(Color.WHITE);
        btnSignUp.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnSignUp.setFocusPainted(false);

        btnSignUp.addActionListener(e -> signUpAction());

        // ===== Back Button =====
        btnBack = new JButton("Back");
        btnBack.setBounds(50, 50, 100, 35);
        btnBack.setBackground(new Color(121, 85, 72));
        btnBack.setForeground(Color.WHITE);
        btnBack.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnBack.setFocusPainted(false);

        btnBack.addActionListener(e -> {
            new LoginUI();
            dispose();
        });

        // ===== Add Components =====
        add(lblName); add(txtFullName);
        add(lblUser); add(txtUsername);
        add(lblPass); add(txtPassword);
        add(lblConfirm); add(txtConfirm);
        add(btnSignUp);
        add(btnBack);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    // ===== Sign Up Logic =====
    private void signUpAction() {
        String fullName = txtFullName.getText().trim();
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword());
        String confirm = new String(txtConfirm.getPassword());

        if(fullName.isEmpty() || username.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields!");
            return;
        }

        if(!password.equals(confirm)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match!");
            return;
        }

        try (Connection con = DBConnection.getConnection()) {

            // Check if username exists
            PreparedStatement check = con.prepareStatement("SELECT * FROM users WHERE username=?");
            check.setString(1, username);
            ResultSet rs = check.executeQuery();
            if(rs.next()) {
                JOptionPane.showMessageDialog(this, "Username already exists!");
                return;
            }

            // Insert new user
            PreparedStatement insert = con.prepareStatement(
                    "INSERT INTO users (username, password, full_name) VALUES (?, ?, ?)"
            );
            insert.setString(1, username);
            insert.setString(2, password);
            insert.setString(3, fullName);

            int rows = insert.executeUpdate();
            if(rows > 0) {
                JOptionPane.showMessageDialog(this, "Account created successfully!");
                new LoginUI();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error creating account!");
            }

        } catch(Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage());
        }
    }
}
