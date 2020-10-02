package Program.Model;

/**
 * This class makes it possible for a player to move units from one space to another.
 */

class Movement {
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


}
