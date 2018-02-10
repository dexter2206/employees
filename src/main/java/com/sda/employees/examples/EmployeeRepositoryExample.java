package com.sda.employees.examples;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import com.sda.employees.repository.EmployeeRepository;
import com.sda.employees.repository.EmployeeSqlRepository;

public class EmployeeRepositoryExample {

    public static void main(String[] argv) {
        MysqlConnectionPoolDataSource ds = new MysqlConnectionPoolDataSource();
        ds.setUrl( "jdbc:mysql://localhost/employees" );
        ds.setUser("root");
        ds.setPassword("sda");
        EmployeeRepository repository = new EmployeeSqlRepository(ds);
        repository.findAll().stream().limit(10).forEach(System.out::println);
    }
}
