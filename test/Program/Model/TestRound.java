package Program.Model;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.Before;
import org.junit.Test;

public class TestRound {

    Round round = new Round();


    @Test
    public void testRound() {

        assertEquals(Round.Phase.DEPLOY, round.getCurrentPhase(), "Wrong starting phase");

    }
}
