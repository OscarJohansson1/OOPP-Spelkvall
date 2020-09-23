package Program.Model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

import java.util.ArrayList;

public class TestDice {

    MockDice dice = new MockDice();

    @Test
    public void testDiceOneThrow() {
        int value = dice.rollADie(1);
        assertTrue(value <= 6 && value >= 1, "Die-value isn't between 1-6");
    }

    @Test
    public void testDiceListLength() {
        ArrayList<Integer> list = dice.rollNDIce(5,1);
        assertEquals(5, list.size(), "List from rollNDice() has wrong length");
    }


}
