package databases;


import lists.Moneys;
import utils.Conn;
import main.Sets;
import tables.Money;
import utils.Log;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by root on 18.02.17.
 */
public class MoneyDB {
    public static ArrayList<Money> selectAll() {
        ArrayList<Money> list = new ArrayList<>();
        try {
            Connection conn = Conn.getInstance();
            String query = "SELECT * FROM main.money";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int mid = rs.getInt("mid");
                String login = rs.getString("login");
                Date date = rs.getDate("mdate");
                int salary = rs.getInt("deposit");
                int balance = rs.getInt("balance");
                Money money = new Money(mid, login, date, salary, balance);
                list.add(money);
            }

        } catch (SQLException e) {
            Log.logger.error(e);
        }
        return list;
    }

    public static void insert(Object obj) {
        Moneys moneys = (Moneys) obj;
        try {

            Connection conn = Conn.getInstance();
            String sql = "INSERT INTO main.money (login,mdate,deposit,balance) VALUES(?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            for (Money money : moneys.getList()) {
                synchronized (Sets.loginsSet) {
                while (!Sets.loginsSet.contains(money.getLogin())) {
                    Thread.yield();
                }

                if (Sets.moneySet.contains(Money.moneyToStr(money))) continue;
                    Sets.moneySet.add(Money.moneyToStr(money));

                    ps.setString(1, money.getLogin());
                    ps.setDate(2, new Date(money.getDate().getTime()));
                    ps.setInt(3, money.getDeposit());
                    ps.setInt(4, money.getBalance());
                    ps.executeUpdate();


                }
            }

        } catch (SQLException e) {
            Log.logger.error(e);
        }
    }

    public static void insert(Money money) {
        try {
            synchronized (Sets.moneySet) {
                if (Sets.moneySet.contains(Money.moneyToStr(money))) return;
                Sets.moneySet.add(Money.moneyToStr(money));

                Connection conn = Conn.getInstance();
                String sql = "INSERT INTO main.money (login,mdate,deposit,balance) VALUES(?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, money.getLogin());
                ps.setDate(2, new Date(money.getDate().getTime()));
                ps.setInt(3, money.getDeposit());
                ps.setInt(4, money.getBalance());
                ps.executeUpdate();

            }

        } catch (SQLException e) {
            Log.logger.error(e);
        }
    }

}
