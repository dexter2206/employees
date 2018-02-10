package com.sda.employees.dao;

import com.sda.employees.model.Employee;
import com.sda.employees.model.Gender;

import java.sql.*;

public class EmployeeSqlDao implements EmployeeDao {

    private Connection conn;
    private static final String empQuery = "SELECT * FROM employees WHERE emp_no = ?";
    private static final String insertStmt = "INSERT INTO employees(emp_no, birth_date, first_name, last_name, gender, hire_date) " +
            "VALUES(?, ?, ?, ?, ?, ?)";
    private static final String deleteStmt = "DELETE FROM employees WHERE emp_no = ?";
    private static final String updateStmt = "UPDATE employees SET birth_date = ?, first_name = ?, last_name = ?, " +
            "gender = ?, hire_date = ? WHERE emp_no = ?";

    public EmployeeSqlDao(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Employee get(Integer empId) throws SQLException {
        try(PreparedStatement stmt = conn.prepareStatement(empQuery)) {
            stmt.setInt(1, empId);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                return new Employee(empId,
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getDate("birth_date"),
                        rs.getDate("hire_date"),
                        Gender.fromInitial(rs.getString("gender")));
            } else {
                throw new RuntimeException("No Employee with given ID found.");
            }
        }
    }

    @Override
    public void add(Employee newEmp) throws SQLException {
        try(PreparedStatement insert = conn.prepareStatement(insertStmt)) {
            insert.setInt(1, newEmp.getId());
            insert.setDate(2, newEmp.getBirthDate());
            insert.setDate(6, newEmp.getHireDate());
            insert.setString(3, newEmp.getFirstName());
            insert.setString(4, newEmp.getLastName());
            if (newEmp.getGender() == Gender.FEMALE) {
                insert.setString(5, "F");
            } else {
                insert.setString(5, "M");
            }
            if(insert.executeUpdate() != 1) {
                throw new RuntimeException("Failed to add Employee!");
            }
        }
    }

    @Override
    public void delete(Employee emp) throws SQLException {
        try(PreparedStatement delete = conn.prepareStatement(deleteStmt)) {
            delete.setInt(1, emp.getId());
            if(delete.executeUpdate() == 0) {
                throw new RuntimeException("No such employee in the database.");
            }
        }
    }

    @Override
    public void update(Employee emp) throws SQLException {
        try(PreparedStatement update = conn.prepareStatement(updateStmt)) {
            update.setDate(1, emp.getBirthDate());
            update.setString(2, emp.getFirstName());
            update.setString(3, emp.getLastName());
            if (emp.getGender() == Gender.FEMALE) {
                update.setString(4, "F");
            } else {
                update.setString(4, "M");
            }
            update.setDate(5, emp.getHireDate());
            update.setInt(6, emp.getId());

            if(update.executeUpdate() != 1) {
                throw new RuntimeException("Update failed.");
            }
        }
    }
}
