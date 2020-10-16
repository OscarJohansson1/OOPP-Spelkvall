package server.code;

import program.model.*;

import java.util.ArrayList;
import java.util.List;

public class ServerBoardController {

    private Board gameBoard;
    private Space selectedSpace;
    private Space selectSpace2;
    public void initialize(List<User> users){
        ModelDataHandler modelDataHandler = ModelDataHandler.getModelDataHandler();
        gameBoard = new Board(modelDataHandler.randomizeSpaces(modelDataHandler.spaceNames.size(), users));
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
