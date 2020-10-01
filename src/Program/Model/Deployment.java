package Program.Model;

/**
 * This class makes it possible for the current player to deploy units in the marked space IF the marked space is owned
 * by the current player and that player has any units left to deploy.
 */

class Deployment {
    /**
     * Method that adds one unit to a space if the player controls the space.
     * @param space The space which should be increased with one unit.
     * @param currentplayer The player that plays the current turn.
     * @return If the deployment was successful.
     */
    static boolean startDeployment(Space space, Player currentplayer, int amount) {
        if(space.getPlayer() == currentplayer && currentplayer.getUnits() >= amount) {
            deployUnit(space,amount);
            currentplayer.setUnits(currentplayer.getUnits() - amount);
            return true;
        }
        else{
            return false;
        }
    }

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
}
