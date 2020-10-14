package program.model;

/**
 * This class makes it possible for the current player to deploy units in the marked space IF the marked space is owned
 * by the current player and that player has any units left to deploy.
 */

class Deployment implements IPhase {

    private IPhase nextPhase;

    /**
     * This method checks if the current player has any units left to deploy.
     * @param space The space which should be increased with one unit.
     * @param unitsToDeploy The amount of units that can be deployed.
     */
    private static void deployUnit(Space space, int unitsToDeploy){
        if(unitsToDeploy > 0) {
            space.updateSpace(space.getUnits() + unitsToDeploy);
        }
        else {
            System.out.println("No Units Remaining");
        }
    }

    @Override
    public IPhase nextPhase() {
        return nextPhase;
    }

    @Override
    public void setNextPhase(IPhase phase) {
        nextPhase = phase;
    }

    @Override
    public void startPhase(Space selectedSpace, Space selectedSpace2, Player player, int amount) {
        if(selectedSpace != null) {
            if(player.getUnits() == 0){

            }
            if (selectedSpace.getPlayer() == player && player.getUnits() >= amount) {
                deployUnit(selectedSpace, amount);
                player.setUnits(player.getUnits() - amount);
            }
        }
    }

    @Override
    public String getPhaseName() {
        return "DEPLOY";
    }
}
