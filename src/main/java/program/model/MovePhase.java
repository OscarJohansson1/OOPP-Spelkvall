package program.model;

import java.io.Serializable;

/**
 * This class makes it possible for a player to move units from one space to another.
 */
class MovePhase implements IPhase, Serializable {

    private IPhase nextPhase;

    /**
     * Method that starts the Movement-phase. The method takes in two spaces and moves an amount of units from the first
     * to the second.
     *
     * @param selectedSpace  The first space that units move from.
     * @param selectedSpace2 The second space that units move to.
     * @param player         The player that controls the spaces.
     * @param amount         The amount of units to move from the first space, to the second.
     */
    @Override
    public boolean startPhase(Space selectedSpace, Space selectedSpace2, Player player, int amount) {
        if (selectedSpace != null && selectedSpace2 != null) {
            int units1 = selectedSpace.getUnits();
            int units2 = selectedSpace2.getUnits();

            if (units1 > 0 && units2 > 0 && (units1 - amount >= 1)) {
                selectedSpace.updateSpace(selectedSpace.getPlayer(), units1 - amount);
                selectedSpace2.updateSpace(selectedSpace.getPlayer(), units2 + amount);
            }
            return true;
        }
        return false;
    }

    /**
     * Method that returns the next phase.
     *
     * @return The next phase.
     */
    @Override
    public IPhase nextPhase() {
        return nextPhase;
    }

    /**
     * Method that returns the name of the phase.
     *
     * @return The name as a String in the form: "MOVE".
     */
    @Override
    public String getPhaseName() {
        return "MOVE";
    }

    @Override
    public void setNextPhase(IPhase phase) {
        nextPhase = phase;
    }
}
