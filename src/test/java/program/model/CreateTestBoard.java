package program.model;

import java.util.Arrays;
import java.util.List;

/**
 * This is a concrete implementation of a board created to make testing easier.
 */
public class CreateTestBoard implements IBoard {

    private List<Area> areas;
    private final List<Space> spaces;

    /**
     * This is a matrix over the spaces that should be neighbours
     */
    private final int[][] neighbours = {
            {0, 1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 1, 0}
    };

    /**
     * Creates a TestBoard
     *
     * @param spaces A list of all the spaces
     */
    public CreateTestBoard(List<Space> spaces) {
        this.spaces = spaces;
        createAreas();
    }

    /**
     * Creates areas for the TestBoard
     */
    public void createAreas() {
        Area area1 = new Area("area1", 5, Arrays.asList(spaces.get(0), spaces.get(1)));
        Area area2 = new Area("area2", 5, Arrays.asList(spaces.get(2), spaces.get(3)));
        Area area3 = new Area("area3", 5, Arrays.asList(spaces.get(4), spaces.get(5)));

        areas = Arrays.asList(area1, area2, area3);
    }

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
