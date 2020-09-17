public class Movement {

    private Round currentRound;

    public Movement(Round currentRound) {
        this.currentRound = currentRound;
    }

    private boolean validateSpaces(Round currentRound, Space space1, Space space2) {
        return (currentRound.getCurrentPlayer() == space1.getPlayer()) && (currentRound.getCurrentPlayer() == space2.getPlayer());
    }

    public void MoveUnits(Space space1, Space space2) {
        if (validateSpaces(this.currentRound, space1, space2)) {
            int units1 = space1.getUnits();
            int units2 = space2.getUnits();
            int totalUnits = space1.getUnits() + space2.getUnits();

            //TODO insert way for player to determine desired unit spread via the UI

            System.out.println(space1.getUnits());
            System.out.println(space2.getUnits());
            if (units1 > 0 && units2 > 0 && units1 + units2 == totalUnits) {
                space1.updateSpace(this.currentRound.getCurrentPlayer(), units2);
                space2.updateSpace(this.currentRound.getCurrentPlayer(), units1);
            }
            System.out.println(space1.getUnits());
            System.out.println(space2.getUnits());

        } else {
            System.out.println("Both spaces do not belong to the current player, this message should never be shown.");

        }
    }
}
