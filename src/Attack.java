import java.util.ArrayList;

public class Attack {

    Dice dice = new Dice();

    //This method checks if the attack is possible and declares which space is to be attacked
    boolean DeclareAttack(Space mySpace, Space opponentSpace, int myUnits) {

        if (nextTo(mySpace, opponentSpace) && isAttackPossible(myUnits)) {

            return nextTo(mySpace, opponentSpace) && isAttackPossible(myUnits);
        }
        return false;
    }


    //This method calculates the attack with the help of dices according to the official rules of Risk.
    //This also declares the winner of the attack
    int calculateAttack(Space mySpace, Space enemySpace) {

        System.out.println(mySpace.getUnits() + " " + enemySpace.getUnits());
        int myDice = (mySpace.getUnits() - 1);
        int opponentDice = enemySpace.getUnits();

            ArrayList<Integer> myResults = dice.rollNDIce(myDice);
            ArrayList<Integer> opponentResults = dice.rollNDIce(opponentDice);

            while (myResults.size() > 0 && opponentResults.size() > 0) {

                if (findHighestDie(myResults) > findHighestDie(opponentResults)) {

                    //Kill one unit in opponent space
                    enemySpace.updateSpace(enemySpace.getUnits() -1);
                    System.out.println("Defender loses one unit");

                    if (enemySpace.getUnits() < 1){
                        System.out.println(enemySpace.getPlayer());
                        enemySpace.updateSpace(mySpace.getPlayer(), mySpace.getUnits() - 1);
                        mySpace.updateSpace(1);
                        System.out.println("Units in area are all dead");
                        System.out.println(mySpace.getPlayer());
                        System.out.println(enemySpace.getPlayer());
                        return 1;
                    }

                } else {

                    //Kill one unit in current player space
                    mySpace.updateSpace(mySpace.getUnits() -1);
                    System.out.println("Attacker loses one unit");
                }
            }
            return 0;

    }

    //This checks if one space on the board is next to another
    private boolean nextTo(Space mySpace, Space opponentSpace) {

        return true;
    }

    private boolean isAttackPossible(int myUnits) {
        return myUnits > 1;
    }

    Integer findHighestDie(ArrayList<Integer> rolls) {

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



