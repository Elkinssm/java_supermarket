package org.example.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final Logger logger = LoggerFactory.getLogger(DBConnection.class);

    private static final String URL = "jdbc:mysql://localhost:3306/supermercado?useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8&useUnicode=true";
    private static final String USER = "appuser";
    private static final String PASSWORD = "apppassword";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            logger.info("Driver MySQL loaded successfully.");
        } catch (ClassNotFoundException e) {
            logger.error("MySQL Driver not found.", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        logger.debug("Connecting to DB: {}", URL);
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
