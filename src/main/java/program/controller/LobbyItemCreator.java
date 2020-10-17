package program.controller;

public class LobbyItemCreator {
    private String name;
    private String time;
    private String capacity;
    private String players;

    public void setName(String name) {
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

    public String getPlayers() {
        return players;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public void setPlayers(String players) {
        this.players = players;
    }

    public LobbyItem createLobbyItem(StartController startController) {
        return new LobbyItem(name, time, capacity, players, startController);
    }

    public void resetVariables() {
        name = null;
        time = null;
        capacity = null;
    }
}
