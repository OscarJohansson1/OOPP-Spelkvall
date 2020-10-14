package program.model;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class represents the board and is keeping track of the board
 */
class Board {



    private List<Space> spaces;
    private List<Area> areas;

    Space selectedSpace;
    Space selectedSpace2;

    /**
     * Order of space ID:
     * 0 cubeHubben
     * 1 cubeBasen
     * 2 cubeKajsabaren
     * 3 cubeZaloonen
     * 4 cubeWinden
     * 5 cubeLofTDet
     * 6 cubeRodaRummet
     * 7 cubeVerum
     * 8 cubeVillan
     * 9 cubeADammen
     * 10 cubeFocus
     * 11 cubeFortNox
     * 12 cubeGTSpritis
     * 13 cubeGoldenI
     * 14 cubeChabo
     * 15 cubeWijkanders
     * 16 cubeHrum
     * 17 cubeAlvan
     * 18 cubeSpektrum
     * 19 cubeGasquen
     * 20 cubeChalmersplatsen
     * 21 cubeOlgas
     * 22 cubeRunAn
     * 23 cubeTagvagnen
     * 24 cubeOrigogarden
     * 25 cubeKalleGlader
     * 26 cubeTvargatan
     */
    private int[][] neighbours = {
            {0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0},
    };



    Board(List<Space> spaces)
    {
        this.spaces = spaces;
    }

    /**
     * Hardcoded as of now
     */
    void createAreas(){

        Area karhuset = new Area("Kårhuset", 7,
                Arrays.asList(spaces.get(19), spaces.get(20), spaces.get(21), spaces.get(22), spaces.get(23)));
        Area maskinhuset = new Area("Maskinhuset", 5,
                Arrays.asList(spaces.get(3), spaces.get(4), spaces.get(5), spaces.get(26)));
        Area edit = new Area("EDIT", 3,
                Arrays.asList(spaces.get(0), spaces.get(1), spaces.get(2)));
        Area vasa = new Area("Vasa", 5,
                Arrays.asList(spaces.get(13), spaces.get(14), spaces.get(15), spaces.get(25)));
        Area lindholmen = new Area("Lindholmen", 3,
                Arrays.asList(spaces.get(16), spaces.get(17), spaces.get(18)));
        Area kemigarden = new Area("Kemigården", 5,
                Arrays.asList(spaces.get(10), spaces.get(11), spaces.get(12), spaces.get(24)));
        Area sb = new Area("SB", 5,
                Arrays.asList(spaces.get(6), spaces.get(7), spaces.get(8), spaces.get(9)));

        areas = Arrays.asList(karhuset, maskinhuset, edit, vasa, lindholmen, kemigarden, sb);
    }

    /**
     * Insert a player and get the amount of extra units they should get from holding "areas".
     * @param player
     * @return
     */
    int getUnitsFromAreas(Player player) {
        int count = 0;
        for(Area area : areas){
            if(area.checkArea(player)){
                count += area.getWorth();
            }
        }
        return count;
    }

    int getUnitsForSpacesHold(Player player){
        int count = 0;
        for(Space space : spaces){
            if(space.getPlayer() == player){
                count++;
            }
        }
        return 1 + (count / 2);
    }

    boolean isWinner() {
        Player winner = spaces.get(0).getPlayer();
        for(Space space : spaces){
            if(!(space.getPlayer() == winner)){
                return false;
            }
        }
        return true;
    }

    boolean isPlayerOut(Player player){
        for(Space space : spaces){
            if(space.getPlayer() == player){
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the a space is a neighbour to the current selected space.
     * @param space A space that is not selectedSpace.
     * @return If the space is a neighbour.
     */
    boolean isNeighbours(Space space){
        if (selectedSpace != null){
            return neighbours[selectedSpace.getId()][space.getId()] == 1;
        }
        return false;
    }

    /**
     * Get amount of neighbours of a space. Mainly used for tests.
     * @param id Which space to get amount of neighbours from.
     * @return Amount of neighbours
     */
    int getNumberOfNeighbours(int id){
        int count = 0;
        for(int i = 0; i < neighbours[id].length; i++){
            count += neighbours[id][i];
        }
        return count;
    }


    /**
     * Method that finds a space on the board based on id.
     * @param id id-number of the space
     * @return The space if id exists, else null
     */
    Space findSpace(int id)
    {
        for (Space space : spaces) {
            if (space.getId() == id) {
                return space;
            }
        }
        return null;
    }
    void resetSpaces()
    {
        selectedSpace = null;
        selectedSpace2 = null;
    }

    /**
     * This method initialises all the colors on the spaces that the board starts with
     * @return It then returns the colors
     */
    List<Color> getColorOnAllSpaces()
    {
        List<Color> colors = new ArrayList<>();
        for (Space space : spaces) {
            colors.add(space.getPlayer().getColor());
        }
        return colors;
    }

    Space getSpace(int id){
        return spaces.get(id);
    }
}
