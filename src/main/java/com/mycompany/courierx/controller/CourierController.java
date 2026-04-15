package com.mycompany.courierx.controller;

import com.mycompany.courierx.db.DBConnection;
import com.mycompany.courierx.model.Courier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CourierController {

    // ================= ADD COURIER =================
    public static boolean addCourier(Courier c) {
        String sql = "INSERT INTO courier VALUES (NULL, ?, ?, ?, ?, ?, 'Booked')";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getSenderName());
            ps.setString(2, c.getSenderAddress());
            ps.setString(3, c.getReceiverName());
            ps.setString(4, c.getReceiverAddress());
            ps.setString(5, c.getPhone());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ================= GET ALL COURIERS =================
    public static List<Courier> getAllCouriers() {
        List<Courier> list = new ArrayList<>();
        String sql = "SELECT * FROM courier";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Courier c = new Courier();
                c.setId(rs.getInt("courier_id"));
                c.setSenderName(rs.getString("sender_name"));
                c.setSenderAddress(rs.getString("sender_address"));
                c.setReceiverName(rs.getString("receiver_name"));
                c.setReceiverAddress(rs.getString("receiver_address"));
                c.setPhone(rs.getString("phone"));
                c.setStatus(rs.getString("status"));
                list.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // ================= UPDATE STATUS =================
    public static boolean updateStatus(int courierId, String status) {
        String sql = "UPDATE courier SET status=? WHERE courier_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, courierId);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ================= GET COURIER BY ID =================
    public static Courier getCourierById(int courierId) {
        String sql = "SELECT * FROM courier WHERE courier_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, courierId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Courier c = new Courier();
                c.setId(rs.getInt("courier_id"));
                c.setSenderName(rs.getString("sender_name"));
                c.setSenderAddress(rs.getString("sender_address"));
                c.setReceiverName(rs.getString("receiver_name"));
                c.setReceiverAddress(rs.getString("receiver_address"));
                c.setPhone(rs.getString("phone"));
                c.setStatus(rs.getString("status"));
                return c;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // ================= SEARCH COURIER =================
    public static Courier searchCourier(String keyword) {
        String sql;
        boolean isNumeric = keyword.matches("\\d+");

        if (isNumeric) {
            sql = "SELECT * FROM courier WHERE courier_id=?";
        } else {
            sql = "SELECT * FROM courier WHERE sender_name LIKE ?";
        }

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            if (isNumeric) {
                ps.setInt(1, Integer.parseInt(keyword));
            } else {
                ps.setString(1, "%" + keyword + "%");
            }

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Courier c = new Courier();
                c.setId(rs.getInt("courier_id"));
                c.setSenderName(rs.getString("sender_name"));
                c.setSenderAddress(rs.getString("sender_address"));
                c.setReceiverName(rs.getString("receiver_name"));
                c.setReceiverAddress(rs.getString("receiver_address"));
                c.setPhone(rs.getString("phone"));
                c.setStatus(rs.getString("status"));
                return c;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // ================= UPDATE FULL COURIER =================
    public static boolean updateCourier(Courier c) {
        String sql = "UPDATE courier SET sender_name=?, sender_address=?, receiver_name=?, receiver_address=?, phone=?, status=? WHERE courier_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getSenderName());
            ps.setString(2, c.getSenderAddress());
            ps.setString(3, c.getReceiverName());
            ps.setString(4, c.getReceiverAddress());
            ps.setString(5, c.getPhone());
            ps.setString(6, c.getStatus());
            ps.setInt(7, c.getId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // ================= DELETE COURIER + RESET ID =================
    // (University / Demo purpose)
    public static boolean deleteCourier(int courierId) {

        try (Connection con = DBConnection.getConnection()) {

            // Delete courier
            String deleteSQL = "DELETE FROM courier WHERE courier_id=?";
            PreparedStatement ps = con.prepareStatement(deleteSQL);
            ps.setInt(1, courierId);
            ps.executeUpdate();

            // Reset ID sequence
            Statement st = con.createStatement();
            st.executeUpdate("SET @count = 0");
            st.executeUpdate("UPDATE courier SET courier_id = (@count := @count + 1)");
            st.executeUpdate("ALTER TABLE courier AUTO_INCREMENT = 1");

            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}




//package com.mycompany.courierx.controller;
//
//import com.mycompany.courierx.db.DBConnection;
//import com.mycompany.courierx.model.Courier;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.ArrayList;
//import java.util.List;
//
//public class CourierController {
//
//    // Add new courier
//    public static boolean addCourier(Courier c) {
//        String sql = "INSERT INTO courier VALUES(null, ?, ?, ?, ?, ?, 'Booked')";
//        try (Connection con = DBConnection.getConnection();
//             PreparedStatement ps = con.prepareStatement(sql)) {
//
//            ps.setString(1, c.getSenderName());
//            ps.setString(2, c.getSenderAddress());
//            ps.setString(3, c.getReceiverName());
//            ps.setString(4, c.getReceiverAddress());
//            ps.setString(5, c.getPhone());
//
//            int rows = ps.executeUpdate();
//            return rows > 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    // Get all couriers
//    public static List<Courier> getAllCouriers() {
//        List<Courier> list = new ArrayList<>();
//        String sql = "SELECT * FROM courier";
//        try (Connection con = DBConnection.getConnection();
//             PreparedStatement ps = con.prepareStatement(sql);
//             ResultSet rs = ps.executeQuery()) {
//
//            while (rs.next()) {
//                Courier c = new Courier();
//                c.setId(rs.getInt("courier_id"));
//                c.setSenderName(rs.getString("sender_name"));
//                c.setSenderAddress(rs.getString("sender_address"));
//                c.setReceiverName(rs.getString("receiver_name"));
//                c.setReceiverAddress(rs.getString("receiver_address"));
//                c.setPhone(rs.getString("phone"));
//                c.setStatus(rs.getString("status"));
//
//                list.add(c);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return list;
//    }
//
//    // Update status only
//    public static boolean updateStatus(int courierId, String status) {
//        String sql = "UPDATE courier SET status=? WHERE courier_id=?";
//        try (Connection con = DBConnection.getConnection();
//             PreparedStatement ps = con.prepareStatement(sql)) {
//
//            ps.setString(1, status);
//            ps.setInt(2, courierId);
//
//            int rows = ps.executeUpdate();
//            return rows > 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    // Get courier by ID
//    public static Courier getCourierById(int courierId) {
//        String sql = "SELECT * FROM courier WHERE courier_id=?";
//        try (Connection con = DBConnection.getConnection();
//             PreparedStatement ps = con.prepareStatement(sql)) {
//
//            ps.setInt(1, courierId);
//            ResultSet rs = ps.executeQuery();
//
//            if (rs.next()) {
//                Courier c = new Courier();
//                c.setId(rs.getInt("courier_id"));
//                c.setSenderName(rs.getString("sender_name"));
//                c.setSenderAddress(rs.getString("sender_address"));
//                c.setReceiverName(rs.getString("receiver_name"));
//                c.setReceiverAddress(rs.getString("receiver_address"));
//                c.setPhone(rs.getString("phone"));
//                c.setStatus(rs.getString("status"));
//
//                return c;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    // 🔹 New Method 1: Search by ID or Sender Name
//    public static Courier searchCourier(String keyword) {
//        String sql;
//        boolean isNumeric = keyword.matches("\\d+");
//        if (isNumeric) {
//            sql = "SELECT * FROM courier WHERE courier_id=?";
//        } else {
//            sql = "SELECT * FROM courier WHERE sender_name LIKE ?";
//        }
//
//        try (Connection con = DBConnection.getConnection();
//             PreparedStatement ps = con.prepareStatement(sql)) {
//
//            if (isNumeric) {
//                ps.setInt(1, Integer.parseInt(keyword));
//            } else {
//                ps.setString(1, "%" + keyword + "%");
//            }
//
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                Courier c = new Courier();
//                c.setId(rs.getInt("courier_id"));
//                c.setSenderName(rs.getString("sender_name"));
//                c.setSenderAddress(rs.getString("sender_address"));
//                c.setReceiverName(rs.getString("receiver_name"));
//                c.setReceiverAddress(rs.getString("receiver_address"));
//                c.setPhone(rs.getString("phone"));
//                c.setStatus(rs.getString("status"));
//                return c;
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    // 🔹 New Method 2: Full update of courier
//    public static boolean updateCourier(Courier c) {
//        String sql = "UPDATE courier SET sender_name=?, sender_address=?, receiver_name=?, receiver_address=?, phone=?, status=? WHERE courier_id=?";
//        try (Connection con = DBConnection.getConnection();
//             PreparedStatement ps = con.prepareStatement(sql)) {
//
//            ps.setString(1, c.getSenderName());
//            ps.setString(2, c.getSenderAddress());
//            ps.setString(3, c.getReceiverName());
//            ps.setString(4, c.getReceiverAddress());
//            ps.setString(5, c.getPhone());
//            ps.setString(6, c.getStatus());
//            ps.setInt(7, c.getId());
//
//            return ps.executeUpdate() > 0;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//}
