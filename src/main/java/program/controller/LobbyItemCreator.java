package program.controller;

public class LobbyItemCreator {
    private String name;
    private String time;
    private String capacity;

    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }
    public LobbyItem createLobbyItem(StartController startController){
        return new LobbyItem(name,time,capacity, startController);
    }
    public void resetVariables(){
        name = null;
        time = null;
        capacity = null;
    }
}
