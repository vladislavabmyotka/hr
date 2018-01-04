package com.epam.abmyotka.hr.dao;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class DBPool {
    private final static Logger LOGGER = LogManager.getLogger(DBPool.class);

    private static DBPool instance;
    private static BlockingQueue<Connection> pool = new ArrayBlockingQueue<>(16, true);

    private DBPool() {
        ResourceBundle resource = ResourceBundle.getBundle("database");
        String url = resource.getString("db.url");
        String user = resource.getString("db.user");
        String pass = resource.getString("db.password");

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.ERROR, "Driver not found exception! Detail: " + e.getMessage());
        }
        for (int i = 0; i < 8; i++) {
            try {
                pool.add(DriverManager.getConnection(url, user, pass));
            } catch (SQLException e) {
                LOGGER.log(Level.ERROR, "Error initializing the connection pool! Detail: " + e.getMessage());
            }
        }
    }

    public static synchronized DBPool getInstance() {
        if (instance == null) {
            instance = new DBPool();
        }
        return instance;
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = pool.take();
        } catch (InterruptedException e) {
            LOGGER.log(Level.ERROR, "Error while taking connection from connection pool! Detail: " + e.getMessage());
        }
        return connection;
    }

    public void putConnection(Connection connection) {
        if (connection != null) {
            try {
                pool.put(connection);
            } catch (InterruptedException e) {
                LOGGER.log(Level.ERROR, "Error when returning connection to connection pool! Detail: " +
                        e.getMessage());
            }
        }
    }
}
