package Program.Model;

class Deployment {


    //private Program.View.View.Controller.MapController mc = new Program.View.View.Controller.MapController();

    public Deployment() {

    }

    /**
     * Method that adds one unit to a space if the player controls the space.
     * @param space The space which should be increased with one unit.
     * @param currentplayer The player that plays the current turn.
     * @return If the deployment was successful.
     */
    static boolean startDeployment(Space space, Player currentplayer) {
        int unitsToDeploy = currentplayer.getUnits();

        //while(!allUnitsDeployed()) {
        if(space.getPlayer() == currentplayer) {
            deployUnit(space,unitsToDeploy);
            System.out.println("Deployed units");
            return true;
        }
        else{
            System.out.println("Inte nuvarande spelare");
            return false;
        }

        //currentplayer.setUnits(0);

        //deployUnit(space);
        //currentplayer.setUnits(0);


    }

    private static void deployUnit(Space space, int unitsToDeploy){
        if(unitsToDeploy > 0) {
            unitsToDeploy--;
            space.updateSpace(space.getUnits() + 1);
        }

    }

    private boolean allUnitsDeployed(int unitsToDeploy) {
        return unitsToDeploy == 0;
    }
}
