package models.dao;

import common.exceptions.UserDaoException;
import models.lists.Users;
import common.utils.Conn;
import main.Sets;
import models.pojo.Money;
import models.pojo.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.sql.Date;
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
    private static final String INSERT_USER = "INSERT INTO main.user (name, lname ,login,pass, grp, email) values(?,?,?,?,?,?)";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM main.user WHERE (id) = (?)";

    public static boolean insert(User user) {
        try {
            Connection conn = Conn.getInstance();

            PreparedStatement ps = conn.prepareStatement(INSERT_USER);

            ps.setString(1, user.getName());
            ps.setString(2, user.getLname());
            ps.setString(3, user.getLogin());
            ps.setString(4, user.getPass());
            ps.setInt(5, user.getGroup());
            ps.setString(6, user.getEmail());
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

                ps.setString(1, user.getName());
                ps.setString(2, user.getLname());
                ps.setString(3, user.getLogin());
                ps.setString(4, user.getPass());
                ps.setInt(5, user.getGroup());
                ps.setString(6, user.getEmail());
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
                String name = rs.getString("name");
                String lname = rs.getString("lname");
                String login = rs.getString("login");
                String pass = rs.getString("pass");
                int group = rs.getInt("grp");
                int mid = rs.getInt("mid");
                Date date = rs.getDate("mdate");
                int deposit = rs.getInt("deposit");
                int balance = rs.getInt("balance");


                Money money = new Money(mid, login, date, deposit, balance);
                User user = new User(name, lname, group, login, pass, money);
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
                String name = rs.getString("name");
                String lname = rs.getString("lname");
                String login = rs.getString("login");
                String pass = rs.getString("pass");
                int group = rs.getInt("grp");

                return new User(name, lname, group, login, pass);
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
                String name = rs.getString("name");
                String lname = rs.getString("lname");
                String lgn = rs.getString("login");
                String pwd = rs.getString("pass");
                int grp = rs.getInt("grp");
                String email = rs.getString("email");
                return new User(id, name, lname, grp, lgn, pwd, email);
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
                String name = rs.getString("name");
                String lname = rs.getString("lname");
                String lgn = rs.getString("login");
                String pwd = rs.getString("pass");
                int grp = rs.getInt("grp");
                String email = rs.getString("email");
                list.add( new User(id, name, lname, grp, lgn, pwd, email));
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return list;
    }

}

