package spring.models.entity;

/**
 * Created by root on 02.03.17.
 */
public class Thing {
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

    public Thing(int tid, String login, String thing, int price, int prior) {
        this.tid = tid;
        this.login = login;
        this.thing = thing;
        this.price = price;
        this.prior = prior;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrior() {
        return prior;
    }

    public void setPrior(int prior) {
        this.prior = prior;
    }
}
