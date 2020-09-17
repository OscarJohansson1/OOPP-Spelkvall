public class Deployment {

    private int unitsToDeploy;
    private Player player;
    private MapController mc = new MapController();

    public Deployment(Player player, int unitsToDeploy) {
        this.unitsToDeploy = unitsToDeploy;
        this.player = player;
    }

    public void startDeployment(Player player, int unitsToDeploy) {
            this.unitsToDeploy = unitsToDeploy;
            this.player = player;
            while(!allUnitsDeployed()) {
                while (mc.getSelectedSpace().getPlayer().getId() != player.getId());
                deployUnit(mc.getSelectedSpace());
            }
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
