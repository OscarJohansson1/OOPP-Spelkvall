package program.model;

import static org.junit.jupiter.api.Assertions.*;
import javafx.scene.paint.Color;
import org.junit.Before;
import org.junit.Test;

public class TestMovement {

    Movement movement;

    Player player1;
    Player player2;

    Space space1;
    Space space2;
    Space space3;

    @Before
    public void before(){

        player1 = new Player(10,1, Color.color(1, 0, 0));
        player2 = new Player(10,2, Color.color(0, 0, 1));

        space1 = new Space(1, player1, 5, "Test");
        space2 = new Space(2, player1, 3, "Test");

        movement = new Movement();
    }

    @Test
    public void testMovement() {
        // Rewrite test when movement has been implemented correctly
        movement.MoveUnits(space1, space2, 10);
        assertEquals(3, space1.getUnits(), "Didn't switch units with space2");
    }

}
