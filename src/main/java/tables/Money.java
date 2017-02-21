package tables;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by root on 18.02.17.
 */
@XmlType(propOrder = {"mid","login","date","deposit","balance"})
@XmlRootElement
public class Money implements Serializable {

    private int mid;
    private String login;
    private Date date;
    private int deposit;
    private int balance;

    public Money(){}

    public Money(String login, int balance) {
        this.login = login;
        this.balance = balance;
    }

    public Money(String login, Date date, int deposit) {
        this.login = login;
        this.date = date;
        this.deposit = deposit;
    }

   public Money(int mid, String login, Date date, int deposit, int balance) {
        this.mid = mid;
        this.login = login;
        this.date = date;
        this.deposit = deposit;
        this.balance = balance;
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

    public Date getDate() {
        return date;
    }

    @XmlElement
    public void setDate(Date date) {
        this.date = date;
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


    public static String moneyToStr(Money money){
        return ""+money.getMid()+money.getLogin();
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

        Money money = (Money) obj;
        if (this.login.equals(money.getLogin()) && this.getDate() == money.getDate()
                &&this.balance==money.balance && this.getDeposit() == money.getDeposit()
                && this.getMid()==money.getMid()){
            return true;
        }
        return false;
    }
}


