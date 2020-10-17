package server.code.controller;

import program.model.*;

import java.util.ArrayList;
import java.util.List;

public class GameController {

    private Board gameBoard;

    public void initializeGame(List<Player> players) {
        ModelDataHandler modelDataHandler = ModelDataHandler.getModelDataHandler();
        gameBoard = new Board(modelDataHandler.randomizeSpaces(modelDataHandler.spaceNames.size(), players));
        gameBoard.createAreas();
    }

    public Board getBoard() {
        return gameBoard;
    }

}
