package program.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

import javax.swing.*;

/**
 * Tests for Round
 */
public class TestRound {

    Round round = new Round();

    /**
     * Testing if all the phases move unto the next correct phase
     */
    @Test
    public void testRound() {
        DeployPhase deploy = new DeployPhase();
        AttackPhase attack = new AttackPhase();
        MovePhase move = new MovePhase();
        round.setCurrentPhase(deploy);
        deploy.setNextPhase(attack);
        attack.setNextPhase(move);
        move.setNextPhase(deploy);

        assertEquals("DEPLOY", round.getCurrentPhase(), "Wrong starting phase");
        round.nextPhase();
        assertEquals("ATTACK", round.getCurrentPhase(), "Didn't go from deployPhase to attackPhase");
        round.nextPhase();
        assertEquals("MOVE", round.getCurrentPhase(), "Didn't go from attackPhase to movePhase");
        round.nextPhase();
        assertEquals("DEPLOY", round.getCurrentPhase(), "Didn't go from movePhase to deployPhase");
    }
}
