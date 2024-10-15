package com.example;

import java.sql.SQLException;

import com.example.database.MysqlConnection;

public class App {
    public static void main(String[] args) throws SQLException {
        System.out.println("Hello World!");
        System.out.println(MysqlConnection.getConnection());
    }
}
