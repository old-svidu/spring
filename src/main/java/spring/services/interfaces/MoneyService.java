package spring.services.interfaces;

import spring.common.exceptions.MoneyDaoException;
import spring.models.entity.Money;

import java.util.List;

/**
 * Created by root on 02.03.17.
 */
public interface MoneyService {
    List<Money> selectAllMoney() throws MoneyDaoException;
    boolean isMoneyInserted(Money money) throws MoneyDaoException;
}
