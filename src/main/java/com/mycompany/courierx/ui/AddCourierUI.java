package com.mycompany.courierx.ui;

import com.mycompany.courierx.controller.CourierController;
import com.mycompany.courierx.model.Courier;
import javax.swing.*;
import java.awt.*;

public class AddCourierUI extends JFrame {

    JTextField txtSenderName, txtSenderAddress, txtReceiverName, txtReceiverAddress, txtPhone;
    JButton btnAdd, btnBack;

    public AddCourierUI() {
        setTitle("Add Courier");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // fullscreen
        setLayout(null);

        // ===== Theme Colors =====
        Color lightBrown = new Color(245, 240, 235);
        Color darkBrown = new Color(121, 85, 72);
        Color white = Color.WHITE;

        getContentPane().setBackground(lightBrown);

        // ===== Title =====
        JLabel lblTitle = new JLabel("Add New Courier", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitle.setForeground(darkBrown);
        lblTitle.setBounds(450, 50, 400, 40);
        add(lblTitle);

        // ===== Labels & TextFields =====
        int lblX = 400, txtX = 550;
        int yStart = 150, yStep = 60;
        int width = 200, height = 25;

        JLabel lblSender = new JLabel("Sender Name:", SwingConstants.RIGHT);
        lblSender.setForeground(darkBrown);
        lblSender.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblSender.setBounds(lblX, yStart, 140, height);
        txtSenderName = new JTextField();
        txtSenderName.setBounds(txtX, yStart, width, height);

        JLabel lblSenderAddr = new JLabel("Sender Address:", SwingConstants.RIGHT);
        lblSenderAddr.setForeground(darkBrown);
        lblSenderAddr.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblSenderAddr.setBounds(lblX, yStart + yStep, 140, height);
        txtSenderAddress = new JTextField();
        txtSenderAddress.setBounds(txtX, yStart + yStep, width, height);

        JLabel lblReceiver = new JLabel("Receiver Name:", SwingConstants.RIGHT);
        lblReceiver.setForeground(darkBrown);
        lblReceiver.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblReceiver.setBounds(lblX, yStart + 2*yStep, 140, height);
        txtReceiverName = new JTextField();
        txtReceiverName.setBounds(txtX, yStart + 2*yStep, width, height);

        JLabel lblReceiverAddr = new JLabel("Receiver Address:", SwingConstants.RIGHT);
        lblReceiverAddr.setForeground(darkBrown);
        lblReceiverAddr.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblReceiverAddr.setBounds(lblX, yStart + 3*yStep, 140, height);
        txtReceiverAddress = new JTextField();
        txtReceiverAddress.setBounds(txtX, yStart + 3*yStep, width, height);

        JLabel lblPhone = new JLabel("Phone:", SwingConstants.RIGHT);
        lblPhone.setForeground(darkBrown);
        lblPhone.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblPhone.setBounds(lblX, yStart + 4*yStep, 140, height);
        txtPhone = new JTextField();
        txtPhone.setBounds(txtX, yStart + 4*yStep, width, height);

        // ===== Buttons =====
        btnAdd = new JButton("Add Courier");
        btnAdd.setBounds(txtX, yStart + 5*yStep, width, 35);
        btnAdd.setBackground(darkBrown);
        btnAdd.setForeground(white);
        btnAdd.setFont(new Font("Segoe UI", Font.BOLD, 16));

        btnBack = new JButton("Back");
        btnBack.setBounds(50, 50, 100, 35);
        btnBack.setBackground(darkBrown);
        btnBack.setForeground(white);
        btnBack.setFont(new Font("Segoe UI", Font.BOLD, 14));

        // ===== Actions =====
        btnAdd.addActionListener(e -> {
            Courier c = new Courier();
            c.setSenderName(txtSenderName.getText());
            c.setSenderAddress(txtSenderAddress.getText());
            c.setReceiverName(txtReceiverName.getText());
            c.setReceiverAddress(txtReceiverAddress.getText());
            c.setPhone(txtPhone.getText());

            boolean added = CourierController.addCourier(c);
            if (added) {
                JOptionPane.showMessageDialog(this, "Courier Added Successfully!");
                new DashboardUI();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error Adding Courier!");
            }
        });

        btnBack.addActionListener(e -> {
            new DashboardUI();
            dispose();
        });

        // ===== Add to Frame =====
        add(lblSender); add(txtSenderName);
        add(lblSenderAddr); add(txtSenderAddress);
        add(lblReceiver); add(txtReceiverName);
        add(lblReceiverAddr); add(txtReceiverAddress);
        add(lblPhone); add(txtPhone);
        add(btnAdd); add(btnBack);

        setVisible(true);
    }
}
