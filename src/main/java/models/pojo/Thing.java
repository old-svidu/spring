package models.pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by root on 18.02.17.
 */
@XmlType(propOrder = {"tid","login","thing","price","prior"})
@XmlRootElement
public class Thing implements Serializable {

    private int tid;
    private String login;
    private String thing;
    private int price;
    private int prior;


    public Thing() {
    }

    public Thing(String login, String thing, int price, int prior) {
        this.login = login;
        this.thing = thing;
        this.price = price;
        this.prior = prior;
    }

    public Thing(int tid, String login, String thing, int price,int prior) {
        this.tid = tid;
        this.login = login;
        this.thing = thing;
        this.price = price;
        this.prior = prior;
    }

    public int getTid() {
        return tid;
    }

    @XmlElement
    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getLogin() {
        return login;
    }

    @XmlElement
    public void setLogin(String login) {
        this.login = login;
    }

    public String getThing() {
        return thing;
    }

    @XmlElement
    public void setThing(String thing) {
        this.thing = thing;
    }

    public int getPrice() {
        return price;
    }

    @XmlElement
    public void setPrice(int price) {
        this.price = price;
    }

   public int getPrior() {
        return prior;
    }

    @XmlElement
    public void setPrior(int prior) {
        this.prior = prior;
    }
}
