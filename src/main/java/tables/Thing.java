package tables;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by root on 18.02.17.
 */
@XmlType(propOrder = {"tid","login","thing","price","tdate","prior"})
@XmlRootElement
public class Thing implements Serializable {

    private int tid;
    private String login;
    private String thing;
    private int price;
    private Date tdate;
    private int prior;


    public Thing() {
    }

    public Thing(String login, String thing, int tprice, Date tdate, int prior) {
        this.login = login;
        this.thing = thing;
        this.price = tprice;
        this.tdate = tdate;
        this.prior = prior;
    }

    public Thing(int tid, String login, String thing, int cost, Date tdate, int prior) {
        this.tid = tid;
        this.login = login;
        this.thing = thing;
        this.price = cost;
        this.tdate = tdate;
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

    public Date getTdate() {
        return tdate;
    }

    @XmlElement
    public void setTdate(Date tdate) {
        this.tdate = tdate;
    }

    public int getPrior() {
        return prior;
    }

    @XmlElement
    public void setPrior(int prior) {
        this.prior = prior;
    }
}
