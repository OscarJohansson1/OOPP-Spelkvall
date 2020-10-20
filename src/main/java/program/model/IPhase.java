package program.model;

/**
 * An interface that describes the overall structure of a phase.
 */
interface IPhase {

    /**
     * The primary method that holds the logic of what happens during a phase.
     *
     * @param selectedSpace  The first selected space.
     * @param selectedSpace2 The second selected space.
     * @param player         The active player during the phase.
     * @param amount         The amount of units that should change, in some way, during the phase.
     */
    boolean startPhase(Space selectedSpace, Space selectedSpace2, Player player, int amount);

    /**
     * Method that returns the phase that follows this phase.
     *
     * @return The following phase.
     */
    IPhase nextPhase();

    /**
     * Method that set the phase that should follow this phase.
     *
     * @param phase The phase that should follow.
     */
    void setNextPhase(IPhase phase);

    /**
     * Method that returns the name of the phase.
     *
     * @return The name of the phase.
     */
    String getPhaseName();
}
