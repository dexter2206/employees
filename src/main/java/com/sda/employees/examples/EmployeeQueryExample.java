package com.sda.employees.examples;

import java.sql.*;

public class EmployeeQueryExample {

    private static final String url = "jdbc:mysql://localhost/employees";
    private static final String user = "root";
    private static final String password = "sda";

    public static void main(String[] argv) {
        try(Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println(String.format("There are %d employees and %d departments in the database.",
                    getUsersCount(conn),
                    getDepartmentsCount(conn)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static int getUsersCount(Connection conn) throws SQLException {
        try(Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM employees");
            rs.next();
            return rs.getInt(1);
        }
    }

    private static int getDepartmentsCount(Connection conn) throws SQLException {
        try(Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as total FROM departments");
            rs.next();
            return rs.getInt("total");
        }
    }
}
