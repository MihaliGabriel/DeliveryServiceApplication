package BusinessLogic;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Contine utilizatorii care se logheaza in aplicatie.
 * Este serializabila.
 */

public class User implements Serializable {
    private static final long serialversionUID =
            129348938L;
    private int user_id;
    private String username;
    private String password;
    private String user_type;

    public User() {

    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.user_type = new String();
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public int getUser_id() {
        return user_id;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }
    @Override
    public String toString() {
        return "" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", user_type='" + user_type + '\'';
    }
}
