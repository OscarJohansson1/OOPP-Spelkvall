package server.code.model;

import org.junit.Test;
import program.model.Player;

import static org.junit.Assert.assertEquals;

/**
 * Test for lobby, gameloby and menulobby
 */
public class TestLobby {


    /**
     * Tests if the method "addPlayer" in Lobby adds the players to the list in correct order and if the names are correct
     */
    @Test
    public void testAddPlayer() {
        Lobby lobby = new MenuLobby("yes");
        lobby.addPlayer(new Player(0, 0, "", "", "yesman"));
        lobby.addPlayer(new Player(0, 0, "", "", "noman"));
        lobby.addPlayer(new Player(0, 0, "", "", "man3"));
        lobby.addPlayer(new Player(0, 0, "", "", "man4"));
        assertEquals(4, lobby.getLobbyPlayers().size());
        assertEquals("noman", lobby.getLobbyPlayers().get(1).getName());
    }

    /**
     * Tests if the method "nextPlayer" in GameLobby correctly switches to the next player in the list
     */
    @Test
    public void testNextPlayer() {
        GameLobby gameLobby = new GameLobby("yes");
        gameLobby.addPlayer(new Player(0, 0, "", "", "yesman"));
        gameLobby.addPlayer(new Player(0, 1, "", "", "noman"));
        gameLobby.addPlayer(new Player(0, 2, "", "", "man3"));
        gameLobby.addPlayer(new Player(0, 3, "", "", "man4"));
        gameLobby.setCurrentPlayer(gameLobby.getLobbyPlayers().get(0));
        gameLobby.nextPlayer();
        assertEquals("noman", gameLobby.getCurrentPlayer().getName());
        gameLobby.nextPlayer();
        assertEquals("man3", gameLobby.getCurrentPlayer().getName());
        gameLobby.nextPlayer();
        assertEquals("man4", gameLobby.getCurrentPlayer().getName());
        gameLobby.nextPlayer();
        assertEquals("yesman", gameLobby.getCurrentPlayer().getName());
    }

    /**
     * Tests if the method "checkIfAllPlayersReady" in MenuLobby correctly checks if all the players are ready
     */
    @Test
    public void testCheckIfAllPlayersReady() {
        MenuLobby menuLobby = new MenuLobby("yes");
        menuLobby.addPlayer(new Player(0, 0, "", "", "yesman"));
        menuLobby.addPlayer(new Player(0, 1, "", "", "noman"));
        menuLobby.addPlayer(new Player(0, 2, "", "", "man3"));
        menuLobby.addPlayer(new Player(0, 3, "", "", "man4"));
        assertEquals(false, menuLobby.checkIfAllPlayersReady());
        menuLobby.getLobbyPlayers().get(0).setReady(true);
        menuLobby.getLobbyPlayers().get(1).setReady(true);
        menuLobby.getLobbyPlayers().get(2).setReady(true);
        menuLobby.getLobbyPlayers().get(3).setReady(true);
        assertEquals(true, menuLobby.checkIfAllPlayersReady());

    }
}
