package vendor.security;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database
{
    private Connection connection;
    
    public Database() {
        final String DRIVER = "org.h2.Driver";
        final String JDBC_URL = "jdbc:h2:./resources/loga";
        final String USERNAME = "vendor/config";
        final String PASSWORD = "Log@gmc+";

        try {
            Class.forName(DRIVER);
            this.connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Connection getConnection() {
        return this.connection;
    }
}