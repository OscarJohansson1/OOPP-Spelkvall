package program.model;

import static org.junit.jupiter.api.Assertions.*;


import javafx.scene.paint.Color;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestAttackPhase {

    AttackPhase attackPhase = new AttackPhase();

    private Player player1;
    private Player player2;

    private Space space1;
    private Space space2;

    @Before
    public void before(){
        player1 = new Player(10,1, "#123123", "hej", "hej");
        player2 = new Player(10,2, "#123123", "hej", "hej");

        space1 = new Space(0, player1, 5, "Test");
        space2 = new Space(1, player2, 5, "Test");
    }

    @Test
    public void testStartPhase(){
        assertTrue(attackPhase.startPhase(space1, space2, player1, 0), "Space is null, or attack not possible");
    }
}
