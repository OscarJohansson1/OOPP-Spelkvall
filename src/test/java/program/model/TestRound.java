package program.model;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

import javax.swing.*;

public class TestRound {

    Round round = new Round();

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
