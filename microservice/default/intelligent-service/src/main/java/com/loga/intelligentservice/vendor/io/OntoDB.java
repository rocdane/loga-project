package com.loga.intelligentservice.vendor.io;

import java.sql.Connection;
import java.sql.DriverManager;

public class OntoDB
{
    private static Connection connection;

    public OntoDB() {
        final String DRIVER = "org.postgresql.Driver";
        final String JDBC_URL = "jdbc:postgresql://localhost:5432/loga";
        final String USERNAME = "postgres";
        final String PASSWORD = "Log@gmc+";

        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static Connection getConnection() {
        return connection;
    }
}