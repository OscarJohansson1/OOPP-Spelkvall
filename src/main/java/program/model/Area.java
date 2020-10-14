package program.model;

import java.util.List;

public class Area {

    private final String name;
    private final int worth;
    private final List<Space> spaces;

    Area(String name, int worth, List<Space> spaces) {
        this.name = name;
        this.worth = worth;
        this.spaces = spaces;
    }

    boolean checkArea(Player player){
        for(Space space : spaces){
            if(!(player == space.getPlayer())){
                return false;
            }
        }
        return true;
    }

    int getWorth() {
        return worth;
    }

    String getName() {
        return name;
    }
}
