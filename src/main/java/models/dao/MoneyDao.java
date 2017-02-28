package models.dao;


import models.lists.Moneys;
import common.utils.Conn;
import main.Sets;
import models.pojo.Money;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 18.02.17.
 */
public class MoneyDao {
    private static Logger logger = Logger.getLogger(MoneyDao.class);

    private static final String SELECT_ALL_MONEY = "SELECT * FROM main.money";
    private static final String INSERT_MONEY = "INSERT INTO main.money (login,deposit,balance, note) VALUES(?,?,?,?)";

    public static List<Money> selectAllMoney() {
        List<Money> list = new ArrayList<>();
        try {
            Connection conn = Conn.getInstance();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(SELECT_ALL_MONEY);
            while (rs.next()) {
                int mid = rs.getInt("mid");
                String login = rs.getString("login");
                int deposit = rs.getInt("deposit");
                int balance = rs.getInt("balance");
                String note = rs.getString("note");
                Money money = new Money(mid, login, deposit, balance, note);
                list.add(money);
            }

        } catch (SQLException e) {
            logger.error(e);
        }
        return list;
    }

    public static void insert(Object obj) {
        Moneys moneys = (Moneys) obj;
        try {

            Connection conn = Conn.getInstance();

            PreparedStatement ps = conn.prepareStatement(INSERT_MONEY);

            for (Money money : moneys.getList()) {
                synchronized (Sets.loginsSet) {
                while (!Sets.loginsSet.contains(money.getLogin())) {
                    Thread.yield();
                }

                if (Sets.moneySet.contains(Money.moneyToStr(money))) continue;
                    Sets.moneySet.add(Money.moneyToStr(money));

                    ps.setString(1, money.getLogin());
                    ps.setInt(2, money.getDeposit());
                    ps.setInt(3, money.getBalance());
                    ps.setString(4,money.getNote());
                    ps.executeUpdate();


                }
            }

        } catch (SQLException e) {
            logger.error(e);
        }
    }

    public static void insert(Money money) {
        try {
            synchronized (Sets.moneySet) {
                if (Sets.moneySet.contains(Money.moneyToStr(money))) return;
                Sets.moneySet.add(Money.moneyToStr(money));

                Connection conn = Conn.getInstance();
                PreparedStatement ps = conn.prepareStatement(INSERT_MONEY);
                ps.setString(1, money.getLogin());
                ps.setInt(2, money.getDeposit());
                ps.setInt(3, money.getBalance());
                ps.setString(4, money.getNote());
                ps.executeUpdate();

            }

        } catch (SQLException e) {
            logger.error(e);
        }
    }

}
