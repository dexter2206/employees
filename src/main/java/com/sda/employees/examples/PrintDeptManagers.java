package com.sda.employees.examples;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrintDeptManagers {

    static String query = "SELECT first_name, last_name, from_date, to_date " +
            "FROM departments d JOIN dept_manager dm ON d.dept_no = dm.dept_no " +
            "JOIN employees e ON dm.emp_no = e.emp_no " +
            "WHERE d.dept_no = ?";

    public static void main(String[] args) {
        try(Connection conn = ConnectionFactory.getConnection()) {
            printDeptManagers(conn, "d001");
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void printDeptManagers(Connection conn, String deptNo) {
        ResultSet rs = null;
        System.out.println("Managers for department no. " + deptNo);
        try(PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, deptNo);
            rs = stmt.executeQuery();
            while(rs.next()) {
                String endDate = rs.getString("to_date");
                if(endDate.equals("9999-01-01")) {
                    endDate = "present";
                }

                System.out.println(String.format("%s %s: %s - %s",
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("from_date"),
                        endDate));
            }

        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try { if(rs != null) { rs.close(); } } catch(Exception e) {}
        }
    }
}
