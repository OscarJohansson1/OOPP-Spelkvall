package program.model;

import java.io.Serializable;

/**
 * A class representing a User, with a corresponding name and image.
 */
public class User implements Serializable {

    private String imageUrl;
    private String userName;
    private String color;
    private boolean ready = false;
    private boolean myTurn = false;

    public User(String  userName, String imageUrl, String color) {
        this.userName = userName;
        this.imageUrl = imageUrl;
        this.color = color;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getUserName() {
        return userName;
    }
    public boolean setReady(){
        return ready = true;
    }
    public String getColor(){
        return color;
    }
    public void setMyTurn(boolean myTurn){
        this.myTurn = myTurn;
    }
}
