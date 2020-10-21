package program.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestWin {

    Player player1;
    Player player2;

    Space space1;
    Space space2;
    Space space3;
    Space space4;
    Space space5;
    Space space6;

    List<Space> spaces;

    @Before
    public void before(){

        player1 = new Player(10,1,"#123123");
        player2 = new Player(10,2, "#123123");

        space1 = new Space(0, player1, 10, "Test");
        space2 = new Space(1, player1, 2, "Test");
        space3 = new Space(2, player1, 10, "Test");
        space4 = new Space(3, player1, 2, "Test");
        space5 = new Space(4, player1, 10, "Test");
        space6 = new Space(5, player1, 2, "Test");

        spaces = new ArrayList<>(Arrays.asList(space1, space2, space3, space4, space5, space6));
    }

    @Test
    public void testWin() {
        BoardManager board = new BoardManager(new ChalmersBoard(spaces));

        assertTrue(board.isWinner());
    }

    @Test
    public void testRemovePlayer() {
        BoardManager board = new BoardManager(new ChalmersBoard(spaces));

        assertTrue(board.isPlayerOut(player2));

    }

    //@Test(expected = IllegalArgumentException.class)
    //public void testGetBadIndex() {
        // Test
    //}

}
