package program.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

public class TestMovement {

    MovePhase movement;

    Player player1;
    Player player2;

    Space space1;
    Space space2;
    Space space3;

    @Before
    public void before(){

        player1 = new Player(10,1, "#123123", "hej", "hej");
        player2 = new Player(10,2, "#123123", "hej", "hej");

        space1 = new Space(1, player1, 10, "Test");
        space2 = new Space(2, player1, 2, "Test");
    }

    @Test
    public void testMovement() {
        //Movement.MoveUnits(space1, space2, 5);
        assertEquals(5, space1.getUnits(), "space1 has the wrong amount of units after move");
        assertEquals(7, space2.getUnits(), "space2 has the wrong amount of units after move");
    }

    @Test
    public void testMovementWithIllegalUnits() {
        //Movement.MoveUnits(space1, space2, 15);
        assertEquals(10, space1.getUnits(), "Units where moved when they shouldn't have been");
        assertEquals(2, space2.getUnits(), "Units where moved when they shouldn't have been");
    }

}
