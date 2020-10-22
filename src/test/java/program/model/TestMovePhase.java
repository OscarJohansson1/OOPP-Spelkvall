package program.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

public class TestMovePhase {

    MovePhase movement = new MovePhase();

    Player player1;
    Player player2;

    Space space1;
    Space space2;

    @Before
    public void before() {
        player1 = new Player(10, 1, "#123123", "hej", "hej");
        player2 = new Player(10, 2, "#123123", "hej", "hej");

        space1 = new Space(1, player1, 15, "Test");
        space2 = new Space(2, player1, 5, "Test");
    }

    @Test
    public void testMovement() {
        movement.startPhase(space1, space2, player1, 10);
        assertEquals(5, space1.getUnits(), "space1 has the wrong amount of units after move");
        assertEquals(15, space2.getUnits(), "space2 has the wrong amount of units after move");
    }

    @Test
    public void testMovementWithIllegalUnits() {
        movement.startPhase(space1, space2, player1, 20);
        assertEquals(15, space1.getUnits(), "Units were moved when they shouldn't have been");
        assertEquals(5, space2.getUnits(), "Units were moved when they shouldn't have been");
    }
}
