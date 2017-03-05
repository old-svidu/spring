package spring.models.dao;

import spring.common.exceptions.UserDaoException;

import spring.common.utils.Conn;
import spring.models.entity.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.*;

/**
 * Created by root on 18.02.17.
 */

@Component
public class UserDao {
    private static Logger logger = Logger.getLogger(UserDao.class);

    private static final String SELECT_ALL_USERS = "SELECT * FROM main.user";

    private static final String SELECT_USER_BY_LOGIN_PWD = "SELECT * FROM main.user WHERE login=? AND pass=?";
    private static final String INSERT_USER = "INSERT INTO main.user (login, pass, email, role) values(?,?,?,?)";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM main.user WHERE id = ?";
    private static final String SELECT_USER_BY_LOGIN = "SELECT * FROM main.user WHERE login = ?";
    private static final String DELETE_USER_BY_ID = "DELETE FROM main.user WHERE id = ?";
    private static final String UPDATE_USER_BY_ID = "UPDATE main.user SET(login, pass, email) = (?,?,?) WHERE id = ?";

    public boolean insert(User user) throws UserDaoException {
        try {
            Connection conn = Conn.getConnection();
            PreparedStatement ps = conn.prepareStatement(INSERT_USER);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPass());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getRole());
            int count = ps.executeUpdate();
            if (count > 0){
                return true;
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new UserDaoException();
        }
        return false;
    }

    public User selectUserById(int id) throws UserDaoException {
        User user = null;
        try {
            Connection conn = Conn.getConnection();
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
            throw new UserDaoException();
        }
        return user;
    }

    public void deleteAll() throws UserDaoException {
        try {
            Connection conn = Conn.getConnection();
            String query = "DELETE FROM main.user ";
            Statement st = conn.createStatement();
            st.execute(query);
        } catch (SQLException e) {
            logger.error(e);
            throw new UserDaoException();
        }
    }

    public User getUserByLoginAndPassword(String login, String password) throws UserDaoException {
        User user = new User();
        try {
            Connection conn = Conn.getConnection();
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

    public List<User> selectAllUsers() throws UserDaoException {
        List<User> list = new ArrayList<>();
        try {
            Connection conn = Conn.getConnection();
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
            throw new UserDaoException();
        }
        return list;
    }

    public boolean deleteUserById(int id) throws UserDaoException {
        try {
            Connection conn = Conn.getConnection();
            PreparedStatement ps = conn.prepareStatement(DELETE_USER_BY_ID);
            ps.setInt(1,id);
            int num = ps.executeUpdate();
            if (num > 0 ){
                return true;
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new UserDaoException();
        }
        return false;
    }

    public boolean updateUserById(int id, String login, String pass, String email) throws UserDaoException{
        try {
            Connection conn = Conn.getConnection();
            PreparedStatement ps = conn.prepareStatement(UPDATE_USER_BY_ID);
            ps.setString(1,login);
            ps.setString(2,pass);
            ps.setString(3,email);
            ps.setInt(4,id);
            int num = ps.executeUpdate();
            if (num > 0) {
                return true;
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new UserDaoException();
        }
        return false;
    }

    public boolean isUserWithLoginExist(String login) throws UserDaoException {
        try {
            Connection conn = Conn.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECT_USER_BY_LOGIN);
            ps.setString(1,login);
            int num = ps.executeUpdate();
            if (num > 0){
                return true;
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new UserDaoException();
        }
        return false;
    }

}

