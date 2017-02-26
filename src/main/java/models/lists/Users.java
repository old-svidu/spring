package models.lists;

import models.dao.UserDao;
import common.utils.DataManager;
import models.pojo.User;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * Created by root on 18.02.17.
 */
@XmlRootElement
public class Users implements Serializable{
    private List<User> users;

    public Users() {
    }

    @XmlElement(name = "user")
    public void setList(List<User> users) {
        this.users = users;
    }

    public List<User> getList() {
        return users;
    }

    public void selectAll(){
        this.setList(UserDao.selectAll());
    }

    public void insert(){
       UserDao.insert(DataManager.deserialize("models.lists.Users.xml"));
    }




}
