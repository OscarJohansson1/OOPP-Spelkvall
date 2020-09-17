import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Round {

    Player currentPlayer;
    Phase currentPhase = Phase.DEPLOY;
    MapController mapController;
    Space mySpace;
    Space oppSpace;
    Deployment deployment = new Deployment();
    Attack attack = new Attack();
    Movement movement = new Movement(this);


    public enum Phase {

        DEPLOY, ATTACK, MOVE, END;

        private Phase next;

        static {
            DEPLOY.next = ATTACK;
            ATTACK.next = MOVE;
            MOVE.next = END;
        }

        public Phase getNextPhase() {
            return next;
        }

    }

    public Round(Player currentPlayer, MapController mapController){

        this.currentPlayer = currentPlayer;
        this.mapController = mapController;

    }

    //Ge en random spelare första turen

    public void nextPhase(){

        //Byter till nästa fas

        if(currentPhase == Phase.MOVE)
        {
            currentPhase = Phase.DEPLOY;
            if(currentPlayer == mapController.player1)
            {
                currentPlayer = mapController.player2;
            }
            else {
                currentPlayer = mapController.player1;
            }
            mapController.phaseText.setText(currentPhase.name());
            return;

        }
        currentPhase = currentPhase.next;
        mapController.phaseText.setText(currentPhase.name());
        switch (currentPhase)
        {
            case DEPLOY:
                mapController.rootpane.getChildren().get(mapController.getChildren().indexOf(mapController.deployPhase)).toFront();
                break;
            case ATTACK:
                mapController.rootpane.getChildren().get(mapController.getChildren().indexOf(mapController.attackPhase)).toFront();
                break;
            case MOVE:
                mapController.rootpane.getChildren().get(mapController.getChildren().indexOf(mapController.movePhase)).toFront();
                break;
            case END:
                break;
        }

    }
    public void nextMove()
    {
        switch (currentPhase)
        {
            case DEPLOY:
                startDeployment();
                mapController.deployPhase.toFront();
                System.out.println("Started deploying");
                break;
            case ATTACK:
                startAttacking();
                mapController.attackPhase.toFront();
                System.out.println("Started attacking");
                break;
            case MOVE:
                startMoving();
                mapController.movePhase.toFront();
                System.out.println("Started moving");
                break;
            case END:
                break;
        }
    }
    public void startDeployment()
    {
        if(mapController.getSelectedSpace() != null && currentPlayer.getUnits() > 0) {

            mySpace = mapController.getSelectedSpace();
            if (mySpace != null ) {

                deployment.startDeployment(mySpace, currentPlayer);
                mapController.updateText();
                mySpace = null;
                oppSpace = null;
            }
        }
        if(currentPlayer.getUnits() == 0){
            nextPhase();
        }

    }
    public void startAttacking(){
        if(mapController.getSelectedSpace() != null)
        {
            if((currentPlayer == mapController.getSelectedSpace().getPlayer()) && mySpace == null){
                //Signalera fel
                mySpace = mapController.getSelectedSpace();
                return;
            }
            else if (!(currentPlayer == mapController.getSelectedSpace().getPlayer()) && oppSpace == null) {
                //Signalera fel
                oppSpace = mapController.getSelectedSpace();

            }
            if(attack.DeclareAttack(mySpace, oppSpace, mySpace.getUnits()) && mySpace != null && oppSpace != null && mySpace != oppSpace) {

                if(1 == attack.calculateAttack(mySpace, oppSpace)){
                    mapController.updateColor();
                    mapController.updateText();
                }

                mySpace = null;
                oppSpace = null;
                return;
            }else{
                oppSpace = null;
                return;
            }

        }
    }
    public void startMoving()
    {
        if(mapController.getSelectedSpace() != null)
        {
            if((currentPlayer == mapController.getSelectedSpace().getPlayer()) && mySpace == null){
                //Signalera fel
                mySpace = mapController.getSelectedSpace();
                return;
            }
            else if ((currentPlayer == mapController.getSelectedSpace().getPlayer()) && oppSpace == null) {
                //Signalera fel
                oppSpace = mapController.getSelectedSpace();

            }
            if(mySpace != null && oppSpace != null)
            {
                movement.MoveUnits(mySpace,oppSpace);
                mapController.updateText();
                mySpace = null;
                oppSpace = null;
            }





        }
    }



    /*
        while(){        // Attackloop

        -if- Vill du fortsätta attackera? Om knappen är nedtryckt?

            while(true){
            -choose- din space att attackera ifrån
            currentPlayer == Space.getPlayer;

            mySpace?

            }

            while(true){
            -choose- deras space att attackera
            currentPlayer == Space.getPlayer;

            theirSpace
            }

            -control- att attacken är giltig
            declareAttack

            -execute-
            calculateAttack

        -present-
        Nu kommer vi köra i terminalen. Sen kommer det utvecklas.

    }


        }
    */

    public Color getCurrentPlayerColor() {
        return currentPlayer.getColor();
    }

    public Player getCurrentPlayer(){
        return currentPlayer;
    }

    public Phase getCurrentPhase(){
        return currentPhase;
    }



}
