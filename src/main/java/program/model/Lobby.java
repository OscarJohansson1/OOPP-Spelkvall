package program.model;

import java.text.DateFormat;
import java.util.Date;


public class Lobby {

    String lobbyName;
    Date lobbyTime;
    int lobbyCapacity; //Vet servern om

    public Lobby(){

    }

    public Date getLobbyTime() {
        return lobbyTime;
    }

    public String getLobbyName() {
        return lobbyName;
    }

    public int getLobbyCapacity() {
        return lobbyCapacity;
    }

    /*
    DateFormat date = new SimpleDateFormat("yyyy/MM/dd - HH:mm");
    this.orderDateLabel.setText(date.format(order.getDate()));
    //method 2 - via Date
    Date date = new Date();
        System.out.println(new Timestamp(date.getTime()));

    import java.text.SimpleDateFormat;
    import java.util.Date;
         */



}
