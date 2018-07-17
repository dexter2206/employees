package com.sda.employees.repository;


import java.sql.Connection;
import java.sql.PreparedStatement;

public interface Specification {

    PreparedStatement toSql(Connection conn);
}
