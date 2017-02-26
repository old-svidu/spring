package models.lists;

import models.dao.ThingDao;
import common.utils.DataManager;
import models.pojo.Thing;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * Created by root on 19.02.17.
 */
@XmlRootElement
public class Things implements Serializable {

    private List<Thing> things;

    @XmlElement(name = "thing")
    public void setList(List<Thing> list) {
        this.things = list;
    }

    public List<Thing> getList() {
        return things;
    }

    public void selectAll(){
        this.setList(ThingDao.selectAll());
    }

    public void insert(){
        ThingDao.insert(DataManager.deserialize("models.lists.Things.xml"));
    }

}
