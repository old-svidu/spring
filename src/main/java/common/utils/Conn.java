package common.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by root on 19.02.17.
 */
public class Conn {


    public static final String URL = "jdbc:postgresql://127.0.0.1:5432/PurchaseProj";
    public static final String USER = "postgres";
    public static final String PASSWORD = "123456";

    private static Connection instance;

    private Conn() {

        try {
            instance = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            Log.logger.error(e);
        }
    }

    public static Connection getInstance() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            Log.logger.error(e);
        }
        if (instance == null) {
            new Conn();
        }
        return instance;
    }
}

