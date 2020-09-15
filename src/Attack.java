import java.util.ArrayList;

public class Attack {

    Dice dice = new Dice();
    Space space = new Space();
    private boolean attack = false;

    //This method checks if the attack is possible and declares which space is to be attacked
    void DeclareAttack(Space mySpace, Space opponentSpace, int myUnits){

        if (isAttackPossible(myUnits)){
            if (nextTo(mySpace, opponentSpace))
            {
                attack = true;
            }
        }



    }



    //This method calculates the attack with the help of dices according to the official rules of Risk.
    //This also declares the winner of the attack
    void calculateAttack(int myUnits, int opponentUnits){

        int myDices = myUnits;
        int opponentDices = opponentUnits;

        if(attack){

            ArrayList<Integer> myResults = dice.rollNDIces(myDices);
            ArrayList<Integer> opponentResults = dice.rollNDIces(opponentDices);



        }


    }

    //Don't know if needed yet
    //This method calculates the results of the attack and the losses of the losing side
    void resultsOfAttack(){

    }

    //This checks if one space on the board is next to another
    private boolean nextTo(Space mySpace, Space opponentSpace){

        return true;
    }

    private boolean isAttackPossible(int myUnits){
        return myUnits > 1;
    }

    private ArrayList<Integer> sortArrayByHighest(ArrayList<Integer> array){

        for (int i = 0; i < array.size(); i++){

        }

        return array;
    }
}
