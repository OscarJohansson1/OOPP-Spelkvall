import java.util.ArrayList;

public class Attack {

    Dice dice = new Dice();
    //Space space = new Space();
    private boolean attack = true;

    //This method checks if the attack is possible and declares which space is to be attacked
    void DeclareAttack(Space mySpace, Space opponentSpace, int myUnits){

            if (nextTo(mySpace, opponentSpace) && isAttackPossible(myUnits))
            {
                attack = true;
            }
    }



    //This method calculates the attack with the help of dices according to the official rules of Risk.
    //This also declares the winner of the attack
    void calculateAttack(int myUnits, int opponentUnits){

        int myDice = (myUnits -1);
        int opponentDice = opponentUnits ;

        if(attack){

            ArrayList<Integer> myResults = dice.rollNDIce(myDice);
            ArrayList<Integer> opponentResults = dice.rollNDIce(opponentDice);

            while(myResults.size() > 0 && opponentResults.size() > 0 ){

                if(findHighestDie(myResults) > findHighestDie(opponentResults)){
                    //Kill one unit in opponent space
                    System.out.println("Die Bitch");
                }
                else {
                    //Kill one unit in current player space
                    System.out.println("Ouch i failed");
                }
            }
        }
    }

    //This checks if one space on the board is next to another
    private boolean nextTo(Space mySpace, Space opponentSpace){

        return true;
    }

    private boolean isAttackPossible(int myUnits){
        return myUnits > 1;
    }

     Integer findHighestDie(ArrayList<Integer> rolls){

        int value = 0;
        int index = 0;

        for (int i = 0; i < rolls.size(); i++){
            if (rolls.get(i) > value){
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
