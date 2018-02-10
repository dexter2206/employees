package com.sda.employees.repository;

import com.sda.employees.model.Employee;
import com.sda.employees.model.Gender;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeSqlRepository implements EmployeeRepository{

    private DataSource ds;

    public EmployeeSqlRepository(DataSource ds) {
        this.ds = ds;
    }

    public List<Employee> findAll() {
        try(Connection conn = ds.getConnection(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM employees");
            return readEmployeeList(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("SQL error has occured.");
        }
    }

    public List<Employee> findByLastName(String lastName) {
        String query = "SELECT * FROM employee WHERE last_name = ?";
        try(Connection conn = ds.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, lastName);
            ResultSet rs = stmt.executeQuery();
            return readEmployeeList(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("SQL error has occured.");
        }
    }

    private List<Employee> readEmployeeList(ResultSet rs) {
        ArrayList<Employee> result = new ArrayList<>();
        try {
            while (rs.next()) result.add(readEmployee(rs));
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error when reading ResultSet.");
        }
    }

    private Employee readEmployee(ResultSet rs) {
        try {
            return new Employee(
                    rs.getInt("emp_no"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getDate("birth_date"),
                    rs.getDate("hire_date"),
                    Gender.fromInitial(rs.getString("gender")));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Invalid data in ResultSet; cannot read the Employee.");
        }
    }
}
