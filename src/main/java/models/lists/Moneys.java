package models.lists;

import models.dao.MoneyDao;
import common.utils.DataManager;
import models.pojo.Money;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * Created by root on 19.02.17.
 */
@XmlRootElement
public class Moneys implements Serializable {

    private List<Money> moneys;

    @XmlElement(name = "money")
    public void setList(List<Money> list) {
        this.moneys = list;
    }

    public List<Money> getList() {
        return moneys;
    }

    public void selectAll(){
        this.setList(MoneyDao.selectAllMoney());
    }

    public static void insert(){
        MoneyDao.insert(DataManager.deserialize("models.lists.Moneys.xml"));
    }

}

