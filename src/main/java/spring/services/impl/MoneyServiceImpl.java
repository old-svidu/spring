package spring.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.common.exceptions.MoneyDaoException;
import spring.models.dao.MoneyDao;
import spring.models.entity.Money;
import spring.services.interfaces.MoneyService;

import java.util.List;

/**
 * Created by root on 02.03.17.
 */
@Service
public class MoneyServiceImpl implements MoneyService{

    private MoneyDao moneyDao;
    @Autowired
    public void setMoneyDao(MoneyDao moneyDao) {
        this.moneyDao = moneyDao;
    }

    @Override
    public boolean isMoneyInserted(Money money) throws MoneyDaoException {
        return moneyDao.insert(money);
    }

    @Override
    public List<Money> selectAllMoney() throws MoneyDaoException {
        return moneyDao.selectAllMoney();
    }

}
