package program.model;

import java.io.Serializable;

/**
 * This class makes it possible for the current player to deploy units in the marked space IF the marked space is owned
 * by the current player and that player has any units left to deploy.
 */
class DeployPhase implements IPhase, Serializable {

    private IPhase nextPhase;

    /**
     * The main method describing what should happen during the Deployment-phase. The method add units to a space if the
     * player is matching the player holding the space.
     *
     * @param selectedSpace  The first selected space.
     * @param selectedSpace2 Not used in this phase.
     * @param player         The active player during the phase.
     * @param amount         The amount of units that should be added to the space.
     */
    @Override
    public boolean startPhase(Space selectedSpace, Space selectedSpace2, Player player, int amount) {
        if (selectedSpace != null) {
            if (selectedSpace.getPlayerId() == player.getId() && player.getUnits() >= amount) {
                deployUnit(selectedSpace, amount);
                player.setUnits(player.getUnits() - amount);
                return true;
            }
        }
        return false;
    }

    private void deployUnit(Space space, int unitsToDeploy) {
        if (unitsToDeploy > 0) {
            space.updateSpace(space.getUnits() + unitsToDeploy);
        }
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

    @Override
    public void setNextPhase(IPhase phase) {
        nextPhase = phase;
    }

    /**
     * Method that returns the name of the phase.
     *
     * @return The name as a String in the form: "DEPLOY".
     */
    @Override
    public String getPhaseName() {
        return "DEPLOY";
    }
}
