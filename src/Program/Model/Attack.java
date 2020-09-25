package Program.Model;

import java.util.ArrayList;

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
        System.out.println(mySpace.getUnits() + " " + enemySpace.getUnits());
        int myDice = (mySpace.getUnits() - 1);
        int opponentDice = enemySpace.getUnits();

            ArrayList<Integer> myResults = dice.rollNDIce(myDice);
            ArrayList<Integer> opponentResults = dice.rollNDIce(opponentDice);

            while (myResults.size() > 0 && opponentResults.size() > 0) {

                if (findHighestDie(myResults) > findHighestDie(opponentResults)) {

                    //Kill one unit in opponent space
                    enemySpace.updateSpace(enemySpace.getUnits() -1);
                    //System.out.println("Defender loses one unit");

                    if (enemySpace.getUnits() < 1){
                        //System.out.println(enemySpace.getPlayer());
                        enemySpace.updateSpace(mySpace.getPlayer(), mySpace.getUnits() - 1);
                        mySpace.updateSpace(1);
                        //System.out.println("Units in area are all dead");
                        //System.out.println(mySpace.getPlayer());
                        //System.out.println(enemySpace.getPlayer());
                        return true;
                    }

                } else {

                    //Kill one unit in current player space
                    mySpace.updateSpace(mySpace.getUnits() -1);
                    //System.out.println("Attacker loses one unit");
                    return true;
                }
            }
            return false;

    }

    //This checks if one space on the board is next to another
    private static boolean nextTo(Space mySpace, Space opponentSpace) {

        return true;
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
        //System.out.println("Value:" + value);
        //System.out.println("Index:" + index);
        //System.out.println("Array:" + rolls.toString());
        return value;
    }
}



