package spring.models.entity;

/**
 * Created by root on 02.03.17.
 */
public class Money {
    private int mid;
    private String login;
    private int deposit;
    private int balance;
    private String note;

    public Money() {
    }

    public Money(String login, int deposit, String note) {
        this.login = login;
        this.deposit = deposit;
        this.note = note;
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

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
