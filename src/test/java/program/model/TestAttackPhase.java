package program.model;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Tests for AttackPhase
 */
public class TestAttackPhase {

    AttackPhase attackPhase = new AttackPhase();

    private Player player1;
    private Player player2;

    private Space space1;
    private Space space2;

    private boolean successfulAttack = false;

    private int attackerLoss;
    private int defenderLoss;

    /**
     * SetUp needed for testing AttackPhase
     */
    @Before
    public void before() {
        player1 = new Player(10, 1, "#123123", "hej", "hej");
        player2 = new Player(10, 2, "#123123", "hej", "hej");

        space1 = new Space(0, player1, 5, "Test");
        space2 = new Space(1, player2, 5, "Test");
    }

    /**
     * Testing startPhase for attack
     */
    @Test
    public void testStartPhase() {
        assertTrue(attackPhase.startPhase(space1, space2, player1, 0), "Space is null, or attack not possible");
        assertFalse(attackPhase.startPhase(space1, null, player1, 5), "SelectedSpace2 is null so attack should not be possible, but is");
        assertFalse(attackPhase.startPhase(null, space2, player1, 5), "SelectedSpace1 is null so attack should not be possible, but is");
        assertFalse(attackPhase.startPhase(null, null, player1, 5), "Both spaces is null so attack should not be possible, but is");
    }

    /**
     * Testing if an attack is possible
     */
    @Test
    public void testIsAttackPossible() {
        space1.updateSpace(2);
        assertTrue(attackPhase.startPhase(space1, space2, player1, 5), "Attack should be possible, but isn't");
        space1.updateSpace(1);
        assertFalse(attackPhase.startPhase(space1, space2, player1, 5), "Attack shouldn't be possible, but is");
        space1.updateSpace(0);
        assertFalse(attackPhase.startPhase(space1, space2, player1, 5), "Attack shouldn't be possible, but is");
    }

    /**
     * Test for private method calculateAttack(). Parts Copied form AttackPhase to check if the private method works
     */
    @Test
    public void testCalculateAttack() {
        attackPhase.startPhase(space1, space2, player1, 5);
        space1.updateSpace(5);
        space2.updateSpace(5);

        List<Integer> testAttacker = attackPhase.attackerDiceResults();
        List<Integer> testDefender = attackPhase.defenderDiceResults();

        while (testAttacker.size() > 0 && testDefender.size() > 0) {
            if (findHighestDie(testAttacker) > findHighestDie(testDefender)) {
                space2.updateSpace(space2.getUnits() - 1);
                defenderLoss++;
                successfulAttack = true;
                if (space2.getUnits() < 1) {
                    space2.updateSpace(space1.getUnits() - 1);
                    space1.updateSpace(1);
                }
            } else {
                space1.updateSpace(space1.getUnits() - 1);
                attackerLoss++;
                successfulAttack = false;
            }
        }

        if (!successfulAttack) {
            assertEquals((5 - attackerLoss), space1.getUnits(), "Player1 failed his attack and should lose units, but didn't");
        } else {
            assertEquals((5 - defenderLoss), space2.getUnits(), "Player1's attack succeeded and player2 should lose units, but didn't ");
        }
    }

    /**
     * This is used to find the highest die
     */
    private Integer findHighestDie(List<Integer> rolls) {
        int value = 0;
        int index = 0;
        for (int i = 0; i < rolls.size(); i++) {
            if (rolls.get(i) > value) {
                value = rolls.get(i);
                index = i;
            }
        }
        rolls.remove(index);
        return value;
    }
}
