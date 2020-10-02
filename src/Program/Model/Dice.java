package program.model;

import java.util.ArrayList;

/**
 * This class makes it possible to calculate an attack depending on the value of the dices.
 */

class Dice {

    private final int NUMBER_RANGE = 6;

    /**
     * Method that rolls a die and returns the value of the die.
     * @return The value of the die.
     */
    int rollADie(){
        int value = (int)(Math.random() * NUMBER_RANGE) + 1;
        System.out.println(value);
        return value;

    }

    /**
     * Method that creates an ArrayList with values from a dice-roll
     * @param n Numbers of dice that should be rolled and added to the ArrayList
     * @return A list with values from a dice-roll with n dice.
     */
    ArrayList<Integer> rollNDIce(int n){
        ArrayList<Integer> results = new ArrayList<>();

        for (int i = 0; i < n; i++){
            results.add(rollADie());
        }

        System.out.println(results.toString());

        return results;
    }
}
