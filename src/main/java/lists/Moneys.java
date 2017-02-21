package lists;

import databases.MoneyDB;
import utils.DataManager;
import tables.Money;

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
        this.setList(MoneyDB.selectAll());
    }

    public static void insert(){
        MoneyDB.insert(DataManager.deserialize("lists.Moneys.xml"));
    }

}

