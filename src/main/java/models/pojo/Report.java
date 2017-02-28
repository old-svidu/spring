package models.pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * Created by root on 18.02.17.
 */
@XmlType(propOrder = {"rid","login","thing","status"})
@XmlRootElement
public class Report implements Serializable {

    private int rid;
    private String login;
    private String thing;
    private String status;


    public Report() {
    }

    public Report(String login, String thing, String status) {
        this.login = login;
        this.thing = thing;
        this.status = status;

    }

    public Report(int rid, String login, String thing, String status) {
        this.rid = rid;
        this.login = login;
        this.thing = thing;
        this.status = status;

    }

    public int getRid() {
        return rid;
    }

    @XmlElement
    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public String getStatus() {
        return status;
    }

    @XmlElement
    public void setStatus(String status) {
        this.status = status;
    }

    public String getThing() {
        return thing;
    }

    @XmlElement
    public void setThing(String thing) {
        this.thing = thing;
    }


}
