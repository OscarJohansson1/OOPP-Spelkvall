package program.model;

import java.util.List;

/**
 * A class that limits the direct access from the view package to the model package, lowering coupling between packages,
 * as well as enabling safer modification of the model because the public interface stays the same.
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
     * Method that returns if there can be another attack or if one of the spaces can no longer attack/be attacked.
     *
     * @return If there cannot be another attack.
     */
    public boolean isAttackDone() {
        return modelDataHandler.isAttackDone();
    }

    /**
     * Method that returns the attackers dice from the most previous attack.
     *
     * @return A list of Integer representing the attackers dice throw.
     */
    public List<Integer> getAttackerDiceResults() {
        return modelDataHandler.getAttackerDiceResults();
    }

    /**
     * Method that returns the defenders dice from the most previous attack.
     *
     * @return A list of Integer representing the defenders dice throw.
     */
    public List<Integer> getDefenderDiceResults() {
        return modelDataHandler.getDefenderDiceResults();
    }

    /**
     * Method that returns the casualties of an attack.
     *
     * @return A list of Strings representing the casualties from an attack.
     */
    public List<String> getAttackResults() {
        return modelDataHandler.attackResult();
    }

    /**
     * Method that returns the name of either the first or second selected space.
     *
     * @param number Should be 1 if the name of first selected space should be returned, 2 if the name of the second
     *               should be returned.
     * @return The name of selected space, or null if the input is invalid.
     */
    public String getSelectedSpaceName(int number) {
        if (number == 1) {
            return modelDataHandler.getSelectedSpace().getName();
        } else if (number == 2) {
            return modelDataHandler.getSelectedSpace2().getName();
        }
        return null;
    }

    /**
     * Method that returns the amount of units from either the first or second selected space.
     *
     * @param number Should be 1 if the amount of units from the first selected space should be returned, 2 if the
     *               amount of units from the second should be returned.
     * @return The amount of units from a selected space, or 0 if the input is invalid.
     */
    public int getSelectedSpaceUnits(int number) {
        if (number == 1) {
            return modelDataHandler.getSelectedSpace().getUnits();
        } else if (number == 2) {
            return modelDataHandler.getSelectedSpace2().getUnits();
        }
        return 0;
    }

    /**
     * Method that returns the amount of units from either the first or second selected space.
     *
     * @param number Should be 1 if the id of the first selected space should be returned, 2 if the id of the second
     *               should be returned.
     * @return The id of a selected space, or 0 if the input is invalid.
     */
    public int getSelectedSpaceId(int number) {
        if (number == 1) {
            return modelDataHandler.getSelectedSpace().getId();
        } else if (number == 2) {
            return modelDataHandler.getSelectedSpace2().getId();
        }
        return 0;
    }
}
