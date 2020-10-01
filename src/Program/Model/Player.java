package Program.Model;

import javafx.scene.paint.Color;

public class Player {

    private int units;
    private int id;
    private Color color;

    Player(int units, int id, Color color) {
        this.units = units;
        this.id = id;
        this.color = color;
    }

    public int getUnits() {
        return units;
    }

    void setUnits(int units) {
        this.units = units;
    }

    int getId() {
        return id;
    }

    public Color getColor() {
        return color;
    }


}