package program.model;

import static org.junit.jupiter.api.Assertions.*;

import javafx.embed.swing.JFXPanel;
import javafx.scene.paint.Color;
import org.junit.Before;
import org.junit.Test;

public class TestSpace {

    private Player player2;
    private Player player1;
    private Space space1;

    @Before
    public void before(){

        JFXPanel jfxPanel = new JFXPanel(); // This is magic if I have ever seen it. Comment this away and errors will fly.

        player1 = new Player(10, 1, Color.color(1,0,0), "");
        player2 = new Player(15, 2, Color.color(0,0,1), "");
        space1 = new Space(1, player1,10, "Space");
    }

    @Test
    public void testUpdateSpace() {
        space1.updateSpace(20);
        assertEquals(space1.getUnits(), 20);
    }

    @Test
    public void testUpdateSpaceNewPlayer() {
        space1.updateSpace(player2,20);
        assertEquals(space1.getPlayer(), player2, "Player didn't change when space was updated");
    }
}
