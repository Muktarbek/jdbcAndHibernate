package peaksoft.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import peaksoft.model.User;
import peaksoft.util.Util;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
            try {
                Session session = Util.getSessionFactory().openSession();
                session.beginTransaction();
                session.createSQLQuery("CREATE TABLE IF NOT EXISTS user_dao (\n" +
                        "    id serial primary key,\n" +
                        "    name varchar(255) not null,\n" +
                        "    lastName varchar(255) not null,\n" +
                        "    age integer not null \n" +
                        ");").executeUpdate();
                session.getTransaction().commit();
                session.close();
            }catch (Exception ex){
                System.out.println(ex.getMessage());

            }
            System.out.println("Create table succesfully ");
        }

    @Override
    public void dropUsersTable() {
        try {
            Session session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS user_dao").executeUpdate();
            session.getTransaction().commit();
            session.close();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        System.out.println("Drop tables successfully");
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            User user = new User(name,lastName,age);
            Session session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            session.close();
            System.out.println(user.getName() + " is successfully saved!");
        }catch (Exception ex ){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void removeUserById(long id) {

         try {
             Session session = Util.getSessionFactory().openSession();
             session.beginTransaction();
             User user = session.get(User.class, id);
             if (user != null) {
                 session.delete(user);
             }
             session.getTransaction().commit();
             session.close();
             System.out.println(user +"    deleted");
         }catch (Exception ex){
             System.out.println(ex.getMessage());
         }
    }

    @Override
    public List<User> getAllUsers() {
  try {
      Session session = Util.getSessionFactory().openSession();
      session.beginTransaction();
      List<User> users = session.createQuery("from User").list() ;
      session.getTransaction();
      session.close();
      return users;
  }catch (Exception ex){
      System.out.println(ex.getMessage());
      return null;
  }
    }

    @Override
    public void cleanUsersTable() {
        try {
            Session session = Util.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("delete from User");
            query.executeUpdate();
            session.getTransaction().commit();
            session.close();
            System.out.println("All employees were deleted");
        } catch(Exception e) {
            e.getMessage();
        }
    }
}
