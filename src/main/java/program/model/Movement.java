package program.model;

/**
 * This class makes it possible for a player to move units from one space to another.
 */

class Movement implements IPhase {

    private IPhase nextPhase;

    /**
     * Method that moves units from one space to another. The amount of units is chosen with a slider int the GUI.
     * @param space1 Space which units move from.
     * @param space2 Space which units move to.
     */
    static boolean MoveUnits(Space space1, Space space2, int amount) {

        int units1 = space1.getUnits();
        int units2 = space2.getUnits();

        if (units1 > 0 && units2 > 0 && (units1 - amount >= 1)) {
            space1.updateSpace(space1.getPlayer(), units1 - amount);
            space2.updateSpace(space1.getPlayer(), units2 + amount);
        }
        return true;
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
        if(selectedSpace != null && selectedSpace2 != null) {
            int units1 = selectedSpace.getUnits();
            int units2 = selectedSpace2.getUnits();

            if (units1 > 0 && units2 > 0 && (units1 - amount >= 1)) {
                selectedSpace.updateSpace(selectedSpace.getPlayer(), units1 - amount);
                selectedSpace2.updateSpace(selectedSpace.getPlayer(), units2 + amount);
            }
        }
    }

    @Override
    public String getPhaseName() {
        return "MOVE";
    }
}
