package Program.Model;

import java.util.ArrayList;

/**
 * This class makes it possible for the current player to attack another players space. It then calculates the results of the attack
 * and if all units on the other players space dies then it moves all units except one.
 */

 class Attack {

    //This method checks if the attack is possible and declares which space is to be attacked

     /**
      * Method that returns if an attack is possible, based on if the spaces are neighbours and there's more than
      * one attacking unit.
      * @param mySpace The space which the attacker attacks from.
      * @param opponentSpace The space which is attacked.
      * @param myUnits Amount of units on the space (mySpace) the attacker attacks from.
      * @return Whether the attack between the two spaces is possible.
      */
    static boolean DeclareAttack(Space mySpace, Space opponentSpace, int myUnits) {
        // myUnits kanske kan fås av mySpace.getUnits i en framtida refaktorisering
        return nextTo(mySpace, opponentSpace) && isAttackPossible(myUnits);
    }

     /**
      * Method that calculates the attack and updates the state of the spaces involved in the attack.
      * @param mySpace The space which the attacker attacks from.
      * @param enemySpace The space which is attacked.
      * @return An int with value 1 if the attack was successful and 0 if the attack was unsuccessful
      */
    static boolean calculateAttack(Space mySpace, Space enemySpace) {
        Dice dice = new Dice();
        int myDice = (mySpace.getUnits() - 1);
        int opponentDice = enemySpace.getUnits();

            ArrayList<Integer> myResults = dice.rollNDIce(myDice);
            ArrayList<Integer> opponentResults = dice.rollNDIce(opponentDice);

            while (myResults.size() > 0 && opponentResults.size() > 0) {
                if (findHighestDie(myResults) > findHighestDie(opponentResults)) {

                    enemySpace.updateSpace(enemySpace.getUnits() -1);

                    if (enemySpace.getUnits() < 1){
                        enemySpace.updateSpace(mySpace.getPlayer(), mySpace.getUnits() - 1);
                        mySpace.updateSpace(1);
                        return true;
                    }
                } else {
                    mySpace.updateSpace(mySpace.getUnits() -1);
                    return true;
                }
            }
            return false;
    }

    //This checks if one space on the board is next to another
    private static boolean nextTo(Space mySpace, Space opponentSpace) {
        return true;
    }

     /**
      * Method that checks if an attack is possible
      * @param myUnits This is the amount of units that a player has in one space
      * @return As long as there are more than one unit in a space the player is allowed to make an attack from that space
      */
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
}



