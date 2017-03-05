package spring.models.dao;


import org.springframework.stereotype.Component;
import spring.common.exceptions.MoneyDaoException;
import spring.common.utils.Conn;
import spring.models.entity.Money;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 18.02.17.
 */
@Component
public class MoneyDao {
    private static Logger logger = Logger.getLogger(MoneyDao.class);

    private static final String SELECT_ALL_MONEY = "SELECT * FROM main.money";
    private static final String INSERT_MONEY = "INSERT INTO main.money (login,deposit,balance, note) VALUES(?,?,?,?)";

    public List<Money> selectAllMoney() throws MoneyDaoException {
        List<Money> list = new ArrayList<>();
        try {
            Connection conn = Conn.getConnection();
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
            throw new MoneyDaoException();
        }
        return list;
    }


    public boolean insert(Money money) throws MoneyDaoException {
        try {
            Connection conn = Conn.getConnection();
            PreparedStatement ps = conn.prepareStatement(INSERT_MONEY);
            ps.setString(1, money.getLogin());
            ps.setInt(2, money.getDeposit());
            ps.setInt(3, money.getBalance());
            ps.setString(4, money.getNote());
            int num = ps.executeUpdate();
            if (num > 0){
                return true;
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new MoneyDaoException();
        }
        return false;
    }

}
