package program.model;

import java.util.List;

/**
 * A class that limits the direct access from the view package to the model package, lowering coupling between packages,
 * as well as enabling easier modification and extension of the code.
 */
public class ModelToViewFacade {

    private GameManager modelDataHandler = GameManager.getGameManager();

    /**
     * Method that returns a string of the file-path to a team logotype.
     *
     * @param i The id of a space.
     * @return The file-path, as a String, to the logotype of the player holding the space.
     */
    public String getTeamLogo(int i) {
        return modelDataHandler.getTeamLogo(i);
    }

    /**
     * Method that returns if there can be another attack
     * @return
     */
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
