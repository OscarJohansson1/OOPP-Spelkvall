package Program.Model;

import java.util.ArrayList;

public class MockDice {

    static int rollADie(int value){

        return value;

    }

    //Roll N dices and return an ArrayList with the results
    static ArrayList<Integer> rollNDIce(int n, int value){
        ArrayList<Integer> results = new ArrayList<>();

        for (int i = 0; i < n; i++){
            results.add(rollADie(value));
        }

        return results;
    }

}
