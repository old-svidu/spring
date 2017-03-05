package spring.models.entity;

/**
 * Created by root on 02.03.17.
 */
public class Report {
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

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getThing() {
        return thing;
    }

    public void setThing(String thing) {
        this.thing = thing;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
