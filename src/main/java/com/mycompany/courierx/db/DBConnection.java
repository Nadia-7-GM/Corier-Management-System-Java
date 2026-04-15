package com.mycompany.courierx.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/courier_db"; // port 3306 check karo
    private static final String USER = "root";
    private static final String PASSWORD = "newpassword";

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  // ye driver load hona chahiye
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("DB Connected Successfully!"); // Debug line
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver missing!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check DB URL/User/Password.");
            e.printStackTrace();
        }
        return con;
    }

    public static void main(String[] args) {
        getConnection(); // Test connection
    }
}
