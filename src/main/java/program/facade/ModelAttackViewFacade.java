package program.facade;

import javafx.scene.image.Image;
import program.model.GameManager;

import java.util.List;

public class ModelAttackViewFacade {

    private final GameManager modelDataHandler = GameManager.getGameManager();

    public Image getTeamLogo(int i) {
        return new Image(modelDataHandler.getTeamLogo(i));
    }

    public boolean isAttackDone() {
        return modelDataHandler.isAttackDone();
    }

    public List<Integer> getAttackerDiceResults() {
        return modelDataHandler.getAttackerDiceResults();
    }

    public List<Integer> getDefenderDiceResults() {
        return modelDataHandler.getDefenderDiceResults();
    }

    public List<String> getAttackResults() {
        return modelDataHandler.attackResult();
    }

    /**
     * i represents selected space 1 or 2
     *
     * @param number
     */
    public String getSelectedSpaceName(int number) {
        if (number == 1) {
            return modelDataHandler.getSelectedSpace().getName();
        } else if (number == 2) {
            return modelDataHandler.getSelectedSpace2().getName();
        }
        return null; // error instead
    }

    public int getSelectedSpaceUnits(int number) {
        if (number == 1) {
            return modelDataHandler.getSelectedSpace().getUnits();
        } else if (number == 2) {
            return modelDataHandler.getSelectedSpace2().getUnits();
        }
        return 0; // error instead
    }

    public int getSelectedSpaceId(int number) {
        if (number == 1) {
            return modelDataHandler.getSelectedSpace().getId();
        } else if (number == 2) {
            return modelDataHandler.getSelectedSpace2().getId();
        }
        return 0; // error instead
    }

}
