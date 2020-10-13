package program.model;

import static org.junit.jupiter.api.Assertions.*;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.junit.Before;
import org.junit.Test;

public class TestDeployment {



    Rectangle rec1;
    Rectangle rec2;

    Player player1;
    Player player2;

    Space space1;
    Space space2;

    @Before
    public void before(){

        rec1 = new Rectangle();
        rec2 = new Rectangle();

        player1 = new Player(10,1, Color.color(1, 0, 0));
        player2 = new Player(10,2, Color.color(0, 0, 1));

        space1 = new Space(0, player1, 5, "Test");
        space2 = new Space(1, player2, 5, "Test");
    }

    @Test
    public void testStartDeploymentUnitIncrement1() {
        assertTrue(Deployment.startDeployment(space2, player2, 5), "Should return true if player and space match, but didn't");
    }

    @Test
    public void testStartDeploymentUnitIncrement2() {
        Deployment.startDeployment(space1, player1, 5);
        assertEquals(10, space1.getUnits(), "Amount of units on space didn't increase with 1");
    }

    @Test
    public void testStartDeploymentWrongSpace1() {
        assertFalse(Deployment.startDeployment(space1, player2, 5), "Should return false if player and space don't match, but didn't");
    }

    @Test
    public void testStartDeploymentWrongSpace2() {
        Deployment.startDeployment(space2, player1,5);
        assertEquals(5, space2.getUnits(), "Amount of units on space changed, when it shouldn't");
    }
}