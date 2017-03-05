package spring.models.entity;

/**
 * Created by root on 02.03.17.
 */
public class User {
    private int uid;
    private String role;
    private String login;
    private String pass;
    private String email;


    public User() {
    }

    public User(String role, String login, String pass, String email) {
        this.role = role;
        this.login = login;
        this.pass = pass;
        this.email = email;
    }

    public User(int uid, String login, String pass, String email, String role) {
        this.uid = uid;
        this.role = role;
        this.login = login;
        this.pass = pass;
        this.email = email;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
