public class Movement {

    //Fråga modelDataHandler om currentplayer, inte round.
    private modelDataHandler modelDataHandler;

    private boolean validateSpaces(modelDataHandler modelDataHandler, Space space1, Space space2) {
        return (modelDataHandler.getCurrentPlayer() == space1.getPlayer()) && (modelDataHandler.getCurrentPlayer() == space2.getPlayer());
    }

    public void MoveUnits(Space space1, Space space2, Player player) {
        if (validateSpaces(modelDataHandler, space1, space2)) {
            int units1 = space1.getUnits();
            int units2 = space2.getUnits();
            int totalUnits = space1.getUnits() + space2.getUnits();

            //TODO insert way for player to determine desired unit spread via the UI

            System.out.println(space1.getUnits());
            System.out.println(space2.getUnits());
            if (units1 > 0 && units2 > 0 && units1 + units2 == totalUnits) {
                space1.updateSpace(modelDataHandler.getCurrentPlayer(), units2);
                space2.updateSpace(modelDataHandler.getCurrentPlayer(), units1);
            }
            System.out.println(space1.getUnits());
            System.out.println(space2.getUnits());

        } else {
            System.out.println("Both spaces do not belong to the current player, this message should never be shown.");

        }
    }
}
