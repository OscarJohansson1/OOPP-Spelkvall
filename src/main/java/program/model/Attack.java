package program.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class makes it possible for the current player to attack another players space. It then calculates the results of the attack
 * and if all units on the other players space dies then it moves all units except one.
 */
class Attack implements IPhase {

    private IPhase nextPhase;

    private List<Integer> dices;

    private int attackerLoss;
    private int defenderLoss;

    boolean nextAttackPossible = true;

    @Override
    public void startPhase(Space selectedSpace, Space selectedSpace2, Player player, int amount) {
        if(selectedSpace != null && selectedSpace2 != null) {
            if (Attack.DeclareAttack(selectedSpace, selectedSpace2, selectedSpace.getUnits())) {
                attackerLoss = selectedSpace.getUnits();
                defenderLoss = selectedSpace2.getUnits();
                dices = Attack.calculateAttack(selectedSpace, selectedSpace2);
                if (selectedSpace.getUnits() == 1 && (selectedSpace.getPlayer() == selectedSpace2.getPlayer())) {
                    attackerLoss -= selectedSpace2.getUnits() + 1;
                    defenderLoss = -1;
                    nextAttackPossible = false;
                } else if (selectedSpace.getUnits() == 1) {
                    attackerLoss -= selectedSpace.getUnits();
                    defenderLoss -= selectedSpace2.getUnits();
                    nextAttackPossible = false;
                } else {
                    attackerLoss -= selectedSpace.getUnits();
                    defenderLoss -= selectedSpace2.getUnits();
                    nextAttackPossible = true;
                }
            }
        }
    }

    /**
     * Method that calculates the attack and updates the state of the spaces involved in the attack.
     * @param mySpace The space which the attacker attacks from.
     * @param enemySpace The space which is attacked.
     * @return An int with value 1 if the attack was successful and 0 if the attack was unsuccessful
     */
    static List<Integer> calculateAttack(Space mySpace, Space enemySpace) {

        int myDice;
        int enemyDice;
        if(mySpace.getUnits() > 3) {
            myDice = 3;
        } else if(mySpace.getUnits() > 2) {
            myDice = 2;
        } else {
            myDice = 1;
        }
        if(enemySpace.getUnits() > 1) {
            enemyDice = 2;
        } else {
            enemyDice = 1;
        }
        ArrayList<Integer> myResults = Dice.rollNDIce(myDice);
        ArrayList<Integer> opponentResults = Dice.rollNDIce(enemyDice);
        ArrayList<Integer> allresults = new ArrayList<>();
        allresults.addAll(myResults);
        allresults.addAll(opponentResults);
        while (myResults.size() > 0 && opponentResults.size() > 0) {
            if (findHighestDie(myResults) > findHighestDie(opponentResults)) {
                enemySpace.updateSpace(enemySpace.getUnits() -1);
                if (enemySpace.getUnits() < 1) {
                    enemySpace.updateSpace(mySpace.getPlayer(), mySpace.getUnits() - 1);
                    mySpace.updateSpace(1);
                }
            } else {
                mySpace.updateSpace(mySpace.getUnits() -1);
            }
        }
        return allresults;
    }

    private static boolean DeclareAttack(Space mySpace, Space opponentSpace, int myUnits) {
        return isAttackPossible(myUnits);
    }

    private static boolean isAttackPossible(int myUnits) {
        return myUnits > 1;
    }

    /**
     * Method that finds the die with the highest value in an ArrayList of Integers (Dice) and removes it.
     * @param rolls An ArrayList with Integer-values of a dice-roll.
     * @return The value of the die with the highest value.
     */
    static Integer findHighestDie(ArrayList<Integer> rolls) {
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

    List<Integer> diceresults() {
        return dices;
    }

    List<String> attackResults() {
        List<String> results = new ArrayList<>();
        results.add(" lost: " + attackerLoss);
        if(defenderLoss == -1) {
            results.add(" lost all remaining units! ");
        } else {
            results.add(" lost: " + defenderLoss);
        }
        return results;
    }

    @Override
    public IPhase nextPhase() {
        return nextPhase;
    }

    @Override
    public void setNextPhase(IPhase phase) {
        nextPhase = phase;
    }

    @Override
    public String getPhaseName() {
        return "ATTACK";
    }
}



