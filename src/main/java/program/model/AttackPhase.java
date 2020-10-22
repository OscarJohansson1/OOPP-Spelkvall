package program.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class makes it possible for the current player to attack another players space. It then calculates the results of the attack
 * and if all units on the other players space dies then it moves all units except one.
 */
public class AttackPhase implements IPhase, Serializable {

    private IPhase nextPhase;

    private List<Integer> attackerDice;
    private List<Integer> defenderDice;

    private int attackerLoss;
    private int defenderLoss;

    public boolean nextAttackPossible = true;

    public boolean destroyedOpponent = false;


    public AttackPhase() {

    }

    public AttackPhase(AttackPhase attack) {
        nextPhase = attack.nextPhase;
        attackerDice = attack.attackerDice;
        defenderDice = attack.defenderDice;
        attackerLoss = attack.attackerLoss;
        defenderLoss = attack.defenderLoss;
        nextAttackPossible = attack.nextAttackPossible;
        destroyedOpponent = attack.destroyedOpponent;
    }

    public void updateAttack(AttackPhase attack){
        nextPhase = attack.nextPhase;
        attackerDice = attack.attackerDice;
        defenderDice = attack.defenderDice;
        attackerLoss = attack.attackerLoss;
        defenderLoss = attack.defenderLoss;
        nextAttackPossible = attack.nextAttackPossible;
        destroyedOpponent = attack.destroyedOpponent;
    }

    /**
     * Method that calculates the attack and updates the state of the spaces involved in the attack.
     *
     * @param selectedSpace  The space which the attacker attacks from.
     * @param selectedSpace2 The space which is the defender.
     * @param player         Not used.
     * @param amount         Not used.
     */
    @Override
    public boolean startPhase(Space selectedSpace, Space selectedSpace2, Player player, int amount) {
        if (selectedSpace != null && selectedSpace2 != null) {
            if (isAttackPossible(selectedSpace.getUnits())) {
                destroyedOpponent = false;
                savePreAttackState(selectedSpace, selectedSpace2);
                calculateAttack(selectedSpace, selectedSpace2);
                updateCasualties(selectedSpace, selectedSpace2);
                System.out.println(attackerDice);
                System.out.println(this);
                return true;
            }
        }
        return false;
    }

    private void savePreAttackState(Space attacker, Space defender) {
        attackerLoss = attacker.getUnits();
        defenderLoss = defender.getUnits();
    }

    private void updateCasualties(Space attacker, Space defender) {
        if (attacker.getUnits() == 1 && destroyedOpponent) {
            attackerLoss -= defender.getUnits() + 1;
            defenderLoss = -1;
            nextAttackPossible = false;
        } else if (attacker.getUnits() == 1) {
            attackerLoss -= attacker.getUnits();
            defenderLoss -= defender.getUnits();
            nextAttackPossible = false;
        } else {
            attackerLoss -= attacker.getUnits();
            defenderLoss -= defender.getUnits();
            nextAttackPossible = true;
        }
    }

    private void calculateAttack(Space attacker, Space defender) {
        throwDice(attacker, defender);
        List<Integer> tempAttacker = new ArrayList<>(attackerDice);
        List<Integer> tempDefender = new ArrayList<>(defenderDice);
        while (tempAttacker.size() > 0 && tempDefender.size() > 0) {
            if (findHighestDie(tempAttacker) > findHighestDie(tempDefender)) {
                defender.updateSpace(defender.getUnits() - 1);
                if (defender.getUnits() < 1) {
                    defender.updateSpace(attacker.getUnits() - 1);
                    destroyedOpponent = true;
                    attacker.updateSpace(1);
                    System.out.println("opponent destroyed");
                }
            } else {
                attacker.updateSpace(attacker.getUnits() - 1);
            }
        }
    }

    private void throwDice(Space attacker, Space defender) {
        int myDice;
        int enemyDice;
        if (attacker.getUnits() > 3) {
            myDice = 3;
        } else if (attacker.getUnits() > 2) {
            myDice = 2;
        } else {
            myDice = 1;
        }
        if (defender.getUnits() > 1) {
            enemyDice = 2;
        } else {
            enemyDice = 1;
        }
        attackerDice = sortList(Dice.rollNDIce(myDice));
        defenderDice = sortList(Dice.rollNDIce(enemyDice));
    }

    private List<Integer> sortList(List<Integer> list) {
        int temp;
        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < list.size() - 1; i++) {
                if (list.get(i).compareTo(list.get(i + 1)) < 0) {
                    temp = list.get(i);
                    list.set(i, list.get(i + 1));
                    list.set(i + 1, temp);
                    sorted = false;
                }
            }
        }
        return list;
    }

    private boolean isAttackPossible(int myUnits) {
        return myUnits > 1;
    }

    /**
     * Method that finds the die with the highest value in an ArrayList of Integers (Dice) and removes it.
     *
     * @param rolls An ArrayList with Integer-values of a dice-roll.
     * @return The value of the die with the highest value.
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

    public List<Integer> attackerDiceResults() {
        return new ArrayList<>(attackerDice);
    }

    public List<Integer> defenderDiceResults() {
        return new ArrayList<>(defenderDice);
    }

    //TODO kolla om det här kan göras i view
    public List<String> attackResults() {
        List<String> results = new ArrayList<>();
        results.add(" lost: " + attackerLoss);
        if (defenderLoss == -1) {
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



