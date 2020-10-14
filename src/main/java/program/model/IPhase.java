package program.model;

public interface IPhase {

    IPhase nextPhase();

    void setNextPhase(IPhase phase);

    void startPhase(Space selectedSpace, Space selectedSpace2, Player player, int amount);

    String getPhaseName();

}
