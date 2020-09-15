import java.util.ArrayList;

public class Dice {

    private final int NUMBER_RANGE = 6;


    //Roll one dice and return a number between 1-6
    public int rollADice(){
        int value = (int)(Math.random() * NUMBER_RANGE) + 1;
        System.out.println(value);
        return value;

    }

    //Roll N dices and return an ArrayList with the results
    public ArrayList<Integer> rollNDIces(int n){
        ArrayList<Integer> results = new ArrayList<>();

        for (int i = 0; i < n; i++){
            results.add(rollADice());
        }

        System.out.println(results.toString());

        return results;
    }
}
