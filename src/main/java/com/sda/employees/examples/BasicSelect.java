package com.sda.employees.examples;

import java.sql.*;

public class BasicSelect {

    public static void main(String[] args) {

        try(Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/employees",
                "root",
                "sda");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM employees LIMIT 10");) {
            while(rs.next()) {
                System.out.println(rs.getString("first_name"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
