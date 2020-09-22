import static org.junit.jupiter.api.Assertions.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class TestAttack {

    Attack attack = new Attack();

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

        space1 = new Space(1, player1, 5, "Test", rec1 );
        space2 = new Space(2, player2, 5, "Test", rec2 );
    }

    @Test
    public void testDeclareAttack() {
        // Rewrite test when nextTo() is implemented correctly
        assertTrue(attack.DeclareAttack(space1, space2, player1.getUnits()));
    }

    @Test
    public void testCalculateAttackSuccessful(){
        int winner = attack.calculateAttack(space1, space2);
        if (space1.getPlayer() == space2.getPlayer()){
            assertEquals(1, winner, "Successful attack, but wrong return");
        } else {
            assertEquals(0, winner, "Unsuccessful attack, but wrong return");
        }
    }

    @Test
    public void testCalculateAttackUnitsLeft(){
        attack.calculateAttack(space1, space2);
        if (space1.getPlayer() == space2.getPlayer()){
            assertEquals(1, space1.getUnits(), "Successful attack, but not the correct amount of units left on space1");
        } else {
            assertTrue(space1.getUnits() > 0, "Less than 1 unit left after unsuccessful attack");
        }
    }

    @Test
    public void testFindHighestDieRightReturnValue(){
        int highest = attack.findHighestDie(MockDice.rollNDIce(5, 6));
        assertEquals(6, highest, "Wrong value returned");
    }

    @Test
    public void testFindHighestDieListLength(){
        ArrayList<Integer> list = MockDice.rollNDIce(5, 6);
        attack.findHighestDie(list);
        assertEquals(4, list.size(), "List doesn't have the correct length after findHighestDie call");
    }
}
