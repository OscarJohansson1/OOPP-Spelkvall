package program.model;

import java.util.List;

/**
 * Round controls the start of each phase and the navigation between phases during a round.
 */
public class Round {

    private final AttackPhase attack;
    private IPhase currentPhaseNew;

    //TODO Hårdkoda inte
    Round() {
        //TODO Inte hårdkoda
        DeployPhase deploy = new DeployPhase();
        attack = new AttackPhase();
        MovePhase move = new MovePhase();
        currentPhaseNew = deploy;
        deploy.setNextPhase(attack);
        attack.setNextPhase(move);
        move.setNextPhase(deploy);
    }

    /**
     * Method that switch to the next phase. If the current phase is Move, switch to Deploy instead.
     */
    public void nextPhase() {
        currentPhaseNew = currentPhaseNew.nextPhase();
    }


    /**
     * Method that calls start-methods of Deployment, Attack and Move, based on current phase, selected spaces and
     * the current player.
     *
     * @param selectedSpace  The space the action starts from. Eg. the space to deploy units on, attack from and move from
     * @param selectedSpace2 The space the action impacts. Eg. the space that is attacked or moved to.
     * @param currentPlayer  The active player during the turn.
     * @return True if phase can start as expected, else false.
     */
    boolean startPhase(Space selectedSpace, Space selectedSpace2, Player currentPlayer, int units) {
        return currentPhaseNew.startPhase(selectedSpace, selectedSpace2, currentPlayer, units);
    }

    List<Integer> attackerDiceResults() {
        return attack.attackerDiceResults();
    }

    List<Integer> defenderDiceResults() {
        return attack.defenderDiceResults();
    }

    List<String> attackResults() {
        return attack.attackResults();
    }

    public String getCurrentPhase() {
        return currentPhaseNew.getPhaseName();
    }

    boolean isNextAttackPossible() {
        return attack.nextAttackPossible;
    }

    public AttackPhase getAttack() {
        return attack;
    }

    public Space getOldSpace(){return attack.getOldspace();}
}
