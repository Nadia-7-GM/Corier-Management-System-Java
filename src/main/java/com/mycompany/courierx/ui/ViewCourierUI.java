package com.mycompany.courierx.ui;

import com.mycompany.courierx.controller.CourierController;
import com.mycompany.courierx.model.Courier;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewCourierUI extends JFrame {

    JButton btnBack, btnDelete;
    JTable table;
    DefaultTableModel model;

    public ViewCourierUI() {

        setTitle("View Couriers");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);

        // ===== Theme Colors =====
        Color lightBrown = new Color(245, 240, 235);
        Color darkBrown = new Color(121, 85, 72);
        Color white = Color.WHITE;
        Color red = new Color(183, 28, 28);

        getContentPane().setBackground(lightBrown);

        // ===== Title =====
        JLabel lblTitle = new JLabel("All Couriers");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitle.setForeground(darkBrown);
        lblTitle.setBounds(550, 60, 400, 40);
        add(lblTitle);

        // ===== Table Columns =====
        String[] columns = {
                "ID", "Sender", "Sender Address",
                "Receiver", "Receiver Address",
                "Phone", "Status"
        };

        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        table.setRowHeight(28);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        // ===== Table Header Styling =====
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.getTableHeader().setBackground(darkBrown);
        table.getTableHeader().setForeground(white);

        // ===== Load Data =====
        loadCourierData();

        // ===== Center align ID and Status =====
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);

        // ===== ScrollPane =====
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(100, 140, 1150, 500);
        scroll.setBorder(BorderFactory.createLineBorder(darkBrown, 2));
        add(scroll);

        // ===== Back Button =====
        btnBack = new JButton("Back");
        btnBack.setBounds(50, 50, 100, 35);
        btnBack.setBackground(darkBrown);
        btnBack.setForeground(white);
        btnBack.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnBack.addActionListener(e -> {
            new DashboardUI();
            dispose();
        });
        add(btnBack);

        // ===== Delete Button =====
        btnDelete = new JButton("Delete");
        btnDelete.setBounds(170, 50, 120, 35);
        btnDelete.setBackground(red);
        btnDelete.setForeground(white);
        btnDelete.setFont(new Font("Segoe UI", Font.BOLD, 14));
        add(btnDelete);

        // ===== Delete Action =====
        btnDelete.addActionListener(e -> {

            int selectedRow = table.getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(
                        null,
                        "Please select a courier to delete",
                        "No Selection",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to delete this courier?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {

                int courierId = (int) model.getValueAt(selectedRow, 0);

                boolean deleted = CourierController.deleteCourier(courierId);

                if (deleted) {
                    JOptionPane.showMessageDialog(null, "Courier deleted successfully");
                    loadCourierData(); // refresh table
                } else {
                    JOptionPane.showMessageDialog(null, "Delete failed");
                }
            }
        });

        setVisible(true);
    }

    // ===== Load / Refresh Table Data =====
    private void loadCourierData() {
        model.setRowCount(0);
        List<Courier> couriers = CourierController.getAllCouriers();

        for (Courier c : couriers) {
            model.addRow(new Object[]{
                    c.getId(),
                    c.getSenderName(),
                    c.getSenderAddress(),
                    c.getReceiverName(),
                    c.getReceiverAddress(),
                    c.getPhone(),
                    c.getStatus()
            });
        }
    }
}
