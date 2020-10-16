package program.model;

import static org.junit.jupiter.api.Assertions.*;


import javafx.scene.paint.Color;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestAttack {

    private Player player1;
    private Player player2;

    private Space space1;
    private Space space2;

    @Before
    public void before(){

        player1 = new Player(10,1, "#123123");
        player2 = new Player(10,2, "#123123");

        space1 = new Space(0, player1, 5, "Test");
        space2 = new Space(1, player2, 5, "Test");
    }

    @Test
    public void testAmountOfDice(){
        List<Integer> dice = Attack.calculateAttack(space1, space2);
        assertEquals(5, dice.size(), "5 dice should've been used, but only " + dice.size() + " was");
    }

    @Test
    public void testAmountOfDiceWhenFewUnits(){
        space1.updateSpace(3);
        space1.updateSpace(1);
        List<Integer> dice = Attack.calculateAttack(space1, space2);
        assertEquals(3, dice.size(), "3 dice should've been used, but only " + dice.size() + " was");
    }

    @Test
    public void testCalculateAttackUnitsLeft(){
        Attack.calculateAttack(space1, space2);
        if (space1.getPlayer() == space2.getPlayer()){
            assertTrue(space1.getUnits() < 5, "Successful attack, but 5 or more units left on space1");
        } else {
            assertTrue(space1.getUnits() > 0, "Less than 1 unit left after unsuccessful attack");
        }
    }

    @Test
    public void testFindHighestDieRightReturnValue(){
        int highest = Attack.findHighestDie(MockDice.rollNDIce(5, 6));
        assertEquals(6, highest, "Wrong value returned");
    }

    @Test
    public void testFindHighestDieListLength(){
        ArrayList<Integer> list = MockDice.rollNDIce(5, 6);
        Attack.findHighestDie(list);
        assertEquals(4, list.size(), "List doesn't have the correct length after findHighestDie call");
    }
}
