package program.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

import java.util.ArrayList;

/**
 * tests for Dice
 */
public class TestDice {

    Dice dice = new Dice();

    /**
     * Test if Dice works for one die
     */
    @Test
    public void testDiceOneThrow() {
        int value = Dice.rollADie();
        assertTrue(value <= 6 && value >= 1, "Die-value isn't between 1-6");
    }

    /**
     * Test if Dice works for multiple dice
     */
    @Test
    public void testDiceListLength() {
        ArrayList<Integer> list = Dice.rollNDIce(5);
        assertEquals(5, list.size(), "List from rollNDice() has wrong length");
    }
}
