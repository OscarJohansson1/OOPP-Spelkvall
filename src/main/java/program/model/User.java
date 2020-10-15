package program.model;

import java.io.Serializable;

/**
 * A class representing a User, with a corresponding name and image.
 */
public class User implements Serializable {

    private String imageUrl;
    private String userName;

    public User(String  userName, String imageUrl) {
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
