package com.sda.employees.examples;

import java.sql.*;

public class EmployeeInsertDeleteExample {

    private static final String url = "jdbc:mysql://localhost/employees";
    private static final String user = "root";
    private static final String password = "sda";
    private static final String insertEmployeeQuery = "INSERT INTO employees(emp_no, birth_date, first_name, last_name, gender, hire_date) " +
            "VALUES(?, ?, ?, ?, ?, ?)";
    private static final String deleteEmployeeQuery = "DELETE FROM employees WHERE emp_no = ?";

    public static void main(String[] argv) throws SQLException{
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            try {
                conn.setAutoCommit(false);
                PreparedStatement insert = conn.prepareStatement(insertEmployeeQuery, PreparedStatement.RETURN_GENERATED_KEYS);
                insert.setInt(1, 512370);
                insert.setDate(2, Date.valueOf("1988-06-22"));
                insert.setDate(6, Date.valueOf("2018-02-10"));
                insert.setString(3, "Konrad");
                insert.setString(4, "Jałowiecki");
                insert.setString(5, "M");

                if(insert.executeUpdate() == 0) {
                    System.out.println("Failed to add a record.");
                    insert.close();
                    conn.rollback();
                    return;
                }
                insert.close();
                PreparedStatement delete = conn.prepareStatement(deleteEmployeeQuery);
                delete.setInt(1, 512370);
                if(delete.executeUpdate() == 0) {
                    conn.rollback();
                }
                delete.close();
            } catch(SQLException e) {
                e.printStackTrace();
                conn.rollback();
            }
            finally {
                conn.setAutoCommit(true);
            }
        }
    }
}
