package models.pojo;



import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.XmlElement;
import java.io.Serializable;


/**
 * Created by root on 18.02.17.
 */

@XmlType(propOrder = {"uid","login","pass", "role","money","email","notify"})
@XmlRootElement(name = "user")
public class User implements Serializable {

    private int uid;
    private String role;
    private String login;
    private String pass;
    private String email;
    private Money money;


   public User() {
    }


    public User(String login, String pass, String role) {
        this.role = role;
        this.login = login;
        this.pass = pass;
    }

    public User(int uid, String login, String pass, String role) {
        this.uid = uid;
        this.role = role;
        this.login = login;
        this.pass = pass;
    }

    public User(int uid,String login, String pass, String email, String role) {
        this.uid = uid;
        this.role = role;
        this.login = login;
        this.pass = pass;
        this.email = email;

    }

    public User(int uid,String login, String pass, String email, String role, Money money) {
        this.uid = uid;
        this.role = role;
        this.login = login;
        this.pass = pass;
        this.email = email;
        this.money = money;
    }

    public User(String login, String pass, String email, String role) {
        this.role = role;
        this.login = login;
        this.pass = pass;
        this.email = email;
    }

    public int getUid() {
        return uid;
    }


    @XmlElement
    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getRole() {
        return role;
    }

    @XmlElement
    public void setRole(String role) {
        this.role = role;
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


    public static String userToStr(User user){
       return user.getLogin();
    }

    public String getEmail() {
        return email;
    }

    @XmlElement
    public void setEmail(String email) {
        this.email = email;
    }


}



