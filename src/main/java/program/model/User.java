package program.model;

import javafx.scene.image.Image;

import java.io.Serializable;

public class User implements Serializable {

    String imageUrl;
    String userName;

    public User(String  userName, String imageUrl){
        this.userName = userName;
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getUserName() {
        return userName;
    }
}
