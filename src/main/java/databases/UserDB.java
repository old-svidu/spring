package databases;

import lists.Users;
import utils.Conn;
import main.Sets;
import tables.Money;
import tables.User;
import utils.Log;

import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * Created by root on 18.02.17.
 */
public class UserDB {

    public UserDB() {
    }

    public static void insert(User user) {
        try {
            Connection conn = Conn.getInstance();
            String sql = "INSERT INTO main.user (name, lname ,login,pass,grp) values(?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, user.getName());
            ps.setString(2, user.getLname());
            ps.setString(3, user.getLogin());
            ps.setString(4, user.getPass());
            ps.setInt(5, user.getGroup());
            ps.executeUpdate();

        } catch (SQLException e) {
            Log.logger.error(e);
        }
    }

    public static void insert(Object obj) {
        Users users = (Users) obj;
        try {
            Connection conn = Conn.getInstance();
            String sql = "INSERT INTO main.user (name, lname ,login,pass, grp) values(?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            for (User user : users.getList()) {
                if (Sets.userSet.contains(user)) {
                    if (Sets.moneySet.contains(Money.moneyToStr(user.getMoney()))) {
                        continue;
                    } else {
                        MoneyDB.insert(user.getMoney());
                    }
                    continue;
                }

                ps.setString(1, user.getName());
                ps.setString(2, user.getLname());
                ps.setString(3, user.getLogin());
                ps.setString(4, user.getPass());
                ps.setInt(5, user.getGroup());
                ps.executeUpdate();

                if (Sets.moneySet.contains(Money.moneyToStr(user.getMoney()))) {
                    continue;
                } else {
                    MoneyDB.insert(user.getMoney());
                }

                Sets.userSet.add(user);
                Sets.loginsSet.add(user.getLogin());
            }
        } catch (SQLException e) {
            Log.logger.error(e);
        }
    }

    public static List<User> selectAll() {
        ArrayList<User> list = new ArrayList<>();
        try {
            Connection conn = Conn.getInstance();
            String query = "SELECT * FROM main.user mu "
                    + "LEFT JOIN main.money mm ON mu.login = mm.login";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
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
            Log.logger.error(e);
        }
        return list;
    }

    public static User selectById(int id) {
        User user = null;
        try {
            Connection conn = Conn.getInstance();
            String query = "SELECT * FROM main.user WHERE (id) = (?)";
            PreparedStatement ps = conn.prepareStatement(query);
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
            Log.logger.error(e);
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
            Log.logger.error(e);
        }
    }

}
