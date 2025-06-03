package ru.netology.data;

import lombok.SneakyThrows;

import java.sql.*;

public class DbHelper {
    private static final String url = System.getProperty("db.url", "jdbc:mysql://localhost:3306/aqa");
    private static final String user = System.getProperty("db.user", "root");
    private static final String password = System.getProperty("db.password", "root");



    @SneakyThrows
    public static String getPaymentStatus() {
        String status = null;
        var sql = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1;";
        try (
                Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    status = rs.getString("status");
                }
            }
        }
        return status;
    }

    @SneakyThrows
    public static void clearTables() {
        try (
                Connection conn = DriverManager.getConnection(url, user, password);
                Statement stmt = conn.createStatement()
        ) {
            stmt.executeUpdate("DELETE FROM order_entity;");
            stmt.executeUpdate("DELETE FROM payment_entity;");
            stmt.executeUpdate("DELETE FROM credit_request_entity;");
        }
    }
    @SneakyThrows
    public static String getCreditStatus() {
        String status = null;
        var sql = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1;";
        try (
                Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    status = rs.getString("status");
                }
            }
        }
        return status;
    }
}
