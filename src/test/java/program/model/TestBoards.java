package program.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for ChalmersBoard and BoardManager using the TestBoard
 */
public class TestBoards {

    Player player1;
    Player player2;

    Space space1;
    Space space2;
    Space space3;
    Space space4;
    Space space5;
    Space space6;

    Area area1;
    Area area2;
    Area area3;

    List<Space> spaces;
    List<Area> areas;
    BoardManager board;
    CreateTestBoard testBoard;


    /**
     * SetUp needed for testing TestBoards
     */
    @Before
    public void before() {
        player1 = new Player(10, 1, "#123123", "hej", "hej");
        player2 = new Player(10, 2, "#123123", "hej", "hej");

        space1 = new Space(0, player1, 10, "Test");
        space2 = new Space(1, player1, 2, "Test");
        space3 = new Space(2, player1, 10, "Test");
        space4 = new Space(3, player1, 2, "Test");
        space5 = new Space(4, player1, 10, "Test");
        space6 = new Space(5, player1, 2, "Test");
        spaces = new ArrayList<>(Arrays.asList(space1, space2, space3, space4, space5, space6));

        area1 = new Area("area1", 5, Arrays.asList(spaces.get(0), spaces.get(1)));
        area2 = new Area("area2", 5, Arrays.asList(spaces.get(2), spaces.get(3)));
        area3 = new Area("area3", 5, Arrays.asList(spaces.get(4), spaces.get(5)));
        areas = Arrays.asList(area1, area2);

        testBoard = new CreateTestBoard(spaces);
        board = new BoardManager(testBoard);
    }

    /**
     * Test if a player can win
     */
    @Test
    public void testWin() {
        assertTrue(board.isWinner(), "Player1 has won");
        space6.updateSpace(player2, 20);
        assertFalse(board.isWinner(), "There is a winner when it shouldn't be any winner");
    }

    /**
     * Test if a player can be removed
     */
    @Test
    public void testRemovePlayer() {
        assertTrue(board.isPlayerOut(player2), "Player 2 should be removed but isn't");
        space6.updateSpace(player2, 20);
        assertFalse(board.isPlayerOut(player2), "Player 2 is removed but shouldn't be");
    }

    /**
     * Test if two spaces can be neighbours
     */
    @Test
    public void testIsNeighbours() {
        assertTrue(testBoard.isNeighbours(spaces.get(0).getId(), spaces.get(1).getId()), "Space 1 and 2 should be neighbours but weren't");
        assertTrue(testBoard.isNeighbours(spaces.get(4).getId(), spaces.get(5).getId()), "Space 5 and 6 should be neighbours but weren't");
        assertFalse(testBoard.isNeighbours(spaces.get(2).getId(), spaces.get(3).getId()), "Space 3 and 4 shouldn't be neighbours but were");
    }

    /**
     * Test if areas can be created
     */
    @Test
    public void testCreateAreas() {
        assertTrue(area1.getSpaces().contains(space1) && area1.getSpaces().contains(space2), "Space1 and 2 should be a part of area1 but isn't");
        assertFalse(area1.getSpaces().contains(space2) && area1.getSpaces().contains(space3), "Space1 and 2 shouldn't be a part of area1 but is");
    }
}
