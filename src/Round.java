import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Round {

    Player currentPlayer;
    Phase currentPhase = Phase.DEPLOY;
    MapController mapController;
    Space mySpace;
    Space oppSpace;
    Attack attack = new Attack();


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

    public void startRound(Player currentPlayer){

        //Deploymentfasen, kastar in i deployments loop
        mapController.phaseText.setText(currentPhase.name());
        currentPhase = currentPhase.next;
        //Deployment.startDeploying();
        mapController.phaseText.setText(currentPhase.name());
        //Attackfasen, kastar in i attackloopen
        currentPhase = currentPhase.next;
        startAttacking(currentPlayer);
        mapController.phaseText.setText(currentPhase.name());
        //Movementfasen, kastar in i movementloopen, inväntar rörelseorder
        currentPhase = currentPhase.next;

        //Movement.startMoving();

    }

    public void startAttacking(Player currentPlayer){

        while(!mapController.getSkipCheck()){



            while(!mapController.getSkipCheck() && mySpace == null){

                if(!(currentPlayer == mapController.getSelectedSpace().getPlayer())){
                    //Signalera fel
                    mySpace = mapController.getSelectedSpace();
                    break;
                }

            }

            while(!mapController.getSkipCheck() && oppSpace == null){

                if(currentPlayer == mapController.getSelectedSpace().getPlayer()){
                    //Signalera fel
                    oppSpace = mapController.getSelectedSpace();
                    break;
                }

            }

            if(attack.DeclareAttack(mySpace, oppSpace, mySpace.getUnits())) {

                attack.calculateAttack(mySpace, oppSpace);

                mySpace = null;
                oppSpace = null;

            }else{
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
