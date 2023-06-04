package com.loga.microservices.datamanagementservices.vendor.io;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database
{
    private static Database instance;
    private static Connection connection;

    private Database() {
        final String DRIVER = "org.postgresql.Driver";
        final String JDBC_URL = "jdbc:postgresql://localhost:5432/loga_dwh";
        final String USERNAME = "loga_dwh";
        final String PASSWORD = "Log@gmc+";

        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Database getInstance(){
        if (instance == null)
            instance = new Database();
        return instance;
    }
    public static Connection getConnection() {
        return connection;
    }
}