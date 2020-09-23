package Program.Model;

class Deployment {


    //private Program.View.View.Controller.MapController mc = new Program.View.View.Controller.MapController();

    public Deployment() {

    }

    public static boolean startDeployment(Space space, Player currentplayer) {
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
