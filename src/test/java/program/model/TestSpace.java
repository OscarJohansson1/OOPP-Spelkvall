package program.model;

import static org.junit.jupiter.api.Assertions.*;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;

public class TestSpace {

    private Player player1;
    private Player player2;
    private Space space1;
    private Space space2;


    @Before
    public void before(){

        player1 = new Player(10, 1, "#123123", "hej", "hej");
        player2 = new Player(15, 2, "#123123", "hej", "hej");
        space1 = new Space(1, player1,10, "FirstSpace");
        space2 = new Space(2, player2,10, "SecondSpace");
    }

    @Test
    public void testUpdateSpaceNewUnits() {
        space1.updateSpace(20);
        assertEquals(space1.getUnits(), 20, "Units didn't change when space was updated");
    }

    @Test
    public void testUpdateSpaceNewPlayer() {
        space1.updateSpace(player2,20);
        assertEquals(player2.getId(), space1.getPlayerId(), "Player didn't change when space was updated");
    }

    @Test
    public void testUpdateSpaceNewSpace(){
        space1.updateSpace(space2);
        assertEquals(space2.getId(), space1.getId(), "Id didn't change when space was updated");
        assertEquals(space2.getPlayerId(), space1.getPlayerId(), "Player didn't change when space was updated");
        assertEquals(space2.getUnits(), space1.getUnits(), "Units didn't change when space was updated");
        assertEquals(space2.getName(), space1.getName(), "Name didn't change when space was updated");
    }
}
