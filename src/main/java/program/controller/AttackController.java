package program.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import program.client.Client;
import program.model.ModelDataHandler;
import program.model.Attack;
import program.view.AttackView;


import java.io.IOException;
import java.util.*;

/**
 * This class is the controller for the attackView. It holds all the logic for the buttons in that view.
 */

public class AttackController extends AnchorPane {

    @FXML
    private ImageView attackerDieImage1;
    @FXML
    private ImageView attackerDieImage2;
    @FXML
    private ImageView attackerDieImage3;

    @FXML
    private ImageView defenderDieImage1;
    @FXML
    private ImageView defenderDieImage2;

    @FXML
    Button attackButton;
    @FXML
    Button abortButton;

    @FXML
    private Text attackerText;
    @FXML
    private Text defenderText;

    @FXML
    private Text attackerUnits;
    @FXML
    private Text defenderUnits;

    @FXML
    private ImageView attackerImageView;
    @FXML
    private ImageView defenderImageView;

    private final AttackView attackView;
    private final MapController mapController;
    private final ModelDataHandler modelDataHandler;

    AttackController(MapController mapController) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("attackMenu.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.mapController = mapController;
        this.modelDataHandler = ModelDataHandler.getModelDataHandler();
        this.attackView = new AttackView(new ArrayList<>(Arrays.asList(attackerDieImage1, attackerDieImage2, attackerDieImage3, defenderDieImage1, defenderDieImage2)),
                new ArrayList<>(Arrays.asList(attackerImageView, defenderImageView)));
        if (!Client.getClient().startedConnection) attack();
    }

    void attack() throws IOException {
        attackView.updateDice(Client.getClient());
        attackView.updateText(attackerText, defenderText, attackerUnits, defenderUnits, attackButton, abortButton);
    }

    public void attack(Attack attack) {
        attackView.updateDice(attack);
        attackView.updateText(attackerText, defenderText, attackerUnits, defenderUnits, attackButton, abortButton, attack);
    }

    /**
     * When the attack button is pressed in the attackView, an attack is done.
     * If the defender doesn't have any units left the abort button is changed to done
     */
    @FXML
    public void attackButtonPressed() throws IOException {
        if (!modelDataHandler.startPhase()) {
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
    public void abortButtonPressed() throws IOException {
        mapController.removeAttackView();
    }
}
