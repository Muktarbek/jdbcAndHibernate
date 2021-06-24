package peaksoft.dao;

import                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {

    Util util = new Util();

    public UserDaoJdbcImpl() {

    }

    public void createUsersTable() {
          String SQL = "CREATE TABLE IF NOT EXISTS user_dao (\n" +
                  "    id serial primary key,\n" +
                  "    name varchar(255) not null,\n" +
                  "    lastName varchar(255) not null,\n" +
                  "    age integer not null \n" +
                  ");";
          try (Connection conn = util.connect();
               Statement stmt = conn.createStatement()){
                stmt.executeQuery(SQL);
          }catch (Exception ex){
              System.out.println(ex.getMessage());
          }
    }

    public void dropUsersTable() {
       String SQL = "DROP TABLE IF EXISTS user_dao";

       try(Connection conn =util.connect();
            Statement stmt = conn.createStatement()){
           stmt.executeQuery(SQL);
       }
       catch (Exception ex ){
           System.out.println(ex.getMessage());
       }
    }

    public void saveUser(String name, String lastName, byte age) {
         String SQL ="INSERT INTO user_dao(name,lastName,age) VALUES(?,?,?)";

         try(Connection conn =util.connect();
             PreparedStatement prstmt = conn.prepareStatement(SQL)){
             prstmt.setString(1,name);
             prstmt.setString(2,lastName);
             prstmt.setByte(3,age);
             prstmt.executeUpdate();
             System.out.println(name+"  базага кашулду");
         } catch (Exception ex){
             System.out.println(ex.getMessage());
         }
    }

    public void removeUserById(long id) {

        String SQL = "DELETE  FROM  user_dao WHERE id = ?;";

        int m = 0;

        try(Connection conn = util.connect();
            PreparedStatement statement = conn.prepareStatement(SQL)){
            statement.setLong(1,id);
           m =  statement.executeUpdate();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

    }

    public List<User> getAllUsers() {

        String SQL = "SELECT * FROM user_dao";
        ArrayList<User> users = new ArrayList<>();
         User user = null;
        try(Connection conn = util.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL)){
            while (rs.next()){
              user = new User(rs.getInt("id"),rs.getString("name"),rs.getString("lastName"),(byte)rs.getInt("age"));
               users.add(user);
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return users;
    }

    public void cleanUsersTable() {
        String SQL = "TRUNCATE TABLE user_dao";
        try (Connection conn = util.connect();
             Statement stmt = conn.createStatement()){
             stmt.executeQuery(SQL);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

    }
}