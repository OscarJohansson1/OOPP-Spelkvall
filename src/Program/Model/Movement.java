package Program.Model;

class Movement {

    public static void MoveUnits(Space space1, Space space2) {

        int units1 = space1.getUnits();
        int units2 = space2.getUnits();
        int totalUnits = space1.getUnits() + space2.getUnits();

        //TODO insert way for player to determine desired unit spread via the UI

        System.out.println(space1.getUnits());
        System.out.println(space2.getUnits());
        if (units1 > 0 && units2 > 0 && units1 + units2 == totalUnits) {
            space1.updateSpace(space1.getPlayer(), units2);
            space2.updateSpace(space1.getPlayer(), units1);
        }
        System.out.println(space1.getUnits());
        System.out.println(space2.getUnits());
    }


}
