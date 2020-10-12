package program.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.Before;
import org.junit.Test;


public class TestBoard {

    Board board = new Board(null);

    @Test
    public void testNeighboursMatrix() {
        int[] neighbours = {3, 3, 3, 3, 5, 4, 3, 3, 2, 3, 4, 3, 4, 1, 5, 3, 3, 2, 2, 3, 3, 4, 3, 3, 4, 3, 4};
        for(int i = 0; i < 27; i++){
            assertEquals(neighbours[i], board.getNumberOfNeighbours(i), "Wrong amount of neighbours in " + i);
        }
    }

    @Test
    public void testIsNeighbour() {
        board.selectedSpace = new Space(0, null, 0, null);
        assertTrue(board.isNeighbours(new Space(1, null, 0, null)), "Hubben and Basen is no longer neighbours");
    }

}
