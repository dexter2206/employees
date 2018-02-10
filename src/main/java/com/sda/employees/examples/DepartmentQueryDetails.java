package com.sda.employees.examples;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DepartmentQueryDetails {

    static final String url = "jdbc:mysql://localhost/employees";
    static final String user = "root";
    static final String password = "sda";
    static final String getDepartmentsQuery = "SELECT CONCAT(dept_no, \" \" , dept_name) AS dept FROM departments ORDER BY dept_no";
    static final String getNumberOfEmployeesQuery = "SELECT COUNT(*) FROM dept_emp WHERE dept_no = ? AND from_date <= ? AND (to_date >= ? OR to_date = '9999-01-01')";
    static final String getManagerQuery = "SELECT employees.first_name, employees.last_name FROM dept_manager " +
            "INNER JOIN employees ON employees.emp_no = dept_manager.emp_no " +
            "WHERE dept_manager.dept_no = ? AND from_date <= ? AND (to_date >= ? OR to_date = '9999-01-01')";
    public static void main(String[] argv) {
        try(Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Departments present in the DB:");
            List<String> deptNames = getDepartments(conn);
            deptNames.stream().map((name) -> "- " + name).forEach(System.out::println);

            System.out.print("Choose an id: ");
            Scanner in = new Scanner(System.in);
            String chosenNo = in.nextLine();
            System.out.print("Choose a day: ");
            Date date = Date.valueOf(in.nextLine());
            printDepartmentDetails(conn, date, chosenNo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static List<String> getDepartments(Connection conn) throws SQLException {
        List<String> deptNames = new ArrayList<>();
        try(Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(getDepartmentsQuery);
            while(rs.next()) {
                deptNames.add(rs.getString("dept"));
            }
        }
        return deptNames;
    }

    private static void printDepartmentDetails(Connection conn, Date date, String deptNo) throws SQLException {
        try(PreparedStatement ps = conn.prepareStatement(getNumberOfEmployeesQuery)) {
            ps.setString(1, deptNo);
            ps.setDate(2, date);
            ps.setDate(3, date);
            ResultSet rs = ps.executeQuery();
            rs.next();
            System.out.println("Number of employees: " + Integer.toString(rs.getInt(1)));
        }
        try(PreparedStatement ps = conn.prepareStatement(getManagerQuery)) {
            ps.setString(1, deptNo);
            ps.setDate(2, date);
            ps.setDate(3, date);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                System.out.println(String.format("Manager: %s %s", rs.getString("first_name"), rs.getString("last_name")));
            }
        }
    }
}
