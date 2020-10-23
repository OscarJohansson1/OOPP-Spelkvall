package program.model;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

public class TestDeployPhase {

    DeployPhase deploy = new DeployPhase();


    Player player1;
    Player player2;

    Space space1;
    Space space2;

    @Before
    public void before() {
        player1 = new Player(10, 1, "#123123", "hej", "hej");
        player2 = new Player(10, 2, "#123123", "hej", "hej");

        space1 = new Space(0, player1, 5, "Test");
        space2 = new Space(1, player2, 5, "Test");
    }

    @Test
    public void testStartDeployment() {
        deploy.startPhase(space1, null, player1, 5);
        assertEquals(10, space1.getUnits(), "5 units should have been deployed to space1, but didn't");

        deploy.startPhase(space1, null, player1, player1.getUnits() + 5);
        assertEquals(10, space1.getUnits(), "Player1 shouldn't be able to deploy more units than it owns, but could");

        assertFalse(deploy.startPhase(null, null, player1, 5), "No selected space so the method should return false, but didn't");

        deploy.startPhase(space2, null, player2, 5);
        assertEquals(5, player2.getUnits(),"If player2 deploys 5 units player2 should only have 5 units left, but has more");
    }


}
