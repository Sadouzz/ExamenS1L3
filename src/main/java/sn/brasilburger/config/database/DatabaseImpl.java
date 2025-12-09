package sn.brasilburger.config.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DatabaseImpl implements Database {
    private Connection connection;
    public static DatabaseImpl instance;

    public static DatabaseImpl getInstance(Map<String, String> config) {
        if (instance == null)
            instance = new DatabaseImpl(config);
        return instance;
    }

    public static DatabaseImpl getInstance(String driver, String url, String user, String pwd) {
        if (instance == null)
            instance = new DatabaseImpl(driver, url, user, pwd);
        return instance;
    }

    private DatabaseImpl(Map<String, String> config) {
        String driver = config.get("driver");
        String url = config.get("url");
        String user = config.get("user");
        String password = config.get("password");
        connection = openConnection(driver, url, user, password);
    }

    private DatabaseImpl(String driver, String url, String user, String pwd) {
        connection = openConnection(driver, url, user, pwd);
    }

    public Connection openConnection(String driver, String url, String user, String pwd) {
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, user, pwd);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }





}
