package program.controller;

public class LobbyItemCreator {
    private String name;
    private String time;
    private String capacity;
    private String players;
    private boolean done = false;

    public void setVariables(String message) {
        if (name == null) {
            name = message;
        } else if (time == null) {
            time = message;
        } else if (capacity == null) {
            capacity = message;
        } else if (players == null) {
            players = message;
            done = true;
        }
    }

    public LobbyItem createLobbyItem(StartController startController) {
        if (done) {
            LobbyItem lobbyItem = new LobbyItem(name, time, capacity, players, startController);
            resetVariables();
            return lobbyItem;
        }
        return null;
    }

    private void resetVariables() {
        name = null;
        time = null;
        capacity = null;
        players = null;
        done = false;
    }
}
