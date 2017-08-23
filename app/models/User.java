package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name="UserManager")
public class User {

    //Declaration vars
    @Id
    @Column(name = "login", unique = true, nullable = false)
    private String login;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name="Token")
    private String authToken;


    //Methods---------------------------------
    public String newToken() {
        authToken = UUID.randomUUID().toString();
        return authToken;
    }

    //Getters & Setters------------------------
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

    public String getAuthToken() {
        return authToken;
    }
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
    //-----------------------------------------
}
