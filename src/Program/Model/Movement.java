package Program.Model;

class Movement {
    /**
     * Method that moves units from one space to another. (currently switch place)
     * @param space1 Space which units move from.
     * @param space2 Space which units move to.
     */
    static boolean MoveUnits(Space space1, Space space2, int amount) {

        int units1 = space1.getUnits();
        int units2 = space2.getUnits();


        //TODO insert way for player to determine desired unit spread via the UI

        System.out.println(space1.getUnits());
        System.out.println(space2.getUnits());
        if (units1 > 0 && units2 > 0 && (units1 - amount >= 1)) {
            space1.updateSpace(space1.getPlayer(), units1 - amount);
            space2.updateSpace(space1.getPlayer(), units2 + amount);
        }
        System.out.println(space1.getUnits());
        System.out.println(space2.getUnits());
        return true;
    }


}
