package program.model;
import static org.junit.jupiter.api.Assertions.*;

import javafx.embed.swing.JFXPanel;
import javafx.scene.paint.Color;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class TestModelDataHandler {

    @Test
    public void testNextPhase() {
        ModelDataHandler mdh = ModelDataHandler.getModelDataHandler();
        int n = 27;
        int player1 = 0;
        int player2 = 0;
        int player3 = 0;
        JFXPanel jfxPanel = new JFXPanel();

        mdh.initialize(n, new ArrayList<>(Arrays.asList(Color.rgb(1,0,0),
                Color.rgb(0,1,0), Color.rgb(0,0,1))), new ArrayList<>(Arrays.asList(
                "it_logo", "a_logo", "d_logo"
        )));

        for(int i = 0; i < n; i++){
            if(Color.rgb(1, 0, 0).equals(mdh.getColorOnSpace(i))){
                player1++;
            } else if(Color.rgb(0, 1, 0).equals(mdh.getColorOnSpace(i))){
                player2++;
            } else {
                player3++;
            }
        }
        assertTrue((Math.abs(player1 - player2) <= 1) && (Math.abs(player2 - player3) <= 1) && (Math.abs(player1 - player3) <= 1),
                "Players have: " + player1 + ", " + player2 + ", " + player3 + " spaces respective");
    }

}
