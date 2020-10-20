package program.model;

import java.util.List;

interface IBoard {

    boolean isNeighbours(int firstIndex, int secondIndex);

    List<Area> getAreas();

    List<Space> getSpaces();

}
