package com.mycompany.courierx.ui;

import com.mycompany.courierx.controller.CourierController;
import com.mycompany.courierx.model.Courier;

import javax.swing.*;
import java.awt.*;

public class UpdateStatusUI extends JFrame {

    JTextField txtSearch, txtSender, txtReceiver, txtPhone;
    JTextArea txtSenderAddr, txtReceiverAddr;
    JComboBox<String> comboStatus;
    JButton btnSearch, btnUpdate, btnBack;

    Courier currentCourier;

    public UpdateStatusUI() {

        // Background color / theme
        getContentPane().setBackground(new Color(245, 240, 235)); // light brownish
        setTitle("Update Courier");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);

        Color brown = new Color(121, 85, 72); // dark brown for buttons & border

        // ===== Title =====
        JLabel lblTitle = new JLabel("Search & Update Courier");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitle.setForeground(brown);
        lblTitle.setBounds(500, 50, 500, 35);
        add(lblTitle);

        // ===== Search =====
        JLabel lblSearch = new JLabel("Search by ID / Sender Name:");
        lblSearch.setBounds(400, 120, 200, 25);
        add(lblSearch);

        txtSearch = new JTextField();
        txtSearch.setBounds(600, 120, 200, 25);
        add(txtSearch);

        btnSearch = new JButton("Search");
        btnSearch.setBounds(820, 120, 100, 25);
        btnSearch.setBackground(brown);
        btnSearch.setForeground(Color.WHITE);
        add(btnSearch);

        // ===== Sender Name =====
        JLabel lblSender = new JLabel("Sender Name:");
        lblSender.setBounds(400, 170, 150, 25);
        add(lblSender);

        txtSender = new JTextField();
        txtSender.setBounds(600, 170, 200, 25);
        add(txtSender);

        // ===== Sender Address =====
        JLabel lblSenderAddr = new JLabel("Sender Address:");
        lblSenderAddr.setBounds(400, 210, 150, 25);
        add(lblSenderAddr);

        txtSenderAddr = new JTextArea();
        txtSenderAddr.setBounds(600, 210, 200, 50);
        txtSenderAddr.setBorder(BorderFactory.createLineBorder(brown, 1));
        add(txtSenderAddr);

        // ===== Receiver Name =====
        JLabel lblReceiver = new JLabel("Receiver Name:");
        lblReceiver.setBounds(400, 280, 150, 25);
        add(lblReceiver);

        txtReceiver = new JTextField();
        txtReceiver.setBounds(600, 280, 200, 25);
        add(txtReceiver);

        // ===== Receiver Address =====
        JLabel lblReceiverAddr = new JLabel("Receiver Address:");
        lblReceiverAddr.setBounds(400, 320, 150, 25);
        add(lblReceiverAddr);

        txtReceiverAddr = new JTextArea();
        txtReceiverAddr.setBounds(600, 320, 200, 50);
        txtReceiverAddr.setBorder(BorderFactory.createLineBorder(brown, 1));
        add(txtReceiverAddr);

        // ===== Phone =====
        JLabel lblPhone = new JLabel("Phone:");
        lblPhone.setBounds(400, 390, 150, 25);
        add(lblPhone);

        txtPhone = new JTextField();
        txtPhone.setBounds(600, 390, 200, 25);
        add(txtPhone);

        // ===== Status =====
        JLabel lblStatus = new JLabel("Status:");
        lblStatus.setBounds(400, 430, 150, 25);
        add(lblStatus);

        comboStatus = new JComboBox<>(new String[]{"Booked", "In Transit", "Delivered"});
        comboStatus.setBounds(600, 430, 200, 25);
        add(comboStatus);

        // ===== Buttons =====
        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(600, 480, 200, 30);
        btnUpdate.setBackground(brown);
        btnUpdate.setForeground(Color.WHITE);
        add(btnUpdate);

        btnBack = new JButton("Back");
        btnBack.setBounds(600, 520, 200, 30);
        btnBack.setBackground(brown);
        btnBack.setForeground(Color.WHITE);
        add(btnBack);

        // ===== Actions =====

        // 🔎 Search courier
        btnSearch.addActionListener(e -> {
            String keyword = txtSearch.getText().trim();
            if (keyword.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter ID or Sender Name!");
                return;
            }

            currentCourier = CourierController.searchCourier(keyword);

            if (currentCourier != null) {
                txtSender.setText(currentCourier.getSenderName());
                txtSenderAddr.setText(currentCourier.getSenderAddress());
                txtReceiver.setText(currentCourier.getReceiverName());
                txtReceiverAddr.setText(currentCourier.getReceiverAddress());
                txtPhone.setText(currentCourier.getPhone());
                comboStatus.setSelectedItem(currentCourier.getStatus());
            } else {
                JOptionPane.showMessageDialog(this, "Courier not found!");
            }
        });

        // 🔄 Update courier
        btnUpdate.addActionListener(e -> {
            if (currentCourier == null) {
                JOptionPane.showMessageDialog(this, "Search courier first!");
                return;
            }

            currentCourier.setSenderName(txtSender.getText());
            currentCourier.setSenderAddress(txtSenderAddr.getText());
            currentCourier.setReceiverName(txtReceiver.getText());
            currentCourier.setReceiverAddress(txtReceiverAddr.getText());
            currentCourier.setPhone(txtPhone.getText());
            currentCourier.setStatus((String) comboStatus.getSelectedItem());

            boolean updated = CourierController.updateCourier(currentCourier);

            if (updated) {
                JOptionPane.showMessageDialog(this, "Courier updated successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Update failed!");
            }
        });

        // 🔙 Back button
        btnBack.addActionListener(e -> {
            dispose();
            new DashboardUI();
        });

        setVisible(true);
    }
}
