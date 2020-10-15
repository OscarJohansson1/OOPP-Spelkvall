package program.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import program.model.ModelDataHandler;
import program.view.AttackView;


import java.io.IOException;
import java.util.*;

/**
 * This class is the controller for the attackView. It holds all the logic for the buttons in that view.
 */

public class AttackController extends AnchorPane {

    @FXML private ImageView attackerDieImage1;
    @FXML private ImageView attackerDieImage2;
    @FXML private ImageView attackerDieImage3;

    @FXML private ImageView defenderDieImage1;
    @FXML private ImageView defenderDieImage2;

    @FXML private Button attackButton;
    @FXML private Button abortButton;

    @FXML private Text attackerText;
    @FXML private Text defenderText;

    @FXML private Text attackerUnits;
    @FXML private Text defenderUnits;

    @FXML private ImageView attackerImageView;
    @FXML private ImageView defenderImageView;

    private AttackView attackView;
    private MapController mapController;
    private ModelDataHandler modelDataHandler;
    private Stage stage;

    AttackController(MapController mapController, Stage stage) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("attackMenu.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.mapController = mapController;
        this.stage = stage;
        this.modelDataHandler = ModelDataHandler.getModelDataHandler();
        this.attackView = new AttackView(new ArrayList<>(Arrays.asList(attackerDieImage1,attackerDieImage2,attackerDieImage3, defenderDieImage1,defenderDieImage2)),
                new ArrayList<>(Arrays.asList(attackerImageView, defenderImageView)));
        attack();
    }

    private void attack() {
        attackView.updateDice();
        attackView.updateText(attackerText, defenderText, attackerUnits, defenderUnits, attackButton, abortButton);
    }

    /**
     * When the attack button is pressed in the attackView, an attack is done.
     * If the defender doesn't have any units left the abort button is changed to done
     */
    @FXML
    public void attackButtonPressed() {
        if(!modelDataHandler.startPhase()){
            attackView.attackDone(attackButton, abortButton);
            return;
        }
        attack();
    }

    /**
     * The player can abort an attack by clicking this button.
     * When done the player is returned to the map
     */
    @FXML
    public void abortButtonPressed() {
        mapController.removeAttackView();
        if(modelDataHandler.isWinner()){
            Parent root = new EndController(stage);
            Scene scene = new Scene(root, 1920, 1080);

            stage.setTitle("End");
            stage.setScene(scene);
            stage.show();
        }
    }
}
