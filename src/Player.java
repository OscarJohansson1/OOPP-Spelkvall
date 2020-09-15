public class Player {

    private int units;
    private String id;
    private String color;

    public Player(int units, String id, String color) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
