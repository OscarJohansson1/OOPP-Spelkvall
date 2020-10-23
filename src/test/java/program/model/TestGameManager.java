package program.model;

import javafx.scene.paint.Color;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for GameManager
 */
public class TestGameManager {

    Player player1;
    Player player2;
    Player player3;
    List<Player> players;

    GameManager gameManager = GameManager.getGameManager();

    /**
     * SetUp needed for testing GameManager
     */
    @Before
    public void before() {
        player1 = new Player(10, 0, "#123123", "hej", "hej");
        player2 = new Player(15, 1, "#123123", "hej", "hej");
        player3 = new Player(15, 2, "#123123", "hej", "hej");

        players = Arrays.asList(player1, player2, player3);
    }

    /**
     * Test for initialize
     */
    @Test
    public void testInitialize() {
        int n = 27;
        int player1 = 0;
        int player2 = 0;
        int player3 = 0;

        gameManager.initialize(n, new ArrayList<>(Arrays.asList("#676767",
                "#343434", "#DFDFDF")), new ArrayList<>(Arrays.asList(
                "it_logo", "a_logo", "d_logo"
        )));

        for (int i = 0; i < n; i++) {
            if ("#676767".equals(gameManager.getColorOnSpace(i))) {
                player1++;
            } else if ("#343434".equals(gameManager.getColorOnSpace(i))) {
                player2++;
            } else {
                player3++;
            }
        }
        assertTrue((Math.abs(player1 - player2) <= 1) && (Math.abs(player2 - player3) <= 1) && (Math.abs(player1 - player3) <= 1),
                "Players have: " + player1 + ", " + player2 + ", " + player3 + " spaces respective");
    }

    /**
     * Test for nextPlayer()
     */
    @Test
    public void testNextPlayer() {
        gameManager.setCurrentPlayer(player1);
        assertEquals(player1.getId(), gameManager.getCurrentPlayer().getId(), "Should be player1's turn but it isn't");
        gameManager.setCurrentPlayer(GameManager.nextPlayer(players, gameManager.getCurrentPlayer()));
        assertEquals(player2.getId(), gameManager.getCurrentPlayer().getId(), "Should be player2's turn but it isn't");
        gameManager.setCurrentPlayer(GameManager.nextPlayer(players, gameManager.getCurrentPlayer()));
        assertEquals(player3.getId(), gameManager.getCurrentPlayer().getId(), "Should be player3's turn but it isn't");
        gameManager.setCurrentPlayer(GameManager.nextPlayer(players, gameManager.getCurrentPlayer()));
        assertEquals(player1.getId(), gameManager.getCurrentPlayer().getId(), "Should be player1's turn but it isn't");
    }
}
