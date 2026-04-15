package com.mycompany.courierx.ui;

import com.mycompany.courierx.controller.CourierController;
import com.mycompany.courierx.model.Courier;
import javax.swing.*;
import java.awt.*;

public class TrackCourierUI extends JFrame {

    JTextField txtId;
    JButton btnTrack, btnBack;
    JTextArea txtInfo;

    public TrackCourierUI() {

        setTitle("Track Courier");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);

        // 🎨 Theme Colors
        Color brown = new Color(121, 85, 72);
        Color lightBrown = new Color(215, 204, 200);
        Color white = Color.WHITE;

        // 🧱 Page Border
        getRootPane().setBorder(
                BorderFactory.createLineBorder(brown, 6)
        );

        getContentPane().setBackground(white);

        // 🔹 Title
        JLabel lblTitle = new JLabel("Track Courier by ID");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitle.setForeground(brown);
        lblTitle.setBounds(500, 120, 400, 35);

        // 🔹 ID Label
        JLabel lblId = new JLabel("Courier ID:");
        lblId.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblId.setForeground(brown);
        lblId.setBounds(420, 200, 80, 25);

        // 🔹 ID Field
        txtId = new JTextField();
        txtId.setBounds(500, 200, 200, 28);
        txtId.setBorder(BorderFactory.createLineBorder(brown, 2));

        // 🔹 Track Button
        btnTrack = new JButton("Track");
        btnTrack.setBounds(500, 245, 200, 35);
        btnTrack.setBackground(brown);
        btnTrack.setForeground(white);
        btnTrack.setFocusPainted(false);

        // 🔹 Back Button
        btnBack = new JButton("Back");
        btnBack.setBounds(50, 50, 100, 35);
        btnBack.setBackground(lightBrown);
        btnBack.setForeground(brown);
        btnBack.setFocusPainted(false);

        // 🔹 Info Area
        txtInfo = new JTextArea();
        txtInfo.setBounds(420, 310, 450, 240);
        txtInfo.setEditable(false);
        txtInfo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtInfo.setBackground(lightBrown);
        txtInfo.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(brown, 2),
                        BorderFactory.createEmptyBorder(10, 10, 10, 10)
                )
        );

        // 🔎 Track action
        btnTrack.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                Courier c = CourierController.getCourierById(id);

                if (c != null) {
                    txtInfo.setText(
                            "Courier ID: " + c.getId() + "\n\n" +
                            "Sender Name: " + c.getSenderName() + "\n" +
                            "Sender Address: " + c.getSenderAddress() + "\n\n" +
                            "Receiver Name: " + c.getReceiverName() + "\n" +
                            "Receiver Address: " + c.getReceiverAddress() + "\n\n" +
                            "Phone: " + c.getPhone() + "\n" +
                            "Status: " + c.getStatus()
                    );
                } else {
                    txtInfo.setText("❌ Courier not found!");
                }

            } catch (NumberFormatException ex) {
                txtInfo.setText("⚠️ Please enter a valid numeric ID!");
            }
        });

        // 🔙 Back action
        btnBack.addActionListener(e -> {
            new DashboardUI();
            dispose();
        });

        add(lblTitle);
        add(lblId);
        add(txtId);
        add(btnTrack);
        add(btnBack);
        add(txtInfo);

        setVisible(true);
    }
}
