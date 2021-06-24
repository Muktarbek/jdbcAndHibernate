package peaksoft.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private final String url = "jdbc:postgresql://localhost:5432/Practice1";
    private final String user = "postgres";
    private final String password = "0411";
    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager. getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage()) ;
        }
        return conn;
    }
}
