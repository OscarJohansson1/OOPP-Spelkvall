import javafx.fxml.FXML;
import javafx.scene.shape.Rectangle;

public class Space {

    private final int id;
    private Player player;
    private int units;
    private final String name;
    @FXML
    private final Rectangle cube;

    public Space(int id, Player player, int units, String name, Rectangle cube){
        this.id = id;
        this.player = player;
        this.units = units;
        this.name = name;
        this.cube = cube;
    }

    public void updateSpace(Player player, int units) {
        setPlayer(player);
        setUnits(units);
        // Change color and units
    }

    public void updateSpace(int units) {
        updateSpace(this.player, units);
    }

    private void setPlayer(Player player) {
        this.player = player;
    }

    private void setUnits(int n) {
        units = n;
    }

    public int getId() {
        return id;
    }

    public Player getPlayer() {
        return player;
    }

    public int getUnits() {
        return units;
    }

    public String getName() {
        return name;
    }
}
