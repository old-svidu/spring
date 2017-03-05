package spring.common.utils;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by root on 19.02.17.
 */
public class Conn {
    private static Logger logger = Logger.getLogger(Conn.class);

    static Connection conn;
    static {
        try {
            Context context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/ISPP");
            conn = ds.getConnection();
        } catch (NamingException e) {
            logger.error(e);
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    public static Connection getConnection(){
        return conn;
    }
}

