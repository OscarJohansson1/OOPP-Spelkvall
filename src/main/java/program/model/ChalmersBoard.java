package program.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * This class represents a concrete implementation of the board of the campus of Chalmers.
 */
public class ChalmersBoard implements IBoard, Serializable {

    private List<Area> areas;
    private final List<Space> spaces;

    /**
     * Indexes in the neighbours-matrix represent the following spaces:
     * 0 Hubben
     * 1 Basen
     * 2 Kajsabaren
     * 3 Zaloonen
     * 4 Winden
     * 5 LofTDet
     * 6 Röda Rummet
     * 7 Verum
     * 8 Villan
     * 9 A-dammen
     * 10 Focus
     * 11 Fort NOx
     * 12 GT Spritis
     * 13 Golden I
     * 14 Chabo
     * 15 Wijkanders
     * 16 Hyddan
     * 17 11:an
     * 18 Spektrum
     * 19 Gasquen
     * 20 Chalmersplatsen
     * 21 Olgas
     * 22 RunAn
     * 23 Tågvagnen
     * 24 Origogården
     * 25 Kalle Glader
     * 26 Tvärgatan
     */
    private final int[][] neighbours = {
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

    public ChalmersBoard(List<Space> spaces) {
        this.spaces = spaces;
        createAreas();
    }

    private void createAreas() {
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
     * Method that takes in two id (of spaces) and checks if they are neighbours.
     *
     * @param firstIndex  The index of the first space.
     * @param secondIndex The index of the second space.
     * @return If they are neighbours or not.
     */
    @Override
    public boolean isNeighbours(int firstIndex, int secondIndex) {
        return neighbours[firstIndex][secondIndex] == 1;
    }

    @Override
    public List<Area> getAreas() {
        return List.copyOf(areas);
    }

    @Override
    public List<Space> getSpaces() {
        return List.copyOf(spaces);
    }
}
