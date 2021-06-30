
package peaksoft.util;


import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import org.hibernate.cfg.Configuration;

import org.hibernate.service.ServiceRegistry;
import peaksoft.model.User;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Util {
    // реализуйте настройку соеденения с БД

    //SQL connection
    private static final String url = "jdbc:postgresql://localhost:5432/Practice1";
    private static final String user = "postgres";
    private static final String password = "0411";

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    //Hibernate connection
    private static final SessionFactory sessionFactory = buildSessionFactory();
    private static SessionFactory buildSessionFactory() {
        try{
            return new Configuration().setProperty("hibernate.connection.driver_class","org.postgresql.Driver")
                    .setProperty("hibernate.connection.url","jdbc:postgresql://localhost:5432/Practice1")
                    .setProperty("hibernate.connection.username","postgres")
                    .setProperty("hibernate.connection.password","0411")
                    .setProperty("hibernate.dialect","org.hibernate.dialect.PostgreSQL9Dialect")
//                    .setProperty("hibernate.hbm2ddl.auto","create")
                    .setProperty("show_sql","true").addAnnotatedClass(User.class)
                    .buildSessionFactory();
        }catch (Throwable ex) {
            System.out.println("Session not created " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
        public static void shutDown () {
            getSessionFactory().close();
        }}

