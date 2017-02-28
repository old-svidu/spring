package models.pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by root on 18.02.17.
 */
@XmlType(propOrder = {"mid","login","deposit","balance","note"})
@XmlRootElement
public class Money implements Serializable {

    private int mid;
    private String login;
    private int deposit;
    private int balance;
    private String note;

    public Money(){}

    public Money(String login, int deposit) {
        this.login = login;
        this.deposit = deposit;
    }

   public Money(int mid, String login, int deposit, int balance, String note) {
        this.mid = mid;
        this.login = login;
        this.deposit = deposit;
        this.balance = balance;
        this.note = note;
    }

    public int getMid() {
        return mid;
    }

    @XmlElement
    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getLogin() {
        return login;
    }

    @XmlElement
    public void setLogin(String login) {
        this.login = login;
    }

    public int getDeposit() {
        return deposit;
    }

    @XmlElement
    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public int getBalance() {
        return balance;
    }

    @XmlElement
    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getNote() {
        return note;
    }

    @XmlElement
    public void setNote(String note) {
        this.note = note;
    }

    public static String moneyToStr(Money money){
        return ""+money.getMid()+money.getLogin();
    }



}


