package tables;



import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;


/**
 * Created by root on 18.02.17.
 */

@XmlType(propOrder = {"uid","name","lname","login","pass","group","money"})
@XmlRootElement(name = "user")
public class User implements Serializable {

    private int uid;
    private String name;
    private String lname;
    private int group;
    private String login;
    private String pass;
    private Money money;


   public User() {
    }

    public User(String name, String lname, int group, String login, String pass) {
        this.name = name;
        this.lname = lname;
        this.group = group;
        this.login = login;
        this.pass = pass;
    }

    public User(String name, String lname, int group, String login, String pass, Money money) {
        this.name = name;
        this.lname = lname;
        this.group = group;
        this.login = login;
        this.pass = pass;
        this.money = money;
    }

    public User(int uid, String name, String lname, int group, String login, String pass, Money money) {
        this.uid = uid;
        this.name = name;
        this.lname = lname;
        this.group = group;
        this.login = login;
        this.pass = pass;
        this.money = money;
    }

    public int getUid() {
        return uid;
    }


    @XmlElement
    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public String getLname() {
        return lname;
    }

    @XmlElement
    public void setLname(String lname) {
        this.lname = lname;
    }

    public int getGroup() {
        return group;
    }

    @XmlElement
    public void setGroup(int group) {
        this.group = group;
    }

    public String getLogin() {
        return login;
    }

    @XmlElement
    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    @XmlElement
    public void setPass(String pass) {
        this.pass = pass;
    }

    public Money getMoney() {
        return money;
    }

    @XmlElement
    public void setMoney(Money moneys) {
        this.money = moneys;
    }

    @Override
    public int hashCode() {
        return login.hashCode()*21;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null){
            return false;
        }

        if (obj == this){
            return true;
        }

        if(!(obj instanceof User)){
            return false;
        }

        User user = (User) obj;
        if (this.login.equals(user.getLogin())){
            return true;
        }
        return false;
    }
}



