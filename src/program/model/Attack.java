package program.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class makes it possible for the current player to attack another players space. It then calculates the results of the attack
 * and if all units on the other players space dies then it moves all units except one.
 */

 class Attack {
     /**
      * Method that returns if an attack is possible, based on if the spaces are neighbours and there's more than
      * one attacking unit.
      * @param mySpace The space which the attacker attacks from.
      * @param opponentSpace The space which is attacked.
      * @param myUnits Amount of units on the space (mySpace) the attacker attacks from.
      * @return Whether the attack between the two spaces is possible.
      */
    static boolean DeclareAttack(Space mySpace, Space opponentSpace, int myUnits) {
        return nextTo(mySpace, opponentSpace) && isAttackPossible(myUnits);
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
        if(mySpace.getUnits() > 3)
        {
            myDice = 3;
        }
        else if(mySpace.getUnits() > 2)
        {
            myDice = 2;
        }
        else {
            myDice = 1;
        }
        if(enemySpace.getUnits() > 1)
        {
            enemyDice = 2;
        }
        else {
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
                if (enemySpace.getUnits() < 1){
                    enemySpace.updateSpace(mySpace.getPlayer(), mySpace.getUnits() - 1);
                    mySpace.updateSpace(1);
                }
            }
            else {
                mySpace.updateSpace(mySpace.getUnits() -1);

            }
        }
            return allresults;
    }



    //This checks if one space on the board is next to another
    /**
     * This method checks if one space is next to another space
     * @param mySpace The space that the current player is attacking from
     * @param opponentSpace The space that the current player is attacking that belongs to another player
     * @return True if the two spaces is next to each other
     */
    private static boolean nextTo(Space mySpace, Space opponentSpace) {
        //Going to be implemented at a later stage
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



