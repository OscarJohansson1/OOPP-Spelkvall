package server.code;

import program.model.Board;
import program.model.ModelDataHandler;
import program.model.Player;
import program.model.Space;

import java.util.ArrayList;
import java.util.List;

public class ServerBoardController {

    public Board gameBoard;
    private final ModelDataHandler modelDataHandler = ModelDataHandler.getModelDataHandler();
    private Space selectedSpace;
    private Space selectSpace2;
    public void initialize(){
        gameBoard = new Board(modelDataHandler.randomizeSpaces(modelDataHandler.spaceNames.size()));
        gameBoard.createAreas();
    }
    public Board getBoard(){
        return gameBoard;
    }
    public Space getSelectedSpace(){
        return selectedSpace;
    }
    public Space getSelectSpace2(){
        return selectSpace2;
    }


}
