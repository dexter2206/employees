package com.sda.employees.dao;

import com.sda.employees.model.Employee;

import java.sql.SQLException;

public interface EmployeeDao {

    Employee get(Integer empId) throws SQLException;
    void add(Employee newEmp) throws SQLException;
    void delete(Employee emp) throws SQLException;
    void update(Employee emp) throws SQLException;
}
