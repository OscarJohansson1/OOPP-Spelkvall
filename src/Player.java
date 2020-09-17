import javafx.scene.paint.Color;

public class Player {

    private int units;
    private int id;
    private Color color;

    public Player(int units, int id, Color color) {
        this.units = units;
        this.id = id;
        this.color = color;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}