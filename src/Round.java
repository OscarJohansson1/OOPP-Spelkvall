import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Round {

    Player currentPlayer;
    Phase currentPhase;
    MapController mapController;


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

    public Round(Player currentPlayer){

        this.currentPlayer = currentPlayer;
        this.mapController = mapController;
    }

    //Ge en random spelare första turen

    public void startRound(Player currentPlayer){

        //Deploymentfasen, kastar in i deployments loop
        currentPhase = currentPhase.next;
        //Deployment.startDeploying();

        //Attackfasen, kastar in i attackloopen
        currentPhase = currentPhase.next;
        startAttacking(currentPlayer);

        //Movementfasen, kastar in i movementloopen, inväntar rörelseorder
        currentPhase = currentPhase.next;
        //Movement.startMoving();

    }

    public void startAttacking(Player currentPlayer){

        while(!mapController.getSkipCheck()){



            while(!mapController.getSkipCheck()){



            }

            while(!mapController.getSkipCheck()){



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

    }

    //Chans skriver startRound(player); och så ska Round sköta resten.
    //START FÖR ROUND HÄR

    /*
    Round kallas på när man ska manipulera spelflödet och dess faser.
    I Chans finns gameloopen. Chans sätter igång en Round som i sin tur togglar igenom alla faser tills Round är slut.
    Round får spelare från Chans när den skapar en Round.
    Switcha spelare efter

    I Chans:

        /*

    Första spelaren väljs randomly

    //Gameloop

        //Byt till nästa spelare
        //Round startar, Round togglar genom alla faser
            //Startar Deploy
            //Startar Attack
            //Startar Move
        //Round är färdig.

        //Är spelet avslutat? Win conditions met?

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
