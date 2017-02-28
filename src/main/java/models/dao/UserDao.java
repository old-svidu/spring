package models.dao;

import common.exceptions.UserDaoException;
import models.lists.Users;
import common.utils.Conn;
import main.Sets;
import models.pojo.Money;
import models.pojo.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.*;

/**
 * Created by root on 18.02.17.
 */
public class UserDao {
    private static Logger logger = Logger.getLogger(UserDao.class);

    private static final String SELECT_ALL_USERS = "SELECT * FROM main.user";
    private static final String SELECT_USER_JOIN_MONEY = "SELECT * FROM main.user mu "
            + "LEFT JOIN main.money mm ON mu.login = mm.login";
    private static final String SELECT_USER_BY_LOGIN_PWD = "SELECT * FROM main.user WHERE login=(?) AND pass=(?)";
    private static final String INSERT_USER = "INSERT INTO main.user (login, pass, email, role) values(?,?,?,?)";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM main.user WHERE (id) = (?)";
    private static final String EMAILS_FOR_NOTIFICATION = "SELECT * FROM main.user WHERE role = 'admin' AND notify='true' AND id <> (?)";
    private static final String CHANGE_NOTIFICATION = "UPDATE main.user SET notify=(?) WHERE id = (?)";

    public static boolean insert(User user) {
        try {
            Connection conn = Conn.getInstance();

            PreparedStatement ps = conn.prepareStatement(INSERT_USER);

            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPass());
            ps.setString(3, user.getEmail());
            ps.setString(4,user.getRole());
            int count = ps.executeUpdate();
            if (count > 0){
                return true;
            }

        } catch (SQLException e) {
            logger.error(e);
        }
        return false;
    }

    public static void insert(Object obj) {
        Users users = (Users) obj;
        try {
            Connection conn = Conn.getInstance();
            PreparedStatement ps = conn.prepareStatement(INSERT_USER);

            for (User user : users.getList()) {
                if (Sets.loginsSet.contains(user.getLogin())) {
                    if (Sets.moneySet.contains(Money.moneyToStr(user.getMoney()))) {
                        continue;
                    } else {
                        MoneyDao.insert(user.getMoney());
                    }
                    continue;
                }

                ps.setString(1, user.getLogin());
                ps.setString(2, user.getPass());
                ps.setString(3, user.getEmail());
                ps.setString(4, user.getRole());
                ps.executeUpdate();

                if (Sets.moneySet.contains(Money.moneyToStr(user.getMoney()))) {
                    continue;
                } else {
                    MoneyDao.insert(user.getMoney());
                }

                Sets.loginsSet.add(user.getLogin());
            }
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    public static List<User> selectAll() {
        ArrayList<User> list = new ArrayList<>();
        try {
            Connection conn = Conn.getInstance();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(SELECT_USER_JOIN_MONEY);
            while (rs.next()) {
                int id = rs.getInt("id");

                String login = rs.getString("login");
                String pass = rs.getString("pass");
                String email = rs.getString("email");
                String role = rs.getString("role");
                int mid = rs.getInt("mid");
                int deposit = rs.getInt("deposit");
                int balance = rs.getInt("balance");
                String note = rs.getString("note");


                Money money = new Money(mid, login, deposit, balance, note);
                User user = new User(id, login, pass, email, role, money);
                list.add(user);
            }

        } catch (SQLException e) {
            logger.error(e);
        }
        return list;
    }

    public static User selectUserById(int id) {
        User user = null;
        try {
            Connection conn = Conn.getInstance();

            PreparedStatement ps = conn.prepareStatement(SELECT_USER_BY_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int uid = rs.getInt("id");
                String login = rs.getString("login");
                String pass = rs.getString("pass");
                String email = rs.getString("email");
                String role  = rs.getString("role");

                return new User(uid,login, pass, email, role);
            }

        } catch (SQLException e) {
            logger.error(e);
        }
        return user;
    }

    public static void deleteAll() {
        try {
            Connection conn = Conn.getInstance();
            String query = "DELETE FROM main.user ";
            Statement st = conn.createStatement();
            st.execute(query);
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    public static User getUserByLoginAndPassword(String login, String password) throws UserDaoException {
        User user = new User();
        try {
            Connection conn = Conn.getInstance();
            PreparedStatement ps = conn.prepareStatement(SELECT_USER_BY_LOGIN_PWD);
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String lgn = rs.getString("login");
                String pwd = rs.getString("pass");
                String role = rs.getString("role");
                String email = rs.getString("email");

                return new User(id, lgn, pwd, email, role);
            }

        } catch (SQLException e) {
            logger.error(e);
            throw new UserDaoException();
        }
        return user;
    }

    public static List<User> selectAllUsers(){
        List<User> list = new ArrayList<>();
        try {
            Connection conn = Conn.getInstance();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(SELECT_ALL_USERS);
            while (rs.next()){
                int id = rs.getInt("id");
                String lgn = rs.getString("login");
                String pwd = rs.getString("pass");
                String email = rs.getString("email");
                String role = rs.getString("role");

                list.add( new User(id, lgn, pwd, email, role));
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return list;
    }

    public static List<String> selectEmailsForNotification(int id){
        List<String> adminEmails = new ArrayList<>();
        try {
            Connection conn = Conn.getInstance();
            PreparedStatement ps = conn.prepareStatement(EMAILS_FOR_NOTIFICATION);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                adminEmails.add(rs.getString("email"));
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return adminEmails;
    }

    public static void notifyAdmin(String notify, int id){
        try {
            Connection conn = Conn.getInstance();
            PreparedStatement ps = conn.prepareStatement(CHANGE_NOTIFICATION);
            ps.setString(1,notify);
            ps.setInt(2,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
        }

    }

}

