public class Deployment {

    private int unitsToDeploy;
    //private MapController mc = new MapController();

    public Deployment() {

    }

    public boolean startDeployment(Space space, Player currentplayer) {
        this.unitsToDeploy = currentplayer.getUnits();

        //while(!allUnitsDeployed()) {
        if(space.getPlayer() == currentplayer) {
            deployUnit(space);
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

    private void deployUnit(Space space){
        if(unitsToDeploy > 0) {
            unitsToDeploy--;
            space.updateSpace(space.getUnits() + 1);
        }

    }

    private boolean allUnitsDeployed() {
        return unitsToDeploy == 0;
    }
}
