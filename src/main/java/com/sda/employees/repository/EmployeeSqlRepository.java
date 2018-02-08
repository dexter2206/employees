package com.sda.employees.repository;

import javax.sql.DataSource;

public class EmployeeSqlRepository {

    private DataSource ds;

    public EmployeeSqlRepository(DataSource ds) {
        this.ds = ds;
    }
}
