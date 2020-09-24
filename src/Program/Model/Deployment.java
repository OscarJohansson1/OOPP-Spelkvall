package Program.Model;

class Deployment {
    /**
     * Method that adds one unit to a space if the player controls the space.
     * @param space The space which should be increased with one unit.
     * @param currentplayer The player that plays the current turn.
     * @return If the deployment was successful.
     */
    static boolean startDeployment(Space space, Player currentplayer) {
        if(space.getPlayer() == currentplayer) {
            deployUnit(space,currentplayer.getUnits());
            currentplayer.setUnits(currentplayer.getUnits() - 1);
            return true;
        }
        else{
            System.out.println("Inte nuvarande spelare");
            return false;
        }
    }
    private static void deployUnit(Space space, int unitsToDeploy){
        if(unitsToDeploy > 0) {
            space.updateSpace(space.getUnits() + 1);
        }
        else {
            System.out.println("No Units Remaining");
        }
    }
}
