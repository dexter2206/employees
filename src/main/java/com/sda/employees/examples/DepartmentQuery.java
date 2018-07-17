package com.sda.employees.examples;

import java.sql.*;

/**
 * Created by dexter on 16.07.18.
 */
public class DepartmentQuery {

    public static void main(String[] args) {
        Statement stmt = null;
        ResultSet rs = null;
        try(Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/employees",
                "root",
                "sda"))
        {
            stmt = conn.createStatement();
            // Ilość departamentów
            rs = stmt.executeQuery("SELECT COUNT(*) from departments");
            if(rs.next()) {
                System.out.println("Ilość deparamentów: " + rs.getString(1));
            }
            rs.close();
            rs = stmt.executeQuery("SELECT dept_no, dept_name FROM departments");
            while(rs.next()) {
                System.out.println(String.format("Dept %s: %s",
                        rs.getString("dept_no"),
                        rs.getString("dept_name")));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
