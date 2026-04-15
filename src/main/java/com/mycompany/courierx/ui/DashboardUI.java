package com.mycompany.courierx.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DashboardUI extends JFrame {

    JButton btnAdd, btnView, btnTrack, btnUpdate, btnBack;

    public DashboardUI() {
        setTitle("CourierX - Dashboard");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);

        // ===== Colors =====
        Color lightBrown = new Color(245, 240, 235);
        Color darkBrown = new Color(121, 85, 72);
        Color white = Color.WHITE;
        Color panelBrown = new Color(205, 183, 158);

        getContentPane().setBackground(lightBrown);

        // ===== Title =====
        JLabel lblTitle = new JLabel("CourierX Dashboard", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 35));
        lblTitle.setForeground(darkBrown);
        lblTitle.setBounds(0, 20, 1280, 50); // full width
        add(lblTitle);

        // ===== Left Buttons Panel =====
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(null);
        leftPanel.setBackground(lightBrown);
        leftPanel.setBounds(50, 100, 300, 400);
        leftPanel.setBorder(BorderFactory.createLineBorder(darkBrown, 2));
        add(leftPanel);

        int btnWidth = 220, btnHeight = 50;
        int btnStartY = 20;
        int btnGap = 70;

        btnAdd = new JButton("Add Courier");
        btnAdd.setBounds(40, btnStartY, btnWidth, btnHeight);
        btnView = new JButton("View Couriers");
        btnView.setBounds(40, btnStartY + btnGap, btnWidth, btnHeight);
        btnTrack = new JButton("Track Courier");
        btnTrack.setBounds(40, btnStartY + 2 * btnGap, btnWidth, btnHeight);
        btnUpdate = new JButton("Update Status");
        btnUpdate.setBounds(40, btnStartY + 3 * btnGap, btnWidth, btnHeight);
        btnBack = new JButton("Logout");
        btnBack.setBounds(40, btnStartY + 4 * btnGap, btnWidth, btnHeight);

        JButton[] buttons = {btnAdd, btnView, btnTrack, btnUpdate, btnBack};
        for (JButton b : buttons) {
            b.setBackground(darkBrown);
            b.setForeground(white);
            b.setFont(new Font("Segoe UI", Font.BOLD, 16));
            b.setFocusPainted(false);

            // Simple hover effect
            b.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    b.setBackground(panelBrown);
                    b.setForeground(darkBrown);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    b.setBackground(darkBrown);
                    b.setForeground(white);
                }
            });

            leftPanel.add(b);
        }

        // ===== Right Panel / Box Stack =====
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(null);
        rightPanel.setBackground(lightBrown);
        rightPanel.setBounds(400, 100, 800, 500);
        add(rightPanel);

        // Decorative stacked boxes
        for (int i = 0; i < 3; i++) {
            JPanel box = new JPanel();
            box.setLayout(new BorderLayout());
            box.setBackground(panelBrown);
            box.setBounds(50, 20 + i * 160, 700, 140);
            box.setBorder(BorderFactory.createLineBorder(darkBrown, 2));

            JLabel lblBoxTitle = new JLabel("", SwingConstants.CENTER);
            lblBoxTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
            lblBoxTitle.setForeground(darkBrown);

            switch (i) {
                case 0: lblBoxTitle.setText("📦 Total Couriers"); break;
                case 1: lblBoxTitle.setText("🚚 Couriers In Transit"); break;
                case 2: lblBoxTitle.setText("✅ Delivered Couriers"); break;
            }

            box.add(lblBoxTitle, BorderLayout.CENTER);
            rightPanel.add(box);
        }

        // ===== Button Actions =====
        btnAdd.addActionListener(e -> {
            new AddCourierUI();
            dispose();
        });
        btnView.addActionListener(e -> {
            new ViewCourierUI();
            dispose();
        });
        btnTrack.addActionListener(e -> {
            new TrackCourierUI();
            dispose();
        });
        btnUpdate.addActionListener(e -> {
            new UpdateStatusUI();
            dispose();
        });
        btnBack.addActionListener(e -> {
            new LoginUI(); // back to login
            dispose();
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
