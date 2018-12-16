package client.model;

import java.io.Serializable;

public class User implements Serializable {
    private String login;
    private String password;
    private int role;
    private String title;
    private int userId;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User(String login, String password, int role, String title) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.title= title;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public User(String login, String password, int role, String title, int userId) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.title = title;
        this.userId = userId;
    }
}
