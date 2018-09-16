package bean;

import java.io.Serializable;

/**
 * Created by Rubens Santos Barbosa
 */
public class User implements Serializable {

    private int id;
    private String name;
    private boolean connected = true;
    private boolean myTurn;

    // Construtor
    public User(String name) {
        this.name = name;
    }

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void disconnect() {
        this.connected = false;
    }

    public void connect() {
        this.connected = true;
    }

    public boolean isMyTurn() {
        return myTurn;
    }

    public void setMyTurn(boolean myTurn) {
        this.myTurn = myTurn;
    }

}
