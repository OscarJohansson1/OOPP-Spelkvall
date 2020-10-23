package program.controller;

/**
 * Class for creation of LobbyItems
 */
public class LobbyItemCreator {
    private String name;
    private String time;
    private String capacity;
    private String players;
    private boolean done = false;

    /**
     * Sets the remaining non set variables until all are set
     * @param message String that is related to one of the private strings
     */
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

    /**
     * if done then creates a new LobbyItem from the set strings in setVariables.
     * @return returns the created LobbyItem.
     */
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
